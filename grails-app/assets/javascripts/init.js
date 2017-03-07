$(function () {
    // init menu
    $.ajax('/user/menuData',{'method':'POST'}).then(function (datas) {
        var menu = $('#side-menu');
        var first = []
        for(var i in datas){
            if(datas[i].pid == ''){
                first.push(datas[i])
            }
        }
        for(var i in first){
            var data = first[i]
            var seconds = subMenus(data.id,datas)
            if(seconds.length>0){
                var secondStr = '<li><a href="#"><i class="fa ' + data.icon +' fa-fw"></i>' + data.name +'<span class="fa arrow"></span></a><ul class="nav nav-second-level">'
                for(var j in seconds){
                    var second = seconds[j]
                    var thirds = subMenus(second.id,datas)
                    if(thirds.length>0){
                        var thirdStr = '<li><a href="#">' + second.name +'<span class="fa arrow"></span></a><ul class="nav nav-third-level">'
                        for(var k in thirds){
                            var third = thirds[k]
                            thirdStr += '<li><a href="' + third.uri + '">' + third.name +'</a></li>'
                        }
                        thirdStr += '</ul></li>'
                        secondStr += thirdStr
                    }else{
                        secondStr += '<li><a href="' + second.uri + '">' + second.name +'</a></li>'
                    }
                }
                secondStr += '</ul></li>'
            }else{
                secondStr = '<li><a href="' + data.uri +'"><i class="fa fa-dashboard fa-fw"></i>' + data.name +'</a></li>'
            }
            menu.append(secondStr)
        }

        menu.metisMenu();

        $(window).bind("load resize", function() {
            var topOffset = 50;
            var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
            if (width < 768) {
                $('div.navbar-collapse').addClass('collapse');
                topOffset = 100; // 2-row-menu
            } else {
                $('div.navbar-collapse').removeClass('collapse');
            }

            var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
            height = height - topOffset;
            if (height < 1) height = 1;
            if (height > topOffset) {
                $("#page-wrapper").css("min-height", (height) + "px");
            }
        });

        var url = window.location;
        // var element = $('ul.nav a').filter(function() {
        //     return this.href == url;
        // }).addClass('active').parent().parent().addClass('in').parent();
        var element = $('ul.nav a').filter(function() {
            return this.href == url;
        }).addClass('active').parent();

        while (true) {
            if (element.is('li')) {
                element = element.parent().addClass('in').parent();
            } else {
                break;
            }
        }
    });

    // init massage
    $.ajax('/notification/lastMsgs',{'method':'POST'}).then(function (datas) {
        var msg = $('#top-massage')
        var msgStr = ''
        var size = datas.length
        for(var i in datas){
            var data = datas[i];
            msgStr += '<li><a href="#"><div><strong>' + data.type +'</strong>'
            msgStr += '<span class="pull-right text-muted"><em>' + data.time + '</em></span></div><div>'
            msgStr += data.content +'</div></a></li><li class="divider"></li>'
        }
        msgStr += '<li><a href="/notification" class="text-center"><strong>查看全部(未读:' + size +')</strong></a></li>';
        msg.append(msgStr);
        if(size>0){
            $('#top-massage-head').addClass('open');
        }
    });
});

function subMenus(id,datas) {
    var menus = []
    for(var i in datas){
        if(datas[i].pid == id){
            menus.push(datas[i])
        }
    }
    return menus
}