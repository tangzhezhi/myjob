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
        '../person/main'
    ],
    function   (
        common,
        datetimepicker,
        formValidation,
        bootstrapValidator,
        fileinput,
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


            //$("#uploadFile").fileinput({
            //    uploadUrl: 'person/uploadFile', // you must set a valid URL here else you will get an error
            //    allowedFileExtensions : ['jpg', 'png','gif'],
            //    overwriteInitial: false,
            //    maxFileSize: 1000,
            //    maxFilesNum: 10,
            //    //allowedFileTypes: ['image', 'video', 'flash'],
            //    slugCallback: function(filename) {
            //        alert("haha");
            //        return filename.replace('(', '_').replace(']', '_');
            //    }
            //});


            $("#pdFile").fileinput({
                showPreview : false,
                //allowedFileExtensions : ["txt", "zip", "bar", "bpmn", "bpmn20.xml" ], //限制文件类型
                elErrorContainer : "#fileError",
                browseClass : "btn btn-success",
                browseLabel : "查找文件",
                browseIcon : '<i class="glyphicon glyphicon-search"></i>',
                removeClass : "btn btn-danger",
                removeLabel : "删除",
                removeIcon : '<i class="glyphicon glyphicon-trash"></i>',
                uploadClass : "btn btn-info",
                uploadLabel : "部署",
                uploadIcon : '<i class="glyphicon glyphicon-upload"></i>',
        });

            $("#uploadForm").submit(function(event) {
                var formData = new FormData(this); //这里用的是this，如果是Form的话需要Form[0]

                event.preventDefault(); //阻止当前提交事件，自行实现，否则会跳转
                $.ajax({
                    url : 'person/uploadFile',
                    type : 'POST',
                    data : formData,
                    contentType : false, //这两个参数需要被定义，否则报错
                    processData : false,
                    success : function(data) {
                        alert("dd");
                    if (data.result == 'success') {
                        $("#uploadFileDiv").slideToggle("slow");
                    }
                },
                error : function() {
                    alert("haha");
                }
            });
        });

            main.validateForm("myFormInput",main.postForm);
        }

    });
