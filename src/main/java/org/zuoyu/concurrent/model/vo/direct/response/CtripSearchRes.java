package org.zuoyu.concurrent.model.vo.direct.response;

import java.util.ArrayList;
import java.util.List;

import org.zuoyu.concurrent.model.vo.direct.vo.CtripRouting;


/**
 * @author huangpan
 * @ClassName: CtripSearchRes
 * @Description: TODO(携程直连询价返回实体)
 * @date 2017年12月13日 下午3:34:15
 */
public class CtripSearchRes extends CtripBaseResponse {

    private static final long serialVersionUID = 3610750958120089164L;

    /**
     * 报价信息
     */
    private List<CtripRouting> routings;

    public List<CtripRouting> getRoutings() {
        return routings;
    }

    public void setRoutings(List<CtripRouting> routings) {
        if (routings == null) {
            routings = new ArrayList<>();
        }
        this.routings = routings;
    }

    public CtripSearchRes() {
    }

    public CtripSearchRes(int status, String msg) {
        super(status, msg);
    }
}
