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
        'fileinput':'fileinput',
        'jquery.media':'jquery.media',
        'common':'../common/index',
        app: '../${className}'
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
                'css!../../../resources/css/bootstrap-datetimepicker.css'
            ]
        },

        'fileinput':{
            deps:[
                'jquery',
                'bootstrap',
                'css!../../../resources/css/fileinput.css'
            ]
        },
        'jquery.media':{
            deps:[
                'jquery'
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
        'fileinput',
        'jquery.media',
        '../${className}/main'
    ],
    function   (
        common,
        datetimepicker,
        formValidation,
        bootstrapValidator,
        fileinput,
        media,
        main
    ) {
        var oTimer = null;
        var userid = common.getUserId();
        if(userid!=null){
            /**
             * 监听消息
             */

            /**
             * 实例化表格
             * @type {*|jQuery}
             */
            var mytable = main.get${className}(userid,"mytable");

            $('.media').media( { width: 400, height: 300, autoplay: true } );

            /**
             * 查询监听
             */
            $(".my_query_btn").click(function(){
                main.query(mytable);
            } );

            /**
             * 日期控件实例化
             */
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

            /**
             * 新增按钮事件
             */
            $(".my_add_btn").bind("click",function(){
                if(!$(".myshow").hasClass("hide")){
                    $(".myshow").addClass("hide");
                }
                if($(".myForm").hasClass("hide")){
                    $(".myForm").removeClass("hide");
                }
            });

            /**
             * 返回按钮事件
             */
            $(".form_back_btn").click(function(){
                if($(".myshow").hasClass("hide")){
                    $(".myshow").removeClass("hide");
                }
                if(!$(".myForm").hasClass("hide")){
                    $(".myForm").addClass("hide");
                }
            });

            /**
             * 重置按钮事件
             */
            $(".form_reset_btn").click(function(){
                $(".myForm input").each(function(){
                    $(this).val("");
                });
            });

            /**
             * 表单验证
             */
            main.validateForm("myFormInput",main.postForm);
        }

        $(".form_save_btn").click(function(e){
            e.preventDefault();
            main.postForm("myFormInput");
        });

    });
