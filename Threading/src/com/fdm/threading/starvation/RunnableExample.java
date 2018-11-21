package com.fdm.threading.starvation;

public class RunnableExample implements Runnable {
	
	private String name;

	public RunnableExample(String name) {
		super();
		this.name = name;
	}
	
	
	@Override
	public void run() {
		synchronized (name) {
			while(true)
			{
				System.out.println("Runnable Running");
			}
		}
		
	}



}
