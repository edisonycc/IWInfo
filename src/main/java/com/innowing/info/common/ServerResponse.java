package com.innowing.info.common;


/**
 * @ Author     ：Edison
 * @ Date       ：Created in 22:47 2020/4/30
 * @ Description：Common response
 * @ Modified By：
 * @Version: 1.0
 */
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data

public class ServerResponse {
    Integer code;       // response code
    String message;			// response message
    private Map<String, Object> data = new HashMap<String, Object>();		// response date

    public static  ServerResponse getInstance() {
        return new ServerResponse();
    }

    public  ServerResponse code(Integer code){
        this.code = code;
        return (ServerResponse) this;
    }

    public  ServerResponse message(String message){
        this.message = message;
        return (ServerResponse) this;
    }

    public  ServerResponse data(String key, Object value){
        this.data.put(key, value);
        return (ServerResponse) this;
    }

    public  ServerResponse responseEnum(ResponseEnum responseEnum){
        this.code = responseEnum.code;
        this.message = responseEnum.message;
        return (ServerResponse) this;
    }

    @Override
    public String toString() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", this.code);
        resultJson.put("message", this.message);
        resultJson.put("data", this.data);
        return resultJson.toString();
    }

//    //测试
//    public static void main(String[] args) {
//        System.out.println(ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS));
//    }
}
