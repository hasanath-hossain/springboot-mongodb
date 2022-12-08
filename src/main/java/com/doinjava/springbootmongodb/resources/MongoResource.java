package com.doinjava.springbootmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doinjava.springbootmongodb.entities.Student;
import com.doinjava.springbootmongodb.repositories.StudentRepository;

@RestController
public class MongoResource {
	
	@Autowired
	StudentRepository repository;
	
	@GetMapping("/mongo/students")
	public List<Student> getAllStudent() {
		
		return repository.findAll();
	}
	
	@GetMapping("/mongo/students/{id}")
	public Student getStudent(@PathVariable String id) {
		return repository.findById(id).get();
	}
	
	@GetMapping("/mongo/students/name/{fname}")
	public Student getStudentByName(@PathVariable String fname) {
		return repository.findByFirstName(fname).get();
	}
	
	@PutMapping("/mongo/students")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		Student s=repository.save(student);
		String message="Student details Updated Successfully.";
		if(s==null)
			message="Failed to update student details.";
			
		return ResponseEntity.ok().body(message);	
	}
	
	@PostMapping("/mongo/students")
	public ResponseEntity<String> createStudent(@RequestBody Student student){
		Student s=repository.save(student);
		String message="Student details Created Successfully.";
		if(s==null)
			message="Failed to create student details.";
			
		return ResponseEntity.ok().body(message);	
	}
	
	@DeleteMapping("/mongo/students/{id}")
	public ResponseEntity<String> createStudent(@PathVariable String id){
		repository.deleteById(id);
		String message="Student details deleted Successfully.";
		
		return ResponseEntity.ok().body(message);
	}

}
