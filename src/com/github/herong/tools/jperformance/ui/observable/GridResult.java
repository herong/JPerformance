package com.github.herong.tools.jperformance.ui.observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GridResult {
	private StringProperty threadName;
	private StringProperty state;
	private StringProperty startTime;
	private StringProperty endTime;
	private StringProperty costTime;

	public GridResult(String threadName, String state, String startTime,
			String endTime, String costTime) {
		this.threadName = new SimpleStringProperty(threadName);
		this.state = new SimpleStringProperty(state);
		this.startTime = new SimpleStringProperty(startTime);
		this.endTime = new SimpleStringProperty(endTime);
		this.costTime = new SimpleStringProperty(costTime);
	}

	public String getThreadName() {
		return threadName.get();
	}

	public void setThreadName(String threadName) {
		this.threadName.set(threadName);
	}

	public String getState() {
		return state.get();
	}

	public void setState(String state) {
		this.state.set(state);
	}

	public String getStartTime() {
		return startTime.get();
	}

	public void setStartTime(String startTime) {
		this.startTime.set(startTime);
	}

	public String getEndTime() {
		return endTime.get();
	}

	public void setEndTime(String endTime) {
		this.endTime.set(endTime);
	}

	public String getCostTime() {
		return costTime.get();
	}

	public void setCostTime(String costTime) {
		this.costTime.set(costTime);
	}

}
