<g:set var="entityName" value="${message(code: 'documentType.label', default: 'DocumentType')}" />
<g:set var="htmlTitle" value="${message(code: 'default.edit.label', args: [entityName])}" />
<g:set var="method" value="PUT" />
<g:render template="save" model='[htmlTitle:"${htmlTitle}"]'/>