package com.fdm.threading.raceCondition;

public class RunnableExample implements Runnable {
	
	private String name;

	public RunnableExample(String name) {
		super();
		this.name = name;
	}
	
	
	@Override
	public void run() {
		System.out.println("Runnable");
		
	}



}
