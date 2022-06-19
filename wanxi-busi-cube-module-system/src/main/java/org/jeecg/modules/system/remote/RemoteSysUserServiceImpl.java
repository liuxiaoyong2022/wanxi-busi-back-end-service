package org.jeecg.modules.system.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysUserCacheInfo;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.base.service.BaseCommonService;
import org.jeecg.modules.system.domain.dto.SysRoleDto;
import org.jeecg.modules.system.domain.dto.SysUserDto;
import org.jeecg.modules.system.entity.*;
import org.jeecg.modules.system.mapper.*;
import org.jeecg.modules.system.model.SysUserSysDepartModel;
import org.jeecg.modules.system.service.IRemoteSysUserService;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecg.modules.system.service.impl.ThirdAppDingtalkServiceImpl;
import org.jeecg.modules.system.service.impl.ThirdAppWechatEnterpriseServiceImpl;
import org.jeecg.modules.system.vo.SysUserDepVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
@RestController
@RequestMapping("/remote/user")
public class RemoteSysUserServiceImpl implements IRemoteSysUserService {

	@Autowired
	ISysUserService sysUserService;

	@Override
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<SysUserDto> list() {
		List<SysUser> list=sysUserService.list();
		if(list!=null&&list.size()>0) {
			List<SysUserDto> dtoList=new ArrayList<>();
			for (SysUser user : list) {
				//属性copy
				SysUserDto userDto=new SysUserDto();

				BeanUtils.copyProperties(user, userDto);
				dtoList.add(userDto);

			}
			return dtoList;
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/getUserByRoleId", method = RequestMethod.GET)
	public IPage<SysUserDto> getUserByRoleId(@RequestBody  Page<SysUserDto> page,
											 String roleId,
											 String username) {


		List<SysUserDto> dtoList=page.getRecords();
		if(dtoList!=null&&dtoList.size()>0){
			List<SysUser> userList=new ArrayList<>();
			for (SysUserDto userDto : dtoList) {
				//属性copy
				SysUser user = new SysUser();
				BeanUtils.copyProperties(userDto, user);
				userList.add(user);
			}
			Page<SysUser> userPage=new Page();
			BeanUtils.copyProperties(page, userPage);
			userPage.setRecords(userList);
			IPage<SysUser> sysUserPage=sysUserService.getUserByRoleId(userPage,roleId,username);

			if(sysUserPage!=null&&
					sysUserPage.getRecords()!=null&&
					sysUserPage.getRecords().size()>0) {
				List<SysUserDto> userDtoList = new ArrayList<>();
				for (SysUser user:sysUserPage.getRecords()) {
					SysUserDto userDto=new SysUserDto();
					BeanUtils.copyProperties(user, userDto);
					userDtoList.add(userDto);
				}
				IPage<SysUserDto> dtoPage=new Page<>();
				BeanUtils.copyProperties(sysUserPage, dtoPage);
				dtoPage.setRecords(userDtoList);

				return dtoPage;

			}

		}


		return null;
	}

//	@Override
//	public boolean deleteUser(String userId) {
//		return false;
//	}
//
//	@Override
//	public boolean deleteBatchUsers(String userIds) {
//		return false;
//	}
//
//	@Override
//	public SysUserDto getUserByName(String username) {
//		return null;
//	}
//
//	@Override
//	public void addUserWithRole(SysUserDto user, String roles) {
//
//	}
//
//	@Override
//	public void editUserWithRole(SysUserDto user, String roles) {
//
//	}
//
//	@Override
//	public List<String> getRole(String username) {
//		return null;
//	}
//
//	@Override
//	public Map<String, String> getDepNamesByUserIds(List<String> userIds) {
//		return null;
//	}
//
//	@Override
//	public Set<String> getUserRolesSet(String username) {
//		return null;
//	}
//
//	@Override
//	public Set<String> getUserPermissionsSet(String username) {
//		return null;
//	}
//
//	@Override
//	public void updateUserDepart(String username, String orgCode) {
//
//	}
//
//	@Override
//	public SysUserDto getUserByPhone(String phone) {
//		return null;
//	}
//
//	@Override
//	public SysUserDto getUserByEmail(String email) {
//		return null;
//	}
//
//	@Override
//	public void addUserWithDepart(SysUserDto user, String selectedParts) {
//
//	}
//
//	@Override
//	public void editUserWithDepart(SysUserDto user, String departs) {
//
//	}
//
//	@Override
//	public List<SysUserDto> queryLogicDeleted() {
//		return null;
//	}
//
//	@Override
//	public boolean revertLogicDeleted(List<String> userIds, SysUserDto updateEntity) {
//		return false;
//	}
//
//	@Override
//	public boolean removeLogicDeleted(List<String> userIds) {
//		return false;
//	}
//
//	@Override
//	public boolean updateNullPhoneEmail() {
//		return false;
//	}
//
//	@Override
//	public void saveThirdUser(SysUserDto sysUser) {
//
//	}
//
//	@Override
//	public List<SysUserDto> queryByDepIds(List<String> departIds, String username) {
//		return null;
//	}
//
//	@Override
//	public void saveUser(SysUserDto user, String selectedRoles, String selectedDeparts) {
//
//	}
//
//	@Override
//	public void editUser(SysUserDto user, String roles, String departs) {
//
//	}
//
//	@Override
//	public List<String> userIdToUsername(Collection<String> userIdList) {
//		return null;
//	}
}
