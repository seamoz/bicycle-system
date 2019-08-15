package com.ps.allapp.domain;

import java.io.Serializable;

public class Message<T> implements Serializable {
	private int code;
	private T data;
	private String msg;
	private boolean state;

	public Message() {
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TableVO{" +
				"code=" + code +
				", data=" + data +
				", msg='" + msg + '\'' +
				'}';
	}
}
