<title>${htmlTitle}</title>
<meta name="layout" content="nav">
<content tag="body">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    ${htmlTitle}
                </div>
                <div class="panel-body">
                    <div class="row">
                        <g:form resource="${obj}" method="${method}" role="form" class="col-lg-12">
                            <div class="form-group col-lg-6">
                                <label>文档标题</label><label class="control-label" for="title"></label>
                                <input class="form-control" name="title" id="title" value="${obj.title}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>文档类型</label><label class="control-label" for="documentType"></label>
                                <g:select class="form-control" id="documentType" name="documentType.id" from="${gadmin.DocumentType.list()}" optionKey="id" optionValue="name" value="${obj?.documentType?.id}" />
                            </div>
                            <div class="form-group col-lg-6">
                                <label>年份</label><label class="control-label" for="year"></label>
                                <input class="form-control" name="year" id="year" value="${obj.year}" type="number">
                            </div>
                            <div class="form-group col-lg-12">
                                <label>备注</label><label class="control-label" for="comment"></label>
                                <input class="form-control" name="comment" id="comment" value="${obj.comment}">
                            </div>
                            <div class="form-group col-lg-12">
                                <label>附件</label><label class="control-label" for="attachment"></label>
                                <input id="attachment" name="attachment" type="hidden">
                                <input type="button" id="uploadButton" value="上传" />
                                <div id="attachmentInfo" class="attachment"/>
                            </div>
                            <div class="col-lg-12 text-center">
                                <g:link class="btn btn-default" action="index">返回</g:link>
                                <g:submitButton name="create" class="btn btn-default" value="${message(code: 'default.button.create.label', default: '保存')}" />
                            </div>
                        </g:form>
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
    </div>

    <g:hasErrors bean="${obj}">
        <g:eachError bean="${obj}" var="error">
            <g:if test="${error in org.springframework.validation.FieldError}">
                <script>
                    (function () {
                        var id = '${error.field}';
                        var div = $('#' + id).parent();
                        div.addClass('has-error');
                        var label = div.find("[for='" + id + "']");
                        label.html('${g.message(error:error)}');
                        label.show();
                    })();
                </script>
            </g:if>
        </g:eachError>
    </g:hasErrors>
</content>
<content tag="js">
    <script type="text/javascript">
        $(function() {
            bindUploadbutton('document');
            showAttachments("${obj.attachment}",true);
        });
    </script>
</content>