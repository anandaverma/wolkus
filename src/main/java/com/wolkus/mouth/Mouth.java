package com.wolkus.mouth;

public class Mouth implements Runnable {

	private Thread t;
	private String threadName = "Mouth";

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
