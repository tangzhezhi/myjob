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

    return {
        getPersonRealTimeMsg_RepeatLogin:getPersonRealTimeMsg_RepeatLogin
    }

});



