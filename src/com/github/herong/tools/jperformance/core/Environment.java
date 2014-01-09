/**
 * @文件名 : Environment.java
 * @包名 : cn.sinobest.framework.comm
 * @描述 : TODO(用一句话描述该文件做什么)
 * @作者 : herong 填写您的邮箱地址
 * @版权 : cn.sinobest 版权所有
 * @创建时间: 2010-11-1 下午11:04:28
 * @版本 : V1.0
 */
package com.github.herong.tools.jperformance.core;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.herong.tools.jperformance.core.model.InitParams;
import com.github.herong.tools.jperformance.core.model.TestResult;

/**
 * @类型名称: Environment
 * @功能说明: TODO(这里用一句话描述这个类的作用)
 * @作者 : herong
 * @创建时间: 2010-11-1 下午11:04:28
 * @修改人员: herong
 * @修改说明: TODO(描述本次修改内容)
 * @修改时间: 2010-11-1 下午11:04:28
 * @版本 : V1.0
 * @参考 :
 */
public class Environment implements Serializable {
	
	public final static String STATE_SUCCESS = "成功";
	public final static String STATE_FALIURE = "失败";
	
	public final static InitParams initParams = new InitParams();
	
	public final static Map<String,TestResult> testResult = new ConcurrentHashMap<String, TestResult>();
	
	public static TestResult getResultByTheadName(String name) {
		TestResult result = testResult.get(name);
		if (result == null) {
			result = new TestResult(name, STATE_SUCCESS,"", "", "");
			testResult.put(name, result);
		}
		return result;
	}
}
