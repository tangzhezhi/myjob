/**
 * Created by Administrator on 2015/3/24.
 */
$(function(){
    // 设置jQuery Ajax全局的参数
    $.ajaxSetup({
        type: "POST",
        error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(500):
                    alert("服务器系统内部错误");
                    break;
                case(401):
                    alert("未登录");
                    break;
                case(403):
                    alert("无权限执行此操作");
                    break;
                case(408):
                    alert("请求超时");
                    break;
                case(404):
                    alert("没有找到资源");
                    break;
                default:
                    alert("未知错误");
            }
        },
        success: function(data){
            alert("操作成功");
        },
        complete: function(XMLHttpRequest, textStatus) {
            console.log(XMLHttpRequest);
            this; // 调用本次AJAX请求时传递的options参数
        }
    });
});