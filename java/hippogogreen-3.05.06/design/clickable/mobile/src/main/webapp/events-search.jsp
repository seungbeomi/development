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
    <jsp:param name="section" value="events"/>
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
            <li class="expanded"><span><span class="name">43 results <span class="separator">|</span></span><a href="#">Filter results</a></span>
                <ul class="subfilter">
                    <li class="clear"><span><span class="name">Date:</span><a class="hidden" href="#">Any date</a>
                        <a class="filter" href="#">Upcoming</a></span><a class="clear" href="#" title="Clear filter: Upcoming">&nbsp;</a>
                        <ul class="subsubfilter">
                            <li><a href="#">Upcoming</a></li>
                            <li><a href="#">Past</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li id="sort-by" class="last"><span><span class="name">Sort by:</span><a href="#">Relevance</a></span>
                <ul class="subsubfilter">
                    <li><a href="#">Name</a></li>
                    <li><a href="#">Date</a></li>
                </ul>
            </li>
        </ul>
        
        <!-- search results -->
    <div id="content">
        <div id="events" class="results">
            <ul class="event-item">
                <li class="full-link"><a href="events-detail.jsp"></a></li>
                <li class="calendar"><img src="images/bg-calendar.png"/>
                    <span class="month">Nov</span>
                    <span class="day">30</span>
                </li>
                <li class="gmaps"><a target="_blank"
                   href="http://maps.google.com/?ll=52.359832,4.901393">
                    <img src="http://maps.google.com/maps/api/staticmap?&zoom=10&size=150x100&maptype=roadmap&markers=color:green|52.359832,4.901393&sensor=true"/>
                </a></li>
                <li class="title"><a href="events-detail.jsp">NextGenFuels 2010</a></li>
            </ul>
            <ul class="event-item">
                <li class="full-link"><a href="events-detail.jsp"></a></li>
                <li class="calendar"><img src="images/bg-calendar.png"/>
                    <span class="month">Jul</span>
                    <span class="day">23</span>
                </li>
                <li class="gmaps"><a target="_blank"
                   href="http://maps.google.com/?ll=52.359832,4.901393">
                    <img src="http://maps.google.com/maps/api/staticmap?&zoom=10&size=150x100&maptype=roadmap&markers=color:green|52.359832,4.901393&sensor=true"/>
                </a></li>
                <li class="title"><a href="events-detail.jsp">Rhode Island Green Home and Living Show</a></li>
            </ul>
            <ul class="event-item">
                <li class="full-link"><a href="events-detail.jsp"></a></li>
                <li class="calendar"><img src="images/bg-calendar.png"/>
                    <span class="month">May</span>
                    <span class="day">22</span>
                </li>
                <li class="gmaps"><a target="_blank"
                   href="http://maps.google.com/?ll=52.359832,4.901393">
                    <img src="http://maps.google.com/maps/api/staticmap?&zoom=10&size=150x100&maptype=roadmap&markers=color:green|52.359832,4.901393&sensor=true"/>
                </a></li>
                <li class="title"><a href="events-detail.jsp">Cradle to Cradle Design event</a></li>
            </ul>
            <ul class="event-item">
                <li class="full-link"><a href="events-detail.jsp"></a></li>
                <li class="calendar"><img src="images/bg-calendar.png"/>
                    <span class="month">Jul</span>
                    <span class="day">23</span>
                </li>
                <li class="gmaps"><a target="_blank"
                   href="http://maps.google.com/?ll=52.359832,4.901393">
                    <img src="http://maps.google.com/maps/api/staticmap?&zoom=10&size=150x100&maptype=roadmap&markers=color:green|52.359832,4.901393&sensor=true"/>
                </a></li>
                <li class="title"><a href="events-detail.jsp">Water Purification summit</a></li>
            </ul>
        </div>
        
        <div id="show-more">
            <a href="#">Show 25 more...</a>
        </div>
    </div>
    <div id="extra">
        <a href="#linktojson-forlayar">View events in Layar</a>
    </div>

<jsp:include page="includes/footer.jsp"/>
