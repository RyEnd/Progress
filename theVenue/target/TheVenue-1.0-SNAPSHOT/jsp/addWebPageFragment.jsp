<%-- 
    Document   : addWebPageFragment
    Created on : 23-Apr-2016, 5:40:00 PM
    Author     : Nathan Ward <nateward.nw@gmail.com>
--%>

<div class="row" id="addForm">
    <div class="col-xs-1"></div>
    <div class="col-xs-10">
        <h2 class="text-center" id="addStaticPageTitle">Add Web Page</h2>

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addPageTitle" class="col-xs-2 control-label">
                    Name of Web Page:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="form-control"
                           id="addPageTitle"
                           placeholder="Title of Web Page"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addHeading" class="col-xs-2 control-label">
                    Heading Name:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="form-control"
                           id="addHeading"
                           placeholder="Heading Name Here"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addParentPages" class="col-xs-2 control-label">
                    Choose a parent page. Leave empty if this Web Page is a parent page:</label>
                <br/>
                <div class="col-xs-8">
                    <select id="addParentPagesList" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="addNavBarName" class="col-xs-2 control-label">
                    Nav Bar Name:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="pull-left form-control"
                           id="addNavBarName"
                           placeholder="Nav Bar Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addPageContent" class="col-xs-2 control-label">
                    Content of the Web Page:</label>

                <div class="col-md-8" style="width: 740px;">
                    <textarea style="height: 300px; width: 737px"class="form-control" name="addPageContent" id="addPageContent"></textarea>
                </div>
            </div>
        </form>
    </div>
    <div class="col-xs-1"></div>
</div>
<div class="row">
    <div class="col-xs-2"></div>
    <div class="col-xs-8">
        <div class="row">
            <div class="col-xs-3"></div>
            <div class="col-xs-2">
                <button class="btn btn-info gradient" id="cancelStaticPage">Cancel</button>
            </div>
            <div class="col-xs-2">
                <button class="btn btn-info gradient" id="addStaticPage">Save</button>
            </div>
            <div class="col-xs-2">
                <button class="btn btn-info gradient" id="unsavedPreviewStaticPage">Preview</button>
            </div>
        </div>
    </div>
</div>