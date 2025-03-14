<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<jsp:include page="DBHeader.jsp" />
<body>
<br />

<form id="params" name="params" method="post" action="SQLGenerator">
<table align="center" border class="alerts" style='width: 50%'>

	<tr class="alertHd">
		<td colspan='10'><input type="button" value="Back"
			onClick="javascript:history.go(-1);"> <input type="submit"
			value="Next"></td>
	</tr>

	<tr class="alertHd">
		<td><b>Property</b></td>
		<td><b>Value</b></td>
	</tr>

	<c:set var="row" value="0" />

	<tr class="alertBod" style="height: 20">
		<td class="rowNumber">Table Name</td>
		<td>
			<b><c:out value="${MODEL.currentTable}" escapeXml="true" /></b>
		</td>
	</tr>

	<tr class="alertBod" style="height: 20">
		<td class="rowNumber">Select</td>
		<td>
			<c:forEach var="pos" items="${MODEL.currentColumns}">
				<c:out value="${pos}," escapeXml="true" />
			</c:forEach>
		</td>
	</tr>

	<tr class="alertBod" style="height: 20">
		<td class="rowNumber">Group by</td>
		<td>
			<c:forEach var="pos" items="${MODEL.groupByColumns}">
				<c:out value="${pos}," escapeXml="true" />
			</c:forEach>
		</td>
	</tr>

	<tr class="alertBod" style="height: 20">
		<td class="rowNumber">Diff</td>
		<td>
			<c:forEach var="pos" items="${MODEL.diffColumns}">
				<c:out value="${pos}," escapeXml="true" />
			</c:forEach>
		</td>
	</tr>
	
	<tr class="alertHd">
		<td colspan='10'><input type="button" value="Back"
			onClick="javascript:history.go(-1);"> <input type="submit"
			value="Next"></td>
	</tr>
</table>
</form>

<br />

</body>
</html>
