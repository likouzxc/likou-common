package com.likou.common.character;

import com.likou.common.net.NetUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by jiangli on 16/8/3.
 * 建议用于日志记录
 */
class Creater32ID {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static Random random = new Random();

    /**
     * 获取id :yyyyMMddHHmmssSSS + 000 + 4位随机数 +ip(3位) + 线程id(5位) = 32位
     * @return
     */
    public synchronized static String getID(){

        StringBuffer id = new StringBuffer();
        id.append(dateFormat.format(new Date()));
        id.append("00");
        id.append(String.format("%05d", random.nextInt(100000)));
        id.append(getIP());
        id.append(String.format("%05d",Thread.currentThread().hashCode()%100000));
        id.setLength(32);
        return id.toString();
    }

    private static String getIP() {
        String hostAddr = NetUtils.getIP().substring(NetUtils.getIP().lastIndexOf('.')+1);
        if(hostAddr == null){
            hostAddr = String.valueOf(random.nextInt(1000));
        }
        return String.format("%03d", Integer.parseInt(hostAddr));
    }
}
