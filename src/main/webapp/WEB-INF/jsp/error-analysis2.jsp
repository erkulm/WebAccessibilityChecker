<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page language="java" import = "java.util.*"%>
<%@ page import="edu.itu.wac.service.response.ComparisonChart" %>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Language" content="tr">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Error Analysis</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
	<meta name="description" content="Huge selection of charts created with the React ChartJS Plugin">
	<meta name="msapplication-tap-highlight" content="no">

	<style> <%@include file="../static/style2.css"%> </style>
	<style> <%@include file="../static/main.css"%> </style>
	<script> <%@include file="../static/script.js"%> </script>

</head>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>

<script type="text/javascript">


	var label1 = "Hata 1";  //horizontalBar label1
	var label2 = "Hata 2";    //horizontalBar label2
	var label3 = "Hata 3";     //horizontalBar label3
	var label4 = "Hata 4";     //horizontalBar label4
	var label5 = "Hata 5";     //horizontalBar label5

	var data1 = 0;
	var data2 = 0;
	var data3 = 0;
	var data4 = 0;
	var data5 = 0;
	var hBarMaxVal = 0;     //horizontalBarMaxvalue


	var llabel=" "; var llabel1 = " "; var llabel2 = " "; var llabel3 = " "; var llabel4 = " "; var llabel5 = " "; var llabel6 = " "; var llabel7 = " "; var llabel8 = " ";
	var rdata = 0; var rdata1 = 0; var rdata2 = 0; var rdata3 = 0; var rdata4 = 0; var rdata5 = 0; var rdata6 = 0; var rdata7 = 0; var rdata8 = 0;
	var bdata = 0; var bdata1 = 0; var bdata2 = 0; var bdata3 = 0; var bdata4 = 0; var bdata5 = 0; var bdata6 = 0; var bdata7 = 0; var bdata8 = 0;
	var gdata = 0; var gdata1 = 0; var gdata2 = 0; var gdata3 = 0; var gdata4 = 0; var gdata5 = 0; var gdata6 = 0; var gdata7 = 0; var gdata8 = 0;
	var dataset1 = ""; var dataset2 = ""; var dataset3 = "";


	<%
    List comparisonWebsiteList = (List)request.getAttribute("comparisonWebsiteList");
    List comparisonList = (List)request.getAttribute("comparisonDashboardCounts");
    if(comparisonList != null){
        ComparisonChart chartData;
        for(int i=0; i<comparisonList.size(); i++){
            chartData = (ComparisonChart)comparisonList.get(i);
            if(i==0 && i<comparisonList.size()){%>
	llabel = "<%=chartData.getLabel()%>";
	rdata = parseInt(<%=chartData.getData()[0]%>);
	bdata = parseInt(<%=chartData.getData()[1]%>);
	gdata = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==1 && i<comparisonList.size()){%>
	llabel1 = "<%=chartData.getLabel()%>";
	rdata1 = parseInt(<%=chartData.getData()[0]%>);
	bdata1 = parseInt(<%=chartData.getData()[1]%>);
	gdata1 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==2 && i<comparisonList.size()){%>
	llabel2 = "<%=chartData.getLabel()%>";
	rdata2 = parseInt(<%=chartData.getData()[0]%>);
	bdata2 = parseInt(<%=chartData.getData()[1]%>);
	gdata2 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==3 && i<comparisonList.size()){%>
	llabel3 = "<%=chartData.getLabel()%>";
	rdata3 = parseInt(<%=chartData.getData()[0]%>);
	bdata3 = parseInt(<%=chartData.getData()[1]%>);
	gdata3 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==4 && i<comparisonList.size()){%>
	llabel4 = "<%=chartData.getLabel()%>";
	rdata4 = parseInt(<%=chartData.getData()[0]%>);
	bdata4 = parseInt(<%=chartData.getData()[1]%>);
	gdata4 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==5 && i<comparisonList.size()){%>
	llabel5 = "<%=chartData.getLabel()%>";
	rdata5 = parseInt(<%=chartData.getData()[0]%>);
	bdata5 = parseInt(<%=chartData.getData()[1]%>);
	gdata5 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==6 && i<comparisonList.size()){%>
	llabel6 = "<%=chartData.getLabel()%>";
	rdata6 = parseInt(<%=chartData.getData()[0]%>);
	bdata6 = parseInt(<%=chartData.getData()[1]%>);
	gdata6 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==7 && i<comparisonList.size()){%>
	llabel7 = "<%=chartData.getLabel()%>";
	rdata7 = parseInt(<%=chartData.getData()[0]%>);
	bdata7 = parseInt(<%=chartData.getData()[1]%>);
	gdata7 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%}else if(i==8 && i<comparisonList.size()){%>
	llabel8 = "<%=chartData.getLabel()%>";
	rdata8 = parseInt(<%=chartData.getData()[0]%>);
	bdata8 = parseInt(<%=chartData.getData()[1]%>);
	gdata8 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
	<%		}
        }
    }
    %>

	<%if(comparisonWebsiteList!=null){%>
	dataset1 = "<%=comparisonWebsiteList.get(0) !=null ? comparisonWebsiteList.get(0) : ""%>";
	dataset2 = "<%=comparisonWebsiteList.size() > 1 ? comparisonWebsiteList.get(1) : ""%>";
	dataset3 = "<%=comparisonWebsiteList.size() > 2 ? comparisonWebsiteList.get(2) : ""%>";
	<%}%>


