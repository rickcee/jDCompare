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
<!--
    function toggle_visibility(id) {
       var e = document.getElementById(id);
       if(e.style.display == 'block') {
          e.style.display = 'none';
          arrowImage.src = 'resources/images/arrow-down-double.png';
       } else {
          e.style.display = 'block';
          arrowImage.src = 'resources/images/arrow-up-double.png';
       }
    }
//-->
</script>
</head>

<body>

<!-- 
<table style="width: 100%;">
	<tr>
		<td
			style="background: transparent; border: none; width: 50%; text-align: center;"><img
			src="resources/images/jd_logo.png" /></td>
	</tr>
</table>
 -->
<br />

<form:form name="form" action="AddNewConnection" modelAttribute="MODEL"
	method="POST">
	<table class="tableSettings">
		<thead>
			<tr>
				<th class="alt" colspan='3'
					style="color: blue; font-size: 16px; font-weight: bold">Oooops,
				an error has ocurred!</th>
			</tr>
		</thead>
		<tbody>
			<tr class="Label">
				<td class="Input" style="color: red; text-align: center;">
				${MODEL.exceptionMessage}</td>
			</tr>
			<tr class="Label">
				<td class="Input" style="text-align: right">Stack Trace: <img
					id="arrowImage" src="resources/images/arrow-down-double.png"
					onclick="toggle_visibility('arrow');" />
				<div id="arrow"
					style="display: none; text-align: left; font-weight: normal"><c:out
					value="${MODEL.exceptionTrace}" /></div>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="3"><a href="Init"> <img
					src="resources/images/back.png" style="border-width: 0" title="Back" /></a></th>
			</tr>
		</tfoot>
	</table>

</form:form>

</body>
</html>
