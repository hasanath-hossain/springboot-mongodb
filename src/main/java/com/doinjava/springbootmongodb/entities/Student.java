package com.doinjava.springbootmongodb.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document()
@Data
public class Student {
	@Id
    private String id;
	private String firstName;
	private String lastName;
	@Indexed(unique=true)
	private String email;
	private GENDER gender;
	private Address address;
	private boolean isStudentActive;
	private List<String> favouriteSubject;
	private BigDecimal totalSpentInBooks;	
}
