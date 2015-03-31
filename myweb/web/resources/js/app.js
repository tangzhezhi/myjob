/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    paths: {
        app: '../app',
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.bootstrap.message':'jquery.bootstrap.message',
        'formvalidation.bootstrap':'formvalidation.bootstrap',
        'jquery.md5':'jquery.md5',
        'jquery.form':'jquery.form',
        'formValidation':'formValidation'
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        'formvalidation.bootstrap':['jquery'],
        'jquery.bootstrap.message':['jquery'],
        'jquery.md5':['jquery']
    }
    ,waitSeconds: 500
});

// Start the main app logic.
requirejs([
        'jquery',
        'bootstrap',
        'jquery.bootstrap.message',
        'jquery.md5',
        'jquery.form',
        'formvalidation.bootstrap',
        'formValidation',
        'common',
        'app/top_news',
        'app/portal_product',
        'app/login'
        ],
    function   ($,bootstrap,message,md5,form,formvalidation_bootstrap,formValidation,common,top_news,portal_product,login) {

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
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情 &raquo;</a></p> " +
                        "</div>";
                    });
                }
                else if(data.length == 2){
                    $.each(data,function(i,item){
                        detailHtml = detailHtml + "<div class='col-md-6'>" +
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情 &raquo;</a></p> " +
                        "</div>";
                    });
                }
                else{
                    $.each(data,function(i,item){
                        detailHtml = detailHtml + "<div class='col-md-4'>" +
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情 &raquo;</a></p> " +
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
                userName: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: 'The username is required and can\'t be empty'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: 'The username must be more than 6 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
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

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });;

        //$("#loginBtn").click(function(){
        //    var user = {
        //        userName:$("#user_name").val(),
        //        userPwd: $.md5($("#password").val())
        //    }
        //    login.init(user);
        //
        //});

    });
