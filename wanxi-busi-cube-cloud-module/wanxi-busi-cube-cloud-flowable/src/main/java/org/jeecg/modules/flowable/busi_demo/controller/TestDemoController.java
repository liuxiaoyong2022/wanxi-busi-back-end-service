package org.jeecg.modules.flowable.busi_demo.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;

import org.jeecg.modules.flowable.apithird.business.entity.FlowMyBusiness;
import org.jeecg.modules.flowable.apithird.business.service.impl.FlowMyBusinessServiceImpl;
import org.jeecg.modules.flowable.apithird.entity.SysUser;
import org.jeecg.modules.flowable.apithird.service.IFlowThirdService;
import org.jeecg.modules.flowable.busi_demo.entity.TestDemo;
import org.jeecg.modules.flowable.busi_demo.service.ITestDemoService;
import org.jeecg.modules.flowable.domain.dto.FlowCurrentUserAndTaskBusi;
import org.jeecg.modules.flowable.service.IFlowDefinitionService;
import org.jeecg.modules.flowable.service.IFlowTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2021-11-30
 * @Version: V1.0
 */
@Api(tags="测试用户表")
@RestController
@RequestMapping("/test_demo/testDemo")
@Slf4j
public class TestDemoController extends JeecgController<TestDemo, ITestDemoService> {
	@Autowired
	private ITestDemoService testDemoService;

	@Autowired
	private IFlowTaskService flowTaskService;

	@Autowired
	FlowMyBusinessServiceImpl flowMyBusinessService;

