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

<c:set var="productsmaintitle"><fmt:message key="products.main.title"/></c:set>
<hippo-gogreen:title title="${productsmaintitle}"/>

<div id="bd" class="yui-t1">
    <!-- left -->
    <hst:include ref="leftnav"/>

    <div class="yui-main">
        <div id="content" class="yui-b left-and-right">
            <hst:include ref="content"/>
        </div>
    </div>

    <!-- products facet navigation -->
    <hst:include ref="right"/>
</div>
