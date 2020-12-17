<%@page import="com.liferay.training.employee.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.training.employee.service.EmployeeLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%@page import="javax.portlet.RenderRequest"%>


<h1>Thanks for Submitting your Details</h1>

<%
	long employeeId = Long.valueOf((Long) renderRequest.getAttribute("employeeId"));
%>


<%
List<Employee> employee=EmployeeLocalServiceUtil.getEmployees(0,EmployeeLocalServiceUtil.getEmployeesCount());

%>





<liferay-ui:search-container>
	<liferay-ui:search-container-results results="<%=employee%>" />
	<liferay-ui:search-container-row
		className="com.liferay.training.employee.model.Employee"
		modelVar="entry">


		<liferay-ui:search-container-column-text property="employeeId" />
		<liferay-ui:search-container-column-text property="employeeName" />
		<liferay-ui:search-container-column-text property="employeeEmail" />
		<liferay-ui:search-container-column-text
			property="employeeDesignation" />
		<liferay-ui:search-container-column-text property="employeeAddress" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

