<g:set var="entityName" value="${message(code: 'notification.label', default: 'notification')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<meta name="layout" content="nav">
<content tag="body">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <button type="button" class="btn btn-xs btn-success" onclick="request('${g.createLink(action:'readAll')}','${g.createLink()}')">全部已读</button>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                        <thead>
                        <tr>
                            <th>消息内容</th>
                            <th>发送时间</th>
                            <th>已读</th>
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
            var url = "${g.createLink(controller: 'notification', action:'read')}"
            var nextUrl = "${g.createLink()}"
            $('#dataTable').DataTable( {
                "processing": true,
                "serverSide": true,
                "ajax": "${createLink(uri: '/notification/dataGrid')}",
                "createdRow": function ( row, data, index ) {
                    var last = data.length-1
                    var id = data[last]
                    $('td', row).eq(last-1).html('<button type="button" class="btn btn-xs btn-success" onclick="request(\'' + url + '/' + id + '\',\'' + nextUrl + '\')">已读</button>')
                }
            } );
        });
    </script>
</content>