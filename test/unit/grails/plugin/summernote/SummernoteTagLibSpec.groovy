package grails.plugin.summernote

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(SummernoteTagLib)
class SummernoteTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test option tag"() {
        expect:
        shouldFail() {
            applyTemplate( '<summernote:option/>' )
        }
        shouldFail() {
            applyTemplate( '<summernote:option name="sample"/>' )
        }
        applyTemplate( '<summernote:option name="sample" value="\'Hi!\'"/>' ) == "options.sample = 'Hi!';"
        applyTemplate( '<summernote:option name="sample" value="Hi!" quote="${true}"/>' ) == "options.sample = 'Hi!';"
        applyTemplate( '<summernote:option name="sample">\'Hi!\'</summernote:option>' ) == "options.sample = 'Hi!';"
        applyTemplate( '<summernote:option name="sample" value="Hi!" quote="${true}">You</summernote:option>' ) == "options.sample = 'You';"
    }
}
