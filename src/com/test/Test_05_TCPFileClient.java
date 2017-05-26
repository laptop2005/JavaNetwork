package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test_05_TCPFileClient {
public void clientRun(){
		
		Socket socket = null;
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			socket = new Socket("127.0.0.1",7777);
			
			is = socket.getInputStream();
			
			fos = new FileOutputStream(new File("C:/Users/Á¤¿ì¼®/Documents/test2.html"));
			
			byte[] tmp = new byte[1024];
			int size = 0;
			while((size = is.read(tmp))>0){
				fos.write(tmp);
			}
			
			
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
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Test_05_TCPFileClient obj = new Test_05_TCPFileClient();
		obj.clientRun();
	}
}
