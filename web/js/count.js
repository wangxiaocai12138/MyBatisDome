/*统计页面访问人数*/
$(function () {
    var path = $("#path").val();
    $.ajax({
        type:"GET",//请求类型
        url:path+"/count.do",//请求的url
        /*data:{method:"getproviderlist"},*///请求参数
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function (data) {
            if (data!=null){
                $("#count").html("页面访问人数："+data.sumCount);
            }
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            $("#count").html("页面访问人数：统计失败~请联系管理员");
        }
    });
})

/*
$.ajax({
    type:"GET",//请求类型
    url:path+"/jsp/bill.do",//请求的url
    data:{method:"getproviderlist"},//请求参数
    dataType:"json",//ajax接口（请求url）返回的数据类型
    success:function(data){//data：返回数据（json对象）
        if(data != null){
            $("select").html("");//通过标签选择器，得到select标签，适用于页面里只有一个select
            var options = "<option value=\"0\">请选择</option>";
            for(var i = 0; i < data.length; i++){
                //alert(data[i].id);
                //alert(data[i].proName);
                options += "<option value=\""+data[i].id+"\">"+data[i].proName+"</option>";
            }
            $("select").html(options);
        }
    },
    error:function(data){//当访问时候，404，500 等非200的错误状态码
        validateTip(providerId.next(),{"color":"red"},imgNo+" 获取供应商列表error",false);
    }
});*/
