package gadmin

class Notification {

    static mapping = {
        notificationType enumType: 'string'
        sort known:'asc',sendTime: "desc"
    }

    User user // 通知对象
    String content // 内容
    boolean known = false // 是否已读（默认显示未读的内容）
    NotificationType notificationType=NotificationType.MSG
    Date sendTime=new Date() // 发送时间

}

