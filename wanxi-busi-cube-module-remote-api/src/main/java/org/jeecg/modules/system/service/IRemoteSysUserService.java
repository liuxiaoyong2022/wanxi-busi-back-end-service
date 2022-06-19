package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.ServiceNameConstants;
import org.jeecg.common.system.vo.SysUserCacheInfo;
import org.jeecg.modules.system.domain.dto.SysUserDto;
//import org.jeecg.modules.system.entity.SysUser;
//import org.jeecg.modules.system.model.SysUserSysDepartModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@FeignClient( contextId = "sysUserRemoteApi", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface IRemoteSysUserService {

	/**
	 * 重置密码
	 *

	 * @return
	 */
	@GetMapping("/remote/user/list")
	public  List<SysUserDto>   list();
	/**
	 * 修改密码
	 *
	 *
	 * @return
	 */
	@GetMapping("/remote/user/getUserByRoleId")
	public IPage<SysUserDto>  getUserByRoleId(@RequestBody Page<SysUserDto> page,
											  @RequestParam(name="roleId") String roleId,
											  @RequestParam(name="username") String username);

//	/**
//	 * 删除用户
//	 * @param userId
//	 * @return
//	 */
//	public boolean deleteUser(String userId);
//
//	/**
//	 * 批量删除用户
//	 * @param userIds
//	 * @return
//	 */
//	public boolean deleteBatchUsers(String userIds);
//
//	public SysUserDto getUserByName(String username);
//
//	/**
//	 * 添加用户和用户角色关系
//	 * @param user
//	 * @param roles
//	 */
//	public void addUserWithRole(SysUserDto user,String roles);
//
//
//	/**
//	 * 修改用户和用户角色关系
//	 * @param user
//	 * @param roles
//	 */
//	public void editUserWithRole(SysUserDto user,String roles);
//
//	/**
//	 * 获取用户的授权角色
//	 * @param username
//	 * @return
//	 */
//	public List<String> getRole(String username);
//
//	/**
//	  * 查询用户信息包括 部门信息
//	 * @param username
//	 * @return
//	 */
////	public SysUserCacheInfo getCacheUser(String username);
//
//	/**
//	 * 根据部门Id查询
//	 * @param
//	 * @return
//	 */
////	public IPage<SysUserDto> getUserByDepId(Page<SysUserDto> page, String departId, String username);
//
//	/**
//	 * 根据部门Ids查询
//	 * @param
//	 * @return
//	 */
////	public IPage<SysUser> getUserByDepIds(Page<SysUser> page, List<String> departIds, String username);
//
//	/**
//	 * 根据 userIds查询，查询用户所属部门的名称（多个部门名逗号隔开）
//	 * @param
//	 * @return
//	 */
//	public Map<String,String> getDepNamesByUserIds(List<String> userIds);
//
//    /**
//     * 根据部门 Id 和 QueryWrapper 查询
//     *
//     * @param page
//     * @param departId
//     * @param queryWrapper
//     * @return
//     */
////    public IPage<SysUser> getUserByDepartIdAndQueryWrapper(Page<SysUser> page, String departId, QueryWrapper<SysUser> queryWrapper);
//
//	/**
//	 * 根据 orgCode 查询用户，包括子部门下的用户
//	 *
//	 * @param orgCode
//	 * @param userParams 用户查询条件，可为空
//	 * @param page 分页参数
//	 * @return
//	 */
////	IPage<SysUserSysDepartModel> queryUserByOrgCode(String orgCode, SysUser userParams, IPage page);
//
//	/**
//	 * 根据角色Id查询
//	 * @param
//	 * @return
//	 */
//
//
//	/**
//	 * 通过用户名获取用户角色集合
//	 *
//	 * @param username 用户名
//	 * @return 角色集合
//	 */
//	Set<String> getUserRolesSet(String username);
//
//	/**
//	 * 通过用户名获取用户权限集合
//	 *
//	 * @param username 用户名
//	 * @return 权限集合
//	 */
//	Set<String> getUserPermissionsSet(String username);
//
//	/**
//	 * 根据用户名设置部门ID
//	 * @param username
//	 * @param orgCode
//	 */
//	void updateUserDepart(String username,String orgCode);
//
//	/**
//	 * 根据手机号获取用户名和密码
//	 */
//	public SysUserDto getUserByPhone(String phone);
//
//
//	/**
//	 * 根据邮箱获取用户
//	 */
//	public SysUserDto getUserByEmail(String email);
//
//
//	/**
//	 * 添加用户和用户部门关系
//	 * @param user
//	 * @param selectedParts
//	 */
//	void addUserWithDepart(SysUserDto user, String selectedParts);
//
//	/**
//	 * 编辑用户和用户部门关系
//	 * @param user
//	 * @param departs
//	 */
//	void editUserWithDepart(SysUserDto user, String departs);
//
//	/**
//	   * 校验用户是否有效
//	 * @param sysUser
//	 * @return
//	 */
////	Result checkUserIsEffective(SysUserDto sysUser);
//
//	/**
//	 * 查询被逻辑删除的用户
//	 */
//	List<SysUserDto> queryLogicDeleted();
//
//	/**
//	 * 查询被逻辑删除的用户（可拼装查询条件）
//	 */
////	List<SysUserDto> queryLogicDeleted(LambdaQueryWrapper<SysUserDto> wrapper);
//
//	/**
//	 * 还原被逻辑删除的用户
//	 */
//	boolean revertLogicDeleted(List<String> userIds, SysUserDto updateEntity);
//
//	/**
//	 * 彻底删除被逻辑删除的用户
//	 */
//	boolean removeLogicDeleted(List<String> userIds);
//
//    /**
//     * 更新手机号、邮箱空字符串为 null
//     */
//    boolean updateNullPhoneEmail();
//
//	/**
//	 * 保存第三方用户信息
//	 * @param sysUser
//	 */
//	void saveThirdUser(SysUserDto sysUser);
//
//	/**
//	 * 根据部门Ids查询
//	 * @param
//	 * @return
//	 */
//	List<SysUserDto> queryByDepIds(List<String> departIds, String username);
//
//	/**
//	 * 保存用户
//	 * @param user 用户
//	 * @param selectedRoles 选择的角色id，多个以逗号隔开
//	 * @param selectedDeparts 选择的部门id，多个以逗号隔开
//	 */
//	void saveUser(SysUserDto user, String selectedRoles, String selectedDeparts);
//
//	/**
//	 * 编辑用户
//	 * @param user 用户
//	 * @param roles 选择的角色id，多个以逗号隔开
//	 * @param departs 选择的部门id，多个以逗号隔开
//	 */
//	void editUser(SysUserDto user, String roles, String departs);
//
//	/** userId转为username */
//	List<String> userIdToUsername(Collection<String> userIdList);

}
