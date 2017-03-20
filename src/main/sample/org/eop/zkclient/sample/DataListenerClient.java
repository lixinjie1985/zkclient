package org.eop.zkclient.sample;

import org.I0Itec.zkclient.ZkClient;

public class DataListenerClient {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000);
		Thread.sleep(10000);
		zk.createPersistent("/node", "你好");
		System.out.println("------------------------节点创建-------------------------");
		Thread.sleep(10000);
		zk.writeData("/node", "我好");
		System.out.println("------------------------节点更新-------------------------");
		Thread.sleep(10000);
		zk.delete("/node");
		System.out.println("------------------------节点删除-------------------------");
		Thread.sleep(10000);
	}

}
