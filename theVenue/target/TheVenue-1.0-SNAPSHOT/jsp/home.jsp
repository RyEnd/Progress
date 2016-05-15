<%-- 
    Document   : home
    Created on : Apr 19, 2016, 2:21:10 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Venue</title>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" />

    </head>
    <%@include file="HeaderNavBarFrag.jsp"%>
    <body>
        <div class="container">
            <div id="listofblogs">
                <div class="blog">

                    <h3> Blog no 1) Title of the blog<span style="float: right" >Date</span>.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut ante ligula. 
                        Mauris porttitor, nulla eget pulvinar fermentum, lacus augue sagittis tortor, 
                        imperdiet pretium mi ligula vitae velit. Donec gravida dictum efficitur. Nullam
                        auctor sem at rutrum convallis. Aliquam erat volutpat. Sed tempor aliquam enim vel
                        tempus. Pellentesque consectetur pellentesque ex vitae mattis. Quisque ex tortor,
                        vestibulum tristique enim non, venenatis sollicitudin justo. Quisque id ultrices 
                        enim, nec faucibus diam. Fusce accumsan, tortor vel finibus pretium, neque ligula 
                        imperdiet urna, et hendrerit metus massa eu tellus. Suspendisse nibh lectus, eleifend 
                        ut eleifend ut, facilisis non enim. Fusce convallis metus a.
                    </p>                   
                    <h3>Tags-####,###,### <span style="float: right" >By- Henry</span>.</h3>
                </div><br/><br/>
                <div class="blog">

                    <h3>Blog no. 2)Title of the blog<span style="float: right" >Date</span>.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut ante ligula. 
                        Mauris porttitor, nulla eget pulvinar fermentum, lacus augue sagittis tortor, 
                        imperdiet pretium mi ligula vitae velit. Donec gravida dictum efficitur. Nullam
                        auctor sem at rutrum convallis. Aliquam erat volutpat. Sed tempor aliquam enim vel
                        tempus. Pellentesque consectetur pellentesque ex vitae mattis. Quisque ex tortor,
                        vestibulum tristique enim non, venenatis sollicitudin justo. Quisque id ultrices 
                        enim, nec faucibus diam. Fusce accumsan, tortor vel finibus pretium, neque ligula 
                        imperdiet urna, et hendrerit metus massa eu tellus. Suspendisse nibh lectus, eleifend 
                        ut eleifend ut, facilisis non enim. Fusce convallis metus a.
                    </p>                   
                    <h3>Tags-####,###,### <span style="float: right" >By- Henry</span>.</h3>
                </div><br/><br/>
                <div class="blog">

                    <h3>Blog no. 3 Title of the blog<span style="float: right" >Date</span>.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut ante ligula. 
                        Mauris porttitor, nulla eget pulvinar fermentum, lacus augue sagittis tortor, 
                        imperdiet pretium mi ligula vitae velit. Donec gravida dictum efficitur. Nullam
                        auctor sem at rutrum convallis. Aliquam erat volutpat. Sed tempor aliquam enim vel
                        tempus. Pellentesque consectetur pellentesque ex vitae mattis. Quisque ex tortor,
                        vestibulum tristique enim non, venenatis sollicitudin justo. Quisque id ultrices 
                        enim, nec faucibus diam. Fusce accumsan, tortor vel finibus pretium, neque ligula 
                        imperdiet urna, et hendrerit metus massa eu tellus. Suspendisse nibh lectus, eleifend 
                        ut eleifend ut, facilisis non enim. Fusce convallis metus a.
                    </p>                   
                    <h3>Tags-####,###,### <span style="float: right" >By- Henry</span>.</h3>
                </div><br/><br/>
                <div class="blog">

                    <h3>Blog no 4 Title of the blog<span style="float: right" >Date</span>.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ut ante ligula. 
                        Mauris porttitor, nulla eget pulvinar fermentum, lacus augue sagittis tortor, 
                        imperdiet pretium mi ligula vitae velit. Donec gravida dictum efficitur. Nullam
                        auctor sem at rutrum convallis. Aliquam erat volutpat. Sed tempor aliquam enim vel
                        tempus. Pellentesque consectetur pellentesque ex vitae mattis. Quisque ex tortor,
                        vestibulum tristique enim non, venenatis sollicitudin justo. Quisque id ultrices 
                        enim, nec faucibus diam. Fusce accumsan, tortor vel finibus pretium, neque ligula 
                        imperdiet urna, et hendrerit metus massa eu tellus. Suspendisse nibh lectus, eleifend 
                        ut eleifend ut, facilisis non enim. Fusce convallis metus a.
                    </p>                   
                    <h3>Tags-####,###,### <span style="float: right" >By- Henry</span>.</h3>
                </div><br/><br/>

            </div>
        </div>
        <div class="container">
            <div id="blog-post-display"></div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <script src="js/blogList.js" type="text/javascript"></script>
    </body>
</html>
