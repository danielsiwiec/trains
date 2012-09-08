package com.danielsiwiec.trains;

public class MissingRouteException extends RuntimeException{

	private static final long serialVersionUID = 229665919362379458L;
	
	public MissingRouteException(){
		super();
	}
	
	public MissingRouteException(String message){
		super(message);
	}
}
