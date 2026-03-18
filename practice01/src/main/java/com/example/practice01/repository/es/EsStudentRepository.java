package com.example.practice01.repository.es;

import com.example.practice01.docs.StudentDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface EsStudentRepository extends ElasticsearchRepository<StudentDocument, Long> {
    Optional<StudentDocument> findById(Long id);

    // Define custom query methods if needed
    // For example:
    // List<Student> findByDepartmentId(Long departmentId);

}