</script>

<script type="text/javascript">

	/*$('*').click(function(event) {
        if (this === event.target) { // only fire this handler on the original element

        }
    });  */

	$( document ).ready(function() {

		if(llabel.length>2){
			document.getElementById("canvasDiv").style.display="block";
		}
		/*	alert("label:" + llabel + " - rdata:" + rdata + "  - gdata:" + bdata + "  - bdata:" + gdata);
            alert("label:" + llabel1 + " - rdata:" + rdata1 + "  - gdata:" + bdata1 + "  - bdata:" + gdata1);
            alert("label:" + llabel2 + " - rdata:" + rdata2 + "  - gdata:" + bdata2 + "  - bdata:" + gdata2);
            alert("label:" + llabel3 + " - rdata:" + rdata3 + "  - gdata:" + bdata3 + "  - bdata:" + gdata3);
            alert("label:" + llabel4 + " - rdata:" + rdata4 + "  - gdata:" + bdata4 + "  - bdata:" + gdata4);
            alert("label:" + llabel5 + " - rdata:" + rdata5 + "  - gdata:" + bdata5 + "  - bdata:" + gdata5);
            alert("label:" + llabel6 + " - rdata:" + rdata6 + "  - gdata:" + bdata6 + "  - bdata:" + gdata6);
            alert("label:" + llabel7 + " - rdata:" + rdata7 + "  - gdata:" + bdata7 + "  - bdata:" + gdata7);
            alert("label:" + llabel8 + " - rdata:" + rdata8 + "  - gdata:" + bdata8 + "  - bdata:" + gdata8);
            alert("dataset1:" + dataset1 + "  - dataset2:" + dataset2 + "   -dataset3" + dataset3); */
	});

	function changeColor(element){
		if(element.style.background != "red"){
			element.style.background = "red";
		}else{
			element.style.background = "white";
		}
	}

	function compareWebsites(){
		var websiteElements = document.getElementsByName("websiteName");
		var comparisonElements = document.getElementsByName("comparisonName");


		var websiteIds="";
		for (i = 0; i < websiteElements.length; i++) {
			if (websiteElements[i].style.background == "red") {
				websiteIds = websiteIds + websiteElements[i].id + "-";
			}
		}

		if(websiteIds.split("-").length - 1>2){
			alert("En fazla 3 vebsitesi karşılaştırılabilir");
			return false;
		}


		var elements="";
		for (i = 0; i < comparisonElements.length; i++) {
			if (comparisonElements[i].style.background == "red") {
				elements = elements + comparisonElements[i].id + "-";
			}
		}

		document.form1.action = "WACControlCenter?action=getComparisonCriterions&websiteIds="+websiteIds+"&elements="+elements;
		form1.submit();
	}

</script>

