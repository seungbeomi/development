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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${not composermode}">
<hst:headContribution keyHint="jqueryEasing" category="jsInternal">
    <hst:link path="/js/galleryview-2.1.1/jquery.easing.1.3.js" var="jqEasingJs"/>
    <script type="text/javascript" src="${jqEasingJs}"></script>
</hst:headContribution>

<hst:headContribution keyHint="jqueryGalleryView" category="jsInternal">
    <hst:link path="/js/galleryview-2.1.1/jquery.galleryview-2.1.1-pack.js" var="jqGalleryViewJs"/>
    <script type="text/javascript" src="${jqGalleryViewJs}"></script>
</hst:headContribution>

<hst:headContribution keyHint="jqueryTimer" category="jsInternal">
    <hst:link path="/js/galleryview-2.1.1/jquery.timers-1.2.js" var="jqTimersJs"/>
    <script type="text/javascript" src="${jqTimersJs}"></script>
</hst:headContribution>

<hst:headContribution keyHint="galleryViewCss" category="css">
    <hst:link var="galleryViewCss" path="/js/galleryview-2.1.1/galleryview.css"/>
    <link rel="stylesheet" media="screen" type="text/css" href="${galleryViewCss}"/>
</hst:headContribution>

<hst:headContribution keyHint="loadGallery" category="jsInternal">
    <script type="text/javascript">
        $(document).ready(function() {
            $('#gallery').galleryView({
                show_panels: true,                    //BOOLEAN - flag to show or hide panel portion of gallery
                show_filmstrip: true,                //BOOLEAN - flag to show or hide filmstrip portion of gallery

                panel_width: 400,                    //INT - width of gallery panel (in pixels)
                panel_height: 300,                    //INT - height of gallery panel (in pixels)
                frame_width: 40,                    //INT - width of filmstrip frames (in pixels)
                frame_height: 30,                    //INT - width of filmstrip frames (in pixels)

                start_frame: 1,                        //INT - index of panel/frame to show first when gallery loads
                filmstrip_size: 3,
                transition_speed: 800,                //INT - duration of panel/frame transition (in milliseconds)
                transition_interval: 4000,            //INT - delay between panel/frame transitions (in milliseconds)

                overlay_opacity: 0.7,                //FLOAT - transparency for panel overlay (1.0 = opaque, 0.0 = transparent)
                frame_opacity: 0.3,                    //FLOAT - transparency of non-active frames (1.0 = opaque, 0.0 = transparent)

                pointer_size: 4,                    //INT - Height of frame pointer (in pixels)

                nav_theme: 'light',                    //STRING - name of navigation theme to use (folder must exist within 'themes' directory)
                easing: 'swing',                    //STRING - easing method to use for animations (jQuery provides 'swing' or 'linear', more available with jQuery UI or Easing plugin)

                filmstrip_position: 'bottom',        //STRING - position of filmstrip within gallery (bottom, top, left, right)
                overlay_position: 'bottom',            //STRING - position of panel overlay (bottom, top, left, right)

                panel_scale: 'nocrop',                //STRING - cropping option for panel images (crop = scale image and fit to aspect ratio determined by panel_width and panel_height, nocrop = scale image and preserve original aspect ratio)
                frame_scale: 'nocrop',                //STRING - cropping option for filmstrip images (same as above)

                frame_gap: 5,                        //INT - spacing between frames within filmstrip (in pixels)

                show_captions: false,                //BOOLEAN - flag to show or hide frame captions
                fade_panels: true,                    //BOOLEAN - flag to fade panels during transitions or swap instantly
                pause_on_hover: false                //BOOLEAN - flag to pause slideshow when user hovers over the gallery

            });
        });

    </script>
</hst:headContribution>
</c:if>


<div class="clear">
    <c:choose>
       <c:when test="${composermode}">
           <c:forEach items="${document.images}" var="image" varStatus="status">
               <c:if test="${status.index eq 0}">
                   <hst:link hippobean="${image.largeThumbnail}" var="imgLink"/>
                   <img src="${imgLink}" alt="${fn:escapeXml(image.alt)}"/>
               </c:if>
           </c:forEach>
       </c:when>
        <c:otherwise>
            <ul id="gallery">
        <%--@elvariable id="galleryItems" type="java.util.List"--%>
        <c:forEach items="${document.images}" var="image">
            <%--@elvariable id="image" type="com.onehippo.gogreen.beans.compound.ImageSet"--%>
            <hst:link hippobean="${image.largeThumbnail}" var="imgLink"/>
            <li><img src="${imgLink}" alt="${fn:escapeXml(image.alt)}"/></li>
        </c:forEach>
    </ul>
        </c:otherwise>

    </c:choose>



</div>