package com.example.practice01.controller;

import com.example.practice01.entity.Student;
import com.example.practice01.service.ElasticSearchService;
import com.example.practice01.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/create")
    public ResponseEntity createStudent(@RequestBody Student createStudentPayload) {
        try {
            log.info("starting create student with payload: {}", createStudentPayload);
            Student student = studentService.createAndUpdateStudent(createStudentPayload, null);
            log.info("Student created successfully: {}", student);
            return ResponseEntity.ok(student);

        } catch (Exception e) {
            log.error("Error creating student: ", e);
            return ResponseEntity.badRequest().body("Error creating student: " + e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/update/{id}")
    public ResponseEntity updateStudent(@RequestBody Student updateStudentPayload, Long id) {
        try {
            log.info("starting update student with payload: {}, id: {}", updateStudentPayload, id);
            Student student = studentService.createAndUpdateStudent(updateStudentPayload, id);
            log.info("Student updated successfully: {}", student);
            return ResponseEntity.ok(student);

        } catch (Exception e) {
            log.error("Error updating student: ", e);
            return ResponseEntity.badRequest().body("Error updating student: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get/{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        try {
            log.info("Fetching student with id from db: {}", id);
            Student student = studentService.getStudentById(id);
            log.info("Found student: {}", student);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error fetching student from db: ", e);
            return ResponseEntity.badRequest().body("Error fetching student: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllStudents() {
        try {
            log.info("Fetching all students from db");
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            log.error("Error fetching all students from db: ", e);
            return ResponseEntity.badRequest().body("Error fetching all students from db: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        try {
            log.info("Deleting student with id: {}", id);
            studentService.deleteStudent(id);
            log.info("Student deleted successfully");
            return ResponseEntity.ok("Student deleted successfully from db");
        } catch (Exception e) {
            log.error("Error deleting student from db: ", e);
            return ResponseEntity.badRequest().body("Error deleting student from db: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get/{deptId}/students")
    public ResponseEntity getStudentsByDepartment(@PathVariable Long deptId) {
        try {
            log.info("Starting to fetch students for department with id: {}", deptId);
            return ResponseEntity.ok(studentService.getStudentsByDepartment(deptId));
        } catch (Exception e) {
            log.error("Error fetching students by department from db: ", e);
            return ResponseEntity.badRequest().body("Error fetching students by department from db: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/es/students")
    public ResponseEntity getStudentsFromEs() {
        try {
            log.info("Fetching all students from Elasticsearch");
            return ResponseEntity.ok(studentService.getAllStudentsFromEs());
        } catch (Exception e) {
            log.error("Error fetching all students from Elasticsearch: ", e);
            return ResponseEntity.badRequest().body("Error fetching all students from Elasticsearch: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/es/student/{id}")
    public ResponseEntity getStudentFromEs(@PathVariable Long id) {
        try {
            log.info("Fetching student with id: {} from Elasticsearch", id);
            return ResponseEntity.ok(studentService.getStudentFromEs(id));
        } catch (Exception e) {
            log.error("Error fetching student from Elasticsearch: ", e);
            return ResponseEntity.badRequest().body("Error fetching student from Elasticsearch: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/es/delete/{id}")
    public ResponseEntity deleteStudentFromEs(@PathVariable Long id) {
        try {
            log.info("Deleting student with id: {} from Elasticsearch", id);
            studentService.deleteStudentFromEs(id);
            log.info("Student deleted successfully from Elasticsearch");
            return ResponseEntity.ok("Student deleted successfully from Elasticsearch");
        } catch (Exception e) {
            log.error("Error deleting student from Elasticsearch: ", e);
            return ResponseEntity.badRequest().body("Error deleting student from Elasticsearch: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/es/matchAll")
    public ResponseEntity matchAllStudentsFromEs() {
        try {
            log.info("Fetching all students from Elasticsearch using matchAll query");
            return ResponseEntity.ok(elasticSearchService.matchAllStudentsWithQuery());
        } catch (Exception e) {
            log.error("Error fetching all students from Elasticsearch using matchAll query: ", e);
            return ResponseEntity.badRequest().body("Error fetching all students from Elasticsearch: " + e.getMessage());
        }
    }
}
