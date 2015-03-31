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
                    error_back("没有数据");
                }
            }
        });
    }

    return {
        init:init
    }

});



