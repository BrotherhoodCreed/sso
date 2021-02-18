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
<table id="demo" lay-filter="test"></table>
<div id="test1"></div>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script>
    layui.use('tree', function(){
        var tree = layui.tree;

        //渲染
        var inst1 = tree.render({
            elem: '#test1'  //绑定元素
            ,data: [{
                title: '江西' //一级菜单
                ,children: [{
                    title: '南昌' //二级菜单
                    ,children: [{
                        title: '高新区' //三级菜单
                        //…… //以此类推，可无限层级
                    }]
                }]
            },{
                title: '陕西' //一级菜单
                ,children: [{
                    title: '西安' //二级菜单
                }]
            }]
        });
    });
</script>

</body>
</html>