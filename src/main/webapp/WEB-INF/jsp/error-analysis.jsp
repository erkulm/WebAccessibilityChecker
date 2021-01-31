<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" %>
<%@ page import="edu.itu.wac.service.response.ComparisonChart" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="tr">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>ChartJS - Huge selection of charts created with the React ChartJS Plugin</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>
    <meta name="description" content="Huge selection of charts created with the React ChartJS Plugin">
    <meta name="msapplication-tap-highlight" content="no">

    <title>Dashboard</title>
    <link href="./main.css" rel="stylesheet">
    <link href="./ccbtemp01.css" rel="stylesheet">

    <style> <%@include file="../static/style2.css"%> </style>
    <style> <%@include file="../static/main.css"%> </style>
    <script> <%@include file="../static/script.js"%> </script>

    <style>
        p2 {
            color: black;
            font-size: 14px;
            margin-top: -2px;
        }

        p3 {
            color: black;
            font-size: 18px;
            margin-top: 0px;
            color: red;
        }

        /* The container */
        .container {
            display: block;
            position: relative;
            padding-left: 35px;
            margin-bottom: 12px;
            cursor: pointer;
            font-size: 16px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        /* Hide the browser's default checkbox */
        .container input {
            position: absolute;
            opacity: 0;
            cursor: pointer;
            height: 0;
            width: 0;
        }

        /* Create a custom checkbox */
        .checkmark {
            position: absolute;
            top: 0;
            left: 0;
            height: 25px;
            width: 25px;
            background-color: #eee;
        }

        /* On mouse-over, add a grey background color */
        .container:hover input ~ .checkmark {
            background-color: #ccc;
        }

        /* When the checkbox is checked, add a blue background */
        .container input:checked ~ .checkmark {
            background-color: #2196F3;
        }

        /* Create the checkmark/indicator (hidden when not checked) */
        .checkmark:after {
            content: "";
            position: absolute;
            display: none;
        }

        /* Show the checkmark when checked */
        .container input:checked ~ .checkmark:after {
            display: block;
        }

        /* Style the checkmark/indicator */
        .container .checkmark:after {
            left: 9px;
            top: 5px;
            width: 5px;
            height: 10px;
            border: solid white;
            border-width: 0 3px 3px 0;
            -webkit-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
        }

        .customLabel {
            margin-left: -7px;
            margin-top: -7px;
            color: red;
            display: none;
        }
    </style>

</head>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>

<script type="text/javascript">

    var llabel = "";
    var llabel1 = "";
    var llabel2 = "";
    var llabel3 = "";
    var llabel4 = "";
    var llabel5 = "";
    var llabel6 = "";
    var llabel7 = "";
    var llabel8 = "";
    var rdata = 0;
    var rdata1 = 0;
    var rdata2 = 0;
    var rdata3 = 0;
    var rdata4 = 0;
    var rdata5 = 0;
    var rdata6 = 0;
    var rdata7 = 0;
    var rdata8 = 0;
    var bdata = 0;
    var bdata1 = 0;
    var bdata2 = 0;
    var bdata3 = 0;
    var bdata4 = 0;
    var bdata5 = 0;
    var bdata6 = 0;
    var bdata7 = 0;
    var bdata8 = 0;
    var gdata = 0;
    var gdata1 = 0;
    var gdata2 = 0;
    var gdata3 = 0;
    var gdata4 = 0;
    var gdata5 = 0;
    var gdata6 = 0;
    var gdata7 = 0;
    var gdata8 = 0;

    var rlabel = "";
    var rlabel1 = "";
    var rlabel2 = "";
    var rlabel3 = "";
    var rlabel4 = "";
    var rlabel5 = "";
    var rlabel6 = "";
    var rlabel7 = "";
    var rlabel8 = "";
    var rrdata = 0;
    var rrdata1 = 0;
    var rrdata2 = 0;
    var rrdata3 = 0;
    var rrdata4 = 0;
    var rrdata5 = 0;
    var rrdata6 = 0;
    var rrdata7 = 0;
    var rrdata8 = 0;
    var rbdata = 0;
    var rbdata1 = 0;
    var rbdata2 = 0;
    var rbdata3 = 0;
    var rbdata4 = 0;
    var rbdata5 = 0;
    var rbdata6 = 0;
    var rbdata7 = 0;
    var rbdata8 = 0;
    var rgdata = 0;
    var rgdata1 = 0;
    var rgdata2 = 0;
    var rgdata3 = 0;
    var rgdata4 = 0;
    var rgdata5 = 0;
    var rgdata6 = 0;
    var rgdata7 = 0;
    var rgdata8 = 0;


    var label1 = "Hata 1";  //horizontalBar label1
    var label2 = "Hata 2";    //horizontalBar label2
    var label3 = "Hata 3";     //horizontalBar label3
    var label4 = "Hata 4";     //horizontalBar label4
    var label5 = "Hata 5";     //horizontalBar label5
    var data1 = parseInt("<%=(String)request.getSession().getAttribute("HBarValue1")%>");
    var data2 = parseInt("<%=(String)request.getSession().getAttribute("HBarValue2")%>");
    var data3 = parseInt("<%=(String)request.getSession().getAttribute("HBarValue3")%>");
    var data4 = parseInt("<%=(String)request.getSession().getAttribute("HBarValue4")%>");
    var data5 = parseInt("<%=(String)request.getSession().getAttribute("HBarValue5")%>");
    var hBarMaxVal = parseInt("<%=(String)request.getSession().getAttribute("HBarMaxValue")%>");     //horizontalBarMaxvalue


    var dataset1 = "";
    var dataset2 = "";
    var dataset3 = "";
    dataset1 = "<%=(String)request.getSession().getAttribute("website")%>";

    var showChart = "<%=(String)request.getSession().getAttribute("showComparisonChart")%>";


    <%
    List comparisonWebsiteList = (List)request.getSession().getAttribute("comparisonWebsiteList");
    List comparisonList = (List)request.getSession().getAttribute("comparisonDashboardCounts1");
    List comparisonList2 = (List)request.getSession().getAttribute("comparisonDashboardCounts2");
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

        if(comparisonList2!=null && comparisonList2.size()>0){
            for(int i=0; i<comparisonList2.size(); i++){
                chartData = (ComparisonChart)comparisonList2.get(i);
                if(i==0 && i<comparisonList2.size()){%>
    rlabel = "<%=chartData.getLabel()%>";
    rrdata = parseInt(<%=chartData.getData()[0]%>);
    rbdata = parseInt(<%=chartData.getData()[1]%>);
    rgdata = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==1 && i<comparisonList2.size()){%>
    rlabel1 = "<%=chartData.getLabel()%>";
    rrdata1 = parseInt(<%=chartData.getData()[0]%>);
    rbdata1 = parseInt(<%=chartData.getData()[1]%>);
    rgdata1 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==2 && i<comparisonList2.size()){%>
    rlabel2 = "<%=chartData.getLabel()%>";
    rrdata2 = parseInt(<%=chartData.getData()[0]%>);
    rbdata2 = parseInt(<%=chartData.getData()[1]%>);
    rgdata2 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==3 && i<comparisonList2.size()){%>
    rlabel3 = "<%=chartData.getLabel()%>";
    rrdata3 = parseInt(<%=chartData.getData()[0]%>);
    rbdata3 = parseInt(<%=chartData.getData()[1]%>);
    rgdata3 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==4 && i<comparisonList2.size()){%>
    rlabel4 = "<%=chartData.getLabel()%>";
    rrdata4 = parseInt(<%=chartData.getData()[0]%>);
    rbdata4 = parseInt(<%=chartData.getData()[1]%>);
    rgdata4 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==5 && i<comparisonList2.size()){%>
    rlabel5 = "<%=chartData.getLabel()%>";
    rrdata5 = parseInt(<%=chartData.getData()[0]%>);
    rbdata5 = parseInt(<%=chartData.getData()[1]%>);
    rgdata5 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==6 && i<comparisonList2.size()){%>
    rlabel6 = "<%=chartData.getLabel()%>";
    rrdata6 = parseInt(<%=chartData.getData()[0]%>);
    rbdata6 = parseInt(<%=chartData.getData()[1]%>);
    rgdata6 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==7 && i<comparisonList2.size()){%>
    rlabel7 = "<%=chartData.getLabel()%>";
    rrdata7 = parseInt(<%=chartData.getData()[0]%>);
    rbdata7 = parseInt(<%=chartData.getData()[1]%>);
    rgdata7 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%}else if(i==8 && i<comparisonList2.size()){%>
    rlabel8 = "<%=chartData.getLabel()%>";
    rrdata8 = parseInt(<%=chartData.getData()[0]%>);
    rbdata8 = parseInt(<%=chartData.getData()[1]%>);
    rgdata8 = parseInt(<%=chartData.getData().length > 2 ? chartData.getData()[2] : "0"%>);
    <%		}

        }
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

    $(document).ready(function () {

        document.getElementById("canvasDiv").style.display = "block";

        if (rlabel.length > 1) {
            document.getElementById("radarDiv").style.display = "block";
        }
    });

    function compareWebsites() {
        var websiteElements = document.getElementsByName("websiteName");
        var comparisonElements = document.getElementsByName("comparisonName");

        var websiteIds = "";
        for (i = 0; i < websiteElements.length; i++) {
            if (websiteElements[i].checked) {
                websiteIds = websiteIds + websiteElements[i].id + "-";
            }
        }

        if (websiteIds.indexOf("-") < 0) {
            alert("Karşılaştırılacak websitelerini belirleyin.. ");
            return;
        }

        if (websiteIds.split("-").length - 1 > 2) {
            alert("En fazla 3 websitesi karşılaştırılabilir");
            return;
        }

        var elements = "";
        for (i = 0; i < comparisonElements.length; i++) {
            if (comparisonElements[i].checked) {
                elements = elements + comparisonElements[i].id + "-";
            }
        }

        if (elements.indexOf("-") < 0) {
            alert("Karşılaştırma kriterlerini belirleyin.. ");
            return;
        }


        document.form1.action = "WACControlCenter?action=getComparisonCriterions&websiteIds=" + websiteIds + "&elements=" + elements;
        form1.submit();
    }

    function setCountLayer(parentDivId) {

        var websiteElements = document.getElementsByName("websiteName");
        var counter = 0;
        var websiteIds = "";
        for (i = 0; i < websiteElements.length; i++) {
            if (websiteElements[i].checked) {
                if ("tab-eg5-" + parentDivId == websiteElements[i].parentElement.parentElement.parentElement.id) {
                    counter++;
                }
            }
        }

        var element;
        if (parentDivId == "0") {
            element = document.getElementById("label-eg5-0");
        } else if (parentDivId == "1") {
            element = document.getElementById("label-eg5-1");
        } else {
            element = document.getElementById("label-eg5-2");
        }

        if (counter > 0)
            element.style.display = "block";
        else
            element.style.display = "none";
        element.innerHTML = counter;
    }


    function returnToMainPage() {
        document.form1.action = "WACControlCenter?action=returnToMainPage";
        form1.submit();

    }
</script>


<body>
<form name="form1" id="TheForm" method="post">
    <input type="image" id="image" alt="EngelsizWeb"
           src="./css/Images/engelsizWeb2.png" onclick="returnToMainPage()">
    <h3 style="margin-left:400px;margin-right:auto;margin-top:-40px">WEB SİTESİ HATA ANALİZ PANELİ</h3>
    <div class="row" style="margin-left:auto;margin-right:auto;margin-top:50px">
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-grow-early">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">Toplam Sayfa Sayısı</div>
                        <div class="widget-subheading">Sitenin tüm sayfaları</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-white"><span>${errorReport.totalErrors}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-midnight-bloom">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">Toplam Hata Sayısı</div>
                        <div class="widget-subheading">Tüm site tarandığında</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-white"><span>${errorReport.numberOfSubPages}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-primary">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">En Çok Alınan Hata</div>
                        <div class="widget-subheading">${errorCountInfoList.get(0).errorDesc}</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-white"><span>${errorCountInfoList.get(0).errorCount}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="main-card mb-3 card">
                <div class="card-body">
                    <h5 class="card-title">EN ÇOK ALINAN HATALAR</h5>
                    <canvas id="chart-area"></canvas>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <table>
                <tr>
                    <div><a href="https://www.w3.org/WAI/WCAG20/versions/guidelines/wcag20-guidelines-20081211-a4.pdf"
                            target="_blank">
                        <p3 style="color:blue"><i>WCAG 2.0 AA</i></p3>
                    </a></div>
                </tr>
                <c:forEach var="errorCountInfo" items="${errorCountInfoList}" varStatus="status">
                    <c:if test="${status.index<3}">
                        <tr>
                            <div>
                                <p3>Error ${status.index + 1}:</p3>
                                <p2>${errorCountInfo.document.substring(8,18)}</p2>
                                <p2>${errorCountInfo.document.substring(19,31)} </p2>
                            </div>
                            <div><label>${errorCountInfo.errorDesc}</label></div>
                        </tr>
                    </c:if>
                </c:forEach>

            </table>
        </div>
        <div class="col-md-15">
            <div class="main-card mb-3 card">
                <div class="card-body"><h5 class="card-title">Hata Detay Tablosu</h5>
                    <table class="mb-0 table table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Hata Dokümanı</th>
                            <th>Hata Açıklaması</th>
                            <th>Hata Sayısı</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="errorCountInfo" items="${errorCountInfoList}" varStatus="status">

                            <tr>
                                <th scope="row">${status.index+1}</th>
                                <td>${errorCountInfo.document.substring(0,31)}</td>
                                <td>${errorCountInfo.errorDesc}</td>
                                <td>${errorCountInfo.errorCount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%--		<div class="col-md-6" style="margin-top:40px;">--%>
        <%--			<%List websitesWithSameGroup = (List) request.getSession().getAttribute("websitesWithSameGroup");--%>
        <%--				List websitesWithSameAddress = (List) request.getSession().getAttribute("websitesWithSameAddress");--%>
        <%--				List allWebsitesList = (List) request.getSession().getAttribute("allWebsitesForCompare");--%>
        <%--				String tabSizeStr = (String)request.getSession().getAttribute("tabSize");--%>
        <%--				int tabSize = Integer.parseInt(tabSizeStr);--%>
        <%--				String hrefStr;--%>
        <%--				String classStr;--%>
        <%--				int counter = 1;%>--%>

        <%--			<div class="mb-3 card">--%>
        <%--				<div class="card-header-title">--%>
        <%--					<h5 class="card-title">Karşılaştırılacak Websiteleri</h5>--%>
        <%--				</div>--%>
        <%--				<div>--%>
        <%--					<p2 style="color:red">En fazla 3 websitesi karşılaştırılabilir</p2>--%>
        <%--				</div>--%>
        <%--				<div class="card-header-tab card-header">--%>
        <%--					<ul class="nav">--%>
        <%--						<li class="nav-item"><a data-toggle="tab" href="#tab-eg5-0" class="active nav-link">Tüm Websiteler</a><label id ="label-eg5-0" class="customLabel">2</label>--%>
        <%--						<li class="nav-item"><a data-toggle="tab" href="#tab-eg5-1" class="nav-link">Sektördeki Websiteler</a><label id ="label-eg5-1" class="customLabel">1</label>--%>
        <%--						<li class="nav-item"><a data-toggle="tab" href="#tab-eg5-2" class="nav-link"><%=(String)request.getSession().getAttribute("website")%></a><label id ="label-eg5-2" class="customLabel">3</label>--%>
        <%--					</ul>--%>
        <%--				</div>--%>
        <%--				<div class="card-body">--%>
        <%--					<div class="scroll-area-md">--%>
        <%--						<div class="scrollbar-container ps--active-y">--%>
        <%--							<div class="tab-content">--%>
        <%--								<%if(allWebsitesList != null){--%>
        <%--									Website web;--%>
        <%--								%>--%>
        <%--								<div class="tab-pane active" id="tab-eg5-0" role="tabpanel">--%>
        <%--									<ul class="list-group">--%>

        <%--										<%--%>
        <%--											for(int i=0; i<allWebsitesList.size(); i++){--%>
        <%--												web = (Website) allWebsitesList.get(i);--%>
        <%--										%>--%>
        <%--										<label class="container"> <input name="websiteName" id="<%=String.valueOf(web.getWebsiteId())%>" type="checkbox" onclick="setCountLayer(0);"><%=web.getWebsite()%> - <%=web.getCrDate()%>  <span class="checkmark"></span>--%>
        <%--											</input>--%>
        <%--										</label>--%>
        <%--										<%} %>--%>
        <%--									</ul>--%>
        <%--								</div>--%>
        <%--								<%} %>--%>
        <%--								<%if(websitesWithSameGroup != null){--%>
        <%--									Website web;--%>
        <%--								%>--%>
        <%--								<div class="tab-pane" id="tab-eg5-1" role="tabpanel">--%>
        <%--									<ul class="list-group">--%>
        <%--										<%--%>
        <%--											for(int i=0; i<websitesWithSameGroup.size(); i++){--%>
        <%--												web = (Website) websitesWithSameGroup.get(i);--%>
        <%--										%>--%>
        <%--										<label class="container"> <input name="websiteName" id="<%=String.valueOf(web.getWebsiteId())%>" type="checkbox" onclick="setCountLayer(1);"><%=web.getWebsite()%> - <%=web.getCrDate()%>  <span class="checkmark"></span>--%>
        <%--											</input>--%>
        <%--										</label>--%>
        <%--										<%} %>--%>
        <%--									</ul>--%>
        <%--								</div>--%>
        <%--								<%} %>--%>
        <%--								<%if(websitesWithSameAddress != null){--%>
        <%--									Website web;--%>
        <%--								%>--%>
        <%--								<div class="tab-pane" id="tab-eg5-2" role="tabpanel">--%>
        <%--									<ul class="list-group">--%>
        <%--										<%--%>
        <%--											for(int i=0; i<websitesWithSameAddress.size(); i++){--%>
        <%--												web = (Website) websitesWithSameAddress.get(i);--%>
        <%--										%>--%>
        <%--										<label class="container"> <input name="websiteName" id="<%=String.valueOf(web.getWebsiteId())%>" type="checkbox" onclick="setCountLayer(2);"><%=web.getWebsite()%> - <%=web.getCrDate()%>  <span class="checkmark"></span>--%>
        <%--											</input>--%>
        <%--										</label>--%>
        <%--										<%} %>--%>
        <%--									</ul>--%>
        <%--								</div>--%>
        <%--								<%} %>--%>
        <%--							</div>--%>
        <%--						</div>--%>
        <%--					</div>--%>
        <%--				</div>--%>
        <%--			</div>--%>
        <%--		</div>--%>
        <div class="col-md-6" style="margin-top:40px;">
            <div class="card-header-title"><h5 class="card-title">Karşılaştırma Kriterleri</h5></div>
            <div class="card-body">
                <ul class="list-group">
                    <label class="container">
                        <input name="comparisonName" id="1" type="checkbox">Toplam Hata Sayısı
                        <span class="checkmark"></span>
                        </input></label>
                    <label class="container"> <input name="comparisonName" id="2" type="checkbox">Toplam Sayfa Sayısı
                        <span class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="3" type="checkbox">Table tag <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="4" type="checkbox">Kontrast <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="5" type="checkbox">Form tag <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="6" type="checkbox">Button tag <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="7" type="checkbox">Link hatasi <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="8" type="checkbox">Doküman dili <span
                            class="checkmark"></span></input></label>
                    <label class="container"> <input name="comparisonName" id="9" type="checkbox">Img tag <span
                            class="checkmark"></span></input></label>
                </ul>
            </div>
            <div style="margin-right:auto;margin-top:0px">
                <button class="mb-2 mr-2 btn btn-primary btn-lg btn-block" onclick="compareWebsites();"
                        style="font-size: 16px;">KARŞILAŞTIR
                </button>
            </div>
        </div>
        <% List comparisonListTotal = (List) request.getSession().getAttribute("comparisonDashboardCounts");
            ComparisonChart compData;
            if (comparisonListTotal != null && comparisonListTotal.size() > 1) {
        %>
        <div class="col-md-15">
            <div class="main-card mb-3 card">
                <div class="card-body"><h5 class="card-title">Karşılaştırmalı Hata Analiz Tablosu</h5>
                    <table class="mb-0 table table-hover">
                        <thead>
                        <tr>
                            <th>Websitesi</th>
                            <%
                                for (int i = 0; i < comparisonListTotal.size(); i++) {
                                    compData = (ComparisonChart) comparisonListTotal.get(i);
                            %>
                            <th><%=compData.getLabel()%>
                            </th>
                            <%} %>
                        </tr>
                        <%for (int i = 0; i < comparisonWebsiteList.size(); i++) {%>
                        <tr>
                            <th scope="row"><%=comparisonWebsiteList.get(i) %>
                            </th>

                            <%
                                for (int j = 0; j < comparisonListTotal.size(); j++) {
                                    compData = (ComparisonChart) comparisonListTotal.get(j);
                            %>
                            <td><%=compData.getData()[i] %>
                            </td>
                            <%} %>
                        </tr>
                        <%} %>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <%} %>
        <div class="col-md-6" id="canvasDiv" style="display:none;">
            <div class="main-card mb-3 card" id="canvasInsideDiv">
                <div class="card-body">
                    <h5 class="card-title">Karşılaştırmalı Hata Analiz Grafiği</h5>
                    <canvas id="canvas"></canvas>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="radarDiv" style="display:none;">
            <div class="main-card mb-3 card">
                <div class="card-body">
                    <h5 class="card-title">Karşılaştırmalı Hata Analiz Grafiği(Devamı)</h5>
                    <canvas id="radar-chart"></canvas>
                </div>
            </div>
        </div>

    </div>
</form>
<script type="text/javascript" src="../static/main.js"></script>

</body>
</html>