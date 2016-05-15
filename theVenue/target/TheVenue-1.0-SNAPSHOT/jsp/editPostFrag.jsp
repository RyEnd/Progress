<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row" id="editPostForm">
    <div class="col-xs-1"></div>
    <div class="col-xs-10">
        <h2 class="text-center">Edit Blog Post</h2>

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="editPostTitle" class="col-xs-2 control-label">
                    Name of Blog Post:
                </label>

                <div class="col-xs-8">
                    <input type="text"
                           class="form-control"
                           id="editPostTitle"
                           placeholder="Title of Blog Post"/>
                </div>
            </div>
            <input type="hidden" id="editAuthorId">
            <div class="form-group">
                <label for="startDatepicker" class="col-xs-2 control-label">
                    Start Date</label>

                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="startEditDatepicker">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="endDatepicker" class="col-xs-2 control-label">
                    End Date:</label>

                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="endEditDatepicker">
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
                        <select class="form-control"id="editCategoryList">
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="">  
                        <input type="text" class="col-xs-6 form-control" id="addEditCategoryInput">
                    </div>
                    <div class="btn btn-sm btn-info gradient" id="addEditCategoryButton">Add Category</div>
                </div>
            </div>
            <div class="form-group">
                <label for="addTag" class="col-xs-2 control-label">
                    Tags:</label>

                <div class="col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" id="editTagsInput" placeholder="Add a tag here">
                        <span class="input-group-addon btn btn-sm btn-info gradient" id="createEditTagsButton">Add Tag</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-2"></div>
                <div class="col-xs-8">
                    <div id="tagsEditPlaceholder"></div>
                </div>
            </div>
            <div class="form-group">
                <input type="hidden" id="edit-postId">
                <label for="editPostContent" class="col-xs-2 control-label">
                    Blog:</label>

                <div class="col-xs-9">
                    <div class="col-xs-11">
                        <textarea style="height: 300px;"class="form-control" name="editPostContent" id="editPostContent"></textarea>
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
        <div class="validationErrors text-center" style="color: red"></div>
        <div class="row text-center">
            <button class="btn btn-info gradient" id="cancelEditBlog" value="">Cancel</button>
            <button class="btn btn-info gradient" id="saveEditDraftBtn" value="">Save As Draft</button>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <button class="btn btn-info gradient" id="archiveEditBtn" value="">Archive</button>
            </sec:authorize>
            <button class="btn btn-info gradient" id="unPublishedEditBtn" value="">Done(Un-Published)</button>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <button class="btn btn-info gradient" id="publishEditBtn" value="">Publish</button>
            </sec:authorize>
        </div>
    </div>
</div>