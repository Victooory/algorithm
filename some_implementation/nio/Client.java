package algorithm.some_implementation.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


public class Client implements Runnable{

	private static String ip =null;
	private static String name = null;
	private static String serverHost = "127.0.0.1"; //服务器地址
	private static int port = 5050;
	private static SocketChannel socket = null;
	
	public static void main(String[] args) {
		if(args.length>0){
			if(args[0].indexOf(':')== -1){
				System.out.println("目的地址格式不正确");
				return;
			}
			serverHost = args[0].split(":")[0];
			port  = Integer.parseInt(args[0].split(":")[1]);
		}
		try {
			//初始化失败退出程序
			if(!init()){
			}
			ByteBuffer buff = ByteBuffer.allocate(1500); 	//字节缓冲器
			while(socket.read(buff)!=-1){			//读取信息
				String msg = new String(buff.array(),0,buff.position()); //	转换成字符串
				buff.clear();
				System.out.println(msg);
			}
		} catch (IOException e) {
			System.out.println("与服务器断开连接");
		}finally {
			close();
			System.out.println("程序已退出");
		}
	}
	
	public static boolean init(){
		System.out.println("正在连接服务器");
		try {
			//打开通道
			socket  = SocketChannel.open(new InetSocketAddress(serverHost, port));
		} catch (IOException e) {
			System.out.println("无法连接到服务器");
			return false;
		}
		System.out.println("已经连接至服务器");
		try {
			InetAddress address = InetAddress.getLocalHost(); //获取本地网络信息
			ip = address.getHostAddress();
			name  = address.getHostName();
			
			socket.write(ByteBuffer.wrap((name+ "-" + ip + "上线了").getBytes()));//发送上线信息
	
		} catch (IOException e) {
			System.out.println("网络异常");
			return false;
		}
		Thread thread = new Thread(new Client());
		thread.setDaemon(true);
		thread.start();
		return true;
	}
	@Override
	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			//循环等待输入
			while(sc.hasNext()){
				String msg = sc.nextLine();
				if("exit".equals(msg)){
					socket.write(ByteBuffer.wrap((name+ "-" + ip + "下线了").getBytes()));	//发送下线信息
					break;
				}
				msg = name + "-" + ip + ":" + msg;
				socket.write(ByteBuffer.wrap(msg.getBytes()));
			}
		} catch (IOException e) {
			System.out.println("与服务器断开连接");
		}finally {
			close();
		}
		
	}
	public static void close(){
		try {
			if(socket!=null)
				socket.close();
		} catch (IOException e) {
		}
	}
}
