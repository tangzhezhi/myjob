define(["jquery"], function($){

    function login(user){
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

                }
                else{

                }
            }
        });
    }

    return {
        init:login
    }

});



