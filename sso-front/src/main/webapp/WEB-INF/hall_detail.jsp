<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增堂食活动</title>

    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
    <style>
        .layui-form-label {
            width: 100px;
        }
        .layui-input-block {
            margin-left: 130px;
        }
        .layui-form-item{
            margin-top: 10px;
        }
    </style>

<body>
    <form class="layui-form" style="font-size:3px" action="" id="app">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">活动类型</label>
                <div class="layui-input-inline">
                    <select id="activityTypes">
                        <option value="">请选择</option>
                    <%--<option  v-bind:value="item.descriptionCode"  v-for="item in activityType" >{{item.description}}</option>--%>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" pane>销售开始时间</label>
                <div class="layui-input-block">
                    <input type="text" style="width:190px" name="date" id="test1" autocomplete="off" class="layui-input"  v-model="detail.salesStartTime">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">销售结束时间</label>
                <div class="layui-input-block">
                    <input type="text"  style="width:190px" name="date" id="test2" autocomplete="off" class="layui-input" v-model="detail.salesEndTime">
                </div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">回款周期</label>
                <div class="layui-input-inline">
                    <select name="modules" lay-verify="required" lay-search="" id="billCycle">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label" style="width: auto;">每台限用张数/金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" @input="valueChange"  v-model="detail.amount" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">活动简述</label>
            <div class="layui-input-block">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" v-model="detail.introduction" style="width: 90%"  @input="textChange">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">活动描述</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" style="width: 90%" v-model="detail.description" ></textarea>
            </div>
        </div>

        <div class="layui-form-item">
