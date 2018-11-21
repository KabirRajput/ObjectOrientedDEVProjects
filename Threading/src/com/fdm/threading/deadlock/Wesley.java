package com.fdm.threading.deadlock;

public class Wesley implements Runnable{

	private Pen pen;
	private Board board;

	public Wesley(Pen pen, Board board) {
		super();
		this.pen = pen;
		this.board = board;
	}

	@Override
	public void run() {
		while (true) {
				synchronized (board) {
					synchronized (pen) {
					System.out.println("darren is waiting");
				}
			}
		}
	}
}
