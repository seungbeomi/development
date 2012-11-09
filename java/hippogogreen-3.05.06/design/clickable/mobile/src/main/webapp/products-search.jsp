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
    <jsp:param name="page" value="search"/>
</jsp:include>
        
        <!-- search navigation -->
        <ul id="filter-by">
            <li id="search">
                <form method="get" action="#">
                    <p>
                        <input type="text" value="Search" class="search-field gray">
                        <input type="submit" value="Search" class="search-button" >
                    </p>
                </form>
            </li>
            
            <%-- Some filters are expanded/selected by default. This is only to provide a
            basic example of which classes are used and how they're used. All filters should be
            unexpanded and unselected/inactive in the site by default --%>
            <li class="expanded"><span><span class="name">122 results <span class="separator">|</span></span><a href="#">Filter results</a></span>
                <ul class="subfilter">
                    <li class="clear"><span><span class="name">Price:</span><a class="hidden" href="#">Any price</a>
                        <a class="filter" href="#">&euro; 100 - &euro; 250 (6)</a></span><a class="clear" href="#" title="Clear filter: &gt; 100 &euro;">&nbsp;</a>
                        <ul class="subsubfilter">
                            <li><a href="#">&euro; 0 - &euro; 25 (15)</a></li>
                            <li><a href="#">&euro; 25 - &euro; 50 (14)</a></li>
                            <li><a href="#">&euro; 50 - &euro; 100 (8)</a></li>
                            <li><a href="#">&euro; 100 - &euro; 250 (6)</a></li>
                            <li><a href="#">&euro; 250 - &euro; 500 (2)</a></li>
                            <li><a href="#">&euro; 500 and more (1)</a></li>
                        </ul>
                    </li>
                    <li><span><span class="name">Product category:</span><a href="#">Any category</a></span>
                        <ul class="subsubfilter">
                            <li><a href="#">Reuse (13)</a></li>
                            <li><a href="#">Food (10)</a></li>
                            <li><a href="#">Animal &amp; Garden (9)</a></li>
                            <li><a href="#">Health care (6)</a></li>
                            <li><a href="#">Clothing (5)</a></li>
                            <li><a href="#">Transport (5)</a></li>
                        </ul>
                     </li>
                    <li><span><span class="name">Score:</span><a href="#">Any score</a></span>
                        <ul class="subsubfilter">
                            <li><a href="#">1 star or more (6)</a></li>
                            <li><a href="#">2 stars or more (6)</a></li>
                            <li><a href="#">3 stars or more (4)</a></li>
                            <li><a href="#">4 stars or more (2)</a></li>
                            <li><a href="#">5 stars (1)</a></li>
                        </ul>
                     </li>
                     <li class="expanded"><span><span class="name">New since:</span><a href="#">Forever</a></span>
                        <ul class="subsubfilter">
                            <li><a href="#">Last week (10)</a></li>
                            <li><a href="#">Last month (20)</a></li>
                            <li><a href="#">Last 3 months (42)</a></li>
                            <li><a href="#">Last year (50)</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li id="sort-by" class="last"><span><span class="name">Sort by:</span><a href="#">Relevance</a></span>
                <ul class="subsubfilter">
                    <li><a href="#">Name</a></li>
                    <li><a href="#">Date</a></li>
                    <li><a href="#">Most popular</a></li>
                    <li><a href="#">Most recent</a></li>
                    <li><a href="#">Price: high to low</a></li>
                    <li><a href="#">Price: low to high</a></li>
                </ul>
            </li>
        </ul>
        
        <!-- search results -->
        <div id="content">
          <div id="products" class="results">
            <ul class="product-item">
                <li class="full-link"><a href="products-detail.jsp"></a></li>
                <li class="image"><img src="images/content-images/lamp.png"></li>
                <li class="title"><a href="products-detail.jsp">Energy-efficient lamp</a></li>
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
