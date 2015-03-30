define(["jquery"], function($){

    function loadJumbotronContent(id,callback,error_back){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexJumbotronContent?random='+parseInt(Math.random()*100000),
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
        init:loadJumbotronContent
    }

});



