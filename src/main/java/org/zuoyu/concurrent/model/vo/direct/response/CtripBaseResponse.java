package org.zuoyu.concurrent.model.vo.direct.response;

import java.io.Serializable;

public class CtripBaseResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 0, 成功；
     * 3,其他失败原因；
     * 4,ctrip请求参数错误；
     * 5,程序异常；
     * -1,网络异常(ctrip使用)
     * -2,response数据异常（ctrip使用）
     * 【当查询实际无航线政策数据时也请返回成功】
     */
    private int status;

    /**
     * 提示信息，长度小于 64。
     */
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CtripBaseResponse() {
        super();
    }

    public CtripBaseResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
