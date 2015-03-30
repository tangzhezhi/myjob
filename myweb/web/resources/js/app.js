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
        'jquery.bootstrap.message':['jquery']

    }
    //,waitSeconds: 150
});

// Start the main app logic.
requirejs(['jquery', 'bootstrap','jquery.bootstrap.message','common', 'app/top_news', 'app/portal_product'],
    function   ($,b,c,d,top_news,portal_product) {

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
            detailHtml = "<div class='col-md-4'>" +
                            "<h2>"+data.name+"</h2>" +
                            "<p>"+data.desc+"</p>" +
                            "<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p> " +
                         "</div>";
            $("#"+id).empty().html(detailHtml);
        }

        top_news.init("jumbotronContent",showTopNew, d.alert_message("没有数据"));

        portal_product.init("portal_product",showPortalProduct, d.alert_message("没有数据"));

    });
