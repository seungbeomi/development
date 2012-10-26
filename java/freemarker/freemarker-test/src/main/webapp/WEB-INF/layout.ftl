<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"] />
<html>
<head>
	<title><@tiles.getAsString name="title"/></title>
</head>
<body>
	<@tiles.insertAttribute name="header"/> 
	<br />
	<@tiles.insertAttribute name="menu"/>
	<br />
	<@tiles.insertAttribute name="contents"/>
	<br /> 
	<@tiles.insertAttribute name="footer"/>
</body>
</html>