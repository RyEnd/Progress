/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Status;

/**
 *
 * @author apprentice
 */
public interface StatusDao {
    public Status getStatusById(int StatusId);
}
