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

            //var sock = common.getSockJs("/sockjs/websck");
        var socket = null;
        var stompClient = null;

        function connect() {
            var sock = new SockJS('myHandler');
            sock.onopen = function() {
                console.log('open');

                sock.send("haha");
            };
            sock.onmessage = function(e) {
                console.log('message', e.data);
            };
            sock.onclose = function() {
                console.log('close');
            };
        }

        function connectSock() {
            var sock = new SockJS('sock');



            stompClient = Stomp.over(sock);

            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);


                stompClient.subscribe('/topic/greetings' , function (data) {
                    console.log("data::::::"+data)
                });

                var quote = {symbol: 'APPL', value: 195.46};
                stompClient.send("/greeting",{},JSON.stringify(quote));
            });

            //stompClient.connect('random','random', function (frame) {
            //    console.log('Connected: ' + frame);
            //    stompClient.subscribe('/topic/greetings' , function (data) {
            //        console.log("data::::::"+data)
            //    });
            //    //stompClient.send("/app/greetings","haha");
            //});

        }


        function connect3() {
            var socket = new SockJS('hello');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function(greeting){
                    console.log("data::::::"+greeting)
                });

                stompClient.send("/app/hello", {}, JSON.stringify({ 'name': "haha" }));

            });
        }


        //connect();

        //connectSock();

        connect3();

    });
