/*!
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
    // Hide all unexpanded filters
    $("#filter-by > li:not(#search, .expanded), #filter-by .subfilter > li:not(.expanded)").addClass("hidden");
    // Add remove filter function to clear buttons
    //$("#filter-by .subfilter > li > a.clear").click( 
    //    function (e) { e.preventDefault(); removeFilter(this); });
    // Add hide show toggle to filters
    $("#filter-by > li:not(#search) > span > a, #filter-by .subfilter > li > span > a:not(.clear)").click(
        function (e) { e.preventDefault(); expandToggle(this); });
    // Add addFilter functionality to all items
    //$("#filter-by .subsubfilter > li > a").click(
    //    function (e) { e.preventDefault(); addFilter(this); });
});

function expandToggle(obj) {
    var subfilter = $(obj).parent().parent();
    if(subfilter.hasClass("expanded"))
        hideFilter(subfilter);
    else
        showFilter(subfilter);
}

function showFilter(obj) {
    $(obj).children("ul").show("slow");
    $(obj).addClass("expanded");
    $(obj).removeClass("hidden");
}
function hideFilter(obj) {
    $(obj).children("ul").hide("slow");
    $(obj).addClass("hidden");
    $(obj).removeClass("expanded");
}

/*
function addFilter(obj) {
    var subfilter = $(obj).parent().parent().parent();
    $(subfilter).removeClass("expanded");
    if($(subfilter).hasClass("clear") || $(subfilter).attr("id") == "sort-by") { // Change filter
        $(subfilter).children("span").children("a:not(.hidden)").remove();
    } else { // Add filter; When no previous filter has been selected
        $(subfilter).addClass("clear");
        $(subfilter).children("span").children("a").addClass("hidden");
    }
    
    // Add filter hide show toggle and clear button
    $(subfilter).children("span").append(" <a class=\"filter\" href=\"" + $(obj).attr("href") +
        "#\" onclick=\"javascript:expandToggle(this); return false\" title=\"" + $(obj).attr("title") +
        "\">" + $(obj).html() + "</a>");
    // Don't add clear button to sorting 
    if($(subfilter).attr("id") != "sort-by") {
        $(subfilter).append("<a class=\"clear\" href=\"" + $(obj).attr("href") +
           "#\" onclick=\"javascript:removeFilter(this); return false\" title=\"Clear filter: " +
           $(obj).attr("title") + "\">&nbsp;</a>");
    }
    $(subfilter).children("ul").hide("slow");
}

function removeFilter(obj) {
    // Get parent object of object, so we have a reference object after object gets removed
    var parent = $(obj).parent();
    $(parent).children("span").children("a:not(.hidden)").remove();
    $(parent).children("a.clear").remove();
    $(parent).children("span").children("a").removeClass("hidden");
    $(parent).removeClass("clear");
}
*/