	/**
	 * 分页列表查询
	 *
	 * @param testDemo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试用户表-分页列表查询")
	@ApiOperation(value="测试用户表-分页列表查询", notes="测试用户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestDemo testDemo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestDemo> queryWrapper = QueryGenerator.initQueryWrapper(testDemo, req.getParameterMap());
		Page<TestDemo> page = new Page<TestDemo>(pageNo, pageSize);
		//IPage<TestDemo> pageList = testDemoService.page(page, queryWrapper);

		IPage<TestDemo> pageList = testDemoService.myPage(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @AutoLog(value = "测试用户表-分页列表查询")
	 @ApiOperation(value="测试用户表-分页列表查询", notes="测试用户表-分页列表查询")
	 @GetMapping(value = "/myTaskList")
	 public Result<?> queryPageMyTaskList(TestDemo testDemo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<TestDemo> queryWrapper = QueryGenerator.initQueryWrapper(testDemo, req.getParameterMap());
		 Page<TestDemo> page = new Page<TestDemo>(pageNo, pageSize);
		 //IPage<TestDemo> pageList = testDemoService.page(page, queryWrapper);

//		 List<FlowCurrentUserAndTaskBusi> myBusiList= flowTaskService.getCurrentUserBusiness();
//         if(myBusiList==null&&myBusiList.size()<1){
//         	return Result.ok("当前用户没有任务！");
//		 }
//		 List<String> dataidList=new ArrayList<>(myBusiList.size());
//		 for(FlowCurrentUserAndTaskBusi busi: myBusiList) {
//		 	if(busi.getBusiness()!=null){
//				dataidList.add(busi.getBusiness().getDataId());
//			}
//
//		 }
//		 queryWrapper.in("t.id",dataidList);
		 IPage<TestDemo> pageList = testDemoService.getCurrentUserTask( page,queryWrapper);
//		 //将任务更新为当前用户的目标任务
//		 if(pageList!=null&&pageList.getRecords()!=null){
//		 	List<TestDemo> myTaskList=pageList.getRecords();
//		 	for (TestDemo testDemo1 : myTaskList){
//				for(FlowCurrentUserAndTaskBusi busi: myBusiList) {
//					if(busi.getBusiness()!=null&&busi.getBusiness().getDataId().equals(testDemo1.getDataId())){
//						testDemo1.setTaskId(busi.getTask().getId());
//						testDemo1.setTaskName(busi.getTask().getName());
//						log.info("--->update user task taskname:"+testDemo1.getTaskName()+"   taskId:"+testDemo1.getTaskId());
//					}
//
//				}
//			}
//		 }

		 return Result.OK(pageList);
	 }

	@AutoLog(value = "测试用户表-分页列表查询")
	@ApiOperation(value="测试用户表-分页列表查询", notes="测试用户表-分页列表查询")
	@GetMapping(value = "/myApplylist")
	public Result<?> queryPagemyApplyList(TestDemo testDemo,
										 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
										 HttpServletRequest req) {
		QueryWrapper<TestDemo> queryWrapper = QueryGenerator.initQueryWrapper(testDemo, req.getParameterMap());
		Page<TestDemo> page = new Page<TestDemo>(pageNo, pageSize);
		//IPage<TestDemo> pageList = testDemoService.page(page, queryWrapper);

//		SysUser loginUser = iFlowThirdService.getLoginUser();
//		queryWrapper.eq("t.create_by",loginUser.getUsername());
//		IPage<TestDemo> pageList = testDemoService.myPage(page, queryWrapper);
		IPage<TestDemo> pageList = testDemoService.getCurrentUserApply( page,queryWrapper);
		return Result.OK(pageList);
	}
	 /**
	 *   添加
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "测试用户表-添加")
	@ApiOperation(value="测试用户表-添加", notes="测试用户表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestDemo testDemo) {
		testDemoService.save(testDemo);
		return Result.OK("添加成功！");
	}
	 @RequestMapping(value = "/relationAct", method = RequestMethod.GET)
	 public Result<?> relationAct(HttpServletRequest request, HttpServletResponse response) {
		 String dataId = request.getParameter("dataId");
		 testDemoService.relationAct(dataId);
		 return Result.OK();
	 }
	 @RequestMapping(value = "/relationEmployeeProcess", method = RequestMethod.GET)
	 public Result<?> relationEmployeeProcess(HttpServletRequest request, HttpServletResponse response) {
		 String dataId = request.getParameter("dataId");
		 testDemoService.relationEmployeeProcess(dataId);
		 return Result.OK();
	 }

	 @RequestMapping(value = "/relationAbsenceProcess", method = RequestMethod.GET)
	 public Result<?> relationAbsenceProcess(HttpServletRequest request, HttpServletResponse response) {
		 String dataId = request.getParameter("dataId");
		 testDemoService.relationAbsenceProcess(dataId);
		 return Result.OK();
	 }

	 @RequestMapping(value = "/relationJoaLeave", method = RequestMethod.GET)
	 public Result<?> relationJoaLeave(HttpServletRequest request, HttpServletResponse response) {
		 String dataId = request.getParameter("dataId");
		 testDemoService.relationJoaLeave(dataId);
		 return Result.OK();
	 }
	 @RequestMapping(value = "/bindCurrentFlow", method = RequestMethod.GET)
	 public Result<?> bindCurrentFlow(HttpServletRequest request, HttpServletResponse response) {
		 String dataId = request.getParameter("dataId");
		 testDemoService.bindCurrentFlow(dataId);
		 return Result.OK();
	 }
	 /**
	 *  编辑
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "测试用户表-编辑")
	@ApiOperation(value="测试用户表-编辑", notes="测试用户表-编辑")
	@PutMapping(value = "/edit")
	public  Result<?> edit(@RequestBody TestDemo testDemo) {
		testDemoService.updateById(testDemo);
		//更新用户选择的流程到业务扩展表中
		LambdaQueryWrapper<FlowMyBusiness> flowMyBusinessQueryWrapper = new LambdaQueryWrapper<>();
		flowMyBusinessQueryWrapper.eq(FlowMyBusiness::getDataId,testDemo.getId());
		FlowMyBusiness business = flowMyBusinessService.getOne(flowMyBusinessQueryWrapper);
		if (business!=null){
			business.setProcessDefinitionKey(testDemo.getProcessDefinitionKey());
			 flowMyBusinessService.updateById(business);

		}
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用户表-通过id删除")
	@ApiOperation(value="测试用户表-通过id删除", notes="测试用户表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {

		LambdaQueryWrapper<FlowMyBusiness> flowMyBusinessQueryWrapper = new LambdaQueryWrapper<>();
		flowMyBusinessQueryWrapper.eq(FlowMyBusiness::getDataId,id);
		FlowMyBusiness business = flowMyBusinessService.getOne(flowMyBusinessQueryWrapper);

		if(business!=null&&business.getProcessInstanceId()!=null) {
			flowTaskService.deleteProcessInstance(business.getProcessInstanceId());
		}
		testDemoService.removeById(id);

		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试用户表-批量删除")
	@ApiOperation(value="测试用户表-批量删除", notes="测试用户表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testDemoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用户表-通过id查询")
	@ApiOperation(value="测试用户表-通过id查询", notes="测试用户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestDemo testDemo = testDemoService.getById(id);
		if(testDemo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(testDemo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testDemo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestDemo testDemo) {
        return super.exportXls(request, testDemo, TestDemo.class, "测试用户表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TestDemo.class);
    }


}
