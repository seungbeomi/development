/*
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// set url to display within overlay iframe here
var url = window.location.protocol + "//www.onehippo.com/help";
var maxWidth = 1008;

var helpButtonHTML = 
	'<div id="hippo-help-button"><a href="#" rel="#hippo-help-overlay"></a></div>';

var helpOverlayHTML = 
	'<div id="hippo-help-overlay" class="simple-overlay">'
		+ '<div id="hippo-help-overlay-wrapper"></a></div>'
	+ '</div>';

var overlayLoaded = false;

$(document).ready(function(){
  // Add HTML for help button and overlay
  $('BODY').append(helpButtonHTML);
  $('BODY').append(helpOverlayHTML);

  //keep overlay centered, also on window resize
  $('#hippo-help-overlay').height(($(window).height() - 150));
  $('#hippo-help-overlay').width( Math.min(($(window).width() * 0.9), maxWidth));
  $('#hippo-help-overlay').css('left',  ($(window).width() - $('#hippo-help-overlay').width())/ 2);
  $(window).resize(function() {
	  $('#hippo-help-overlay').height(($(window).height() - 150));
	  $('#hippo-help-overlay').width( Math.min(($(window).width() * 0.9), maxWidth));
	  $('#hippo-help-overlay').css('left', ($(window).width() - $('#hippo-help-overlay').width())/ 2);
  });

  // layover
  $("#hippo-help-button a[rel]").overlay({
	  mask: '#333',
	  
	  // load iframe with help page
	  onBeforeLoad: function(){
	  	if (!overlayLoaded){
	  		$('#hippo-help-overlay-wrapper').append('<iframe src="' + url + '"></iframe>');
	  		overlayLoaded = true;
	  		}
  		}
  });
});