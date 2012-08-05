var mode = "add";
var query = {};
query["mode"] = mode;

$(function(){
	$("#id").focus();

	$("#data").keydown(asyn);

	$("crud").click(function(e) {
		mode - e.target.id;
		query["mode"] = mode;
		clear();
		$("#status").text(" ");
		$("id").focus();
	});

	$("#id").keyup(function(){
		if($("#id").val().length == 5) {
			if(mode == "view" || mode == "update" || mode == "delete") {
				query["id"] = $("#id").val();
				$.get("/development/person/crud", query, read);
			} else if (mode == "add") {
				$("#name").focus();
			}
		}
	});
});

function read(resp) {
	var items = resp.split("<i>");
	if(items[0] == "NO") {
		clear();
		$("status").text(items[1]);
	} else {
		var i = 0;
		$.each(["name", "address"], function() {
			$("#" + this).val(items[i++]);
		});
		$("#status").text(items[2]);
	}
}

function asyn(e) {
	if (e.keyCode == 27) {
		query["id"] = $("#id").val();
		if (mode == "add" || mode == "update") {
			query["name"] = $("#name").val();
			query["address"] = $("#address").val();
			$.post("/development/person/crud", query, function(data){
				$("body").html(data);
				clear();
			});
		}
	} else if (mode == "delete") {
		$.post("/development/person/crud", query, function(resp) {
			$("status").text(resp);
			clear();
		})
	}
}

function clear() {
	$.each(["id", "name", "address"], function() {
		$("#" + this).val("");
	});

}