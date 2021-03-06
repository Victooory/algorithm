package algorithm.some_implementation.web_program;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Socket mysocket = null;
		DataInputStream in= null;
		DataOutputStream out = null;
		Thread readData;
		//String username= null;
		Read read = null;
		try {
			mysocket = new Socket();
			read = new Read();
			readData = new Thread(read);       //负责读取信息的线程
			System.out.println("输入服务器的IP：");
			String IP = scanner.nextLine();
			System.out.println("输入用户名：");
			username.username = scanner.nextLine();
			//System.out.println("输入端口号");
			//int port = scanner.nextInt();
			int port = 5555;
			if(mysocket.isConnected()){}
			else{
				InetAddress address = InetAddress.getByName(IP);
				InetSocketAddress socketAddress = new InetSocketAddress(address,port);
				mysocket.connect(socketAddress);
				in = new DataInputStream(mysocket.getInputStream());
				out = new DataOutputStream(mysocket.getOutputStream());
				out.writeUTF("欢迎（"+username.username+"）进入聊天室");
				read.setDataInputStream(in);
				readData.start();			//启动负责读取信息的线程
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("服务器已断开：" + e);
		}
	
		System.out.println("：");
		while(scanner.hasNext()){
			String s = null;
			try {
				s = scanner.nextLine();
			} catch (InputMismatchException exp) {
				// TODO: handle exception
				System.exit(0);
			}
			try {
				out.writeUTF(username.username+":"+s);    //向服务器发送信息
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
class Read implements Runnable{
	DataInputStream in;
	public void setDataInputStream(DataInputStream in){
		this.in = in;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String s = null;
		while(true){
			try {
				s = in.readUTF();      //读取服务器发送的信息
				System.out.println(s);
				System.out.print("：");
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("与服务器断开连接"+e);
				break;
			}
		}
	}
}
class username{
	public static String username = null;
}
