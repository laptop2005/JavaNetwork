package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Test_08_UDPFileServer {
	public void serverRun(){
		DatagramSocket socket = null;
		DatagramPacket inPacket = null;
		DatagramPacket outPacket = null;
		
		byte[] inMsg = new byte[100];
		byte[] outData  = new byte[1024*60];
		
		FileInputStream fis = null;
		
		try {
			socket = new DatagramSocket(7777);
			
			//데이터를 수신하기 위한 패킷
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			//패킷을 통해 데이터를 수신
			socket.receive(inPacket);
			
			//수신한 패킷으로부터 클라이언트의 IP와 Port를 얻는다
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			
			File file = new File("C:/UTIL/JDK/jdk-7u80-windows-x64.exe");
			fis = new FileInputStream(file);
			
			String fileSize = file.length()+"";
			outPacket = new DatagramPacket(fileSize.getBytes(), fileSize.getBytes().length, address, port);
			socket.send(outPacket);
			
			int cnt = 0;
			int size = 0;
			String receiveMsg = "continue";
			while((size=fis.read(outData))>0&&receiveMsg.trim().equals("continue")){
				//패킷을 생성해서 클라이언트에 전송
				outPacket = new DatagramPacket(outData, outData.length, address, port);
				inPacket = new DatagramPacket(inMsg, inMsg.length);
				socket.send(outPacket);
				socket.receive(inPacket);
				receiveMsg = new String(inPacket.getData());
				System.out.println(++cnt);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null){
				try {
					fis.close();
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
		Test_08_UDPFileServer server = new Test_08_UDPFileServer();
		server.serverRun();
	}
}
