<g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}" />
<g:set var="title" value="${message(code: 'default.create.label', args: [entityName])}" />
<g:set var="method" value="POST" />
<g:render template="save" model='[title:"${htmlTitle}"]'/>