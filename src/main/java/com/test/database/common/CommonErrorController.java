package com.test.database.common;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CommonErrorController extends BasicErrorController {

    //统一处理filter抛出的token相关的异常 返回给前端标准格式的json和装填码
    private static final String PATH = "/error";
    private static final String TOKEN_MISS = "Missing or invalid Authorization header";
    private static final String TOKEN_EXPIRED = "token expired";
    private static final String TOKEN_INVALID = "token invalid";
    private static final String TOKEN_ERROR = "error";

    public CommonErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @Override
    @RequestMapping(produces = {"application/json"})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        //加入跨域相关内容
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "X-Total-Count");
        response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, x-http-method-override, content-type, Authentication, Authorization, hospital");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS, HEAD, PATCH");

        HttpStatus status = this.getStatus(request);
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, true);
        String message = (String)errorAttributes.get("message");
        Map<String, Object> body = new LinkedHashMap<>(16);
        body.put("code", getCode(message));
        body.put("message", message);
        body.put("data", null);
        return new ResponseEntity(body, status);
    }

    private int getCode(String msg){
        if (TOKEN_MISS.equals(msg)){
            return -1;
        }else if (TOKEN_EXPIRED.equals(msg)){
            return -2;
        }else if (TOKEN_INVALID.equals(msg)){
            return -3;
        }else if (TOKEN_ERROR.equals(msg)){
            return -4;
        }else {
            return -5;
        }
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
