package gadmin

class Document {

    static constraints = {
        comment nullable: true
    }

    DocumentType documentType
    String title
    int year
    String comment
    String attachment
    Date updateTime=new Date()
}
