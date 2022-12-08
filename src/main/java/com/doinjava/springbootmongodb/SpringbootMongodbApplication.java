package com.doinjava.springbootmongodb;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.doinjava.springbootmongodb.entities.Address;
import com.doinjava.springbootmongodb.entities.GENDER;
import com.doinjava.springbootmongodb.entities.Student;
import com.doinjava.springbootmongodb.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
	
		return args->{
			Address address=new Address();
			address.setCountry("India");
			address.setCity("Sabalsinghapur");
			address.setPincode("654321");
			Student student=new Student();
			student.setFirstName("Sefina Begum");
			student.setLastName("NA");
			student.setEmail("sefina.begum@gmail.com");
			student.setAddress(address);
			student.setGender(GENDER.FEMALE);
			student.setFavouriteSubject(Arrays.asList("HomeScience","Bengali","Maths"));
			student.setStudentActive(false);
			student.setTotalSpentInBooks(BigDecimal.TEN);
			
			
			//insertUsingMongoTemplateQuery(student,mongoTemplate,repository);
			Optional<Student> s=repository.findStudentByEmail(student.getEmail());
			if(s.isPresent()){
				System.out.println("Alreday exists with same email : "+s.get());
			}
			else {
				
				repository.insert(student);
			}
			
			
		};
	}

	private void insertUsingMongoTemplateQuery(Student student, MongoTemplate mongoTemplate, StudentRepository repository) {
		Query query=new Query();
		query.addCriteria(Criteria.where("email").is(student.getEmail()));
		List<Student> students=mongoTemplate.find(query, Student.class);
		
		if(students.size()>1) {
			throw new IllegalStateException("Found many students with same email : "+student.getEmail());
		}
		
		if(students.isEmpty())
		repository.insert(student);
		else {
			System.out.println("Student with same email already exists : "+students.get(0));
		}
	}
}
