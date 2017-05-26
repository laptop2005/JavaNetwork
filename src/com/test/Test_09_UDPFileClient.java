package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test_09_UDPFileClient {
	public void clientRun(){
		DatagramSocket socket = null;
		InetAddress address = null;
		DatagramPacket outPacket = null;
		DatagramPacket inPacket = null;
		
		FileOutputStream fos = null;
		
		byte[] msg = new byte[100];
		byte[] recieveMsg = new byte[100];
		byte[] data = new byte[1024*60];
		
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName("127.0.0.1");
			
			
			outPacket = new DatagramPacket(msg, 1, address, 7777);
			inPacket = new DatagramPacket(recieveMsg, recieveMsg.length);
			
			socket.send(outPacket);
			
			socket.receive(inPacket);
			long fileSize = Long.parseLong(new String(inPacket.getData()).trim());
			System.out.println(fileSize);
			
			fos = new FileOutputStream(new File("C:/UTIL/JDK/downFile.exe"));
			
			int cnt=0;
			long recieveFileSize = 0;
			do{
				outPacket = new DatagramPacket("continue".getBytes(), "continue".getBytes().length, address, 7777);
				inPacket = new DatagramPacket(data,data.length);
				socket.send(outPacket);
				socket.receive(inPacket);
				fos.write(inPacket.getData());
				recieveFileSize += inPacket.getLength();
				System.out.println(++cnt);
			}while(recieveFileSize<fileSize);
			
			outPacket = new DatagramPacket("complete".getBytes(), "complete".getBytes().length, address, 7777);
			socket.send(outPacket);
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket!=null){
				socket.close();
			}
		}
	}
	
	public static void main(String[] args) {
		Test_09_UDPFileClient client = new Test_09_UDPFileClient();
		client.clientRun();
	}
}
