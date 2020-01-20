package com.chao.design.model.factory;

/**
 * 为 Color 和 Shape 对象创建抽象类来获取工厂
 * @author Administrator
 *
 */
public abstract class AbstractFactory {
	
	abstract Shape getShape(String shape);

	abstract Color getColor(String color);
}
