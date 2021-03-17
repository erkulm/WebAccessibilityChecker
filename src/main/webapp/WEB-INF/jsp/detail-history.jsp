<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Collection,
                 java.util.ArrayList,
                 java.util.List,
                                 edu.itu.wac.service.response.ErrorResponse, edu.itu.wac.model.ErrorCategory"%>
<%@ page import="edu.itu.wac.entity.Error" %>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<style> <%@include file="../static/style2.css"%> </style>
	<script> <%@include file="../static/script.js"%> </script>

	<title>Web Page Details</title>
</head>
<body>

<div class="px-4">
	<div>
		<%@include file="../static/top-menu.jsp" %>
	</div>
</div>


<div class="row">

	<div id="sideBar" class="col-4">

		<div class="container text-center p-3">

			<h1 class="my-3">Welcome!</h1>
			<h3>Web Accessibility Tool</h3>
			<br>
			<div class="display-4 mb-3" style="font-size:2.5rem;">
				Total Error: ${errornumber}
			</div>

			<div class="categories">
				<%


					String temp = "";
					List<ErrorCategory> errorCategoryList = (List) request.getAttribute("errorCategory");
					List<ErrorResponse> errorReportList = (List) request.getAttribute("errorlist");
					int f = 0;
					for(int i=0; i<errorCategoryList.size(); i++){
						ErrorCategory e = errorCategoryList.get(i);

						int j = 0;
						boolean isExist = false;
						for(j = 0;j<i;j++){
							ErrorCategory tempObj = errorCategoryList.get(j);
							if(tempObj.getHeader().equals(e.getHeader())){
								isExist = true;
							}
						}
						if(isExist == true){

						}
						else{
				%> <button id="buttonId" type="button" class="btn btn btn-primary btn-block text-left" data-toggle="collapse" data-target="#table<%=f%>" aria-expanded="true" aria-controls="table<%=f%>"><%=e.getHeader() %></button>
				<div id="table<%=f%>" class="collapse" >
					<table class="table table-striped mb-0" id="tableErrors">
						<tbody>
						<tr>
							<th colspan="4">
								<%=e.getDetail()%>
								<a href="<%=e.getLink()%>">More Detail</a>
							</th>
						</tr>
						<%
							int m =0;
							int l=0;
							for(m=0;m<errorReportList.size(); m++){
								ErrorResponse er = errorReportList.get(m);
								if(er.getDocument().equals(e.getHeader2())){
									l++;
						%>
						<tr>
							<th scope="row"><%=l %></th>
							<td> ERROR </td>
							<td style="position: relative;"><a name="#info_<%=er.getId()%>" class="btn btn-sm mr-1 control-8 btn-light">Show</a>

							</td>
							<td><i class="fas fa-angle-down"></i></td>
						</tr>

						<tr class="extra "><td colspan="4" ><%=er.getErrorDesc()%></td></tr>
						<tr class="extra mb-1 "><td colspan="4"><%=er.getErrorAddress()%></td></tr>

						<%

							}

						%>
						<%}%>
						</tbody>
					</table>
				</div>

				<%
							f++;
							temp = e.getHeader();
						}
					}
				%>

			</div>

			<div>
				<form action="more" method="get">
					<button type="submit" class="btn btn-secondary mt-3 float-right">More Detail</button>
				</form>
			</div>
		</div>


	</div>



	<div id="mainBar" class="col-8">

		<div class="navbar navbar-dark bg-light mt-3 text-center" id="navBar">
			<div class="text-center titleDiv">
				<h5 class="yourWebPage">${url}</h5>
			</div>
			<div id="webPart">

				${pageHtml}

			</div>


		</div>

	</div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>