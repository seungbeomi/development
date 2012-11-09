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

<%@include file="../includes/tags.jspf"%>

<div id="left-nav">
  <ul>
    <c:forEach var="item1" items="${menu.menuItems}">
      <li>
         <hst:link var="link1" link="${item1.hstLink}" />
         <a href="${link1}"><c:out value="${item1.name}"/></a>
         <c:if test="${item1.expanded and not empty item1.childMenuItems}">
          <ul class="sub-sec">
            <c:forEach var="item2" items="${item1.childMenuItems}">
              <li>
                <hst:link var="link2" link="${item2.hstLink}" />
                <a href="${link2}"><c:out value="${item2.name}"/></a>
                <c:if test="${item2.expanded and not empty item2.childMenuItems}">
                  <ul>
                    <c:forEach var="item3" items="${item2.childMenuItems}">
                      <li>
                        <hst:link var="link3" link="${item3.hstLink}" />
                        <a href="${link3}"><c:out value="${item3.name}"/></a>
                        <c:if test="${item3.expanded and not empty item3.childMenuItems}">
                          <ul>
                            <c:forEach var="item4" items="${item3.childMenuItems}">
                              <li>
                                <hst:link var="link4" link="${item4.hstLink}" />
                                <a href="${link4}"><c:out value="${item4.name}"/></a>
                              </li>
                            </c:forEach>
                          </ul>
                        </c:if>
                      </li>
                    </c:forEach>
                  </ul>
                </c:if>
              </li>
            </c:forEach>
          </ul>
        </c:if>
      </li>
    </c:forEach>
  </ul>
</div>
