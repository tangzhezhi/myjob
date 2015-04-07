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
        'FormValidation.Framework.Bootstrap':'formvalidation.bootstrap',
        'FormValidation':'formValidation',
        'lightbox':'lightbox',
        'jquery.cookie':'jquery.cookie',
        app: '../app'
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        'FormValidation.Framework.Bootstrap':['jquery'],
        'FormValidation':['jquery'],
        'jquery.toastmessage':['jquery'],
        'jquery.md5':['jquery'],
        'lightbox':['jquery']
    }
    ,waitSeconds: 1000
});

// Start the main app logic.
requirejs([
        'jquery',
        'bootstrap',
        'jquery.toastmessage',
        'jquery.md5',
        'jquery.form',
        'formValidation',
        'formvalidation.bootstrap',
        'lightbox',
        'jquery.cookie',
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
            var user = {
                userName:$.cookie('userName')
            }
            $("#myForm").addClass("hidden").after("<div class='navbar-right'><span class='navbar-brand'></p>欢迎"+user.userName+"</span></p></div>");
        }

    });
