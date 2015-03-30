define(["jquery"], function($){

    function init(id,callback,error_back){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexProducts?random='+parseInt(Math.random()*100000),
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.result!=null){
                    callback(id,data.result);
                }
                else{
                    error_back();
                }
            }
        });
    }

    return {
        init:init
    }

});



