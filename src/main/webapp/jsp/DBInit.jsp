<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>jDCompare</title>
<link rel='stylesheet' type='text/css' href='resources/dbCompare.css' />

<script type="text/javascript">
function goExecReport(value) {
	document.form.reportID.value = value;
    document.form.submit();
}

function goExportReport(value) {
    document.formExport.reportID.value = value;
    document.formExport.submit();
}

function goAddUpdateReport(value) {
    document.form2.reportID.value = value;
    document.form2.isDeleteReport.value = false;
    document.form2.submit();
}

function goDeleteReport(value) {
    document.form2.reportID.value = value;
    document.form2.isDeleteReport.value = true;
    document.form2.submit();
}

function goAddUpdateConn(value) {
    document.form3.configID.value = value;
    document.form3.isDeleteConfig.value = false;
    document.form3.submit();
}

function goDeleteConn(value) {
    document.form3.configID.value = value;
    document.form3.isDeleteConfig.value = true;
    document.form3.submit();
}
</script>
</head>

<body>
<!-- 
<table style="width: 100%; ">
	<tr>
		<td 
			style="background: transparent; border: none; width: 50%; text-align: center;"><img
			src="resources/images/jd_logo.png" /></td>
	</tr>
</table>
 -->
<br />

<table class="tableSettings">
	<thead>
		<tr>
			<th class="alt" colspan="3"><c:out
				value="Active database connections" /></th>
			<th class="alt"><img src="resources/images/plus.png" width="24"
				style="border-width: 0; cursor: pointer;"
				onclick="javascript:goAddUpdateConn(null);"
				title="Add a new connection" /></th>
		</tr>
		<tr>
			<th><c:out value="Alias" /></th>
			<th><c:out value="URL" /></th>
			<th colspan="2">Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="config" items="${model.dbs}">
			<tr>
				<td><c:out value="${config.dbAlias}" /></td>
				<td><c:out value="${config.dbURL}" /></td>
				<td><img src="resources/images/search.png" width="24"
					onclick="javascript:goAddUpdateConn(${config.id});"
					style="border-width: 0; cursor: pointer;" title="View / Modify" /></td>
				<td><img src="resources/images/trash-empty.png" width="24"
					onclick="javascript:goDeleteConn(${config.id});"
					style="border-width: 0; cursor: pointer;" " title="Delete" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br />

<form:form name="form" action="SQLQuery" modelAttribute="model"
	method="POST">
	<form:hidden id="reportID" path="reportID" />
</form:form>

<form:form name="formExport" action="Export" modelAttribute="model"
    method="POST">
    <form:hidden id="reportID" path="reportID" />
</form:form>

<form:form name="form2" action="AddNewReport" modelAttribute="model"
	method="GET">
	<form:hidden id="reportID" path="reportID" />
	<form:hidden id="isDeleteReport" path="isDeleteReport" />
</form:form>

<form:form name="form3" action="AddNewConnection" modelAttribute="model"
	method="GET">
	<form:hidden id="configID" path="configID" />
	<form:hidden id="isDeleteConfig" path="isDeleteConfig" />
</form:form>

<table class="tableSettings">
	<thead>
		<tr>
			<th class="alt" colspan="5"><c:out value="Active Reports" /></th>
			<th class="alt"><img src="resources/images/plus.png" width="24"
				style="border-width: 0; cursor: pointer;"
				onclick="javascript:goAddUpdateReport(null);"
				title="Add a new report" /></th>
		</tr>
		<tr>
			<th><c:out value="Report Name" /></th>
			<th><c:out value="Description" /></th>
			<th colspan="4">Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="pos" items="${model.reports}">
			<tr>
				<td><c:out value="${pos.sqlQueryName}" /></td>
				<td><c:out value="${pos.sqlQueryDescription}" /></td>
				<td><img src="resources/images/search.png" width="24"
					onclick="javascript:goAddUpdateReport(${pos.id});"
					style="border-width: 0; cursor: pointer;" title="View / Modify" /></td>
				<td><img src="resources/images/trash-empty.png" width="24"
					onclick="javascript:goDeleteReport(${pos.id});"
					style="border-width: 0; cursor: pointer;" title="Delete" /></td>
				<td><img src="resources/images/execute.png" width="24"
					onclick="javascript:goExecReport(${pos.id});"
					style="border-width: 0; cursor: pointer;" title="Execute" /></td>
                <td><img src="resources/images/icon-excel.png" width="24"
                    onclick="javascript:goExportReport(${pos.id});"
                    style="border-width: 0; cursor: pointer;" title="Export to Excel" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>
