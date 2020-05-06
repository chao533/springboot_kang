package com.chao.design.observer;

/**
 * <p>Title: Subject</p>
 * <p>Description: 通知者的抽象类(目标或主题)被观察的对象<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public interface Subject {

	/**
	 * <p>Title: add</p>
	 * <p>Description: 绑定观察者方法</p>
	 * @param @param observer
	 */
	public void add(Observer observer);
	
	/**
	 * <p>Title: remove</p>
	 * <p>Description: 移除观察者方法</p>
	 * @param @param observer
	 */
	public void remove(Observer observer);
	
	
	/**
	 * <p>Title: notifyObservers</p>
	 * <p>Description: 通知所有绑定的观察者</p>
	 * @param
	 */
	public void notifyObservers();
	
	/**
	 * <p>Title: setState</p>
	 * <p>Description: 操作</p>
	 * @param
	 */
	public void setState();
	
}
