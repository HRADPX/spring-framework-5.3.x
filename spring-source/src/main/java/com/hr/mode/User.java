package com.hr.mode;

public class User {

	public User() {

	}

	public User(int id, String name) {
		this(id, name, false);
	}

	public User(int id, String name, boolean exception) {
		this.id = id;
		this.name = name;
		this.exception = exception;
	}

	private int id;

	private String name;

	private boolean exception;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isException() {
		return exception;
	}

	public void setException(boolean exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
