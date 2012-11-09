<%@include file="../includes/tags.jspf" %>

<hst:element var="googleMapsApiSensor" name="script">
  <hst:attribute name="type">text/javascript</hst:attribute>
  <hst:attribute name="src">http://maps.google.com/maps/api/js?sensor=true</hst:attribute>
</hst:element>
<hst:element var="googleMaps" name="script">
  <hst:attribute name="type">text/javascript</hst:attribute>
  <hst:attribute name="src">
    <hst:link path="/js/google_maps.js" />
  </hst:attribute>
</hst:element>

<hst:headContribution keyHint="api" element="${googleMapsApiSensor}" category="jsInternal"/>
<hst:headContribution keyHint="maps" element="${googleMaps}" category="jsInternal"/>
<hst:headContribution keyHint="mapsInit" element="${googleMapsInit}" category="jsInternal">
     <script type="text/javascript">
     initializeLocationAddress("${address}");
     </script>
</hst:headContribution>

<c:choose>
    <c:when test="${composermode && empty address}">
        <ul class="box-general">No content defined yet.</ul>
    </c:when>
    <c:otherwise>
        <param name="address" value="${address}"/>
        <param name="allowFullScreen" value="true"/>
        <param name="allowScriptAccess" value="always"/>
        <div class="box-general">
            <div id="map_canvas" style="width: 100%;"></div>
        </div>
    </c:otherwise>
</c:choose>