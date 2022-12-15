package org.zuoyu.concurrent.enmus;

/**
 * 行程类型 枚举类
 */
public enum TripTypeEnum {

    SINGLE(1, "单程"),
    ROUND(2, "往返"),
    MULTIPASS(3, "多程"),
    OPENJAW(4, "缺口"),
    CONN(5, "联程"),
    ;

    private Integer value;

    private String code;

    TripTypeEnum(Integer value, String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
