package com.menagerie.exception.custom;

public class AlreadyExistDataExceptioin extends RuntimeException {

	private static final long serialVersionUID = 1307360460679499924L;

	public AlreadyExistDataExceptioin(String s) {
		super(s);
	}

}
