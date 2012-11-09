<%@include file="../includes/tags.jspf" %>

<script>
$(document).ready(function(){
	  $('embed').each(function(){
      $(this).height($(this).width()* 0.75  );
	  });
	});
</script>

<c:choose>
  <c:when test="${composermode && empty videoURL}">
    <ul class="box-general">No content defined yet.</ul>
  </c:when>
  <c:otherwise>
    <object class="youtube">
      <param name="movie" value="${videoURL}"/>
      <param name="allowFullScreen" value="true"/>
      <param name="allowScriptAccess" value="always"/>
      <embed src="${videoURL}" type="application/x-shockwave-flash" allowfullscreen="true"
        allowScriptAccess="always" wmode="opaque"/>
    </object>
  </c:otherwise>
</c:choose>