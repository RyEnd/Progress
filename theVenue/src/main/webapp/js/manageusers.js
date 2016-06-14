/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    loadUsers();

    $('#add-button').click(function (event) {
        alert("add button clizzicked");
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/TheVenue/user',
            data: JSON.stringify({
                userName: $('#add-user-name').val(),
                email: $('#add-email').val(),
                password: $('#add-password').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            alert("success");
            $('#add-user-name').val('');
            $('#add-password').val('');
            $('#add-email').val('');
            loadUsers();
            clearErrors();
        }).error(function (data, status) {
            var errorDiv = $('#validationErrors');
            clearErrors();
            $.each(data.responseJSON.fieldErrors,
                    function (index, validationError) {
                        errorDiv.append(validationError.message).append($('<br>'));
                    });
        }).error(function (){
            alert("error");
        });
    });



    $('#edit-button').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/TheVenue/user/' + $('#edit-user-id').val(),
            data: JSON.stringify({
                userName: $('#edit-user-name').val(),
                email: $('#edit-email').val(),
                password: $('#edit-password').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'

        }).success(function (data, status) {
            hideEditForm();
            loadUsers();
            clearErrors();
        }).error(function (data, status) {
            var errorDiv = $('#validationErrorsEdit');
            clearErrors();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

});
function loadUsers() {

    clearUserTable();

    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/TheVenue/users'
    }).success(function (data, status) {
        $.each(data, function (index, user) {
            var username = user.username;
            var email = user.email;
            var id = user.userId;

            var row = '<tr>';
            row += '<td>' + username + '</td>';
            row += '<td>' + email + '</td>';
            row += '<td><a onclick="showEditForm(' + id + ')">Edit</a></td>';
            row += '<td><a onclick="deleteUser(' + id + ')">Delete</a></td>';
            row += '</tr>';
            contentRows.append(row);


        });
    });


}

function clearErrors() {
    $('#validationErrors').empty();

    $('#validationErrorsEdit').empty();
}

function clearUserTable() {

    $('#contentRows').empty();

}

function deleteUser(userId) {

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/TheVenue/user/' + userId
    }).success(function () {
        hideEditForm();
        loadUsers();
    });
}

function showEditForm(userId) {

    $.ajax({
        type: 'GET',
        url: 'user/' + userId
    }).success(function (user, status) {
        $('#edit-user-name').val(user.username);
        $('#edit-email').val(user.email);
        $('#edit-password').val(user.password);
        $('#edit-user-id').val(user.userId);
        $('#editFormDiv').show();
        $('#addFormDiv').hide();
    });
}

function hideEditForm() {

    $('#edit-user-name').val('');
    $('#edit-email').val('');
    $('#edit-password').val('');
    $('#edit-user-id').val('');
    $('#addFormDiv').show();
    $('#editFormDiv').hide();
}