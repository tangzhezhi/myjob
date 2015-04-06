define(["jquery"], function($){

    function initHtml(id,data){

        var content = "<div id='"+id+"_myCarousel' class='carousel slide' data-ride='carousel'>" +
                        "<ol class='carousel-indicators'>" ;

        $.each(data,function(i,item){
            var c_a = "";
            if(i==0){
                c_a = "class='active'";
            }
            content = content +"<li data-target='#"+id+"_myCarousel' data-slide-to="+i+" "+c_a+"></li>";
        });

        content = content +"</ol>";

        content = content +" <div class='carousel-inner' role='listbox'>";

        $.each(data,function(i,item){
            var c_a = "";
            if(i==0){
                c_a = "active";
            }
            content = content +" <div class='item "+c_a+" '>" +
                                    " <img class='first-slide' src='"+item.picUrl+"' alt='First slide'>" +
                                    "<div class='container'>" +
                                        "<div class='carousel-caption'>" +
                                            "<h1>"+item.name+"</h1>" +
                                            "<p>"+item.describe+"</p>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>" ;
        });

        content = content +" </div> " +
            "<a class='left carousel-control' href='#"+id+"_myCarousel' role='button' data-slide='prev'>" +
                "<span class='glyphicon glyphicon-chevron-left' aria-hidden='true'></span>" +
            "   <span class='sr-only'>Previous</span>" +
            " </a> " +
            "<a class='right carousel-control' href='#"+id+"_myCarousel'  role='button' data-slide='next'> " +
                " <span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span> <span class='sr-only'>Next</span>" +
            "</a></div>";

        $("#"+id).empty().html(content);
    }

    return {
        init:initHtml
    }


});