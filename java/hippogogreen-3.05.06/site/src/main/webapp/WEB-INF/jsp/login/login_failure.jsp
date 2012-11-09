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

<%@ taglib prefix='hst' uri="http://www.hippoecm.org/jsp/hst/core" %>

<hst:link var="redirectUrl" path="/">
  <hst:param name="login" value="true"/>
  <hst:param name="error" value="true"/>
</hst:link>
<html>
  <head>
    <meta http-equiv="refresh" content="0;URL=${redirectUrl}" />
  </head>
<body>
</body>
</html>
