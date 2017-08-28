package algorithm.some_implementation.nio;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Server {
	private static List<SocketChannel> clientList = new LinkedList<>();	//客户端列表
	private static Selector clientManager = null; //通道管理器
	private static ServerSocketChannel server = null; //服务器通道
	private static ByteBuffer buff = ByteBuffer.allocate(1000); //缓冲器
	private static int port = 5050;
	
	public static void main(String[] args) {
		if(args.length>0){
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("端口号只能为数字");
			}
		}
		try {
			//初始化失败退出
			if(!init())
				return ;
			
			while(clientManager.isOpen()){
				clientManager.select();				//accept
				//获取就绪的key列表
				Set<SelectionKey> keys = clientManager.selectedKeys();
				//遍历事件并处理
				Iterator<SelectionKey> it = keys.iterator();
				while(it.hasNext()){
					SelectionKey key = it.next();
					//判断key是否有效
					if(!key.isValid()){
						it.remove();
						continue;
					}
					
					if(key.isAcceptable()) //有请求
						accept(key);
				}
			}
		} catch (Exception e) {
		}
	}
	/**
	 * 初始化服务端
	 * @return
	 */
	private static boolean init(){
		System.out.println("服务器启动中");

		try {
			//获取管理器
			clientManager = Selector.open();
			//打开通道
			server = ServerSocketChannel.open();
			//绑定端口
			server.socket().bind(new InetSocketAddress(port));
			//设置成非阻塞模式
			server.configureBlocking(false);
			//把通道注册到管理器中，只监听接收连接事件
			server.register(clientManager, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("服务器启动成功");
		return true;
	}

	private static void accept(SelectionKey key) throws IOException{
		SocketChannel socket = null;

			//接收请求的连接
		socket = ((ServerSocketChannel) key.channel()).accept();
		if(socket == null)
			return;
		SocketAddress address = null;
		address = socket.getRemoteAddress();
		
		
	}
}
