package com.fdm.threading.raceCondition;

public class ThreadExample extends Thread{

	private String name;

	public ThreadExample(String name) {
		super();
		this.name = name;
	}


	@Override
	public void run() {
		System.out.println("ThreadExample");

	}
}