package com.fdm.threading.deadlock;

public class Darren implements Runnable{

	private Pen pen;
	private Board board;
	
	public Darren(Pen pen, Board board) {
		super();
		this.pen = pen;
		this.board = board;
	}


	@Override 
	public void run() {
		while (true) {
			synchronized (pen) {
				synchronized (board) {
					System.out.println("darren is waiting");
				}
			}
		}
	}
}
