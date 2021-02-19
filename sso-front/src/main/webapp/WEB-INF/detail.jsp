<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        input {
            margin-right: 14px;
        }
        
        select {
            margin-right: 14px;
        }
        
        #app div {
            margin-bottom: 10px !important;
        }

        #app {
            margin-top: 30px !important;
            margin-left: 50px !important;
        }
    </style>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">

<body>
    <form action="" id="app">
        <div><span>促销编码</span> <input type="number" v-model="detail.activityCode"  style="width: 110px;"> <span>活动类型</span> <select v-model="detail.activityType"  style="width: 100px;"><option value="1">团购</option></select>
            <span>销售开始时间</span>
            <input type="text" id="test1">

            <span>销售结束时间</span>
            <input type="text" id="test2">
        </div>
        <div><span>每台现用张数/金额</span> <input type="number" @input="valueChange"  v-model="detail.amount" style="width: 5rem; text-align: center;">
            <span>回款周期</span>
            <select v-model="detail.billCycle"  style="width: 100px; ">
                <option value="1">T+1</option>
                <option value="2">T+2</option>
                <option value="3">T+3</option>
                <option value="4">T+4</option>
                <option value="5">T+5</option>
                <option value="6">T+6</option>
                <option value="7">T+7</option>
                <option value="8">T+8</option>
                <option value="9">T+9</option>
                <option value="10">T+10</option>
                <option value="11">T+11</option>
                <option value="12">T+12</option>
                <option value="13">T+13</option>
                <option value="14">T+14</option>
                <option value="15">T+15</option>
            </select>
        </div>
        <div>
            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">活动描述</h3>
            <textarea style="width: 895px; height: 55px; resize: none;" v-model="detail.description">
            </textarea>
        </div>
        <div><span>七字描述</span> <input type="text " v-model="detail.introduction" @input="valueChange" style="width: 110px;">
            <span>团购网站</span> <select v-model="detail.theWay"  style="width: 100px;">
                <option value="" >请选择</option>
                <option value="1" >美团</option>
                <option value="2" >饿了么</option>
            </select>
            <span>核销开始时间</span>
            <input type="text" id="test3">
            <span>核销结束时间</span>
            <input type="text" id="test4">
        </div>
        <div>
            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">与本活动共存的活动</h3>
            <label for="id_select"></label>
            <select id="id_select" class="selectpicker bla bla bli" multiple data-live-search="true">
                <option v-bind:value="item.id" v-for="item in items">{{item.id}}</option>
                </optgroup>
            </select>
        </div>
        <div><span>销售单价</span> <input type="number" v-model="detail.sellingPrice" @input="valueChange"  style="width: 5rem;">
            <span>回款单价</span> <input type="number " v-model="detail.billPrice" @input="valueChange" style="width: 5rem;margin-right: 62px;">
            <span>手续费</span><input type="number " v-model="detail.handlingFee" @input="valueChange"  style="width: 5rem; margin-right: 46px;">
            <span>手续费率</span> <input type="number" v-model="detail.taxRate" @input="valueChange"    style="width: 10rem;"></div>
        <div>
            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">其他</h3>
            <textarea v-model="detail.other"  style="width: 895px; height: 35px; resize: none;"></textarea>
        </div>
        <div>
            <button type="button" class="btn btn-default" id="addActive">添加活动</button>
        </div>
        <table id="demo" lay-filter="test"></table>

        <div>
            <button type="button" class="btn btn-default" @click="save()">保存</button>
        </div>

    </form>
    <div class="showChooseDiv" style="display: none;  padding: 20px">
            <div id="dept_main" style="margin-right: 2%;">
                <span>
                    <input type="text" />
                    <button type="button" style="width: 10%;">搜索</button>
                </span>
                <div id="dept_tree">
                </div>
                <div class="layui-form-item float-right">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal layui-btn-sm" id="lay-submit-Choose">确定</button>
                    </div>
                </div>
            </div>
        </div>
</body>

