<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shared/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><tiles:getAsString name="Project Admin"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<c:url value='/resources/bootstrap/2.1.1/css/bootstrap.css' />" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="<c:url value='/resources/bootstrap/2.1.1/css/bootstrap-responsive.css' />" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<c:url value='/resources/bootstrap/2.1.1/ico/favicon.ico'/>">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='/resources/bootstrap/2.1.1/ico/apple-touch-icon-144-precomposed.png'/>">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value='/resources/bootstrap/2.1.1/ico/apple-touch-icon-114-precomposed.png'/>">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value='/resources/bootstrap/2.1.1/ico/apple-touch-icon-72-precomposed.png'/>">
    <link rel="apple-touch-icon-precomposed" href="<c:url value='/resources/bootstrap/2.1.1/ico/apple-touch-icon-57-precomposed.png'/>">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <tiles:insertAttribute name="header"/>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <tiles:insertAttribute name="sidebar"/>
          </div><!--/.well -->
        </div><!--/span-->
        
        <div class="span9">
        	<tiles:insertAttribute name="content"/>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <tiles:insertAttribute name="footer"/>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/jquery.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-transition.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-alert.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-modal.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-dropdown.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-scrollspy.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-tab.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-tooltip.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-popover.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-button.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-collapse.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-carousel.js' />"></script>
    <script src="<c:url value='/resources/bootstrap/2.1.1/js/bootstrap-typeahead.js' />"></script>

  </body>
</html>
