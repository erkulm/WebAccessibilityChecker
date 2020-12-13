$(document).ready(function(){
   
   
   $(".categories table td a").click(function(){
   		
   		
   		var ids = $(this).attr("name");
   		ids = ids.split("_");
   		var last = ids[ids.length - 1];
   		var id = "error"+last;
   		   		
   		console.log(id);
		if ($("#"+id).length) {
			
			console.log("deneme");			
			var element = document.getElementById(id);
			element.scrollIntoView({behavior: "smooth", block: "center", inline: "nearest"});
			
		}
		
	    $("#"+id).css("border","5px dashed #F76C6C");
	    setTimeout(() => {  $("#"+id).css("border","none"); }, 2000);
	    
        
   });
   
   $(".categories table td i").click(function(){
   
   		
   		$(this).toggleClass("rotate180");
   		$(this).parent().parent().next().toggle();
   		$(this).parent().parent().next().next().toggle();
   
   })
   
   
   
});

