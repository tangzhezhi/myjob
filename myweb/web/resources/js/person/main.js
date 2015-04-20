/**
 * Created by Administrator on 2015/4/17.
 */
define([
    'common'
], function(common){

    function connect() {
        var stompClient = null;
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



    return {
        getRootPath:getRootPath,
        alert_message:alert_message
    }

});



