<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>DB Compare IT</title>
<link rel="stylesheet" type="text/css" href="../test.css" />
<link rel='stylesheet' type='text/css' href='../mms.css' />
</head>

<body>
<br />

<form id="params" name="params" method="post" action="TableSelection">
<table align="center" border class="alerts" style='width: 75%'>
	<tr class="alertHd" style="font-size: 12">
		<td colspan='2'>Database Configuration // Environment 1</td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">URL</td>
		<td class="rowNumber"><input type="text" name="DB1_URL"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Driver Class</td>
		<td class="rowNumber"><input type="text" name="DB1_CLASS"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Username</td>
		<td class="rowNumber"><input type="text" name="DB1_USERNAME"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Password</td>
		<td class="rowNumber"><input type="text" name="DB1_PASSWORD"
			style="width: 100%"></td>
	</tr>
</table>
<br />
<table align="center" border class="alerts" style='width: 75%'>
	<tr class="alertHd" style="font-size: 12">
		<td colspan='2'>Database Configuration // Environment 2</td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">URL</td>
		<td class="rowNumber"><input type="text" name="DB2_URL"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Driver Class</td>
		<td class="rowNumber"><input type="text" name="DB2_CLASS"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Username</td>
		<td class="rowNumber"><input type="text" name="DB2_USERNAME"
			style="width: 100%"></td>
	</tr>

	<tr class="alertHd">
		<td class="rowNumber" style="width: 30%">Password</td>
		<td class="rowNumber"><input type="text" name="DB2_PASSWORD"
			style="width: 100%"></td>
	</tr>
</table>
<br />
<table align="center" border class="alerts" style='width: 75%'>
	<tr class="alertHd">
		<td colspan='6'><input type="submit" value="Next..."></td>
	</tr>
</table>

</form>

<br />

</body>
</html>
