package algorithm.some_implementation.web_program;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;




public class Server {

	public static ArrayList<Socket>userList=new ArrayList<Socket>();

	public static void main(String args[]) {
		ServerSocket server = null;
		Socket Conn = null;
		InetAddress ip = null;
		while (true) {
			try {
				server = new ServerSocket(5555);
			} catch (IOException e1) {
				// TODO: handle exception
				System.out.println("正在监听");
			}
			try {
				System.out.println("等待客户呼叫");
				Conn = server.accept();
				add(Conn);			
				System.out.println("客户的地址：" + Conn.getInetAddress());
				ip = Conn.getInetAddress();
				counter.counter++;
				System.out.println("在线人数："+counter.counter);
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("正在等待客户");
			}
			if (Conn != null) {
				new ServerThread(Conn,userList).start(); // 为每个用户启动一个专门线程
			}
		}
	}
	public synchronized static void add(Socket Conn){		//对全局变量加锁
		userList.add(Conn);
	}

}

class ServerThread extends Thread {
	Socket socket;
	DataOutputStream out = null;
	DataInputStream in = null;
	String s = null;
	ArrayList<Socket>userList;
	InetAddress ip = null;
	ServerThread(Socket t,ArrayList<Socket> userList) {
		this.userList=userList;
		socket = t;
		
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void run() {
		while (true) {
			try {
				String s = in.readUTF(); // 阻塞
				
				for(int i=0;i<userList.size();i++)
				{
					out = new DataOutputStream(userList.get(i).getOutputStream());
					out.writeUTF(s);
				}
			
			} catch (IOException e) {
				System.out.println("客户离开");
				counter.counter--;
				System.out.println("在线人数："+counter.counter);
				return;
			}
		}
	}
}
class counter{
	public static int counter = 0;
}