package com.liferay.training.employee.web.portlet;

import com.liferay.training.employee.exception.EmployeeValidationException;
import com.liferay.training.employee.model.Employee;
import com.liferay.training.employee.service.EmployeeLocalService;
import com.liferay.training.employee.service.EmployeeService;
import com.liferay.training.employee.web.constants.EmployeeDemoWebPortletKeys;

import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeDemoWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeeDemoWebPortletKeys.EMPLOYEEDEMOWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeDemoWebPortlet extends MVCPortlet {
	
	
	
	  @ProcessAction(name="addEmployee") public void addEntry(ActionRequest
	  request, ActionResponse response) throws PortalException {
	  
	  
	  
	  ThemeDisplay themeDisplay = (ThemeDisplay)
	  request.getAttribute(WebKeys.THEME_DISPLAY); ServiceContext serviceContext =
	  ServiceContextFactory.getInstance(Employee.class.getName(),request);
	  
	  
	  String employeeName= ParamUtil.getString(request, "employeeName");
	  
	  String employeeEmail= ParamUtil.getString(request,"employeeEmail"); String
	  employeeDesignation = ParamUtil.getString(request, "employeeDesignation");
	  String employeeAddress=ParamUtil.getString(request,"employeeAddress");
	  
	  long employeeId=ParamUtil.getLong(request,"employeeId");
	  
	  
	  
	  try {
	  
	  _employeeService.addEmployee(themeDisplay.getScopeGroupId(), employeeName, employeeEmail, employeeDesignation, employeeAddress, serviceContext);
	  
	  SessionMessages.add(request, "employeeAdded");
		response.setRenderParameter("mvcPath","/Employee/view.jsp");
	  
	  
	  
	  }catch (EmployeeValidationException ave)
		{
		ave.printStackTrace();
			response.setRenderParameter("mvcPath","/Employee/edit_employee.jsp");
		}
		catch (PortalException pe)
		{
			pe.printStackTrace();
			response.setRenderParameter("mvcPath","/Employee/edit_employee.jsp");
		}
	  }
	  
	  
	  
	  @Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		  
		  
		  try {
		        ServiceContext serviceContext = ServiceContextFactory.getInstance(
		            Employee.class.getName(), renderRequest);

		        long groupId = serviceContext.getScopeGroupId();
		        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long userId = themeDisplay.getUserId();
				

		        
				User user = UserLocalServiceUtil.getUser(userId);
				//Employee employee1=_employeeLocalService.getEmployee(userId);
				
				
						       System.out.println("Inside portlet Before getting");


		        List<Employee> employees = (List<Employee>) _employeeLocalService.getEmployees(groupId);
		        
		        
		        System.out.println(employees);

		        long employeeId = employees.get(0).getEmployeeId();
		        String employeeName=employees.get(0).getEmployeeName();
		        
		        String employeeEmail=employees.get(0).getEmployeeEmail();
		        String employeeDesignation=employees.get(0).getEmployeeDesignation();
		        String employeeAddress=employees.get(0).getEmployeeAddress();

			System.out.println("After employees");
			
			
			
			System.out.println(employeeId);
			
			System.out.println(employeeName);
			  if (employeeId == 0) { employeeId = employees.get(0).getEmployeeId(); }
			 
		        renderRequest.setAttribute("employeeId", employeeId);
		        
		        
		        renderRequest.setAttribute("employeeName", employeeName);
		        renderRequest.setAttribute("employeeEmail", employeeEmail);
		        
		        renderRequest.setAttribute("employeeDesignation", employeeDesignation);
		        renderRequest.setAttribute("employeeAddress", employeeAddress);
		        
		        
		        renderRequest.setAttribute("user", user);
				
				renderRequest.setAttribute("employee",employees.get(0));

				System.out.println("After Setting Attributes");
		    }
		    catch (Exception e) {
		        throw new PortletException(e);
		    }

		  
		
			
		  
		  
		  
		super.render(renderRequest, renderResponse);
	}


@Reference
protected EmployeeLocalService _employeeLocalService;

	@Reference
	  protected EmployeeService _employeeService;
	 

}