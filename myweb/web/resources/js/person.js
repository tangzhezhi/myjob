/**
 * Created by Administrator on 2015/4/17.
 */
/**
 * Created by Administrator on 2015/3/27.
 */

requirejs.config({
    baseUrl: 'resources/js/lib',
    paths: {
        jquery: 'jquery',
        bootstrap: 'bootstrap',
        'jquery.toastmessage':'jquery.toastmessage',
        'jquery.cookie':'jquery.cookie',
        'sockjs':'sockjs-0.3.4',
        'stomp':'stomp',
        'datatables':'jquery.dataTables',
        'dataTables.responsive':'dataTables.responsive',
        'datetimepicker':'bootstrap-datetimepicker',
        'bootstrapValidator':'formvalidation.bootstrap',
        'formValidation':'formValidation',
        'common':'../common/index',
        app: '../person'
    },
    shim : {
        bootstrap : {
            deps : [
                'jquery',
                'css!../../../resources/css/bootstrap.css',
                'css!../../../resources/css/app/app-carousel.css'
            ]
        },
        'datatables' : {
            deps : [
                'jquery',
                'css!../../../resources/css/jquery.dataTables.css',
                'css!../../../resources/css/jquery.dataTables_themeroller.css'
            ]
        },
        'dataTables.responsive' : {
            deps : [
                'jquery',
                'datatables',
                'css!../../../resources/css/dataTables.responsive.css'
            ]
        },
        'jquery.toastmessage':{
            deps:[
                'jquery',
                'css!../../../resources/css/jquery.toastmessage.css'
            ]
        },
        'datetimepicker':{
            deps:[
                'jquery',
                //'datetimepicker.zh',
                'css!../../../resources/css/bootstrap-datetimepicker.css'
            ]
        },
        'formValidation': {
            deps:[
                'jquery'
                ,'css!../../../resources/css/formValidation.css'
            ]
        },
        'bootstrapValidator':{
            deps:['jquery', 'formValidation']
        }
    }
    ,waitSeconds: 500,
    map: {
        '*': {
            'css': 'css'
        }
    }
});

// Start the main app console.logic.
requirejs([
        'common',
        'datetimepicker',
        'formValidation',
        'bootstrapValidator',
        '../person/main'
    ],
    function   (
        common,
        datetimepicker,
        formValidation,
        bootstrapValidator,
        main
    ) {
        var userid = common.getUserId();
        if(userid!=null){
            main.getPersonRealTimeMsg_RepeatLogin(userid);
            var mytable = main.getPersonPicture(userid,"mytable");

            $(".my_query_btn").click(function(){
                main.query(mytable);
            } );

            $(".form_datetime").datetimepicker(
                {
                    language:'zh',
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format: 'yyyy-mm-dd'
                });

            $(".my_add_btn").bind("click",function(){
                if(!$(".myshow").hasClass("hide")){
                    $(".myshow").addClass("hide");
                }
                if($(".myForm").hasClass("hide")){
                    $(".myForm").removeClass("hide");
                }
            });


            $(".form_back_btn").click(function(){
                if($(".myshow").hasClass("hide")){
                    $(".myshow").removeClass("hide");
                }
                if(!$(".myForm").hasClass("hide")){
                    $(".myForm").addClass("hide");
                }
            });

            $(".form_reset_btn").click(function(){
                $(".myForm input").each(function(){
                    $(this).val("");
                });
            });

            main.validateForm("myForm",main.postForm);
        }

    });
