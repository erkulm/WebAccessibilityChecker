<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
    <title>Web Accessibility Checker</title>
</head>
<body>

<div class="container mt-5 pb-5">
    <form method="post" action="/detail">
        <h1 class="display-3 text-center mb-5">Welcome</h1>
        <div class="input-group urlInput">
            <label for="deep_check">
                <small>Full Inspection</small>
                <input id="deep_check" class="fa-money-check-alt" type="checkbox" name="deep"/>
            </label>
            <input type="text" class="form-control" name="url">
            <div class="input-group-append">
                <button class="btn btn-success" type="submit">Go!</button>
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