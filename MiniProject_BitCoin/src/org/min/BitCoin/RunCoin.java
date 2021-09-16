package org.min.BitCoin;

import java.text.DecimalFormat;
import java.util.Scanner;

public class RunCoin implements Runnable {
	DecimalFormat formatter = new  DecimalFormat("###,###.##");
	Scanner sc;
	BitCoin bitCoin ;
	Ripple ripple ;
	Money money;
	int haveBit =0;
	int haveRip =0;
	int haveBitMoney =0;
	int haveRipMoney =0;
	int bitRevenue = 0;
	int ripRevenue = 0;
	
	public RunCoin() {
		sc = new Scanner(System.in);
		bitCoin = new BitCoin();
		ripple = new Ripple();
		money = new Money();
		money.setStartMoney(10000000);
		bitCoin.setBitCoinPrice(100000);
		ripple.setRipplePrice(50000);
	}
	
	public void menu() {
		System.out.println("1. 코인구매");
		System.out.println("2. 판매");
		System.out.println("3. 현재자산");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
	}
	
	public void buyCoin() {
		System.out.println("어떤 코인을 구매하겠습니까?");
		System.out.println("[1. 비트코인\t2. 리플\t3. 구입취소]");
		int select1 = sc.nextInt();
		
		if(select1 == 1) {
			System.out.println("현재 비트코인 가격은 "+formatter.format(bitCoin.getBitCoinPrice()) +"원 입니다."+"\n비트코인을 얼마나 구입하겠습니까?");
			int buyCoinVolume = sc.nextInt();
			
			if(money.getNowMoney() >= (bitCoin.getBitCoinPrice()*buyCoinVolume) ) {
				bitCoin.setBuyBitCoinVolume(buyCoinVolume);
				haveBit +=buyCoinVolume;
				haveBitMoney += bitCoin.getBitCoinPrice()*buyCoinVolume;
				money.setTradeMoney(haveBitMoney,0);
			}
			else if(money.getNowMoney() < (bitCoin.getBitCoinPrice()*buyCoinVolume)) {
				System.out.println("소지금이 부족합니다.");
				buyCoin();
			}
		}
		else if(select1 ==2) {
			System.out.println("현재 리플 가격은 "+formatter.format(ripple.getRipplePrice()) +"원 입니다."+"\n리플을 얼마나 구입하겠습니까?");
			int buyCoinVolume = sc.nextInt();
			
			if(money.getNowMoney() >= (ripple.getRipplePrice()*buyCoinVolume)) {
				ripple.setBuyRippleVolume(buyCoinVolume);
				haveRip += buyCoinVolume;
				haveRipMoney += ripple.getRipplePrice()*buyCoinVolume;
				money.setTradeMoney(haveRipMoney,0);
			}
			else if (money.getNowMoney() < (ripple.getRipplePrice()*buyCoinVolume)) {
				System.out.println("소지금이 부족합니다.");
				buyCoin();
			}
		}
		else if (select1 ==3) {
			
		}
		else {
			System.out.println("다시 선택하세요");
			buyCoin();
		}
	}
	
	public void sellCoin() {
		System.out.println("어떤 코인을 팔겠습니까?");
		System.out.println("1. 비트코인\t2.리플");
		int select1 = sc.nextInt();
		
		if(select1 == 1) {
			System.out.println("현재 비트코인을"+haveBit+"개 가지고 있습니다.\n얼마나 파시겠습니까?");
			int select2 = sc.nextInt();
			
			if(select2 <=haveBit) {
				haveBit -= select2;
				haveBitMoney -= select2*bitCoin.getBitCoinPrice();   
				money.setTradeMoney(0, select2*bitCoin.getBitCoinPrice());
			}
			else if( select2 > haveBit) {
				System.out.println("다시 입력하세요");
				sellCoin();
			}
		}
		else if(select1 ==2) {
			System.out.println("현재 리플을"+haveRip+"개 가지고 있습니다.\n얼마나 파시겠습니까?");
			int select2 = sc.nextInt();
			
			if(select2 <=haveRip) {
				haveRip -= select2;
				haveRipMoney -= select2*ripple.getRipplePrice();
				money.setTradeMoney(0, select2*ripple.getRipplePrice());
			}
			else if( select2 > haveRip) {
				System.out.println("다시 입력하세요");
				sellCoin();
			}
		}
		else {
			System.out.println("다시 입력하세요");
			sellCoin();
		}
	}
	
	public void showMoney() {
		if(haveBit == 0) {
			bitRevenue = 0;
		}
		else {
			bitRevenue = (bitCoin.getBitCoinPrice()*haveBit)-haveBitMoney;
		}
		
		if(haveRip ==0) {
			ripRevenue = 0;
		}
		else {
			ripRevenue = (ripple.getRipplePrice()*haveRip)-haveRipMoney;
		}
		System.out.println("현재 소유금 : "+formatter.format((money.getNowMoney())));
		System.out.println("소유 비트코인 : "+formatter.format(haveBit)+", 현재손익: "+formatter.format(bitRevenue)+"원");
		System.out.println("소유 리플 : "+formatter.format(haveRip)+", 현재손익: "+formatter.format(ripRevenue)+"원");
	}
	
	public void exit() {
		System.out.println("프로그램을 종료합니다");
		sc.close();
		System.exit(0);
	}
	
	public void randomPrice() {
		bitCoin.setBitCoinPrice((100000+(int)(Math.random()*10000+1))-(int)(Math.random()*100000-1));
		ripple.setRipplePrice(50000+(int)(Math.random()*50000+1)-(int)(Math.random()*50000+1));
	}
	public void run() {
		while(true)
		try {
			Thread.sleep(5000);
			randomPrice();
			System.out.println("");
			System.out.println("===================");
			System.out.println("현재 비트코인가격 :"+formatter.format(bitCoin.getBitCoinPrice()));
			System.out.println("현재 리플가격: "+formatter.format(ripple.getRipplePrice()));
			System.out.println("===================");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		System.out.println("------------------");
		System.out.println("현재 비트코인가격 :"+formatter.format(bitCoin.getBitCoinPrice()));
		System.out.println("현재 리플가격: "+formatter.format(ripple.getRipplePrice()));
		System.out.println("------------------");
		while(true) {
			menu();
			System.out.print("작업을 선택하세요 >>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1 : buyCoin(); break;
			case 2 : sellCoin(); break;
			case 3 : showMoney(); break;
			case 0 : exit(); break;
			default : System.out.println("다시 선택하세요");
			}
		}
	}
}
