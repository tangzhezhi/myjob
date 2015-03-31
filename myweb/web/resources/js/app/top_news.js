define(["jquery"], function($){

    function loadIndexTopNews(id,callback,error){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexTopNews?random='+parseInt(Math.random()*100000),
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    callback(id,data);
                }
                else{
                    error(data.msg);
                }
            }
        });
    }

    return {
        init:loadIndexTopNews
    }

});



