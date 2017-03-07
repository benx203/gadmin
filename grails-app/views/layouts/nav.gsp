<g:applyLayout name="nonav">
    <title><g:layoutTitle/></title>
    <content tag="wrapper">
        <g:render template="/layouts/navbar"/>

        <div id="page-wrapper">
            <div class="container-fluid">
                <g:pageProperty name="page.body"/>
            </div>
        </div>
    </content>
    <content tag="js">
        <g:pageProperty name="page.js"/>
    </content>
    <content tag="init">
        <asset:javascript src="init.js"/>
    </content>
</g:applyLayout>