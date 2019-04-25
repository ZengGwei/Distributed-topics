package com.gwz.zookeeperDemo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * create by Guangwei.Zeng on 2019/2019/4/24
 */
public class zkClient {
    public  void createZookeeperSession(){
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("192.168.7.66:2181," + "192.168.7.67:2181," + "192.168.7.68:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
                        //如果客户端收到响应，表示连接成功
                        System.out.println(watchedEvent.getState());
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            ZooKeeper.States state = zooKeeper.getState();
            System.out.println(state);
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        zkClient zkClient = new zkClient();
        zkClient.createZookeeperSession();


    }



}
