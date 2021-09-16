package org.min.BitCoin;

public class Main {
	public static void main(String[] args) {

		RunCoin rich = new RunCoin();
		Thread th = new Thread(rich);
		th.start();
		rich.play();
		
	}
}
