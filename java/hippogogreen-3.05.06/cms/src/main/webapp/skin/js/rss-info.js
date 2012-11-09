"use strict";

(function(google, $) {

    google.load("feeds", "1");

    function initialize() {
        $("#feeds").hide();
        var feed = new google.feeds.Feed("http://www.onehippo.com/en/rss-events");
        feed.setNumEntries(1);
        feed.load(function (result) {
            if (!result.error) {
                appendEntries(result, "events");
            } else {
                $("#events").hide();
            }
        });
        feed = new google.feeds.Feed("http://www.onehippo.com/en/rss-news");
        feed.setNumEntries(1);
        feed.load(function (result) {
            if (!result.error) {
                appendEntries(result, "news");
            } else {
                $("#news").hide();
            }
        });
        feed = new google.feeds.Feed("http://www.onehippo.com/en/rss-blogs");
        feed.setNumEntries(3);
        feed.load(function (result) {
            if (!result.error) {
                appendEntries(result, "blogs");
            } else {
                $("#blogs").hide();
            }
        })
    }

    function appendEntries(result, containerId) {
        var container = $("#" + containerId);
        for (var i = 0; i < result.feed.entries.length; i++) {
            var entry = result.feed.entries[i];
            var title = '<h3>' + entry.title + '</h3>';
            var docDate = '<span>' + entry.publishedDate + '</span>';
            var description = '<p>' + entry.contentSnippet + '...</p>';
            var item = '<li><a href="' + entry.link + '">' + title + docDate + description + '</a></li>';
            container.append(item);
        }
        $("#feeds").show();
    }

    google.setOnLoadCallback(initialize);

    $(document).ready(function () {
        var domain = document.location.href;
        var addRandom = domain.indexOf('onehippo.com') > -1;
        var randomAuthor = "author" + (addRandom ? randomWithZeros(20) : '');
        var randomEditor = "editor" + (addRandom ? randomWithZeros(20) : '');
        var randomAdmin = "admin" + (addRandom ? randomWithZeros(20) : '');
        $("#RandomAuthor").append(randomAuthor + " - " + randomAuthor);
        $("#RandomEditor").append(randomEditor + " - " + randomEditor);
        $("#RandomAdmin").append(randomAdmin + " - " + randomAdmin);
    });
    
    function randomWithZeros(upperLimit) {
        var randomNumber = Math.ceil(Math.random() * upperLimit);
        return (randomNumber < 10) ? "0" + randomNumber : ""+randomNumber;
    }

})(google, jQuery);