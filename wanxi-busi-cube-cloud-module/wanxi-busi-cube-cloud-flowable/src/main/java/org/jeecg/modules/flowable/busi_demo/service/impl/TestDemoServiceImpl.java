package org.jeecg.modules.flowable.busi_demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import liquibase.pro.packaged.F;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.ProcessDefinition;
import org.jeecg.common.api.vo.Result;

import org.jeecg.modules.flowable.apithird.business.entity.FlowMyBusiness;
import org.jeecg.modules.flowable.apithird.entity.SysUser;
import org.jeecg.modules.flowable.apithird.service.FlowCallBackServiceI;
import org.jeecg.modules.flowable.apithird.service.FlowCommonService;
import org.jeecg.modules.flowable.apithird.service.IFlowThirdService;
import org.jeecg.modules.flowable.busi_demo.entity.TestDemo;
import org.jeecg.modules.flowable.busi_demo.mapper.TestDemoMapper;
import org.jeecg.modules.flowable.busi_demo.service.ITestDemoService;
import org.jeecg.modules.flowable.domain.dto.FlowCurrentUserAndTaskBusi;
import org.jeecg.modules.flowable.service.IFlowDefinitionService;
import org.jeecg.modules.flowable.service.IFlowTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.flowable.task.api.Task;

import javax.annotation.Resource;

/**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2021-11-30
 * @Version: V1.0
 */
