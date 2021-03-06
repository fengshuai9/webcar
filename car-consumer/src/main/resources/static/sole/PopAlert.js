var Pop = {};

Pop = {
    $: function (id, doc) {
        var doc = doc || document;
        return doc.getElementById(id);
    },
    $$: function (tagName, doc) {
        var doc = doc || document;
        return doc.getElementsByTagName(tagName);
    },
    $$$: function (tagName, doc) {
        var doc = doc || document;
        return doc.createElement(tagName);
    }, 
    getOs: function () {
        if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
            return "MSIE8";
        }
        else if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
            return "MSIE6";
        }
        else if (navigator.userAgent.indexOf("MSIE 7.0") > 0) {
            return "MSIE7";
        }
        else if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
            return "Firefox";
        }
        if (navigator.userAgent.indexOf("Chrome") > 0) {
            return "Chrome";
        }
        else {
            return "Other";
        }
    },
    getPostXY: function () {
        var de = document.documentElement;
        var winWidth = 0;
        var winHeight = 0;
        // 获取窗口宽度
        if (window.innerWidth)
            winWidth = window.innerWidth;
        else if ((document.body) && (document.body.clientWidth))
            winWidth = document.body.clientWidth;
        // 获取窗口高度
        if (window.innerHeight)
            winHeight = window.innerHeight;
        else if ((document.body) && (document.body.clientHeight))
            winHeight = document.body.clientHeight;
        // 通过深入 Document 内部对 body 进行检测，获取窗口大小
        if (de && de.clientHeight && de.clientWidth) {
            winHeight = de.clientHeight;
            winWidth = de.clientWidth;
        }
        var scrollTop = document.body.scrollTop || (de && de.scrollTop);
        var scrollLeft = document.body.scrollLeft || (de && de.scrollLeft);

        return { 'W': winWidth, 'H': winHeight, 'scrollTop': scrollTop, 'scrollLeft': scrollLeft };
    },
    getCharactersLen: function (str) {
        var Browser = this.getOs();
        var strVal = str;
        if (Browser == 'Firefox' || Browser == 'Chrome' || Browser == 'Other') {
            strVal = strVal.replace(/[\n]/g, '\n\r');
        }
        var totalCount = 0;
        for (var i = 0; i < strVal.length; i++) {
            var c = strVal.charCodeAt(i);
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                totalCount++;
            } else {
                totalCount += 2;
            }
        }
        return totalCount;
    },
    getCookie: function (sName) {
        var aCookie = document.cookie.split("; ");
        for (var i = 0; i < aCookie.length; i++) {
            var aCrumb = aCookie[i].split("=");
            if (sName == aCrumb[0])
                return unescape(aCrumb[1]);
        }
        return null;
    }
};

var baseScroll = function () { };
var baseOnresize = function () { };
Pop.Dialog = function (DivId, Color, isMask) {

    var de = document.documentElement;
    var Color = Color || '#000';
    var isMask = isMask || '1';
    var DivObj = this.$(DivId);
    var MaskDivId = 'PopMaskDiv';
    var init = function () {
        var SP = Pop.getPostXY();
        if (isMask == '1') { create(SP); }
        DivObj.style.display = 'block';
        DivObj.style.zIndex = '9999';
        DivObj.style.margin = '0';
        DivObj.style.padding = '0';
        DivObj.style.position = 'absolute';
        DivObj.style.top = (SP.scrollTop + de.clientHeight / 2 - DivObj.offsetHeight / 2) + "px";
        DivObj.style.left = (SP.scrollLeft + de.clientWidth / 2 - DivObj.offsetWidth / 2) + "px";

    };
    var create = function (fun) {
        var MaskObj = Pop.$(MaskDivId);
        if (MaskObj) {
            MaskObj.style.top = (fun.scrollTop + de.clientHeight / 2 - fun.H / 2) + "px";
            MaskObj.style.left = (fun.scrollLeft + de.clientWidth / 2 - fun.W / 2) + "px";
            MaskObj.style.width = fun.W + 'px';
            MaskObj.style.height = fun.H + 'px';
        }
        else {
            var MaskDiv = Pop.$$$('div');
            MaskDiv.id = MaskDivId;
            MaskDiv.style.display == 'block';
            MaskDiv.style.background = Color;
            MaskDiv.style.position = 'absolute';
            MaskDiv.style.top = (fun.scrollTop + de.clientHeight / 2 - fun.H / 2) + "px";
            MaskDiv.style.left = '0';
            MaskDiv.style.filter = 'alpha(opacity=50)';
            MaskDiv.style.opacity = '.50';
            MaskDiv.style.zIndex = '9998';
            MaskDiv.style.width = fun.W + 'px';
            MaskDiv.style.height = fun.H + 'px';
            //MaskDiv.onclick = function () { Pop.Dialog.Hide(DivId); }
            document.body.appendChild(MaskDiv);
        }
    };
    init();

    baseOnresize = window.onresize;
    baseScroll = window.onscroll;

    window.onresize = init;
    window.onscroll = init;
};

Pop.Dialog.Hide = function (DivId) {
    var MaskDivId = 'PopMaskDiv';
    if (Pop.$(MaskDivId)) {
        document.body.removeChild(Pop.$(MaskDivId));
    }
    window.onresize = baseOnresize;
    window.onscroll = baseScroll;

    Pop.$(DivId).style.display = 'none';
};