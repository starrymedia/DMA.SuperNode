
package org.elastos.record.utility.denum;

public enum Action {
    DEFAULT(-1, ""),
    SUCCESS(0, "操作成功"),
    ERROR(1, "请求失败"),
    PARAMS_ERROR(2, "参数错误"),
    NO_RECORD(3, "无记录"),
    SYSTEM_ERROR(4, "系统异常"),
    METHOD_NOT_ALLOWED(5, "请求业务不允许"),
    UNAUTHORIZED(6, "未授权"),
    ASSETS_CANNOT_BE_EMPTY(7, "资产数量不能为空"),
    PRODUCT_NOT_EXISTS(8, "商品不存在"),
    PRODUCT_ADDRESS_EMPTY(9, "商品地址不能为空"),
    REPEAT_ADD(10, "重复添加"),
    SELL_EMPTY(11, "上架数量小于1"),
    UNKNOWN_OWNER(12, "钱包地址不能为空"),
    SOLD_OUT(13, "售空"),
    NAME_EMPTY(14, "名称不能为空"),
    SYMBOL_EMPTY(15, "简称不能为空"),
    TOKEN_ID_EMPTY(16, "tokenId不能为空"),
    PRICE_EMPTY(17, "价格不能为空"),
    ASSET_NOT_EXISTS(18, "资产不存在"),
    ORDER_NO_EMPTY(19, "订单号不能为空"),
    UNKNOWN_TO_OWNER(20, "toOwner不能为空"),
    ORDER_NOT_EXISTS(21, "订单号不存在"),
    ORDER_HAS_PROCESSED(22, "订单重复处理"),
    ASSET_ID_EMPTY(23, "资产编号不能为空"),
    TRADE_NO_IS_EMPTY(24, "成交号不能为空"),
    TRADE_STATUS_IS_EMPTY(25, "成交状态不能为空"),
    ETH_ADDRESS_ERROR(26, "ETH钱包地址错误"),
    DID_ADDRESS_ERROR(27, "DID地址错误"),
    ELA_ADDRESS_ERROR(28, "ELA钱包地址错误"),
    RPC_ERROR(29, "远程调用失败"),
    SYS_PRODUCT_TEMPLATE_NOT_EXISTS(30, "商品模板不存在"),
    DELETE_ERROR(31, "删除失败"),
    ADD_NULL_PRODUCT(32, "添加商品信息不能为空"),
    ADD_NULL_ASSET(33, "添加资产信息不能为空"),
    ADD_NULL_PRODUCT_TEMPLATE(34, "添加模板信息不能为空"),
    ADD_NULL_PRODUCT_TEMPLATE_TYPE(35, "添加商品模板类型不能为空"),
    SYS_PRODUCT_TEMPLATE_ID_EMPTY(36, "未指定商品模板id"),
    SYS_PRODUCT_TEMPLATE_TYPE_ID_EMPTY(37, "未指定商品模板类型id"),
    NOT_LOG_IN(41, "未登录"),
    PAR_INFO_NAME_EMPTY(42, "票面名称为空"),
    PAR_INFO_PRICE_EMPTY(43, "票面价格为空"),
    PRODUCT_ID_EMPTY(45, "未指定商品id"),
    BATH_ADD_TIME_INFO_EMPTY(46, "添加场次信息不能为空"),
    UPDATE_ERROR(41, "信息修改失败"),
    PAR_INFO_NOT_EXISTS(45, "票面不存在"),
    PAR_INFO_ID_EMPTY(46, "票档ID不能为空"),
    ORDER_CREATE_ERROR(48, "创建订单失败"),
    ORDER_TO_OWNER_EMPTY(49, "toOwner不能为空"),
    ORDER_STATUS_EMPTY(50, "订单状态不能为空"),
    ORDER_PRICE_EMPTY(51, "订单价格不能为空"),
    ORDER_NUMBER_DUPLICATE(52, "订单号重复"),
    PRODUCT_SUPPLY_LIMIT_EMPTY(53, "发行量能为空"),
    TIME_INFO_EMPTY(54, "场次信息不能为空"),
    TIME_INFO_NOT_EXISTS(55, "场次不存在"),
    PAR_INFO_EMPTY(56, "票面信息不能为空"),
    DELETE_SUCCESS(57, "删除成功"),
    UPDATE_SUCCESS(58, "修改成功"),
    BURN_INFO_ID_OR_TX_ID_EMPTY(59, "ID或者txId不能为空"),
    BURN_INFO_EMPTY(60, "销毁记录参数为空"),
    BURN_INFO_ID_EMPTY(61, "销毁记录ID参数不能为空"),
    LOGIN_SUCCESS(62, "登陆成功"),
    NO_DID_ERROR(63, "未导入DID账号"),
    DID_OR_PWD_ERROR(64, "DID账号或密码错误"),
    PRODUCT_DEPLOY_SUCCESS(65, "商品发布成功"),
    PRODUCT_DEPLOY_SUCCESS_SAVE_ERROR(66, "商品发布成功但是保存失败"),
    APPROVE_SUCCESS(67, "授权成功"),
    OVER_THE_TRANSFER_LIMIT(68, "超出转让次数限制"),
    ORDER_DETAILS_EMPTY(69, "订单明细信息不能为空"),
    ORDER_DETAILS_ID_EMPTY(70, "订单明细ID不能为空"),
    UNLOCK_COUNTDOWN_NOT_IN_EFFECT(71, "倒计时未生效"),
    REVOKE_ERROR(72, "下架失败"),
    ON_SALE_ERROR(73, "上架失败"),
    TASK_COMMITTED(74, "任务已提交"),
    SALE_STATUS_EMPTY(75, "售卖状态不能为空"),
    UNDERFINANCED(76, "金额不足"),
    ALREADY_USED(77, "已使用"),
    OWNER_NOT_EQ_PRODUCT_OWNER(78, "非商品拥有者"),
    OWNER_NOT_EQ_ASSET_OWNER(79, "非资产拥有者"),
    ASSET_NOT_EXISTS_ON_CHAIN(80, "链上不存在该资产"),
    PRICE_ERROR(81, "不合法的价格"),
    TRANS_ERROR(82, "转让失败"),
    START_TIME_EMPTY(83, "开始时间不能为空"),
    END_TIME_EMPTY(84, "结束时间不能为空"),
    START_SELL_TIME_EMPTY(85, "开始销售时间"),
    END_SELL_TIME_EMPTY(86, "结束销售时间"),
    END_TIME_INVALID(87, "结束时间不合法"),
    END_SELL_TIME_INVALID(88, "销售时间不合法"),
    PRE_ORDER_ERROR(89, "预下单失败"),
    ORDER_INFO_EMPTY(90, "订单信息不能为空"),
    ORDER_QUANTITY_EMPTY(91, "下单数量不能为空"),
    CONFIRM_ORDER_ERROR(92, "下单失败"),
    PRIVATE_KEY_EMPTY(93, "私钥不能为空"),
    GAS_PRICE_EMPTY(94, "gasPrice 不能为空"),
    GAS_LIMIT_EMPTY(95, "gasLimit 不能为空"),
    CREATE_ORDER_ERROR(96, "创建订单失败"),
    PRIVATE_KEY_ERROR(97, "私钥错误"),
    ID_EMPTY(98, "未能识别ID"),
    MINT_ERROR(99, "资产创建失败"),
    UNKNOWN_SHELF_TYPE(100, "上下架类型错误"),
    PRODUCT_DEPLOY_ERROR(101, "商品创建失败"),
    GAS_UNDERFINANCED(102, "gas费用不足"),
    PLATFORM_ADDRESS_EMPTY(103, "平台商品地址错误"),
    NODE_URL_EMPTY(104, "节点地址不能为空"),
    NODE_URL_ERROR(105, "节点地址错误"),
    ASSET_LEVEL_EMPTY(105, "一级市场和二手市场属性不能为空"),
    TRANSACTION_TYPE_EMPTY(106, "交易类型不能为空"),
    ORDER_FLAG_EMPTY(107, "订单Tag不能为空"),
    ASSET_TYPE_EMPTY(108, "资产类型不能为空"),
    COORDINATES_EMPTY(109, "经纬度不能为空"),
    MNEMONIC_EMPTY(110, "助记词不能为空"),
    CARRIER_ADDRESS_EMPTY(111, "carrier地址不能为空"),
    CARRIER_ADDRESS_ERROR(112, "carrier地址错误"),
    PASSWORD_ERROR(113, "密码错误"),
    USERNAME_ERROR(114, "用户名格式错误"),
    MOBILE_ERROR(115, "电话格式错误"),
    PAYMENT_MERCHANT_REGISTERED(116, "已注册"),
    WALLET_ADDRESS_EMPTY(117, "钱包地址不能为空"),
    PAYMENT_MERCHANT_ID_EMPTY(118, "支付商户号不能为空"),
    PAYMENT_MERCHANT_ID_NOT_EXISTS(119, "支付商户号不存在"),
    PAYMENT_SIGN_EMPTY(120, "验签字段不能为空"),
    PAYMENT_SIGN_INVALID(121, "验签不通过"),
    PASSWORD_ERROR_UNSAFE(122, "密码安全度太低"),
    EMAIL_INVALID(123, "邮箱格式错误"),
    ORDER_EXISTS(124, "订单号已存在"),
    ORDER_AMOUNT_ERROR(125, "订单金额错误"),
    SIDE_ERROR(127, "交易类型错误"),
    ORDER_TIME_OUT(128, "订单超时"),
    LOW_STOCK(129, "库存不足"),
    EXPRESS_TPL_ERROR(130, "手续费配置错误"),
    CREATE_TRUSTEE_ERROR(129, "创建托管账户错误"),
    SIGN_TRANSACT_ERROR(130, "签名交易错误"),
    TRUSTEE_ERROR(131, "托管账户错误"),
    TX_SIGN_ANALYSIS_ERROR(132, "交易签名解析错误"),
    TX_SIGN_ERROR(132, "交易签名错误"),
    GET_UTXO_ERROR(133, "获取交易UTXO错误"),
    GET_COMMISSIONS_ERROR(134, "获取佣金比率错误"),
    GET_TRUSTEE_ERROR(135, "获取账号错误"),
    COMMISSIONS_ADDRESS_ERROR(136, "分成账号地址错误错误"),
    COMMISSIONS_ERROR(137, "佣金比率错误"),
    COMMISSIONS_TRANSACT_ERROR(138, "分成转账失败"),
    MEMBER_NOT_EXISTS(139, "会员不存在"),
    UNSUPPORTED_DISTRIBUTION_AREA(141, "不支持的配送区域"),
    TX_ID_EMPTY(142, "txId不能为空"),
    TX_ID_ERROR(143, "txId错误"),
    PRODUCT_SKU_ERROR(144,"商品规格属性错误"),
    PRODUCT_SKU_ATTR_ERROR(145,"商品规格属性值错误"),
    STORE_INFORMATION_ERROR(144,"商户信息错误"),
    AREA_CODE_ERROR(145,"区域配置错误"),
    ORDER_INFO_ERROR(146,"订单信息错误"),
    CHAINID_EMPTY(147, "chainID不能为空"),
    KEY_EXISTS(148,"key已存在"),
    YSY_CONFIG_ERROR(149,"配置参数为错误"),
    ;

    private String code;
    private String msg;
    private String enMsg;

    private Action(int code, String msg) {
        this.code = code + "";
        this.msg = msg;
        this.enMsg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEnMsg() {
        return this.enMsg;
    }

    public String toString() {
        return "var Action {code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + '}';
    }
}
