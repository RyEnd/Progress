/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Tag;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TagDao {

    public Tag addTag(Tag tag);

    public void deleteTag(int tagId);

    public void updateTag(Tag tag);

    public Tag getTagById(int id);
    
    public List<Tag> getTagsByPostId(int postId);

    public List<Tag> getAllTags();
    
    public Tag getTagByTagName(String tagName);

    public String[] getAllTagNamesForTagCloud();
}
