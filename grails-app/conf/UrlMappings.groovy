class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:'nav',action:'index')
        "/blank"(view:"/pages/blank")
        "/buttons"(view:"/pages/buttons")
        "/flot"(view:"/pages/flot")
        "/forms"(view:"/pages/forms")
        "/grid"(view:"/pages/grid")
        "/icons"(view:"/pages/icons")
        "/dashboard"(view:"/pages/dashboard")
        "/login"(view:"/pages/login")
        "/morris"(view:"/pages/morris")
        "/notifications"(view:"/pages/notifications")
        "/panels-wells"(view:"/pages/panels-wells")
        "/tables"(view:"/pages/tables")
        "/typography"(view:"/pages/typography")
        "500"(view:'/error')
        "404"(view:'/notFound')
	}
}
