package algorithm.some_implementation.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
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
	private static ByteBuffer buff = ByteBuffer.allocate(1500); //缓冲器
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
				select();				//获取就绪的channel  有多少就绪的通道是不一定的
				//获取就绪的key列表
				Set<SelectionKey> keys = clientManager.selectedKeys();		//如果select方法返回不为0
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
					else if(key.isReadable())	//有数据
						broadcast(key);
					it.remove();			//操作结束要把处于队列中的移除
				}
			}
		} catch (Exception e) {
		}finally {
			try {
				if(clientManager != null){
					clientManager.close();
				}
			} catch (Exception e2) {
			}
			try {
				if(server != null)
					server.close();
			} catch (Exception e2) {
			}
			try {
				closeAll();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("服务器已停止");
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
			return false;
		}
		Thread service = new Thread();
		service.setDaemon(true);
		service.start();
		System.out.println("服务器启动成功");
		return true;
	}

	/**
	 * 服务端等待连接
	 * 此方法获取请求的socket通道并添加到客户端列表中,当然还要注册到管理器中  
	 * @param key
	 * @throws IOException
	 */
	private static void accept(SelectionKey key) throws IOException{
		SocketChannel socket = null;
		//接收请求的连接
		socket = ((ServerSocketChannel) key.channel()).accept();
		if(socket == null)
			return;
		SocketAddress address = null;
		try{
			address = socket.getRemoteAddress();
			//注册
			socket.configureBlocking(false);
			socket.register(clientManager,SelectionKey.OP_READ);
		}catch (ClosedChannelException e) {
			try {
				if(socket!=null)
					socket.close();
			} catch (IOException e2) {
			}
			return;
		}catch(IOException e){
			try {
				if(socket!=null)
					socket.close();
			} catch (IOException e2) {
			}
			return;
		}
		//添加到客户端列表
		clientList.add(socket);
		System.out.println("主机"+ address + "连接到服务器");
	}
	/**
	 * 此方法接收数据并转发给客户端的每个人
	 * @param key
	 * @throws IOException 
	 */
	public static void broadcast(SelectionKey key) throws IOException{
		SocketChannel sender = (SocketChannel) key.channel();
		//方法结束不清理
		buff.clear();
		int status = -1;
		try {
			//接收数据
			status = sender.read(buff);
		} catch (IOException e) {
			status = -1;
		}
		if(status<0){
			//关闭连接
			remove(sender);  //从列表中移除
		}

		//发送给每一个人
		for(SocketChannel client : clientList){
			//除了自己本身
			if(client == sender)
				continue;
			buff.flip();
			try {
				client.write(buff);
			} catch (IOException e) {
				remove(sender);
			}
		}
	}
	private static void select(){
		try {
			//等待事件
			clientManager.select();
		} catch (IOException e) {
		}
	}
	/**
	 * 移除一个客户端
	 * @param client
	 * @throws IOException
	 */
	public static void remove(SocketChannel client) throws IOException{
		SocketAddress address = client.getRemoteAddress();
		client.close();
		client.keyFor(clientManager).cancel(); //反注册 
		System.out.println("与主机" + "断开连接");
	}
	
	public static void closeAll() throws IOException{
		for(SocketChannel client : clientList){
			if(client!=null)
				client.close();
		}
	}
}
