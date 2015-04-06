/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    paths: {
        jquery: 'jquery',
        bootstrap: 'bootstrap'
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        }
    }
    ,waitSeconds: 1000
});

// Start the main app logic.
requirejs([
        'jquery',
        'bootstrap'
    ],
    function   ($,bootstrap) {
        console.log("哈哈哈");
    });
