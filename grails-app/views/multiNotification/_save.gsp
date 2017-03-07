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
                                <label>消息内容</label><label class="control-label" for="content"></label>
                                <input class="form-control" name="content" id="content" value="${obj.content}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>通知人员</label><label class="control-label" for="users"></label>
                                <select id="users" multiple="multiple" name="users">
                                    <g:each in="${gadmin.User.list()}" var="user">
                                        <option value="${user.id}" ${exist(beans: obj.users,bean: user,'selected="selected"')}>${user.name}</option>
                                    </g:each>
                                </select>
                                <script type="text/javascript">
                                    $('#users').multiselect({
                                        buttonContainer: '<div class="" />',
                                        buttonClass: 'form-control btn-group',
                                        numberDisplayed: 5,
                                        onChange: function(option, checked, select) {
                                            var option_ = $("#users option[value=" + $(option).val() +"]");
                                            if(checked){
                                                option_.attr('selected','selected');
                                            }else{
                                                option_.removeAttr('selected');
                                            }
                                        }
                                    });
                                </script>
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