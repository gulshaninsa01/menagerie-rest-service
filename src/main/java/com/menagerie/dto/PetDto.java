package com.menagerie.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.menagerie.constant.Sex;
import com.menagerie.entity.Event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class PetDto implements Serializable{

	private static final long serialVersionUID = -2156409389621586934L;
	
	private Integer id;
	private String name;
	private String owner;
	private String species;
	private Sex sex;
	private LocalDate birth;
	private LocalDate death;
	List<Event> events;	

}
