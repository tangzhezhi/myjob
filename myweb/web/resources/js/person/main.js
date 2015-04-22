/**
 * Created by Administrator on 2015/4/17.
 */
define([
    'common',
    'jquery.dataTables'
], function(common,dataTables){

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

    function getPersonPicture(userid,id){
        var mytable = $('#mytable').dataTable( {
            "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
            "bServerSide" : true, //是否启动服务器端数据导入
            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
            "sScrollY" : 450, //DataTables的高
            "sScrollX" : 820, //DataTables的宽
            "aLengthMenu" : [20, 40, 60], //更改显示记录数选项
            "iDisplayLength" : 40, //默认显示的记录数
            "bAutoWidth" : true, //是否自适应宽度
            //"bScrollInfinite" : false, //是否启动初始化滚动条
            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
            "bPaginate" : true, //是否显示（应用）分页器
            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数
            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页
            "bSort" : true, //是否启动各个字段的排序功能
            "aaSorting" : [[1, "asc"]], //默认的排序方式，第2列，升序排列
            "bFilter" : true, //是否启动过滤、搜索功能
            "aoColumns": [
                {
                    "mDataProp" : "id",
                    "sDefaultContent" : "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                    "bVisible" : false //此列不显示
                }, {
                    "mDataProp" : "userId",
                    "sTitle" : "用户ID",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "state",
                    "sTitle" : "state",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "remark",
                    "sTitle" : "remark",
                    "sDefaultContent" : "",
                    "sClass" : "center"
                },
                {
                    "mDataProp" : "createTime",
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
                }
            ],
            "oLanguage": { //国际化配置
                "sProcessing" : "正在获取数据，请稍后...",
                "sLengthMenu" : "显示 _MENU_ 条",
                "sZeroRecords" : "没有您要搜索的内容",
                "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                "sInfoEmpty" : "记录数为0",
                "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                "sInfoPostFix" : "",
                "sSearch" : "搜索",
                "sUrl" : "",
                "oPaginate": {
                    "sFirst" : "第一页",
                    "sPrevious" : "上一页",
                    "sNext" : "下一页",
                    "sLast" : "最后一页"
                }
            },
            "sAjaxSource" : 'person/getPersonPicture?random='+parseInt(Math.random()*100000),
            //服务器端，数据回调处理
            "fnServerData" : function(sSource, aDataSet, fnCallback) {
                $.ajax({
                    "dataType" : 'json',
                    "type" : "POST",
                    "url" : sSource,
                    "data" : { userid:userid},
                    "success" : fnCallback
                });
            }
        } );


        //alert("ddd");
        //$('#'+id).dataTable( {
        //    "ajax": 'person/getPersonPicture?random='+parseInt(Math.random()*100000),
        //    "columns": [
        //        { "data": "id" },
        //        { "data": "userId" },
        //        { "data": "state" },
        //        { "data": "remark" },
        //        { "data": "createTime" },
        //        { "data": "startTime" },
        //        { "data": "endTime" },
        //        { "data": "totalAmount" }
        //    ]
        //} );
        //$.ajax({
        //    type: "POST",
        //    url: 'person/getPersonPicture?random='+parseInt(Math.random()*100000),
        //    data:{
        //        userid:userid
        //    },
        //    dataType: 'json',
        //    success: function (data) {
        //        if(data!=null && data.msg === "success"){
        //            callback(data);
        //        }
        //        else{
        //            try {
        //                common.alert_message("error","不好意思，遇到错误");
        //            } catch (e) {
        //                console.log(e);
        //            }
        //        }
        //    }
        //});
    }

    return {
        getPersonRealTimeMsg_RepeatLogin:getPersonRealTimeMsg_RepeatLogin,
        getPersonPicture:getPersonPicture
    }

});



