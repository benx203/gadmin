package gadmin

import grails.converters.JSON
import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils

@Transactional(readOnly = true)
class NotificationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def domainLabel = {message(code: 'notification.label', default: 'Notification')}

    def index() {
        render view: 'index'
    }

    def lastMsgs(){
        render(contentType: 'application/json'){
            Notification.createCriteria().list(max: 5) {
                eq 'user',User.findByUsername(SecurityUtils.subject.principal)
                eq 'known',false
            }.collect{Notification notification->
                [type:notification.notificationType.display,content:notification.content,time:DateUtil.dateToMDHMStr(notification.sendTime)]
            }
        }
    }

    def dataGrid(){
        def recordsTotal = Notification.createCriteria().count{
            eq 'user',User.findByUsername(SecurityUtils.subject.principal)
        }
        String search = params.get('search[value]')
        if(search){
            search = "%${search}%"
        }
        def searchTotal = Notification.createCriteria().count{
            eq 'user',User.findByUsername(SecurityUtils.subject.principal)
            if(search){
                like 'content',search
            }
        }
        def rs = [draw:params.draw,recordsTotal:recordsTotal,recordsFiltered:searchTotal]
        def data = []
        if(searchTotal != 0){
            def objs = Notification.createCriteria().list(max: params.length, offset: params.start) {
                eq 'user',User.findByUsername(SecurityUtils.subject.principal)
                if(search){
                    like 'content',search
                }
            }
            objs.each{Notification obj->
                data.push([obj.content,DateUtil.dateToStr(obj.sendTime),obj.known?'是':'否','',obj.id])
            }
        }
        rs.put('data',data)
        render rs as JSON
    }

    @Transactional
    def read(Notification obj){
        obj.known = true
        obj.save()
        def m = [success:true]
        render m as JSON
    }

    @Transactional
    def readAll(){
        def objs = Notification.createCriteria().list {
            eq 'user',User.findByUsername(SecurityUtils.subject.principal)
        }
        objs.each {obj->
            obj.known = true
            obj.save()
        }
        def m = [success:true]
        render m as JSON
    }

    def setAttr(String key,value){
        request.setAttribute(key,value)
    }
}
