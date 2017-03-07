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
                                <label>角色名称</label><label class="control-label" for="name"></label>
                                <input class="form-control" name="name" id="name" value="${obj.name}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>序号</label><label class="control-label" for="ordinal"></label>
                                <input class="form-control" name="ordinal" id="ordinal" value="${obj.ordinal}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>模块</label><label class="control-label" for="modules"></label>
                                <select id="modules" multiple="multiple" name="modules">
                                    <g:each in="${gadmin.Module.list()}" var="module">
                                        <option value="${module.id}" ${exist(beans: obj.modules,bean: module,'selected="selected"')}>${module.name}</option>
                                    </g:each>
                                </select>
                                <script type="text/javascript">
                                    $('#modules').multiselect({
                                        buttonContainer: '<div class="" />',
                                        buttonClass: 'form-control btn-group',
                                        numberDisplayed: 5,
                                        onChange: function(option, checked, select) {
                                            var option_ = $("#modules option[value=" + $(option).val() +"]");
                                            if(checked){
                                                option_.attr('selected','selected');
                                            }else{
                                                option_.removeAttr('selected');
                                            }
                                        }
                                    });
                                </script>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>备注</label><label class="control-label" for="comment"></label>
                                <input class="form-control" name="comment" id="comment" value="${obj.comment}">
                            </div>
                            <div class="col-lg-12 text-center">
                                <g:link class="btn btn-default" action="index">返回</g:link>
                                <g:submitButton name="save" class="btn btn-default" value="${message(code: 'default.button.create.label', default: '保存')}" />
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