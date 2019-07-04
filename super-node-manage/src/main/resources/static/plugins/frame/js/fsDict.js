/**
 * @Description: 字典配置
 * @Copyright: 2017 wueasy.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.8.2
 * @License：MIT
 */
layui.fsDict = {
    //性别
    sex: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: "",//展示多个数据分隔符，默认,
        data: [{"code": "男", "name": "男"},
            {"code": "女", "name": "女"}]
    }
    , isSend: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: "",
        data: [{"code": "true", "name": "已发放"}, {"code": "false", "name": "未发放"}]
    }
    ,
    //角色
    role: {
        formatType: "server",
        loadUrl: "/sys/core/role/all.json",
        method: "post",
        inputs: "id:1",
        labelField: "name",
        valueField: "id"
    },
    //菜单父级
    menu: {
        formatType: "server",
        loadUrl: "/sys/core/menu/all.json",
        method: "post",
        inputs: "id:-1",
        labelField: "name",
        valueField: "id"
    },
    //主要的分类
    mainCategory: {
        formatType: "server",
        loadUrl: "/sys/product/category/mainCategory.json",
        method: "post",
        inputs: "productTypeId:1",
        labelField: "cateName",
        valueField: "id"
    },
    productType: {
        formatType: "server",
        loadUrl: "/sys/product/type/all.json",
        method: "post",
        inputs: "id:",
        labelField: "typeName",
        valueField: "id"
    },
    productTag: {
        formatType: "server",
        loadUrl: "/sys/product/tag/all.json",
        method: "post",
        labelField: "typeName",
        valueField: "id"
    }
    //商品颜色
    , color: {
        formatType: "local",
        labelField: "name",
        valueField: "id",
        //静态数据
        data: [
            {"id": 1, "name": "红色"},
            {"id": 2, "name": "黄色"},
            {"id": 3, "name": "黑色"},
            {"id": 4, "name": "白色"}
        ]
    } //商品尺寸
    , size: {
        formatType: "local",
        labelField: "size",
        valueField: "id",
        //静态数据
        data: [
            {"id": 1, "size": "s"},
            {"id": 2, "size": "m"},
            {"id": 3, "size": "l"},
            {"id": 4, "size": "xl"},
            {"id": 5, "size": "xxl"},
            {"id": 6, "size": "xxl"}
        ]
    }
    //属性值类型（1文本,2单选,3多选,4图片（单选））
    , attrValueType: {
        formatType: "local",
        labelField: "attrValueType",
        valueField: "id",
        //静态数据
        data: [
            {"id": 1, "attrValueType": "文本"},
            {"id": 2, "attrValueType": "单选"},
            {"id": 3, "attrValueType": "多选"},
            {"id": 4, "attrValueType": "图片（单选）"},
        ]
    }

    //物流公司
    , logisticsCompany: {
        formatType: "server",
        loadUrl: "/sys/logisticsCompany/enabledAll.json",
        method: "post",
        labelField: "cnName",
        valueField: "id"
    }

    , area: {
        formatType: "server",
        loadUrl: "/sys/area/findByDictCode.json",
        method: "post",
        inputs: "dictCode:0",
        labelField: "areaName",
        valueField: "id"
    },
    province: {
        formatType: "server",
        loadUrl: "/sys/area/findByDictCode.json",
        method: "post",
        inputs: "dictCode:1",
        labelField: "areaName",
        valueField: "id"
    },
    chainList: {
        formatType: "server",
        loadUrl: "/sys/chain/all.json",
        method: "post",
        labelField: "name",
        valueField: "id"
    },
    dataMap: {
        formatType: "server",
        loadUrl: "/dataMap/dataList.json",
        method: "post",
        labelField: "desc",
        valueField: "key"
    },


};
