package com.juct.demo;

public class Sync {

	private static Object obj = new Object();
	private static int count = 0;

	public static void main(String[] args) {
		Thread odd = new Thread(() -> {
			synchronized (obj) {//��Ҫ��Integer�ȷ�װ���� ����,��Ϊ������ܻ�ı�ָ��Ӷ�������ʧЧ
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
