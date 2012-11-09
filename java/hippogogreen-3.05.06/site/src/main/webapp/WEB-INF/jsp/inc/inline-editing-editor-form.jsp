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

<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst' %>

<hst:cmseditlink hippobean='${document}' var="editlink" />

<form id="editorForm" method="post" action="<hst:resourceURL resourceId='/WEB-INF/jsp/common/detailpage-inline-edit-result.jsp' />">
  <div class="yui-skin-sam">
    <input type="hidden" name="nodepath" value="${document.path}"/>
    <input type="hidden" name="customnodepath" value=""/>
    <input type="hidden" name="field" value=""/>
    <input type="hidden" name="workflowAction" value=""/>
    <textarea id="editor" name="editor" class="inline-editor-editor" cols="50" rows="5"></textarea>
    
    <hst:link var="icons" path="/images/icons"/>
    <span id="editorToolbar" class="inline-editor-toolbar">
      <img src="${icons}/document-save-16.png" id="editorToolbar_save" alt="Save" title="Save"/>
      <img src="${icons}/document-revert-16.png" id="editorToolbar_close" alt="Close without saving" title="Close without saving"/>
      <img src="${icons}/workflow-requestpublish-16.png" id="editorToolbar_requestPublication" alt="Request publication" title="Request publication"/>
      <img src="${icons}/edit-16.png" id="editorToolbar_editInCMS" alt="Edit in CMS" title="Edit in CMS"/>
    </span>
  </div>
</form>

<script type="text/javascript" language="javascript">
<!--
//Instantiate and configure Loader:
var loader = new YAHOO.util.YUILoader({

    // Identify the components you want to load.  Loader will automatically identify
    // any additional dependencies required for the specified components.
    require: ["container", "menu", "button", "editor", "json", "resize"],

    // Configure loader to retrieve the libraries locally
    base: '<hst:link path="/javascript/yui"/>/',

    // Configure loader to pull in optional dependencies.  For example, animation
    // is an optional dependency for slider.
    loadOptional: true,

    // The function to call when all script/css resources have been loaded
    onSuccess: function() {
        //this is your callback function; you can use
        //this space to call all of your instantiation
        //logic for the components you just loaded.
        
        // Load the edited css for the inline editor.
        // This must be done after the loader has been fully instantiated, so the css loads
        // after the default css (skin.css). That way, the element classes can be overwritten
        // by our own css file.
        var addcss=document.createElement("link")
        addcss.setAttribute("rel", "stylesheet")
        addcss.setAttribute("type", "text/css")
        addcss.setAttribute("href", "<hst:link path='/css/inline-editing.css'/>")
        document.getElementsByTagName("head")[0].appendChild(addcss);

        // Initialize editor
        init_inline_editor("editable_cont", "editorForm", "editor", "editor2", "editorToolbar", "<c:out value="${editlink}"/>&amp;mode=edit");
    },

    // Configure the Get utility to timeout after 10 seconds for any given node insert
    timeout: 10000
});

// Load the files using the insert() method. The insert method takes an optional
// configuration object, and in this case we have configured everything in
// the constructor, so we don't need to pass anything to insert().
loader.insert();
-->
</script>
