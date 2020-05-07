package com.chao.design.observer;

import cn.hutool.core.lang.Console;

/**
 * <p>Title: ObserverImpl_1</p>
 * <p>Description: 观察者实现类一<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public class ConcreteObserver_2 implements Observer{

	@Override
	public void update() {
		Console.log("observer_2 update");
	}

}
