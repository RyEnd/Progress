<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<header>
<div class="container fluid">
    <div class="row"> <br> </div>
    <div class="row">

        <div class="col-xs-4">
            <a href="#signIn" style="height: 50px; width: 50px" class="pull-left" data-toggle="collapse"></a>
        </div>

        <div id="nameContainer" class="col-md-4 well">

            <a href="${pageContext.request.contextPath}/home" id="homeBanner">

                <div class="row">
                    <div class="name col-xs-12 text-center">
                        <p>The Venue</p>
                    </div> 
                </div>

                <!-- <div class="row">
                  <div class="name col-xs-12 text-center">
                    <p>Add something here</p>
                  </div>
                </div> -->

            </a>

        </div> <!-- End of col-xs-4 and well -->

        <div class="col-xs-4"></div>

    </div> <!-- End row -->
</div>
</header> 

<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <!-- The Logo -->
        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        <!-- href="${pageContext.request.contextPath}/" -->
            <a href="${pageContext.request.contextPath}/home" class="navbar-brand">The Venue <span class="glyphicon glyphicon-home"></span></a>
        </div> <!-- End navbar-header -->

        <!-- Menu Items -->
        <div class="collapse navbar-collapse" id="mainNavBar">
            <ul class="nav navbar-nav" id="NavTabsContent">
              <!-- href="${pageContext.request.contextPath}/" -->
              <!-- href="${pageContext.request.contextPath}/" -->
                <!--this is the code for making navbar..--> 

                <c:forEach var="navBarItem"  items="${navBar}"> 
                    <c:choose >
                        <c:when test="${navBarItem.pageFk == 0 && navBarItem.navBarLocationId == 1}" >
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/blog/active">Home</a> 
                        </c:when>
                        <c:when test="${navBarItem.pageFk eq 0}" >
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/blog/active">Blog</a> 
                        </c:when>
                        <c:otherwise>
                            <s:url value="/displayPage/${navBarItem.page.urlTitle}" var="displayNavBar_url"/>
                            <a class="navbar-brand" href="${displayNavBar_url}">${navBarItem.page.navName}</a> 
                        </c:otherwise>
                    </c:choose>       
                </c:forEach>
                            
            </ul>
            <div class="collapse" id="signIn">
                <a href="/control"><button type="button" class="btn btn-default navbar-btn navbar-right pull-right">Sign in</button></a>
            </div>
        </div>
    </div>
</nav>