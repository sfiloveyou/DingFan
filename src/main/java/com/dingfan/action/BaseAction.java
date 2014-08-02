package com.dingfan.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dingfan.utils.Constants;

public class BaseAction extends DispatchAction  {
    private WebApplicationContext _ctx;
    protected final Log log = LogFactory.getLog(getClass());
    public static final String KEEP_URL_SKEY = "_keepUrlSKey";
    
    
    
    public Object getService(String name) {
        if (_ctx == null) {
            _ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet
                    .getServletContext());
        }
        return _ctx.getBean(name);
    }
    
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String filePath,
			String fileName,int fileSize) throws IOException {
		String path = request.getSession().getServletContext().getRealPath(Constants.UPLOAD_PATH + "\\" + filePath);
		path += "\\" + fileName;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			File file = new File(path);
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[4096];

			response.setContentType("application/x-msdownload");
			response.setContentLength(in.available());
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			int n = -1;
			while ((n = in.read(buffer, 0, 4096)) > -1) {
				out.write(buffer, 0, n);
			}
			response.flushBuffer();
		} catch (Exception e) {
			log.error("download file error!", e);
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}
	

    @SuppressWarnings("unchecked")
    protected void keepUrl(String sKey, HttpServletRequest request,
            HttpServletResponse response) {
        StringBuffer urlString = new StringBuffer();
        urlString.append(request.getRequestURI()).append("?");

        Map paramMap = request.getParameterMap();
        Map.Entry entry = null;
        String key = null;
        String[] values = null;
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            entry = (Map.Entry) it.next();
            key = (String) entry.getKey();
            values = (String[]) entry.getValue();
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && values[i].indexOf("\"") != -1) {
                    // dont keep this
                    continue;
                }
                if (values[i] != null
                        && values[i].length() > Constants.MAX_LENGTH_PER_PARA) {
                    continue;
                }
                if (key.indexOf("(") != -1 && key.endsWith(")") == true) {
                    continue; // maybe struts map-backed
                }
                urlString.append(key).append("=").append(values[i]).append("&");
            }
        }
        urlString.deleteCharAt(urlString.length() - 1);

        sKey = (sKey == null ? "" : sKey);
        log.debug("## sKey:" + sKey + 
                " KeepUrl->" + urlString.toString() + " ##");
        request.getSession().setAttribute(sKey + Constants.SESSION_KEEP_URL,
                response.encodeURL(urlString.toString()));
    }
    /**
     * Back to previous page.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward back(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sKey = (String)request.getParameter(KEEP_URL_SKEY);
        sKey = (sKey == null ? "" : sKey);
        
        String sessionUrlKey = sKey + Constants.SESSION_KEEP_URL;
        String url = (String) request.getSession().getAttribute(sessionUrlKey);
        if(url != null) {
            log.debug("## sKey:" + sKey + " backUrl:" + url);
        } else {
            log.warn("## empty backUrl!!!");
        }
        request.getSession().removeAttribute(sessionUrlKey);
        if(StringUtils.isNotBlank(url)) {          
        	String newUrl = new String(url.getBytes("utf-8"), "ISO-8859-1");

            response.sendRedirect(newUrl);       
        }
        return null;
    }
    
	/**
	 * Get I18NString
	 * @param request,key
	 * @return String
	 */
    public String getI18NString(final HttpServletRequest request,String key) {
		return getResources(request).getMessage(request.getLocale(),key);
	} 
    
    public String getJsonStrFromList(List list) {
		StringWriter str = new StringWriter(); 
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(str, list);
		} catch (Exception e) {
			log.error(e);
			return null;
		} 
		return "{root:"+str.toString()+"}";
	}
    
    public String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    } 
}
