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
        '../person/main'
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
        var userid = common.getUserId();
        if(userid!=null){
            /**
             * 监听消息
             */
            main.getPersonRealTimeMsg_RepeatLogin(userid);
            main.getPersonRealTimeMsg_FileUpload(userid);

            /**
             * 实例化表格
             * @type {*|jQuery}
             */
            var mytable = main.getPersonPicture(userid,"mytable");

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
             * 上传插件初始化
             */
            $("#uploadFile").fileinput({
                showPreview : false,
                //allowedFileExtensions : ["txt", "zip", "bar", "bpmn", "bpmn20.xml" ], //限制文件类型
                elErrorContainer : "#fileError",
                browseClass : "btn btn-success",
                browseLabel : "上传文件",
                browseIcon : '<i class="glyphicon glyphicon-search"></i>',
                removeClass : "btn btn-danger",
                removeLabel : "删除",
                removeIcon : '<i class="glyphicon glyphicon-trash"></i>',
                uploadClass : "btn btn-info",
                uploadLabel : "上传",
                uploadIcon : '<i class="glyphicon glyphicon-upload"></i>'
            });

            /**
             * 上传事件处理
             */
            $(".myFormFile").submit(function(event) {
                var formData = new FormData(this); //这里用的是this，如果是Form的话需要Form[0]

                event.preventDefault(); //阻止当前提交事件，自行实现，否则会跳转
                $.ajax({
                    url : 'person/uploadFile?random='+parseInt(Math.random()*100000),
                    type : 'POST',
                    data : formData,
                    contentType : false, //这两个参数需要被定义，否则报错
                    processData : false,
                    success : function(data) {
                    if (data.result == 'success') {
                        $("#fileUrl").val(common.getRootPath()+data.fileUrl);
                        common.alert_message("消息","上传成功");
                    }
                },
                error : function() {
                    common.alert_message("消息","上传文件错误");
                }
            });
        });

            /**
             * 表单验证
             */
            main.validateForm("myFormInput",main.postForm);
        }


        //$("#test").click(function(){
        //    $.ajax({
        //        type: "POST",
        //        url: 'person/test?random='+parseInt(Math.random()*100000),
        //        dataType: 'json',
        //        success: function (data) {
        //            alert(data);
        //        }
        //    });
        //});


        $(".form_save_btn").click(function(e){
            e.preventDefault();
            main.postForm("myFormInput");
        });

    });
