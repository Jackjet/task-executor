package com.wisrc.batch.service;

import com.wisrc.batch.entity.ArgumentDefineEntity;
import com.wisrc.batch.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/17.
 */
public interface ArgumentDefineService {
    /**
     * 查询指定域中的所有参数信息
     *
     * @param domainID 域编码信息
     * @return List 返回某一个域所有的参数定义信息
     */
    List<ArgumentDefineEntity> findAll(String domainID);

}
