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

$(document).ready(function(){
  
  var inputField = $(".search-field");
  var inputText = new Array(inputField.length);
  
  for(i=0; i<inputField.length; i++) {
    inputText[i] = $(inputField[i]).val();
    eval('$(inputField[i]).focus(function(){ if($(this).val() == "' + inputText[i] + '") { $(this).val(""); } $(this).removeClass("gray"); });');
    eval('$(inputField[i]).blur(function(){ if($(this).val() == "") { $(this).val("' + inputText[i] + '"); } $(this).addClass("gray"); });');
  }
  
  $("#show-more a.more").click(function() {
    $("#show-more #load").show();

    var obj = $(this);
    var link = obj.attr("href");
    var parameters = $.getUrlVars(link);
    var jsEnabled = parameters['jsEnabled'];

    var pageSize = parseInt(parameters['pageSize']);
    if (jsEnabled != 'true') {
        // firsttimething
        pageSize = pageSize - 5;
    }
    var from = parseInt(parameters['from']);
    var count = parseInt(parameters['count']);
    var current = parseInt(pageSize + from);
    var type = 0;
    var add="";
   
    if(window.location.href.lastIndexOf("?")>0){
        type = window.location.href.slice((window.location.href.lastIndexOf("/")+1), window.location.href.lastIndexOf("?"));
        add= "&" +window.location.href.slice(window.location.href.lastIndexOf("?")+1);
    }else{
        type = window.location.href.slice((window.location.href.lastIndexOf("/")+1));
    }
    
    //alert(type+".ajax?jsEnabled=true&amp;pageSize="+ pageSize + "&amp;from=" + from + add);

    $.get(type+".ajax?jsEnabled=true&pageSize="+ pageSize + "&from=" + from + add, function(html) {
        $(".results").append(html);
        $("#load").hide();

        if (count > current) {
            if (count - pageSize > 25) {
                pageSize = 25;
            } else {
                pageSize = count - current;
            }
            obj.attr("href", "jsEnabled=true&count="+ count + "&pageSize=" + pageSize + "&from=" + current);
            obj.html("Show " + pageSize + " more..");
        } else {
            $("#show-more").hide();
        }
    });
    return false;
  });

    $.extend( {
        getUrlVars : function(url) {
            var vars = [], hash;
            var hashes = url.slice(url.indexOf('?') + 1).split(
                    '&');
            for ( var i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        },
        getUrlVar : function(url, name) {
            return $.getUrlVars(url)[name];
        }
    });
  
    $("#language > li.active").click(
            function (e) { e.preventDefault(); langToggle(this); });
    
    function langToggle(obj) {
        var subfilter = $(obj).parent();
        if(subfilter.hasClass("expanded")) {
          $(subfilter).children("li").not(".active").hide("slow");
          $(subfilter).removeClass("expanded");
        } else {
          $(subfilter).children("li").not(".active").show("slow");
          $(subfilter).addClass("expanded");
        }
    }
  
});
