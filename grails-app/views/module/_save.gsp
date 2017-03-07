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
                                <label>模块名称</label><label class="control-label" for="name"></label>
                                <input class="form-control" name="name" id="name" value="${obj.name}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>权限代码</label><label class="control-label" for="permission"></label>
                                <input class="form-control" name="permission" id="permission" value="${obj.permission}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>序号</label><label class="control-label" for="ordinal"></label>
                                <input class="form-control" name="ordinal" id="ordinal" value="${obj.ordinal}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>URI</label><label class="control-label" for="uri"></label>
                                <input class="form-control" name="uri" id="uri" value="${obj.uri}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>ICON</label><label class="control-label" for="icon"></label>
                                <input class="form-control" name="icon" id="icon" value="${obj.icon}">
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