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
requirejs(['jquery', 'bootstrap','jquery.bootstrap.message','common', 'app/top_news'],
    function   ($,b,c,d,top_news) {

        function showTopNew(id,data){
            var detailHtml = "";
            detailHtml = "<h1>"+data.result.title+"" +
            "<small>" +
            "<div class='row'>" +
            "<br><div class='col-xs-12 col-sm-offset-2'>"+data.result.content+"</div>" +
            "</div>" +
            "</small>" +
            "</h1>";
            $("#"+id).html(detailHtml);
        }

        top_news.init("jumbotronContent",showTopNew, d.alert_message("没有数据"));

    });
