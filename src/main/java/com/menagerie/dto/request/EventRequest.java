package com.menagerie.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EventRequest implements Serializable{
	
	private static final long serialVersionUID = -7385515473920616466L;

	@NotNull
	private LocalDate date;
	private String type;
	private String remark;

}
