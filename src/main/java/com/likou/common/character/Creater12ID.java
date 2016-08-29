package com.likou.common.character;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

/**
 * Created by jiangli on 16/8/3.
 * 同一个线程中建议使用一次,或者用于不同表中的ID
 */
class Creater12ID {
    private static Random random = new Random();
    /**
     * 获取id :去掉年的时间戳(秒)XXXX(时间戳前4位) + 线程id(4位) + XXXX(时间戳后4位) = 12位
     * @return
     */
    public synchronized static String getID() {
        try {
            StringBuffer id = new StringBuffer();
            id.append(Thread.currentThread().hashCode());
            id.append(random.nextInt(100));
            id.setLength(12);
            return id.toString();
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getID());
    }
}
