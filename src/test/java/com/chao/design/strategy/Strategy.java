package com.chao.design.strategy;

/**
　 * <p>Title: Strategy</p> 
　 * <p>Description: 定义策略接口</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public interface Strategy {

	/**
	 *<p>Title: doOperation</p> 
	 *<p>Description: 对两个数的进行操作方法</p> 
	 * @param num1
	 * @param num2
	 * @return
	 */
	int doOperation(int num1, int num2);
}
