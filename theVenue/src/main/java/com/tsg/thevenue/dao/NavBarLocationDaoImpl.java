/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.NavBarLocation;
import com.tsg.thevenue.dto.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class NavBarLocationDaoImpl implements NavBarLocationDao {

    private static final String SQL_SELECT_NVBL
            = "SELECT * FROM NavBarLocation WHERE NavBarLocationId = ?";
    private static final String SQL_SELECT_ALL_NVBLS
            = "SELECT * FROM NavBarLocation";
    private static final String SQL_SELECT_UNNASSIGNED_PAGEIDS
            = "SELECT PageId FROM Page LEFT OUTER JOIN NavBarLocation ON Page.PageId = NavBarLocation.PageFk WHERE NavBarLocation.PageFk IS NULL";
    private static final String SQL_SELECT_FILLED_NVBLS
            = "SELECT * FROM NavBarLocation WHERE PageFk IS NOT NULL ORDER BY NavBarLocationId ASC";
    private static final String SQL_SELECT_HOME_NVBL
            = "SELECT * FROM NavBarLocation WHERE NavBarLocationId = 1";
    private static final String SQL_UPDATE_SET_NVBL
            = "UPDATE NavBarLocation SET PageFk = null WHERE NavBarLocationId = ?";
    private static final String SQL_UPDATE_SLOT_NVBL
            = "UPDATE NavBarLocation SET PageFk = ? WHERE NavBarLocationId = ?";
    private static final String SQL_INSERT_SWAP_NVBL
            = "UPDATE NavBarLocation SET NavBarLocationId = ?, PageFk = ?";
    private static final String SQL_UPDATE_CLEAR_SLOTS
            = "UPDATE NavBarLocation SET PageFk = null";
    private static final String SQL_UPDATE_SLOT_PAGEFK
            = "UPDATE NavBarLocation SET PageFk = ? WHERE NavBarLocationId = ?";
    private static final String SQL_ADD_NAVBARLOC_ONLY_FOR_TEST
            ="INSERT INTO `NavBarLocation`(`NavBarLocationId`, `PageFk`) VALUES (?,?)";
    private PageDao dao;
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void setDao(PageDao dao) {
        this.dao = dao;
    }

    @Override
    public List<NavBarLocation> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_NVBLS, new NvblMapper());
    }

    @Override
    public List<NavBarLocation> getFilledSlots() {
        List<NavBarLocation> nvblList = jdbcTemplate.query(SQL_SELECT_FILLED_NVBLS, new NvblMapper());
        return nvblList;
    }

    @Override
    public NavBarLocation getHomeSlot() {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HOME_NVBL, new NvblMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public NavBarLocation setSlot(NavBarLocation nvbl) {
        jdbcTemplate.update(SQL_UPDATE_SLOT_PAGEFK,
                nvbl.getPageFk(),
                nvbl.getNavBarLocationId());
                
        return nvbl;
    }
    
    @Override
    public void setAllSlots(List<NavBarLocation> nvblList) {
//        clearAllSlots();
        int setSlots = 0;
        for (int i = 1; i < 21; i++) {
//            if (i < nvblList.size()){
//                setSlot(nvblList.get(i));
//            } else {
//                clearSlot(i);
//            }
            clearSlot(i);
            if (setSlots < nvblList.size()){
                setSlotToPage(nvblList.get(i-1).getPageFk(), i);
                setSlots++;
            }
//            if (setSlots < nvblList.size()){
//                for (NavBarLocation nvbl : nvblList) {
//                    if (nvbl.getNavBarLocationId() == i){
//                        setSlot(nvbl);
//                        setSlots++;
//                    }
//                    if (nvbl.getPageFk().equals(0)){
//                        clearSlot(nvbl.getNavBarLocationId());
//                        setSlot(nvbl);
//                    }
//                }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void swap(NavBarLocation nvbl1, NavBarLocation nvbl2) {
        jdbcTemplate.update(SQL_INSERT_SWAP_NVBL,
                nvbl1.getNavBarLocationId(),
                nvbl1.getPageFk());
        jdbcTemplate.update(SQL_INSERT_SWAP_NVBL,
                nvbl2.getNavBarLocationId(),
                nvbl2.getPageFk());
    }

    @Override
    public void insertSlot(NavBarLocation nvbl) {
        NavBarLocation nvblMove;

        nvblMove = jdbcTemplate.queryForObject(SQL_SELECT_NVBL, new NvblMapper(), nvbl.getNavBarLocationId());
        jdbcTemplate.update(SQL_UPDATE_SET_NVBL,
                nvbl.getNavBarLocationId(),
                nvbl.getPageFk());
        while (nvblMove.getNavBarLocationId() < 20 || nvblMove.getPageFk() == null) {
            nvbl = jdbcTemplate.queryForObject(SQL_SELECT_NVBL, new NvblMapper(), nvbl.getNavBarLocationId() + 1);
            jdbcTemplate.update(SQL_UPDATE_SET_NVBL,
                    nvblMove.getNavBarLocationId(),
                    nvblMove.getNavBarLocationId() + 1);
            nvblMove = jdbcTemplate.queryForObject(SQL_SELECT_NVBL, new NvblMapper(), nvbl.getNavBarLocationId());
        }
    }

    @Override
    public void clearSlot(int navBarLocationId) {
        jdbcTemplate.update(SQL_UPDATE_SET_NVBL,
                navBarLocationId);
    }
    
    @Override
    public void clearAllSlots(){
        jdbcTemplate.update(SQL_UPDATE_CLEAR_SLOTS);
    }
    
    @Override
    public Page getPageByNvbl(NavBarLocation nvbl) {
        Page page = ( nvbl.getPageFk() == null ? null : dao.getPageById(nvbl.getPageFk()) );
        return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Page> getUnassignedPages(){
        List<Page> pList = new ArrayList<Page>();
        List<Integer> piList = jdbcTemplate.queryForList(SQL_SELECT_UNNASSIGNED_PAGEIDS, Integer.class);
        for (Integer integer : piList) {
            Page page = dao.getPageById(integer.intValue());
            pList.add(page);
        }
        return pList;
    }

    @Override
    public void setSlotToPage(Integer pageFk, int i) {
        jdbcTemplate.update(SQL_UPDATE_SLOT_PAGEFK, pageFk, i);
    }
    
    @Override
    public void setPageToNVBLId(Integer pageFk) {
        List<NavBarLocation> nvblList = jdbcTemplate.query(SQL_SELECT_FILLED_NVBLS, new NvblMapper());
        int lastNVBL = 0;
        
        
        
        for (int i = 0; i<nvblList.size(); i++){
            if (nvblList.get(i).getNavBarLocationId() > lastNVBL) {
                lastNVBL = nvblList.get(i).getNavBarLocationId();
            }
        }
        lastNVBL++;
        setSlotToPage(pageFk, lastNVBL);        
    }
    
    @Override
    public NavBarLocation addNavBarLocOnlyForTest(NavBarLocation navBarLoc){
        return jdbcTemplate.queryForObject(SQL_ADD_NAVBARLOC_ONLY_FOR_TEST, new NvblMapper(), navBarLoc.getNavBarLocationId(), navBarLoc.getPageFk());
    }
    
    

    private final class NvblMapper implements RowMapper<NavBarLocation> {

        @Override
        public NavBarLocation mapRow(ResultSet rs, int i) throws SQLException {
            NavBarLocation nvbl = new NavBarLocation();
            nvbl.setPageFk(rs.getObject("pageFK",Integer.class));
            nvbl.setNavBarLocationId(rs.getInt("NavBarLocationId"));
            
            if (nvbl.getPageFk() != null) {
                nvbl.setPage(getPageByNvbl(nvbl));
            }
            return nvbl;
        }
    }
}
