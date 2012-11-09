<%--

    Copyright (C) 2011 Hippo B.V.

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
<% request.setAttribute("user", request.getSession().getAttribute("user")); %>
<% if (request.isUserInRole("reseller")) { %>
  <ul class="box-general reseller-contact">
    <li class="pic"><img src="<hst:link path="/images/johnny.png"/>"/></li>
    <li class="message">
      <fmt:message key="contact.reseller.welcome">
        <fmt:param value="${user.firstname}"/>
      </fmt:message>
    </li>
    <li class="action contact">
      <a onclick="window.open(this.href, 'chatbox', 'width=355,height=350,scrollbars=0,location=0'); return false;" href="<hst:link path="/images/chat.png"/>"><fmt:message key="contact.reseller.contact"/></a>
    </li>
    <li class="action faq">
      <a href="<hst:link path="/resellerfaq"/>"><fmt:message key="contact.reseller.gotofaq"/></a>
    </li>
  </ul>
<% } %>
