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
package com.mazda.technician.patsiltcrs.domain;

import com.mazdausa.common.model.bio.Bio;

/**
 * @author mvu
 * @version
 * <b>Description:</b>
 * 
 */
public class EmdcsAppDomainAbstract implements Bio
{

    /**
     * <b>Description:</b>
     * 
     */
    public EmdcsAppDomainAbstract()
    {
        super();

    }

    /**
     * Override to return unique object id
     * For sorting and hashing
     * @see super class
     */
    public Object getOID()
    {
        return new Integer(this.hashCode());
    }

    /**
     * This method overrides super class
     * @see super class
     */
    public String getSearchPattern()
    {
        return "";
    }

}
