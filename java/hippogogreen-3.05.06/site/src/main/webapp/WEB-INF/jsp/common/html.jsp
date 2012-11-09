<%@include file="../includes/tags.jspf" %>
<c:choose>
  <c:when test="${composermode && empty htmlContent}">
    <ul class="box-general">No content defined yet.</ul>
  </c:when>
  <c:otherwise>${htmlContent}</c:otherwise>
</c:choose>


