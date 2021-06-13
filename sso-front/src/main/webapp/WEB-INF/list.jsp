<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理页面</title>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">

</head>
<script src="${ctx}/static/laydate/laydate.js" type="text/javascript"></script>
<body id="app">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"></div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item"><a onclick="logout()">Sign out</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">活动管理</a>
                    <input id="sp_ids" type="hidden" value="${user.permission}">
                    <dl class="layui-nav-child">
                        <c:forEach items="${user.permission}" var="permission">
                            <c:if test="${permission == 1 }">
                                <dd style="background: #395461;"><a href="<%=request.getContextPath()%>/list">一级外卖</a></dd>
                            </c:if>
                            <c:if test="${permission == 2}">
                                <dd><a href="<%=request.getContextPath()%>/hall/list">堂食</a></dd>
                            </c:if>
                            <c:if test="${3==permission}">
                                <dd><a href="<%=request.getContextPath()%>/permission">权限管理</a></dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding-top: 1rem;">
            <td style="width: 120px">销售开始时间</td>
            <td><input type="text" id="test1" autocomplete="off" readonly="readonly"></td>
            <td style="width: 120px">销售结束时间</td>
            <td><input type="text" id="test2" autocomplete="off" readonly="readonly"></td>
            <td style="width: 120px"></td>
            <td> 促销编码 <input type="text" id="activityCode" autocomplete="off"></td>
            <td><input type="button" id="query" value="查询"></td>
            <td><input type="button" id="reset" value="重置"></td>
        </div>

        <table id="demo" lay-filter="test"></table>

    </div>

    <%--<div class="layui-footer">--%>
    <%--<!-- 底部固定区域 -->--%>
    <%----%>
    <%--</div>--%>
</div>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="add">新增</a>
    <a class="layui-btn layui-btn-xs" lay-event="upload">导出</a>
    <%--<a class="layui-btn layui-btn-xs" lay-event="sumbit">提交OA</a>--%>
    <%--<div class="layui-inline" style="float:right;height:29px;" title="搜索" lay-event="LAYTABLE_SEARCH">--%>
    <%--<i class="layui-icon layui-icon-search"></i>--%>
    <%--</div>--%>
    <%--<input type="text" id="activityCode" style="width:200px;float:right;height:30px;" placeholder="请输入促销编码" autocomplete="off" class="layui-input">--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>

