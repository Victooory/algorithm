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
				System.out.println("锟斤拷锟节硷拷锟斤拷");
			}
			try {
				System.out.println("锟饺达拷锟酵伙拷锟斤拷锟斤拷");
				Conn = server.accept();
				userList.add(Conn);
				System.out.println("锟酵伙拷锟侥碉拷址锟斤拷" + Conn.getInetAddress());
				ip = Conn.getInetAddress();
				counter.counter++;
				System.out.println("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷"+counter.counter);
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("锟斤拷锟节等达拷锟酵伙拷");
			}
			if (Conn != null) {
				new ServerThread(Conn,userList).start(); // 为每锟斤拷锟矫伙拷锟斤拷锟斤拷一锟斤拷专锟斤拷锟竭筹拷
			}
		}
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
				String s = in.readUTF(); // 锟斤拷锟斤拷
				
				for(int i=0;i<userList.size();i++)
				{
					out = new DataOutputStream(userList.get(i).getOutputStream());
					out.writeUTF(s);
				}
			
			} catch (IOException e) {
				System.out.println("锟酵伙拷锟诫开");
				counter.counter--;
				System.out.println("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷"+counter.counter);
				return;
			}
		}
	}
}
class counter{
	public static int counter = 0;
}