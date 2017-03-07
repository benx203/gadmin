package gadmin

class Module {

    static constraints = {
        parent nullable: true
        uri nullable: true
        icon nullable: true
    }

    static mapping = {
        sort ordinal: "asc"
    }

    Module parent

    String name
    String permission
    int ordinal=0

    boolean menu=false
    String uri
    String icon

    Module(String name) {
        this.name = name
    }

    Module(String name, String permission,int ordinal) {
        this(name,permission,null,ordinal,null)
    }

    Module(String name, String permission,int ordinal,boolean menu) {
        this(name,permission,null,ordinal,null)
        this.menu = menu
    }

    Module(String name, String permission,int ordinal,boolean menu,String icon) {
        this(name,permission,ordinal,menu)
        this.icon = icon
    }

    Module(String name, String permission,int ordinal,String uri) {
        this(name,permission,null,ordinal,uri)
    }

    Module(String name, String permission,int ordinal,String uri,boolean menu) {
        this(name,permission,null,ordinal,uri)
        this.menu = menu
    }

    Module(String name, String permission,Module parent,int ordinal) {
        this(name,permission,parent,ordinal,null)
    }

    Module(String name, String permission,Module parent,int ordinal,boolean menu) {
        this(name,permission,parent,ordinal,null)
        this.menu = menu
    }

    Module(String name, String permission,Module parent,int ordinal,String uri) {
        this.name = name
        this.permission = permission
        this.parent = parent
        this.ordinal = ordinal
        this.uri = uri
        this.menu = uri?true:false
    }
}
