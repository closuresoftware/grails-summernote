<%
  def divId = id ?: "summernote_${Long.toString(System.currentTimeMillis(),Character.MAX_RADIX)}"
%><div id="${divId}"
><g:if test="${value}">${raw(value)}</g:if></div>
<script type="application/javascript">
    $(document).ready(function() {
        var $summernote = $("#${divId}");
        var options = {};

        options.defaultFontName = 'Open Sans';
        options.fontNames = [
            'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',
            'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande',
            'Tahoma', 'Times New Roman', 'Verdana', 'Open Sans'
        ];

        ${raw(options)}//{%-- don't remove the comment slashes comment, they're needed for testing --%}

        $summernote.summernote( options );
    });
</script>