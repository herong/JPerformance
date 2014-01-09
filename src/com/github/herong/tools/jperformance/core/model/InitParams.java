package com.github.herong.tools.jperformance.core.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数
 * 
 * @author herong
 * @mail: herong_wskaps@163.com
 */
public class InitParams implements Serializable {

	private static final long serialVersionUID = -655700828647624419L;

	private String path;

	private int port;

	private String protocol;

	private int connect;

	private String encoding;

	private String url;

	private int response;

	private int threadCnt;

	private int success;

	private int faliure;

	private Long tatoalTime;

	private Long avgTime;
	
	private Map<String,String> reqParams;
	
	private String body;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getConnect() {
		return connect;
	}

	public void setConnect(int connect) {
		this.connect = connect;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

	public int getThreadCnt() {
		return threadCnt;
	}

	public void setThreadCnt(int threadCnt) {
		this.threadCnt = threadCnt;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFaliure() {
		return faliure;
	}

	public void setFaliure(int faliure) {
		this.faliure = faliure;
	}

	public Long getTatoalTime() {
		return tatoalTime;
	}

	public void setTatoalTime(Long tatoalTime) {
		this.tatoalTime = tatoalTime;
	}

	public Long getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(Long avgTime) {
		this.avgTime = avgTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, String> getReqParams() {
		if (this.reqParams == null) {
			return Collections.emptyMap();
		}
		return reqParams;
	}

	public void setReqParams(Map<String, String> reqParams) {
		if (this.reqParams == null) {
			this.reqParams = new HashMap<String,String>(1);
		}
		this.reqParams.putAll(reqParams);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void addReqParam(String key,String value) {
		if (this.reqParams == null) {
			this.reqParams = new HashMap<String,String>(1);
		}
		
		this.reqParams.put(key, value);
	}

}
