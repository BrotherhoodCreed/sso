<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改堂食活动</title>

    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">

    <style>
        .layui-form-label {
            width: 105px;
            padding: 9px 10px;
        }
        .layui-input-block {
            margin-left: 130px;
        }
        .layui-form-item{
            margin-top: 10px;
        }
        .layui-form-label.layui-required:after{
            content:"*";
            color:red;
            padding-left:4px;
        }
    </style>
<body>
<form class="layui-form" style="font-size:3px"  action="" id="app">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">促销编码</label>
            <div class="layui-input-inline">
                <input type="text" readonly="readonly" v-model="detail.activityCode" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required   ">活动类型</label>
            <div class="layui-input-inline">
                <select id="activityTypes">
                    <option value="">请选择</option>
                <%--<option  v-bind:value="item.descriptionCode"  v-for="item in activityType" >{{item.description}}</option>--%>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required" pane>销售开始时间</label>
            <div class="layui-input-block">
                <input type="text" style="width:190px" name="date" id="test1" autocomplete="off" class="layui-input"  v-model="detail.salesStartTime">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required">销售结束时间</label>
            <div class="layui-input-block">
                <input type="text" style="width:190px" name="date" id="test2" autocomplete="off" class="layui-input" v-model="detail.salesEndTime">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label layui-required">回款周期</label>
            <div class="layui-input-inline">
                <select name="modules" lay-verify="required" lay-search="" id="billCycle">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label layui-required" style="width: auto;">每台限用张数/金额</label>
            <div class="layui-input-inline">
                <input type="text" name="username" @input="amountChange"  v-model="detail.amount" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label layui-required">键位名称</label>
        <div class="layui-input-block">
            <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" style="width:90%" v-model="detail.introduction"  @input="textChange">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label layui-required">活动描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" v-model="detail.description" style="width:90%" ></textarea>
        </div>
    </div>

    <div class="layui-form-item">
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label">七字描述</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" v-model="detail.introduction">--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label layui-required">核销开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="date" id="test3" lay-verify="date"  autocomplete="off" class="layui-input" v-model="detail.usageStartTime">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required">核销结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="date" id="test4" lay-verify="date"  autocomplete="off" class="layui-input" v-model="detail.usageEndTime">
            </div>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label ">约定售卖份数</label>
            <div class="layui-input-inline">
                <input type="text" name="contractAmount" lay-verify="contractAmount" autocomplete="off" class="layui-input"  v-model="detail.contractAmount" @input="valueChange">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label layui-required">与本活动共存活动</label>
        <div class="layui-input-block">
            <input type="text" name="identity" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input" style="width: 90%" v-model="detail.sharedActivity">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label layui-required">销售单价</label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"  v-model="detail.sellingPrice" @input="sellingPriceChange">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required">回款单价</label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"   v-model="detail.billPrice" @input="billPricePriceChange">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label layui-required">手续费</label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"  v-model="detail.handlingFee" @input="handlingFeeChange">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手续费率(%)</label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"  v-model="detail.taxRate" @input="taxRateChange">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">其他</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" v-model="detail.other"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-setval" @click="edit()">修改活动</button>
            <button type="button" class="layui-btn layui-btn-normal" id="addActive">添加门店</button>
            <button type="button" class="layui-btn"    @click="toList()">返回列表</button>
        </div>
    </div>
</form>
    <table id="demo" lay-filter="test"></table>

    <div class="showChooseDiv" style="display: none;  padding: 20px">
        <div id="dept_main" style="margin-right: 2%;">
                <span>
                    <input type="text" id="search" style="width: 70%;border: 1px solid gray; border-radius: 4px; outline: none;"/>
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
<script type="text/html" id="opt">
    <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${ctx}/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="${ctx}/static/laydate/laydate.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/layer/layer.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/layui/layui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-select.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<script type="text/javascript">;
