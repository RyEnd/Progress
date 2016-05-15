<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    </head>
    <%@include file="HeaderNavBarFrag.jsp"%>
    <body>
        <div>
            <!-- #1 - If login_error == 1 then there was a failed login attempt -->
            <!-- so display an error message -->
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <!-- #2 - Post to Spring security to check our authentication -->
            <br/><br/><br/><br/><br/>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form method="post" class="signin form-horizontal" action="j_spring_security_check">
                        <!--h4>Users Log In Here</h4-->
                        <div class="form-group">
                            <label for="email" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="text" name="j_username" class="form-control" id="email" placeholder="Email" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" name="j_password" class="form-control" id="password" placeholder="Password" required>
                            </div>
                        </div>

                        <div class="col-sm-offset-3 col-sm-9">
                            <a href="/TheVenue/control">
                            <button type="submit" class="btn btn-primary">Log in</button>
                            </a>
                        </div>
                    </form>
                </div>
               <div class="col-md-4"></div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

