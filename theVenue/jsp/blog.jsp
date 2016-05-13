<%-- 
    Document   : blogs
    Created on : Apr 23, 2016, 10:58:18 AM
    Author     : apprentice
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A Blog!</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <style>
            .well{
                background: none;
                background-color: rgba(999, 999, 999, 0.2);
                text-decoration: none;
                border-color: black;
            }
            .panel-default > .panel-heading {
                background-color: black;
            }
            body {
                background-image:url("http://www.dougwalterslive.com/images/bg_blurryStage.jpg");
                background-attachment:fixed;
                background-size:cover;
            }
            img {
                max-width: 100%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="HeaderNavBarFrag.jsp" />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <input type="hidden" id="tArray" value='${tArray}'/>
                    <div id="tagCloud"></div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <h1 style="color:white">This is the List of Blog Posts!</h1>
                </div>
            </div>
            <div class="col-md-5 col-md-offset-1">
                <table>
                    <tr>
                        <th style="color:white">Categories:  </th>
                        <td><h5><a href="${pageContext.request.contextPath}/blog/active"> All |</a></h5></td>
                        <c:forEach var="category" items="${cList}">
                            <td><h5><a href="${pageContext.request.contextPath}/blog/category/${category.categoryName}">${category.categoryName}  |  </a></h5></td>
                        </c:forEach>
                    </tr>
                </table>
            </div>
            <div id="activePostsDiv">
                <jsp:include page="postListFrag.jsp" />
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogList.js"></script>
        <script src="http://d3js.org/d3.v3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/d3.layout.cloud.js"></script>
        <script>
            $.ajax({
                url: '${pageContext.request.contextPath}/tagCloud',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            }).success(function (data, status) {
                var tArray = data;

                var fill = d3.scale.category20();

                d3.layout.cloud().size([600, 200])
                        .words(tArray.map(function (d) {
                            return {text: d, size: 10 + Math.random() * 50};
                        }))
                        .rotate(function () {
                            return ~~(Math.random() * 2) * 90;
                        })
                        .font("Impact")
                        .fontSize(function (d) {
                            return d.size;
                        })
                        .on("end", draw)
                        .start();

                function draw(words) {
                    d3.select("#tagCloud").append("svg")
                            .attr("width", 600)
                            .attr("height", 200)
                            .append("g")
                            .attr("transform", "translate(300,100)")
                            .selectAll("text")
                            .data(words)
                            .enter().append("text")
                            .style("font-size", function (d) {
                                return d.size + "px";
                            })
                            .style("font-family", "Impact")
                            .style("fill", function (d, i) {
                                return fill(i);
                            })
                            .attr("text-anchor", "middle")
                            .attr("onClick", function (d) {
                                var url = "${pageContext.request.contextPath}/blog/tag/" + d.text;
                                setTimeout('window.loaction.href=' + url, 0);
                            })
                            .attr("transform", function (d) {
                                return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                            })
                            .text(function (d) {
                                return d.text;
                            });
                }
            });
        </script>
    </body>
</html>
