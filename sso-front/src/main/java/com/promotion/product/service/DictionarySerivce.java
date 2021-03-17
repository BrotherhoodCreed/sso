package com.promotion.product.service;

import com.promotion.product.dao.dataobject.DictionaryDo;
import com.promotion.product.dao.mysql.DictionaryDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionarySerivce {

    @Autowired
    private DictionaryDao dictionaryDao;

    public List<DictionaryDo> queryDictionary(String descriptionType){
       return   dictionaryDao.select(descriptionType);
    }

}
