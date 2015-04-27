/**
 * Created by Administrator on 2015/4/17.
 */
/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    paths: {
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.toastmessage':'jquery.toastmessage',
        'jquery.cookie':'jquery.cookie',
        'sockjs':'sockjs-0.3.4',
        'stomp':'stomp',
        'datatables':'jquery.dataTables',
        'dataTables.responsive':'dataTables.responsive',
        'common':'../common/index',
        app: '../person'
    },
    shim : {
        bootstrap : {
            deps : [
                'jquery',
                'css!../../../resources/css/bootstrap.css',
                'css!../../../resources/css/app/app-carousel.css'
            ]
        },
        'datatables' : {
            deps : [
                'jquery',
                'css!../../../resources/css/jquery.dataTables.css',
                'css!../../../resources/css/jquery.dataTables_themeroller.css'
            ]
        },
        'dataTables.responsive' : {
            deps : [
                'jquery',
                'datatables',
                'css!../../../resources/css/dataTables.responsive.css'
            ]
        },
        'jquery.toastmessage':{
            deps:[
                'jquery',
                'css!../../../resources/css/jquery.toastmessage.css'
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

// Start the main app console.logic.
requirejs([
        'common',
        '../person/main'
    ],
    function   (
        common,
        main
    ) {
        var userid = common.getUserId();
        if(userid!=null){
            main.getPersonRealTimeMsg_RepeatLogin(userid);
            var mytable = main.getPersonPicture(userid,"mytable");

            $(".my_query_btn").bind("click", mytable);
            //$(".my_add_btn").bind("click", alert("新增"));
        }

    });
