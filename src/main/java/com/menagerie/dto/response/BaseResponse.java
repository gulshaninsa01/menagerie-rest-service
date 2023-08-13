package com.menagerie.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -7407625989918520034L;

	@Builder.Default
	private final String timestamp = LocalDateTime.now().toString();
	private String status;
	private String message;
	private Map<String, String> fieldError;
	private Object data;

}