package com.dingfan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dingfan.service.SystemService;
import com.dingfan.utils.ServiceGetter;


public class LoginAction extends BaseAction {
	private SystemService dfs =ServiceGetter.getInstance().getSystemService();
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("## Entry LoginAction 'init' method ##");
		log.debug("## Leave LoginAction 'init' method ##");
    	return mapping.findForward("login");
	}
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("## Entry LoginAction 'logout' method ##");
		try {
			
		} catch (Exception e) {
			request.setAttribute("onLoadMessage", "系统错误");
			log.error("## LoginAction 'logout' method-error ##", e);
		}
		log.debug("## Leave LoginAction 'logout' method ##");
    	return mapping.findForward("logout");
	}
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("## Entry LoginAction 'logout' method ##");
		try {
		} catch (Exception e) {
			request.setAttribute("onLoadMessage", "系统错误");
			log.error("## LoginAction 'logout' method-error ##", e);
		}
		log.debug("## Leave LoginAction 'logout' method ##");
    	return mapping.findForward("logout");
	}
	public ActionForward loginFailure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("## Entry LoginAction 'loginFailure' method ##");
		try {
			request.setAttribute("onLoadMessage", "登录失败");
		} catch (Exception e) {
			request.setAttribute("onLoadMessage", "系统错误");
			log.error("## LoginAction 'loginFailure' method-error ##", e);
		}
		log.debug("## Leave LoginAction 'loginFailure' method ##");
    	return mapping.findForward("login");
	}
	public ActionForward accessDenied(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("## Entry LoginAction 'accessDenied' method ##");
		try {
			
		} catch (Exception e) {
			request.setAttribute("onLoadMessage", "系统错误");
			log.error("## LoginAction 'loginFailure' method-error ##", e);
		}
		log.debug("## Leave LoginAction 'accessDenied' method ##");
    	return mapping.findForward("accessDenied");
	}
}
