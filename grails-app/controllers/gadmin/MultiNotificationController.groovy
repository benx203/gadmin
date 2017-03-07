package gadmin

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MultiNotificationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE",send: 'POST']

    def domainLabel = {message(code: 'multiNotification.label', default: 'MultiNotification')}

    @Transactional
    def send(MultiNotification obj){
        obj.users.each {
            new Notification(user: it,content: obj.content).save()
        }
        def m = [success:true]
        render m as JSON
    }

    def index() {
        render view: 'index'
    }

    def dataGrid(){
        def recordsTotal = MultiNotification.count()
        String search = params.get('search[value]')
        if(search){
            search = "%${search}%"
        }
        def searchTotal = MultiNotification.createCriteria().count{
            if(search){
                like 'content',search
            }
        }
        def rs = [draw:params.draw,recordsTotal:recordsTotal,recordsFiltered:searchTotal]
        def data = []
        if(searchTotal != 0){
            def objs = MultiNotification.createCriteria().list(max: params.length, offset: params.start) {
                if(search){
                    like 'content',search
                }
            }
            objs.each{MultiNotification obj->
                data.push([obj.content,DateUtil.dateToStr(obj.createTime),'',obj.id])
            }
        }
        rs.put('data',data)
        render rs as JSON
    }

    def show(MultiNotification obj) {
        setAttr('obj',obj)
        respond obj
    }

    def create() {
        def obj=new MultiNotification(params)
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def save(MultiNotification obj) {
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

    def edit(MultiNotification obj) {
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def update(MultiNotification obj) {
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
    def delete(MultiNotification obj) {

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
