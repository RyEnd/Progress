<%-- 
    Document   : listStaticPages
    Created on : 23-Apr-2016, 1:40:12 PM
    Author     : Nathan Ward <nateward.nw@gmail.com>
--%>


<div class="col-xs-10 col-xs-offset-1">
    <div class="row">
        <div class="col-xs-6">
            <button class="btn btn-info pull-left gradient" id="newPageButton">New Web Page</button>
        </div>
        <div class="col-xs-6">
            <button style="display: none"class="btn btn-success pull-right gradient" id="savePageOrder">Save Nav Bar Position</button>
        </div>
    </div>
    <div class="row">
        <table id="sort" class="grid table table-hover">
            <caption class="text-center"><h4>Assigned Web Pages</h4></caption>
            <thead>
                <tr>
                    <th class="index" width="10%">Position</th>
                    <th width="35%">Web Page Title (click to edit)</th>
                    <th width="30%">Nav Bar Name</th>
                    <th width="15%">Unassign</th>
                    <th width="10%">Preview</th>
                    <th width="10%">Delete</th>
                </tr>
            </thead>
            <tbody id="activePageRows">
            </tbody>
        </table>
    </div>
    <div class="col-xs-12">
        <br/>
        <br/>
    </div>
    <div class="row">
        <table id="notInNavBar" class="table table-hover">
            <caption class="text-center"><h4>Unassigned Web Pages</h4></caption>
            <thead class="Unassigned">
                <tr>
                    <th class="index" width="10%"></th>
                    <th width="35%">Web Page Title (click Title to edit)</th>
                    <th width="30%">Nav Bar Name</th>
                    <th width="15%">Assign</th>
                    <th width="10%">Preview</th>
                    <th width="10%">Delete</th>
                </tr>
            </thead>
            <tbody id="inactivePageRows">
            </tbody>
        </table>
    </div>
</div>