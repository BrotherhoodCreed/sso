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
                <input type="tel" name="phone" autocomplete="off" class="layui-input" v-model="add.name">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input"  v-model="add.mobile">
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
            <button type="button" class="layui-btn layui-btn-normal" @click="addPermission()">新增</button>
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
        //     console.log(data.value);
        //     console.log(data.elem.checked); //是否被选中，true或者false
        //     if(data.elem.checked){
        //         app.add.roleCodes.push(data.value);
        //     }else {
        //         app.add.roleCodes
        //     }
        // });
    });

    var app = new Vue({
        el: "#app",
        data: {
            add:{
                name:'',
                mobile:'',
                roleCodes:[],
                roledesc:''
            },
            permissionList : [],
        },
        created:function(){
            this.query("role_core");
        },
        methods: {
            query:function (type) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        if('role_core' == type){
                            this.permissionList = response.data.data;
                            var html = '';
                            $.each(this.permissionList, function(index, item) {
                                //赋值
                                 html = html + '<input type="checkbox" name="permission" title="'+ item.description + '" value="'+item.descriptionCode+'">';
                              $('#checkBox').html(html);

                            });
                        }
                        layui.form.render("checkbox");
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            addPermission:function () {
                if('' ==this.add.name){
                    layer.msg('姓名为空');
                    return;
                }
                if('' ==this.add.mobile){
                    layer.msg('手机为空');
                    return;
                }
                this.add.roleCodes=[];
                var roledesc = [];
                $('#checkBox input[type=checkbox]:checked').each(function() {
                   console.log($(this));
                    app.add.roleCodes.push($(this).val());
                    roledesc.push($(this).attr("title"));
                });
                if(this.add.roleCodes.length == 0){
                    layer.msg('请选择权限');
                    return;
                }
                this.add.roledesc = roledesc.join(',');
                this.$http.post('<%=request.getContextPath()%>/PromotionController/permission/add',
                    JSON.stringify(this.add),{emulateJSON:false}).then(function(response) {
                        //设置好内容后刷新，  多用于异步请求
                        layer.msg('保存成功');
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