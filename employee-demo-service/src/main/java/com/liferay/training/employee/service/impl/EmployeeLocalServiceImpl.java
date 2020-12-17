/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.employee.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.training.employee.model.Employee;
import com.liferay.training.employee.service.base.EmployeeLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the employee local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.employee.service.EmployeeLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Pankaj
 * @see EmployeeLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.training.employee.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.training.employee.service.EmployeeLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.training.employee.service.EmployeeLocalServiceUtil</code>.
	 */
	
	
	public Employee addEmployee(long groupId,String employeeName,String employeeEmail,String employeeDesignation,String employeeAddress,ServiceContext serviceContext) throws PortalException{
		
		//Get Group and User information
				Group group= groupLocalService.getGroup(groupId);
				long userId = serviceContext.getUserId();
				User user =userLocalService.getUser(userId);
				
				//Genrate the Primary key for the Assignment
				long employeeId= counterLocalService.increment(Employee.class.getName());
				
				//Create Employee , this doesnt yet persist the Entity
				Employee employee= createEmployee(employeeId);
				
				
		employee.setCompanyId(group.getCompanyId());
		employee.setGroupId(groupId);
		employee.setUserId(userId);
		employee.setUserName(user.getFullName());
		employee.setCreateDate(serviceContext.getCreateDate(new Date()));
		employee.setModifiedDate(serviceContext.getCreateDate(new Date()));
		
		employee.setEmployeeName(employeeName);
		employee.setEmployeeEmail(employeeEmail);
		employee.setEmployeeDesignation(employeeDesignation);
		employee.setEmployeeAddress(employeeAddress);
		
		employee=super.addEmployee(employee);
		
		return employee;
	}
	
	
	public List<Employee> getEmployees(long groupId) {

		return employeePersistence.findByGroupId(groupId);
	}



	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not Supported");	}
	
	
	
	
	
	
}