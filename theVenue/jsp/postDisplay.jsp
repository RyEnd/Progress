<%-- 
    Document   : postDisplay
    Created on : Apr 29, 2016, 10:01:07 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.title}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    </head>
    <jsp:include page="HeaderNavBarFrag.jsp" />
    <body>
        <div class="container-fluid col-md-10 col-md-offset-1">
        <h1>Hello From post display</h1>
            <div class="blog">
                <h3> Blog no #${post.postId} <h3>${post.title}</h3><span style="float: right" >${post.publishDate}</span>.</h3>
                <p>${post.body}</p>                   
                <h3>Tags-####,###,### <span style="float: right" >By- ${post.author.authorName}</span>.</h3>
            </div>
            <br/><br/>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>