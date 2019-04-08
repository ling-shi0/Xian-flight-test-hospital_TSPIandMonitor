package com.xiaobei.TSPI;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class StateListener extends Thread{
	public static TextMessage stringInThread;
	/**
	 * 这是开关按钮的消息监听线程方法
	 */
	@Override
	public void run(){
		
		  while(true){
			  try {
				  if(stringInThread.getText().equals("testchangeSwitch")){
					  System.out.println("接收到改变开关信号...");
//					  Main.changeSwitch();
					  break;
				  }
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		   
	}
}
