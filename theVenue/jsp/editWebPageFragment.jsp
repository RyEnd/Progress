<div class="row" id="editForm">
    <div class="col-xs-1"></div>
    <div class="col-xs-10">
        <h2 class="text-center">Edit Web Page</h2>

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="editPageTitle" class="col-xs-2 control-label">
                    Name of Web Page:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="form-control"
                           id="editPageTitle"
                           placeholder="Title of Web Page"/>
                </div>
            </div>
            <div class="form-group">
                <label for="editHeading" class="col-xs-2 control-label">
                    Heading Name:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="form-control"
                           id="editHeading"
                           placeholder="Heading Name Here"/>
                </div>
            </div>
            <div class="form-group">
                <label for="editParentPages" class="col-xs-2 control-label">
                    Choose a parent page. Leave empty if this Web Page is a parent page:</label>
                <br/>
                <div class="col-xs-8">
                    <select id="editParentPagesList" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="editNavBarName" class="col-xs-2 control-label">
                    Nav Bar Name:
                </label>

                <div class="col-md-8">
                    <input type="text"
                           class="pull-left form-control"
                           id="editNavBarName"
                           placeholder="Nav Bar Name"/>
                </div>
            </div>
            <div class="form-group">
                <input type="hidden" id="edit-staticPageId">
                <label for="editPageContent" class="col-xs-2 control-label">
                    Content of the Web Page:</label>

                <div class="col-md-8" style="width: 740px;">
                    <textarea style="height: 300px;"class="form-control" name="editPageContent" id="editPageContent"></textarea>
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
                <button class="btn btn-info" id="cancelStaticPage">Cancel</button>
            </div>
            <div class="col-xs-2">
                <button class="btn btn-info" id="editStaticPage">Save</button>
            </div>
            <div class="col-xs-2">
                <button class="btn btn-info" id="previewSomething">Preview</button>
            </div>
        </div>
    </div>
</div>