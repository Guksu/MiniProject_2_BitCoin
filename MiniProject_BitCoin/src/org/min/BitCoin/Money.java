package org.min.BitCoin;

public class Money {

	int startMoney;
	int nowMoney;
	int tradeMoney =0;
	
	public int getNowMoney() {
		return getStartMoney()+getTradeMoney();
	}

	public int getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(int buy, int sell) {
		this.tradeMoney = tradeMoney+sell-buy;
	}

	public int getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(int startMoney) {
		this.startMoney = startMoney;
	}
}
