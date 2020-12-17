create index IX_9D9291FF on Emp_Employee (groupId);
create index IX_9240943F on Emp_Employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_530F1E81 on Emp_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);