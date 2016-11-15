package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test_03_TCPClient {
	public void clientRun(){
		
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			socket = new Socket("127.0.0.1",7777);
			
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			
			for(int inx=0;inx<100;inx++){
				System.out.println(dis.readUTF());
				dos.writeUTF("Hello from client! : "+inx);
				Thread.sleep(1000);
			}
			
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
	}
	
	public static void main(String[] args) {
		Test_03_TCPClient obj = new Test_03_TCPClient();
		obj.clientRun();
	}
}
