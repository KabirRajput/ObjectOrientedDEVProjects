package com.fdm.threading.deadlock;

public class Runner {

	public static void main(String[] args) {
		Pen pen = new Pen();
		Board board = new Board();

		Darren darren = new Darren(pen, board);
		Wesley wesley = new Wesley(pen, board);

		Thread darrenThread = new Thread(darren);
		Thread wesleyThread = new Thread(wesley);

		darrenThread.start();
		wesleyThread.start();

	}
}
