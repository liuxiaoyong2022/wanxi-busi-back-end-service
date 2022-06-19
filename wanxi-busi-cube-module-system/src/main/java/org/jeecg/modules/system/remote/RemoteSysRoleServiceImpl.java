package org.jeecg.modules.system.remote;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.ImportExcelUtil;
import org.jeecg.modules.system.domain.dto.SysRoleDto;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUserDepart;
import org.jeecg.modules.system.mapper.SysRoleMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.jeecg.modules.system.service.IRemoteSysRoleService;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Service
@Slf4j
@RestController
@RequestMapping("/remote/role")
public class RemoteSysRoleServiceImpl  implements IRemoteSysRoleService {
    @Autowired
    ISysRoleService sysRoleService;


    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SysRoleDto> list() {
       log.info("-------> remote role i get it!");
        List<SysRole> list=sysRoleService.list();
        if(list!=null&&list.size()>0) {
            List<SysRoleDto> dtoList=new ArrayList<>();
            for (SysRole role : list) {
                //属性copy
                SysRoleDto dto=new SysRoleDto();
                dto.setRoleId(role.getId());
                dto.setRoleCode(role.getRoleCode());
                dto.setDescription(role.getDescription());
                dto.setRoleName(role.getRoleName());
                dtoList.add(dto);

            }
            return dtoList;
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public boolean deleteRole(String roleId) {
        return sysRoleService.deleteRole(roleId);
    }

    @Override
    @RequestMapping(value = "/deleteBatchRole", method = RequestMethod.POST)
    public boolean deleteBatchRole(String[] roleids) {
        return sysRoleService.deleteBatchRole(roleids);
    }
}
