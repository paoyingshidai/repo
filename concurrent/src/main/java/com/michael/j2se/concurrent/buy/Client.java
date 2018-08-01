<<<<<<< HEAD
package com.michael.j2se.concurrent.buy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.michael.j2se.test.support.AbstractCountDownLatch;

public class Client {

	private List<User> users;
	UserCurrent currentUtil;

	public Client() {
		this.users = new ArrayList<>();
		Shop shop = new Shop(new AtomicInteger(9));						
		this.currentUtil = new UserCurrent(shop, 9);
	}
	
	public static void main(String[] args) {
		
		Client client = new Client();
		client.currentUtil.start();
		client.currentUtil.isLog(false);
		client.users.forEach((u) -> {System.out.println(u == null ? null : u.goods );});
	}
	
	
	class UserCurrent extends AbstractCountDownLatch {
		private Shop shop;
		public UserCurrent(Shop shop, int threadNumber) {
			super(threadNumber);
			this.shop = shop;
		}
		@Override
		protected void doWork() {
			 User u = new User(shop);
			 synchronized (u) {
				 u.buy();
			}
			 users.add(u);
		}
	}
}
=======
package com.michael.j2se.concurrent.buy;

import java.util.ArrayList;
import java.util.List;

import com.michael.j2se.test.support.AbstractCountDownLatch;

public class Client {

	private List<User> users;
	UserCurrent currentUtil;

	public Client() {
		this.users = new ArrayList<>();
		Shop shop = new Shop(19);
		this.currentUtil = new UserCurrent(shop, 190);
	}

	public static void main(String[] args) {

		Client client = new Client();
		client.currentUtil.isLog(false);
		client.currentUtil.start();
		client.users.forEach((u) -> {System.out.println(u == null ? null : u.goods );});
	}


	class UserCurrent extends AbstractCountDownLatch {
		private Shop shop;
		public UserCurrent(Shop shop, int threadNumber) {
			super(threadNumber);
			this.shop = shop;
		}
		@Override
		protected void doWork() {
			 User u = new User(shop);
			 u.buy();
			 users.add(u);
		}
	}
}
>>>>>>> branch 'master' of https://github.com/paoyingshidai/javase.git
