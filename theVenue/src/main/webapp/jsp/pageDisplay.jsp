<%-- 
    Document   : pageDisplay
    Created on : Apr 22, 2016, 10:12:44 AM
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
        <link href="${pageContext.request.contextPath}/css/simple-sidebar.css" rel="stylesheet">
    </head>
    <jsp:include page="HeaderNavBarFrag.jsp" />
    <body>
    <div class="container-fluid">
        <c:if test="${not empty childPages}">
            <div id="wrapper">
                <div id="sidebar-wrapper">
                    <ul class="sidebar-nav">
                        <li class="sidebar-brand">
                            <a href="#">
                                Related to ${page.title}:
                            </a>
                        </li>
                        <c:forEach var="childPage" items="${childPages}">
                            <s:url value="/displayPage/${childPage.urlTitle}" var="childPage_url" />
                            <li>
                                <a href="${childPage_url}">${childPage.title}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="page-content-wrapper">
                </c:if>
                <div class="container-fluid col-md-10 col-md-offset-1">
                    <div class="row">
                        <div class="lead text-center">
                            <h1>${page.title}</h1>
                        </div>
                            <h3 class="text-justify">${page.body}</h3>
                        </div>
                    </div>
                </div>
            <c:if test="${not empty childPages}">
            </div>
        </c:if>
    </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>
    </body>
</html>
