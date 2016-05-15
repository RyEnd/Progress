/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.NavBarLocation;
import com.tsg.thevenue.dto.Page;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface NavBarLocationDao {

    public List<NavBarLocation> getAll();

    public List<NavBarLocation> getFilledSlots();

    public NavBarLocation getHomeSlot(); //get what's in the home slot

    public NavBarLocation setSlot(NavBarLocation nvbl);

    public void swap(NavBarLocation nvbl1, NavBarLocation nvbl2);

    public void insertSlot(NavBarLocation nvbl); //move objects down list

    public void clearSlot(int navBarLocationId);

    public void setAllSlots(List<NavBarLocation> nvblList);

    public Page getPageByNvbl(NavBarLocation nvbl);

    public List<Page> getUnassignedPages();

    public void clearAllSlots();

    public void setSlotToPage(Integer pageFk, int i);

    public void setPageToNVBLId(Integer pageFk);

    public NavBarLocation addNavBarLocOnlyForTest(NavBarLocation navBarLoc);
}
