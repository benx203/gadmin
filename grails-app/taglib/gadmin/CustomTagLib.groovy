package gadmin

class CustomTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /**
     *@attr beans REQUIRED the field beans
     *@attr bean REQUIRED the field bean
     */
    def exist = { attrs, body ->
        if (attrs.beans.collect{it.id}.contains(attrs.bean.id)) {
            out << body()
        }
    }
}
