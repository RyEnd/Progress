<%-- 
    Document   : newjspplay
    Created on : May 4, 2016, 10:45:06 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/d3.layout.cloud.js"></script>
    <script>
        var fill = d3.scale.category20();

        d3.layout.cloud().size([300, 300])
                .words([
                    ".NET", "Silverlight", "jQuery", "CSS3", "HTML5", "JavaScript", "SQL", "C#"].map(function (d) {
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
            d3.select("tagCloud").append("svg")
                    .attr("width", 300)
                    .attr("height", 300)
                    .append("g")
                    .attr("transform", "translate(150,150)")
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
                    .attr("transform", function (d) {
                        return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                    })
                    .text(function (d) {
                        return d.text;
                    });
        }
    </script>
</html>
