package online.blickle.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String message;
	private int number;
	
	public ErrorMessage() {}
	
	public ErrorMessage(Exception ex) {
		number = 0;
		message = ex.getMessage();
	}
	
	public ErrorMessage(int number, Exception ex) {
		this.number = number;
		message = ex.getMessage();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
