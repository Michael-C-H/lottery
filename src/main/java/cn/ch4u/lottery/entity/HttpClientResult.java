package cn.ch4u.lottery.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HttpClientResult implements Serializable {

	/**
	 * 响应状态码
	 */
	private int code;

	/**
	 * 响应数据
	 */
	private String content;

	public HttpClientResult(int code, String content) {
		this.code = code;
		this.content = content;
	}

	public HttpClientResult(int code) {
		this.code = code;
	}
}