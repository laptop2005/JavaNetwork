package com.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test_07_UDPClient {
	public void clientRun(){
		DatagramSocket socket = null;
		InetAddress address = null;
		DatagramPacket outPacket = null;
		DatagramPacket inPacket = null;
		
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName("127.0.0.1");
			
			byte[] msg = new byte[100];
			
			outPacket = new DatagramPacket(msg, 1,address, 7777);
			inPacket = new DatagramPacket(msg, msg.length);
			
			socket.send(outPacket);
			
			for(int inx=0;inx<100;inx++){
				socket.receive(inPacket);
				System.out.println(new String(inPacket.getData()));
				Thread.sleep(1000);
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(socket!=null){
				socket.close();
			}
		}
	}
	
	public static void main(String[] args) {
		Test_07_UDPClient obj = new Test_07_UDPClient();
		obj.clientRun();
	}
}
