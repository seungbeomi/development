<%--

    Copyright (C) 2010 Hippo B.V.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>

<%@include file="../includes/tags.jspf" %>

<c:set var="jobstitle"><fmt:message key="jobs.main.title"/></c:set>
<hippo-gogreen:title title="${jobstitle}"/>

<c:set var="datePattern" value="dd-MM-yyyy"/>
<div class="yui-main">
    <div class="right more-padding-left" id="content">
        <ol id="breadcrumbs">
            <li><fmt:message key="jobs.main.location.label"/></li>
            <li>
              <hst:link var="home" siteMapItemRefId="home" />
              <a href="${home}"><fmt:message key="jobs.main.location.home"/></a> &gt;
            </li>
        </ol>
        <h2><fmt:message key="jobs.main.title"/></h2>

        <div id="jobs-results">
            <c:if test="${docs.total gt 0}">
              <span id="results-summary">
            	<fmt:message key="jobs.main.pagerinfo">
            		<fmt:param value="${(docs.startOffset+1)}"/>
            		<fmt:param value="${docs.endOffset}"/>
            		<fmt:param value="${docs.total}"/>
            	</fmt:message>            
              </span>
            </c:if>
            <c:forEach items="${docs.items}" var="doc">
                <ul class="job-result <c:if test="${preview}">editable</c:if>">
                    <c:if test="${preview}">
                      <li><hst:cmseditlink hippobean="${doc}"/></li>
                    </c:if>
                    <li class="title"><a href="<hst:link hippobean="${doc}"/>"><c:out value="${doc.title}"/></a></li>
                    <li class="date"><span class="seperator">|</span> <fmt:formatDate pattern="${datePattern}" value="${doc.closingDate}"/></li>

                    <li class="text"><c:out value="${doc.summary}"/></li>
                </ul>
            </c:forEach>
        </div>
        <c:choose>
          <c:when test="${docs.total eq 0}">
            <p id="results"><fmt:message key="search.results.noresults"/> '${query}'</p>
          </c:when>
          <c:otherwise>
            <hippo-gogreen:pagination pageableResult="${docs}" queryName="query" queryValue="${query}"/>
          </c:otherwise>
        </c:choose>

    </div>
</div>
