package com.menagerie.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.menagerie.constant.Sex;

import lombok.Data;

@Data
public class PetUpdateRequest implements Serializable {

	private static final long serialVersionUID = -6996715960421665695L;

	@NotNull
	private Integer id;
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
