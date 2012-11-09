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

<jsp:include page="includes/header.jsp">
    <jsp:param name="section" value="products"/>
    <jsp:param name="page" value="detail"/>
</jsp:include>
    
        <script type="text/javascript">
        $(document).ready(function() {
            $('#gallery').galleryView({
                show_panels: true,                    //BOOLEAN - flag to show or hide panel portion of gallery
                show_filmstrip: true,                //BOOLEAN - flag to show or hide filmstrip portion of gallery

                panel_width: 210,                    //INT - width of gallery panel (in pixels)
                panel_height: 192,                    //INT - height of gallery panel (in pixels)
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
    
    <div id="content" class="detail">
        <h1>Energy-efficient lamp</h1>
        <p id="product-info" class="doc-info">
          <span class="price">&euro; 12,95</span>
          <span class="seperator">|</span>
          <span class="rating stars-17">1.7</span>
        </p>
        
        <!-- image gallery -->
        <div id="gallery-container">
          <ul id="gallery">
              <li><img src="images/content-images/gallery-1.jpg" alt="gallery 1" title="gallery 1"/></li>
              <li><img src="images/content-images/gallery-2.jpg" alt="gallery 2" title="gallery 2"/></li>
              <li><img src="images/content-images/gallery-3.jpg" alt="gallery 3" title="gallery 3"/></li>
              <li><img src="images/content-images/gallery-4.jpg" alt="gallery 4" title="gallery 4"/></li>
          </ul>
          <p class="copyright">&lt;image copyright&gt;</p>
        </div>
        
        <p class="date">feb 24, 2010</p>
        <p class="summary">&lt;summary&gt; Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor labore et dolore magna aliqua.</p>
        <p class="description">&lt;description&gt; Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation magna dolor sed elit, ullamco tempor consequat.</p>
        <p class="copyright">&lt;text copyright&gt;</p>
    </div>  

<jsp:include page="includes/footer.jsp"/>
