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

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul id="page-actions">
    <li class="function email"><a href="javascript:sendMail();" title="<fmt:message key="shareblock.email" />"></a></li>
    <li class="function print"><a href="javascript:window:print();" title="<fmt:message key="shareblock.print" />"></a></li>
    <li class="function bookmark"><a href="javascript:bookmark();" title="<fmt:message key="shareblock.bookmark" />"></a></li>
    <li class="function share">
      <a class="a2a_dd" href="http://www.addtoany.com/share_save"></a>
       <hst:headContribution keyHint="addtoanyLocale" category="jsExternal">
         <script type="text/javascript">
           var a2a_config = a2a_config || {};
           a2a_config.locale = "${pageContext.request.locale.language}";
         </script>
      </hst:headContribution>
      <hst:headContribution keyHint="addtoany" category="jsExternal">
        <script type="text/javascript" src="http://static.addtoany.com/menu/page.js"></script>
      </hst:headContribution>
    </li>
</ul>
