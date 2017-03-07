<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
<meta name="layout" content="nav">
<content tag="body">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <g:message code="default.show.label" args="[entityName]" />
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group col-lg-6">
                                <label>文档标题</label>
                                <p class="form-control-static">${obj.title}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>文档类型</label>
                                <p class="form-control-static">${obj.documentType?.name}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>年份</label>
                                <p class="form-control-static">${obj.year}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>修改时间</label>
                                <p class="form-control-static">${obj.updateTime}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>备注</label>
                                <p class="form-control-static">${obj.comment}</p>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>附件</label>
                                <div id="attachmentInfo" class="attachment"/>
                            </div>
                            <div class="col-lg-12 text-center">
                                <g:link class="btn btn-default" action="index">返回</g:link>
                            </div>
                        </div>
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
    </div>
</content>
<content tag="js">
<script>
    $(function () {
        showAttachments("${obj.attachment}")
    });
</script>
</content>