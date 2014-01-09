/**
 * 
 */
package com.github.herong.tools.jperformance.core.log;

import java.util.Date;

import com.github.herong.tools.jperformance.core.Environment;
import com.github.herong.tools.jperformance.core.model.TestResult;
import com.github.herong.util.DateUtil;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2012-2-23 下午02:59:14
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class LogTime {

	public static <T> T exec(ILogTime<T> logTime) throws Exception {
		Long s = System.currentTimeMillis();
		TestResult result = Environment.getResultByTheadName(Thread.currentThread().getName());
		result.setStartTime(DateUtil.date2Str(new Date(s),DateUtil.CurDate.YYYYMMDDHHmmssSSS_DO.getPattern()));

		logTime.run();
		
		Long e = System.currentTimeMillis();
		Long costTime = e - s;
		
		result.setEndTime(DateUtil.date2Str(new Date(e),DateUtil.CurDate.YYYYMMDDHHmmssSSS_DO.getPattern()));
		result.setCostTime(String.valueOf(costTime));
		
		System.out.println("【" + Thread.currentThread().getName() + "】耗时(ms):"
				+ costTime + "\n");
		return (T) costTime;
	}
}
