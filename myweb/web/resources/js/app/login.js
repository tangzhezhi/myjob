define(["jquery","common", 'jquery.cookie'], function($, common,cookie){

    function login(user,id,callback,error){
        $.ajax({
            type: "POST",
            url: 'login?random='+parseInt(Math.random()*100000),
            data:{
                    first:$.base64('encode', JSON.stringify(user))
            },
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    callback(data.user,id);
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

            login(user,id,loginCallback,common.alert_message);
        });
    }

    function loginCallback(user,id){
        $.cookie('userName', user.userName, { expires: 7 });
        location.href = "index";
    }

    return {
        init:validateLogin
    }

});



