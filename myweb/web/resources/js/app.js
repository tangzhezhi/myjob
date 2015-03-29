/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    paths: {
        app: '../app',
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        jquery_bootstrap_message:'jquery.bootstrap.message',
        top_news: '../app/top_news'
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        jquery_bootstrap_message:{
            deps : [ 'jquery','bootstrap' ],
            exports:'jquery.bootstrap.message'
        }
    },
    waitSeconds: 150
});


requirejs(['jquery', 'bootstrap','jquery.bootstrap.message','common','app/top_news'],
    function   ($,b,c,d,top_news) {
        top_news.init("jumbotronContent");
    });