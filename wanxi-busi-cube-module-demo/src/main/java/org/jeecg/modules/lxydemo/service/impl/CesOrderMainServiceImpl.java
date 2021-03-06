package org.jeecg.modules.lxydemo.service.impl;

import org.jeecg.modules.lxydemo.entity.CesOrderMain;
import org.jeecg.modules.lxydemo.entity.CesOrderGoods;
import org.jeecg.modules.lxydemo.entity.CesOrderCustomer;
import org.jeecg.modules.lxydemo.mapper.CesOrderGoodsMapper;
import org.jeecg.modules.lxydemo.mapper.CesOrderCustomerMapper;
import org.jeecg.modules.lxydemo.mapper.CesOrderMainMapper;
import org.jeecg.modules.lxydemo.service.ICesOrderMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商城订单表
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
@Service
public class CesOrderMainServiceImpl extends ServiceImpl<CesOrderMainMapper, CesOrderMain> implements ICesOrderMainService {

	@Autowired
	private CesOrderMainMapper cesOrderMainMapper;
	@Autowired
	private CesOrderGoodsMapper cesOrderGoodsMapper;
	@Autowired
	private CesOrderCustomerMapper cesOrderCustomerMapper;
	
	@Override
	@Transactional
	public void saveMain(CesOrderMain cesOrderMain, List<CesOrderGoods> cesOrderGoodsList,List<CesOrderCustomer> cesOrderCustomerList) {
		cesOrderMainMapper.insert(cesOrderMain);
		if(cesOrderGoodsList!=null && cesOrderGoodsList.size()>0) {
			for(CesOrderGoods entity:cesOrderGoodsList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderGoodsMapper.insert(entity);
			}
		}
		if(cesOrderCustomerList!=null && cesOrderCustomerList.size()>0) {
			for(CesOrderCustomer entity:cesOrderCustomerList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(CesOrderMain cesOrderMain,List<CesOrderGoods> cesOrderGoodsList,List<CesOrderCustomer> cesOrderCustomerList) {
		cesOrderMainMapper.updateById(cesOrderMain);
		
		//1.先删除子表数据
		cesOrderGoodsMapper.deleteByMainId(cesOrderMain.getId());
		cesOrderCustomerMapper.deleteByMainId(cesOrderMain.getId());
		
		//2.子表数据重新插入
		if(cesOrderGoodsList!=null && cesOrderGoodsList.size()>0) {
			for(CesOrderGoods entity:cesOrderGoodsList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderGoodsMapper.insert(entity);
			}
		}
		if(cesOrderCustomerList!=null && cesOrderCustomerList.size()>0) {
			for(CesOrderCustomer entity:cesOrderCustomerList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		cesOrderGoodsMapper.deleteByMainId(id);
		cesOrderCustomerMapper.deleteByMainId(id);
		cesOrderMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			cesOrderGoodsMapper.deleteByMainId(id.toString());
			cesOrderCustomerMapper.deleteByMainId(id.toString());
			cesOrderMainMapper.deleteById(id);
		}
	}
	
}
