package org.jeecg.modules.lxydemo.service;

import org.jeecg.modules.lxydemo.entity.CesOrderCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
public interface ICesOrderCustomerService extends IService<CesOrderCustomer> {

	public List<CesOrderCustomer> selectByMainId(String mainId);
}
