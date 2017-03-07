package gadmin

enum NotificationType {
    OA_TODO('待办流程',''),
    MSG('系统消息','')

    String display
    String url

    NotificationType(String display, String url) {
        this.display = display
        this.url = url
    }
}
