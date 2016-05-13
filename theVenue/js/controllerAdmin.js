/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function (event) {

    $(document).on('click', '.removeTag', function (event) {
        $(this).parent().remove();
    });

    loadPostList();
    $('#blogOptions').click(function (event) {
        hideAll();
        clearTags();
        $('#blogsDiv').show();
    });
    $('#savePageOrder').click(function (event) {
        saveStaticPageOrder(event);
    });
    $('#pageOptions').click(function (event) {
        loadPageList();
        clearTags();
        hideAll();
        $('#pagesDiv').show();
    });
    $('#newBlogButton').click(function (event) {
        hideAll();
        clearCategoriesFields();
        clearTags();
        loadCategories();
        $('#addBlogDiv').show();
    });
    $('#newPageButton').click(function (event) {
        hideAll();
        clearParentPagesFields();
        loadParentPages();
        $('#addWebPageDiv').show();
    });
    $('#createEditTagsButton').click(function (event) {
        event.preventDefault();
        var tag = $('#editTagsInput').val();
        $('#editTagsInput').val('');
        tag = createTagButton(tag);
        var allTags = $('#tagsEditPlaceholder').html();
        var newAllTags = "";
//        for (var i = 0; i < allTags.length; i++) {
        newAllTags += allTags + " ";
//        };
        newAllTags += tag + " ";
        $('#tagsEditPlaceholder').empty();
        $('#tagsEditPlaceholder').append(newAllTags);
    });
    $('#createTagsButton').click(function (event) {
        event.preventDefault();
        var tag = $('#TagsInput').val();
        $('#TagsInput').val('');
        tag = createTagButton(tag);
        var allTags = $('#tagsPlaceholder').html();
        var newAllTags = "";
//        for (var i = 0; i < allTags.length; i++) {
        newAllTags += allTags + " ";
//        };
        newAllTags += tag + " ";
        $('#tagsPlaceholder').empty();
        $('#tagsPlaceholder').append(newAllTags);
    });
    $('#addCategoryButton').click(function (event) {
        event.preventDefault();
        var category = $('#addCategoryInput').val();

        addCategoryToDB(category);
        clearCategoriesFields();
        loadCategories();
    });
    $('#addEditCategoryButton').click(function (event) {
        event.preventDefault();
        var category = $('#addEditCategoryInput').val();

        addCategoryToDB(category);
        clearCategoriesFields();
        loadCategories();
    });
    $('#previewSomething').click(function (event) {
        event.preventDefault();
        var title = $('#editPageTitle').val();
        var heading = $('#editHeading').val();
        var navName = $('#editNavBarName').val();
        var body = tinymce.get("editPageContent").getContent();
        var w = window.open('', 'PreviewPage', '');
        var html = '<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>PreviewPage</title><link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" /><link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"><style>@font-face{font-family: Sofia;src: url(http://fonts.googleapis.com/css?family=Sofia)}#homeBanner{font-family: Sofia;color: black;font-size: 50px;/*color: white;*/}</style></head><body><div class="row"> <br> </div><div class="row"><div class="col-xs-4"></div><div id="nameContainer" class="col-xs-4"><a href="#" id="homeBanner"><div class="row"><div class="name col-xs-12 text-center"><p>The Venue</p></div></div><!-- <div class="row"><div class="name col-xs-12 text-center"><p>Add something here</p></div></div> --></a></div> <!-- End of col-xs-4 and well --><div class="col-xs-4"></div></div> <!-- End row --><nav class="navbar navbar-inverse"><div class="container-fluid"><!-- The Logo --><div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><!-- href="${pageContext.request.contextPath}/" --><a href="#" class="navbar-brand">The Venue <span class="glyphicon glyphicon-home"></span></a></div> <!-- End navbar-header --><!-- Menu Items --></div></nav><!--<body>--><div class="col-xs-10 col-xs-offset-1"><div><h1 class="text-center" id="pageTitle">' + title + '</h1></div><div id="pageBody">' + body + '</div></div><script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script><script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script></body></html>';
        w.document.open();
        w.document.write(html);
        w.document.close();
    });
    $('#unsavedPreviewStaticPage').click(function (event) {
        event.preventDefault();
        var title = $('#addPageTitle').val();
        var heading = $('#addHeading').val();
        var navName = $('#addNavBarName').val();
        var body = tinymce.get("addPageContent").getContent();
        var w = window.open('', 'PreviewPage', '');
        var html = '<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>PreviewPage</title><link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Sofia" /><link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"><style>@font-face{font-family: Sofia;src: url(http://fonts.googleapis.com/css?family=Sofia)}#homeBanner{font-family: Sofia;color: black;font-size: 50px;/*color: white;*/}</style></head><body><div class="row"> <br> </div><div class="row"><div class="col-xs-4"></div><div id="nameContainer" class="col-xs-4"><a href="#" id="homeBanner"><div class="row"><div class="name col-xs-12 text-center"><p>The Venue</p></div></div><!-- <div class="row"><div class="name col-xs-12 text-center"><p>Add something here</p></div></div> --></a></div> <!-- End of col-xs-4 and well --><div class="col-xs-4"></div></div> <!-- End row --><nav class="navbar navbar-inverse"><div class="container-fluid"><!-- The Logo --><div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><!-- href="${pageContext.request.contextPath}/" --><a href="#" class="navbar-brand">The Venue <span class="glyphicon glyphicon-home"></span></a></div> <!-- End navbar-header --><!-- Menu Items --></div></nav><!--<body>--><div class="col-xs-10 col-xs-offset-1"><div><h1 class="text-center" id="pageTitle">' + title + '</h1></div><div id="pageBody">' + body + '</div></div><script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script><script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script></body></html>';
        w.document.open();
        w.document.write(html);
        w.document.close();
    });
    $('#cancelStaticPage').click(function (event) {
        $('#addPageTitle').val('');
        $('#addHeading').val('');
        $('#addParentChild').val('');
        tinyMCE.activeEditor.setContent('');
        $('#addLocation').val('');
        $('#addNavBarName').val('');
        loadPageList();
        hideAll();
        $('#pagesDiv').show();
    });
    $('#addStaticPage').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'staticPage',
            data: JSON.stringify({
                title: $('#addPageTitle').val(),
                heading: $('#addHeading').val(),
                parentPageFk: $('#addParentPagesList option:selected').val(),
                navName: $('#addNavBarName').val(),
                body: tinymce.get("addPageContent").getContent()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#addPageTitle').val('');
            $('#addHeading').val('');
            tinyMCE.activeEditor.setContent('');
            $('#addParentChild').val('');
            $('#addLocation').val('');
            $('#addNavBarName').val('');
            loadPageList();
            hideAll();
            $('#pagesDiv').show();
        });
    });
    $(document).on('click', '.viewDetails', function (event) {
        event.preventDefault();
        var data = JSON.parse($(this).attr('page-data'));
        previewStaticPage(data);
    });

    $('#editStaticPage').click(function (event) {
        $.ajax({
            type: 'PUT',
            url: 'staticPage/' + $('#edit-staticPageId').val(),
            data: JSON.stringify({
                title: $('#editPageTitle').val(),
                heading: $('#editHeading').val(),
                parentPageFk: $('#editParentPagesList option:selected').val(),
                navName: $('#editNavBarName').val(),
                body: tinymce.get("editPageContent").getContent()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            loadPageList();
            hideAll();
            $('#pagesDiv').show();
        });
    });

    $('#saveAsDraftBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {statusId: 3};
        var username = $('#username').text().split(/\s+/g).join("");
        if (username === 'admin') {
            var authorObject = {"authorId": 1};
        } else {
            var authorObject = {"authorId": 2};
        }
        var categoryList = [{"categoryId": $('#addCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startDatepicker').val() === "" ? null : new Date($('#startDatepicker').val()).toISOString());
        var endDate = ($('#endDatepicker').val() === "" ? null : new Date($('#endDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'POST',
            url: "post",
            data: JSON.stringify({
                title: $('#addBlogTitle').val(),
                author: authorObject,
                body: tinymce.get("addBlogBody").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#archiveBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {statusId: 2};
        var authorObject = {"authorId": 1};
        var categoryList = [{"categoryId": $('#addCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startDatepicker').val() === "" ? null : new Date($('#startDatepicker').val()).toISOString());
        var endDate = ($('#endDatepicker').val() === "" ? null : new Date($('#endDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'POST',
            url: "post",
            data: JSON.stringify({
                title: $('#addBlogTitle').val(),
                author: authorObject,
                body: tinymce.get("addBlogBody").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#unPublishedBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {statusId: 4};
        var username = $('#username').text().split(/\s+/g).join("");
        if (username === 'admin') {
            var authorObject = {"authorId": 1};
        } else {
            var authorObject = {"authorId": 2};
        }
        var categoryList = [{"categoryId": $('#addCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startDatepicker').val() === "" ? null : new Date($('#startDatepicker').val()).toISOString());
        var endDate = ($('#endDatepicker').val() === "" ? null : new Date($('#endDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'POST',
            url: "post",
            data: JSON.stringify({
                title: $('#addBlogTitle').val(),
                author: authorObject,
                body: tinymce.get("addBlogBody").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#publishBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {statusId: 1};
        var authorObject = {"authorId": 1};
        var categoryList = [{"categoryId": $('#addCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startDatepicker').val() === "" ? null : new Date($('#startDatepicker').val()).toISOString());
        var endDate = ($('#endDatepicker').val() === "" ? null : new Date($('#endDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'POST',
            url: "post",
            data: JSON.stringify({
                title: $('#addBlogTitle').val(),
                author: authorObject,
                body: tinymce.get("addBlogBody").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            hideAll();
            loadPostList();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#cancelAddBlog').click(function (event) {
        $('.validationErrors').empty();
        clearBlogFields();
        clearPostList();
        clearTags();
        loadPostList();
        hideAll();
        $('.validationErrors').hide();
        $('#blogsDiv').show();
    });

    $('#saveEditDraftBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {"statusId": 3};
        var username = $('#username').text().split(/\s+/g).join("");
        if (username === 'admin') {
            var authorObject = {"authorId": 1};
        } else {
            var authorObject = {"authorId": 2};
        }
        var categoryList = [{"categoryId": $('#editCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startEditDatepicker').val() === "" ? null : new Date($('#startEditDatepicker').val()).toISOString());
        var endDate = ($('#endEditDatepicker').val() === "" ? null : new Date($('#endEditDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'PUT',
            url: "post/" + $('#saveEditDraftBtn').val(),
            data: JSON.stringify({
                title: $('#editPostTitle').val(),
                author: authorObject,
                body: tinymce.get("editPostContent").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });

    $('#archiveEditBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {"statusId": 2};
        var authorObject = {"authorId": 1};
        var categoryList = [{"categoryId": $('#editCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startEditDatepicker').val() === "" ? null : new Date($('#startEditDatepicker').val()).toISOString());
        var endDate = ($('#endEditDatepicker').val() === "" ? null : new Date($('#endEditDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'PUT',
            url: "post/" + $('#archiveEditBtn').val(),
            data: JSON.stringify({
                title: $('#editPostTitle').val(),
                author: authorObject,
                body: tinymce.get("editPostContent").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#unPublishedEditBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {"statusId": 4};
        var authorObject = {"authorId": 2};
        var categoryList = [{"categoryId": $('#editCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startEditDatepicker').val() === "" ? null : new Date($('#startEditDatepicker').val()).toISOString());
        var endDate = ($('#endEditDatepicker').val() === "" ? null : new Date($('#endEditDatepicker').val()).toISOString());
        var ajaxSetup = {
            type: 'PUT',
            url: "post/" + $('#unPublishedEditBtn').val(),
            data: JSON.stringify({
                title: $('#editPostTitle').val(),
                author: authorObject,
                body: tinymce.get("editPostContent").getContent(),
                startDate: startDate,
                endDate: endDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#publishEditBtn').click(function (event) {
        event.preventDefault();
        var statusObject = {"statusId": 1};
        var authorObject = {"authorId": 1};
        var categoryList = [{"categoryId": $('#editCategoryList option:selected').val()}];
        var tagsList = getAllTags();
        var startDate = ($('#startEditDatepicker').val() === "" ? null : new Date($('#startEditDatepicker').val()).toISOString());
        var endDate = ($('#endEditDatepicker').val() === "" ? null : new Date($('#endEditDatepicker').val()).toISOString());
        var pubDate = new Date($('#cancelEditBlog').val()).toISOString();
        var ajaxSetup = {
            type: 'PUT',
            url: "post/" + $('#publishEditBtn').val(),
            data: JSON.stringify({
                title: $('#editPostTitle').val(),
                author: authorObject,
                body: tinymce.get("editPostContent").getContent(),
                startDate: startDate,
                endDate: endDate,
                publishDate: pubDate,
                categories: categoryList,
                tags: tagsList,
                status: statusObject
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        };
        $.ajax(ajaxSetup).success(function () {
            clearBlogFields();
            clearPostList();
            clearTags();
            loadPostList();
            hideAll();
            $('#blogsDiv').show();
        }).error(function (data, status) {
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('.validationErrors');
                $('.validationErrors').empty();
                errorDiv.append(validationError.message).append($('<br>'));
                errorDiv.show();
            });
        });
    });
    $('#cancelEditBlog').click(function (event) {
        $('.validationErrors').empty();
        clearBlogFields();
        clearPostList();
        clearTags();
        loadPostList();
        hideAll();
        $('.validationErrors').hide();
        $('#blogsDiv').show();
    });
});

function showEditPostForm(postId) {
    $.ajax({
        url: 'post/' + postId
    }).success(function (post, status) {
        var tagList = [];
        if (post.tags !== null) {

            for (var i = 0; i < post.tags.length; i++) {
                tagList.push(post.tags[i].tagName);
            }
            var catId = post.categories[0].categoryId;
            $('#saveEditDraftBtn').val(postId);
            $('#archiveEditBtn').val(postId);
            $('#unPublishedEditBtn').val(postId);
            $('#publishEditBtn').val(postId);
            $('#cancelEditBlog').val(post.publishDate);
            $('#editPostTitle').val(post.title);
            $('#editAuthorId').val(post.author.authorId);
            tinyMCE.get("editPostContent").setContent(post.body);
            $('#edit-postId').val(post.postId);
            clearCategoriesFields();
            clearTags();
            loadEditCategories(catId);
            hideAll();
            $('#editPostDiv').show();
            addAllTags(tagList);
        } else {
            var catId = post.categories[0].categoryId;
            $('#saveEditDraftBtn').val(postId);
            $('#archiveEditBtn').val(postId);
            $('#unPublishedEditBtn').val(postId);
            $('#publishEditBtn').val(postId);
            $('#cancelEditBlog').val(post.publishDate);
            $('#editPostTitle').val(post.title);
            $('#editAuthorId').val(post.author.authorId);
            tinyMCE.get("editPostContent").setContent(post.body);
            $('#edit-postId').val(post.postId);
            clearCategoriesFields();
            clearTags();
            loadEditCategories(catId);
            hideAll();
            $('#editPostDiv').show();
        }
    });
}

function showEditForm(pageId) {
    $.ajax({
        type: 'GET',
        url: 'staticPage/' + pageId
    }).success(function (page, status) {
        var parentId = page.parentPageFk;
        $('#editPageTitle').val(page.title);
        $('#editHeading').val(page.heading);
        tinyMCE.activeEditor.setContent(page.body);
        $('#editNavBarName').val(page.navName);
        $('#edit-staticPageId').val(page.pageId);
        hideAll();
        if (parentId !== null) {
            loadEditParentPages(parentId);
        }
        $('#editWebPageDiv').show();
    });
}

//function loadInNavTabs() {
//    clearNavTabs();
//    var navTabsContent = $('#NavTabsContent');
//    var navTab1 = "Blog Options";
//    var navTab2 = "Web Page Options";
//    var navId1 = "blogOptions";
//    var navId2 = "pageOptions";
//    var navigationTabs = [navTab1, navTab2];
//    var navigationIDs = [navId1, navId2];
//    var numberOfNavTabs = navigationTabs.length;
//    for (i = 0; i < numberOfNavTabs; i++) {
//        navTabsContent.append($('<li>').append($('<a>')
//                .attr({
//                    'id': navigationIDs[i],
//                    'href': "#"
//                })
//                .text(navigationTabs[i])));
//    }
//}

function loadPostList() {
    clearPostList();
    var username = $('#username').text().split(/\s+/g).join("");
    var activeRows = $('#activePostRows');
    var inactiveRows = $('#inactivePostRows');

    if (username === 'admin') {
        $.ajax({
            url: 'posts'
        }).success(function (postList, status) {
            for (var i = 0; i < postList.length; i++) {
                if (postList[i].status.statusId === 1) {
                    var currentPost = postList[i];
                    activeRows.append($('<tr>')
                            .attr({
                                id: 'post-' + currentPost.postId
                            })
                            .append($('<td>')
                                    .attr({
                                        class: 'index'
                                    })
                                    .text(currentPost.postId))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                href: '#',
                                                onclick: "showEditPostForm(" + currentPost.postId + ")"
                                            })
                                            .append($('<kbd>')
                                                    .text(currentPost.title))))
                            .append($('<td>')
                                    .text(currentPost.author.authorName))
                            .append($('<td>')
                                    .text(currentPost.publishDate))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                onclick: 'archivePost(' + currentPost.postId + ')'
                                            })
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-success gradient'
                                                    })
                                                    .text('Archive'))))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                onclick: 'deletePost(' + currentPost.postId + ')'
                                            })
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-danger gradient'
                                                    })
                                                    .text('Delete')))));
                } else {
                    var currentPost = postList[i];
                    inactiveRows.append($('<tr>')
                            .attr({
                                id: 'post-' + currentPost.postId
                            })
                            .append($('<td>')
                                    .attr({
                                        class: 'index'
                                    })
                                    .text(currentPost.postId))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                href: '#',
                                                onclick: "showEditPostForm(" + currentPost.postId + ")"
                                            })
                                            .append($('<kbd>')
                                                    .text(currentPost.title))))
                            .append($('<td>')
                                    .text(currentPost.author.authorName))
                            .append($('<td>')
                                    .text(currentPost.publishDate))
                            .append($('<td>')
                                    .text(currentPost.status.status))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                onclick: 'publishPost(' + currentPost.postId + ')'
                                            })
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-success gradient'
                                                    })
                                                    .text('Activate'))))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                onclick: 'deletePost(' + currentPost.postId + ')'
                                            })
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-danger gradient'
                                                    })
                                                    .text('Delete')))));
                }
            }

        });
    } else {
        $.ajax({
            url: 'getEmployeePosts'
        }).success(function (postList, status) {
            for (var i = 0; i < postList.length; i++) {
                if (postList[i].status.statusId === 1) {
                    var currentPost = postList[i];
                    activeRows.append($('<tr>')
                            .attr({
                                id: 'post-' + currentPost.postId
                            })
                            .append($('<td>')
                                    .attr({
                                        class: 'index'
                                    })
                                    .text(currentPost.postId))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                href: '#',
                                                onclick: "showEditPostForm(" + currentPost.postId + ")"
                                            })
                                            .append($('<kbd>')
                                                    .text(currentPost.title))))
                            .append($('<td>')
                                    .text(currentPost.author.authorName))
                            .append($('<td>')
                                    .text(currentPost.publishDate))
                            .append($('<td>')
                                    .append($('<a>')
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-success gradient'
                                                    })
                                                    .text('disabled'))))
                            .append($('<td>')
                                    .append($('<a>')
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-danger gradient'
                                                    })
                                                    .text('disabled')))));
                } else {
                    var currentPost = postList[i];
                    inactiveRows.append($('<tr>')
                            .attr({
                                id: 'post-' + currentPost.postId
                            })
                            .append($('<td>')
                                    .attr({
                                        class: 'index'
                                    })
                                    .text(currentPost.postId))
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({
                                                href: '#',
                                                onclick: "showEditPostForm(" + currentPost.postId + ")"
                                            })
                                            .append($('<kbd>')
                                                    .text(currentPost.title))))
                            .append($('<td>')
                                    .text(currentPost.author.authorName))
                            .append($('<td>')
                                    .text(currentPost.publishDate))
                            .append($('<td>')
                                    .text(currentPost.status.status))
                            .append($('<td>')
                                    .append($('<a>')
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-success gradient'
                                                    })
                                                    .text('Disabled'))))
                            .append($('<td>')
                                    .append($('<a>')
                                            .append($('<span>')
                                                    .attr({
                                                        class: 'btn btn-danger gradient'
                                                    })
                                                    .text('Disabled')))));
                }
            }
        });
    }
    ;
}

