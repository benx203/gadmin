<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <g:link uri="/index" class="navbar-brand">SB Admin v2.0</g:link>
    </div>

    <ul class="nav navbar-top-links navbar-right" id="navbar-right">
        <g:render template="/layouts/navbar-right"/>
    </ul>

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <g:render template="/layouts/sidebar"/>
            </ul>
        </div>
    </div>
</nav>