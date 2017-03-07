<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
<g:set var="htmlTitle" value="${message(code: 'default.create.label', args: [entityName])}" />
<g:set var="method" value="POST" />
<g:render template="save" model='[htmlTitle:"${htmlTitle}"]'/>