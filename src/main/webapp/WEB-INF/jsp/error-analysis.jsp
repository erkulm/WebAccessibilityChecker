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
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>
    <meta name="description" content="Huge selection of charts created with the React ChartJS Plugin">
    <meta name="msapplication-tap-highlight" content="no">

    <title>Error Analysis</title>
    <link href="./main.css" rel="stylesheet">
    <link href="./ccbtemp01.css" rel="stylesheet">

    <style> <%@include file="../static/style2.css"%> </style>
    <style> <%@include file="../static/main.css"%> </style>
    <script> <%@include file="../static/script.js"%> </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

    <script>
        let websitesToCompare = [];

        function addWebsiteToCompare(element) {

            let reportId = $(element).attr('id');
            if (websitesToCompare.includes(reportId)) {
                const index = websitesToCompare.indexOf(reportId);
                if (index > -1) {
                    websitesToCompare.splice(index, 1);
                }
            } else {
                websitesToCompare.push(reportId);
            }
        }
    </script>

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

    $(document).ready(function () {

        var ctx = document.getElementById('chart-area').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ["Error 1", "Error 2", "Error 3", "Error 4", "Error 5"],
                datasets: [{
                    label: '# of Errors',
                    data: ${barChartData},
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            }
        });


        document.getElementById("canvasDiv").style.display = "block";

        if (rlabel.length > 1) {
            document.getElementById("radarDiv").style.display = "block";
        }
    });

    function compareWebsites() {
        // var websiteElements = document.getElementsByName("websiteName");
        // var comparisonElements = document.getElementsByName("comparisonName");
        //
        // var websiteIds = "";
        // for (i = 0; i < websiteElements.length; i++) {
        //     if (websiteElements[i].checked) {
        //         websiteIds = websiteIds + websiteElements[i].id + "-";
        //     }
        // }
        //
        // if (websiteIds.indexOf("-") < 0) {
        //     alert("Karşılaştırılacak websitelerini belirleyin.. ");
        //     return;
        // }
        //
        // if (websiteIds.split("-").length - 1 > 2) {
        //     alert("En fazla 3 websitesi karşılaştırılabilir");
        //     return;
        // }
        //
        // var elements = "";
        // for (i = 0; i < comparisonElements.length; i++) {
        //     if (comparisonElements[i].checked) {
        //         elements = elements + comparisonElements[i].id + "-";
        //     }
        // }
        //
        // if (elements.indexOf("-") < 0) {
        //     alert("Karşılaştırma kriterlerini belirleyin.. ");
        //     return;
        // }
        let comparisonRequest = {};
        comparisonRequest.reportIds = websitesToCompare;

        $.ajax({
            url: "/compare-reports",
            method: "POST",
            contentType: 'application/json',
            data: JSON.stringify(comparisonRequest)
        }).then(function (data) {
            console.log(data);
            var ctx = document.getElementById('canvas');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: Object.keys(data[0].data),
                    datasets: [{
                        label: data[0].address,
                        data: Object.values(data[0].data),
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderWidth: 1
                    }, {
                        label: data[1].address,
                        data: Object.values(data[1].data),
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderWidth: 1
                    }, {
                        label: data[2].address,
                        data: Object.values(data[2].data),
                        backgroundColor: 'rgba(255, 206, 86, 0.2)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        });

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
           src="../images/logo.png" onclick="returnToMainPage()">
    <h3 style="margin-left:400px;margin-right:auto;margin-top:-40px">Error Analysis Panel</h3>
    <div class="row" style="margin-left:auto;margin-right:auto;margin-top:50px">
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-grow-early">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">Total Pages</div>
                        <div class="widget-subheading">All subpages of Website</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-white"><span>${errorReport.numberOfSubPages}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-midnight-bloom">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">Total Errors</div>
                        <div class="widget-subheading">Including subpages</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-white"><span>${errorReport.totalErrors}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card mb-3 widget-content bg-primary">
                <div class="widget-content-wrapper text-white">
                    <div class="widget-content-left">
                        <div class="widget-heading">Most Recurring Error</div>
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
                    <h5 class="card-title">Most Recurring Errors</h5>
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
                    <c:if test="${status.index<5}">
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
        <div class="col-md-6" style="margin-top:40px;">
            <div class="mb-3 card">
                <div class="card-header-title">
                    <h5 class="card-title">Karşılaştırılacak Websiteleri</h5>
                </div>
                <div>
                    <p2 style="color:red">En fazla 3 websitesi karşılaştırılabilir</p2>
                </div>
                <div class="card-header-tab card-header">
                    <ul class="nav">
                        <li class="nav-item"><a data-toggle="tab" href="#tab-eg5-0" class="active nav-link">Tüm
                            Websiteler</a><label id="label-eg5-0" class="customLabel">2</label>
                        <li class="nav-item"><a data-toggle="tab" href="#tab-eg5-1" class="nav-link">Sektördeki
                            Websiteler</a><label id="label-eg5-1" class="customLabel">1</label>
                        <li class="nav-item"><a data-toggle="tab" href="#tab-eg5-2"
                                                class="nav-link">${website}</a><label id="label-eg5-2"
                                                                                      class="customLabel">3</label>
                    </ul>
                </div>
                <div class="card-body">
                    <div class="scroll-area-md">
                        <div class="scrollbar-container ps--active-y">
                            <div class="tab-content">
                                <c:if test="${allReports != null}">
                                    <div class="tab-pane active" id="tab-eg5-0" role="tabpanel">
                                        <ul class="list-group">

                                            <c:forEach var="report" items="${allReports}" varStatus="status">
                                                <label class="container">
                                                    <input name="websiteName_ + ${report.id}" id="${report.id}"
                                                           type="checkbox"
                                                           onclick="addWebsiteToCompare($(this));"
                                                    >
                                                    <span class="checkmark"></span>
                                                    <span>${report.website.address} - ${report.website.dateCreated}</span>
                                                </label>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </c:if>
                                <%--                                <div class="tab-pane" id="tab-eg5-1" role="tabpanel">--%>
                                <%--                                    <ul class="list-group">--%>
                                <%--                                        <c:forEach var="report" items="${reportsFromSameIndustry}" varStatus="status">--%>
                                <%--                                            <label class="container">--%>
                                <%--                                                <input name="websiteName_ + ${report.id}" id="${report.id}" type="checkbox"--%>
                                <%--                                                       onclick="setCountLayer(0); addWebsiteToCompare(this)"--%>
                                <%--                                                       >--%>
                                <%--                                                <span class=" checkmark"></span>--%>
                                <%--                                                <span>${report.website.address}</span>--%>
                                <%--                                            </label>--%>
                                <%--                                        </c:forEach>--%>
                                <%--                                    </ul>--%>
                                <%--                                </div>--%>
                                <%--                                <div class="tab-pane" id="tab-eg5-2" role="tabpanel">--%>
                                <%--                                    <ul class="list-group">--%>
                                <%--                                        <c:forEach var="report" items="${reportsFromSameWebsite}" varStatus="status">--%>
                                <%--                                            <label class="container">--%>
                                <%--                                                <input name="websiteName_ + ${report.id}" id="${report.id}"--%>
                                <%--                                                       type="checkbox"--%>
                                <%--                                                       onclick="setCountLayer(0);"--%>
                                <%--                                                       >--%>
                                <%--                                                <span class="checkmark"></span>--%>
                                <%--                                                <span>${report.website.address}</span>--%>
                                <%--                                            </label>--%>
                                <%--                                        </c:forEach>--%>
                                <%--                                    </ul>--%>
                                <%--                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                <button type="button" class="mb-2 mr-2 btn btn-primary btn-lg btn-block" onclick="compareWebsites();"
                        style="font-size: 16px;">KARŞILAŞTIR
                </button>
            </div>
        </div>
        <% List comparisonListTotal = (List) request.getSession().getAttribute("comparisonDashboardCounts");
            ComparisonChart compData;
            if (comparisonListTotal != null && comparisonListTotal.size() > 1) {
        %>
        <%--        <div class="col-md-15">--%>
        <%--            <div class="main-card mb-3 card">--%>
        <%--                <div class="card-body"><h5 class="card-title">Karşılaştırmalı Hata Analiz Tablosu</h5>--%>
        <%--                    <table class="mb-0 table table-hover">--%>
        <%--                        <thead>--%>
        <%--                        <tr>--%>
        <%--                            <th>Websitesi</th>--%>
        <%--                            <%--%>
        <%--                                for (int i = 0; i < comparisonListTotal.size(); i++) {--%>
        <%--                                    compData = (ComparisonChart) comparisonListTotal.get(i);--%>
        <%--                            %>--%>
        <%--                            <th><%=compData.getLabel()%>--%>
        <%--                            </th>--%>
        <%--                            <%} %>--%>
        <%--                        </tr>--%>
        <%--                        <%for (int i = 0; i < comparisonWebsiteList.size(); i++) {%>--%>
        <%--                        <tr>--%>
        <%--                            <th scope="row"><%=comparisonWebsiteList.get(i) %>--%>
        <%--                            </th>--%>

        <%--                            <%--%>
        <%--                                for (int j = 0; j < comparisonListTotal.size(); j++) {--%>
        <%--                                    compData = (ComparisonChart) comparisonListTotal.get(j);--%>
        <%--                            %>--%>
        <%--                            <td><%=compData.getData()[i] %>--%>
        <%--                            </td>--%>
        <%--                            <%} %>--%>
        <%--                        </tr>--%>
        <%--                        <%} %>--%>
        <%--                        </thead>--%>
        <%--                    </table>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%} %>
        <div class="col-md-11" id="canvasDiv" style="display:none;">
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