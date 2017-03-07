package gadmin

import grails.plugin.cache.GrailsCacheManager
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class CacheUtil implements ApplicationContextAware{
    GrailsCacheManager grailsCacheManager

    def evictUser(String name){
        userCache().evict(name)
        roleCache().evict(name)
        permissionCache().evict(name)
        menuCache().evict(name)
    }

    def user(String name){
        def user = userCache().get(name)?.get()
        if(!user){
            user = User.findByUsername(name)
            if(user){
                userCache().put(name,user)
            }
        }
        user
    }

    def roles(String name){
        def roles = roleCache().get(name)?.get()
        if(!roles){
            def user = user(name)
            roles = User.withCriteria {
                eq("username", name)
            }
            if(roles){
                roleCache().put(name,roles)
            }
        }
        roles
    }

    def permissions(String name){
        def permissions = permissionCache().get(name)?.get()
        if(!permissions){
            def user = User.findByUsername(name)
            def modules = user?.roles?.modules[0]
            if(modules){
                putMenus(name,modules)
            }
            permissions = modules?.permission

            // If has '*:create',add '*:save'.
            permissions?.findAll{it.contains(':create')}.each {
                String[] arr = it.split(':')
                permissions.add(arr[0] + ':save')
            }

            // If has '*:edit',add '*:update'.
            permissions?.findAll{it.contains(':edit')}.each {
                String[] arr = it.split(':')
                permissions.add(arr[0] + ':update')
            }

            if(permissions){
                permissionCache().put(name,permissions)
            }
        }
        permissions
    }

    def putMenus(name,modules){
        for (Module module  : modules) {
            if(module.permission == '*:*'){
                modules = Module.list()
                break
            }
        }

        def menus = []
        modules.each{Module module->
            if(module.menu){
                menus.add([id:module.id,name:module.name,pid:module.parent?.id?:'',ordinal:module.ordinal,uri:module.uri,icon:module.icon])
            }
        }
        menuCache().put(name,menus)
    }

    def menus(String name){
        menuCache().get(name)?.get()
    }

    def userCache(){
        grailsCacheManager.getCache('user')
    }

    def roleCache(){
        grailsCacheManager.getCache('role')
    }

    def permissionCache(){
        grailsCacheManager.getCache('permission')
    }

    def menuCache(){
        grailsCacheManager.getCache('menu')
    }

    @Override
    void setApplicationContext(ApplicationContext context) throws BeansException {
        grailsCacheManager = context.getBean('grailsCacheManager')
    }
}