<body>
<form name="form1" id="comparisonForm" method="post">
	<h3 style="margin-left:400px;margin-right:auto;margin-top:30px">ERROR ANALYSIS PANEL</h3>
	<div class="row" style="margin-left:auto;margin-right:auto;margin-top:50px">
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-arielle-smile">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">Karşılaştırılan </div>
						<div class="widget-heading"> Websitesi</div>
					</div>
					<div class="widget-content-right">
						<%String website = (String)request.getSession().getAttribute("website");%>
						<div class="widget-heading" name="website"><span><%=website %></span></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-midnight-bloom">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">Number of Total Errors</div>
						<div class="widget-subheading">Tüm site tarandığında</div>
					</div>
					<div class="widget-content-right">
						<div class="widget-numbers text-white"><span>${errorReport.totalErrors}</span></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xl-4">
			<div class="card mb-3 widget-content bg-grow-early">
				<div class="widget-content-wrapper text-white">
					<div class="widget-content-left">
						<div class="widget-heading">Toplam Sayfa Sayısı</div>
						<div class="widget-subheading">Sitenin tüm sayfaları</div>
					</div>
					<div class="widget-content-right">
						<div class="widget-numbers text-white"><span>${errorReport.numberOfSubPages}</span></div>
					</div>
				</div>
			</div>
		</div>
		<div class="dropdown d-inline-block" style="margin-left:100px;margin-top:30px">
			<button type="button" id="websiteListBtn" aria-haspopup="true" aria-expanded="false" data-toggle="dropdown" class="mb-2 mr-2 dropdown-toggle btn btn-primary">Websitesi Seçiniz</button>
<%--			<div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu">--%>
<%--				<%--%>
<%--					List websiteList = (List) request.getSession().getAttribute("websitesWithSameGroup");--%>
<%--					if(websiteList != null){--%>
<%--						Website web;--%>
<%--						for(int i=0; i< websiteList.size(); i++){--%>
<%--							web = (Website) websiteList.get(i);--%>
<%--				%>--%>
<%--				<button type="button" name="websiteName" tabindex="0" id="<%=String.valueOf(web.getWebsiteId())%>" class="dropdown-item" onclick="changeColor(this);"><%=web.getWebsite()%> - <%=web.getCrDate()%></button>--%>
<%--				<%}} %>--%>
<%--			</div>--%>
		</div>
		<div class="dropdown d-inline-block" style="margin-left:300px;margin-top:30px">
			<button type="button" id="comparisonListBtn" aria-haspopup="true" aria-expanded="false" data-toggle="dropdown" class="mb-2 mr-2 dropdown-toggle btn btn-primary">Karşılaştırma Kriterleri</button>
			<div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu">
				<button type="button" tabindex="0" name="comparisonName" id="1" class="dropdown-item" onclick="changeColor(this);">Toplam Hata Sayısı</button>
				<button type="button" tabindex="0" name="comparisonName" id="2" class="dropdown-item" onclick="changeColor(this);">Toplam Sayfa Sayısı</button>
				<button type="button" tabindex="0" name="comparisonName" id="3" class="dropdown-item" onclick="changeColor(this);">Table tag</button>
				<button type="button" tabindex="0" name="comparisonName" id="4" class="dropdown-item" onclick="changeColor(this);">Kontrast</button>
				<button type="button" tabindex="0" name="comparisonName" id="5" class="dropdown-item" onclick="changeColor(this);">Form tag</button>
				<button type="button" tabindex="0" name="comparisonName" id="6" class="dropdown-item" onclick="changeColor(this);">Button tag</button>
				<button type="button" tabindex="0" name="comparisonName" id="7" class="dropdown-item" onclick="changeColor(this);">Link hatasi</button>
				<button type="button" tabindex="0" name="comparisonName" id="8" class="dropdown-item" onclick="changeColor(this);">Doküman dili</button>
				<button type="button" tabindex="0" name="comparisonName" id="9" class="dropdown-item" onclick="changeColor(this);">Img tag</button>
			</div>
		</div>
		<div  style="margin-left:300px;margin-top:30px">
			<button class="mb-2 mr-2 btn btn-secondary" onclick="compareWebsites();">Karşılaştır</button>
		</div>
		<div class="col-md-6" id="canvasDiv"  style="display:none;">
			<div class="main-card mb-3 card" id="canvasInsideDiv">
				<div class="card-body">
					<h5 class="card-title">Karşılaştırmalı Hata Analiz Grafiği</h5>
					<canvas id="canvas" ></canvas>
				</div>
			</div>
			<div><br/><br/><<br/></div>
			<div class="main-card mb-3 card" id="stackBarInsideDiv">
				<div class="card-body">
					<h5 class="card-title">Karşılaştırmalı Hata Analiz Grafiği - Stack Bar</h5>
					<canvas id="stacked-bars-chart"></canvas>
				</div>
			</div>
		</div>
	</div>

</form>
<script type="text/javascript" src="../static/main.js"> </script>

</body>
</html>