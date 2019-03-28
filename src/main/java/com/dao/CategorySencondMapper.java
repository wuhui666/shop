package com.dao;

import com.pojo.CategorySencond;
import com.pojo.CategorySencondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategorySencondMapper {
    long countByExample(CategorySencondExample example);

    int deleteByExample(CategorySencondExample example);

    int deleteByPrimaryKey(Integer csid);

    int insert(CategorySencond record);

    int insertSelective(CategorySencond record);

    List<CategorySencond> selectByExample(CategorySencondExample example);

    CategorySencond selectByPrimaryKey(Integer csid);

    int updateByExampleSelective(@Param("record") CategorySencond record, @Param("example") CategorySencondExample example);

    int updateByExample(@Param("record") CategorySencond record, @Param("example") CategorySencondExample example);

    int updateByPrimaryKeySelective(CategorySencond record);

    int updateByPrimaryKey(CategorySencond record);
}