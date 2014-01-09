package com.github.herong.tools.jperformance.core.model;


public class TestResult {
	private String threadName;
	private String state;
	private String startTime;
	private String endTime;
	private String costTime;

	public TestResult(String threadName, String state, String startTime,
			String endTime, String costTime) {
		this.threadName = threadName;
		this.state = state;
		this.startTime = startTime;
		this.endTime = endTime;
		this.costTime = costTime;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

}
