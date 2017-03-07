<%=packageName ? "package ${packageName}\n\n" : ''%>


import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ${className}Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def domainLabel = {message(code: '${domainClass.propertyName}.label', default: '${className}')}

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def objs = ${className}.list(params)
        setAttr('objs',objs)
        respond objs, model:[count: ${className}.count()]
    }

    def show(${className} obj) {
        setAttr('obj',obj)
        respond obj
    }

    def create() {
        def obj=new ${className}(params)
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def save(${className} obj) {
        if (obj == null) {
            notFound()
            return
        }

        if (obj.hasErrors()) {
            setAttr('obj',obj)
            respond obj.errors, view:'create'
            return
        }

        obj.save flush:true

        afterCreate(obj)
    }

    def edit(${className} obj) {
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def update(${className} obj) {
        if (obj == null) {
            notFound()
            return
        }

        if (obj.hasErrors()) {
            setAttr('obj',obj)
            respond obj.errors, view:'edit'
            return
        }

        obj.save flush:true

        afterUpdate(obj)
    }

    @Transactional
    def delete(${className} obj) {

        if (obj == null) {
            notFound()
            return
        }

        obj.delete flush:true

        afterDelete(obj)
    }

    protected afterCreate(obj) {
        redirectIndex('default.created.message',obj,CREATED)
    }

    protected afterUpdate(obj){
        redirectIndex('default.updated.message',obj,OK)
    }

    protected afterDelete(obj){
        redirectIndex('default.deleted.message',obj,NO_CONTENT)
    }

    protected notFound = {redirectIndex('default.not.found.message',params,NOT_FOUND)}

    protected void redirectIndex(code,params,status) {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: code, args: [domainLabel(), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: status }
        }
    }

    def setAttr(String key,value){
        request.setAttribute(key,value)
    }
}