//日期控件
var myDate = new Date();
var defaultMinDate ={
    year: myDate.getFullYear(),
    month: myDate.getMonth(),
    date:myDate.getDate()+1,
    hours: 0,
    minutes: 0,
    seconds: 0
};
var defaultMaxDate = {
    year: 2222,
    month: 1,
    date:1,
    hours: 0,
    minutes: 0,
    seconds: 0
};
var aMaxDate = defaultMaxDate;
var aMinDate = defaultMinDate;
var bMinDate = defaultMinDate;
var bMaxDate = defaultMaxDate;
var cMaxDate = defaultMaxDate;
var cMinDate = defaultMinDate;
var dMinDate = defaultMinDate;
var dMaxDate = defaultMaxDate;


var a = laydate.render({
    elem: '#test1' //指定元素
    ,show: false //直    接显示
    ,trigger: 'click' //采用click弹出
    ,btns: ['clear', 'confirm']
    ,min:1
    ,max:30
    ,done: function(value, date, endDate){
        app.detail.salesStartTime=value;
        if (value == '' || value == undefined){
            bMinDate = defaultMinDate;
            c.config.min=defaultMinDate;
        }else {
            const obj3 = JSON.parse(JSON.stringify(date));
            date.month = date.month-1;
            date.date = date.date+1;
            bMinDate = date;
            b.config.min=bMinDate;
            b.config.max=bMaxDate;

            obj3.month = obj3.month-1;
            obj3.date=obj3.date;
            cMinDate = obj3;
            c.config.min=cMinDate;
        }
    }
});
//
// lay('#test1').on('click', function(e){
//     a.config.max=aMaxDate;
//     a.config.min=aMinDate;
// });


var b = laydate.render({
    elem: '#test2' //指定元素
    ,show: false //直接显示
    ,trigger: 'click' //采用click弹出
    ,btns: ['clear', 'confirm']
    // ,closeStop: '#test2' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
    ,done: function(value, date, endDate){
        app.detail.salesEndTime=value;
        if (value == '' || value == undefined){
            aMaxDate = defaultMaxDate
        }else {
            date.month = date.month-1
            aMaxDate = date;
            a.config.max=aMaxDate;
            a.config.min=aMinDate;
        }
    }
});

// lay('#test2').on('click', function(e){
//     if(bMinDate == undefined || bMinDate == ''){
//         b.config.min=defaultMinDate;
//     }else {
//         b.config.min=bMinDate;
//         b.config.max=bMaxDate;
//
//     }
//
// });

var c = laydate.render({
    elem: '#test3' //指定元素
    ,show: false //直    接显示
    ,trigger: 'click' //采用click弹出
    ,btns: ['clear', 'confirm']
    ,min:1
    // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
    ,done: function(value, date, endDate){
        app.detail.usageStartTime=value;
        if (value == '' || value == undefined){
            dMinDate = defaultMinDate
        }else {
            date.month = date.month-1;
            dMinDate = date;
            d.config.min=dMinDate;
            d.config.max=dMaxDate;
        }
    }
});

// lay('#test3').on('click', function(e){
//     c.config.max=cMaxDate;
//     c.config.min=cMinDate;
// });

var d = laydate.render({
    elem: '#test4' //指定元素
    ,show: false //直    接显示
    ,trigger: 'click' //采用click弹出
    ,btns: ['clear', 'confirm']
    ,min:1
    // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
    ,done: function(value, date, endDate){
        app.detail.usageEndTime=value;
        if (value == '' || value == undefined){
            cMaxDate = defaultMinDate
        }else {
            date.month = date.month-1;
            cMaxDate = date;
            c.config.max=cMaxDate;
            c.config.min=cMinDate;
        }
    }
});

