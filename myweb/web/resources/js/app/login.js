define(["jquery"], function($){

    function login(user,callback,error){
        var username = user.userName;
        var password = $.md5(user.password);

        $.ajax({
            type: "POST",
            url: 'index/login?random='+parseInt(Math.random()*100000),
            data:{
                    username:username,
                    password:password
            },
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    callback(id,data);
                }
                else{
                    error(data.msg);
                }
            }
        });
    }

    return {
        init:login
    }

});



