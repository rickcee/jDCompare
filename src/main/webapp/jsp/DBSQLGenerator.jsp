<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>jDCompare</title>
<link rel='stylesheet' type='text/css' href='resources/dbCompare.css' />
</head>

<body>
<br />

<table class="resultTable">
	<thead>
		<tr>
			<th rowspan="2"><a href="Init"> <img
				src="resources/images/back.png" style="border-width: 0" title="Back" /></a></th>
			<c:forEach var="meta" items="${MODEL.resultView.metadataPK}">
				<th rowspan="2"><c:out value="${meta}" escapeXml="true" /></th>
			</c:forEach>
			<c:forEach var="meta" items="${MODEL.resultView.metadata}">
				<th colspan='3'><c:out value="${meta}" escapeXml="true" /></th>
			</c:forEach>
		</tr>
		<tr>
			<!--<td class="rowNumber" />
		<c:forEach var="meta" items="${METADATA_PK}">
			<td class="rowNumber"></td>
		</c:forEach>-->
			<c:forEach var="meta" items="${MODEL.resultView.metadata}">
				<th>(${MODEL.report.db1.dbAlias})</th>
				<th>(${MODEL.report.db2.dbAlias})</th>
				<th>Difference</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="pos" items="${MODEL.resultView.rows}">
			<tr class="alertBod">
				<c:set var="row" value="${row+1}" />
				<td><c:out value="${row}" escapeXml="true" /></td>
				<c:forEach var="col" items="${pos.pks}">
					<td class="ids"><c:out value="${col.displayValue}"
						escapeXml="true" /></td>
				</c:forEach>
				<c:forEach var="col" items="${pos.result}">
					<td><c:out value="${col.fieldValue1}" /></td>
					<td><c:out value="${col.fieldValue2}" /></td>
					<td
						<c:choose>
                     <c:when test="${col.isOK}"> class="alertGreen" </c:when>
                     <c:otherwise> class="alertRed" </c:otherwise>
                </c:choose>>
					<c:out value="${col.diff}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>
