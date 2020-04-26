package com.kang.mapper.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface SeckillingGoodsMapper {

	
	@Delete("DELETE FROM goods_succ_detail WHERE goods_id = #{goodsId}")
	int deleteGoodsSuccDetail(Long goodsId);
	
	@Update("UPDATE goods_stock SET number = #{number},version = 0 WHERE id = #{id}")
	int updateGoodsStock(@Param("id")Long id,@Param("number")Integer number);
	
	
	
	@Select("SELECT number FROM goods_stock WHERE id = #{id}")
	int getGoodsStock(Long id);
	
	@Select("SELECT id id,number number,version version FROM goods_stock WHERE id = #{id}")
	Map<String,Object> getGoodsStockData(Long id);
	
	@Select("SELECT number FROM goods_stock WHERE id = #{id} FOR UPDATE")
	int getGoodsStock_Pess(Long id);
	
	@Update("UPDATE goods_stock  SET number = number - 1 WHERE id = #{id} AND number > 0")
	int subGoodsStock(Long id);
	
	@Update("UPDATE goods_stock SET number=number-1,version=version+1 WHERE id=#{id} AND version = #{version}")
	int subGoodsStock_Opti(@Param("id")Long id,@Param("version")Integer version);
	
	@Insert("insert into goods_succ_detail (goods_id,user_id,state,create_time) values (#{goodsId},#{userId},#{state},NOW())")
	@SelectKey(statement = "select last_insert_id()", keyProperty = "goods_succ_detail.id", before = false, resultType = int.class)
	int insertGoodsSuccDetail(Map<String,Object> params);
	
	@Select("SELECT count(*) FROM goods_succ_detail WHERE goods_id = #{goodsId}")
	int getGoodsSuccDetailCount(Long goodsId);
}
