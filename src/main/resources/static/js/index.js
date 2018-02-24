$().ready(function () {
    $('#dg').datagrid({
        //method    类型string    请求远程数据的方法（method）类型。    默认值为post
        method: 'get',
        //url    类型string    从远程站点请求数据的 URL。
        url: 'http://localhost:8080/user/all',
        fit: true,
        fitColumns: true,
        //rownumbers    类型boolean    设置为 true，则显示带有行号的列。 默认值为false
        //rownumbers: true,
        pageNumber: 1,
        columns: [[
            {
                field: 'id',
                title: 'ID',
                width: 50
        },
            {
                field: 'name',
                title: 'Name',
                width: 50
        },
            {
                field: 'password',
                title: 'Password',
                width: 50
        },
        ]],
        //queryParams    类型object 当请求远程数据时，发送的额外参数。
        //    queryParams: {
        //        method: 'getJianKongFileDetail',
        //        type: encodeURI(_e.text())
        //    },
        //当请求远程数据时，发送的额外参数。 返回要显示的过滤数据。该函数有一个参数 'data' ，表示原始数据。您可以把原始数据变成标准数据格式。该函数必须返回标准数据对象，含有 'total' 和 'rows' 属性。
        loadFilter: function (data) {
            return loadFilter(data); //自定义过滤方法         
        }
        //        onBeforeSelect: function (rowIndex, rowData) {
        //            return false;
        //        }
    });
});




function loadFilter(originalData) {
    //    if (typeof data.length == 'number' && typeof data.splice == 'function') { // 判断数据是否是数组
    var newData;
    if (originalData.success) {
        newData = {
            total: originalData.data.length,
            rows: originalData.data
        }
    }
    //        data = {
    //            total: data.length,
    //            rows: data
    //        }
    //    }
    //    var dg = $(this);
    //    var opts = dg.datagrid('options');
    //    var pager = dg.datagrid('getPager');
    //    pager.pagination({
    //        onSelectPage: function (pageNum, pageSize) {
    //            opts.pageNumber = pageNum;
    //            opts.pageSize = pageSize;
    //            pager.pagination('refresh', {
    //                pageNumber: pageNum,
    //                pageSize: pageSize
    //            });
    //            dg.datagrid('loadData', data);
    //        }
    //    });
    //    if (!data.originalRows) {
    //        data.originalRows = (data.rows);
    //    }
    //    var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
    //    var end = start + parseInt(opts.pageSize);
    //    data.rows = (data.originalRows.slice(start, end));
    return newData;
}
