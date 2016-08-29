package com.likou.common.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class NetUtils {

	/**
	 * 检查端口是否被占用
	 * @param host 检测服务器的ip地址
	 * @param port 待检查的端口号
	 * @return
	 * @throws IOException
	 */
	public static boolean isPortUsing(String host,int port) throws IOException{  
        boolean flag = false;  
        InetAddress theAddress = InetAddress.getByName(host);
        Socket socket = null;
        try {  
            socket = new Socket(theAddress,port);  
            flag = true;
            socket.close();
        } catch (IOException e) {  
        }
        return flag;  
    }
	/**
	 * 从host主机中随即获取从1025到65534之间的一个端口
	 * @param host 需要获取的端口的主机
	 * @return
	 * @throws IOException
	 */
	public static int getRandomPortUnUsing(String host) throws IOException{
		Random random = new Random();
		int port = 1025;
		
		do{
			port = random.nextInt(65535 - 1024) + 1024;
		}while(isPortUsing(host, port));
		
		return port;
	}
	public static String getIP() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String hostAddr = addr.getHostAddress();
			return hostAddr;
		}
		catch (IOException e) {
			return null;
		}
	}
}
