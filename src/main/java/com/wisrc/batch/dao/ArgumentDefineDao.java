package com.wisrc.batch.dao;


import com.wisrc.batch.entity.ArgumentDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface ArgumentDefineDao {

    /**
     * 查询某一个域中所有的参数
     *
     * @param domainId 所属域编码
     *                 返回参数定义表中,所以参数,如果参数类型是固定参数,则返回固定参数的值
     */
    List findAll(String domainId);


}
