/**
 * Created by Administrator on 2015/4/17.
 */
define([
    'common',
    'datatables',
    'dataTables.responsive',
    'text!../lib/jquery.dataTables.lang.zh.txt'
], function(common,dataTables,responsive,language){

    function getPersonRealTimeMsg_RepeatLogin(userid) {
        common.getWebSocketMsg('/topic/repeatLogin/'+userid,function(data){
            console.log("data::::"+data);
            common.alert_message("请注意","此用户帐号在其它地方登陆，您被挤下线",function(){
                setTimeout(function(){
                    location.href = "welcome.html";
                },5000);
            }());
        });
    }

    /**
     * 初始化表格，然后返回DataTable对象
     * @param userid
     * @param id
     * @returns {*|jQuery}
     */
    function getPersonPicture(userid,id){

        //自定义布局
//*
//        l - Length changing * f - Filtering input*
//        t - The table!* i - Information*
//        p - Pagination* r - pRocessing*
//        < and > - div elements*
//        <"class" and >
//        - div with a class
//        * Examples: <"wrapper"flipt>, <lf<t>ip>

        var mytable = $('#'+id).DataTable( {
            "sDom":'<"top"f<"clear">> rt<"bottom"ipl<"wrapper clear">> ',
            "ordering": false,
            responsive: true,
            "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
            "bServerSide" : true, //是否启动服务器端数据导入
            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
            "aLengthMenu" : [10, 50, 100], //更改显示记录数选项
            "iDisplayLength" : 10, //默认显示的记录数
            "bAutoWidth" : true, //是否自适应宽度
            "bScrollInfinite" : false, //是否启动初始化滚动条
            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
            "bPaginate" : true, //是否显示（应用）分页器
            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数
            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页
            "bSort" : false, //是否启动各个字段的排序功能
            "aaSorting" : [[1, "asc"]], //默认的排序方式，第2列，升序排列
            "bFilter" : false, //是否启动过滤、搜索功能
            "aoColumns": [
                {
                    "mDataProp" : function(source, type, val){
                        return '&nbsp;';
                    },
                    "sClass" : "left"
                },
                {
                    "mDataProp" : "userId",
                    "sTitle" : "用户ID",
                    "sClass" : "hide"
                },
                {
                    "mDataProp" : "state",
                    "sTitle" : "state",
                    "sClass" : "hide"
                },
                {
                    "mDataProp" : "remark",
                    "sTitle" : "remark",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" :"createTime",
                    "sTitle" : "createTime",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "startTime",
                    "sTitle" : "startTime",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "endTime",
                    "sTitle" : "endTime",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "totalAmount",
                    "sTitle" : "totalAmount",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {"mDataProp": function(source, type, val){
                        return '<div class="btn-toolbar">'
                                    +'<button class="btn btn-warning btn-xs" type="button" data-toggle="tooltip" data-placement="top" title="更新">' +
                                        '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
                                    '</button>' +
                                    '<button type="button" class="btn btn-danger btn-xs" data-toggle="tooltip" data-placement="top" title="删除"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>'
                                +'</div>';
                    },
                    "sTitle" : "操作",
                    "sClass" : "center"
                }
            ],
            "oLanguage": $.parseJSON(language),
            "sAjaxSource" : 'person/getPersonPicture',
            "fnServerParams":function(aoData){
                if($(".my_query").val()!=null && $(".my_query").val().length>1){
                    aoData.push({ "name": "remark", "value": $(".my_query").val()});
                }
                aoData.push({ "name": "userId", "value": userid});
            },
            //服务器端，数据回调处理
            "fnServerData" : function(sSource, aDataSet, fnCallback) {
                $.ajax({
                    "dataType" : 'json',
                    "type" : "POST",
                    "url" : sSource,
                    "data" : aDataSet,
                    "success" : function(data){
                        fnCallback(data);
                    }
                });
            }

        }
        );
        return $('#mytable').dataTable();
    }

    function query(dataTable) {
        dataTable._fnClearTable();
        //dataTable._fnDraw(mytable.settings);
        dataTable._fnPageChange(dataTable.settings,"first", true);
    }



    function validateForm(id,callback){
        $("#"+id).formValidation({
            message: '您的输入值不符合要求',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                row: '.col-sm-4',
                userId: {
                    message: 'Email无效',
                    validators: {
                        notEmpty: {
                            message: 'Email不能为空'
                        },
                        stringLength: {
                            min: 4,
                            max: 30,
                            message: 'Email必须超过6个字符少于30字符'
                        },
                        emailAddress: {
                            message: '不是一个有效的邮件格式'
                        }
                    }
                }
            }
        }).on('success.form.fv', function(e) {
            e.preventDefault();
            callback(id);
        });
    }


    function postForm(id){
        var data = {} ;
        $("#"+id+" input" ).each(function(i,item){
            data[item.id] = $(item).val();
        });

        $.ajax({
            type: "POST",
            url: 'person/add?random='+parseInt(Math.random()*100000),
            data:data,
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    //query( $('#mytable').dataTable());
                }
            }
        });
    }


    return {
        query:query,
        validateForm:validateForm,
        postForm:postForm,
        getPersonRealTimeMsg_RepeatLogin:getPersonRealTimeMsg_RepeatLogin,
        getPersonPicture:getPersonPicture
    }

});