@Slf4j
@Service("testDemoService")
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService, FlowCallBackServiceI {
    @Autowired
    FlowCommonService flowCommonService;

    @Autowired
    private ITestDemoService testDemoService;
    @Resource
    private IFlowThirdService iFlowThirdService;
    @Autowired
    private IFlowTaskService flowTaskService;

    @Autowired
    private IFlowDefinitionService flowDefinitionService;
    @Override
    public void afterFlowHandle(FlowMyBusiness business) {
        //流程操作后做些什么
        business.getTaskNameId();//接下来审批的节点
        business.getValues();//前端传进来的参数
        business.getActStatus();//流程状态 ActStatus.java
        //....其他



    }
    @Override
    public void afterFlowHandle(FlowMyBusiness business,Map<String, Object> values){
        if(values!=null){
            values.put("taskTimeout",'0');
            log.info("--->set taskTimeout==0");
        }
    }




    @Override
    public Object getBusinessDataById(String dataId) {
        return this.getById(dataId);
    }

    @Override
    public Map<String, Object> flowValuesOfTask(String taskNameId, Map<String, Object> values) {
        return null;
    }

    @Override
    public List<String> flowCandidateUsernamesOfTask(Task nextTask, Map<String, Object> values) {
        // 关于下一节点的处理候选人指定，1.先从流程定义文件中获取该task是否指定了 activiti:assignee="xxx"
        List<String> flowCandidateUsernamesOfTask=null;
        if(nextTask!=null&&nextTask.getAssignee()!=null&&(!nextTask.getAssignee().equals(""))){
            flowCandidateUsernamesOfTask=new ArrayList<>();
            flowCandidateUsernamesOfTask.add(nextTask.getAssignee());
        }
        // 2.如果在用户指定了该任务的下一节点处理人，那么以指定的候选人代替(覆盖)流程定义xml中该节点的默认指定人
        if(values.get("assigneeList")!=null&&
                ((List<String>) values.get("assigneeList")!=null)&&
                ((List<String>) values.get("assigneeList")).size()>0) {


                flowCandidateUsernamesOfTask=(List<String>) values.get("assigneeList");



        }
        log.info("***nextTaskCandidateUserList-->" + JSON.toJSONString(flowCandidateUsernamesOfTask));
        return flowCandidateUsernamesOfTask;
    }

    @Override
    public IPage<TestDemo> myPage(Page<TestDemo> page, QueryWrapper<TestDemo> queryWrapper) {


        return this.baseMapper.myPage(page, queryWrapper);
    }
    @Override
    public void relationAct(String dataId) {
        flowCommonService.initActBusiness("测试流程：dataId"+dataId,dataId,"testDemoService","test-demo",null);
    }

    @Override
    public void relationEmployeeProcess(String dataId) {

        flowCommonService.initActBusiness("入职流程：dataId"+dataId,dataId,"testDemoService","oa_employment_apply",null);
    }

    @Override
    public void relationAbsenceProcess(String dataId) {

        flowCommonService.initActBusiness("入职流程：dataId"+dataId,dataId,"testDemoService","oa_absence_apply",null);
    }

    @Override
    public void relationJoaLeave(String dataId) {

        flowCommonService.initActBusiness("请假审批流程：dataId"+dataId,dataId,"testDemoService","joa_leave",null);
    }
   @Override
    public void bindCurrentFlow(String dataId){
//        flowCommonService.initActBusiness("通用审批流程：dataId"+dataId,dataId,"testDemoService","process_health_check",null);oa_officialdoc_received
//       flowCommonService.initActBusiness("测试流程：dataId"+dataId,dataId,"testDemoService","project_apply_concurrent",null);

       flowCommonService.initActBusiness("测试流程：dataId"+dataId,dataId,"testDemoService");

    }

    @Override
    public IPage<TestDemo> getCurrentUserTask(Page<TestDemo> page,QueryWrapper<TestDemo> queryWrapper) {

        List<FlowCurrentUserAndTaskBusi> myBusiList= flowTaskService.getCurrentUserBusiness();
        if(myBusiList==null&&myBusiList.size()<1){
            return null;
        }
        List<String> dataidList=new ArrayList<>(myBusiList.size());
        for(FlowCurrentUserAndTaskBusi busi: myBusiList) {
            if(busi.getBusiness()!=null){
                dataidList.add(busi.getBusiness().getDataId());
            }

        }
        if(dataidList==null||dataidList.size()==0){
            return null;
        }
        queryWrapper.in("t.id",dataidList);
        IPage<TestDemo> pageList = testDemoService.myPage(page, queryWrapper);
        //将任务更新为当前用户的目标任务
        if(pageList!=null&&pageList.getRecords()!=null){
            List<TestDemo> myTaskList=pageList.getRecords();
            for (TestDemo testDemo1 : myTaskList){
                for(FlowCurrentUserAndTaskBusi busi: myBusiList) {
                    if(busi.getBusiness()!=null&&busi.getBusiness().getDataId().equals(testDemo1.getDataId())){
                        testDemo1.setTaskId(busi.getTask().getId());
                        testDemo1.setTaskName(busi.getTask().getName());
                        //获取当前process的名称
                        ProcessDefinition processDefinition= flowDefinitionService.getProceddDefinitionByDataId(busi.getBusiness().getDataId());
                       if(processDefinition!=null) {
                           testDemo1.setProcessDefineName(processDefinition.getName());

                       }
                        log.info("--->update user task taskname:"+testDemo1.getTaskName()+"   taskId:"+testDemo1.getTaskId()+"    processName:"+testDemo1.getProcessDefineName()+"  pkey:"+testDemo1.getProcessDefinitionKey());
                    }

                }
            }
        }
        return pageList;
    }

    @Override
    public IPage<TestDemo> getCurrentUserApply( Page<TestDemo> page,QueryWrapper<TestDemo> queryWrapper) {

        SysUser loginUser = iFlowThirdService.getLoginUser();
        queryWrapper.eq("t.create_by",loginUser.getUsername());
        IPage<TestDemo> pageList = testDemoService.myPage(page, queryWrapper);
        if(pageList!=null&&pageList.getRecords()!=null){
            List<TestDemo> myTaskList=pageList.getRecords();
            for (TestDemo testDemo1 : myTaskList){
                ProcessDefinition processDefinition= flowDefinitionService.getProceddDefinitionByDataId(testDemo1.getDataId());
                if(processDefinition!=null) {
                    testDemo1.setProcessDefineName(processDefinition.getName());

                }
            }
        }
        return pageList;
    }



    @Override
    public boolean save(TestDemo testDemo) {
        /**新增数据，初始化流程关联信息**/
        testDemo.setId(IdUtil.fastSimpleUUID());
        this.relationAct(testDemo.getId());
        return super.save(testDemo);
    }
    @Override
    public boolean removeById(Serializable id) {
        /**删除数据，移除流程关联信息**/
        flowCommonService.delActBusiness(id.toString());
        return super.removeById(id);
    }

}
