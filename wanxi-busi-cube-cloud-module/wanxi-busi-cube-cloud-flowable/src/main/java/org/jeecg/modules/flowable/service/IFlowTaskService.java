package org.jeecg.modules.flowable.service;

import org.flowable.task.api.Task;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.flowable.apithird.business.entity.FlowMyBusiness;
import org.jeecg.modules.flowable.domain.dto.FlowCurrentUserAndTaskBusi;
import org.jeecg.modules.flowable.domain.dto.FlowNextDto;
import org.jeecg.modules.flowable.domain.dto.FlowViewerDto;
import org.jeecg.modules.flowable.domain.vo.FlowTaskVo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 */
public interface IFlowTaskService {

    /**
     * 审批任务
     *
     * @param task 请求实体参数
     */
    Result complete(FlowTaskVo task);
    Result completeByDateId(FlowTaskVo flowTaskVo);
    /**
     * 驳回任务
     *
     * @param flowTaskVo
     */
    void taskReject(FlowTaskVo flowTaskVo);
    void taskRejectByDataId(FlowTaskVo flowTaskVo);

    /**
     * 退回任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void taskReturn(FlowTaskVo flowTaskVo);
    void taskReturnByDataId(FlowTaskVo flowTaskVo);
    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo
     * @return
     */
    Result findReturnTaskList(FlowTaskVo flowTaskVo);
    Result findReturnTaskListByDataId(FlowTaskVo flowTaskVo);

    /**
     * 删除任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void deleteTask(FlowTaskVo flowTaskVo);

    /**
     * 认领/签收任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void claim(FlowTaskVo flowTaskVo);

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void unClaim(FlowTaskVo flowTaskVo);

    /**
     * 委派任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void delegateTask(FlowTaskVo flowTaskVo);


    /**
     * 转办任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void assignTask(FlowTaskVo flowTaskVo);

    /**
     * 我发起的流程
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result myProcess(Integer pageNum, Integer pageSize);

    /**
     * 取消申请
     * @param flowTaskVo
     * @return
     */
    Result stopProcess(FlowTaskVo flowTaskVo);

    /**
     * 撤回流程
     * @param flowTaskVo
     * @return
     */
    Result revokeProcess(FlowTaskVo flowTaskVo);


    /**
     * 代办任务列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return
     */
    Result todoList(Integer pageNum, Integer pageSize);


    /**
     * 已办任务列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return
     */
    Result finishedList(Integer pageNum, Integer pageSize);

    /**
     * 流程历史流转记录
     *
     * @param procInsId 流程实例Id
     * @return
     */
    Result flowRecord(String dataId);

    /**
     * 根据任务ID查询挂载的表单信息
     *
     * @param taskId 任务Id
     * @return
     */
    Task getTaskForm(String taskId);

    /**
     * 获取流程过程图
     * @param processId
     * @return
     */
    InputStream diagram(String processId);

    /**
     * 获取流程执行过程
     * @param procInsId
     * @return
     */
    List<FlowViewerDto> getFlowViewer(String procInsId);
    List<FlowViewerDto> getFlowViewerByDataId(String dataId);
    /**
     * 获取流程变量
     * @param taskId
     * @return
     */
    Result processVariables(String taskId);

    /**
     * 获取下一节点
     * @param flowTaskVo 任务
     * @return
     */
    Result getNextFlowNode(FlowTaskVo flowTaskVo);

    FlowNextDto getNextFlowNode(String taskId, Map<String, Object> values);


    public void prepareNextFlowNode(Task task,
                             FlowMyBusiness business,
                             FlowTaskVo taskVo,
                             FlowNextDto nextFlowNode);


    public List<FlowCurrentUserAndTaskBusi> getCurrentUserBusiness( );

    public void deleteProcessInstance(String instanceId);
}
