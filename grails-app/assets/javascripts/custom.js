function showSuccessTip(tip,url) {
    swal({
        title: tip || '操作成功!',
        text: '',
        type: "success",
        timer: 800,
        confirmButtonText: "关闭"
    },function () {
        if(url){
            location.href = url
        }
    });
}

// 显示操作失败提示,不会自动关闭
function showFailTip(tip) {
    swal({
            title: tip||'操作失败!',
            text: '',
            type: "error",
            confirmButtonText: "关闭"
        });
}

function showInfoTip(tip) {
    swal({
        title: tip,
        text: '',
        type: "info",
        timer: 800,
        confirmButtonText: "关闭"
    });
}

function deleteObj(href,nextUrl) {
    swal({
            title: "确认是否删除?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
            cancelButtonText:'取消',
            closeOnConfirm: false
        },
        function(){
            swal.close();
            $.ajax(href,{'method':'DELETE'}).then(function () {
                location.href = nextUrl;
            });
        });
}

function request(url,nextUrl) {
    $.ajax(url,{'method':'POST'}).then(function () {
        showSuccessTip('',nextUrl);
    });
}

// 绑定编辑器
function bindEditor(id) {
    window.setTimeout(function() {
        editor = KindEditor.create('#' + id, {
            width : '680px',
            height : '300px',
            items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist',
                'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
                'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons',
                'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
            uploadJson : '/file/upload',
            fileManagerJson : '/file/fileManage',
            allowFileManager : true
        });
    }, 1);
}

// 绑定上传按钮,上传单个或多个文件，默认上传多个，如上传单个，设置multi=false
function bindUploadbutton(dir,btnId,infoId,valueId,multi) {
    if(btnId == true || btnId == false){// 参数后移处理,调用时方便省略参数
        valueId = multi;
        multi = btnId;
        btnId = infoId;
        infoId = valueId;
    }
    btnId = btnId || 'uploadButton';
    infoId = infoId || 'attachmentInfo';
    valueId = valueId || 'attachment';
    if(multi == false){// 上传单个
        var type = 'file';
        if(tagName(infoId) == 'IMG'){
            type = 'image';
        }
        window.setTimeout(function() {
            var uploadBtn =  KindEditor.uploadbutton({
                button :  $('#' + btnId),
                url : '/file/upload?type=' + type + '&dir=' + dir,
                afterUpload : function(data) {
                    if (data.error === 0) {
                        $('#' + valueId).val(data.url);
                        var info = $('#' + infoId);
                        if(info){
                            var tagName = info.prop('tagName');
                            if(tagName == 'A'){// 比如检测项目testproject上传原始记录模板后显示
                                info.attr('href','' + data.url);
                                info.html(data.name);
                            }else if(tagName == 'IMG'){// 比如user管理上传签名图片后显示图片
                                info.attr('src','' + data.url);
                            }
                        }
                    } else {
                        showFailTip(data.message);
                    }
                },
                afterError : function(str) {
                    showFailTip('错误: ' + str);
                }
            });
            uploadBtn.fileBox.change(function(e) {
                uploadBtn.submit();
            });
        }, 1);
    }else{
        window.setTimeout(function() {
            var uploadBtn =  KindEditor.uploadbutton({
                button :  $('#' + btnId),
                url : '/file/upload?type=file&dir=' + dir,
                afterUpload : function(data) {
                    if (data.error === 0) {
                        $('#' + infoId).append('<div class="inline-block"><a href="' + data.url+ '" class="span4" target="_blank">'
                            + data.name + '</a><input type="button" value="删除" onclick="removeObj(this,\'' + infoId + '\',\'' + valueId + '\')"></div>');
                        mergeUploadInfo(infoId,valueId);
                    } else {
                        showFailTip(data.message);
                    }
                },
                afterError : function(str) {
                    showFailTip('错误: ' + str);
                }
            });
            uploadBtn.fileBox.change(function(e) {
                uploadBtn.submit();
            });
        }, 1);
    }
}

// 获取元素的标签名称，比如div,a,img等
function tagName(id) {
    var info = $('#' + id);
    if(info){
        return info.prop('tagName');
    }
}

// 将上传的附件信息设置到valueId对应的字段，类似:/aaa.jpg,/bbb.jpg
function mergeUploadInfo(infoId,valueId) {
    var urls = '';
    $('#' + infoId).find('a').each(function(){
        urls += $(this).attr('href') + ',';
    });
    urls = urls.substr(0,urls.length-1);
    $('#' + valueId).val(urls);
}

// form表单提交时按需检查是否已上传附件
function existAttachments(valueId) {
    valueId = valueId || 'attachment';
    if($('#' + valueId).val() == ''){
        showInfoTip('请上传附件!');
        return false;
    }
    return true;
}

// 显示上传的附件信息到infoId对应的div中
function showAttachments(infos,canDelete,infoId,valueId) {
    infoId = infoId || 'attachmentInfo';
    valueId = valueId || 'attachment';
    var valueDiv = $('#' + valueId);
    if(valueDiv){
        valueDiv.val(infos);
    }
    if(!infos){
        return;
    }
    var arr = infos.split(',');
    var frag;
    for(var i in arr){
        frag = '<div class="inline-block"><a href="' + arr[i]+ '" class="span4" target="_blank">' + arr[i].substr(arr[i].lastIndexOf('/') + 1) + '</a>';
        if(canDelete){
            frag += '<input type="button" value="删除" onclick="removeObj(this,\'' + infoId + '\',\'' + valueId + '\')">';
        }
        frag += '</div>';
        $('#' + infoId).append(frag);
    }
}

function removeObj(obj,infoId,valueId){
    $(obj).parent().remove();
    mergeUploadInfo(infoId,valueId);
}