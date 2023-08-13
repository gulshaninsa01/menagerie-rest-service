package com.menagerie.exception.custom;

public class ResourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7957241048119029544L;

	public ResourseNotFoundException(String s) {
		super(s);
	}

}
