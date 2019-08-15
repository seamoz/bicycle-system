package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {
	/**
	 * 状态码
	 * */
	private int code;

	/**
	 * 返回数据
	 * */
	private T data;

	/**
	 * 消息提示
	 * */
	private String msg;

	/**
	 * 标识
	 * */
	private boolean state;
}
