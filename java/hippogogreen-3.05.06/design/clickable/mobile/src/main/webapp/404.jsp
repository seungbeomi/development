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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
    <title>Hippo Sales Demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=320; user-scalable=0;"/>
    <link rel="stylesheet" media="screen" type="text/css" href="css/yui-css.css">
    <link rel="stylesheet" media="screen" type="text/css" href="css/screen.css">
    
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/js-common.js"></script>
    
</head>
<body>
    <!-- document wrapper -->
    <div id="doc" class="yui-d-custom">
        <!-- header -->
        <div id="hd">
            <!-- logo -->
            <img src="images/logo.png" alt="Hippo Logo" class="logo">
        </div>
        
        <!-- navigation -->
        <ul id="nav">
            <li><a title="Products" href="products.jsp">Popular</a></li>
            <li><a title="Events" href="events.jsp">Recent</a></li>
        </ul>
        <ul id="sub-nav">
            <li><a title="Popular" href="home.jsp">Popular</a></li>
            <li><a title="Recent" href="#">Recent</a></li>
            <li><a title="Search" href="search.jsp">Search</a></li>
        </ul>
        
        <!-- body / main -->
        <div id="bd">
            <div id="content">
                <h1>Page not found!</h1>
                <p class="description">Sorry! The page you've requested could not be found. <br/>
                  Try using the search below or use the main navigation to find the page you're looking for.</p>
                <div id="search">
                <form method="get" action="#">
                    <p>
                        <input type="text" value="Search" class="search-field gray">
                        <input type="submit" value="Search" class="search-button" >
                    </p>
                </form>
                </div>
            </div>
        </div>
        <div id="ft">
        <h1>
        This demo site is built with <a href=#>Hippo CMS 7</a> and <br>
        <a href=#>HST 2</a>. If you find any issues please <a href=#>report them</a>.
    </div>
    </h1>
</body>
</html>