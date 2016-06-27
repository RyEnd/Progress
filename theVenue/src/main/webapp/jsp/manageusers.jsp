<%-- 
    Document   : manageusers
    Created on : Apr 29, 2016, 10:07:18 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navBarStyling.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" />
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    </head>
    <%@include file="HeaderNavBarFrag.jsp"%>
    <body>
        <div class="container">
            <!--h1>Blog Users</h1-->
            <!--
            Add a row to our container - this will hold the summary table and the new contact form.
            -->
            <div class="row">
                <!-- #2: Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-6">
                    <div id="contactTableDiv">
                        <h2>The Venue Users</h2>

                        <table id="userTable" class="table table-hover">
                            <tr>
                                <th width="40%">User Name</th>
                                <th width="30%">Email</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="contentRows"></tbody>
                        </table>
                    </div>
                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other half of the row
                -->
                <div class="col-md-6">

                    <div id="editFormDiv" style="display: none">
                        <h2 onclick="hideEditForm()">Edit User</h2>

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-user-name" class="col-md-4 control-label">
                                    User Name:
                                </label>

                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-user-name"
                                           placeholder="User Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-email" class="col-md-4 control-label">
                                    Email:
                                </label>

                                <div class="col-md-8">
                                    <input type="email"
                                           class="form-control"
                                           id="edit-email"
                                           placeholder="Email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-password" class="col-md-4 control-label">
                                    Password:</label>

                                <div class="col-md-8">
                                    <input type="password"
                                           class="form-control"
                                           id="edit-password"
                                           placeholder="Password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">
                                    <input type="hidden" id="edit-user-id">

                                    <button type="button"
                                            id="edit-cancel-button"
                                            class="btn btn-default"
                                            onclick="hideEditForm()">
                                        Cancel
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit"
                                            id="edit-button"
                                            class="btn btn-default">
                                        Update User
                                    </button>
                                </div>
                            </div>

                        </form>
                        <div id="validationErrorsEdit" style="color: red" />
                    </div>
                </div>

                <div id="addFormDiv">

                    <h2>Add New User</h2>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-user-name" class="col-md-4 control-label">
                                User Name:
                            </label>

                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-user-name"
                                       placeholder="User Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">
                                Email:
                            </label>

                            <div class="col-md-8">
                                <input type="email"
                                       class="form-control"
                                       id="add-email"
                                       placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-password" class="col-md-4 control-label">
                                Password:</label>

                            <div class="col-md-8">
                                <input type="password"
                                       class="form-control"
                                       id="add-password"
                                       placeholder="Password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit"
                                        id="add-button"
                                        class="btn btn-default">
                                    Create User
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" style="color: red">
                </div>
            </div>
        </div> <!-- End col div -->
    </div> <!-- End row div -->
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/manageusers.js"></script>
</body>
</html>

