package org.zuoyu.concurrent.model;

import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description TODO 验价结果统计
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/2 15:40
 * @Version 1.0
 */
@Document(indexName = "verify_result")
public class VerifyResult extends Result<CtripQDirectVo.CtripQOrderResponse> {

	private static final long serialVersionUID = -4453671058712747927L;
}
