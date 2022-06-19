package org.jeecg.modules.flowable.busi_demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.flowable.busi_demo.entity.TestDemo;


/**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2021-11-30
 * @Version: V1.0
 */
public interface ITestDemoService extends IService<TestDemo> {

    IPage<TestDemo> myPage(Page<TestDemo> page, QueryWrapper<TestDemo> queryWrapper);

    void relationAct(String dataId);
    void relationEmployeeProcess(String dataId);
    void relationAbsenceProcess(String dataId);
    void relationJoaLeave(String dataId);
    void bindCurrentFlow(String dataId);
   public IPage<TestDemo> getCurrentUserTask(Page<TestDemo> page,QueryWrapper<TestDemo> queryWrapper);
   public IPage<TestDemo> getCurrentUserApply(Page<TestDemo> page,QueryWrapper<TestDemo> queryWrapper);
}
