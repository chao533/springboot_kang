package com.kang.config.zk;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
　 * <p>Title: ZkLockUtil</p> 
　 * <p>Description: zookeeper 分布式锁</p> 
　 * @author CK 
　 * @date 2020年4月16日
 */
@Component
public class ZkLock{
	
	@Autowired
	private InterProcessMutex interProcessMutex;
	
	/**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private ZkLock(){};
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     * 针对一件商品实现，多件商品同时秒杀建议实现一个map
     */
    //获得了锁
    public boolean acquire(long time, TimeUnit unit){
    	try {
			return interProcessMutex.acquire(time,unit);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    //释放锁
    public void release(){
    	try {
    		interProcessMutex.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}  
