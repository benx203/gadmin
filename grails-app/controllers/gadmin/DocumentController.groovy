package gadmin

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DocumentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def domainLabel = {message(code: 'document.label', default: 'Document')}

    def index() {
        render view: 'index'
    }

    def dataGrid(){
        def recordsTotal = Document.count()
        String search = params.get('search[value]')
        if(search){
            search = "%${search}%"
        }
        def searchTotal = Document.createCriteria().count{
            if(search){
                like 'title',search
            }
        }
        def rs = [draw:params.draw,recordsTotal:recordsTotal,recordsFiltered:searchTotal]
        def data = []
        if(searchTotal != 0){
            def objs = Document.createCriteria().list(max: params.length, offset: params.start) {
                if(search){
                    like 'title',search
                }
            }
            objs.each{Document obj->
                data.push([obj.title,obj.year,obj.documentType?.name,DateUtil.dateToStr(obj.updateTime),'',obj.id])
            }
        }
        rs.put('data',data)
        render rs as JSON
    }

    def show(Document obj) {
        setAttr('obj',obj)
        respond obj
    }

    def create() {
        def obj=new Document(params)
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def save(Document obj) {
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

    def edit(Document obj) {
        setAttr('obj',obj)
        respond obj
    }

    @Transactional
    def update(Document obj) {
        if (obj == null) {
            notFound()
            return
        }

        obj.updateTime = new Date()

        if (obj.hasErrors()) {
            setAttr('obj',obj)
            respond obj.errors, view:'edit'
            return
        }

        obj.save flush:true

        afterUpdate(obj)
    }

    @Transactional
    def delete(Document obj) {

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
