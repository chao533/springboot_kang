package com.chao.hutool;

import java.io.Serializable;

public class Singleton implements Serializable, Cloneable {
	private static final long serialVersionUID = 6125990676610180062L;
	private static Singleton singleton;
	private static boolean isFristCreate = true;// 默认是第一次创建

	private Singleton() {
		if (isFristCreate) {
			synchronized (Singleton.class) {
				if (isFristCreate) {
					isFristCreate = false;
				}
			}
		} else {
			throw new RuntimeException("已然被实例化一次，不能在实例化");
		}
	}

	public void doAction() {
		// TODO 实现你需要做的事
	}

	public static Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}

	@Override
	protected Singleton clone() throws CloneNotSupportedException {
		return singleton;
	}

	private Object readResolve() {
		return singleton;
	}
}
