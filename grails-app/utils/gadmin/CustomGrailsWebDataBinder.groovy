package gadmin

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.GrailsDomainClass
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.DataBindingSource
import org.grails.databinding.events.DataBindingListener
import org.springframework.beans.factory.annotation.Autowired

class CustomGrailsWebDataBinder extends GrailsWebDataBinder{

    @Autowired
    CustomGrailsWebDataBinder(GrailsApplication grailsApplication) {
        super(grailsApplication)
    }

    @Override
    protected Object setPropertyValue(Object obj, DataBindingSource source, MetaProperty metaProperty, Object propertyValue, DataBindingListener listener) {
        def propName = metaProperty.name
        def domainClass = (GrailsDomainClass)grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, obj.getClass().name)
        if (domainClass != null) {
            def property = domainClass.getPersistentProperty propName
            if (property != null) {
                if (Collection.isAssignableFrom(property.type)) {
                    clearCollection(obj,propName,property.type)
                }
            }
        }
        return super.setPropertyValue(obj, source, metaProperty, propertyValue, listener)
    }

    protected clearCollection(obj, String propName, Class propertyType) {
        def coll = initializeCollection obj, propName, propertyType
        coll.clear()
    }
}