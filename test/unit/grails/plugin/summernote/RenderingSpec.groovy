package grails.plugin.summernote

import spock.lang.Specification
import grails.test.mixin.TestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin

/**
 * ToDo: class description
 *
 * Date: 24/11/15
 * Time: 20:05
 * @author ceo@closure.software
 * @since ToDo: set since version
 */
@TestMixin(GroovyPageUnitTestMixin)
class RenderingSpec extends Specification {

    void "test rendering editor simple"() {
        when:
        def result = render(template: '/summernote/editor', model: [ id: 'one' ] )

        then:
        result == """<div id="one"
></div>
<script type="application/javascript">
    \$(document).ready(function() {
        var \$summernote = \$("#one");
        var options = {};

        options.defaultFontName = 'Open Sans';
        options.fontNames = [
            'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',
            'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande',
            'Tahoma', 'Times New Roman', 'Verdana', 'Open Sans'
        ];

        //

        \$summernote.summernote( options );
    });
</script>"""
    }

    void "test rendering editor with value"() {
        when:
        def result = render(template: '/summernote/editor', model: [ id: 'one', value: '<b>Hi</b>' ] )

        then:
        result == """<div id="one"
><b>Hi</b></div>
<script type="application/javascript">
    \$(document).ready(function() {
        var \$summernote = \$("#one");
        var options = {};

        options.defaultFontName = 'Open Sans';
        options.fontNames = [
            'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',
            'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande',
            'Tahoma', 'Times New Roman', 'Verdana', 'Open Sans'
        ];

        //

        \$summernote.summernote( options );
    });
</script>"""
    }
}