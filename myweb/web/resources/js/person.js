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
        'sockjs':'sockjs-0.3.4',
        'stomp':'stomp',
        //'common':'../common',
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
        'jquery.toastmessage':{
            deps:[
                'jquery',
                'css!../../../resources/css/jquery.toastmessage.css'
            ]
        }
    }
    ,waitSeconds: 500
});

// Start the main app console.logic.
requirejs([
        'jquery',
        'bootstrap',
        'jquery.toastmessage',
        'sockjs',
        'stomp'
        //'./common/index'
        //'../person/main'
    ],
    function   (
        $,bootstrap,toastmessage,sock,stomp
        //,common
    ) {

        var socket = null;
        var stompClient = null;

        function connect() {
            var socket = new SockJS('socket_msg');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);

                stompClient.subscribe('/topic/repeatLogin'+"/tangzhezi@126.com", function(greeting){
                    console.log("repeatLogin:data::::::"+greeting)
                });

                stompClient.subscribe('/topic/greetings', function(greeting){
                    console.log("data::::::"+greeting)
                });

            });
        }

        connect();

    });
