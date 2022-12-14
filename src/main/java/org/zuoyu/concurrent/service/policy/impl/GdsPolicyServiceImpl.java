package org.zuoyu.concurrent.service.policy.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.service.RedisService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/12 10:58
 * @Version 1.0
 */
@Slf4j
@Service
public class GdsPolicyServiceImpl implements GdsPolicyService {

	private static final String KEY = "gds:policy";
	private static final String TYPE = "@type";
	private final Set<GdsPolicy> gdsPolicySet;

	public GdsPolicyServiceImpl(@NonNull RedisService redisService) {
		// 这里对政策进行了过滤，目前只查询携程说走就走国际的单程
		Set<String> keys = redisService.hashKeys(KEY).stream()
				.filter(s -> s.endsWith(Modules.XCSZJZGJ) && s.contains(Integer.toString(1)))
				.collect(Collectors.toSet());
		List<Set<JSONObject>> multiCacheMapValue = redisService.getMultiCacheMapValue(KEY, keys);
		gdsPolicySet = multiCacheMapValue.stream().flatMap(Collection::stream).map(json -> {
			json.remove(TYPE);
			return json.to(GdsPolicy.class);
		})
				.collect(Collectors.toSet());
	}

	@Override
	public Set<GdsPolicy> getGdsPolicySet() {
		return this.gdsPolicySet;
	}
}