function archivePost(id) {
    $.ajax({
        type: 'PUT',
        url: 'post/archive',
        data: JSON.stringify(id),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        hideAll();
        loadPostList();
        $('#blogsDiv').show();
    });
}

function publishPost(id) {
    $.ajax({
        type: 'PUT',
        url: 'post/publish/',
        data: JSON.stringify(id),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        hideAll();
        loadPostList();
        $('#blogsDiv').show();
    });
}

function deletePost(id) {
    $.ajax({
        type: 'DELETE',
        url: 'post/' + id
    }).success(function (data, status) {
        hideAll();
        loadPostList();
        $('#blogsDiv').show();
    });
}

function loadPageList() {
    clearPageList();
    $.ajax({
        type: 'GET',
        url: 'staticPagesAndNavBarLocations'
    }).success(function (data, status) {
        var pagesList = data;
        for (var i = 0; i < pagesList.navBarLocation.length; i++) {
            var currentPage = pagesList.navBarLocation[i];
            if (pagesList.navBarLocation[i].page === null) {
                $('#activePageRows').append($('<tr>').attr({
                    id: 'page-' + currentPage.pageFk,
                    'data-navBarLocation': currentPage.navBarLocationId,
                    'data-pageFk': currentPage.pageFk

                })
                        .append($('<td>')
                                .attr({
                                    style: 'display: none;',
                                    class: 'pageFk',
                                    value: '0'
                                })).append($('<td>')
                        .attr({
                            class: 'index'
                        }).text(pagesList.navBarLocation[i].navBarLocationId)).append($('<td>').text('Blogs'))
                        .append($('<td>').text('Blogs'))
                        .append($('<td>').append('')).append($('<td>').append('')).append($('<td>').append(''))
                        );
            } else {

                $('#activePageRows').append($('<tr>').attr({
                    id: 'page-' + currentPage.pageFk,
                    'data-navBarLocation': currentPage.navBarLocationId,
                    'data-pageFk': currentPage.pageFk

                })
                        .append($('<td>')
                                .attr({
                                    style: 'display: none;',
                                    class: 'pageFk',
                                    value: pagesList.navBarLocation[i].page.pageId
                                })).append($('<td>')
                        .attr({
                            class: 'index'
                        }).text(pagesList.navBarLocation[i].navBarLocationId)).append($('<td>').append($('<a>').attr({
                    href: '#',
                    onclick: "showEditForm(" + currentPage.pageFk + ")"
                }).text(pagesList.navBarLocation[i].page.title)))
                        .append($('<td>').append($('<td>').attr({
                            onclick: "#"
                        }).text(pagesList.navBarLocation[i].page.navName)))
                        .append($('<td>').append($('<a>').attr({
                            onclick: 'unassignNavBarLocation(' + pagesList.navBarLocation[i].navBarLocationId + ')'
                        }).append($('<span>')
                                .attr({
                                    class: 'btn btn-success gradient'
                                }).text('Unassign')))).append($('<td>').append($('<a>').attr({
                    onclick: 'previewStaticPage(' + pagesList.navBarLocation[i].page.pageId + ')'
                }).append($('<span>')
                        .attr({
                            class: 'btn btn-info gradient'
                        }).text('Preview')))).append($('<td>').append($('<a>').attr({
                    onclick: 'deleteStaticPage(' + pagesList.navBarLocation[i].page.pageId + ')',
                    value: pagesList.navBarLocation[i].page.pageId
                }).append($('<span>')
                        .attr({
                            class: 'btn btn-danger gradient'
                        }).text('Delete'))))
                        );
            }
        }

        for (var i = 0; i < pagesList.page.length; i++) {
            var currentPage = pagesList.page[i];
            if (currentPage.pageId === -2) {

            } else {
                $('#inactivePageRows').append($('<tr>').attr({
                    id: 'page-' + currentPage.pageId,
                    'data-pageFk': currentPage.pageId
                })
                        .append($('<td>')
                                .attr({
                                    style: 'display: none;',
                                    class: 'pageId',
                                    value: pagesList.page[i].pageId
                                })).append($('<td>')
                        .attr({
                            class: 'index'
                        }).text(pagesList.page[i].navBarLocationId)).append($('<td>').append($('<a>').attr({
                    href: '#',
                    onclick: "showEditForm(" + currentPage.pageId + ")"
                }).text(pagesList.page[i].title)))
                        .append($('<td>').text(pagesList.page[i].navName))
                        .append($('<td>').append($('<a>').attr({
                            onclick: 'assignNavBarLocation(' + pagesList.page[i].pageId + ')'
                        }).append($('<span>')
                                .attr({
                                    class: 'btn btn-success gradient'
                                }).text('Assign')))).append($('<td>').append($('<a>').attr({
                    onclick: 'previewStaticPage(' + pagesList.page[i].pageId + ')'
                }).append($('<span>')
                        .attr({
                            class: 'btn btn-info gradient'
                        }).text('Preview')))).append($('<td>').append($('<a>').attr({
                    onclick: 'deleteStaticPage(' + pagesList.page[i].pageId + ')',
                    value: pagesList.page[i].pageId
                }).append($('<span>')
                        .attr({
                            class: 'btn btn-danger gradient'
                        }).text('Delete'))))
                        );
            }
        }
    });
}

