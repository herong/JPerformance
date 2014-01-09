/**
 * 
 */
package com.github.herong.tools.jperformance.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.herong.tools.jperformance.core.model.InitParams;
import com.github.herong.tools.jperformance.core.model.TestResult;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-6-7 下午04:02:50
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class ConcurentCallableTest {

	private AtomicInteger success = new AtomicInteger(0);
	private AtomicInteger falurie = new AtomicInteger(0);

	private CountDownLatch countDownLatch;
	private CyclicBarrier cyclicBarrier;
	private int threadSize;
	private InitParams initParams;
	private List<TestResult> resultList;
	private LinkedHashMap<String,FutureTask<Long>> taskMap;

	/**
	 * @throws Exception
	 * 
	 */
	public ConcurentCallableTest(InitParams initParams) throws Exception {
		if (initParams.getThreadCnt() <= 0) {
			throw new Exception("请指定线程数>0");
		}
		this.initParams = initParams;
		this.threadSize = initParams.getThreadCnt();
		countDownLatch = new CountDownLatch(threadSize);
		cyclicBarrier = new CyclicBarrier(threadSize);

	}

	public void run() throws InterruptedException, ExecutionException {
		long beginTime = System.currentTimeMillis();
		taskMap= new LinkedHashMap<String,FutureTask<Long>>(this.threadSize);
		String threadName = "";
		for (int i = 1; i <= threadSize; i++) {
			FutureTask<Long> ft = new FutureTask<Long>(new VUserCallable(
					success, falurie, countDownLatch, cyclicBarrier));
			
			threadName = "线程"+String.valueOf(i);
			taskMap.put(threadName, ft);
			new Thread(ft,threadName ).start();
		}

		countDownLatch.await();

		long totalTime = 0;
		int i = 1;
		
		
		for (Map.Entry<String,FutureTask<Long>> f : taskMap.entrySet()) {
			long t = f.getValue().get();
			totalTime += t;
			System.out.println("第" + (i++) + "个线程,耗时：" + t);
			
			TestResult result  = Environment.getResultByTheadName(f.getKey());
			result.setCostTime(String.valueOf(t));
		}
		
		long runTime = System.currentTimeMillis() - beginTime;
		long avgTime = totalTime / threadSize;
		initParams.setAvgTime(avgTime);
		initParams.setTatoalTime(totalTime);
		initParams.setSuccess(success.intValue());
		initParams.setFaliure(falurie.intValue());
		System.out.println("程序运行总共耗时(ms):" + runTime);
		System.out.println("计算总共耗时(ms):" + totalTime + "\n平均耗时(ms):" + avgTime);
		System.out.println("总任务数：" + threadSize + ",成功：" + success + ",失败:"
				+ falurie);
	}
	
	
    public void stop() {
    	for (Map.Entry<String,FutureTask<Long>> f : taskMap.entrySet()) {
			f.getValue().cancel(true);
		}
    }
	public static void main(String[] args) throws InterruptedException,
			Exception {

		// new ConcurentCallableTest(100).run();
	}
}
