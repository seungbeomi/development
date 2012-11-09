<%@include file="../../includes/tags.jspf" %>

<c:set var="mobileproductstitle"><fmt:message key="mobile.products.overview.title"/></c:set>
<hippo-gogreen:title title="${mobileproductstitle}"/>

<!-- Most of this code comes from the original PugPig.com book -->

<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
<title>pugpig</title>
<meta charset="utf-8" />
<meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, width=device-width, user-scalable=no">
<!-- To get rid of the URL and button bars -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- Status Bar Style (default, black, black-translucent) -->
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="delaySnapshotUntilReady" content="YES" />
<link rel="stylesheet" href="http://kaldorgroup.com/Books/BOOKPUGPIG/css/style.css" />

<script type="text/javascript" src="http://kaldorgroup.com/Books/BOOKPUGPIG/js/pugpig.js"></script>
<script type="text/javascript" src="http://kaldorgroup.com/Books/BOOKPUGPIG/js/jquery.min.js"></script>
<script src="http://kaldorgroup.com/Books/BOOKPUGPIG/js/jquery.animate-enhanced.js"></script>
<script src="http://kaldorgroup.com/Books/BOOKPUGPIG/js/overlay.js"></script>
<script type="text/javascript" src="http://kaldorgroup.com/Books/BOOKPUGPIG/js/cloud-carousel.1.0.5.js"></script>

<script type="text/javascript">

var products =
   [
            <%--@elvariable id="docs" type="com.onehippo.gogreen.utils.PageableCollection"--%>
            <c:forEach items="${docs.items}" var="product">
                <hst:link var="prdlink" hippobean="${product}"/>
            
               { "image": "<c:choose><c:when test="${product.mainImage.mobileThumbnail != null}"><hst:link var="thumbnail" hippobean="${product.mainImage.largeThumbnail}"/>${thumbnail}</c:when><c:otherwise><hst:link var="thumbnail" hippobean="${product.mainImage.smallThumbnail}"/>${thumbnail}</c:otherwise></c:choose>", "name": "<c:out value="${product.title}"/>", "desc": "Rated <fmt:formatNumber value="${product.rating * 10}" var="ratingStyle" pattern="#0" /><c:out value="${product.rating}"/> stars!", "price":"<fmt:formatNumber value="${product.price}" type="currency"/>", },
            </c:forEach>

   ];

$(document).ready(function(){
    for (var idx=0; idx < products.length; idx++) {
      $("#carousel1").append("<img class='cloudcarousel' src='" + products[idx].image + "' alt='" + products[idx].desc + "' title='" + products[idx].name + "' data-price='" + products[idx].price + "' />");
	}

	$("#carousel1").CloudCarousel(
		{
			xPos: 380,
			yPos: 50,
			altBox: $("#description"),
			titleBox: $("#product"),
            priceBox: $("#price"),
			bringToFront: true,
			reflHeight: 0
		}
	);
});

function touchStart(event) {
  // event.preventDefault();
  startX = event.targetTouches[0].pageX;
}

function touchMove(event) {
  event.preventDefault();
  curX = event.targetTouches[0].pageX - startX;
  if (curX > 100) {
    startX = event.targetTouches[0].pageX;
    $("#carousel1").data('cloudcarousel').rotate(-1);
  }
  else if (curX < -100) {
    startX = event.targetTouches[0].pageX;
    $("#carousel1").data('cloudcarousel').rotate(1);
  }
}

function touchEnd(event) {
    $("#carousel1").data('cloudcarousel').showFrontText();
}

</script>

</head>
<body>
  <div class="page">
    <div id="carousel">
        <section>
            <header>
                <h1>GoGreen's Top Sellers</h1>
                <p>Latest goodies from the Hippo GoGreen store! Go find yourself a nice Green thingy. Their all great!</p>
            </header>
            <article>
                <div id="priceboard">
                    <div id="priceboardcontent">
                        <h3 id="product"></h3>
                        <p id="description"></p>
                        <p id="price"></p>
                    </div>
                </div>
                <div id="carousel1" ontouchstart="touchStart(event);" ontouchmove="touchMove(event);" ontouchend="touchEnd(event);"></div>
            </article>
            <footer>
            </footer>
        </section>
        <div id="overlay" onclick="hideOverlay();">
            <p>The carousel uses Cloud Carousel by Christophe Beyls, although we have modified it to accept touch events. The page also has an example of the script to time the pugpig snapshot, which is something you'll need on certain pages.</p>
            <img id="pig" src="http://kaldorgroup.com/Books/BOOKPUGPIG/images/prod2.png" alt="">
        </div>
        <div id="overlay-preloader">&nbsp;</div>
    </div>
  </div>
</body>
</html>


