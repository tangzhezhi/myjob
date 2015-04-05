/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    //urlArgs: "bust=" + (new Date()).getTime(),
    paths: {
        app: '../app',
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.bootstrap.message':'jquery.bootstrap.message',
        'jquery.md5':'jquery.md5',
        'jquery.form':'jquery.form',
        'common':'common',
        'FormValidation.Framework.Bootstrap':'formvalidation.bootstrap',
        'FormValidation':'formValidation'

    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        'FormValidation.Framework.Bootstrap':['jquery'],
        'FormValidation':['jquery'],
        'jquery.bootstrap.message':['jquery'],
        'jquery.md5':['jquery']
    }
    ,waitSeconds: 1000
});

// Start the main app logic.
requirejs([
        //"domReady!",
        'jquery',
        'bootstrap',
        'jquery.bootstrap.message',
        'jquery.md5',
        'jquery.form',
        'formValidation',
        'formvalidation.bootstrap',
        'common',
        './app/top_news',
        './app/portal_product',
        './app/login'
        ],
    function   ($,bootstrap,message,md5,form,formValidation,formvalidation_bootstrap,common,top_news,portal_product,login) {

        function showTopNew(id,data){
            var detailHtml = "";
            detailHtml = "<h1>"+data.result.title+"" +
            "<small>" +
            "<div class='row'>" +
            "<br><div class='col-xs-12 col-sm-offset-2'>"+data.result.content+"</div>" +
            "</div>" +
            "</small>" +
            "</h1>";
            $("#"+id).empty().html(detailHtml);
        }

        function showPortalProduct(id,data){
            var detailHtml = "";
            if(data!=null){
                if(data.length == 1){
                    $.each(data,function(i,item){
                        detailHtml = detailHtml + "<div class='col-md-12'>" +
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情ad &raquo;</a></p> " +
                        "</div>";
                    });
                }
                else if(data.length == 2){
                    $.each(data,function(i,item){
                        detailHtml = detailHtml + "<div class='col-md-6'>" +
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情cdccc &raquo;</a></p> " +
                        "</div>";
                    });
                }
                else{
                    $.each(data,function(i,item){
                        detailHtml = detailHtml + "<div class='col-md-4'>" +
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情cd &raquo;</a></p> " +
                        "</div>";
                    });
                }
            }

            $("#"+id).empty().html(detailHtml);
        }

        top_news.init("jumbotronContent",showTopNew, common.alert_message);

        portal_product.init("portal_product",showPortalProduct, common.alert_message);

        $("form").formValidation({
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

            login.init(user,loginCallback,common.alert_message);
        });


        function loginCallback(user){
            $("#myForm").addClass("hidden").after("<div class='navbar-right'><span class='navbar-brand'></p>欢迎"+user.userName+"</span></p></div>");
        }

    });
