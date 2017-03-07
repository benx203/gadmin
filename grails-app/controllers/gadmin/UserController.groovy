package gadmin

import grails.converters.JSON
import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class UserController{
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def domainLabel = {message(code: 'user.label', default: 'User')}

    def shiroSecurityService

    CacheUtil cacheUtil

    def menuData(){
        render(contentType: 'application/json'){
            cacheUtil.menus(SecurityUtils.subject.principal)
        }
    }

    def index() {
        render view: 'index'
    }

    def dataGrid(){
        def recordsTotal = User.count()
        String search = params.get('search[value]')
        if(search){
            search = "%${search}%"
        }
        def searchTotal = User.createCriteria().count{
            if(search){
                like 'username',search
            }
        }
        def rs = [draw:params.draw,recordsTotal:recordsTotal,recordsFiltered:searchTotal]
        def data = []
        if(searchTotal != 0){
            def objs = User.createCriteria().list(max: params.length, offset: params.start) {
                if(search){
                    like 'username',search
                }
            }
            objs.each{User obj->
                data.push([obj.username,obj.name,obj.nickname,obj.email,obj.phone,obj.enabled?'是':'否','',obj.id])
            }
        }
        rs.put('data',data)
        render rs as JSON
    }

    def show(User obj) {
        setAttr('obj',obj)
        setAttr('roles',Role.list())
        respond obj
    }

    def create() {
        def obj=new User(params)
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def save(User obj) {
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

    def edit(User obj) {
        setAttr('obj',obj)
        setAttr('roles',Role.list())
        respond obj
    }

    @Transactional
    def update() {
        User obj = User.get(params.id)
        if (obj == null) {
            notFound()
            return
        }

        cacheUtil.evictUser(obj.name)

        if(params.password){
            params.password = shiroSecurityService.encodePassword(params.password)
        }else{
            params.remove('password')
        }
        obj.properties = params

        if (obj.hasErrors()) {
            setAttr('obj',obj)
            respond obj.errors, view:'edit'
            return
        }

        obj.save flush:true

        afterUpdate(obj)
    }

    @Transactional
    def delete(User obj) {
        if (obj == null) {
            notFound()
            return
        }

        obj.delete flush:true

        cacheUtil.evictUser(obj.name)

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
