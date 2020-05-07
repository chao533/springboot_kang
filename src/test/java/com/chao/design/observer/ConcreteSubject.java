package com.chao.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
　 * <p>Title: ConcreteSubject</p> 
　 * <p>Description: 具体的主题</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class ConcreteSubject implements Subject{
	
	private List<Observer> list = new ArrayList<>();

	@Override
	public void add(Observer observer) {
		list.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		list.remove(observer);
	}

	@Override
	public void notifyObservers() {
		list.forEach(observer -> {
			observer.update();
		});
	}

	@Override
	public void setState() {
		this.notifyObservers();
	}

}
