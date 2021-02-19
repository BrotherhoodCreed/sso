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
<div class="layui-form-item">
    <label class="layui-form-label" style="width: 120px;">通知用户</label>
    <div class="layui-input-inline" style="margin-left: 20px; width: 450px;">
        <input type="text" name="noticUser" autocomplete="on" placeholder="请输入通知用户" class="layui-input">
        <i class="layui-icon iconSerch">&#xe615;</i>
    </div>
</div>

<div class="showChooseDiv" style="display: none;  padding: 20px">
    <div id="dept_main" style="margin-right: 2%;">
        <div id="dept_tree">
        </div>
        <div class="layui-form-item float-right">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal layui-btn-sm" id="lay-submit-Choose">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script>
    var files;
    var form;
    var layer;
    var $;
    var transfer;
    var str = [{
        id:1,
        title: '江西' //一级菜单
        ,children: [{
            id:3,
            title: '南昌' //二级菜单
            ,children: [{
                id:4,
                title: '高新区' //三级菜单
                //…… //以此类推，可无限层级
            },
                {
                    id:5,
                    title: '高新区2' //三级菜单
                    //…… //以此类推，可无限层级
                }
            ]
        }]
    },{
        id:2,
        title: '陕西' //一级菜单
        ,children: [{
            id:6,
            title: '西安' //二级菜单
        }]
    }];

    layui.use(['layer','tree'], function() {
        var $ = layui.jquery;
        layer = layui.layer;
        var tree = layui.tree;
        //树形组件复选框
        tree.render({
            elem: '#dept_tree',
            data: str,
            id: 'checkTree',
            showCheckbox: true,     //是否显示复选框
            onlyIconControl: true
        });
        function getData(){
            var data = [];
            $.ajax({
                url : "/PtRole/allRole",//后台数据请求地址
                dataType : 'json',
                type : 'GET',
                async : false,
                success: function(resut){
                    data = resut;
                }
            });
            return data;
        }

        //打开选择页
        $("body").on("click", ".iconSerch", function() {
            var dataInto=$(this).prev().attr("name");
            $("#lay-submit-Choose").attr("data-into",dataInto);
            var dataDicType="";
            if(dataInto.lastIndexOf('.')==-1){
                dataDicType=dataInto;
            }else{
                dataDicType=dataInto.substr(dataInto.lastIndexOf('.')+1)
            }

            layer.open({
                type: 1,
                title: "选择",
                area: ['18%', '50%'],
                content: $(".showChooseDiv"),
                maxmin: false,
                shadeClose: true,
                shade: false,
            });
        });
        //选择页确定
        $("#lay-submit-Choose").on("click", function() {
            var getData = tree.getChecked('checkTree');
            console.log(getData);
            if (getData.length<1) {
                layer.alert("请选择一项");
            }else{
                var datas = getCheckedId(getData);
                console.log(datas);
                layer.close(layer.index);
            }
            return false;
        });
        //渲染字典项选择
        function renderChoose(dataDicType) {
            layer.alert("请选择一项");
        };

        function getCheckedId(jsonObj) {
            var id = "";
            $.each(jsonObj, function (index, item) {
                if(undefined == item.children){
                    console.log('当前id'+item.id+'是叶子节点');
                    //到了叶子节点
                    if (id != ""){
                        id = id + "," + item.id;
                    }
                    else {
                        id = item.id;
                    }
                }else {
                    console.log('当前id'+item.id+'存在子节点');
                    if (id != ""){
                        id = id + "," + getCheckedId(item.children);
                    }
                    else {
                        id = getCheckedId(item.children);
                    }
                }
            });
            return id;
        }

    });
</script>

</body>
</html>