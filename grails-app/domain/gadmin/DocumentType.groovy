package gadmin

class DocumentType {

    static constraints = {
        comment nullable: true
    }

    static mapping = {
        sort ordinal: "asc"
    }

    String name
    int ordinal=0
    String comment
}
