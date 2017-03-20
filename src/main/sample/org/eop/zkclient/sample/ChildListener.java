package org.eop.zkclient.sample;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class ChildListener {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000);
		zk.subscribeChildChanges("/parent", new IZkChildListener() {
			
			//本节点的创建和删除，子节点的创建和删除都会收到该事件
			//本节点创建时子节点为空，本节点删除时子节点为null
			//本节点和子节点的更新不会收到该事件
			@Override
			public void handleChildChange(String path, List<String> children) throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("孩子改变：父路径=" + path + "，孩子=" + children);
			}
		});
		
		synchronized (ChildListener.class) {
			ChildListener.class.wait();
		}
	}

}
