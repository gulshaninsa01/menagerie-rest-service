package com.menagerie.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.menagerie.constant.Sex;

import lombok.Data;

@Data
public class PetRequest implements Serializable{

	private static final long serialVersionUID = -6179382533278318463L;
	
	@NotBlank
	private String name;
	@NotBlank
	private String owner;
	@NotBlank
	private String species;
	@NotNull
	private Sex sex;
	
	private LocalDate birth;
	
	private LocalDate death;

}
