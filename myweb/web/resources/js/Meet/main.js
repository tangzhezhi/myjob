/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

/**
 * Created by Administrator on 2015/4/17.
 */
define([
    'common',
    'datatables',
    'dataTables.responsive',
    'text!../lib/jquery.dataTables.lang.zh.txt'
], function(common,dataTables,responsive,language){

    /**
     * 初始化表格，然后返回DataTable对象
     * @param userid
     * @param id
     * @returns {*|jQuery}
     */
    function getMeet(userid,id){

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
                        "mDataProp" : "id",
                        "sTitle" : "会议ID",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {
                        "mDataProp" : "meetName",
                        "sTitle" : "会议名称",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {
                        "mDataProp" : "roomId",
                        "sTitle" : "房间ID",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {
                        "mDataProp" : "startTime",
                        "sTitle" : "开始时间",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {
                        "mDataProp" : "endTime",
                        "sTitle" : "结束时间",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {
                        "mDataProp" : "joinNumber",
                        "sTitle" : "与会人数",
                        "sDefaultContent" : "",
                        "sClass" : "center"
                    },
                    {"mDataProp": function(source, type, val){
                        return '<div class="btn-toolbar">'
                            +'<button class="btn btn-success btn-xs joinMeet" type="button" data-toggle="tooltip" data-placement="top" title="加入">' +
                            '<span class="glyphicon glyphicon-hourglass " aria-hidden="true"></span>'+
                            '</button>'
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
                "sAjaxSource" : 'meet/query',
                "fnServerParams":function(aoData){
                    if($(".my_query").val()!=null && $(".my_query").val().length>1){
                        aoData.push({ "name": "query", "value": $(".my_query").val()});
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
                },
                "fnDrawCallback":function(){

                    $(".preview").click(function(e){
                        var url = $(e.target).parent().attr("value");
                    });

                    $(".joinMeet").click(function(){

                        var a = $("<a href='http://localhost:3000/chat?userid=1' target='_blank'>会议</a>").get(0);
                        var e = document.createEvent('MouseEvents');
                        e.initEvent( 'click', true, true );
                        a.dispatchEvent(e);

                        //window.open("http://localhost:3000/chat?userid=1", "_blank");
                    });

                }

            }
        );
        return $('#mytable').dataTable();
    }

    function query(dataTable) {
        dataTable._fnClearTable();
        dataTable._fnDraw();
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
                    id :{
                        message: '会议ID无效',
                            validators: {
                            notEmpty: {
                                message: '会议ID不能为空'
                            }
                        }
                    },
                    meetName :{
                        message: '会议名称无效',
                            validators: {
                            notEmpty: {
                                message: '会议名称不能为空'
                            }
                        }
                    },
                    roomId :{
                        message: '房间ID无效',
                            validators: {
                            notEmpty: {
                                message: '房间ID不能为空'
                            }
                        }
                    },
                    startTime :{
                        message: '开始时间无效',
                            validators: {
                            notEmpty: {
                                message: '开始时间不能为空'
                            }
                        }
                    },
                    endTime :{
                        message: '结束时间无效',
                            validators: {
                            notEmpty: {
                                message: '结束时间不能为空'
                            }
                        }
                    },
                    joinNumber :{
                        message: '与会人数无效',
                            validators: {
                            notEmpty: {
                                message: '与会人数不能为空'
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
            url: 'meet/add?random='+parseInt(Math.random()*100000),
            data:data,
            dataType: 'json',
            success: function (data) {
                if(data!=null && data.msg === "success"){
                    $(".myshow").removeClass("hide");
                    $(".myForm").addClass("hide");
                    $('#mytable').dataTable()._fnClearTable();
                    $('#mytable').dataTable().fnDraw();
                }
            }
        });
    }

    return {
        getMeet:getMeet,
        query:query,
        validateForm:validateForm,
        postForm:postForm
    }

});



