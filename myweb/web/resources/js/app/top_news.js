define(["jquery"], function($){

    function loadIndexTopNews(id,callback,error_back){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexTopNews?random='+parseInt(Math.random()*100000),
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.result!=null){
                    callback(id,data);
                }
                else{
                    error_back();
                }
            }
        });
    }

    return {
        init:loadIndexTopNews
    }

});



