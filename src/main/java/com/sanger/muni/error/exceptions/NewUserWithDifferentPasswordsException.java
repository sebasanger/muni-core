package com.sanger.muni.error.exceptions;

public class NewUserWithDifferentPasswordsException extends RuntimeException {

	private static final long serialVersionUID = -7978601526802035152L;

	public NewUserWithDifferentPasswordsException() {
		super("Password not missmatch");
	}

}
