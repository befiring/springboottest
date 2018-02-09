var AjaxRequestParam = {
    asmx: "http://localhost:8080/user/",
    type: "post",
    dataType: "json",
    async: false, //是否异步
    isAutoShowMessage: true, //是否自动显示错误信息
    beforeSend: undefined, //发起请求之前的事件
    error: undefined, //执行失败的回调
    success: undefined, //执行成功的回调
    complete: undefined //执行完成的回调（无论是否成功）
}

//ajax请求 //TODO:重载几个方法
var AjaxRequest = function (method, dataParameter, opts) {
    //首先判定是否有传入其他参数
    var otherPropertys = opts ? opts : AjaxRequestParam;
    var value;
    $.ajax({
        type: otherPropertys.type,
        url: otherPropertys.asmx + method,
        data: dataParameter,
        dataType: otherPropertys.dataType,
        async: otherPropertys.async,
        scriptCharset: "utf-8", //解决返回中文乱码
        beforeSend: function () {
            //if (method !== "CheckUserPassWord") {
            //    CheckCookieTimeOut();
            //}
            if (otherPropertys.beforeSend) {
                otherPropertys.beforeSend();
            }
        },
        success: function (data) {
            value = data;
            //            if (data.IsSuccess) {
            //                if (otherPropertys.success) {
            //                    otherPropertys.success();
            //                }
            //            } else {
            //                if (data.Err === "会话超时") {
            //                    QuitToLogin();
            //                    return;
            //                }
            //                if (otherPropertys.isAutoShowMessage) {
            //                    //ShowMessage(data.Err, "错误", "error");
            //                }
            //                if (otherPropertys.error) {
            //                    otherPropertys.error();
            //                }
            //            }
        },
        error: function (err) {
            if (otherPropertys.isAutoShowMessage) {
                ShowMessage("请求数据失败：" + err.responseText, "错误", "error");
            }
            if (otherPropertys.error) {
                otherPropertys.error();
            }
        },
        complete: function () {
            if (otherPropertys.complete) {
                otherPropertys.complete();
            }
        }
    });
    return value;
};
