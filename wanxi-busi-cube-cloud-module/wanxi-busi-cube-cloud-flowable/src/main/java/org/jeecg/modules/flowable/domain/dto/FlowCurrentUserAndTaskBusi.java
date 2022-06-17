package org.jeecg.modules.flowable.domain.dto;

import lombok.Data;
import org.flowable.task.api.Task;
import org.jeecg.modules.flowable.apithird.business.entity.FlowMyBusiness;
import org.jeecg.modules.flowable.apithird.entity.SysUser;

import java.io.Serializable;
import java.util.List;

/**
 * 人员、组
 */
@Data
public class FlowCurrentUserAndTaskBusi implements Serializable {
    /**
     * 节点对象
     */
    private Task Task;
    /**
     * 待办人员
     */
    private SysUser currentUser;

    private FlowMyBusiness business;

}
