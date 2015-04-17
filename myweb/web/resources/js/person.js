/**
 * Created by Administrator on 2015/4/17.
 */
/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    //urlArgs: "bust=" + (new Date()).getTime(),
    paths: {
        app: '../person'
    }
    ,waitSeconds: 500
});

// Start the main app logic.
requirejs([
        './person/main'
    ],
    function   (
        main
    ) {

    });
