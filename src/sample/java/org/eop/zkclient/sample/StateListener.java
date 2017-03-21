package org.eop.zkclient.sample;

import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class StateListener {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程：" + Thread.currentThread().getId());
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3000, 3000);
		zk.subscribeStateChanges(new IZkStateListener() {
			
			//模拟测试，把服务器关闭，收到连接断开事件，把服务器重新启动，客户端用原来的Session ID
			//重连服务器，收到连接成功。因为服务器已经没有原来的会话信息，所以不存在会话超时的概念
			//不会收到会话新建的事件
			@Override
			public void handleStateChanged(KeeperState state) throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("状态改变，状态：" + state);
			}
			
			@Override
			public void handleSessionEstablishmentError(Throwable e) throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("会话建立失败：" + e);
			}
			
			//模式测试，服务器和客户端分别在两台机器，拔掉网线，过段时间重新插上
			//此时服务器上原来Session超时，客户端使用原来Session ID重连，连接
			//成功后收到会话超时状态，然后客户端重新连接，并建立全新的会话
			@Override
			public void handleNewSession() throws Exception {
				System.out.println("事件线程：" + Thread.currentThread().getId());
				System.out.println("会话建立成功");
			}
		});
		
		synchronized (StateListener.class) {
			StateListener.class.wait();
		}
	}

}
