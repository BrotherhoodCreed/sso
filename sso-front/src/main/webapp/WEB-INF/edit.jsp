<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改活动</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        input {
            margin-right: 14px;
            width: 130px;
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
        <table style=" width: 85%; max-width: 1000px; border-collapse: separate;border-spacing: 5px;">

            <tr>
                <td style="width: 150px">促销编码</td>
                <td>
                    <input type="number" v-model="detail.activityCode"  readonly="readonly" style="width: 130px;">
                </td>
                <td style="width: 120px">活动类型</td>
                <td>
                    <select v-model="detail.activityType"  style="width: 130px;">
                        <option value="" >请选择</option>
                        <option  v-bind:value="item.descriptionCode" v-for="item in activityType" >{{item.description}}</option>
                    </select>
                </td>
                <td  style="width: 120px">销售开始时间</td>
                <td> <input type="text" id="test1" v-model="detail.salesStartTime" autocomplete="off"></td>
                <td  style="width: 120px">销售结束时间</td>
                <td>   <input type="text" id="test2"  v-model="detail.salesEndTime" autocomplete="off"></td>
            </tr>
            <tr>
                <td >每台限用张数/金额</td>
                <td>
                    <input type="number" @input="valueChange"  v-model="detail.amount" style="width: 130px; text-align: center;">
                </td>
                <td>回款周期</td>
                <td>
                    <select v-model="detail.billCycle"  style="width: 130px; ">
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
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td>  </td>
            </tr>
            <tr>
                <td colspan="8">活动描述</td>
            </tr>
            <tr>
                <td colspan="8">
                    <textarea style="width: 100%; height: 55px; resize: none;" v-model="detail.description" @input="textChange" >
                    </textarea>
                </td>
            </tr>

            <tr>
                <td >七字描述</td>
                <td>
                    <input type="text " v-model="detail.introduction"  style="width: 100px;">
                </td>
                <td>团购网站</td>
                <td>
                    <select v-model="detail.theWay"  style="width: 100px;">--%>
                        <option value="" >请选择</option>
                        <option  v-bind:value="item.descriptionCode" v-for="item in channel" >{{item.description}}</option>
                    </select>
                </td>
                <td style="min-width: 120px">核销开始时间</td>
                <td> <input type="text" id="test3"  v-model="detail.usageStartTime" autocomplete="off"></td>
                <td style="min-width: 120px">核销结束时间</td>
                <td><input type="text" id="test4" v-model="detail.usageEndTime" autocomplete="off"></td>
            </tr>

            <tr>
                <td >销售单价</td>
                <td>
                    <input type="number" v-model="detail.sellingPrice" @input="valueChange"  @blur.native.capture="changeCount"  style="width: 100px;">
                </td>
                <td>回款单价</td>
                <td>
                    <input type="number " v-model="detail.billPrice" @input="valueChange" @blur.native.capture="billPricechangeCount(0)"  style="width: 100px;margin-right: 62px;">
                </td>
                <td>手续费</td>
                <td> <input type="number " v-model="detail.handlingFee" @input="valueChange"  @blur.native.capture="billPricechangeCount(1)" style="width:130px; margin-right: 46px;"></td>
                <td>手续费率(%)</td>
                <td><input type="number" v-model="detail.taxRate" @input="valueChange"   @blur.native.capture="billPricechangeCount(2)"   style="width: 130px;"></td>
            </tr>

            <tr>
                <td colspan="8">其他</td>
            </tr>
            <tr>
                <td colspan="8">
                    <textarea v-model="detail.other"  style="width: 100%; height: 55px; resize: none;"></textarea>
                </td>
            </tr>

        </table>
        <%--<div><span>促销编码</span> <input type="number" v-model="detail.activityCode"  readonly="readonly" style="width: 110px;">--%>
            <%--<span>活动类型</span>--%>
            <%--<select v-model="detail.activityType"  style="width: 100px;">--%>
                <%--<option value="" >请选择</option>--%>
                <%--<option  v-bind:value="item.descriptionCode" v-for="item in activityType" >{{item.description}}</option>--%>
            <%--</select>--%>
            <%--<span>销售开始时间</span>--%>
            <%--<input type="text" id="test1" v-model="detail.salesStartTime" autocomplete="off">--%>

            <%--<span>销售结束时间</span>--%>
            <%--<input type="text" id="test2"  v-model="detail.salesEndTime" autocomplete="off">--%>
        <%--</div>--%>
        <%--<div><span>每台现用张数/金额</span> <input type="number" @input="valueChange"  v-model="detail.amount" style="width: 5rem; text-align: center;">--%>
            <%--<span>回款周期</span>--%>
            <%--<select v-model="detail.billCycle"  style="width: 100px; ">--%>
                <%--<option value="1">T+1</option>--%>
                <%--<option value="2">T+2</option>--%>
                <%--<option value="3">T+3</option>--%>
                <%--<option value="4">T+4</option>--%>
                <%--<option value="5">T+5</option>--%>
                <%--<option value="6">T+6</option>--%>
                <%--<option value="7">T+7</option>--%>
                <%--<option value="8">T+8</option>--%>
                <%--<option value="9">T+9</option>--%>
                <%--<option value="10">T+10</option>--%>
                <%--<option value="11">T+11</option>--%>
                <%--<option value="12">T+12</option>--%>
                <%--<option value="13">T+13</option>--%>
                <%--<option value="14">T+14</option>--%>
                <%--<option value="15">T+15</option>--%>
            <%--</select>--%>
        <%--</div>--%>
<%--        <div>--%>
<%--            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">活动描述</h3>--%>
<%--            <textarea style="width: 895px; height: 55px; resize: none;" v-model="detail.description">--%>
<%--            </textarea>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <table style=" width: 70%; max-width: 1000px; border-collapse: separate;border-spacing: 5px;">--%>
<%--                <tr>--%>
<%--                    <td >七字描述</td>--%>
<%--                    <td>--%>
<%--                        <input type="text " v-model="detail.introduction"  style="width: 100px;">--%>
<%--                    </td>--%>
<%--                    <td>团购网站</td>--%>
<%--                    <td>--%>
<%--                     <select v-model="detail.theWay"  style="width: 100px;">&ndash;%&gt;--%>
<%--                         <option value="" >请选择</option>--%>
<%--                         <option  v-bind:value="item.descriptionCode" v-for="item in channel" >{{item.description}}</option>--%>
<%--                     </select>--%>
<%--                    </td>--%>
<%--                    <td>核销开始时间</td>--%>
<%--                    <td> <input type="text" id="test3"  v-model="detail.usageStartTime" autocomplete="off"></td>--%>
<%--                    <td>核销结束时间</td>--%>
<%--                    <td><input type="text" id="test4" v-model="detail.usageEndTime" autocomplete="off"></td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td >销售单价</td>--%>
<%--                    <td>--%>
<%--                        <input type="number" v-model="detail.sellingPrice" @input="valueChange"  @blur.native.capture="changeCount"  style="width: 100px;">--%>
<%--                    </td>--%>
<%--                    <td>回款单价</td>--%>
<%--                    <td>--%>
<%--                        <input type="number " v-model="detail.billPrice" @input="valueChange" @blur.native.capture="billPricechangeCount(0)"  style="width: 100px;margin-right: 62px;">--%>
<%--                    </td>--%>
<%--                    <td>手续费</td>--%>
<%--                    <td> <input type="number " v-model="detail.handlingFee" @input="valueChange"  @blur.native.capture="billPricechangeCount(1)" style="width:130px; margin-right: 46px;"></td>--%>
<%--                    <td>手续费率(%)</td>--%>
<%--                    <td><input type="number" v-model="detail.taxRate" @input="valueChange"   @blur.native.capture="billPricechangeCount(2)"   style="width: 130px;"></td>--%>
<%--                </tr>--%>

<%--            </table>--%>

<%--            <span>七字描述</span> <input type="text " v-model="detail.introduction" @input="valueChange" style="width: 110px;">--%>
<%--            <span>团购网站</span> <select v-model="detail.theWay"  style="width: 100px;">--%>
<%--                <option value="" >请选择</option>--%>
<%--                <option  v-bind:value="item.descriptionCode" v-for="item in channel" >{{item.description}}</option>--%>
<%--            </select>--%>
<%--            <span>核销开始时间</span>--%>
<%--            <input type="text" id="test3"  v-model="detail.usageStartTime" autocomplete="off">--%>
<%--            <span>核销结束时间</span>--%>
<%--            <input type="text" id="test4" v-model="detail.usageEndTime" autocomplete="off">--%>
<%--        </div>--%>
        <%--<div>--%>
            <%--<h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">与本活动共存的活动</h3>--%>
            <%--<label for="id_select"></label>--%>
            <%--<select id="id_select" class="selectpicker bla bla bli" multiple data-live-search="true">--%>
                <%--&lt;%&ndash;<option v-bind:value="item.id" v-for="item in items">{{item.id}}</option>&ndash;%&gt;--%>
                <%--</optgroup>--%>
            <%--</select>--%>
        <%--</div>--%>
<%--        <div><span>销售单价</span> <input type="number" v-model="detail.sellingPrice" @input="valueChange"  @blur.native.capture="changeCount"  style="width: 5rem;">--%>
<%--            <span>回款单价</span> <input type="number " v-model="detail.billPrice" @input="valueChange" @blur.native.capture="billPricechangeCount(0)"  style="width: 5rem;margin-right: 62px;">--%>
<%--            <span>手续费</span><input type="number " v-model="detail.handlingFee" @input="valueChange"  @blur.native.capture="billPricechangeCount(1)" style="width: 5rem; margin-right: 46px;">--%>
<%--            <span>手续费率(%)</span> <input type="number" v-model="detail.taxRate" @input="valueChange"   @blur.native.capture="billPricechangeCount(2)"   style="width: 10rem;">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">其他</h3>--%>
<%--            <textarea v-model="detail.other"  style="width: 895px; height: 35px; resize: none;"></textarea>--%>
<%--        </div>--%>

        <div>
            <button type="button" class="btn btn-default" @click="edit()">修改活动</button>
            <button type="button" class="btn btn-default" id="addActive" style="    margin-left: 3rem;">添加门店</button>
            <button type="button" class="btn btn-default" @click="toList()" style="    margin-left: 3rem;">返回列表</button>
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
                billPrice:''
            },
            promotionMapper : [],
            activityType:[],
            channel:[],
            items:[]
        },
        created:function(){
            this.queryRelActive();
            this.query("activity_type");
            this.query("channel");
            this.queryDetail();

            // $('.selectpicker').selectpicker('refresh');     //设置好内容后刷新，  多用于异步请求
        },
        methods: {
            textChange(e){
                let  val = e.target.value;
                if(val.length>7){
                    this.detail.introduction = val.slice(0,7)
                }else {
                    this.detail.introduction =val;
                }
            },
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
            edit:function () {
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
                if (this.detail.salesStartTime == ''){
                    layer.msg('销售开始时间为空');
                    return;
                }
                if (this.detail.salesEndTime == ''){
                    layer.msg('销售结束时间为空');
                    return;
                }
                var startTimestamp = new Date(this.detail.salesStartTime.replace(/-/g, "/"));
                var saleBegainTime = new Date(startTimestamp).getTime();
                var endTimestamp = new Date(this.detail.salesEndTime.replace(/-/g, "/"));
                var saleEndTime = new Date(endTimestamp).getTime();
                if (saleEndTime < saleBegainTime){
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
                if (this.detail.usageStartTime == ''){
                    layer.msg('核销开始时间为空');
                    return;
                }
                if (this.detail.usageEndTime == ''){
                    layer.msg('核销结束时间为空');
                    return;
                }

                // if (this.detail.sharedActivity == ''){
                //     layer.msg('请选择共存活动');
                //     return;
                // }
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
                        if('activity_type' == type){
                            this.activityType = response.data.data;
                        }else if('channel' == type){
                            this.channel = response.data.data;
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
                            // for (var i = 0; i < this.detail.sharedActivity.length; i++) {
                            //     $('#id_select').selectpicker('val',this.detail.sharedActivity);
                            //     $('#id_select').selectpicker('refresh');     //设置好内容后刷新，  多用于异步请求
                            // }

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
                    location.href="<%=request.getContextPath()%>/list";
            },
        }
    });

    $(window).on('load', function () {
        $('.selectpicker').selectpicker();
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
        ,min:0
        ,value: app.detail.salesStartTime
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.salesStartTime=value;
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
        ,min:0
        ,value: app.detail.salesEndTime
        ,btns: ['clear', 'confirm']
        // ,closeStop: '#test2' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.salesEndTime=value;
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
        ,min:0
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.usageStartTime=value;
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
        ,min:0
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            app.detail.usageEndTime=value;
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