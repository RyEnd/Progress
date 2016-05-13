<%-- 
    Document   : postListFrag
    Created on : Apr 28, 2016, 8:44:00 PM
    Author     : apprentice
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3">
    <div class="row">
        <div id="listofblogs">
            <c:forEach var="post"  items="${pList}"> 
                <s:url value="/blog/post/${post.urlTitle}" var="post_url" />
                <div id="blog" class="panel panel-default">
                    <div class="panel-heading">
                        <h2 style="color:white">#${post.postId} <a href="${post_url}">${post.title}</a></h2>
                        <hr>
                        <h5 style="color: gray">Author - ${post.author.authorName} ............. Date Published: ${post.publishDate}</h5>
                    </div>
                    <div class="panel-body">
                        ${post.body}
                    </div>
                    <div class="panel-footer">
                        <h3>
                            <table>
                                <tr>
                                    <c:forEach var="tag" items="${post.tags}">
                                        <td><a href="${pageContext.request.contextPath}/blog/tag/${tag.tagName}"><h4>#${tag.tagName} </h4></a></td>
                                    </c:forEach>
                                </tr>
                            </table>
                        </h3>
                    </div>
                </div>
                <br/><br/>
            </c:forEach>
        </div>
    </div>
</div>