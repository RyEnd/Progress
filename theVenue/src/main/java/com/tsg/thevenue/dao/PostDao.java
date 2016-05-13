/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Post;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PostDao {
    public Post createPost(Post post);
    public void deletePost(int id);
    public Post updatePost(Post post);
    public List<Post> getAllPosts();
//    public void createAuthor(String author);
    public Post getPostById(int id);
    public Post getPostByTitle(String postTitle);
    public Post getPostByUrlTitle(String urlTitle);
    //public List<Post> getPublishedPost();

    public void publishPost(int id);

    public void archivePost(int id);
    
    public List<Post> getPostsByTagId(int tagId);
     public List<Post> getPostByTagName(String tagName);
     public List<Post> getPostByCategoryId(int categoryId);
     public List<Post> getPostByCategoryName(String CategoryName);

    public List<Post> getAllActivePosts();

    public List<Post> getAllActivePostsDateAsc();

    public List<Post> getEmployeePosts();
}
