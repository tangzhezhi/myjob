define(["jquery"], function($){

    function queryProduct(id,callback,error_back){
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

    function queryTopProduct(id,callback,error_back){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexTopProduct?random='+parseInt(Math.random()*100000),
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
        queryTopProduct:queryTopProduct,
        queryProduct:queryProduct
    }

});



