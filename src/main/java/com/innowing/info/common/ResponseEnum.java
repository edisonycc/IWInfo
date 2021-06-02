package com.innowing.info.common;

public enum ResponseEnum {
    SUCCESS(20000, "Success"),
    FAILED(40000, "Failed"),
    INNER_ERROR(2001, "Internal Error"),
    LOGIN_SUCCESS(20000, "Login in Successfully"),
    LOGOUT_SUCCESS(20000, "Logout Successfully"),
    LOGIN_FAILED(3000, "Login failed，incorrect account or password"),
    ACCOUNT_NOT_FOUND(2002, "Account does not exist，please register"),
    ACCOUNT_NOT_LOGIN(50008, "账号未登录"),
    INVALID_PARAM(40001, "参数非法"),
    USERNAME_EXIST(3002, "Username already exists"),
    REGISTER_SUCCESS(20000, "Register Successfully"),
    UPDATE_SUCCESS(20000, "修改成功"),
    UPDATE_FAILED(20001, "修改失败，原密码错误"),
    GET_SUCCESS(20000, "数据获取成功"),
    ADD_SUCCESS(20000, "添加成功"),
    CLAZZ_EXSIT(3002, "班级已存在"),
    DELETE_SUCCESS(20000, "删除成功"),
    DELETE_FAILED(30000, "Delete failed."),
    ;
    Integer code;		//响应码
    String message;		//响应信息

    ResponseEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
