/**
 * Created by Administrator on 2015/4/17.
 */
define([
    'common'
], function(common){

    function getPersonRealTimeMsg_RepeatLogin(userid) {
        common.getWebSocketMsg('/topic/repeatLogin/'+userid,function(data){
            console.log("data::::"+data);
            common.alert_message("请注意","此用户帐号在其它地方登陆，您被挤下线",function(){
                setTimeout(function(){
                    location.href = "welcome.html";
                },5000);
            }());
        });
    }


    function getPersonPicture(userid,callback){
        $.ajax({
            type: "POST",
            url: 'getPersonPicture?random='+parseInt(Math.random()*100000),
            data:{
                userid:$.base64('encode', JSON.stringify(userid))
            },
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    callback(data);
                }
                else{
                    try {
                        common.alert_message("error","不好意思，遇到错误");
                    } catch (e) {
                        console.log(e);
                    }
                }
            }
        });
    }

    return {
        getPersonRealTimeMsg_RepeatLogin:getPersonRealTimeMsg_RepeatLogin,
        getPersonPicture:getPersonPicture
    }

});



