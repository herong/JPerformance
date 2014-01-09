/**
 * 
 */
package com.github.herong.tools.jperformance.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.herong.tools.jperformance.core.log.ILogTime;
import com.github.herong.tools.jperformance.core.log.LogTime;
import com.github.herong.tools.jperformance.core.model.TestResult;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-12-3 上午11:51:37
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class VUserCallable implements Callable<Long> {

	private CountDownLatch contDonwLatch;
	private CyclicBarrier cyclicBarrier;
	private AtomicInteger success;
	private AtomicInteger falurie;

	public VUserCallable(AtomicInteger success, AtomicInteger falurie,
			CountDownLatch contDownLatch, CyclicBarrier cyclicBarrier) {
		this.contDonwLatch = contDownLatch;
		this.cyclicBarrier = cyclicBarrier;
		this.success = success;
		this.falurie = falurie;
		// System.out.println("【线程" + Thread.currentThread().getName() +
		// "】被创建");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public Long call() throws Exception {
		TestResult result = Environment.getResultByTheadName(Thread.currentThread().getName());
		try {
			System.out.println("【线程" + Thread.currentThread().getName()
					+ "】准备就绪...");
			cyclicBarrier.await();
			System.out
					.println("【线程" + Thread.currentThread().getName() + "】运行");
			
			this.success.incrementAndGet();

			Long time = LogTime.exec(new ILogTime<Long>() {
				public Long run() throws Exception {
					String resp = Processor.request();
					System.out.println("【线程" + Thread.currentThread().getName()
							+ "】返回信息：\n" + resp);
					return 0L;
				}
			});
			
			result.setState(Environment.STATE_SUCCESS);
			System.out
					.println("【线程" + Thread.currentThread().getName() + "】完成");
			return time;

		} catch (Exception e) {
			System.out
					.println("【线程" + Thread.currentThread().getName() + "】出错");
			this.falurie.incrementAndGet();
			result.setState(Environment.STATE_FALIURE);
			e.printStackTrace();
		} finally {
			this.contDonwLatch.countDown();
		}
		return 0L;
	}

}
