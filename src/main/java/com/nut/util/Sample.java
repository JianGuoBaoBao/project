package com.nut.util;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class Sample {
    public static final String APP_ID = "25191781";
    public static final String API_KEY = "hS6EVsksprk45ojBDb181Xam";
    public static final String SECRET_KEY = "adhWWzRsaF0yhFgPXMvkS18LstGQtwuP";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置

//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "/log4j.properties");

        // 调用接口
        String idCardSide = "back";
        String path = "/Users/wanglingjie/IdeaProjects/iris_project/tempImg/3C8C5B451BB4445697730217EC8648E3.jpeg";

        // 通用文字识别
//        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());

        // 身份证识别
        JSONObject res = client.idcard(path, idCardSide, options);

        System.out.println(res.toString(2));

    }
}
