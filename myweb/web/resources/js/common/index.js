/**
 * Created by Administrator on 2015/4/17.
 *
 */
define([
    'jquery',
    'bootstrap',
    'jquery.toastmessage',
    'sockjs',
    'Stomp'
    ], function($, bootstrap,toastmessage,sockjs,Stomp){

    /**
     * 获得项目路径
     * @returns {*}
     */
    function getRootPath(){
        var strFullPath=window.document.location.href;
        var strPath=window.document.location.pathname;
        var pos=strFullPath.indexOf(strPath);
        var prePath=strFullPath.substring(0,pos);
        var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
        return(prePath+postPath);
    }



    function alert_message(title,msg,callback){
        $().toastmessage('showToast',
            {
                text     : msg,
                sticky   : false,
                position : 'top-right',
                type     : 'success',
                close    : function () {
                    callback
                    console.log("toast is closed ...");
                }
            },
            'settings',{
                stayTime: 	3000
            }
        );
    }

    // 设置jQuery Ajax全局的参数
    $.ajaxSetup({
        type: "POST",
        error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(500):
                    alert_message("错误","服务器系统内部错误");
                    break;
                case(401):
                    alert_message("错误","未登录");
                    break;
                case(403):
                    alert_message("错误","无权限执行此操作");
                    break;
                case(408):
                    alert_message("错误","请求超时");
                    break;
                case(404):
                    alert_message("错误","没有找到资源");
                    break;
                default:
                    alert_message("错误","未知错误");
            }
        },
        success: function(data){
            alert_message("提示","操作成功");
        },
        complete: function(XMLHttpRequest, textStatus) {
            //console.log(XMLHttpRequest);
            this; // 调用本次AJAX请求时传递的options参数
        }
    });

    function getWebSocketMsg(endpoint,subscribeAddr,callback) {
        if(endpoint==null || endpoint == "undefined"){
            endpoint = "socket_msg"
        }
        var stompClient = null;
        var socket = new SockJS(endpoint);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe(subscribeAddr, function(data){
                   return callback(data);
            });
        });
    }


    return {
        getRootPath:getRootPath,
        getWebSocketMsg:getWebSocketMsg,
        alert_message:alert_message
    }

});



