package com.building.enums;

/**
 * @author yinjiahui
 * @create 2021-04-05 11:14
 */
public enum REnum {


    SUCCESS_QUERY(0,"查询成功"),
    SUCCESS_INSERT(0,"新增成功"),
    SUCCESS_DELETE(0,"删除成功"),
    SUCCESS_UPDATE(0,"修改成功"),
    SUCCESS_MSG(0,"请求成功"),
    SUCCESS_DATA(0,"沒有更多数据了"),
    SUCCESS_REPLY(0,"回复成功"),

    SUCCESS_REGISTER(0,"注册成功"),
    SUCCESS_LOGIN(0,"登录成功"),


    ERROR(1,"请求失败"),
    ERROR_LOGIN(1,"用户名或密码不正确，请重新登录。"),
    ERROR_SYS(-1,"系统繁忙，请稍后再试...."),

            ;

    //返回的数值
    private Integer code;

    //返回的信息
    private String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 类似构造函数的东西
     * @param code
     * @param msg
     */
    REnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
