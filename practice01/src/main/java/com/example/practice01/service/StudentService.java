package com.example.practice01.service;

import com.example.activmq01.amq.AMQSender;
import com.example.practice01.docs.StudentDocument;
import com.example.practice01.entity.Adress;
import com.example.practice01.entity.Department;
import com.example.practice01.entity.Student;
import com.example.practice01.repository.jpa.AdressRepository;
import com.example.practice01.repository.jpa.DepartmentRepository;
import com.example.practice01.repository.jpa.StudentRepository;
import com.example.practice01.repository.es.EsStudentRepository;
import com.example.practice01.util.getJSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EsStudentRepository esStudentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AdressRepository addressRepository;

    @Autowired
    private getJSONObject getJSONObject;

    @Autowired
    private AMQSender amqSender;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    private StudentDocument saveStudentToElasticsearch(StudentDocument student) throws Exception {
        try {
            log.info("Saving student to Elasticsearch: {}", student);
            return esStudentRepository.save(student);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private Student saveStudentToDatabase(Student student) throws Exception {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public Student createAndUpdateStudent(Student student, Long id) throws Exception {
        // Ensure Department is managed
        if (student.getDepartment() != null && student.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(student.getDepartment().getId())
                    .orElseThrow(() -> new Exception("Department with id " + student.getDepartment().getId() + " not found."));
            student.setDepartment(department);
        }

        // Set student in each address before saving
        if (student.getAddresses() != null) {
            for (Adress address : student.getAddresses()) {
                address.setStudent(student);
            }
        }

        if (id == null) {
            log.info("Creating new student: {}", student);
            log.info("Saving student to database: {}", student);
            Student savedStudent = saveStudentToDatabase(student);
            if (savedStudent != null) {
                log.info("Successfully saved student to database: {}", savedStudent);
                log.info("Sending new student details to AMQ: {}", savedStudent);
                sendStudentDetailsToAmq(savedStudent);
                log.info("Saving student to elasticsearch: {}", savedStudent);
                StudentDocument studentJSON = getJSONObject.getESStudentObject(savedStudent);
                StudentDocument saveStudentToEs = saveStudentToElasticsearch(studentJSON);
                if (saveStudentToEs != null) {
                    log.info("Successfully saved student to Elasticsearch: {}", savedStudent);
                }
            }
            return savedStudent;
        } else {
            log.info("Updating student in with id {}: {}", id, student);
            log.info("Updating student in database", id, student);
            Optional<Student> existingStudent = studentRepository.findById(id);
            if (existingStudent.isPresent()) {
                Student studentJSON = getJSONObject.getStudentObject(student);
                studentJSON.setId(id); // Ensure the id is set for update
                log.info("Updating student in database: {}", studentJSON);
                Student savedStudent = saveStudentToDatabase(studentJSON);
                if (savedStudent != null) {
                    log.info("Successfully updated student in database: {}", savedStudent);
                    sendStudentDetailsToAmq(savedStudent);
                    log.info("Saving updated student to Elasticsearch: {}", savedStudent);
                    Optional<StudentDocument> esStudent = esStudentRepository.findById(id);
                    if (esStudent.isPresent()) {
                        StudentDocument studentDoc = getJSONObject.getESStudentObject(esStudent.get());
                        log.info("Updating student in Elasticsearch: {}", studentDoc);
                        saveStudentToElasticsearch(studentDoc);
                        log.info("Successfully updated student in Elasticsearch: {}", savedStudent);
                    } else {
                        log.warn("No existing student found in Elasticsearch with id: {}", id);
                    }
                }
                return savedStudent;
            } else {
                // If id is provided but not found, treat as new
                throw new Exception("Student with id " + id + " not found.");
            }
        }
    }

    private void sendStudentDetailsToAmq(Student student) {
        StringBuilder message = new StringBuilder();
        message.append('{')
                .append("\"id\":").append(student.getId()).append(',')
                .append("\"firstName\":\"").append(student.getFirstName()).append("\",")
                .append("\"middleName\":\"").append(student.getMiddleName()).append("\",")
                .append("\"lastName\":\"").append(student.getLastName()).append("\",")
                .append("\"dob\":\"").append(student.getDob()).append("\",")
                .append("\"gender\":\"").append(student.getGender()).append("\",")
                .append("\"departmentId\":").append(student.getDepartment() != null ? student.getDepartment().getId() : "null")
                .append('}');
        log.info("Sending student details to AMQ: {}", message);
        amqSender.sendMessage("student-queue", message.toString());
        log.info("Successfully sent student details to AMQ: {}", student.getId());

    }

    @JmsListener(destination = "student-queue")
    public void receiveStudent(String studentMessage) throws Exception {
        log.info("Received student message from AMQ: {}", studentMessage);
        log.info("Sending student message to email to send email");
        emailService.sendSimpleEmail(emailFrom, "Student Notification", "A new student has been added or updated: " + studentMessage);


    }


    public Student getStudentById(Long id) throws Exception {
        log.info("Fetching student from db with id: {}", id);
        Student getStudent = studentRepository.findById(id).orElse(null);
        if (getStudent != null) {
            log.info("Found student from db: {}", getStudent);
            return getStudent;
        } else {
            log.error("No student found with id in db: {}", id);
            throw new Exception("No student found with id in db: " + id);
        }

    }

    public List<Student> getAllStudents() {
        log.info("Fetching all students from db");
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) throws Exception {
        Student deleteStudent = getStudentById(id);
        if (deleteStudent != null) {
            log.info("Found student to delete from db: {}", deleteStudent);
            studentRepository.deleteById(id);
        }

    }

    public List<Student> getStudentsByDepartment(Long deptId) throws Exception {
        log.info("Fetching students from db for department with id: {}", deptId);
        List<Student> students = studentRepository.findByDepartmentId(deptId);
        if (students.isEmpty()) {
            log.warn("No students found in db for department with id: {}", deptId);
            throw new Exception("No students found in db for department with id: " + deptId);
        }
        log.info("Successfully fetched students from db for department with id: {}", deptId);
        return students;

    }

    public Iterable<StudentDocument> getAllStudentsFromEs() {
        log.info("Fetching all students from es");
        return esStudentRepository.findAll();
    }

    public StudentDocument getStudentFromEs(Long id) {
        log.info("Fetching student from Elasticsearch with id: {}", id);
        return esStudentRepository.findById(id).orElse(null);
    }

    public void deleteStudentFromEs(Long id) {
        Optional<StudentDocument> student = esStudentRepository.findById(id);
        if (student.isPresent()) {
            log.info("Deleting student from Elasticsearch with id: {}", id);
            esStudentRepository.deleteById(id);
            log.info("Successfully deleted student from Elasticsearch with id: {}", id);
        } else {
            log.warn("No student found in Elasticsearch with id: {}", id);
        }
    }
}
