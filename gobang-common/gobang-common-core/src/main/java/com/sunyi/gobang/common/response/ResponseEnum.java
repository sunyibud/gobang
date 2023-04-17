package com.sunyi.gobang.common.response;

/**
 * 返回类型枚举
 * @author sunyi
 * @date 2023/4/7
 */
public enum ResponseEnum
{

	/**
	 * ok
	 */
	OK(0, "ok"),

	/**
	 * 用于直接显示提示用户的错误，内容由输入内容决定
	 */
	SHOW_FAIL(1, ""),


	/**
	 * 无法读取获取请求参数
	 */
	HTTP_MESSAGE_NOT_READABLE(2, "请求参数格式有误"),

	/**
	 * 未授权
	 */
	UNAUTHORIZED(4, "Unauthorized"),

	/**
	 * 服务器出了点小差
	 */
	EXCEPTION(5, "服务器出了点小差"),

	/**
	 * 一些需要登录的接口，而实际上因为前端无法知道token是否已过期，导致token已失效时，
	 * 应该返回一个状态码，告诉前端token已经失效了，及时清理
	 */
	CLEAN_TOKEN(6, "clean token");

    private final int code;

	private final String msg;

	public int value() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	ResponseEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
	}

}
