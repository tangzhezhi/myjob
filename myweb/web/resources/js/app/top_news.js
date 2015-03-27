define(["jquery"], function($){
    init();

    function init(){
        loadJumbotronContent();
    }

    function loadJumbotronContent(){
        $.ajax({
            type: "POST",
            url: 'index/loadIndexJumbotronContent?random='+parseInt(Math.random()*100000),
            dataType: 'json',
            success: function (data) {
                if(data){
                    var detailHtml = "";
                    detailHtml = "<h1>"+data.result.title+"" +
                    "<small>" +
                    "<div class='row'>" +
                    "<br><div class='col-xs-12 col-sm-offset-2'>"+data.result.content+"</div>" +
                    "</div>" +
                    "</small>" +
                    "</h1>";
                    $("#jumbotronContent").html(detailHtml);
                }
            }
        });
    }

});



