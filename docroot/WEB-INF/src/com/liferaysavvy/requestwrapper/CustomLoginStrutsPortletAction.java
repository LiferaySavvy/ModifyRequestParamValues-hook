/**
 * 
 */
package com.liferaysavvy.requestwrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

public class CustomLoginStrutsPortletAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		String modifiedRedirectValue = ParamUtil.getString(actionRequest,"redirect");
		String newParamValue = ParamUtil.getString(actionRequest,"newParam");
		logger.info("Modified Redirect Value:"+modifiedRedirectValue);
		logger.info("Nee Parameter Value:"+newParamValue);
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(actionRequest);
		modifiedRedirectValue = ParamUtil.getString(httpServletRequest,"redirect");
		newParamValue = ParamUtil.getString(httpServletRequest,"newParam");
		logger.info("httpServletRequest Modified Redirect Value:"+modifiedRedirectValue);
		logger.info("httpServletRequest Nee Parameter Value:"+newParamValue);
		originalStrutsPortletAction.processAction(portletConfig, actionRequest,
				actionResponse);
	}

	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {
		return originalStrutsPortletAction.render(portletConfig, renderRequest,
				renderResponse);
	}

	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {

		originalStrutsPortletAction.serveResource(portletConfig,
				resourceRequest, resourceResponse);
	}

	private static Log logger = LogFactoryUtil
			.getLog(CustomLoginStrutsPortletAction.class);
}