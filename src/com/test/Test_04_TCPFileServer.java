package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test_04_TCPFileServer {
	
	
	public void serverRun(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		FileInputStream fis = null;
		OutputStream os = null;
		
		try {	
			fis = new FileInputStream(new File("C:/Users/정우석/Documents/test.html"));
			
			serverSocket = new ServerSocket(7777);
			
			socket = serverSocket.accept();
			
			os = socket.getOutputStream();
			
			//파일을 바이트 배열로 읽어오기 위한 변수
			byte[] tmp = new byte[1024];
			int size = 0;
			while((size = fis.read(tmp))>0){
				os.write(tmp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Test_04_TCPFileServer obj = new Test_04_TCPFileServer();
		obj.serverRun();
	}
}
