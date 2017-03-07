package gadmin

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class RoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def domainLabel = {message(code: 'role.label', default: 'Role')}

    def index() {
        render view: 'index'
    }

    def dataGrid(){
        def recordsTotal = Role.count()
        String search = params.get('search[value]')
        if(search){
            search = "%${search}%"
        }
        def searchTotal = Role.createCriteria().count{
            if(search){
                like 'name',search
            }
        }
        def rs = [draw:params.draw,recordsTotal:recordsTotal,recordsFiltered:searchTotal]
        def data = []
        if(searchTotal != 0){
            def objs = Role.createCriteria().list(max: params.length, offset: params.start) {
                if(search){
                    like 'name',search
                }
            }
            objs.each{Role obj->
                data.push([obj.name,obj.ordinal,obj.comment,'',obj.id])
            }
        }
        rs.put('data',data)
        render rs as JSON
    }

    def show(Role obj) {
        setAttr('obj',obj)
        respond obj
    }

    def create() {
        def obj=new Role(params)
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def save(Role obj) {
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

    def edit(Role obj) {
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def update(Role obj) {
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
    def delete(Role obj) {

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
