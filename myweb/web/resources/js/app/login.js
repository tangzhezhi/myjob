define(["jquery"], function($){

    function login(user,callback,error){
        var username = user.userName;
        var password = user.userPwd;

        $.ajax({
            type: "POST",
            url: 'index/login?random='+parseInt(Math.random()*100000),
            data:{
                    userName:username,
                    userPwd:password
            },
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    callback(data.user);
                }
                else{
                    error("用户验证不通过")
                }
            }
        });
    }

    return {
        init:login
    }

});



