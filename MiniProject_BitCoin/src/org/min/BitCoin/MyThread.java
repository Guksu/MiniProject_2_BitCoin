package org.min.BitCoin;

public class MyThread extends RunCoin implements Runnable{


	@Override
	public void randomPrice() {
		super.randomPrice();
	}

	@Override
	public void run() {
		while(true)
		try {
			Thread.sleep(3000);
			System.out.println("");
			System.out.println("===================");
			System.out.println("현재 비트코인가격 :"+formatter.format(bitCoin.getBitCoinPrice()));
			System.out.println("현재 리플가격: "+formatter.format(ripple.getRipplePrice()));
			System.out.println("===================");
			randomPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
