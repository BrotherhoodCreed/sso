<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理页面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body id="app">
    <div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        区域
                    </th>
                    <th>
                        城市
                    </th>
                    <th>
                        餐厅编码
                    </th>
                    <th>
                        促销编码
                    </th>
                    <th>
                        活动类型
                    </th>
                    <th>
                        开始时间
                    </th>
                    <th>
                        结束时间
                    </th>
                    <th>
                        所有对解表栏位
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr @click="toDetail()">
                    <td>
                       1
                    </td>
                    <td>
                       2
                    </td>
                    <td>
                       3
                    </td>
                    <td>
                        4
                    </td>
                    <td>
                        5
                    </td>
                    <td>
                        6
                    </td>
                    <td>
                        7
                    </td>
                    <td>
                        8
                    </td>
                </tr>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        2
                    </td>
                    <td>
                        3
                    </td>
                    <td>
                        4
                    </td>
                    <td>
                        5
                    </td>
                    <td>
                        6
                    </td>
                    <td>
                        7
                    </td>
                    <td>
                        8
                    </td>
                </tr>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        2
                    </td>
                    <td>
                        3
                    </td>
                    <td>
                        4
                    </td>
                    <td>
                        5
                    </td>
                    <td>
                        6
                    </td>
                    <td>
                        7
                    </td>
                    <td>
                        8
                    </td>
                </tr>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        2
                    </td>
                    <td>
                        3
                    </td>
                    <td>
                        4
                    </td>
                    <td>
                        5
                    </td>
                    <td>
                        6
                    </td>
                    <td>
                        7
                    </td>
                    <td>
                        8
                    </td>
                </tr>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        2
                    </td>
                    <td>
                        3
                    </td>
                    <td>
                        4
                    </td>
                    <td>
                        5
                    </td>
                    <td>
                        6
                    </td>
                    <td>
                        7
                    </td>
                    <td>
                        8
                    </td>
                </tr>
                </tbody>
            </table>
            <ul class="pager">
                <li class="previous"><a href="#">&larr; 上一页</a></li>
                <li>当前页面 1,跳转至<input style="width: 2.5rem;"/></li>
                <li class="next"><a href="#">下一页 &rarr;</a></li>
            </ul>
        </div>
    </div>
</div>
</body>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            querydata: []
        },
        created:function(){
            this.getList();
        },
        methods:{
            getList:function(){
                this.$http.get('<%=request.getContextPath()%>/index/list').then(function(response) {
                        this.querydata = response.data;
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            toDetail:function(index){
                window.location.href='<%=request.getContextPath()%>/detail?index='+index;
            },
            modify:function () {
                if (isNaN(this.modifyKey)) {
                    alert('innerBusinessCatetoryType值不是数字');
                    return;
                }
                this.modifyKey = parseInt(this.modifyKey);

                if (isNaN(this.modifyValue)) {
                    alert('stationWindowType值不是数字');
                    return;
                }
                this.modifyValue = parseInt(this.modifyValue);
                //校验重复
                var check = app.querydata.some(function(item,index,array){
                    if(item.innerBusinessCatetoryType==app.modifyKey && index != app.modifyIndex)
                        return true
                });
                if (check){
                    alert("innerBusinessCatetoryType（"+this.modifyKey+"）值已存在");
                    return;
                }
                this.querydata[this.modifyIndex].innerBusinessCatetoryType = this.modifyKey;
                this.querydata[this.modifyIndex].stationWindowType = this.modifyValue;
                var param = JSON.stringify(this.querydata);
                this.$http.post('<%=request.getContextPath()%>/data-query/station_window/modify',{str:param},{emulateJSON:true}).then(function(response) {
                        if ("0" != response.data.code){
                            alert("修改失败("+response.data.msg+")");
                            return;
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            toAdd:function () {
                if (isNaN(this.addKey)) {
                    alert('新增innerBusinessCatetoryType值不是数字');
                    return;
                }
                this.addKey = parseInt(this.addKey);

                if (isNaN(this.addValue)) {
                    alert('新增stationWindowType值不是数字');
                    return;
                }
                this.addValue = parseInt(this.addValue);

                var check = app.querydata.some(function(item,index,array){
                    if(item.innerBusinessCatetoryType==app.addKey)
                        return true
                });
                if (check){
                    alert("innerBusinessCatetoryType（"+this.addKey+"）值已存在");
                    return;
                }
                var a = {innerBusinessCatetoryType:this.addKey,stationWindowType:this.addValue};
                this.querydata.push(a);
                var param = JSON.stringify(this.querydata);
                this.$http.post('<%=request.getContextPath()%>/data-query/station_window/modify',{str:param},{emulateJSON:true}).then(function(response) {
                        if ("0" != response.data.code){
                            alert("添加失败("+response.data.msg+")");
                            return;
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            delete:function (index) {
                var r=confirm("确定要删除吗?");
                if (r==false){
                    return;
                }
                this.querydata.splice(index,1);
                var param = JSON.stringify(this.querydata);
                this.$http.post('<%=request.getContextPath()%>/data-query/station_window/modify',{str:param},{emulateJSON:true}).then(function(response) {
                        if ("0" != response.data.code){
                            alert("删除失败("+response.data.msg+")");
                            return;
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            }
        }
    });
</script>

</html>