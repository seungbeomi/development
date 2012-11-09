<%@include file="../includes/tags.jspf" %>
<div id="home-news">

    <div id="slider">
        <ul class="navigation" style="z-index:1;">
            <%--@elvariable id="banners" type="java.util.List"--%>
            <c:forEach items="${banners}" var="banner" varStatus="index">
                <%--@elvariable id="banner" type="com.onehippo.gogreen.beans.Banner"--%>
                <li><a href="#banner-${index.count}"><c:out value="${banner.title}"/></a></li>
            </c:forEach>
        </ul>
        <div class="scroll">
            <div class="scrollContainer">
                <c:forEach items="${banners}" var="banner" varStatus="index">
                    <%--@elvariable id="banner" type="com.onehippo.gogreen.beans.Banner"--%>
                    <div class="panel" id="doc-${index.count}"
                         style="background: url(<hst:link hippobean="${banner.image.original}"/>) repeat-y !important; ">
                        <div class="title"><a href="<hst:link hippobean="${banner.docLink}"/>"><c:out
                                value="${banner.title}"/></a></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>