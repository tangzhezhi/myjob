/**
 * Created by Administrator on 2015/3/24.
 */

define(["jquery"], function($){

    function alert_message(msg,callback){
        $().message({
            type: 'danger',
            html: "<div class='row'><p class='text-center'>"+msg+"</p></div>",
            icon: true,
            position: 'top',
            autoHide: 4000,
            beforeFunc: function () {
                // code to execute before plugin execution
            },
            afterFunc: function () {
                callback
            },
            onCloseFunc: function () {
                // code to execute on close event of message box
            }
        })();
    }

    // 设置jQuery Ajax全局的参数
    $.ajaxSetup({
        type: "POST",
        error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(500):
                    alert_message("服务器系统内部错误");
                    break;
                case(401):
                    alert_message("未登录");
                    break;
                case(403):
                    alert_message("无权限执行此操作");
                    break;
                case(408):
                    alert_message("请求超时");
                    break;
                case(404):
                    alert_message("没有找到资源");
                    break;
                default:
                    alert_message("未知错误");
            }
        },
        success: function(data){
            alert_message("操作成功");
        },
        complete: function(XMLHttpRequest, textStatus) {
            console.log(XMLHttpRequest);
            this; // 调用本次AJAX请求时传递的options参数
        }
    });

    return {
        alert_message:alert_message
    }
});
