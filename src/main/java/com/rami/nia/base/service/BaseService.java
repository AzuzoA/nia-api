package com.rami.nia.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.rami.nia.base.repository.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseService {
    @Autowired
    BaseMapper baseMapper;
    public Map getCodeList(String testCode) throws Exception {
        
        Map data = new HashMap();
        List list = baseMapper.getCodeList(testCode);
        
        data.put("testCode", testCode);
        data.put("totalCount", list.size());
        data.put("list", list);
        
        return data;
    }

    public int calTotalPage(int totalSize, int pageSize){

        int totalPage = totalSize / pageSize;
        if(totalSize % pageSize > 0) totalPage++;
        return totalPage;
    }
}
