<g:set var="entityName" value="${message(code: 'role.label', default: 'Module')}" />
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
                            <th>角色名称</th>
                            <th>序号</th>
                            <th>备注</th>
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
                "ajax": "${createLink(uri: '/role/dataGrid')}",
                "createdRow": function ( row, data, index ) {
                    var last = data.length-1
                    var id = data[last]
                    var td = '<a type="button" class="btn btn-xs btn-success" href="/role/show/' + id +'">查看</a>&nbsp;'
                    td += '<a type="button" class="btn btn-xs btn-warning" href="/role/edit/' + id +'">编辑</a>&nbsp;'
                    td += '<button type="button" class="btn btn-xs btn-danger" onclick="deleteObj(\'/role/delete/' + id +'\',\'/role/index\')">删除</button>'
                    $('td', row).eq(last-1).html(td)
                }
            } );
        });
    </script>
</content>