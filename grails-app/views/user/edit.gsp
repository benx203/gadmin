<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
<g:set var="htmlTitle" value="${message(code: 'default.edit.label', args: [entityName])}" />
<g:set var="method" value="PUT" />
<%obj.password=''%>
<g:render template="save" model='[htmlTitle:"${htmlTitle}"]'/>