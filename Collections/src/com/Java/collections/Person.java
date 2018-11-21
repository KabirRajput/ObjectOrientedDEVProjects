package com.Java.collections;

import java.util.ArrayList;
import java.util.Collections;

public class Person {

	int age;
	String name;
	
	public Person(int age, String name) {
		super();
		this.age=age;
		this.name=name;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Person> personList = new ArrayList<>();
		personList.add(new Person(21, "Bob"));
		personList.add(new Person(24, "Carl"));
		personList.add(new Person(20, "Alice"));
	
		Collections.sort(personList);
		
		for(Person p : personList) {
			System.out.println(p);
		}
		
		Collections.sort(personList, new Person);
	}
}
