package gadmin

class Role {
    static constraints = {
        name nullable: false, blank: false, unique: true
        comment nullable: true
    }

    static mapping = {
        sort ordinal: "asc"
    }

    static hasMany = [modules:Module]

    String name
    int ordinal=0
    String comment

    Role(String name) {
        this.name = name
    }
}
