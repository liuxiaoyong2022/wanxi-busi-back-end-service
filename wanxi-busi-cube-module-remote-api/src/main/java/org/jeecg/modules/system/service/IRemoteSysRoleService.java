package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.ServiceNameConstants;
import org.jeecg.modules.system.domain.dto.SysRoleDto;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Component
@FeignClient( contextId = "sysRoleRemoteApi", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface IRemoteSysRoleService {

    /**
     * 导入 excel ，检查 roleCode 的唯一性
     *

     * @return
     * @throws Exception
     */
    @GetMapping("/remote/role/list")
   public List<SysRoleDto>   list();

    /**
     * 删除角色
     * @param
     * @return
     */
    @PostMapping("/remote/role/deleteRole")
    public boolean deleteRole(@RequestParam(name="roleId") String roleId);

    /**
     * 批量删除角色
     * @param roleids
     * @return
     */
    @PostMapping("/remote/role/deleteBatchRole")
    public boolean deleteBatchRole(@RequestParam(name="roleids")  String[] roleids);

}
