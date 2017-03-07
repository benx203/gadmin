import gadmin.DocumentType
import gadmin.Module
import gadmin.Notification
import gadmin.Role
import gadmin.User
import grails.util.Environment
import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {

    def init = {ctx ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                ctx.setAttribute("env", "dev")

                def all = new Module('全部模块','*:*',0).save()

                def sys = new Module('系统管理','',100,true,'fa-wrench').save()

                def root = new Module('用户管理','user:index',sys,100,'/user/index').save()
                def add = new Module('用户增加','user:create',root,110).save()
                def edit = new Module('用户修改','user:edit',root,120).save()
                def show = new Module('用户查看','user:show',root,130).save()
                def delete = new Module('用户删除','user:delete',root,140).save()

                def mRoot = new Module('模块管理','module:index',sys,200,'/module/index').save()
                def mAdd = new Module('模块增加','module:create',mRoot,210).save()
                def mEdit = new Module('模块修改','module:edit',mRoot,220).save()
                def mShow = new Module('模块查看','module:show',mRoot,230).save()
                def mDelete = new Module('模块删除','module:delete',mRoot,240).save()

                def roleManage = new Module('角色管理','role:index',sys,300,'/role/index').save()
                new Module('角色增加','role:create',roleManage,210).save()
                new Module('角色修改','role:edit',roleManage,220).save()
                new Module('角色查看','role:show',roleManage,230).save()
                new Module('角色删除','role:delete',roleManage,240).save()

                def oa = new Module('行政办公','',200,true,'fa-edit').save()
                def docType = new Module('文档类型','documentType:index',oa,100,'/documentType/index').save()
                def docTypeAdd = new Module('文档类型增加','documentType:create',docType,100).save()
                def docTypeEdit = new Module('文档类型修改','documentType:edit',docType,200).save()
                def docTypeShow = new Module('文档类型查看','documentType:show',docType,300).save()
                def docTypeDelete = new Module('文档类型删除','documentType:delete',docType,400).save()

                def doc = new Module('文档管理','document:index',oa,200,'/document/index').save()
                def docAdd = new Module('文档增加','document:create',doc,100).save()
                def docEdit = new Module('文档修改','document:edit',doc,200).save()
                def docShow = new Module('文档查看','document:show',doc,300).save()
                def docDelete = new Module('文档删除','document:delete',doc,400).save()

                def multi = new Module('群发消息','multiNotification:index',oa,300,'/multiNotification/index').save()
                new Module('消息增加','multiNotification:create',multi,210).save()
                new Module('消息修改','multiNotification:edit',multi,220).save()
                new Module('消息发送','multiNotification:send',multi,230).save()
                new Module('消息查看','multiNotification:show',multi,240).save()
                new Module('消息删除','multiNotification:delete',multi,250).save()

                new Module('消息管理','notification:index',oa,400,'/notification/index').save()

                def ui = new Module('UI演示','',300,true,'fa-gears').save()
                new Module('仪表盘','dashboard:*',ui,100,'/dashboard').save()
                def graph = new Module('图表','graph:*',ui,200,true).save()
                new Module('曲线图','flot:*',graph,200,'/flot').save()
                new Module('柱状图','morris:*',graph,300,'/morris').save()

                new Module('表格','tables:*',ui,300,'/morris').save()
                new Module('表单','forms:*',ui,400,'/forms').save()

                def element = new Module('UI元素','element:*',ui,500,true).save()
                new Module('面板','panels:*',element,100,'/panels-wells').save()
                new Module('按钮','buttons:*',element,200,'/buttons').save()
                new Module('消息','notifications:*',element,300,'/notifications').save()
                new Module('排版','typography:*',element,400,'/typography').save()
                new Module('ICON','icons:*',element,500,'/icons').save()
                new Module('栅格','grid:*',element,600,'/grid').save()

                def simple = new Module('简单页面','simple:*',ui,600,true).save()
                new Module('空白页面','blank:*',simple,100,'/blank').save()
                new Module('登录页面','login:*',simple,200,'/login').save()

                def adminRole = new Role('管理员')
                adminRole.modules = [all]
                adminRole.save()

                def guestRole = new Role('用户')
                guestRole.modules = [root,show]
                guestRole.save()

                new Role('test1').save()
                new Role('test2').save()

                def admin = new User(username: "admin",name: 'admin', password:new Sha512Hash("111111").toHex(),email: 'admin@admin.com')
                admin.roles = [adminRole]
                admin.save()

                def guest = new User(username: "guest",name: 'guest', password:new Sha512Hash("111111").toHex(),email: 'guest@guest.com')
                guest.roles = [guestRole]
                guest.save()

                new User(username: "test1",name: 'test1', password:new Sha512Hash("111111").toHex(),email: 'test1@test.com').save()
                new User(username: "test2",name: 'test2',password:new Sha512Hash("111111").toHex(),email: 'test2@test.com').save()


                new DocumentType(name: '计划').save()
                new DocumentType(name: '总结').save()

                new Notification(user:admin,content: 'This is a test message').save()
                new Notification(user:admin,content: 'This is a test message too').save()
                break
            case Environment.PRODUCTION:
                ctx.setAttribute("env", "prod")
                break
        }
        ctx.setAttribute("foo", "bar")
    }
    def destroy = {
    }
}
