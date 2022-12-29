package org.zuoyu.concurrent.service.direct;

import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;

/**
 * @Description TODO 验价服务
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/29 13:44
 * @Version 1.0
 */
public interface VerifyService {

	/**
	 * 验价
	 * @param body - 询价返回参数
	 */
	void verify(CtripQDirectVo.CtripQSearchResponse body);
}
