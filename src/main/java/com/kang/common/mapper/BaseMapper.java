package com.kang.common.mapper;


import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
　 * <p>Title: BaseMapper</p> 
　 * <p>Description: 通用BaseMapper接口</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
