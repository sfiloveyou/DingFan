package com.dingfan.action;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dingfan.service.DingFanService;
import com.dingfan.utils.ServiceGetter;

public class DingFanAction extends BaseAction {
	private DingFanService dfs =ServiceGetter.getInstance().getDingFanService();
	public ActionForward queryAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DingFanService df =ServiceGetter.getInstance().getDingFanService();
		List OrdersList = df.getOrdersByToday();	
		response.getWriter().print(getJsonStrFromList(OrdersList));
		
    	return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String personName = (String)request.getParameter("personName");
		String amount = (String)request.getParameter("amount");
		String comments = (String)request.getParameter("comments");
		
		dfs.saveOrders(personName, amount, comments, getIpAddr(request));
		
		return mapping.findForward((String)request.getParameter("page"));
	}
	
	public ActionForward queryPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String personName = (String)request.getParameter("personName");
		List personNames = dfs.getNamesFromOrders(personName);
		response.getWriter().print(getJsonStrFromList(personNames));  
		return null;
	}
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String selectedId = (String)request.getParameter("selectedId");
		dfs.del(selectedId, getIpAddr(request));
		return mapping.findForward((String)request.getParameter("page"));
	}
	
	public ActionForward pay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String selectedId = (String)request.getParameter("selectedId");
		dfs.pay(selectedId, getIpAddr(request));
		return mapping.findForward((String)request.getParameter("page"));
	}
	
	public ActionForward startDingFan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().getServletContext().setAttribute("startDingFan", true);
		return mapping.findForward((String)request.getParameter("page"));
	}
	
	public ActionForward stopDingFan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().getServletContext().setAttribute("startDingFan", false);
		return mapping.findForward((String)request.getParameter("page"));
	}
}
