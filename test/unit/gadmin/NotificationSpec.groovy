package gadmin

import grails.test.mixin.TestFor
import grails.test.runtime.FreshRuntime
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@FreshRuntime
@TestFor(Notification)
class NotificationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when:
        def user = new User(username: 'aaa',email: 'aa',password: 'aaa').save()
        new Notification(user: user,content: 'aaa').save()
        then:
        Notification.list().size() == 1
    }
}
