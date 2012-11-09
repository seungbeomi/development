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

// Default values for text size script
var textSizes = [13, 15, 17];

$(document).ready(function(){
  $(".question").toggle(
    function () {
      $(this).children("div").show("slow");
    },
    function () {
      $(this).children("div").hide("slow");
    });
  $(".parameters li a").toggle(
    function () {
      $(this).parent().children("div").show("slow");
    },
    function () {
      $(this).parent().children("div").hide("slow");
    });
  
  var inputField = $(".search-field, .login-field");
  var inputText = new Array(inputField.length);
  
  for(i=0; i<inputField.length; i++) {
    inputText[i] = $(inputField[i]).val();
    eval('$(inputField[i]).focus(function(){ if($(this).val() == "' + inputText[i] + '") { $(this).val(""); } $(this).removeClass("gray"); });');
    eval('$(inputField[i]).blur(function(){ if($(this).val() == "") { $(this).val("' + inputText[i] + '"); } $(this).addClass("gray"); });');
  }

  // Show the text size buttons. This is hidden by default for clients that don't support JS
  $('#text-size').css('display', 'inline-block');
  // IE7 inline-block fix
  if(typeof(ie7) != "undefined" && ie7) { $('#text-size').css('display', 'inline'); }
  
  // Text size is also changed at rendertime by JSP/JSTL, because changing text size
  // after page load is ugly. Set the text size so inc/dec buttons are removed, where needed.
  var textSize = readCookie('textSize');
  var index = getTextSizeIndex(textSize);
  setTextSize(index);
});

oldTextAry = new Array();

function changeText (fieldObj, newTexStr) {
  if (newTexStr == fieldObj.innerHTML) {
    fieldObj.innerHTML = oldTextAry[fieldObj.id];
  } 
  else {
    oldTextAry[fieldObj.id] = fieldObj.innerHTML;
    fieldObj.innerHTML = newTexStr;
  }
}

// share-block tag
function sendMail() {
    var url = document.location.href;
    window.location="mailto:?subject=" + url ;
}
function bookmark() {
    var url = document.location.href;
    var title = document.title;
    if (document.all) {
        window.external.AddFavorite(url, title);
    } else if (window.sidebar) {
        window.sidebar.addPanel(title, url, '');
    }
}

/* Copyright: http://www.quirksmode.org/js/cookies.html */
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function getTextSize() {
  return parseInt($('body').css('font-size'));
}

function getTextSizeIndex(textSize) {
  for(i=0; i < textSizes.length; i++) {
    if(textSizes[i] == textSize) {
      return i;
    }
  }
  return 0;
}

function decreaseTextSize() {
  var index = getTextSizeIndex(getTextSize());
  if(index > 0) {
    index--;
  }
  setTextSize(index);
}

function increaseTextSize() {
  var index = getTextSizeIndex(getTextSize());
  if(index < textSizes.length - 1) {
    index++;
  }
  setTextSize(index);
}

function setTextSize(index) {
	if (index == '0') {
		$('#text-size *').removeClass('active');
		$('#text-size .normal').addClass('active');
	}
	else if (index == '1') {
		$('#text-size *').removeClass('active');
		$('#text-size .medium').addClass('active');
	}
	else if (index == '2') {
		$('#text-size *').removeClass('active');
		$('#text-size .large').addClass('active');
	}
	
	textSize = textSizes[index];
	$('body').css('font-size', textSize + 'px');
	document.cookie = 'textSize=' + textSize;
};

/**
 * Sanatizes the value of a form field that will be submitted as a request parameter. The field is emptied when it
 * contains the given default text. All '<' and '>' characters are removed from the field so the XSSUrlFilter is not
 * triggered.
 *
 * @param field reference to the field whose value must be sanitized
 * @param defaultText the default text used for the field (optional)
 */
function sanitizeRequestParam(field, defaultText) {
    var query = field.value;
    if (query === defaultText) {
        query = '';
    } else {
        // remove the less-than and greater-than characters that will cause the XSSUrlFilter to return a 400 page
        query = query.replace('<', '');
        query = query.replace('>', '');
    }
    field.value = query;
}
