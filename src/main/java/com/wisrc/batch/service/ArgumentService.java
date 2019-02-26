package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.GroupTaskEntity;
import com.wisrc.batch.entity.TaskArgumentEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface ArgumentService {
    /**
     * 初始化参数信息
     * 在使用@Autowired自动注入这个类得实例后
     * 需要手工调用这个方法初始化对象中的变量属性
     * ArgumentServiceImpl中定义的属性，需要调用这个方法初始化
     *
     * @param conf
     */
    void afterPropertySet(BatchRunConfDto conf, List<GroupTaskEntity> jobKeyList);

    /**
     * 参数 id 是 dispatch_group_task_relation表中的id字段
     * 根据id值,查询这个任务所有的参数信息
     * 只有执行了上边的afterPropertySet函数, 初始化对象的属性后,
     * queryArgument方法才会返回正确的值,否则为null
     *
     * @param id 任务组中任务的key
     */
    List<TaskArgumentEntity> queryArgument(String id);

}
