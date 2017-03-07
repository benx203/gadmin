<g:set var="entityName" value="${message(code: 'multiNotification.label', default: 'MultiNotification')}" />
<g:set var="title" value="${message(code: 'default.edit.label', args: [entityName])}" />
<g:set var="method" value="PUT" />
<g:render template="save" model='[title:"${htmlTitle}"]'/>