<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
                                <label>登录名称</label>
                                <p class="form-control-static">${obj.username}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>用户姓名</label>
                                <p class="form-control-static">${obj.name}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>显示昵称</label>
                                <p class="form-control-static">${obj.nickname}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>电子邮箱</label>
                                <p class="form-control-static">${obj.email}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>手机号码</label>
                                <p class="form-control-static">${obj.phone}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>角色</label>
                                <p class="form-control-static">
                                    <g:each in="${obj.roles}" var="role">
                                        ${role.name}&nbsp;
                                    </g:each>
                                </p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>序号</label>
                                <p class="form-control-static">${obj.ordinal}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>是否启用</label>
                                <p class="form-control-static">${obj.enabled?'是':'否'}</p>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>备注</label>
                                <p class="form-control-static">${obj.comment}</p>
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