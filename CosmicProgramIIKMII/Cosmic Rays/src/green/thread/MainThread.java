package green.thread;

import java.util.concurrent.CountDownLatch;

import green.util.InfoUtils;

public class MainThread extends Thread {
	
	public static CountDownLatch latch = new CountDownLatch(1);
	
	@Override
	public void run() {
		InfoUtils.loadEssentials();		
		InfoUtils.loadExtra();
		InfoUtils.loadConfig();
		latch.countDown();
	}
}
