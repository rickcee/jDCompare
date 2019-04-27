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

<form:form name="form" action="AddNewConnection" modelAttribute="model" 
	method="POST">
	<table class="tableSettings">
		<thead>
			<tr>
				<th class="alt" colspan='3'>Database Configuration</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="Label">Alias</td>
				<td class="Input"><form:input path="config.dbAlias" /></td>
				<td class="Label"><form:errors path="config.dbAlias"
					cssClass="validationError" /></td>
			</tr>

			<tr>
				<td class="Label">URL</td>
				<td class="Input"><form:input path="config.dbURL" /></td>
				<td class="Label"><form:errors path="config.dbURL"
					cssClass="validationError" /></td>
			</tr>

			<tr>
				<td class="Label">Driver Class</td>
				<td class="Input"><form:input path="config.dbDriver" /></td>
				<td class="Label"><form:errors path="config.dbDriver"
					cssClass="validationError" /></td>
			</tr>

			<tr>
				<td class="Label">Username</td>
				<td class="Input"><form:input path="config.dbUserName" /></td>
				<td class="Label"><form:errors path="config.dbUserName"
					cssClass="validationError" /></td>
			</tr>

			<tr>
				<td class="Label">Password</td>
				<td class="Input"><form:password path="config.dbPassword" /></td>
				<td class="Label"><form:errors path="config.dbPassword"
					cssClass="validationError" /></td>
			</tr>


			<tr>
				<th colspan="3"><c:choose>
					<c:when test="${model.isDeleteConfig}">
						<table style="margin-left: auto; margin-right: auto;">
							<tr>
								<th><img src="resources/images/exclamation.png"
									onclick="form.submit();" style="border-width: 0" /></th>
								<th colspan="2"><c:out
									value="Confirm that you want to delete the connection."></c:out></th>
							</tr>
						</table>
					</c:when>
				</c:choose>
				<table style="margin-left: auto; margin-right: auto;">
					<tr>
						<th><a href="Init"> <img src="resources/images/back.png"
							style="border-width: 0" title="Back" /></a></th>
						<c:choose>
							<c:when test="${model.isDeleteConfig}">
								<th><a href="javascript:form.submit();"><img
									src="resources/images/trash-empty.png" onclick="form.submit();"
									style="border-width: 0" title="Delete" /></a></th>
							</c:when>
							<c:otherwise>
								<th><a href="javascript:form.submit();"><img
									src="resources/images/save.png" onclick="form.submit();"
									style="border-width: 0" title="Save" /></a></th>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				</th>
			</tr>
		</tbody>
	</table>

</form:form>

</body>
</html>