<%--            <div class="layui-inline">--%>
<%--                <label class="layui-form-label">七字描述</label>--%>
<%--                <div class="layui-input-inline">--%>
<%--                    <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input" v-model="detail.introduction">--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="layui-inline">
                <label class="layui-form-label">核销开始时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="date" id="test3" lay-verify="date"  autocomplete="off" class="layui-input" v-model="detail.usageStartTime">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">核销结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="date" id="test4" lay-verify="date"  autocomplete="off" class="layui-input" v-model="detail.usageEndTime">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">约定售卖份数</label>
                <div class="layui-input-inline">
                    <input type="text" name="contractAmount" lay-verify="contractAmount" autocomplete="off" class="layui-input"  v-model="detail.contractAmount" @input="contractAmountChange">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">与本活动共存活动</label>
            <div class="layui-input-block">
                <input type="text" name="identity" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input" style="width: 90%;" v-model="detail.sharedActivity">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">销售单价</label>
                <div class="layui-input-inline">
                    <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"  v-model="detail.sellingPrice" @input="sellingPriceChange">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">回款单价</label>
                <div class="layui-input-inline">
                    <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input"   v-model="detail.billPrice" @input="billPricePriceChange">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手续费</label>
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
                <textarea placeholder="请输入内容" class="layui-textarea" v-model="detail.other"  style="width: 90%" ></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal" id="addActive">添加门店</button>
            </div>
        </div>

    </form>
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
                billAccountNumber:"",
                thisTime:'${thisTime}'
            },
            promotionMapper : [],
            items:[],
            activityType:[],
            billCycles:[],
            channel:[],
            saveReturn:false
        },
        created:function(){
            this.queryRelActive();
            this.query("eat_in_type");
            this.query("channel");
            this.query("bill_cycle_type");

        },
        methods: {
            textChange(e){
                let  val = e.target.value;
                if(val.length>20){
                    this.detail.description = val.slice(0,20);
                }else {
                    this.detail.description =val;
                }
                e.target.value =val.slice(0,20);
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
            save:function () {
                // var actives = ($(".selectpicker").val());

                // var param = {
                //     promotionBaseInfoDo: this.detail,
                //     promotionMapperDo:this.promotionMapper
                // };
                // console.log(param);
                this.detail.promotionMapperDo = this.promotionMapper;
                this.$http.post('<%=request.getContextPath()%>/PromotionController/savePromotionBaseInfoTs',
                    JSON.stringify(this.detail),{emulateJSON:false}).then(function(response) {
                        if('10000' == response.data.code){
                            layer.msg('保存成功');
                            this.detail.activityCode = response.data.data.activityCode;
                            location.href='<%=request.getContextPath()%>/hall/list?id='+app.detail.activityCode;
                        }else {
                            layer.msg('保存失败');
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            <%--saveMerch:function () {--%>
                <%--var param = {--%>
                    <%--promotionMapperDos:this.promotionMapper--%>
                <%--};--%>
                <%--this.$http.post('<%=request.getContextPath()%>/PromotionController/savePromotionMapperInfo',--%>
                    <%--JSON.stringify(param),{emulateJSON:false}).then(function(response) {--%>
                        <%--if('10000' == response.data.code){--%>
                            <%--layer.msg('保存成功');--%>
                            <%--this.saveReturn = true;--%>
                            <%--location.href='<%=request.getContextPath()%>/PromotionController/list?id='+app.detail.activityCode;--%>
                        <%--}else {--%>
                            <%--layer.msg('保存失败');--%>
                        <%--}--%>
                    <%--},--%>
                    <%--function(response) {--%>
                        <%--console.log("网络异常");--%>
                    <%--});--%>
            <%--},--%>
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
            query:function (type) {
                this.$http.post('<%=request.getContextPath()%>/PromotionController/queryDictionary',{descriptionType:type},{emulateJSON:true}).then(function(response) {
                        if('eat_in_type' == type){
                            this.activityType = response.data.data;
                            $.each(this.activityType, function(index, item) {
                                $('#activityTypes').append(new Option( item.description,item.descriptionCode));
                            });
                            layui.form.render("select");
                        }else if('channel' == type){
                            this.channel = response.data.data;
                        }else if('bill_cycle_type' == type){
                            this.billCycles = response.data.data;
                            $.each(this.billCycles, function(index, item) {
                                $('#billCycle').append(new Option( item.description,item.descriptionCode));
                            });
                            layui.form.render("select");
                        }
                    },
                    function(response) {
                        console.log("网络异常");
                    });
            },
            billPricechangeCount:function(type) {
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
            }
        },
        //     handlingFeechangeCount:function (){
        //         //detail.billPrice 回款单价
        //         //detail.handlingFee  手续费
        //         //detail.taxRate  手续费率
        //         if(this.detail.billPrice!='' && this.detail.handlingFee!='' ){
        //             this.detail.taxRate= detail.billPrice /detail.handlingFee;
        //         }
        // },
    });


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
        ,done: function(value, date, endDate){
            app.detail.salesStartTime=value;
            if (value == '' || value == undefined){
                bMinDate = defaultMinDate;
            }else {
                date.month = date.month-1;
                bMinDate = date;
                b.config.min=bMinDate;
                b.config.max=bMaxDate;
            }
        }
    });

    // lay('#test1').on('click', function(e){
    //
    // });

    var b = laydate.render({
        elem: '#test2' //指定元素
        ,show: false //直接显示
        ,trigger: 'click' //采用click弹出
        ,btns: ['clear', 'confirm']
        ,min:1
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
    //
    // lay('#test3').on('click', function(e){
    //
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
    //
    //     }
    // });

    $(window).on('load', function () {
        $('.selectpicker').selectpicker({
            'selectedText': '请选择'
        });
    });
</script>

<script>
    var str = [{
        id:'',
        title: '江西' //一级菜单
        ,children: [{
            id:3,
            title: '南昌', //二级菜单,
            checked:true
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
            data:  getData().data,
            id: 'checkTree',
            showCheckbox: true,     //是否显示复选框
            onlyIconControl: true
        });
        function getData(shopName){
            var txt = $("#search").val();
            var data = [];
            $.ajax({
                url :'<%=request.getContextPath()%>/PromotionController/queryTree',//后台数据请求地址
                dataType : 'json',
                type : 'GET',
                async : false,
                data: {shopName: txt,activityCode:app.detail.activityCode},
                success: function(resut){
                    data = resut;
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
            if(app.detail.sharedActivity instanceof Array){
            }else {
                app.detail.sharedActivity = [app.detail.sharedActivity];
            }
            app.detail.activityType = $("#activityTypes").val();
            app.detail.billCycle = $("#billCycle").val();

            // if (app.detail.activityCode == ''){
            //     layer.msg('促销编码为空');
            //     return;
            // }
            if (app.detail.activityType == ''){
                layer.msg('活动类型为空');
                return;
            }
            if (app.detail.salesStartTime == ''){
                layer.msg('销售开始时间为空');
                return;
            }
            if (app.detail.salesEndTime == ''){
                layer.msg('销售结束时间为空');
                return;
            }
            var startTimestamp = new Date(app.detail.salesStartTime.replace(/-/g, "/"));
            var saleBegainTime = new Date(startTimestamp).getTime();
            var endTimestamp = new Date(app.detail.salesEndTime.replace(/-/g, "/"));
            var saleEndTime = new Date(endTimestamp).getTime();

            var usageStartTime = new Date(app.detail.usageStartTime.replace(/-/g, "/"));
            var usageBegainTime = new Date(usageStartTime).getTime();
            if (saleEndTime < saleBegainTime){
                layer.msg('销售结束时间不能小于销售开始时间');
                return;
            }
            var days = parseInt((endTimestamp.getTime()-startTimestamp.getTime()) / (1000 * 60 * 60 * 24));
            if(days > 30){
                layer.msg('销售日期范围应在一个月之内');
                return ;
            }
            if(usageBegainTime<startTimestamp){
                layer.msg('核销开始时间不能小于销售开始时间');
                return;
            }
            if (app.detail.amount == ''){
                layer.msg('每台现用张数/金额为空');
                return;
            }
            if (app.detail.billCycle == ''){
                layer.msg('回款周期为空');
                return;
            }
            if (app.detail.description == ''){
                layer.msg('活动描述为空');
                return;
            }
            if (app.detail.introduction == ''){
                layer.msg('七字描述为空');
                return;
            }
            // if (app.detail.channel == ''){
            //     layer.msg('请选择团购网站');
            //     return;
            // }
            if (app.detail.usageStartTime == ''){
                layer.msg('核销开始时间为空');
                return;
            }
            // if (app.detail.contractAmount == ''){
            //     layer.msg('约定售卖份数为空');
            //     return;
            // }
            if (app.detail.sharedActivity == ''){
                layer.msg('与本活动共存活动为空');
                return;
            }
            // if (app.detail.prepaymentAmount == ''){
            //     layer.msg('预付金额为空');
            //     return;
            // }
            // if (app.detail.wage == ''){
            //     layer.msg('人员费用为空');
            //     return;
            // }
            // if (app.detail.advertisingFee == ''){
            //     layer.msg('广告费用为空');
            //     return;
            // }
            // if (app.detail.testFee == ''){
            //     layer.msg('试吃费用为空');
            //     return;
            // }
            // if (app.detail.couponFee == ''){
            //     layer.msg('尊享券费用为空');
            //     return;
            // }
            // if (app.detail.couponEffectiveTime == ''){
            //     layer.msg('尊享券费有效期为空');
            //     return;
            // }
            // if (app.detail.discountFee == ''){
            //     layer.msg('折扣费用为空');
            //     return;
            // }

            if (app.detail.sellingPrice == ''){
                layer.msg('销售价为空');
                return;
            }
            if (app.detail.billPrice == ''){
                layer.msg('回款单价为空');
                return;
            }
            if (app.detail.handlingFee == ''){
                layer.msg('手续费为空');
                return;
            }
            // if (app.detail.taxRate == ''){
            //     layer.msg('手续费率为空');
            //     return;
            // }
            // var dataInto=$(this).prev().attr("name");
            // if(''==app.detail.activityCode){
            //     layer.msg("请先保存活动");
            //     return;
            // }
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
                app.save();
            }
            return false;
        });

        function getCheckedId(jsonObj) {
            var activityCode = app.detail.activityCode;
            app.promotionMapper=[];
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