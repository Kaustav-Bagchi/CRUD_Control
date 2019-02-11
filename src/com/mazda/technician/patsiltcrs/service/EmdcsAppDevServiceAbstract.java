/*
 * Created on Dec 9, 2009
 *
 * <b>Change History:</b>
 * <pre>
 * Copyright 2004 by Mazda North America Operations, Inc.,
 * 7755 Irvine Center Drive Irvine, CA  92623, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Mazda North America Operations, Inc. ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Mazda North America Operations.
 * </pre>
 *  
 * 
 */
package com.mazda.technician.patsiltcrs.service;

import com.mazdausa.common.exceptions.EmazdaApplicationException;

/**
 * @author mvu
 * @version
 * <b>Description:</b>
 * 
 */
public abstract class EmdcsAppDevServiceAbstract implements IPatsILTService
{

    /**
     * <b>Description:</b>
     * 
     */
    public EmdcsAppDevServiceAbstract()
    {
        super();

    }
    
    
    /**
     * Sub Class provides implementation
     * @see super class
     */
    public abstract void execute() throws EmazdaApplicationException;
}
