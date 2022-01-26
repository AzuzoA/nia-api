package com.rami.nia.base.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BaseMapper {

    List<Map> getCodeList(String testCode) throws Exception;

}
