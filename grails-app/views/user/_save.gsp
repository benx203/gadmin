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
                                <label>登录名称</label><label class="control-label" for="username"></label>
                                <input class="form-control" name="username" id="username" value="${obj.username}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>登录密码${method=='PUT'?'(为空时表示不修改密码)':''}</label><label class="control-label" for="password"></label>
                                <input class="form-control" name="password" id="password" value="${obj.password}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>用户姓名</label><label class="control-label" for="name"></label>
                                <input class="form-control" name="name" id="name" value="${obj.name}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>显示昵称</label><label class="control-label" for="nickname"></label>
                                <input class="form-control" name="nickname" id="nickname" value="${obj.nickname}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>电子邮箱</label><label class="control-label" for="email"></label>
                                <input class="form-control" name="email" id="email" value="${obj.email}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>手机号码</label><label class="control-label" for="phone"></label>
                                <input class="form-control" name="phone" id="phone" value="${obj.phone}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>模块</label><label class="control-label" for="roles"></label>
                                <select id="roles" multiple="multiple" name="roles">
                                    <g:each in="${roles}" var="role">
                                        <option value="${role.id}" ${exist(beans: obj.roles,bean: role,'selected="selected"')}>${role.name}</option>
                                    </g:each>
                                </select>
                                <script type="text/javascript">
                                    $('#roles').multiselect({
                                        buttonContainer: '<div class="" />',
                                        buttonClass: 'form-control btn-group',
                                        numberDisplayed: 5,
                                        onChange: function(option, checked, select) {
                                            var option_ = $("#roles option[value=" + $(option).val() +"]");
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
                                <label>序号</label><label class="control-label" for="ordinal"></label>
                                <input class="form-control" name="ordinal" id="ordinal" value="${obj.ordinal}">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>是否启用</label>
                                <g:select class="form-control" name="enabled" keys="[true,false]" from="['是','否']" value="${obj.enabled}"/>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>备注</label><label class="control-label" for="comment"></label>
                                <textarea class="form-control" rows="3" name="comment" id="comment">${obj.comment}</textarea>
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