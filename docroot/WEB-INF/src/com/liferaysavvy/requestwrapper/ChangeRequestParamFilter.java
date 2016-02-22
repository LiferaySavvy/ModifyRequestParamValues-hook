package com.liferaysavvy.requestwrapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

public class ChangeRequestParamFilter implements Filter {
	
	@Override
	public void destroy() {
		logger.info("ChangeRequestParamFilter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		logger.info("inside ChangeRequestParamFilter doFilter ");
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		
		/*Getting actual Redirect Param Value*/
		String actuallRedirectValue = ParamUtil.getString(httpServletRequest,"redirect");
		logger.info("Actual Redirect Param Value:" + actuallRedirectValue);
		
	   /* Modifying redirect param value and also adding new parameter to request object*/
			Map<String, String[]> modifyAddParamValueMap = new TreeMap<String, String[]>();
			String[] redirectParamArray = new String[1];
			redirectParamArray[0] = "http://localhost:8080/web/guest/custom-portlet-workflow";
			modifyAddParamValueMap.put("redirect", redirectParamArray);
			modifyAddParamValueMap.put("newParam",new String[]{"Hello I am New Param value"});
			HttpServletRequest wrappedRequest = new AddModifyRequestParamValueWrappedRequest(
					httpServletRequest, modifyAddParamValueMap);
			filterChain.doFilter(wrappedRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {

		logger.debug("Called ChangeRequestParamFilter init(" + filterConfig + ")");
	}

	private static final Log logger = LogFactoryUtil
			.getLog(ChangeRequestParamFilter.class);

}
