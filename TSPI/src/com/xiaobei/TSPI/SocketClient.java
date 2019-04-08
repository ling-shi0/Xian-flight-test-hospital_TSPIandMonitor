package com.xiaobei.TSPI;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


public class SocketClient {

    /**
     * Socket
     */
	public SocketClient() {
	}
    public void SocketSendMes(Map<String,String> map) throws SocketException, UnknownHostException {
    	DatagramSocket s = new DatagramSocket();        
        byte[] bs =map.toString().getBytes(); 
        DatagramPacket dp = new DatagramPacket(bs, bs.length, InetAddress.getByName("192.168.1.13"), 8888);
        try {
            s.send(dp);
            System.out.println("我发完了！");
        } catch (IOException e) {
            System.out.println("发送失败： ");
            e.printStackTrace();
        }
        s.close();
    }
    public static void main(String[] args) throws SocketException, UnknownHostException {
    	Map<String,String> mes=new HashMap<String,String>();
    	mes.put("locationB", "emteym58");
		mes.put("locationL", "13teyj7");
		mes.put("locationH", "3ygffgh");
		mes.put("gesA", "24gfh");
		mes.put("gesE", "0.5878");
		mes.put("gesR", "2t4354000");
		mes.put("acceleration","8435    m/s2" );
		mes.put("angAcceleration", "306.5   rad/s2");
		mes.put("name", "TSP");
		new SocketClient().SocketSendMes(mes);
		System.out.println("我发完了");
    }
}
