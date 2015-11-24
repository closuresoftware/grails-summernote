package grails.plugin.summernote

import org.springframework.web.servlet.support.RequestContextUtils

class SummernoteTagLib {

    static namespace = "summernote"

    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static availableLocales = [
            "ar": [ "AR" ],
            "bg": [ "BG" ],
            "ca": [ "ES" ],
            "cs": [ "CZ" ],
            "da": [ "DK" ],
            "de": [ "DE" ],
            "es": [ "ES", "EU" ],
            "fa": [ "IR" ],
            "fi": [ "FI" ],
            "fr": [ "FR" ],
            "he": [ "IL" ],
            "hu": [ "HU" ],
            "id": [ "ID" ],
            "it": [ "IT" ],
            "ja": [ "JP" ],
            "ko": [ "KR" ],
            "lt": [ "LT" ],
            "nb": [ "NO" ],
            "nl": [ "NL" ],
            "pl": [ "PL" ],
            "pt": [ "BR", "PT" ],
            "ro": [ "RO" ],
            "ru": [ "RU" ],
            "sk": [ "SK" ],
            "sl": [ "SI" ],
            "sr": [ "RS" ],
            "sv": [ "SE" ],
            "th": [ "TH" ],
            "tr": [ "TR" ],
            "uk": [ "UA" ],
            "vi": [ "VN" ],
            "zh": [ "CN", "TW" ]
    ]

    /**
     * Render summernote CSS assets.
     */
    def stylesheets = { attrs, body ->
        asset.stylesheet( href: "summernote.css" )
    }

    /**
     * Render summernote javascript assets. Including language if available.
     * @attr minified OPTIONAL boolean, true by default, use minified version
     * @attr locale OPTIONAL Locale object
     */
    def javascripts = { attrs, body ->
        if( attrs.minified == false ) { // not just null
            asset.javascript( src: "summernote.js" )
        }
        else {
            asset.javascript( src: "summernote.min.js" )
        }
        def locale = attrs.locale ?: RequestContextUtils.getLocale( request )
        def countries = availableLocales[locale.language]
        if( countries ) {
            if( countries.contains( locale.country ) ) {
                asset.javascript( src: "lang/summernote-${locale.language}-${locale.country}.js" )
            }
            else {
                asset.javascript( src: "lang/summernote-${locale.language}-${countries[0]}.js" )
            }
        }
    }

    /**
     * Render a summernote editor. The body can contain any number of summernote:option tags.
     * @attr id OPTIONAL html id
     * @attr value OPTIONAL String initial value
     */
    def editor = { attrs, body ->

        def model = [
                options: body()?.toString() ?: "",
                id: attrs.id,
                value: attrs.value
        ]

        out << g.render( plugin: "summernote", template: "summernote/editor", model: model )
    }

    /**
     * Render a summernote editor option, inside a summernote:editor tag. Option value can be set
     * either in the body or in the value attr.
     * @attr name REQUIRED option name
     * @attr value OPTIONAL option value, either this or a tag body must be specified
     * @attr quote OPTIONAL if true, the value will be surrounded by single quotes
     */
    def option = { attrs, body ->
        def name = attrs.name
        if( !name ) {
            throwTagError( "[summernote:option] required attribute 'name' missing" )
        }
        def value = body()
        if( value ) {
            value = value.toString().trim()
            if( value.length() == 0 ) {
                value = null
            }
        }
        if( !value ) {
            value = attrs.value?.toString()
        }
        if( !value ) {
            throwTagError( "[summernote:option] Either a tag body or a 'value' attribute must be provided" )
        }
        if( attrs.quote ) {
            value = "'" + value + "'"
        }
        out << raw("options.${raw(name)} = ${raw(value)};".toString())
    }
}
