<%-- 
    Document   : preview
    Created on : Apr 29, 2016, 11:16:55 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${page.urlTitle}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    </head>
    <jsp:include page="dumbHeaderNavBarFrag.jsp" />
    <body>
        <div class="col-xs-10 col-xs-offset-1">
            <div>
                <h1 class="text-center">${page.title}</h1>
            </div>
            <div>
                ${page.body}
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>