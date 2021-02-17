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
        
        div {
            margin-bottom: 10px;
        }
        
        form {
            margin-top: 30px;
            margin-left: 50px;
        }
    </style>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/bootstrap-select.min.css">

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
            <button type="button" class="btn btn-default" @click="save()">保存</button>
        </div>
    </form>
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
                saleBegain:"",
                saleBegainTime:"",
                saleEnd:"",
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
</html>