/*
 * Created on Mar 10, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.action;

import javax.servlet.http.HttpServletRequest;

import com.mazda.technician.patsiltcrs.service.IApplicationConstants;
import com.mazdausa.common.application.actions.UserContext;
import com.mazdausa.common.user.model.User;

/**
 * @author mvu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationSessionManager implements IApplicationConstants
{
    private HttpServletRequest request;
    
    private ApplicationSessionManager(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public static ApplicationSessionManager getSessionManager(HttpServletRequest request)
    {
        ApplicationSessionManager sessionMgr  = new ApplicationSessionManager(request);
        
        request.setAttribute(APPL_SESSION_MGR, sessionMgr);
        
        return sessionMgr;
    }

    /**
     * Description: Generic method to set the bio bean into the request or session
     * @param userContext
     * @param loggedOnUser
     * @param appl_user_info
     * @param appl_bean_scope_request
     */
    public void set(Object bioBean, String bioBeanAlias, int appl_bean_scope)
    {
        if(APPL_BEAN_SCOPE_REQUEST == appl_bean_scope)
            this.request.setAttribute(bioBeanAlias, bioBean);
        else
            if(APPL_BEAN_SCOPE_SESSION == appl_bean_scope)
            {
                this.request.getSession().setAttribute(bioBeanAlias, bioBean);
            }
        else
            this.request.getSession().setAttribute(bioBeanAlias, bioBean);
    }

    
    /**
     * 
     * @param bioBeanAlias
     * @param appl_bean_scope
     * @return
     */
    public Object get(String bioBeanAlias, int appl_bean_scope)
    {
        Object retObject = null;
        if(APPL_BEAN_SCOPE_REQUEST == appl_bean_scope)
            retObject = this.request.getAttribute(bioBeanAlias);
        else
            if(APPL_BEAN_SCOPE_SESSION == appl_bean_scope)
            {
                this.request.getSession().getAttribute(bioBeanAlias);
            }
        else
            this.request.getSession().getAttribute(bioBeanAlias);
        
        
        return retObject;
    }
    
    /**
     * 
     * @param bioBeanAlias
     * @param appl_bean_scope
     */
    public void remove(String bioBeanAlias, int appl_bean_scope)
    {
        if(APPL_BEAN_SCOPE_REQUEST == appl_bean_scope)
            this.request.removeAttribute(bioBeanAlias);
        else
            if(APPL_BEAN_SCOPE_SESSION == appl_bean_scope)
            {
                this.request.getSession().removeAttribute(bioBeanAlias);
            }
        else
            this.request.getSession().removeAttribute(bioBeanAlias);
    }
    
    
}
