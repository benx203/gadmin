<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<meta name="layout" content="nav">
<content tag="body">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <g:link class="btn btn-xs btn-success" action="create"><g:message code="default.new.label" /></g:link>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                        <thead>
                        <tr>
                            <th>登录名称</th>
                            <th>用户姓名</th>
                            <th>显示昵称</th>
                            <th>电子邮箱</th>
                            <th>手机号码</th>
                            <th>是否启用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</content>
<content tag="js">
    <script>
        $(function () {
            $('#dataTable').DataTable( {
                "processing": true,
                "serverSide": true,
                "ajax": "${createLink(uri: '/user/dataGrid')}",
                "createdRow": function ( row, data, index ) {
                    var last = data.length-1
                    var id = data[last]
                    var td = '<a type="button" class="btn btn-xs btn-success" href="/user/show/' + id +'">查看</a>&nbsp;'
                    td += '<a type="button" class="btn btn-xs btn-warning" href="/user/edit/' + id +'">编辑</a>&nbsp;'
                    td += '<button type="button" class="btn btn-xs btn-danger" onclick="deleteObj(\'/user/delete/' + id +'\',\'/user/index\')">删除</button>'
                    $('td', row).eq(last-1).html(td)
                }
            } );
        });
    </script>
</content>