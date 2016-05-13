<%-- 
    Document   : ControlPanel
    Created on : 19-Apr-2016, 8:31:08 PM
    Author     : Nathan Ward <nateward.nw@gmail.com>
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control Panel</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/controlStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dist/bootstrap-tagsinput.css">
        <!--        <script src='${pageContext.request.contextPath}/js/bootstrap-tags.js'></script>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-tags.css" />-->
        <style>
            .well{
                background: none;
                background-color: rgba(999, 999, 999, 0.2);
                text-decoration: none;
                border-color: black;
            }
            .well#content{
                background: none;
                background-color: rgba(999, 999, 999, 0.4);
                text-decoration: none;
                border-color: black;
            }
            video {
                font-family: inherit;
                font-size: 100%;
                font-weight: inherit;
                font-style: inherit;
                vertical-align: baseline;
                white-space: normal;
                text-align: left;
                margin: 0;
                padding: 0;
                border: 0;
                outline: 0;
                background: transparent;
            } 
            @media only screen and (min-width: 640px) {
                #video-background {
                    display: block;
                    background-image: url("https://www.gitkraken.com/video/ink.jpg");
                    background-repeat: no-repeat;
                    background-position: center center;
                    -webkit-background-size: cover;
                    -moz-background-size: cover;
                    background-size: cover;
                    position: fixed;
                    top: 50%;
                    left: 50%;
                    min-width: 100%;
                    min-height: 100%;
                    width: auto;
                    height: auto;
                    -webkit-transform: translateX(-50%) translateY(-50%);
                    -moz-transform: translateX(-50%) translateY(-50%);
                    -o-transform: translateX(-50%) translateY(-50%);
                    -ms-transform: translateX(-50%) translateY(-50%);
                    transform: translateX(-50%) translateY(-50%);
                }
            }
            caption {
                color: #000;
            }
            #pagesDiv a {
                color: darkblue;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <video autoplay loop id ="video-background">
            <source src="https://www.gitkraken.com/video/ink.webm" type="video/webm">
            <source src="https://www.gitkraken.com/video/ink.mp4" type="video/mp4">
        </video>
        <header>

            <div class="row"> <br> </div>
            <div class="row">

                <div class="col-xs-4"></div>

                <div id="nameContainer" class="col-xs-4 well">

                    <a href="/TheVenue/home" id="homeBanner">

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
                    <a href="/TheVenue/home" class="navbar-brand">The Venue <span class="glyphicon glyphicon-home"></span></a>

                </div> <!-- End navbar-header -->

                <!-- Menu Items -->
                <div class="collapse navbar-collapse" id="mainNavBar">
                    <ul class="nav navbar-nav" id="NavTabsContent">
                        <li>
                            <a id="blogOptions" href="#">Blog Options</a>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li>
                                <a id="pageOptions" href="#">Web Page Options</a>
                            </li>
                        </sec:authorize>





                        <!--*******************************************************************************************-->

                    </ul>
                    <div class="pull-right">
                        <div class="navbar-brand" id="username">
                            <sec:authentication property="principal.username" />
                        </div>
                        <div class="pull-right">
                            <a href="${pageContext.request.contextPath}/j_spring_security_logout"> <div type="button" class="btn btn-default navbar-btn gradient">Sign out</div></a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="col-xs-12 ">
            <div class="well" id ="content">
                <div class="row" id="blogsDiv">
                    <h1 class="text-center">Edit your blogs here.</h1>
                    <%@include file="listBlogsFragment.jsp"%>
                </div>
                <div style= "display:none" id="addBlogDiv">
                    <%@include file="addBlogFragment.jsp"%>
                </div>
                <div style="display:none" id="editPostDiv">
                    <%@include file="editPostFrag.jsp"%>
                </div>
                <div class="row" style="display: none" id="pagesDiv">
                    <h1 class="text-center">Edit your Web Pages here.</h1>
                    <%@include file="listStaticPagesFragment.jsp"%>
                </div>
                <div style= "display:none" id="addWebPageDiv">
                    <%@include file="addWebPageFragment.jsp"%>
                </div>
                <div style="display: none" id="editWebPageDiv">
                    <%@include file="editWebPageFragment.jsp"%>
                </div>
            </div>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/controllerAdmin.js"></script>

    <script src="${pageContext.request.contextPath}/js/blogController.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dist/bootstrap-tagsinput.min.js"></script>
    <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
    <script type="text/javascript">
        tinymce.init({
            selector: 'textarea',
            plugins: ["image imagetools contextmenu advlist link colorpicker paste table textcolor ",
                "advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste youtube"],
            external_plugins: {"youtube": "${pageContext.request.contextPath}/js/tiny_mce/plugins/youtube/plugin.min.js"},
            // menubar: "insert",
            //toolbar: "image styleselect fontsizeselect hr link preview",
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image| youtube",
            contextmenu: "link image inserttable | cell row column deletetable",
            inline: false
        });
    </script>
    <script>
        $(function () {
            $("#startDatepicker").datepicker();
        });
        $(function () {
            $("#startEditDatepicker").datepicker();
        });

        $(function () {
            $("#endDatepicker").datepicker();
        });
        $(function () {
            $("#endEditDatepicker").datepicker();
        });
    </script>
</html>
