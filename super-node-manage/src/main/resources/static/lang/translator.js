'use strict';

/**
 * Translator for documentation pages.
 *
 * To enable translation you should include one of language-files in your index.html
 * after <script src='lang/translator.js' type='text/javascript'></script>.
 * For example - <script src='lang/ru.js' type='text/javascript'></script>
 *
 * If you wish to translate some new texts you should do two things:
 * 1. Add a new phrase pair ("New Phrase": "New Translation") into your language file (for example lang/ru.js). It will be great if you add it in other language files too.
 * 2. Mark that text it templates this way <anyHtmlTag data-sw-translate>New Phrase</anyHtmlTag> or <anyHtmlTag data-sw-translate value='New Phrase'/>.
 * The main thing here is attribute data-sw-translate. Only inner html, title-attribute and value-attribute are going to translate.
 *
 */
window.Translator = {

    _words: [],
    _empty: [],

    translate: function (sel) {
        var $this = this;
        sel = sel || '[dma-translate]';

        var $1 = $(sel);
        $.each($1, function (i, obj) {
            var $2 = $(obj);
            $2.text($this._tryTranslate($(this).html()));
            $2.attr('title', $this._tryTranslate($(this).attr('title')));
            $2.attr('placeholder', $this._tryTranslate($(this).attr('placeholder')));
        });
        $("html").css("display", "inherit");
    },

    _tryTranslate: function (word) {

return word;
        console.log('_tryTranslate == > ', word);
        if (!this._words || this._words.length < 1) {
            var defaultLang = window.sessionStorage.getItem(_lang);
            var key = _lang_dic + ":" + (defaultLang ? defaultLang : _default_lang);
            var item = window.sessionStorage.getItem(key);
            this._words = JSON.parse(item);
            if (!this._words) {
                return word;
            }
        }
        if (word) {
            word = $.trim(word);
            word = word.replace(/[\r\n]/g, ""); //去掉回车换行
            word = word.replace(/[\n]/g, ""); //去掉换行
            word = word.replace(/[\r]/g, ""); //去掉回车
        }

        var b = this._words[$.trim(word)] !== undefined;
        if (!b && word) {
            if (isChinese(word)) {
                this._empty.push(word);
                console.log("new:", word)
            }
        }
        var translate = this._words[word];
        if (b) {
            console.log(word, "==> ", translate)
            return translate;
        } else {
            // $.ajax({url: "/lang/api/1.0/add.do", data: {zh: word}}, function (res) {
            //     console.log("after added:", res);
            // });
            return word;
        }
    },

    init: function (wordsMap) {
        this._words = wordsMap;
        this.translate();
        return this;
    }
};

function translate(lan) {
    document.cookie = 'language=' + lan || 'en';
    var key = _lang_dic + ":" + lan;
    window.sessionStorage.removeItem(key);
    $.getScript("/static/lang/" + lan + ".js");
    window.sessionStorage.setItem(_lang, lan);
    window.location.reload();
}


var strcookie = document.cookie;//获取cookie字符串
var arrcookie = strcookie.split("; ");//分割
//遍历匹配
var hasLang = false;
for (var i = 0; i < arrcookie.length; i++) {
    var arr = arrcookie[i].split("=");
    if (arr[0] == 'language') {
        hasLang = true;
        $.getScript("/static/lang/" + arr[1] + ".js");
        break;
    }
}
if (!hasLang) {
    $.getScript("/static/lang/en.js");
}

