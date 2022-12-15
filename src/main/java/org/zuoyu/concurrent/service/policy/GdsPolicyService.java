package org.zuoyu.concurrent.service.policy;

import java.util.Set;

import org.zuoyu.concurrent.model.vo.GdsPolicy;

/**
 * @Description TODO GDS政策服务
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/12 10:58
 * @Version 1.0
 */
public interface GdsPolicyService {

	/**
	 * 获取政策列表
	 * @return 政策列表
	 */
	Set<GdsPolicy> getGdsPolicySet();
}
