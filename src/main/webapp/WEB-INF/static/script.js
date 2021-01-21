$(document).ready(function(){


	$(".categories table td a").click(function(){


		var ids = $(this).attr("name");
		ids = ids.split("_");
		var last = ids[ids.length - 1];
		var id = "error"+last;


		if ($("#"+id).length) {

			var element = document.getElementById(id);
			element.scrollIntoView({behavior: "smooth", block: "center", inline: "nearest"});

		}

		$("#"+id).css("outline","5px solid  #0E6BC8");
		setTimeout(() => {  $("#"+id).css("outline","none"); }, 2000);


	});

	$(".categories table td i").click(function(){


		$(this).toggleClass("rotate180");
		$(this).parent().parent().toggleClass("table-primary");
		$(this).parent().parent().next().toggle();
		$(this).parent().parent().next().css("border-left","5px solid #b8daff");
		$(this).parent().parent().next().css("border-right","5px solid #b8daff");
		$(this).parent().parent().next().next().toggle();
		$(this).parent().parent().next().next().css("border-left","5px solid #b8daff");
		$(this).parent().parent().next().next().css("border-right","5px solid #b8daff");
		$(this).parent().parent().next().next().css("border-bottom","5px solid #b8daff");

	})

	var errorButtons = $(".control-8");
	errorButtons.each(function(){

		var ids = $(this).attr("name");
		ids = ids.split("_");
		var last = ids[ids.length - 1];
		var id = "error"+last;
		if ($("#"+id).css('display') == 'none' || $("#"+id).css('visibility') == 'hidden' || $("#"+id).css('color') == 'transparent')
		{
			$(this).parent().append("<i class='fas fa-exclamation-circle'></i>");
			$(this).parent().append("<div class='greybox'><p class='hiddenexp'>This Error is in a block which is hidden or not displayed</p></div>");
		}
	})

	$(".info").click(function(){

		var idname = $(this).next().attr("id");
		if(!idname){
			idname = $(this).next().children(":last-child").attr("id");
		}

		console.log(idname);
		var last = idname.substring(5, idname.length);
		var id = "#info_" + last;
		btns = $(".control-8");
		var pos;
		btns.each(function(){
			if ($(this).attr("name") == id) {

				if(!$(this).parent().parent().parent().parent().parent().hasClass("show"))
				{
					$(this).parent().parent().parent().parent().parent().prev().trigger('click');
					$(this).parent().parent().parent().parent().parent().siblings(".show").prev().trigger('click');
				}

				$(this).parent().next().children().trigger('click');
				pos = $(this).offset().top;
				//var myname = $(this).attr("name");
				//myname = "a[name='"+myname+"']";
				//var element2 = document.querySelector(myname);
				//element2.scrollIntoView({behavior: "smooth", block: "start", inline: "start"});
			} else {
				$(this).parent().next().children(".rotate180").trigger('click');
			}
		})

		$(".categories").scrollTop($(this).offset().top);
	})



	$(".info").mouseenter(function(){

		if(!$(this).children().hasClass("item"))
		{
			var pos = $(this).children(":last-child").children(":last-child").offset().top-$(this).children(":last-child").children(":last-child").outerHeight();
			var left = $(this).children(":last-child").children(":last-child").offset().left
			var right = $(this).children(":last-child").children(":last-child").offset().left - $(this).children(":last-child").children(":last-child").outerWidth();
			var width= $("#webPart").innerWidth();
			if(pos < 20){
				$(this).children(":last-child").children(":last-child").css("top","30px");
				$(this).children(":last-child").children(":last-child").css("bottom","auto");
			}
			if(width - right < 20) {
				$(this).children(":last-child").children(":last-child").css("left","-270px");
			}

		} else {
			var pos = $(this).children().children(":last-child").children(":last-child").offset().top-$(this).children().children(":last-child").children(":last-child").outerHeight();
			var left = $(this).children().children(":last-child").children(":last-child").offset().left
			var right = $(this).children().children(":last-child").children(":last-child").offset().left - $(this).children(":last-child").children(":last-child").outerWidth();
			var width= $("#webPart").innerWidth();
			if(pos < 20){
				$(this).children().children(":last-child").children(":last-child").css("top","30px");
				$(this).children().children(":last-child").children(":last-child").css("bottom","auto");
			}
			if(width - right < 20) {
				$(this).children().children(":last-child").children(":last-child").css("left","-270px");
			}
		}
		if(!$(this).next().hasClass("error")){
			$(this).next().children().css("outline","5px solid  #0E6BC8");
		}

	})

	$(".info").mouseleave(function(){

		$(this).next().children().css("outline","none");
		if(!$(this).children().hasClass("item")) {

			$(this).children(":last-child").children(":last-child").css("bottom","30px");
			$(this).children(":last-child").children(":last-child").css("top","inherit");
			$(this).children(":last-child").children(":last-child").css("left","10px");
		} else {

			$(this).children().children(":last-child").children(":last-child").css("bottom","30px");
			$(this).children().children(":last-child").children(":last-child").css("top","inherit");
			$(this).children().children(":last-child").children(":last-child").css("left","10px");
		}

	});






});

