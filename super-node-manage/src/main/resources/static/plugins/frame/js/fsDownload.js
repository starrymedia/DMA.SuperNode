/**
 * @Description: form表单工具
 * @Copyright: 2017 wueasy.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.8.0
 * @License：MIT
 */
layui.define(['layer', "fsCommon", "form", 'laydate', "fsConfig", 'layedit'], function (exports) {
    var layer = layui.layer,
        fsCommon = layui.fsCommon,
        laydate = layui.laydate,
        fsConfig = layui.fsConfig,
        layedit = layui.layedit,
        form = layui.form,
        statusName = $.result(fsConfig, "global.result.statusName", "errorNo"),
        msgName = $.result(fsConfig, "global.result.msgName", "errorInfo"),
        dataName = $.result(fsConfig, "global.result.dataName", "results.data"),
        successNo = $.result(fsConfig, "global.result.successNo", "0"),
        loadDataType = $.result(fsConfig, "global.loadDataType", "0");
    selectVals = {},//下拉框默认值
        Download = function () {
            this.config = {
                btnClassSelector: 'fsDownload'
                , formClassSelector: 'fsDownloadForm'
            }
        };
    var download = new Download();


    /**
     * 提交请求
     */
    Download.prototype.submitDownloadForm = function (btnSelector, formSelector) {

        btnSelector = btnSelector || download.config.btnClassSelector;
        formSelector = formSelector || download.config.formClassSelector;

        var $form = $(formSelector).submit().remove();

    };


    //绑定按钮
    exports("fsDownload", download);
});


