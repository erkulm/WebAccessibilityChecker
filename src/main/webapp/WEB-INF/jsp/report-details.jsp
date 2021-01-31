<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <style> <%@include file="/WEB-INF/static/style.css"%> </style>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"/>
    <link rel="stylesheet" href="./style2.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"></script>
    <title>Report Details</title>
</head>
<body>
<div class="mainDiv px-5 pt-5">
    <div class="col">
        <div class="row">
            <p class="display-4 maintitle">Web Accessibility Analysis</p>
        </div>
        <div class="row sort_row px-5">
            <p class="lead">Report Details</p>
            <div class="ui floating dropdown labeled icon button ml-auto mr-5">
                <i class="filter icon"></i>
                <span class="text">Filter Posts</span>
                <div class="menu">
                    <div class="ui icon search input">
                        <i class="search icon"></i>
                        <input type="text" placeholder="Search tags...">
                    </div>
                    <div class="divider"></div>
                    <div class="scrolling menu">
                        <div class="item">
                            <div class="ui red empty circular label"></div>
                            <a href="?website=${errorReport.website}&f=last_week" class="btn">Last 7 Days</a>
                        </div>
                        <div class="item">
                            <div class="ui blue empty circular label"></div>
                            <a href="?website=${errorReport.website}&filter=last_month" class="btn">Last Month</a>
                        </div>
                        <div class="item">
                            <div class="ui black empty circular label"></div>
                            <a href="?website=${errorReport.website}&filter=six_months" class="btn">Last 6 Months</a>
                        </div>
                        <div class="item">
                            <div class="ui purple empty circular label"></div>
                            <a href="?website=${errorReport.website}&filter=last_year" class="btn">Last Year</a>
                        </div>
                        <div class="item">
                            <div class="ui orange empty circular label"></div>
                            <a href="?website=${errorReport.website}&filter=two_year" class="btn">Last 2 Year</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui floating dropdown labeled icon button mr-5">
                <i class="sort amount down icon"></i>
                <span class="text">Sort</span>
                <div class="menu">
                    <div class="scrolling menu">
                        <div class="item">
                            <a href="?website=${errorReport.website}&sort=error_asc" class="btn">Error Number by Ascending</a>
                        </div>
                        <div class="item">
                            <a href="?website=${errorReport.website}&sort=error_desc" class="btn">Error Number by Descending</a>
                        </div>
                        <div class="item">
                            <a href="?website=${errorReport.website}&sort=date_asc" class="btn">Analysis Date by Ascending</a>
                        </div>
                        <div class="item">
                            <a href="?website=${errorReport.website}&sort=date_desc" class="btn">Analysis Date by Descending</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row historyTable">
        <table class="ui celled compact table">
            <thead>
            <tr>
                <th>#</th>
                <th>
                    <div>
                        SubUrl
                    </div>
                </th>
                <th>
                    <div>
                        Total Error
                    </div>
                </th>
                <th>
                    <div>
                        Detail
                    </div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="error" items="${errorReport.subPageErrors}" varStatus="status">
                <tr>
                    <td class="collapsing">
                        <div class="px-3 row_divs">
                                ${status.index + 1}
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <p>${error.subPage}</p>
                            <div class="badge badge-primary history_badge px-2 py-1">
                                <p class="history_date">${errorReport.createdDate}</p>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <p>${error.errors!= null ? error.errors.size():0}</p>
                            <a href="/error-details?id=${error.id}">Details</a>
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <a href="/detail-history?subPageErrorsId=${error.id}">Details</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<script>
    $('.ui.dropdown').dropdown();
</script>
</body>
</html>