package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test_01_URLConnection {
	
	public void loadWebPage(){
		URL url = null;
		BufferedReader br = null;
		String address = "http://www.naver.com";
//		String address = "http://localhost:8080/05_DBIO_2/fileUpload.jsp";
		
		try {
			url = new URL(address);
			
			br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			
			String line = "";
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void fileDownload(){
		
		URL url = null;
		InputStream is = null;
		FileOutputStream out = null;
		
		String address = "http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif";
		
		int ch = 0;
		
		try {
			url = new URL(address);
			is = url.openStream();
			out = new FileOutputStream("C:/Users/Jeong Woo suk/Documents/naver_logo.gif");
			
			while((ch=is.read())!=-1){
				out.write(ch);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void fileFtpUpload(){
		//ftp��������
		String user = "";
		String password = "";
		String host = "";
		String remoteFile = "";
		
		//ftp Connection
		URL url = null;
		URLConnection m_client = null;
		
		//���ε忡 ����� ��Ʈ��
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		
		String localfilename = "";
		
		try {
			url = new URL("ftp://" + user + ":" + password + "@" + host + "/" + remoteFile + ";type=i");
			m_client = url.openConnection();
			
			is = new FileInputStream(localfilename);
			bis = new BufferedInputStream(is);
			os = m_client.getOutputStream();
			bos = new BufferedOutputStream(os);
			
			byte[] buffer = new byte[1024];
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(bos!=null){
					bos.close();
				}
				if(bis!=null){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fileFtpDownload(){
		//ftp��������
		String user = "";
		String password = "";
		String host = "";
		String remoteFile = "";
		
		//ftp Connection
		URL url = null;
		URLConnection m_client = null;
		
		//�ٿ�ε忡 ����� ��Ʈ��
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		
		String localfilename = "";
		
		try {
			url = new URL("ftp://" + user + ":" + password + "@" + host + "/" + remoteFile + ";type=i");
			m_client = url.openConnection();
			
			is = m_client.getInputStream();
			bis = new BufferedInputStream(is);
			os = new FileOutputStream(localfilename);
			bos = new BufferedOutputStream(os);
			
			byte[] buffer = new byte[1024];
			int readCount;
			
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(bos!=null){
					bos.close();
				}
				if(is!=null){
					is.close(); // close the FTP inputstream
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void fileUpload(){
		
		//URL����
		URL connectURL;
		HttpURLConnection conn;
		
		try {
			connectURL = new URL("http://localhost:8080/05_DBIO_2/fileUpload.jsp");
			//���ο� ������ ����.
			conn = (HttpURLConnection)connectURL.openConnection();
			//�б�� ���� ��� �����ϰ� ����
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			//ĳ�ø� ������� �ʰ� ����
			conn.setUseCaches(false); 
			
			//POSTŸ������ ����
			conn.setRequestMethod("POST"); 
			
			//��� ����
			conn.setRequestProperty("Connection","Keep-Alive"); 
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+"dkjsei40f9844djs8dviwdf"); 
			
			//Output��Ʈ���� ����
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream()); 
			dos.writeBytes("--" + "dkjsei40f9844djs8dviwdf" + "\r\n"); 
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"naver_logo.gif\"" + "\r\n"); 
			dos.writeBytes("\r\n"); 
			
			//���ۻ���� �����Ͽ� buffer�Ҵ�
			FileInputStream fileInputStream = new FileInputStream("C:/Users/Jeong Woo suk/Documents/naver_logo.gif");
			int bytesAvailable = fileInputStream.available(); 
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize); 
			byte[] buffer = new byte[bufferSize];
			
			//��Ʈ���� �ۼ�
			int bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
			while (bytesRead > 0) 
			{ 
				// Upload file part(s) 
				dos.write(buffer, 0, bufferSize); 
				bytesAvailable = fileInputStream.available(); 
				bufferSize = Math.min(bytesAvailable, maxBufferSize); 
				bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
			} 
			dos.writeBytes("\r\n"); 
			dos.writeBytes("--" + "dkjsei40f9844djs8dviwdf" + "--" + "\r\n"); 
			fileInputStream.close();
			
			//���� ���۸� stream�� ���.  
			dos.flush(); 
			
			//����. ����� ����.
//			InputStream is = conn.getInputStream(); 
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void fileUpload2(){
		
		File file = new File("C:/Users/Jeong Woo suk/Documents/naver_logo.gif");
		URL url;
		OutputStream out = null;
		FileInputStream fis = null;
		try {
			url = new URL("http://localhost:8080/05_DBIO_2/fileUpload.jsp");
			URLConnection httpConn = url.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestProperty("Content-type", "application/octet-stream");
			httpConn.setRequestProperty("Content-Length", String.valueOf(file.length()));
			
			out = httpConn.getOutputStream();
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int readcount = 0;
			while ((readcount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readcount);
			}
			out.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Test_01_URLConnection obj = new Test_01_URLConnection();
//		obj.loadWebPage();
//		obj.fileDownload();
		obj.fileUpload2();
	}
}
