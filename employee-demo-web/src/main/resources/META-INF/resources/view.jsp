<%@page import="javax.portlet.RenderRequest"%>


<%@page import="java.util.List" %>
<%@page import="com.liferay.training.employee.model.Employee" %>
<%@page
	import="com.liferay.training.employee.service.EmployeeLocalServiceUtil"%>
<%@ include file="/init.jsp"%>
<%
	long employeeId = Long.valueOf((Long) renderRequest.getAttribute("employeeId"));
%>

<portlet:renderURL var="addEmployeeURL">
	<portlet:param name="mvcPath" value="/Employee/edit_employee.jsp"></portlet:param>
	<portlet:param name="employeeId"
		value="<%=String.valueOf(employeeId)%>" />
</portlet:renderURL>
<aui:button-row>
	<aui:button onClick="<%=addEmployeeURL.toString()%>"
		value="Add Employee"></aui:button>
</aui:button-row>

<portlet:defineObjects />
<%-- <jsp:useBean id="employees" class="java.util.ArrayList" scope="request" />
 --%>
