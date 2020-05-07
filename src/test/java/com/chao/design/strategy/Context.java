package com.chao.design.strategy;

/**
　 * <p>Title: Context</p> 
　 * <p>Description: Context 是一个具体使用了某种策略的类</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class Context {

	
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 *<p>Title: executeStrategy</p> 
	 *<p>Description: 具体执行其中某一种策略的方法</p> 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
