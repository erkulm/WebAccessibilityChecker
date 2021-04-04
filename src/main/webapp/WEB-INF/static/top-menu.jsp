<div class="ui secondary  menu">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <a class="active" href="/index">
        <img alt="EngelsizWeb" width="100px" height="100px"
             src="../images/logo.png">
    </a>
    <a class="item" href="/history">
        History
    </a>
    <div class="right menu">
        <a class="ui item" href="<c:url value="/perform_logout" />">
            Logout
        </a>
    </div>
</div>