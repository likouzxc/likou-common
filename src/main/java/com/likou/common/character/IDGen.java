package com.likou.common.character;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jiangli on 16/8/3.
 */
public class IDGen {

    /**
     * 获取12位长的ID<br/>
     * 注意:同一个线程中建议使用一次,或者用于不同表中的ID,秒会作为一个维度
     * @return
     */
    public static String get12ID(){
        return Creater12ID.getID();
    }
    /**
     * 获取16位长的ID<br/>
     * 注意:同一个线程中建议使用一次,或者用于不同表中的ID,秒会作为一个维度
     * @return
     */
    public static String get16ID(){
        return Creater16ID.getID();
    }
    /**
     * 获取32位长的ID<br/>
     * 注意:数据比较完整,建议用于日志标记与区分,毫秒会作为一个维度
     * @return
     */
    public static String get32ID(){
        return Creater32ID.getID();
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(5000);
        HashSet<String> set12 = new HashSet<String>();
        HashSet<String> set16 = new HashSet<String>();
        HashSet<String> set32 = new HashSet<String>();
        for (int i=0;i<5000;i++){
            Mythread mythread = new Mythread(set12,set16,set32,latch);
            executorService.submit(mythread);
        }
        try {
            latch.await();
            System.out.println(set12.size());
            System.out.println(set16.size());
            System.out.println(set32.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static class Mythread implements Runnable{
        HashSet<String> set12 ;
        HashSet<String> set16;
        HashSet<String> set32;
        CountDownLatch latch;

        public Mythread(HashSet<String> set12, HashSet<String> set16, HashSet<String> set32, CountDownLatch latch) {
            this.set12 = set12;
            this.set16 = set16;
            this.set32 = set32;
            this.latch = latch;
        }

        @Override
        public void run() {
            set12.add(get12ID());
            set16.add(get16ID());
            set32.add(get32ID());
            this.latch.countDown();
        }
    }
}
