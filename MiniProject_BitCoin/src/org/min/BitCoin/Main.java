package org.min.BitCoin;

public class Main {
	public static void main(String[] args) {

		RunCoin rich = new RunCoin();
		MyThread thread = new MyThread();
		Thread th = new Thread(thread);
		
		th.start();
		rich.play();
		
	}
}
