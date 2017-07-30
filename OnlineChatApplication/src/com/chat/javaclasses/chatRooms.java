package com.chat.javaclasses;

import java.util.Stack;

public class chatRooms extends Stack{
	private String name;
	private String description;
	public chatRooms(String name, String description, int i) {
		this.name = name;
		this.description = description;
		setSize(4);
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
}
