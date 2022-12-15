package org.zuoyu.concurrent.model.vo.direct.response;


import org.zuoyu.concurrent.model.vo.direct.vo.CtripRouting;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripRule;

/**
 * @author huangpan
 * @ClassName: CtripVerifyRes
 * @Description: TODO(携程直连价格校验返回实体)
 * @date 2017年12月14日 上午9:53:02
 */
public class CtripVerifyRes extends CtripBaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = 2863173808556220034L;

    /**
     * 会话标识：标记服务接口调用的唯一标识；
     * 数字或字母，长度小于 20 个字符 。
     */
    private String sessionId;

    /**
     * 可预订的座位数，最大为9；
     * 供应商需要确保下maxSeats不小于验价请求人数。
     */
    private Integer maxSeats;

    /**
     * 供应商实际外部错误码
     */
    private String cerrorCode;

    /**
     * 供应商实际外部错误信息描述，长度小于 300
     */
    private String cerrorMsg;

    /**
     * 报价信息
     * 1）参考搜索返回结果中的 Routing Elements
     * 2）不含airlineAncillaries及Rule部分
     */
    private CtripRouting routing;

    /**
     * 退改签信息及行李额信息
     * 1）参考搜索返回结果中的 Rule Element；
     * 2）不需返回serviceFee、isUseCtripRule、tariffNo及ruleNo
     * 3）qte接口，如果产品是公布运价，退改签获取异常和未获取到结果，需要将价格校验接口返回参数的status标注为6
     */
    private CtripRule rule;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getCerrorCode() {
        return cerrorCode;
    }

    public void setCerrorCode(String cerrorCode) {
        this.cerrorCode = cerrorCode;
    }

    public String getCerrorMsg() {
        return cerrorMsg;
    }

    public void setCerrorMsg(String cerrorMsg) {
        this.cerrorMsg = cerrorMsg;
    }

    public CtripRouting getRouting() {
        return routing;
    }

    public void setRouting(CtripRouting routing) {
        this.routing = routing;
    }

    public CtripRule getRule() {
        return rule;
    }

    public void setRule(CtripRule rule) {
        this.rule = rule;
    }

    public CtripVerifyRes() {
    }

    public CtripVerifyRes(int status, String msg) {
        super(status, msg);
    }
}
