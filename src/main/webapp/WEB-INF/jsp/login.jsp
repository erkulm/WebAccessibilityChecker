<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Web Accessibility Checker</title>
    <style type="text/css">
        body {
            background-color: #DADADA;
        }

        body > .grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }
    </style>
    <%--    <script>--%>
    <%--        $(document)--%>
    <%--            .ready(function() {--%>
    <%--                $('.ui.form')--%>
    <%--                    .form({--%>
    <%--                        fields: {--%>
    <%--                            email: {--%>
    <%--                                identifier  : 'email',--%>
    <%--                                rules: [--%>
    <%--                                    {--%>
    <%--                                        type   : 'empty',--%>
    <%--                                        prompt : 'Please enter your e-mail'--%>
    <%--                                    },--%>
    <%--                                    {--%>
    <%--                                        type   : 'email',--%>
    <%--                                        prompt : 'Please enter a valid e-mail'--%>
    <%--                                    }--%>
    <%--                                ]--%>
    <%--                            },--%>
    <%--                            password: {--%>
    <%--                                identifier  : 'password',--%>
    <%--                                rules: [--%>
    <%--                                    {--%>
    <%--                                        type   : 'empty',--%>
    <%--                                        prompt : 'Please enter your password'--%>
    <%--                                    },--%>
    <%--                                    {--%>
    <%--                                        type   : 'length[6]',--%>
    <%--                                        prompt : 'Your password must be at least 6 characters'--%>
    <%--                                    }--%>
    <%--                                ]--%>
    <%--                            }--%>
    <%--                        }--%>
    <%--                    })--%>
    <%--                ;--%>
    <%--            })--%>
    <%--        ;--%>
    <%--    </script>--%>
</head>
<body>

<div class="container mt-5 pb-5">
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2 class="ui teal image header">
                <img src="../images/logo.png" class="image" alt="logo">
                <div class="content">
                    Log-in to your account
                </div>
            </h2>
            <form class="ui large form" action="login_perform" method='POST'>
                <div class="ui stacked segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="user icon"></i>
                            <input type="text" name="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" name="password" placeholder="Password">
                        </div>
                    </div>
                    <button class="ui fluid large teal submit button">Login</button>
                </div>


            </form>

            <c:if test="${param.error == true}">

                <div class="ui error message">Username or password is wrong</div>
            </c:if>

            <h1>${pageContext.request.contextPath}</h1>

            <%--            <div class="ui message">--%>
            <%--                New to us? <a href="#">Sign Up</a>--%>
            <%--            </div>--%>
        </div>
    </div>
    <%--    <form name='f' action="login_perform" method='POST'>--%>
    <%--        <table>--%>
    <%--            <tr>--%>
    <%--                <td>User:</td>--%>
    <%--                <td><input type='text' name='username' value=''></td>--%>
    <%--            </tr>--%>
    <%--            <tr>--%>
    <%--                <td>Password:</td>--%>
    <%--                <td><input type='password' name='password' /></td>--%>
    <%--            </tr>--%>
    <%--            <tr>--%>
    <%--                <td><input name="submit" type="submit" value="submit" /></td>--%>
    <%--            </tr>--%>
    <%--        </table>--%>
    <%--    </form>--%>
</div>


<script src="//code.jquery.com/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>