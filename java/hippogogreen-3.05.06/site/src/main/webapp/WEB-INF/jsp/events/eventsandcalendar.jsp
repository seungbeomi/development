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
<jsp:useBean id="now" class="java.util.Date" />

<c:set var="overviewtitle"><fmt:message key="events.overview.title"/></c:set>
<hippo-gogreen:title title="${overviewtitle}"/>

<!-- content -->
<div class="yui-main">
    <div id="content" class="yui-b left-and-right">
        <ol id="breadcrumbs">
            <li><fmt:message key="events.overview.location.label"/></li>
            <li>
              <hst:link var="home" siteMapItemRefId="home" />
              <a href="${home}"><fmt:message key="events.overview.location.home"/></a> &gt;
            </li>
        </ol>

        <h2><fmt:message key="events.overview.title"/></h2>
        <c:forEach var="document" items="${documents.items}">
            <ul class="event-item<c:if test="${preview}"> editable</c:if>">
                <hst:link var="link" hippobean="${document}"/>
                <li class="title">
                  <hst:cmseditlink hippobean="${document}" />
                  <a href="${link}"><c:out value="${document.title}"/></a>
                </li>
                <li class="day"><fmt:formatDate value="${document.date.time}" pattern="dd"/></li>
                <li class="month"><fmt:formatDate value="${document.date.time}" pattern="MMM"/></li>
                <li class="text"><c:out value="${document.summary}"/></li>
            </ul>
        </c:forEach>
        <hippo-gogreen:pagination pageableResult="${documents}" />
    </div>
</div>

<!-- right -->
<div id="right" class="yui-b">
    <div id="events">
        <h2><fmt:message key="events.calendar.title"/></h2>
        <h3><c:out value="${calendar.monthName}"/>&nbsp;<c:out value="${calendar.year}"/></h3>
        <hst:renderURL var="prev">
            <hst:param name="month" value="${calendar.prevMonth}"/>
            <hst:param name="year" value="${calendar.prevMonthYear}"/>
        </hst:renderURL>
        <span class="nav prev"><a href="${prev}"><fmt:message key="events.calendar.previous"/></a></span>
        <hst:renderURL var="next">
            <hst:param name="month" value="${calendar.nextMonth}"/>
            <hst:param name="year" value="${calendar.nextMonthYear}"/>
        </hst:renderURL>
        <span class="nav next"><a href="${next}"><fmt:message key="events.calendar.next"/></a></span>
        <table id="calendar">
            <tr>
                <th><fmt:message key="events.calendar.monday"/></th>
                <th><fmt:message key="events.calendar.tuesday"/></th>
                <th><fmt:message key="events.calendar.wednesday"/></th>
                <th><fmt:message key="events.calendar.thursday"/></th>
                <th><fmt:message key="events.calendar.friday"/></th>
                <th><fmt:message key="events.calendar.saturday"/></th>
                <th><fmt:message key="events.calendar.sunday"/></th>
            </tr>
            <c:forEach var="week" items="${calendar.weeks}">
                <tr>
                    <c:forEach var="day" items="${week.days}">
                        <c:choose>
                            <c:when test="${day.dummy}"><td class="disabled"></td></c:when>
                            <c:when test="${day.numberOfEvents > 0}">
                                <td class="event box">
                                    <hst:link var="imgLink" path="/images/box-event-arrow.png"/>
                                    <img src="${imgLink}" class="arrow"/>
                                    <a href="#"><c:out value="${day.dayOfMonth}"/></a>
                                        <c:if test="${day.numberOfEvents >1}"><span class="count"><c:out value="${day.numberOfEvents}"/></span></c:if>
                                        <ul class="info">
                                            <li class="top"></li>
                                            <li class="date-title"><c:out value="${day.dayOfMonth}"/>&nbsp;<c:out value="${calendar.monthName}"/>&nbsp;<c:out value="${calendar.year}"/></li>
                                            <c:forEach var="event" items="${day.events}" begin="0" end ="1">
                                                <hst:link var="eventLink" hippobean="${event}" />
                                                <li class="title"><a href="${eventLink}"><c:out value="${event.title}"/></a></li>
                                                <li class="text"><c:out value="${event.summary}"/></li>
                                            </c:forEach>
                                            <li class="bottom"></li>
                                        </ul></td>
                                    </c:when>
                                    <c:otherwise><td><c:out value="${day.dayOfMonth}"/></td></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
