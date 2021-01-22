package com.gczhu.commonsso.mapper;

import com.gczhu.commonsso.model.AppInfo;
import com.gczhu.commonsso.model.AppInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AppInfoMapper {
    int countByExample(AppInfoExample example);

    int deleteByExample(AppInfoExample example);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("appId") String appId);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    List<AppInfo> selectByExample(AppInfoExample example);

    AppInfo selectByPrimaryKey(@Param("id") Integer id, @Param("appId") String appId);

    int updateByExampleSelective(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByExample(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    AppInfo selectOneByAppId(@Param("appId")String appId);
}