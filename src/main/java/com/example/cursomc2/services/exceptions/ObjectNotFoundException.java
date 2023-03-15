package com.example.cursomc2.services.exceptions;

public class ObjectNotFoundException  extends RuntimeException{

	/**implementação de mensagem de exceções **/
	
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	

}
