package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test_02_TCPServer {
	
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	
	private boolean isServerClose = true;
	
	public void serverRun(){
		
		
		try {	
			isServerClose = false;
			
			serverSocket = new ServerSocket(7777);
			
			socket = serverSocket.accept();
			
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			
			while(true){
				dos.writeUTF("Hello from server! : ");
				System.out.println(dis.readUTF());
				Thread.sleep(1000);
				if(isServerClose){
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void serverClose(){
		isServerClose = true;
		if(dos!=null){
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(dis!=null){
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Test_02_TCPServer obj = new Test_02_TCPServer();
		obj.serverRun();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj.serverClose();
	}
}
