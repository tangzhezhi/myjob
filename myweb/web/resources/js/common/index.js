/**
 * Created by Administrator on 2015/4/17.
 *
 */
define([
    'jquery',
    'bootstrap',
    'jquery.toastmessage',
    'jquery.cookie',
    'sockjs',
    'stomp'
    ], function($, bootstrap,toastmessage,cookie,sockjs,stomp){

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

    function getSessionId(){
        var c_name = 'JSESSIONID';
        if(document.cookie.length>0){
            c_start=document.cookie.indexOf(c_name + "=")
            if(c_start!=-1){
                c_start=c_start + c_name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if(c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end));
            }
        }
    }

    function openNewWindow(url){
        var a = $("<a href='"+url+"' target='_blank'></a>").get(0);
        var e = document.createEvent('MouseEvents');
        e.initEvent( 'click', true, true );
        a.dispatchEvent(e);
    }


    function getProgress(id) {
        var now = new Date();
        $.ajax({
            type: "post",
            dataType: "json",
            url: getRootPath()+"/upfile/progress",
            data: now.getTime(),
            success: function(data) {

                var nowvalue = Math.round((data.pBytesRead/data.pContentLength)*100);

                var widthvalue = "width:"+nowvalue+"%";

                if(id===null || id === undefined){
                    $(".progress-bar")[0].innerHTML = nowvalue;
                    $(".progress-bar").attr("style",widthvalue);
                    $(".progress-bar")[1].innerHTML = nowvalue;
                }
                else{
                    $("#"+id).innerHTML = nowvalue;
                    $("#"+id).attr("style",widthvalue);
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
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

    function getWebSocketMsg(subscribeAddr,callback,endpoint) {
        if(endpoint==null || endpoint == "undefined"){
            endpoint = "socket_msg"
        }
        var stompClient = null;
        var socket = new SockJS(endpoint);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe(subscribeAddr,callback);
        });
    }


    function getUserId(){
        if(null!=$.cookie('userName') && "undefined"!=$.cookie('userName')){
            return $.cookie('userName');
        }
       else {
            return null;
        }
    }

    return {
        getSessionId:getSessionId,
        openNewWindow:openNewWindow,
        getRootPath:getRootPath,
        getWebSocketMsg:getWebSocketMsg,
        alert_message:alert_message,
        getUserId:getUserId,
        getProgress:getProgress
    }

});



