package com.doinjava.springbootmongodb.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.doinjava.springbootmongodb.entities.Student;

@Repository
public interface StudentRepository extends  MongoRepository<Student, String>{

	Optional<Student> findByFirstName(String fname);
	Optional<Student> findStudentByEmail(String email);

}
