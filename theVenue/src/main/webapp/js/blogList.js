var sampleBlogs = {
    blog1: {
        title: "Blog no 1) Title of the blog",
        body: "Body of blog 1",
        date: "Date1"
    },
    blog2: {
        title: "Blog no 2) Title of the blog",
        body: "Body of blog 2",
        date: "Date1"
    },
    blog3: {
        title: "Blog no 3) Title of the blog",
        body: "Body of blog 3",
        date: "Date1"
    }
};

$(document).ready(function () {
});

function clearDropDown() {
    $('#dropDownMenu').empty();
}

function getPostsByTag(tagId) {
    $.ajax({
        url: 'postsByTag',
        data: JSON.stringify(tagId),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        hideAll();
        clearPageList();
        loadPageList();
        $('#pagesDiv').show();
    });
}

function populateBlogposts(data, status) {
    var blogPanel = $('#blog-post-display');

    $.each(data, function (index, post) {
        var title = ' <h3>' + post.title + '</h3>';
        blogPanel.append(title);

    });
}



/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//dummy data
//$(document).ready(function () {
//   populateBlogposts(sampleBlogs);
//});





function showlistofblogs() {
    $('#listofblogs').show();
    $('#blogdetail').hide();
}
function  showblog() {
    $('#blogdetail').show();
    $('#listofblogs').hide();
}

