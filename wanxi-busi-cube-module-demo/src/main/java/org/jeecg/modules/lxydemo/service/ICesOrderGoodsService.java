package org.jeecg.modules.lxydemo.service;

import org.jeecg.modules.lxydemo.entity.CesOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
public interface ICesOrderGoodsService extends IService<CesOrderGoods> {

	public List<CesOrderGoods> selectByMainId(String mainId);
}
