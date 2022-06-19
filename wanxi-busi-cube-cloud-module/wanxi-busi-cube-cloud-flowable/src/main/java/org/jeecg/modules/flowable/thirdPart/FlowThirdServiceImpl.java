package org.jeecg.modules.flowable.thirdPart;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.flowable.apithird.entity.SysCategory;
import org.jeecg.modules.flowable.apithird.entity.SysRole;
import org.jeecg.modules.flowable.apithird.entity.SysUser;
import org.jeecg.modules.flowable.apithird.service.IFlowThirdService;
//import org.jeecg.modules.system.service.impl.SysRoleServiceImpl;
//import org.jeecg.modules.system.service.impl.SysUserServiceImpl;
import org.jeecg.modules.system.domain.dto.SysRoleDto;
import org.jeecg.modules.system.domain.dto.SysUserDto;
import org.jeecg.modules.system.service.IRemoteSysRoleService;
import org.jeecg.modules.system.service.IRemoteSysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * flowable模块必需实现类
 *@author PanMeiCheng
 *@date 2021/11/22
 *@version 1.0
 */
@Service
public class FlowThirdServiceImpl implements IFlowThirdService {
    @Autowired
    ISysBaseAPI sysBaseAPI;
    @Autowired
    IRemoteSysUserService remoteSysUserService;
    @Autowired
    IRemoteSysRoleService remoteSysRoleService;
    @Override
    public SysUser getLoginUser() {
        LoginUser sysUser = null;
        SysUser copyProperties = null;
        try {
            sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            copyProperties = BeanUtil.copyProperties(sysUser, SysUser.class);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return copyProperties;
    }

    @Override
    public List<SysUser> getAllUser() {
        List<SysUserDto> list = remoteSysUserService.list();
        List<SysUser> userList = list.stream().map(o -> BeanUtil.copyProperties(o, SysUser.class)).collect(Collectors.toList());
        return userList;
    }

    @Override
    public List<SysUser> getUsersByRoleId(String roleId) {
        Page<SysUserDto> page = new Page<>(1,Integer.MAX_VALUE);
        IPage<SysUserDto> userByRoleId = remoteSysUserService.getUserByRoleId(page, roleId, null);
        List<SysUserDto> records = userByRoleId.getRecords();
        List<SysUser> userList = records.stream().map(o -> BeanUtil.copyProperties(o, SysUser.class)).collect(Collectors.toList());
        return userList;
    }


    @Override
    public SysUser getUserByUsername(String username) {
        LoginUser userByName = sysBaseAPI.getUserByName(username);
        return userByName==null?null:BeanUtil.copyProperties(userByName, SysUser.class);
    }

    @Override
    public List<SysRole> getAllRole() {
        List<SysRoleDto> list = remoteSysRoleService.list();
        List<SysRole> roleList = list.stream().map(o -> BeanUtil.copyProperties(o, SysRole.class)).collect(Collectors.toList());
        return roleList;
    }
    @Override
    public List<SysCategory> getAllCategory() {
        // todo 获取流程分类信息，此处为例子
        SysCategory category1 = new SysCategory();
        category1.setId("oa");
        category1.setName("OA");
        SysCategory category2 = new SysCategory();
        category2.setId("cw");
        category2.setName("财务");
        ArrayList<SysCategory> sysCategories = Lists.newArrayList(category1,category2);
        return sysCategories;
    }

    @Override
    public List<String> getDepartNamesByUsername(String username) {
        List<String> departNamesByUsername = sysBaseAPI.getDepartNamesByUsername(username);
        return departNamesByUsername;
    }
}
