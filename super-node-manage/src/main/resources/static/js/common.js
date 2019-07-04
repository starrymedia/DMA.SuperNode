// 系统字典配置

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (!fmt) {
        fmt = 'yy/MM/dd hh:mm';
    }
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};



/**
 *
 * @param boll
 * @returns {boolean}
 */
function isBoll(boll) {
    return (boll === true || boll === false || boll === 'true' || boll === 'false');
}

/**
 *
 * @param string
 * @returns {boolean}
 */
function isBlank(string) {
    return (string === undefined || string === '' || string.length === 0)
}

/**
 *
 * @param string
 * @returns {boolean}
 */
function isNotBlank(string) {
    return !isBlank(string);
}

function cellFormatBoll(cellValue, trueValue, falseValue) {
    if (cellValue === true || cellValue === 'true') {
        return '<span class="label label-success radius">' + trueValue + '</span>';
    } else if (cellValue === false || cellValue === 'false') {
        return '<span class="label label-error radius">' + falseValue + '</span>';
    } else {
        return '<span class="label label-secondary radius">未知</span>';
    }
}

/**
 *  将数据库中的unix Time 转换为日期
 * @param cellValue
 */
function cellFormatUnixTime(cellValue) {
    return formatDateFromUnixTime(cellValue);
}


/**
 * format data from unixTime
 * @param unixTime
 * @returns {string}
 */
function formatDateFromUnixTime(unixTime) {
    if (unixTime && unixTime > 0) {
        return new Date(unixTime * 1000).format();
    }
    return '';
}

function formatDate(time) {
    if (!time) {
        return '';
    }
    var date = new Date(time);
    return date.format()
}

/**
 * 检测是否为中文，true表示是中文，false表示非中文
 * @param word
 * @returns {boolean}
 */

function isChinese(str) {
    var patrn = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
    if (!patrn.exec(str)) {
        return false;
    } else {
        return true;
    }
}


/**
 * 复制
 * @param obj
 */
function copy(obj) {
    var selector = obj || '.clipboard';
    var clipboard = new ClipboardJS(selector, {
        text: function () {
            var val = $(selector).val();
            if (val) {
                return val;
            } else {
                val = $(selector).text();
            }
            return val;
        }
    });

    clipboard.on('success', function (e) {
        layer.msg(window.Translator._tryTranslate('复制成功'));
        console.log('copy successful', e);
    });

    clipboard.on('error', function (e) {
        console.log(e);
    });
}
