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

// Start the main app logic.
requirejs(['jquery', 'bootstrap','jquery.bootstrap.message','common', 'app/top_news'],
    function   ($,b,c,d,e) {
        //jQuery, canvas and the app/sub module are all
        //loaded and can be used here now.
    });
