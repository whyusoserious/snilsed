package net.javaguides.usermanagmenet.model;

public class User {
	private int id;
	private long number;
		
	
	public User(int id, long number) {
		super();
		this.id = id;
		this.number = number;
	}
	

	public User(long number) {
		super();
		this.number = number;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
}
