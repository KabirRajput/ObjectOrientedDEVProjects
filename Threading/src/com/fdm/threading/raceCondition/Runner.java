package com.fdm.threading.raceCondition;

public class Runner {

	public static void main(String[] args) {

		String name = new String("name");
		String name1 = new String("name");
		String name2 = new String("name");

		ThreadExample threadExample = new ThreadExample(name1);
		
		
		RunnableExample runnableExample = new RunnableExample(name2);
		Thread runnableThread = new Thread(runnableExample);
		
		runnableThread.start();
		threadExample.start();
		new ThreadExample(name).start();
		new ThreadExample(name).start();
		new ThreadExample(name).start();
		
	}
}
