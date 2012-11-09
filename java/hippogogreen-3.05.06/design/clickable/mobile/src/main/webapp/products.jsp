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
    <jsp:param name="page" value="popular"/>
</jsp:include>

    <!-- search results -->
    <div id="content">
        <div id="products" class="results">
            <ul class="product-item">
                <li class="full-link"><a href="products-detail.jsp"></a></li>
                <li class="image"><img src="images/content-images/lamp.png"></li>
                <li class="title"><a href="products-detail.jsp">Energy-efficient lamp </a></li>
                <li class="price"><span>&euro; 12,95 </span>|</li>
                <li class="rating stars-42"><span>4.2</span></li>
                <li class="clr"></li>
            </ul>
            <ul class="product-item">
                <li class="full-link"><a href="products-detail.jsp"></a></li>
                <li class="image"><img src="images/content-images/lamp.png"></li>
                <li class="title"><a href="products-detail.jsp">Energy-efficient lamp</a></li>
                <li class="price"><span>&euro; 12,95 </span>|</li>
                <li class="rating stars-17"><span>1.7</span></li>
                <li class="clr"></li>
            </ul>
            <ul class="product-item">
                <li class="full-link"><a href="products-detail.jsp"></a></li>
                <li class="image"><img src="images/content-images/lamp.png"></li>
                <li class="title"><a href="products-detail.jsp">Energy-efficient lamp</a></li>
                <li class="price"><span>&euro; 12,95 </span>|</li>
                <li class="rating stars-34"><span>3.4</span></li>
                <li class="clr"></li>
            </ul>
            <ul class="product-item">
                <li class="full-link"><a href="products-detail.jsp"></a></li>
                <li class="image"><img src="images/content-images/lamp.png"></li>
                <li class="title"><a href="products-detail.jsp">Energy-efficient lamp</a></li>
                <li class="price"><span>&euro; 12,95 </span>|</li>
                <li class="rating stars-36"><span>3.6</span></li>
                <li class="clr"></li>
            </ul>
        </div>
        
        <div id="show-more">
            <a href="#">Show 25 more...</a>
        </div>
    </div>

<jsp:include page="includes/footer.jsp"/>
