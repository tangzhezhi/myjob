/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: 'resources/js/lib',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        app: '../app',
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.bootstrap.message':'jquery.bootstrap.message',
        top_news: '../app/top_news'
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        'jquery.bootstrap.message':['jquery'],
        'jquery.md5':['jquery']

    }
    //,waitSeconds: 150
});

// Start the main app logic.
requirejs(['jquery', 'bootstrap','jquery.bootstrap.message','common', 'app/top_news', 'app/portal_product', 'app/login'],
    function   ($,b,c,d,top_news,portal_product,login) {

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
                    detailHtml = detailHtml + "<div class='col-md-12'>" +
                    "<h2>"+item.name+"</h2>" +
                    "<p>"+item.describe+"</p>" +
                    "<p><a class='btn btn-default' href='#' role='button'>查看详情 &raquo;</a></p> " +
                    "</div>";
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

        top_news.init("jumbotronContent",showTopNew, d.alert_message);

        portal_product.init("portal_product",showPortalProduct, d.alert_message);

        $("#loginBtn").click(function(){
            var user = {
                username:$("#user_name").val(),
                password:$("#password").val()
            }

            login.init(user);

        });

    });
