<g:set var="entityName" value="${message(code: 'multiNotification.label', default: 'MultiNotification')}" />
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
                                <label>消息内容</label>
                                <p class="form-control-static">${obj.content}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>创建时间</label>
                                <p class="form-control-static">${obj.createTime}</p>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>通知人员</label>
                                <p class="form-control-static">
                                    ${obj.users.name.join(',')}
                                </p>
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