package com.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Test_04_UDPServer {
	public void serverRun(){
		DatagramSocket socket = null;
		DatagramPacket inPacket = null;
		DatagramPacket outPacket = null;
		
		byte[] inMsg = new byte[10];
		byte[] outMsg  = null;
		
		try {
			socket = new DatagramSocket(7777);
			
			//�����͸� �����ϱ� ���� ��Ŷ
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			//��Ŷ�� ���� �����͸� ����
			socket.receive(inPacket);
			
			//������ ��Ŷ���κ��� Ŭ���̾�Ʈ�� IP�� Port�� ��´�
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			
			for(int inx=0;inx<100;inx++){
				
				outMsg = ("Hello from server! : "+inx).getBytes();
				
				//��Ŷ�� �����ؼ� Ŭ���̾�Ʈ�� ����
				outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
				socket.send(outPacket);
				Thread.sleep(1000);
			}
			
		} catch (SocketException e) {
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
		Test_04_UDPServer obj = new Test_04_UDPServer();
		obj.serverRun();
	}
}
