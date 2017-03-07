package gadmin

class User {
    static constraints = {
        username nullable: false, blank: false, unique: true
        email blank: false, email: true
        password blank: false

        name(nullable:true)
        phone(nullable:true)
        nickname(nullable:true)
        comment(nullable:true)
    }

    static mapping = {
        sort ordinal: "asc"
    }

    static hasMany = [ roles: Role]

    String username
    String password
    String name
    String email
    String phone
    String nickname
    String comment
    boolean enabled = true
    int ordinal=0
}
