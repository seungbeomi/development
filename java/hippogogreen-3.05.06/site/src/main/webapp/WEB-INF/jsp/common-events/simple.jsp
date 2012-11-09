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

<hippo-gogreen:title title="${document.title}"/>

<div class="yui-main">
  <div id="content" class="yui-b left-and-right">
    <div id="event-simple" class="about <c:if test="${preview}">editable</c:if>">

      <hst:cmseditlink hippobean="${document}" />
      <h2 class="title"><c:out value="${document.title}" /></h2>
      <p class="introduction">
        <c:out value="${document.summary}" />
      </p>

      <div class="yui-cssbase body">
        <hst:html hippohtml="${document.description}" />

        <hst:include ref="form"/>
      </div>

    </div>
  </div>
</div>