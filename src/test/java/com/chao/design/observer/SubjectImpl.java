package com.chao.design.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject{
	
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
