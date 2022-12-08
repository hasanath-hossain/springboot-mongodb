package com.doinjava.springbootmongodb.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.doinjava.springbootmongodb.entities.GENDER;
import com.doinjava.springbootmongodb.entities.Student;
import com.doinjava.springbootmongodb.repositories.StudentRepository;

@DataJpaTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository repository;
	
	@Test
	void ItShouldCheckStudentByEmail() {
		
		//given
		Student s=new Student();
		s.setFirstName("Sefina Begum");
		s.setLastName("NA");
		s.setEmail("sefina.begum@gmail.com");
		s.setGender(GENDER.FEMALE);
		
		repository.save(s);
		
		//when
		Optional<Student> ops=repository.findStudentByEmail("sefina.begum@gmail.com");
		
		//then		
		assertThat(ops.orElseGet(()->new Student()).getFirstName()).isEqualTo(s.getFirstName());
	}
}
