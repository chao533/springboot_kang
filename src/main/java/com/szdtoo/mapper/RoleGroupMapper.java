package com.szdtoo.mapper;

import com.szdtoo.model.RoleGroup;
import com.szdtoo.model.RoleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleGroupMapper {
    long countByExample(RoleGroupExample example);

    int deleteByExample(RoleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    List<RoleGroup> selectByExample(RoleGroupExample example);

    RoleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByExample(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);
}