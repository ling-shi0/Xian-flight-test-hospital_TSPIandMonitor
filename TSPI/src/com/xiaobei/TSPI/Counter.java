package com.xiaobei.TSPI;

public class Counter {
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Counter() {
		super();
	}
	
	public void CountAdd1(){
		count += 1;
	}
}
