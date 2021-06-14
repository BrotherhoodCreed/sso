<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理页面</title>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .layui-form-label {
            width: 100px;
        }
        .layui-input-block {
            margin-left: 130px;
        }
        .layui-form-item{
            margin-top: 10px;
        }
    </style>
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
                    <dl class="layui-nav-child">
                        <c:forEach items="${user.permission}" var="item">
                            <c:if test="${item == 1}">
                                <dd><a href="<%=request.getContextPath()%>/list">一级外卖</a></dd>
                            </c:if>
                            <c:if test="${item == 2}">
                                <dd><a href="<%=request.getContextPath()%>/hall/list">堂食</a></dd>
                            </c:if>
                            <c:if test="${item == 3}">
                                <dd style="background: #395461;"><a href="<%=request.getContextPath()%>/permission">权限管理</a></dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <table id="demo" lay-filter="test"></table>
    </div>


</div>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="add">新增</a>
<%--    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>--%>

</script>
<script type="text/html" id="opt">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script>
    var app = new Vue({
        el: "#app",
        data: {
            add:{
                name:'',
                mobile:'',
                permission:[],
            },
            edit:{
                name:'',
                mobile:'',
                permission:[],
            },
            permissionList:[]
        },
        created:function(){
            // this.query("permission");
        },
        methods: {
            query:function (type,opt) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        if('role_core' == type){
                            this.permissionList = response.data.data;
                            // $.each(response.data.data, function(index, item) {
                            //     if ('edit' == opt) {
                            //         if (item.descriptionCode == app.edit.permission+'') {
                            //             $('#editSelect').append(new Option(item.description, item.descriptionCode, true, true));
                            //         } else {
                            //             $('#editSelect').append(new Option(item.description, item.descriptionCode));
                            //         }
                            //     }else {
                            //         if (item.descriptionCode == app.add.permission) {
                            //             $('#addSelect').append(new Option(item.description, item.descriptionCode, true, true));
                            //         } else {
                            //             $('#addSelect').append(new Option(item.description, item.descriptionCode));
                            //         }
                            //     }
                            // });
                        }

                        layui.form.render("select");
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            addPermission:function () {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/permission/add',{
                    name:this.add.name,mobile:this.add.mobile,permission:this.add.permission},{emulateJSON:true}).then(function(response) {
                        //设置好内容后刷新，  多用于异步请求
                        layer.msg('保存成功');
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            editPermission:function () {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/permission/edit',{
                    name:this.edit.name,mobile:this.edit.mobile,permission:this.edit.permission},{emulateJSON:true}).then(function(response) {
                        //设置好内容后刷新，  多用于异步请求
                        layer.msg('修改成功');
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
        }
    });


    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        var tableIn = table.render({
            elem: '#demo'
            , id: 'list'
            // , height: 'full-80'
            , url: '<%=request.getContextPath()%>/PromotionController/permission/list' //数据接口
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
                , {field: 'name', title: '姓名', sort: true}
                , {field: 'mobile', title: '手机号'}
                , {field: 'roledesc', title: '权限'}
                , {fixed: 'right', align: 'center', toolbar: '#opt'} //这里的toolbar值是模板元素的选择器
            ]]
            , toolbar: '#barDemo'
        });

        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if (layEvent === 'edit') { //编辑
                window.open('<%=request.getContextPath()%>/editPermission?id=' + data.id);
            }
        });

        table.on('toolbar(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    window.open('<%=request.getContextPath()%>/addPermission');
                    break;
            }
            ;
        });

    });

    function logout(){
        layer.confirm('确定要退出吗', {icon: 3, title: '提示'}, function (index) {
            location.href="<%=request.getContextPath()%>/logout";
        });
    }


</script>
</body>
</html>