<script src="${ctx}/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="${ctx}/static/laydate/laydate.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/layer/layer.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-select.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var app = new Vue({
        el: "#app",
        data: {
            detail:{
                id:"",
                activityCode:'',
                activityType:"",
                amount:"",
                billCycle:"",
                description:"",
                introduction:"",
                channel:'',
                theWay:'',
                salesStartTime:"",
                saleBegainTime:"",
                salesEndTime:"",
                saleEndTime:"",
                writeBegain:"",
                writeEnd:"",
                sharedActivity:[],
                other:"",
                handlingFee:"",
                taxRate:'',
                sellingPrice:'',
                billPrice:'',
            },
            items:[{id:'我'},{id:'我是你爹'},{id:'你的'}],
        },
        methods: {
            valueChange(e){
                e.target.value = e.target.value.replace(/(^\s*)|(\s*$)/g, "");
                const reg = /[^\d.]/g;

                // 只能是数字和小数点，不能是其他输入
                e.target.value = e.target.value.replace(reg, "");

                // 保证第一位只能是数字，不能是点
                e.target.value = e.target.value.replace(/^\./g, "");
                // 小数只能出现1位
                e.target.value = e.target.value
                    .replace(".", "$#$")
                    .replace(/\./g, "")
                    .replace("$#$", ".");
                // 小数点后面保留2位
                e.target.value = e.target.value.replace(
                    /^(\-)*(\d+)\.(\d\d).*$/,
                    "$1$2.$3"
                );
            },
            save:function () {
                var actives = ($(".selectpicker").val());
                this.detail.sharedActivity = actives;
                if (this.detail.activityCode == ''){
                    layer.msg('促销编码为空');
                    return;
                }
                if (this.detail.activityType == ''){
                    layer.msg('活动类型为空');
                    return;
                }
                if (this.detail.saleBegain == ''
                    || this.detail.saleBegainTime == ''){
                    layer.msg('销售开始时间为空');
                    return;
                }
                if (this.detail.saleEnd == ''
                    || this.detail.saleEndTime == ''){
                    layer.msg('销售结束时间为空');
                    return;
                }
                if (this.detail.saleEndTime < this.detail.saleBegainTime){
                    layer.msg('销售结束时间不能小于销售开始时间');
                    return;
                }
                if (this.detail.amount == ''){
                    layer.msg('每台现用张数/金额为空');
                    return;
                }
                if (this.detail.billCycle == ''){
                    layer.msg('回款周期为空');
                    return;
                }
                if (this.detail.description == ''){
                    layer.msg('活动描述为空');
                    return;
                }
                if (this.detail.introduction == ''){
                    layer.msg('七字描述为空');
                    return;
                }
                if (this.detail.theWay == ''){
                    layer.msg('请选择团购网站');
                    return;
                }
                if (this.detail.writeBegain == ''){
                    layer.msg('核销开始时间为空');
                    return;
                }
                if (this.detail.writeEnd == ''){
                    layer.msg('核销结束时间为空');
                    return;
                }

                if (this.detail.sharedActivity == ''){
                    layer.msg('请选择共存活动');
                    return;
                }
                if (this.detail.sellingPrice == ''){
                    layer.msg('销售价为空');
                    return;
                }
                if (this.detail.billPrice == ''){
                    layer.msg('回款单价为空');
                    return;
                }
                if (this.detail.handlingFee == ''){
                    layer.msg('手续费为空');
                    return;
                }
                if (this.detail.taxRate == ''){
                    layer.msg('手续费率为空');
                    return;
                }
                var param = {
                    promotionBaseInfoDo: this.detail,
                    // promotionMapperDo:[{city:'222'}]
                };
                console.log(param);
                this.$http.post('<%=request.getContextPath()%>/PromotionController/savePromotionBaseInfo',
                    JSON.stringify(param),{emulateJSON:false}).then(function(response) {

                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            query:function (type) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        console.log(response.data);
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
        },
    });


    //日期控件
    var bMinDate;
    var dMinDate;
    var defaultMinDate =   {
        year: 1900,
        month: 1,
        date:1,
        hours: 0,
        minutes: 0,
        seconds: 0
    };
    var aMaxDate;
    var cMaxDate;
    var defaultMaxDate = {
        year: 2222,
        month: 1,
        date:1,
        hours: 0,
        minutes: 0,
        seconds: 0
    };


    var a = laydate.render({
        elem: '#test1' //指定元素
        ,show: false //直    接显示
        ,trigger: 'click' //采用click弹出
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            var startTimestamp = new Date(value.replace(/-/g, "/"));
            var begainTime = new Date(startTimestamp).getTime();
            app.detail.saleBegain=value;
            app.detail.saleBegainTime=begainTime;
            if (value == '' || value == undefined){
                bMinDate = defaultMinDate
            }else {
                date.month = date.month-1;
                bMinDate = date;
            }
        }
    });

    lay('#test1').on('click', function(e){
        if(aMaxDate == undefined || aMaxDate == ''){
            a.config.max=defaultMaxDate;
        }else {
            a.config.max=aMaxDate;
        }
    });


    var b = laydate.render({
        elem: '#test2' //指定元素
        ,show: false //直接显示
        ,trigger: 'click' //采用click弹出
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test2' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            var startTimestamp = new Date(value.replace(/-/g, "/"));
            var endTime = new Date(startTimestamp).getTime();
            app.detail.saleEnd=value;
            app.detail.saleEndTime=endTime;
            if (value == '' || value == undefined){
                aMaxDate = defaultMaxDate
            }else {
                date.month = date.month-1
                aMaxDate = date;
            }
        }
    });

    lay('#test2').on('click', function(e){
        if(bMinDate == undefined || bMinDate == ''){
            b.config.min=defaultMinDate;
        }else {
            b.config.min=bMinDate;
        }

    });

    var c = laydate.render({
        elem: '#test3' //指定元素
        ,show: false //直    接显示
        ,trigger: 'click' //采用click弹出
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.writeBegain=value;
            if (value == '' || value == undefined){
                dMinDate = defaultMinDate
            }else {
                date.month = date.month-1;
                dMinDate = date;
            }
        }
    });

    lay('#test3').on('click', function(e){
        if(cMaxDate == undefined || cMaxDate == ''){
            c.config.max=defaultMaxDate;
        }else {
            c.config.max=cMaxDate;
        }
    });

    var d = laydate.render({
        elem: '#test4' //指定元素
        ,show: false //直    接显示
        ,trigger: 'click' //采用click弹出
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.writeEnd=value;
            if (value == '' || value == undefined){
                dMinDate = defaultMinDate
            }else {
                date.month = date.month-1;
                dMinDate = date;
            }
        }
    });

    lay('#test4').on('click', function(e){
        if(dMinDate == undefined || dMinDate == ''){
            d.config.min=defaultMinDate;
        }else {
            d.config.min=dMinDate;
        }
    });

    $(window).on('load', function () {
        $('.selectpicker').selectpicker({
            'selectedText': '请选择'
        });
    });
