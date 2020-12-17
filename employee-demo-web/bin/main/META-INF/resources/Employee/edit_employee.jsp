<%@ include file="/init.jsp" %>


<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/Employee/view.jsp"></portlet:param>
</portlet:renderURL>


<portlet:actionURL name="addEntry" var="addEntryURL" />


<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">



	<aui:fieldset>

		<aui:input name="employeeName" label="Employee Name" />
		<aui:input name="employeeEmail"  label="Employee Email"/>
		<aui:input name="employeeDesignation" label="Employee Designation" />
		<aui:input name="employeeAddress" label="Employee Address"/>
		<aui:input name="employee" type="hidden" />

	</aui:fieldset>

	<aui:button-row>

		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>

	</aui:button-row>
</aui:form>