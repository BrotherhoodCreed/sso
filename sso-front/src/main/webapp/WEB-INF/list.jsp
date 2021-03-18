<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理页面</title>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
</head>
<body id="app">
<div><input id="test" value=""></div>
<table id="demo" lay-filter="test"></table>

<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="add">新增</a>
    <a class="layui-btn layui-btn-xs" lay-event="upload">导出</a>
    <a class="layui-btn layui-btn-xs" lay-event="sumbit">提交OA</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>
<script type="text/html" id="opt">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        var tableIn = table.render({
            elem: '#demo'
            ,id:'list'
            ,height: 'full-20'
            ,url: '<%=request.getContextPath()%>/PromotionController/queryPromotionList' //数据接口
            ,request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                ,statusCode: 10000 //规定成功的状态码，默认：0
                ,msgName: 'message' //规定状态信息的字段名称，默认：msg
                ,countName: 'total' //规定数据总数的字段名称，默认：count
                // ,dataName: 'rows' //规定数据列表的字段名称，默认：data
            }
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'checkbox',field: 'id', title: 'ID',  sort: true, fixed: 'left'}
                ,{field: 'activityCode', title: '促销编码', sort: true}
                ,{field: 'activityType', title: '活动类型' }
                ,{field: 'salesStartTime', title: '开始时间'}
                ,{field: 'salesEndTime', title: '结束时间',  sort: true}
                ,{fixed: 'right', align:'center', toolbar: '#opt'} //这里的toolbar值是模板元素的选择器
            ]]
            ,toolbar:'#barDemo'
            ,defaultToolbar: ['filter', 'print', 'exports', ]
        });

        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if(layEvent === 'detail'){ //查看

            } else if(layEvent === 'edit'){ //编辑
                location.href = '<%=request.getContextPath()%>/edit?id='+data.activityCode;
            }
        });

        table.on('toolbar(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'sumbit':

                    break;
                case 'add':
                    location.href = '<%=request.getContextPath()%>/add';
                    break;
                case 'upload':
                    var ids = [];
                    if (checkStatus.data.length == 0){
                        layer.msg("请至少勾选一条");
                        return;
                    }
                    for(j = 0; j < checkStatus.data.length; j++) {
                        ids.push(checkStatus.data[j].activityCode);
                    }
                    app.upload(ids);
                    break;
                case 'delete':
                    var ids = [];
                    if (checkStatus.data.length == 0){
                        layer.msg("请至少勾选一条");
                        return;
                    }
                    layer.confirm('确定要删除吗',{icon: 3, title:'提示'}, function(index){
                        for(j = 0; j < checkStatus.data.length; j++) {
                            ids.push(checkStatus.data[j].id);
                        }
                        layer.close(index);
                        //向服务端发送删除指令
                        app.delete(ids);
                        tableIn.reload();
                    });

                    break;
            };
        });
    });

    var app = new Vue({
        el: '#app',
        data: {
            querydata: []
        },
        created:function(){
            // this.getList();
        },
        methods: {
            upload:function (data) {
                var idstr =  JSON.stringify(data);
                window.location.href = '<%=request.getContextPath()%>/PromotionController/exportExcel?codestr='+encodeURIComponent(idstr);
            },
            delete:function (data) {
                var idstr =  JSON.stringify(data);
                this.$http.post('<%=request.getContextPath()%>/PromotionController/deletePromotion',{ids:idstr},{emulateJSON:true}).then(function(response) {
                        console.log(response.data);
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
        }

    });

</script>
</body>
</html>