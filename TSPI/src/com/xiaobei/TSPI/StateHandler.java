package com.xiaobei.TSPI;

public class StateHandler {
	public static int State=1;
	public static  void flipState() {
		if(State==4){
			State=1;
		}else{
			State++;
		}
	}
	
}