</script>

<script>
    var str = [{
        id:1,
        title: '江西' //一级菜单
        ,children: [{
            id:3,
            title: '南昌' //二级菜单
            ,children: [{
                id:4,
                title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                //…… //以此类推，可无限层级
            },
            {
                id:7,
                title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                //…… //以此类推，可无限层级
            },
            {
                id:5,
                title: '高新区2厦门市那萨克斯看' //三级菜单
                //…… //以此类推，可无限层级
            }
            ]
        }]
    },
        {
            id:8,
            title: '陕西' //一级菜单
            ,children: [{
                id:9,
                title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                //…… //以此类推，可无限层级
            },
                {
                    id:10,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:14,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:15,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:16,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:17,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:18,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:19,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:20,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:11,
                    title: '高新区2厦门市那萨克斯看' //三级菜单
                    //…… //以此类推，可无限层级
                }

            ]
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
        $("body").on("click", "#addActive", function() {
            // var dataInto=$(this).prev().attr("name");
            layer.open({
                type: 1,
                title: "选择",
                // area: '40%',
                content: $(".showChooseDiv"),
                maxmin: false,
                shadeClose: true,
                shade: false,
                area:['auto',"auto"],
                // maxHeight:'50%',
                offset: '100px'
            });
            $("#layui-layer-content").attr("height","auto");
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
<script>
    layui.use('table', function(){
        var id ='1';
        var table = layui.table;
        //第一个实例
        var tableIn = table.render({
            elem: '#demo'
            ,id:'list'
            // ,height: 'full-20'
            ,url: '<%=request.getContextPath()%>/PromotionController/queryPromotionList?id='+id //数据接口
            ,request: {
                pageName: 'pageIndex' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                ,statusCode: 10000 //规定成功的状态码，默认：0
                ,msgName: 'message' //规定状态信息的字段名称，默认：msg
                // ,countName: 'total' //规定数据总数的字段名称，默认：count
                // ,dataName: 'rows' //规定数据列表的字段名称，默认：data
            }
            ,page: true //开启分页
            ,cols: [[ //表头
                // {type:'checkbox',field: 'id', title: 'ID',  sort: true, fixed: 'left'}，
                 {field: 'activityCode', title: '促销编码', sort: true}
                ,{field: 'activityType', title: '活动类型' }
                ,{field: 'salesStartTime', title: '开始时间'}
                ,{field: 'salesEndTime', title: '结束时间',  sort: true}
                ,{fixed: 'right', align:'center', toolbar: '#opt'} //这里的toolbar值是模板元素的选择器
            ]]
        });
    });
</script>
</html>