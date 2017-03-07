package gadmin

class MultiNotification {

    static constraints = {
    }

    static mapping = {sort createTime: "desc"}

    static hasMany = [users:User]

    String content
    Date createTime=new Date()
}