</script>
<script type="text/html" id="opt">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script>
    var begainTime = '';
    var endTime = '';

    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        var tableIn = table.render({
            elem: '#demo'
            , id: 'list'
            // , height: 'full-80'
            , url: '<%=request.getContextPath()%>/PromotionController/queryPromotionList?type=W' //数据接口
            , request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , response: {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                , statusCode: 10000 //规定成功的状态码，默认：0
                , msgName: 'message' //规定状态信息的字段名称，默认：msg
                , countName: 'total' //规定数据总数的字段名称，默认：count
                // ,dataName: 'rows' //规定数据列表的字段名称，默认：data
            }
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'checkbox', field: 'id', title: 'ID', sort: true, fixed: 'left'}
                , {field: 'activityCode', title: '促销编码', sort: true}
                , {field: 'activityType', title: '活动类型'}
                , {field: 'introduction', title: '七字描述'}
                , {field: 'salesStartTime', title: '开始时间'}
                , {field: 'salesEndTime', title: '结束时间', sort: true}
                , {fixed: 'right', align: 'center', toolbar: '#opt'} //这里的toolbar值是模板元素的选择器
            ]]
            , toolbar: '#barDemo'
            , defaultToolbar: ['filter', 'print', 'exports',]
        });

        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if (layEvent === 'detail') { //查看

            } else if (layEvent === 'edit') { //编辑
                window.open('<%=request.getContextPath()%>/edit?id=' + data.activityCode);
                <%--location.href = '<%=request.getContextPath()%>/edit?id=' + data.activityCode;--%>
            }
        });

        table.on('toolbar(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'sumbit':

                    break;
                case 'add':
                    window.open('<%=request.getContextPath()%>/add');
                <%--location.href = '<%=request.getContextPath()%>/add';--%>
                    break;
                case 'upload':
                    var ids = [];
                    if (checkStatus.data.length == 0) {
                        layer.msg("请至少勾选一条");
                        return;
                    }
                    for (j = 0; j < checkStatus.data.length; j++) {
                        ids.push(checkStatus.data[j].activityCode);
                    }
                    app.upload(ids);
                    break;
                case 'delete':
                    var ids = [];
                    if (checkStatus.data.length == 0) {
                        layer.msg("请至少勾选一条");
                        return;
                    }
                    layer.confirm('确定要删除吗', {icon: 3, title: '提示'}, function (index) {
                        for (j = 0; j < checkStatus.data.length; j++) {
                            ids.push(checkStatus.data[j].id);
                        }
                        layer.close(index);
                        //向服务端发送删除指令
                        app.delete(ids);
                        tableIn.reload();
                    });

                    break;
            }
            ;
        });


        $("#reset").on("click", function () {
            begainTime = '';
            endTime = '';
            $('#test1').val('');
            $('#test2').val('');
            $("#activityCode").val('');
        });

        $("#query").on("click", function () {
            var saleBegainTime = 0;
            if (begainTime != '') {
                var startTimestamp = new Date(begainTime.replace(/-/g, "/"));
                saleBegainTime = new Date(startTimestamp).getTime();

            }
            var saleEndTime = 0;
            if (endTime != '') {
                var endTimestamp = new Date(endTime.replace(/-/g, "/"));
                saleEndTime = new Date(endTimestamp).getTime();
            }

            if (saleEndTime < saleBegainTime) {
                layer.msg('结束时间不能小于开始时间');
                return;
            }
            tableIn.reload(
                {
                    where: {
                        activityCode: $("#activityCode").val(),
                        begainTime: begainTime,
                        endTime: endTime
                    }
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                }
            );
        });

    });

    var app = new Vue({
        el: '#app',
        data: {
            querydata: []
        },
        created: function () {
            // this.getList();
        },
        methods: {
            upload: function (data) {
                var idstr = JSON.stringify(data);
                window.location.href = '<%=request.getContextPath()%>/PromotionController/exportExcel?codestr=' + encodeURIComponent(idstr);
            },
            delete: function (data) {
                var idstr = JSON.stringify(data);
                this.$http.post('<%=request.getContextPath()%>/PromotionController/deletePromotion', {ids: idstr}, {emulateJSON: true}).then(function (response) {
                        console.log(response.data);
                    },
                    function (response) {
                        console.log("网络异常");
                    });
            },
        }

    });


    //日期控件
    var bMinDate;
    var defaultMinDate = {
        year: 1900,
        month: 1,
        date: 1,
        hours: 0,
        minutes: 0,
        seconds: 0
    };
    var aMaxDate;
    var defaultMaxDate = {
        year: 2222,
        month: 1,
        date: 1,
        hours: 0,
        minutes: 0,
        seconds: 0
    };


    var a = laydate.render({
        elem: '#test1' //指定元素
        , show: false //直    接显示
        , trigger: 'click' //采用click弹出
        , btns: ['clear', 'confirm']
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        , done: function (value, date, endDate) {
            begainTime = value;
            if (value == '' || value == undefined) {
                bMinDate = defaultMinDate
            } else {
                date.month = date.month - 1;
                bMinDate = date;
            }
        }
    });

    lay('#test1').on('click', function (e) {
        if (aMaxDate == undefined || aMaxDate == '') {
            a.config.max = defaultMaxDate;
        } else {
            a.config.max = aMaxDate;
        }
    });


    var b = laydate.render({
        elem: '#test2' //指定元素
        , show: false //直接显示
        , trigger: 'click' //采用click弹出
        , btns: ['clear', 'confirm']
        // ,closeStop: '#test2' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        , done: function (value, date, endDate) {
            endTime = value;
            if (value == '' || value == undefined) {
                aMaxDate = defaultMaxDate
            } else {
                date.month = date.month - 1
                aMaxDate = date;
            }
        }
    });

    lay('#test2').on('click', function (e) {
        if (bMinDate == undefined || bMinDate == '') {
            b.config.min = defaultMinDate;
        } else {
            b.config.min = bMinDate;
        }

    });

    function logout(){
        layer.confirm('确定要退出吗', {icon: 3, title: '提示'}, function (index) {
            location.href="<%=request.getContextPath()%>/logout";
        });
    }
</script>
</body>
</html>