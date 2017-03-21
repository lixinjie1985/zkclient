package org.eop.zkclient.sample;

import org.I0Itec.zkclient.ZkClient;

public class ChildListenerClient2 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000);
		Thread.sleep(2000);
		zk.createPersistent("/parent/child", true);
		System.out.println("------------------------递归节点创建-------------------------");
//		Thread.sleep(10000);
		zk.deleteRecursive("/parent");
		System.out.println("------------------------递归节点删除-------------------------");
//		Thread.sleep(10000);
	}

}
