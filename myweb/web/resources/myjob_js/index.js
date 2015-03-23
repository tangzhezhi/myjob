/**
 * Created by Administrator on 2015/3/23.
 */

function loadJumbotronContent(){
    $.ajax({
        url: 'loadIndexJumbotronContent?random='+parseInt(Math.random()*100000),
        dataType: 'json',
        success: function (data) {
            if(data){

            }
            else{

            }
        }
    });
}