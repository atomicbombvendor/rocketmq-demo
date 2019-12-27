package com.bfxy.store.service.provider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bfxy.store.api.StoreServiceApi;
import com.bfxy.store.mapper.StoreMapper;

/**
 * provider是对外部提供的接口，对其他服务提供。
 * 如果一个接口同时对外部和内部都提供，应该在内部和外部的包，写两遍。
 * 如果内部或者外部的接口发生了变化，这是代码的适当冗余。
 *
 * 对外提供的Service，使用dubbo提供的server注解。这个Service也是可以注入到Spring
 * 配置dubbo的协议服务。
 * 新版本的dubbo使用Reference注解。
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class StoreServiceProvider implements StoreServiceApi {

	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public int selectVersion(String supplierId, String goodsId) {
		return storeMapper.selectVersion(supplierId, goodsId);
	}

	@Override
	public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy,
			Date updateTime) {
		return storeMapper.updateStoreCountByVersion(version, supplierId, goodsId, updateBy, updateTime);
	}

	@Override
	public int selectStoreCount(String supplierId, String goodsId) {
		return storeMapper.selectStoreCount(supplierId, goodsId);
	}

}
