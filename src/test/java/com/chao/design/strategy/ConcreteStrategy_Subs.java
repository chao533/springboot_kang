package com.chao.design.strategy;

/**
　 * <p>Title: ConcreteStrategy_Subs</p> 
　 * <p>Description: Strategy 接口的实现策略类二</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class ConcreteStrategy_Subs implements Strategy{

	/**
	 * 对两个数进行减法运算
	 */
	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}

}
