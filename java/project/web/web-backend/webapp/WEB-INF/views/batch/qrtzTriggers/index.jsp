<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shared/taglibs.jsp"%>

<h3>QrtzTriggers</h3>

<table class="table table-striped">
	<tr>
		<th>triggerName</th>
		<th>triggerGroup</th>
		<th>jobName</th>
		<th>jobGroup</th>
		<th>isVolatile</th>
		<th>description</th>
		<th>nextFireTime</th>
		<th>prevFireTime</th>
		<th>priority</th>
		<th>triggerState</th>
		<th>triggerType</th>
		<th>startTime</th>
		<th>endTime</th>
		<th>calendarName</th>
		<th>misfireInstr</th>
	</tr>
	<c:forEach items="${ qrtzTriggers }" var="qrtzTrigger" varStatus="status">
		<tr>
			<td>${ status.count }</td>
			<td>${ qrtzTrigger.qrtzTriggersPK.triggerName }</td>
			<td>${ qrtzTrigger.qrtzTriggersPK.triggerGroup }</td>
			<td>${ qrtzTrigger.qrtzJobDetailsPK.jobName }</td>
			<td>${ qrtzTrigger.qrtzJobDetailsPK.jobGroup }</td>
			<td>${ qrtzTrigger.isVolatile }</td>
			<td>${ qrtzTrigger.description }</td>
			<td>${ qrtzTrigger.nextFireTime }</td>
			<td>${ qrtzTrigger.prevFireTime }</td>
			<td>${ qrtzTrigger.priority }</td>
			<td>${ qrtzTrigger.triggerState }</td>
			<td>${ qrtzTrigger.triggerType }</td>
			<td>${ qrtzTrigger.startTime }</td>
			<td>${ qrtzTrigger.endTime }</td>
			<td>${ qrtzTrigger.calendarName }</td>
			<td>${ qrtzTrigger.misfireInstr }</td>
		</tr>
	</c:forEach>
</table>