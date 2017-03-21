package org.eop.zkclient.sample;

import org.I0Itec.zkclient.ZkClient;

public class ChildListenerClient3 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000);
		Thread.sleep(3000);
		zk.createPersistent("/parent", "你好");
		System.out.println("------------------------父节点创建-------------------------");
		zk.createPersistent("/parent/child", "你好");
		System.out.println("------------------------节点创建-------------------------");
		zk.writeData("/parent/child", "我好");
		System.out.println("------------------------节点更新-------------------------");
		zk.delete("/parent/child");
		System.out.println("------------------------节点删除-------------------------");
		zk.writeData("/parent", "我好");
		System.out.println("------------------------父节点更新-------------------------");
		zk.delete("/parent");
		System.out.println("------------------------父节点删除-------------------------");
	}

}
