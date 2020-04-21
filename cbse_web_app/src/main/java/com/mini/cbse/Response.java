package com.mini.cbse;

public class Response{
	public boolean status;
	public String message;
	public Response(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
}