// lay('#test4').on('click', function(e){
//     if(dMinDate == undefined || dMinDate == ''){
//         d.config.min=defaultMinDate;
//     }else {
//         d.config.min=dMinDate;
//         d.config.max=dMaxDate;
//     }
// });


    var app = new Vue({
        el: "#app",
        data: {
            detail:{
                id:'',
                activityCode:'${activeCode}',
                activityType:"",
                amount:"",
                billCycle:"",
                description:"",
                introduction:"",
                channel:'',
                theWay:'',
                salesStartTime:"",
                salesEndTime:"",
                usageStartTime:"",
                usageEndTime:"",
                sharedActivity:[],
                other:"",
                handlingFee:"",
                taxRate:'',
                sellingPrice:'',
                billPrice:'',
                type:'2',
                contractAmount:'',
                prepaymentAmount:'',
                wage:'',
                advertisingFee:'',
                testFee:'',
                couponFee:'',
                couponEffectiveTime:'',
                discountFee:'',
                billUserName:"",
                billAccountNumber:""
            },
            promotionMapper : [],
            activityType:[],
            billCycles:[],
            channel:[],
            items:[]
        },
        created:function(){
            layui.use('form', function () {
                         var form = layui.form;
                         form.render('select');
                     });
            this.queryRelActive();
            // this.query("channel");
            this.queryDetail();
            // $('.selectpicker').selectpicker('refresh');     //设置好内容后刷新，  多用于异步请求
        },
        methods: {
            textChange(e){
                let  val = e.target.value;
                if(val.length>20){
                    this.detail.description = val.slice(0,20);
                }else {
                    this.detail.description =val;
                }
                this.detail.introduction=val.slice(0,20);
                //e.target.value=val.slice(0,20);
            },
            contractAmountChange:function (e){
                e.target.value = e.target.value.replace(/(^\s*)|(\s*$)/g, "");
                let reg = /[^\d.]/g;
                //约定售卖份数 不能有小数
                if("contractAmount"==e.target.name){
                    reg = /[^\d]/g;
                    e.target.value = e.target.value.replace(reg, "");
                    app.detail.contractAmount = e.target.value;
                    return;
                }
            },
            amountChange:function (e){
                this.detail.amount = this.valueChange(e);
            },
            sellingPriceChange:function (e){
                this.detail.sellingPrice = this.valueChange(e);
            },
            billPricePriceChange:function (e){
                this.detail.billPrice = this.valueChange(e);
            },
            handlingFeeChange:function (e){
                this.detail.handlingFee = this.valueChange(e);
            },
            taxRateChange:function (e){
                this.detail.taxRate = this.valueChange(e);
            },
            valueChange:function (e){
                e.target.value = e.target.value.replace(/(^\s*)|(\s*$)/g, "");
                let reg = /[^\d.]/g;
                //约定售卖份数 不能有小数
                if("contractAmount"==e.target.name){
                    reg = /[^\d]/g;
                    e.target.value = e.target.value.replace(reg, "");
                    app.detail.contractAmount = e.target.value;
                    return;
                }
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
                return e.target.value;
            },
            edit:function () {
                // var actives = ($(".selectpicker").val());
                if(this.detail.sharedActivity instanceof Array){
                }else {
                    this.detail.sharedActivity = [this.detail.sharedActivity];
                }
                this.detail.activityType = $("#activityTypes").val();
                this.detail.billCycle = $("#billCycle").val();
                if (this.detail.activityCode == ''){
                    layer.msg('促销编码为空');
                    return;
                }
                if (this.detail.activityType == ''){
                    layer.msg('活动类型为空');
                    return;
                }
                if (this.detail.salesStartTime == ''){
                    layer.msg('销售开始时间为空');
                    return;
                }
                if (this.detail.salesEndTime == ''){
                    layer.msg('销售结束时间为空');
                    return;
                }
                //
                // var startD = new Date(Date.parse(this.detail.salesStartTime.replace(/-/g,"/")));
                // var endD   = new Date(Date.parse(this.detail.salesEndTime.replace(/-/g,"/")));
                // var days = parseInt((endD.getTime()-startD.getTime()) / (1000 * 60 * 60 * 24));
                // if(days > 30){
                //     alert("日期范围应在一个月之内");
                //     return false;
                // }
                var startTimestamp = new Date(this.detail.salesStartTime.replace(/-/g, "/"));
                var saleBegainTime = new Date(startTimestamp).getTime();
                var endTimestamp = new Date(this.detail.salesEndTime.replace(/-/g, "/"));
                var saleEndTime = new Date(endTimestamp).getTime();


                var usageStartTime = new Date(this.detail.usageStartTime.replace(/-/g, "/"));
                var usageBegainTime = new Date(usageStartTime).getTime();
                if (saleEndTime < saleBegainTime){
                    layer.msg('销售结束时间不能小于销售开始时间');
                    return;
                }
                var myDate=new Date();
                var days = parseInt((startTimestamp.getTime()-myDate.getTime()) / (1000 * 60 * 60 * 24));
                if(days > 30){
                    layer.msg('销售日期范围应在一个月之内');
                    return ;
                }
                if(usageBegainTime<startTimestamp){
                    layer.msg('核销开始时间不能小于销售开始时间');
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
                    layer.msg('键位名称为空');
                    return;
                }
                // if (this.detail.channel == ''){
                //     layer.msg('请选择团购网站');
                //     return;
                // }
                // if (app.detail.contractAmount == ''){
                //     layer.msg('约定售卖份数为空');
                //     return;
                // }
                if (this.detail.usageStartTime == ''){
                    layer.msg('核销开始时间为空');
                    return;
                }
                if (this.detail.usageEndTime == ''){
                    layer.msg('核销结束时间为空');
                    return;
                }
                if (this.detail.sharedActivity == ''){
                    layer.msg('与本活动共存活动为空');
                    return;
                }
                // if (this.detail.sharedActivity == ''){
                //     layer.msg('请选择共存活动');
                //     return;
                // }
                if (this.detail.sellingPrice+'' == ''){
                    layer.msg('销售价为空');
                    return;
                }
                if (this.detail.billPrice+'' == ''){
                    layer.msg('回款单价为空');
                    return;
                }
                if (this.detail.handlingFee+'' == ''){
                    layer.msg('手续费为空');
                    return;
                }
                // if (this.detail.taxRate == ''){
                //     layer.msg('手续费率为空');
                //     return;
                // }
                var param = {
                    promotionBaseInfoDo:this.detail
                };
                this.$http.post('<%=request.getContextPath()%>/PromotionController/updatePromotionBaseInfo',
                    JSON.stringify(param),{emulateJSON:false}).then(function(response) {
                        if(10000 == response.data.code){
                            layer.msg('修改成功');
                        }else {
                            layer.msg('修改失败');
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            saveMerch:function () {
                var param = {
                    promotionMapperDos:this.promotionMapper
                };
                this.$http.post('<%=request.getContextPath()%>/PromotionController/savePromotionMapperInfo',
                JSON.stringify(param),{emulateJSON:false}).then(function(response) {
                    if(10000 == response.data.code){
                        layer.msg('保存成功');
                        location.reload();
                    }else {
                        layer.msg('保存失败');
                    }
                }, function(response) {
                    console.log("网络异常");
                });
            },
            query:function (type) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        if('eat_in_type' == type){
                            this.activityType = response.data.data;
                            $.each(this.activityType, function(index, item) {
                                //赋值
                                if(item.descriptionCode ==  app.detail.activityType ){
                                    $('#activityTypes').append(new Option( item.description,item.descriptionCode,true,true));
                                }else {
                                    $('#activityTypes').append(new Option( item.description,item.descriptionCode));

                                }
                            });
                            layui.form.render("select");
                        }else if('channel' == type){
                            this.channel = response.data.data;
                        }else if('bill_cycle_type' == type){
                            this.billCycles = response.data.data;
                            $.each(this.billCycles, function(index, item) {
                                if(item.descriptionCode ==  app.detail.billCycle ){
                                    $('#billCycle').append(new Option( item.description,item.descriptionCode,true,true));

                                }else{
                                    $('#billCycle').append(new Option( item.description,item.descriptionCode));
                                }
                            });
                            layui.form.render("select");
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            queryRelActive:function () {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/activeList',{},{emulateJSON:true}).then(function(response) {
                        this.items = response.data;
                        var html = "";
                        for (var i = 0; i < this.items.length; i++) {
                            html += "<option value='" +this.items[i].id+ "'>" +this.items[i].name+"</option>";
                        }
                        $('#id_select').html(html);
                        $('#id_select').selectpicker('refresh');     //设置好内容后刷新，  多用于异步请求
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            queryDetail:function () {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryPromotionBaseInfo',{activityCode:this.detail.activityCode},{emulateJSON:true}).then(function(response) {
                        console.log(response.data);
                        if (10000 == response.data.code){
                            this.detail = response.data.data;
                            var salesEnd = new Date(this.detail.salesEndTime);
                            var aDefaultMaxDate ={
                                year: salesEnd.getFullYear(),
                                month: salesEnd.getMonth(),
                                date:salesEnd.getDate(),
                                hours: 0,
                                minutes: 0,
                                seconds: 0
                            };
                            a.config.max = aDefaultMaxDate;
                            var salesStart = new Date(this.detail.salesStartTime);
                            var bDefaultMinDate ={
                                year: salesStart.getFullYear(),
                                month: salesStart.getMonth(),
                                date:salesStart.getDate(),
                                hours: 0,
                                minutes: 0,
                                seconds: 0
                            };
                            b.config.min = bDefaultMinDate;

                            var usageEnd = new Date(this.detail.usageEndTime);
                            var cQueryMaxDate ={
                                year: usageEnd.getFullYear(),
                                month: usageEnd.getMonth(),
                                date:usageEnd.getDate(),
                                hours: 0,
                                minutes: 0,
                                seconds: 0
                            };
                            c.config.max = cQueryMaxDate;

                            var usageStart = new Date(this.detail.usageStartTime);
                            var dQueryMinDate ={
                                year: usageStart.getFullYear(),
                                month: usageStart.getMonth(),
                                date:usageStart.getDate(),
                                hours: 0,
                                minutes: 0,
                                seconds: 0
                            };
                            d.config.min = dQueryMinDate;
                            // for (var i = 0; i < this.detail.sharedActivity.length; i++) {
                            //     $('#id_select').selectpicker('val',this.detail.sharedActivity);
                            //     $('#id_select').selectpicker('refresh');     //设置好内容后刷新，  多用于异步请求
                            // }
                            //等待页面渲染完毕在加载下拉选
                            this.query("eat_in_type");
                            this.query("bill_cycle_type");
                        }else {
                            layer.msg('查询异常');
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            billPricechangeCount:function(type){
                if(1==type){
                    if (this.detail.billPrice != '' && this.detail.handlingFee != '') {
                        this.detail.taxRate = parseFloat(this.detail.handlingFee) / parseFloat(this.detail.billPrice)*100;
                        this.detail.taxRate = Math.round(this.detail.taxRate*100)/100;
                    }
                }
                if(2==type){
                    if (this.detail.billPrice != '' && this.detail.taxRate != '') {
                        this.detail.handlingFee = parseFloat(this.detail.billPrice) * parseFloat(this.detail.taxRate) * 0.01;
                        this.detail.handlingFee = Math.round(handlingFee*100)/100;
                    }
                }
            },
            toList:function(){
                    location.href="<%=request.getContextPath()%>/hall/list";
            },
        }
    });

    $(window).on('load', function () {
        $('.selectpicker').selectpicker();
    });




</script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        var tableIn = table.render({
            elem: '#demo'
            ,id:'list'
            ,height: '350'
            ,url: '<%=request.getContextPath()%>/PromotionController/queryShopBind?activityCode='+app.detail.activityCode //数据接口
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
            ,page: false //开启分页
            ,cols: [[ //表头
                // {type:'checkbox',field: 'id', title: 'ID',  sort: true, fixed: 'left'}
                {field: 'area', title: '区域', sort: true}
                ,{field: 'city', title: '城市' }
                ,{field: 'restaurantCode', title: '门店编号'}
                ,{field: 'restaurantName', title: '门店名称',  sort: true}
                ,{field: 'billAccountNumber', title: '回款人账号',  sort: true}
                ,{field: 'billDepositBank', title: '回款银行',  sort: true}
                ,{field: 'billUserName', title: '回款人姓名',  sort: true}
                ,{fixed: 'right', align:'center', toolbar: '#opt'} //这里的toolbar值是模板元素的选择器
            ]]
        });

        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            layer.confirm('确定要删除吗', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                if(layEvent === 'del'){ //查看
                    $.ajax({
                        url : '<%=request.getContextPath()%>/PromotionController/deletePromotionRel?id='+data.id, //数据接口
                        dataType : 'json',
                        type : 'GET',
                        async : false,
                        success: function(resut){
                            console.log(resut)
                            if(10000 == resut.code){
                                layer.msg('删除成功');
                                obj.del();
                            }
                        }
                    });
                }
            });

        });
    });
</script>

<script>
    var str = [{
        id:1,
        title: '江西' //一级菜单
        ,children: [{
            id:3,
            title: '南昌' //二级菜单,
            ,children: [{
                id:4,
                title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                //…… //以此类推，可无限层级
            },
                {
                    id:7,
                    title: '高新区刷刷那是的你妈山姆大叔的母亲美味妈妈实打实的' //三级菜单
                    ,check:true
                    ,spread:true
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
                    // restaurantName: '高新区', //三级菜单
                    restaurantCode: '高新区' //三级菜单
                    //…… //以此类推，可无限层级
                },
                {
                    id:14,
                    // restaurantName: '高新区', //三级菜单
                    restaurantCode: '高新区' //三级菜单
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
            data: getData().data,
            id: 'checkTree',
            showCheckbox: true,     //是否显示复选框
            onlyIconControl: true
        });
        function getData(){
            var data = [];
            var txt = $("#search").val();
            $.ajax({
                url :'<%=request.getContextPath()%>/PromotionController/queryTree',//后台数据请求地址
                dataType : 'json',
                type : 'GET',
                async : false,
                data: {shopName: txt,activityCode:app.detail.activityCode},
                success: function(resut){
                    data = resut;
                    console.log(data);
                }
            });
            return data;
        };


        $('#search').bind('keypress',function(event){
            if(event.keyCode == "13") {
                tree.reload('checkTree', {
                    elem: '#dept_tree',
                    data:  getData().data,
                    id: 'checkTree',
                    showCheckbox: true,     //是否显示复选框
                    onlyIconControl: true
                });
            }
        });
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
                area:['30%',"50%"],
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
                getCheckedId(getData);
                console.log(app.promotionMapper);
                layer.close(layer.index);
                app.saveMerch();
            }
            return false;
        });

        function getCheckedId(jsonObj) {
            var activityCode = app.detail.activityCode;
            //循环区域
            $.each(jsonObj, function (index, item) {
                var area = item.title;
                if(undefined == item.children){

                }else {
                    //循环城市
                    $.each(item.children, function (index, item1) {
                        var city = item1.title;
                        if(undefined == item1.children){

                        }else {
                            //循环门店
                            $.each(item1.children, function (index, item2) {
                                var data = {
                                    activityCode:activityCode,
                                    area : area,
                                    city : city,
                                    restaurantCode : item2.id,
                                    billUserName : item2.uid,
                                    billAccountNumber : item2.accountnumber,
                                    billDepositBank : item2.depositbank,
                                    restaurantName : item2.title
                                }
                                app.promotionMapper.push(data);
                            });
                        }
                    });
                }
            });
            return;
        }

    });

</script>
</html>