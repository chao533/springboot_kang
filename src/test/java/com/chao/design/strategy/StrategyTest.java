package com.chao.design.strategy;

import cn.hutool.core.lang.Console;
/**
　 * <p>Title: StrategyTest</p> 
　 * <p>Description: 策略模式是对象有某个行为，但是在不同的场景中，该行为有不同的实现算法。</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class StrategyTest {

	
	public static void main(String[] args) {
		// 传入执行加法策略的实现类对象
		Context context1 = new Context(new ConcreteStrategy_Add()); 
		Console.log(context1.executeStrategy(3, 4));
		
		// 传入执行减法策略的实现类对象		
		Context context2 = new Context(new ConcreteStrategy_Subs());
		Console.log(context2.executeStrategy(3, 4));
		
		// 传入执行乘法策略的实现类对象
		Context context3 = new Context(new ConcreteStrategy_Multi());
		Console.log(context3.executeStrategy(3, 4));
	}
}
