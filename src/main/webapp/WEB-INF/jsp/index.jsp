<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"/>
    <title>Web Accessibility Checker</title>
</head>
<body>
<div class="px-4">
    <div>
        <%@include file="../static/top-menu.jsp" %>
    </div>
</div>
<div class="container mt-5 pb-5">

    <form method="post" action="/detail">
        <h1 class="display-3 text-center mb-5">Welcome</h1>
        <div class="input-group urlInput">
            <div class="ui checkbox mt-1 mr-2">
                <input id="deep_check" class="fa-money-check-alt" type="checkbox" name="deep"/>
                <label>Full Inspection</label>
            </div>
            <input type="text" class="form-control" name="url">
            <div class="input-group-append">
                <button class="btn btn-success" type="submit">Test!</button>
            </div>
        </div>
    </form>
</div>


<script src="//code.jquery.com/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>