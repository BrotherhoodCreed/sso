<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
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
            <ul class="layui-nav layui-nav-tree" lay-shrink="all"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">活动管理</a>
                    <dl class="layui-nav-child">
                        <c:forEach items="${user.permission}" var="permission">
                            <c:if test="${permission eq 'role01' }">
                                <dd><a href="<%=request.getContextPath()%>/list">一级外卖</a></dd>
                            </c:if>
                            <c:if test="${permission eq 'role02'}">
                                <dd><a href="<%=request.getContextPath()%>/hall/list">堂食</a></dd>
                            </c:if>
                            <c:if test="${'role03'eq permission}">
                                <dd><a href="<%=request.getContextPath()%>/permission">权限管理</a></dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
    </div>

    <%--<div class="layui-footer">--%>
    <%--<!-- 底部固定区域 -->--%>
    <%----%>
    <%--</div>--%>
</div>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
</body>
</html>