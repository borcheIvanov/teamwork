package mk.polarcape.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex){
		return ex.getMessage();
	}
}