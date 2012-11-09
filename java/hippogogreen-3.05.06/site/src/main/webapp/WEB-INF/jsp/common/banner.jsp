<%@include file="../includes/tags.jspf" %>
<%--@elvariable id="document" type="com.onehippo.gogreen.beans.Banner"--%>
<ul class="banner">
  <c:if test="${not empty document.docLink}">
    <li class="full-link">
      <a href="<hst:link hippobean="${document.docLink}"/>"></a>
    </li>
    <li class="title">
      <a href="<hst:link hippobean="${document.docLink}"/>"><c:out value="${document.title}"/></a>
    </li>
    <li class="image">
        <img src="<hst:link hippobean="${document.image.original}"/>" alt="<fmt:message key="common.banner.image.alt"/>" />
    </li>
  </c:if>
</ul>