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
    <title>Error Details</title>
    <script>
        $(document).ready(function () {
            $('#getexcel').on('click', function () {
                $.ajax({
                    url: '/get-file?type=subpage&id=${subPageErrorId}',
                    method: 'GET',
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function (data) {
                        var a = document.createElement('a');
                        var url = window.URL.createObjectURL(data);
                        a.href = url;
                        a.download = 'errors.xlsx';
                        document.body.append(a);
                        a.click();
                        a.remove();
                        window.URL.revokeObjectURL(url);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="mainDiv px-5 pt-5">

    <div class="col px4">
        <div> <%@include file="../static/top-menu.jsp"%> </div>

        <div class="row">
            <p class="display-4 maintitle">Web Accessibility Analysis</p>
        </div>
        <div class="row sort_row">
            <p class="lead">Error Details</p>
            <div class=" ml-auto mr-5">
            </div>
            <div class="ui floating dropdown labeled icon button mr-5">
                <i class="sort amount down icon"></i>
                <span class="text">Sort</span>
                <div class="menu">
                    <div class="scrolling menu">
                        <button class="ui button icon mr-2" style="border-width: 0" type="button" id="getexcel">
                            <i class="file excel icon"></i>
                            <span class="text">Excel</span>
                        </button>
                        <div class="item">
                            <a href="?id=${subPageErrorId}&sort=document_asc" class="btn">Document by Ascending</a>
                        </div>
                        <div class="item">
                            <a href="?id=${subPageErrorId}&sort=document_desc" class="btn">Document by Descending</a>
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
                        Document
                    </div>
                </th>
                <th>
                    <div>
                        Explanation
                    </div>
                </th>
                <th>
                    <div>
                        Address
                    </div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="error" items="${errors}" varStatus="status">
                <tr>
                    <td class="collapsing">
                        <div class="px-3 row_divs">
                                ${status.index + 1}
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <p>${error.document}</p>
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <p>${error.errorDesc}</p>
                        </div>
                    </td>
                    <td>
                        <div class="px-4 row_divs">
                            <p>${error.errorAddress}</p>
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