/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PageDaoImpl implements PageDao {

    private static final String SQL_SELECT_PAGE_BY_ID
            = "SELECT * FROM Page where PageId = ?";

    private static final String SQL_SELECT_PAGE_BY_TITLE
            = "SELECT * FROM Page WHERE Title = ?";

    private static final String SQL_SELECT_PAGE_BY_URL_TITLE
            = "SELECT * FROM Page WHERE UrlTitle = ?";

    private static final String SQL_INSERT_PAGE
            = "INSERT INTO Page (Title, NavName, Heading, Body, UrlTitle, ParentPageFk) values (?,?,?,?,?,?)";

    private static final String SQL_DELETE_PAGE
            = "DELETE FROM Page WHERE Page.PageId = ?";
    
    private static final String SQL_DELETE_PAGE_AS_PAGE_PARENTPAGEFK
            = "UPDATE Page SET ParentPageFk = null WHERE ParentPageFk = ?";
    
    private static final String SQL_DELETE_PAGE_AS_NVBL_PAGEFK
            = "UPDATE NavBarLocation SET PageFk = null WHERE PageFk = ?";


    private static final String SQL_UPDATE_PAGE
            = "UPDATE Page SET Title = ?, NavName = ?, Heading = ?, Body = ?, ParentPageFk = ? WHERE PageId = ?";

    private static final String SQL_SELECT_ALL_PAGES
            = "SELECT * FROM Page";

    private static final String SQL_SELECT_CHILD_PAGES_BY_PARENT_ID
            = "SELECT * FROM Page WHERE ParentPageFk = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        String urlTitle = " abc daf 5";
        String replacedTitle = urlTitle.replaceAll("\\s+", "+");
        System.out.println("and the string is " + replacedTitle);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Page createPage(Page page) {
        //this is setting up the urlTitle even in the case there is duplicate title
        List<Page> allPages = getAllPages();
        List<Page> PageWithSameTitle = allPages;
//        List<Page> PageWithSameTitle = allPages.stream().filter(s -> s.getTitle().equals(page.getTitle()))
//                .collect(Collectors.toList());
        //if there is no duplicate title
        if (PageWithSameTitle.isEmpty()) {
            String title = page.getTitle();
            //replace all the white space with +
            page.setUrlTitle(title.replaceAll("\\s+", "+"));
            //if there is only one title duplicate title
            //we assume this title has no number attached to it
        } else if (PageWithSameTitle.size() == 1) {
            String title = page.getTitle();
            page.setUrlTitle((title.replaceAll("\\s+", "+") + "+1"));
        } else {
            int highestValueAfterPlus = 0;
            for (Page p : PageWithSameTitle) {

                if (p.getTitle().length() == p.getUrlTitle().length()) {
                    //do nothing
                } else {
                    String urlTitle = p.getUrlTitle();
                    int CurrentValueAfterLastPlus = Integer.parseInt(urlTitle.substring(urlTitle.lastIndexOf("+") + 1));
                    if (CurrentValueAfterLastPlus > highestValueAfterPlus) {
                        highestValueAfterPlus = CurrentValueAfterLastPlus;
                    }
                }
            }
            String title = page.getTitle();
            page.setUrlTitle((title.replaceAll("\\s+", "+") + "+" + (highestValueAfterPlus+1)));

        }
        
        jdbcTemplate.update(SQL_INSERT_PAGE,
                page.getTitle(),
                page.getNavName(),
                page.getHeading(),
                page.getBody(),
                page.getUrlTitle(),
                page.getParentPageFk());
        page.setPageId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return page;
    }
    
    @Override
    public Page previewPage(Page page) {
        //this is setting up the urlTitle even in the case there is duplicate title
        List<Page> allPages = getAllPages();
        List<Page> PageWithSameTitle = allPages;
//        List<Page> PageWithSameTitle = allPages.stream().filter(s -> s.getTitle().equals(page.getTitle()))
//                .collect(Collectors.toList());
        //if there is no duplicate title
        if (PageWithSameTitle.isEmpty()) {
            String title = page.getTitle();
            //replace all the white space with +
            page.setUrlTitle(title.replaceAll("\\s+", "+"));
            //if there is only one title duplicate title
            //we assume this title has no number attached to it
        } else if (PageWithSameTitle.size() == 1) {
            String title = page.getTitle();
            page.setUrlTitle((title.replaceAll("\\s+", "+") + "+1"));
        } else {
            int highestValueAfterPlus = 0;
            for (Page p : PageWithSameTitle) {

                if (p.getTitle().length() == p.getUrlTitle().length()) {
                    //do nothing
                } else {
                    String urlTitle = p.getUrlTitle();
                    int CurrentValueAfterLastPlus = Integer.parseInt(urlTitle.substring(urlTitle.lastIndexOf("+") + 1));
                    if (CurrentValueAfterLastPlus > highestValueAfterPlus) {
                        highestValueAfterPlus = CurrentValueAfterLastPlus;
                    }
                }
            }
            String title = page.getTitle();
            page.setUrlTitle((title.replaceAll("\\s+", "+") + "+" + (highestValueAfterPlus+1)));

        }
        page.setPageId(-1);
        return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePage(int id) {
        jdbcTemplate.update(SQL_DELETE_PAGE_AS_PAGE_PARENTPAGEFK, id);
        jdbcTemplate.update(SQL_DELETE_PAGE_AS_NVBL_PAGEFK, id);
        jdbcTemplate.update(SQL_DELETE_PAGE, id);
    }

    @Override
    public void updatePage(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                page.getTitle(),
                page.getNavName(),
                page.getHeading(),
                page.getBody(),
                page.getParentPageFk(),
                page.getPageId());

    }

    @Override
    public List<Page> getAllPages() {
        List<Page> pList = jdbcTemplate.query(SQL_SELECT_ALL_PAGES, new PageMapper());
        return pList;
    }

    @Override
    public Page getPageById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PAGE_BY_ID, new PageMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Page getPageByTitle(String title) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PAGE_BY_TITLE, new PageMapper(), title);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Page getPageByURLTitle(String urlTitle) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PAGE_BY_URL_TITLE, new PageMapper(), urlTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Page> getChildPages(int pageId) {
        List<Page> pList = jdbcTemplate.query(SQL_SELECT_CHILD_PAGES_BY_PARENT_ID, new PageMapper(), pageId);
        return pList;
    }

    private static final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int i) throws SQLException {
            Page p = new Page();
            p.setPageId(rs.getInt("PageId"));
            p.setTitle(rs.getString("Title"));
            p.setNavName(rs.getString("NavName"));
            p.setHeading(rs.getString("Heading"));
            p.setBody(rs.getString("Body"));
            p.setUrlTitle(rs.getString("URLTitle"));
            p.setParentPageFk(rs.getInt("ParentPageFk"));
            if (p.getParentPageFk()==0) {
                p.setParentPageFk(null);
            }
            return p;
        }
    }

}
