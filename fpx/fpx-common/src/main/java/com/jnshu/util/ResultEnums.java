package com.jnshu.util;

/**
 * @author L
 */
enum ResultEnums {

    SUCCESS("110200", "请求成功"),
    ERROR("110500", "操作失败"),
    USER_ERROR("110500", "用户名错误请重新输入"),
    PASSWORD_ERROR("110500", "密码错误请重新输入"),
    BINGDING_ERROR("110501", "还有绑定的用户"),
    COURSE_BOUND("110502","此课程已绑定"),
    COURSE_ERROR("110505", "此课程id不存在"),
    SERVER_CONFLICT_ERROR("110409", "服务器在完成请求时发生冲突");

    private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
