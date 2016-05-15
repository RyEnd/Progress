<%-- 
    Document   : listBlogsFragment
    Created on : 20-Apr-2016, 4:26:49 PM
    Author     : Nathan Ward <nateward.nw@gmail.com>
--%>

<div class="col-xs-10 col-xs-offset-1">
    <div class="row">
        <div class="col-xs-6">
            <button class="btn btn-info pull-left gradient" id="newBlogButton">New Blog</button>
        </div>
    </div>
    <div class="row">
        <table id="listOfActivePosts" class="grid table table-hover">
            <caption class="text-center"><h4>Published Blog Posts</h4></caption>
            <thead>
                <tr>
                    <th class="index" width="10%">Post ID</th>
                    <th width="30%">Post Title (click to edit)</th>
                    <th width="20%">Author</th>
                    <th width="30%">Date Published</th>
                    <th width="10%">Archive</th>
                    <th width="10%">Delete</th>
                </tr>
            </thead>
            <tbody id="activePostRows">
            </tbody>
        </table>
    </div>
    <div class="col-xs-12">
        <br/>
        <br/>
    </div>
    <div class="row">
        <table id="listOfInactivePosts" class="table table-hover">
            <caption class="text-center"><h4>Blog Post Archive</h4></caption>
            <thead class="Unassigned">
                <tr>
                    <th class="index" width="10%">Post ID</th>
                    <th width="30%">Post Title (click to edit)</th>
                    <th width="20%">Author</th>
                    <th width="20%">Date Published</th>
                    <th width="10%">Status</th>
                    <th width="10%">Publish</th>
                    <th width="10%">Delete</th>
                </tr>
            </thead>
            <tbody id="inactivePostRows">
            </tbody>
        </table>
    </div>
</div>