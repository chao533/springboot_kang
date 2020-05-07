package com.chao.design.observer;

/**
 * <p>Title: ObserverTest</p>
 * <p>Description: 观察者模式又叫发布-订阅模式，当一个对象变化时，其它依赖该对象的对象都会收到通知，
 *   并且随着变化。对象之间是一种一对多的关系。<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public class ObserverTest {

	public static void main(String[] args) {
		Subject subject = new ConcreteSubject(); // 创建通知着实例对象
		
		subject.add(new ConcreteObserver_1()); // 添加多个观察者对象
		subject.add(new ConcreteObserver_2());
		subject.setState();
	}
}
