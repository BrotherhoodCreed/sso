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
    </style>
</head>
<script src="${ctx}/static/laydate/laydate.js" type="text/javascript"></script>
<body id="app">
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" readonly="readonly" autocomplete="off" class="layui-input" v-model="edit.name">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" readonly="readonly" lay-verify="required|phone" autocomplete="off" class="layui-input"  v-model="edit.mobile">
            </div>
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">权限管理</label>
        <div class="layui-input-block" id="checkBox">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal" @click="editPermission()">编辑</button>
        </div>
    </div>
</form>

<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;


        // form.on('checkbox()', function(data){
        //     app.add.roleCodes.push(data.value);
        // });
    });

    var app = new Vue({
        el: "#app",
        data: {
            edit:{
                id:${permissionId},
                name:'${name}',
                mobile:'${mobile}',
                roleCodes:[],
                roledesc:''
            },
            permissionList : [],
        },
        created:function(){
            if(this.edit.id != '' && this.edit.id != undefined && this.edit.id !='null'){
                this.queryDetail();
            }else {
                this.query('role_core');
            }
        },
        methods: {
            query:function (type) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        if('role_core' == type){
                            this.permissionList = response.data.data;
                            var html = '';
                            $.each(app.permissionList, function(index, item) {
                                //赋值
                                var checked = false;
                                $.each(app.edit.roleCodes, function(index, item2) {
                                    if(item2 == item.descriptionCode){
                                        checked = true;
                                    }
                                });
                                if(checked){
                                    html = html + '<input type="checkbox" name="permission"  checked title="'+ item.description + '" value="'+item.descriptionCode+'">';
                                }else {
                                    html = html + '<input type="checkbox" name="permission" title="'+ item.description + '" value="'+item.descriptionCode+'">';
                                }

                              $('#checkBox').html(html);
                            });
                        }
                        layui.form.render("checkbox");
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            queryDetail:function () {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/permission/query',{id:this.edit.id},{emulateJSON:true}).then(function(response) {
                        this.edit = response.data.data;
                        this.query('role_core');
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            editPermission:function () {
                if('' ==this.edit.name){
                    layer.msg('姓名为空');
                    return;
                }
                if('' ==this.edit.mobile){
                    layer.msg('手机为空');
                    return;
                }
                this.edit.roleCodes=[];
                var roledesc = [];
                $('#checkBox input[type=checkbox]:checked').each(function() {
                    console.log($(this));
                    app.edit.roleCodes.push($(this).val());
                    roledesc.push($(this).attr("title"));
                });
                if(this.edit.roleCodes.length == 0){
                    layer.msg('请选择权限');
                    return;
                }
                this.edit.roledesc = roledesc.join(',');
                if(this.edit.roleCodes.length == 0){
                    layer.msg('请选择权限');
                    return;
                }
                this.$http.post('<%=request.getContextPath()%>/PromotionController/permission/edit',
                    JSON.stringify(this.edit),{emulateJSON:false}).then(function(response) {
                        //设置好内容后刷新，  多用于异步请求
                        layer.msg('修改成功');
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