package com.likou.common.character;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by jiangli on 16/8/3.
 * 同一个线程中建议使用一次,或者用于不同表中的ID
 */
class Creater16ID {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static Random random = new Random();

    /**
     * 获取id :去掉年的时间戳(纳秒)XXXXX(时间戳前5位) + 随机数(2位)+ XXXXXXXXX(时间戳后9位) = 16位
     * @return
     */
    public synchronized static String getID() {
        try {
            long time = System.nanoTime();
            StringBuffer id = new StringBuffer();
            id.append(time/10000);
            id.insert(5,String.format("%02d", random.nextInt(100)));
//            id.setLength(16);
            return id.toString();
        }catch (Exception e){
            return null;
        }
    }
}
