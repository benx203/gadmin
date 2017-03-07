<title>Login</title>
<meta name="layout" content="nonav">
<content tag="wrapper">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请输入登录信息</h3>
                    <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                    </g:if>
                </div>

                <div class="panel-body">
                    <g:form role="form" action="signIn">
                        <input type="hidden" name="targetUri" value="${targetUri}" />
                        <fieldset>
                            <div class="form-group">
                                <label for="username" class="control-label">用户名</label>
                                <input id="username" class="form-control" placeholder="username" name="username" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <label for="Password" class="control-label">密码</label>
                                <input id="Password" class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="rememberMe" value="${rememberMe}" />保持登录状态
                                </label>
                            </div>
                            <input type="submit" value="登 录" class="btn btn-lg btn-success btn-block" />
                        </fieldset>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</content>