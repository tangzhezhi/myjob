define(["jquery","common"], function($, common){

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
                    try {
                        error("错误","用户验证不通过")
                    } catch (e) {
                        console.log(e);
                    }
                }
            }
        });
    }


    function validateLogin(id){
        $("#"+id).formValidation({
            message: 'This value is not valid',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                row: '.col-sm-4',
                userName: {
                    message: '用户名无效',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 50,
                            message: '用户名必须超过6个字符少于30字符'
                        },
                        emailAddress: {
                            message: '不是一个有效的邮件格式'
                        }
                    }
                }
            }
        }).on('success.form.fv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the FormValidation instance
            var bv = $form.data('formValidation');

            var user = {
                userName:$("#userName").val(),
                userPwd: $.md5($("#password").val())
            }

            login(user,loginCallback(user,id),common.alert_message);
        });
    }

    function loginCallback(user,id){
        $("#"+id).addClass("hidden").after("<div class='navbar-right'><span class='navbar-brand'></p>欢迎"+user.userName+"</span></p></div>");

        $.cookie('userName', user.userName, { expires: 7 });

    }


    return {
        init:validateLogin
    }

});



