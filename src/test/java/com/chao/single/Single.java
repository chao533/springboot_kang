package com.chao.single;

import java.io.Serializable;

public class Single implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Single s = new Single();

	private Single() {
	}

	public static Single getInstall() {
		return s;
	}

	// 防止序列化和反序列化造成多例
	private Object readResolve() {
		return s;
	}

}
