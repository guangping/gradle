package io.lance.gradle.common.dao.modules.sys.mapper;

import com.github.pagehelper.PageRowBounds;
import io.lance.gradle.common.dao.modules.sys.entity.SysConfig;

import java.util.List;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    List<SysConfig> queryListPage(SysConfig config, PageRowBounds rowBounds);
}