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
</head>

<body>
    <form action="" id="app">
        <div><span>促销编码</span> <input type="text" name=""  style="width: 110px;"> <span>活动类型</span> <select name="" id="" style="width: 100px;"><option value="">团购</option></select>
            <span>销售开始时间</span>
            <input type="text" id="test1">



            <span>销售结束时间</span>
            <input type="text" id="test2">
        </div>
        <div><span>每台现用张数/金额</span> <input type=" text " name=" "  style="width: 40px; text-align: center;">
            <span>回款周期</span>
            <select name=" " id=" " style="width: 100px; ">
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
            <textarea style="width: 895px; height: 55px; resize: none;">
            </textarea>
        </div>
        <div><span>七字描述</span> <input type="text " name=" "  style="width: 110px;"> <span>团购网站</span> <select name=" "  style="width: 100px;"><option value=" " >请选择</option></select> <span>核销开始时间</span> <input type="text " name=" "
                 style="width: 145px;"> <span>核销结束时间</span> <input type="text " name=" "  style="width: 145px;"></div>
        <div>
            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">与本活动共存的活动</h3>
            <textarea name=" "  style="width: 895px; height: 35px; resize: none;">
            </textarea>
        </div>
        <div><span>销售单价</span> <input type="text " name=" "  style="width: 110px;"> <span>回款单价</span> <input type="text " name=" "  style="width: 100px;margin-right: 62px;"> <span>手续费</span> <input type="text " name=" "
                  style="width: 145px; margin-right: 46px;"> <span>手续费率</span> <input type="text" name=" "   style="width: 145px;"></div>
        <div>
            <h3 style="font-size: 16px; font-weight: normal; margin: 0 0 3px;">其他</h3>
            <textarea name=""  style="width: 895px; height: 35px; resize: none;"></textarea>
        </div>
    </form>

</body>

<script src="${ctx}/static/laydate/laydate.js" type="text/javascript"></script>

<script type="text/javascript">
    var app = new Vue({
        el: "#app",
        data: {
            detail:{
                begain:"",
                begainTime:"",
                end:"",
                endTime:""
            }
        },
        methods: {
            clickBtn: function () {
                console.log(this.value1);
            }
        },
    });


    //日期控件
    var bMinDate;
    var defaultMinDate =   {
        year: 1900,
        month: 1,
        date:1,
        hours: 0,
        minutes: 0,
        seconds: 0
    };
    var aMaxDate;
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
        // ,closeStop: '#test1' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            var startTimestamp = new Date(value.replace(/-/g, "/"));
            var begainTime = new Date(startTimestamp).getTime();
            app.begain=value;
            app.begainTime=begainTime;
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
        // ,closeStop: '#test2' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
        ,done: function(value, date, endDate){
            var startTimestamp = new Date(value.replace(/-/g, "/"));
            var endTime = new Date(startTimestamp).getTime();
            app.end=value;
            app.endTime=endTime;
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

</script>
</html>