/* Append File:/2sc/m/tools.js */
var xxxAarr = ['u', 'x', 'k', 'd', 'o', 'p', 'g', 's', 'v', 'j'];

/*20151218 sunping M端公用方法脚本类 */
var Tools = function () {
    var ithis = this;
    /*获取地址栏参数值*/
    this.GetRequest = function (sourcename) {
        var cUrl = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (cUrl.indexOf("?") != -1) {
            var str = cUrl.substr(1);
            var strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest[sourcename];
    };

    this.GetSourceName = function() {
        var sourcename = ithis.GetRequest("sourcename");
        if (sourcename) {
            if (",meituan,weizhang,main,baojia,mainapp,baojiazuche,didapinche,songguojiakao,pinganmap,baojiasearch,ahxiaochengxu,2scxcx,".indexOf(',' + sourcename + ',') > -1) {
                return sourcename;
            } else {
                return '';
            }
        }
        if (sourcename == "baojiasearch") { sourcename = "baojia"; }        
        return sourcename;
    };

    //给传递的地址加上当前url的sourcename值，没有返回原地址
    this.ToUrl = function (url) {
        var sourcename = ithis.GetSourceName();
        var safe = ithis.GetRequest("safe");
        if (sourcename != null && sourcename.length > 0) {
            var splitindex = url.indexOf('?');
            var before = '', end = '';
            if (splitindex > -1) {
                before = url.substr(0, splitindex);
                end = url.substr(splitindex + 1);
                url = before + "?sourcename=" + sourcename + "&safe=" + safe + "&" + end;
            } else {
                splitindex = url.indexOf('#');
                if (splitindex > -1) {
                    before = url.substr(0, splitindex);
                    end = url.substr(splitindex);
                    url = before + "?sourcename=" + sourcename + "&safe=" + safe + end;
                } else {
                    url += "?sourcename=" + sourcename + "&safe=" + safe;
                }
            }
        }
        return url;
    };

    // xxx编码
    this.XxxEncode = function (code) {
        var c = "";
        for (var i = 0; i < code.length; i++) {
            c += xxxAarr[code[i]];

            if ((i + 1) % 2 === 0) {
                c += "-";
            }
        }
        return c;
    }
};

var tools = new Tools();



