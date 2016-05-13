<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-xs-1"></div>
    <div class="col-xs-10">
        <h2 class="text-center">Add New Blog</h2>

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addBlogTitle" class="col-xs-2 control-label">
                    Blog Title:
                </label>

                <div class="col-xs-8">
                    <input type="text"
                           class="form-control"
                           id="addBlogTitle"
                           placeholder="Title of Blog Here"/>
                </div>
            </div>
            <div class="form-group">
                <label for="startDatepicker" class="col-xs-2 control-label">
                    Start Date</label>

                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="startDatepicker">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="endDatepicker" class="col-xs-2 control-label">
                    End Date:</label>

                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="endDatepicker">
                        <span class="input-group-addon "><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="addCategory" class="col-xs-2 control-label">
                    Category:
                </label>

                <div class="col-xs-4">
                    <div class="">
                        <select class="form-control"id="addCategoryList">
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="">  
                        <input type="text" class="col-xs-6 form-control" id="addCategoryInput">
                    </div>
                    <div class="btn btn-sm btn-info gradient form-control" id="addCategoryButton">Add Category</div>
                </div>
            </div>
            <!--=================================================================-->
            <div class="form-group">
                <label for="addTag" class="col-xs-2 control-label">
                    Tags:</label>

                <div class="col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" id="TagsInput" placeholder="Add a tag here">
                        <span class="input-group-addon btn btn-sm btn-info gradient" id="createTagsButton">Add Tag</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-2"></div>
                <div class="col-xs-8">
                    <div id="tagsPlaceholder"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="addBlogBody" class="col-xs-2 control-label">
                    Blog:</label>

                <div class="col-xs-9">
                    <div class="col-xs-11">
                        <textarea style="height: 300px;"class="form-control" id="addBlogBody" name="addBlogBody"></textarea>
                    </div>
                </div>
            </div>

        </form>
    </div>
    <div class="col-xs-1"></div>
</div>

<div class="row">
    <div class="col-xs-2"></div>
    <div class="col-xs-8">
        <div class="validationErrors text-center alert alert-danger"></div>
        <div class="row text-center">
            <button class="btn btn-info gradient" id="cancelAddBlog">Cancel</button>
            <button class="btn btn-info gradient" id="saveAsDraftBtn">Save As Draft</button>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <button class="btn btn-info gradient" id="archiveBtn">Archive</button>
            </sec:authorize>
            <button class="btn btn-info gradient" id="unPublishedBtn">Done(Un-Published)</button>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <button class="btn btn-info gradient" id="publishBtn">Publish</button>
            </sec:authorize>
        </div>
    </div>
</div>