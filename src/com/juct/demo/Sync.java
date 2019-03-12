package com.juct.demo;

public class Sync {

	private static Object obj = new Object();
	private static int count = 0;

	public static void main(String[] args) {
		Thread odd = new Thread(() -> {
			synchronized (obj) {//不要用Integer等封装类型 做锁,因为运算可能会改变指向从而导致锁失效
				while (count < 100) {
					if (count % 2 == 0) {
						count++;
						System.out.println(Thread.currentThread() + "" + count);
						obj.notifyAll();
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		Thread even = new Thread(() -> {
			synchronized (obj) {
				while (count < 100) {
					if (count % 2 != 0) {
						count++;
						System.out.println(Thread.currentThread() + "" + count);
						obj.notifyAll();
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		odd.start();
		even.start();

	}
}
