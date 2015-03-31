package org.tang.myjob.service.exception;

/**
 * Created by Administrator on 2015/3/23.
 */
public interface ExceptionType {
    public int message_code = 101; //消息异常
    public String message_msg = "消息异常"; //消息异常

    public int product_code = 102; //产品异常
    public String product_msg = "产品异常"; //产品异常

    public int login_code = 103;
    public String login_msg = "登录异常";

}
