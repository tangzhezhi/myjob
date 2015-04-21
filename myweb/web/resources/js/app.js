/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    //urlArgs: "bust=" + (new Date()).getTime(),
    paths: {
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.toastmessage':'jquery.toastmessage',
        'jquery.md5':'jquery.md5',
        'jquery.form':'jquery.form',
        'common':'common',
        'bootstrapValidator':'formvalidation.bootstrap',
        'formValidation':'formValidation',
        'lightbox':'lightbox',
        'jquery.cookie':'jquery.cookie',
        'jquery.base64':'jquery.base64',
        app: '../app'
    },
    shim : {
        bootstrap : {
            deps : [
                'jquery',
                'css!../../../resources/css/bootstrap.css',
                'css!../../../resources/css/app/app-carousel.css'
            ]
        },
        'formValidation': {
            deps:[
                'jquery'
                ,'css!../../../resources/css/formValidation.css'
            ]
        },
        'bootstrapValidator':{
            deps:['jquery', 'formValidation']
        },
        'jquery.toastmessage':{
            deps:[
                'jquery',
                'css!../../../resources/css/jquery.toastmessage.css'
            ]
        },
        'jquery.md5':['jquery'],
        'jquery.base64':['jquery'],
        'lightbox':{
            deps : [
                'jquery',
                'css!../../../resources/css/lightbox.css',
                'css!../../../resources/css/screen.css'
            ]
        }
    }
    ,waitSeconds: 500,
    map: {
        '*': {
            'css': 'css'
        }
    }
});

// Start the main app logic.
requirejs([
        'jquery',
        'bootstrap',
        'jquery.toastmessage',
        'jquery.md5',
        'jquery.form',
        'formValidation',
        'bootstrapValidator',
        'lightbox',
        'jquery.cookie',
        'jquery.base64',
        'common',
        './app/top_news',
        './app/portal_product',
        './app/login',
        './app/top_carousel'
    ],
    function   (
                    $,
                    bootstrap,
                    message,
                    md5,
                    form,
                    formValidation,
                    formvalidationbootstrap,
                    lightbox,
                    cookie,
                    base64,
                    common,
                    top_news,
                    portal_product,
                    login,
                    top_carousel
                ) {

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

                $.each(data,function(i,item){
                    detailHtml = detailHtml +
                    "<div class='col-lg-4'>" +
                        "<div class='image-row'>"+
                        "<a class='example-image-link' href='"+item.picUrl+"' data-lightbox='example-1'><img class='example-image' src='"+item.picUrl+"' alt='image-1' /></a>"+
                        "<h2>"+item.name+"</h2>" +
                        "<p>"+item.describe+"</p>" +
                        "<p><a class='btn btn-default' href='#' role='button'>查看详情 &raquo;</a></p> " +
                    "</div>" +
                    "</div>";
                });

            }

            $("#"+id).empty().html(detailHtml);
        }

        //top_news.init("jumbotronContent",showTopNew, common.alert_message);

        portal_product.queryProduct("portal_product",showPortalProduct, common.alert_message);

        portal_product.queryTopProduct("top_pic",top_carousel.init, common.alert_message);

        login.init("myForm");

        if( $.cookie('userName')){
            $("#userName").val($.cookie('userName'));
        }
    });