function getAllTags() {

    var tagList = [];
    $.each($('#tagsEditPlaceholder span.tag'), function (index, tag) {
        if ($('#tagsEditPlaceholder span.tag').text() !== '') {
            var tag = $(tag).text();
            var tagObject = {tagName: tag};
            tagList.push(tagObject);
        }
    });
    $.each($('#tagsPlaceholder span.tag'), function (index, tag) {
        if ($('#tagsPlaceholder span.tag').text() !== '') {
            var tag = $(tag).text();
            var tagObject = {tagName: tag};
            tagList.push(tagObject);
        }
    });

    return tagList;
}

function createTagButton(tag) {

    var button = '<span class="tag label label-default" id="' + tag + '">' + tag +
            '<span href="#" class="glyphicon glyphicon-remove-circle removeTag"></span></span>';
    return button;
}

function addAllTags(tagList) {
    var currentTags = $('#tagsEditPlaceholder');
    for (var i = 0; i < tagList.length; i++) {
        button = '<span class="tag label label-default" id="' + tagList[i] + '">' + tagList[i] +
                '<span href="#" class="glyphicon glyphicon-remove-circle removeTag"></span></span>';
        currentTags.append(button);
    }
}

function unassignNavBarLocation(id) {
    $.ajax({
        type: 'PUT',
        url: 'UnassignNavBarLocation',
        data: JSON.stringify(id),
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

function assignNavBarLocation(id) {
    $.ajax({
        type: 'PUT',
        url: 'AssignNavBarLocation',
        data: JSON.stringify(id),
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

function remove(tag) {
    $('#' + tag).remove();
}

function clearTags() {
    $('#tagsEditPlaceholder').children().remove();
    $('#tagsPlaceholder').children().remove();
}

function clearCategoriesFields() {
    $('#addCategoryInput').val('');
    $('#addEditCategoryInput').val('');
    $('#addCategoryList').children().remove();
    $('#editCategoryList').children().remove();
}

function clearParentPagesFields() {
    $('#addParentPagesList').children().remove();
    $('#editParentPagesList').children().remove();
}

function clearPostList() {
    $('#activePostRows').empty();
    $('#inactivePostRows').empty();
}

function clearPageList() {
    $('#inactivePageRows').empty();
    $('#activePageRows').empty();
}

function clearBlogFields() {
    $('#addBlogTitle').val('');
    $('#addAuthor').val('');
    $('#startDatepicker').val('');
    $('#endDatepicker').val('');
    clearCategoriesFields();
    $('#addTag').val('');
    tinyMCE.activeEditor.setContent('');
    $('#tagsDiv').empty();
    $('#editPostTitle').val('');
    $('#editAuthor').val('');
    $('#startEditDatepicker').val('');
    $('#endEditDatepicker').val('');
    $('#addEditTag').val('');
    $('#editTagsDiv').empty();
    $('.validationErrors').children().remove();
}

function deleteStaticPage(id) {
    $.ajax({
        type: 'DELETE',
        url: 'staticPage/' + id
    }).success(function (data, status) {
        hideAll();
        loadPageList();
        $('#pagesDiv').show();
    });
}

function addCategoryToDB(category) {
    $.ajax({
        type: 'POST',
        url: 'category',
        data: JSON.stringify({
            categoryName: category
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        clearCategoriesFields();
        loadCategories();
    });
}

function loadParentPages() {
    $.ajax({
        type: 'GET',
        url: 'staticPages',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (page, status) {
        for (var i = 0; i < page.length; i++) {
            if (page[i].pageId === -2) {
                $('#addParentPagesList').append($('<option>').attr({
                    id: 'post' + page[i].pageId,
                    value: null
                }).text(page[i].title));
            } else {
                $('#addParentPagesList').append($('<option>').attr({
                    id: 'page' + page[i].pageId,
                    value: page[i].pageId
                }).text(page[i].title));
            }
        }
        ;
    });
}

function loadCategories() {
    $.ajax({
        type: 'GET',
        url: 'categories',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (category, status) {
        clearCategoriesFields();
        for (var i = 0; i < category.length; i++) {
            $('#addCategoryList').append($('<option>').attr({
                id: 'category' + category[i].categoryId,
                value: category[i].categoryId
            }).text(category[i].categoryName));
        }
        ;
    });
}

function loadEditCategories(catId) {
    $.ajax({
        type: 'GET',
        url: 'categories',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (category, status) {
        clearCategoriesFields();
        for (var i = 0; i < category.length; i++) {
            if (catId === category[i].categoryId) {
                $('#editCategoryList').append($('<option>').attr({
                    id: 'editCategory' + category[i].categoryId,
                    value: category[i].categoryId,
                    'selected': 'selected'
                }).text(category[i].categoryName));
            } else {
                $('#editCategoryList').append($('<option>').attr({
                    id: 'editCategory' + category[i].categoryId,
                    value: category[i].categoryId
                }).text(category[i].categoryName));
            }
        }
        ;
    });
}

function loadEditParentPages(parentId) {
    $.ajax({
        type: 'GET',
        url: 'staticPages',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (page, status) {
        clearParentPagesFields();
        for (var i = 0; i < page.length; i++) {
            if (parentId === page[i].pageId) {
                $('#editParentPagesList').append($('<option>').attr({
                    id: 'editParentPage' + page[i].pageId,
                    value: page[i].pageId,
                    'selected': 'selected'
                }).text(page[i].title));
            } else if (page[i].pageId === -2) {
                $('#editParentPagesList').append($('<option>').attr({
                    id: 'post' + page[i].pageId,
                    value: null
                }).text(page[i].title));
            } else {
                $('#editParentPagesList').append($('<option>').attr({
                    id: 'editParentPage' + page[i].pageId,
                    value: page[i].pageId
                }).text(page[i].title));
            }
        }
        ;
    });
}
;

function previewStaticPage(pageId) {
    window.open('previewSaved/' + pageId);
}

function saveStaticPageOrder() {

    event.preventDefault();
    var tableRows = $('#activePageRows').find('> tr');
    var r;
    var nvblList = [];
    $.each($("#activePageRows tr"), function (index, item) {
        var navBarLocation = $(item).attr("data-navBarLocation");
        var pageFkNum = $(item).attr("data-pagefk");
        var pageNVBL = {pageFk: pageFkNum, navBarLocationId: navBarLocation};
        nvblList.push(pageNVBL);
    });
    $.ajax({
        type: 'PUT',
        url: 'NavBarLocations',
//            url: 'NavBar',
        data: JSON.stringify(nvblList),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#savePageOrder').hide();
        clearPageList();
        loadPageList();
    });
}

function clearNavTabs() {
    $('#NavTabsContent').empty();
}

function hideAll() {
    $('#pagesDiv').hide();
    $('#addBlogDiv').hide();
    $('#blogsDiv').hide();
    $('#addWebPageDiv').hide();
    $('#editWebPageDiv').hide();
    $('#editPostDiv').hide();
    $('.validationErrors').hide();
}

function clearAllFields() {
    $('#addPageTitle').val('');
    $('#addHeading').val('');
    tinyMCE.activeEditor.setContent('');
    $('#addParentChildList').val('');
    $('#addNavBarName').val('');
}

var fixHelperModified = function (e, tr) {
    var $originals = tr.children();
    var $helper = tr.clone();
    $helper.children().each(function (index) {
        $(this).width($originals.eq(index).width());
    });
    return $helper;
};
updateIndex = function (e, ui) {
    $('td.index', ui.item.parent()).each(function (i) {
        $(this).html(i + 1);
        $('#savePageOrder').show();
    });
};
$("#sort tbody").sortable({
    helper: fixHelperModified,
    stop: updateIndex
}
).disableSelection();