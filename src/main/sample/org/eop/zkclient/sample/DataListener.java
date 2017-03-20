package org.eop.zkclient.sample;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class DataListener {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000);
		zk.subscribeDataChanges("/node", new IZkDataListener() {
			
			@Override
			public void handleDataChange(String path, Object data) throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("节点改变：路径=" + path + "，数据=" + data);
			}
			
			@Override
			public void handleDataDeleted(String path) throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("节点删除：路径=" + path);
			}
		});
		
		synchronized (DataListener.class) {
			DataListener.class.wait();
		}
	}

}
