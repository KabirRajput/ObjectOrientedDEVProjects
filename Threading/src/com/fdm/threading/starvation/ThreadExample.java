package com.fdm.threading.starvation;

public class ThreadExample extends Thread{

	private String name;

	public ThreadExample(String name) {
		super();
		this.name = name;
	}


	@Override
	public void run() {
		synchronized (name) {
			for(;;) {
				System.out.println("ThreadExample is currently running");
			}
		}

	}
}