package org.zuoyu.concurrent.model;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description TODO 验价结果聚合（分钟）
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/27 10:40
 * @Version 1.0
 */
@Document(indexName = "verify_aggregation")
public class VerifyAggregation extends Aggregation {

	private static final long serialVersionUID = -4964739297126262923L;

	public VerifyAggregation() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
