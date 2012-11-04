<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shared/taglibs.jsp"%>

<h3>QrtzJobDetails</h3>

<table class="table table-striped">
	<tr>
		<th>jobName</th>
		<th>jobGroup</th>
		<th>description</th>
		<th>jobClassName</th>
		<th>isDurable</th>
		<th>isVolatile</th>
		<th>isStateful</th>
		<th>requestsRecovery</th>
	</tr>
	<c:forEach items="${ qrtzJobDetails }" var="qrtzJobDetail" varStatus="status">
		<tr>
			<td>${ status.count }</td>
			<td>${ qrtzJobDetails.qrtzJobDetailsPK.jobName }</td>
			<td>${ qrtzJobDetails.qrtzJobDetailsPK.jobGroup }</td>
			<td>${ qrtzJobDetails.description }</td>
			<td>${ qrtzJobDetails.jobClassName }</td>
			<td>${ qrtzJobDetails.isDurable }</td>
			<td>${ qrtzJobDetails.isVolatile }</td>
			<td>${ qrtzJobDetails.isStateful }</td>
			<td>${ qrtzJobDetails.requestsRecovery }</td>
		</tr>
	</c:forEach>
</table>