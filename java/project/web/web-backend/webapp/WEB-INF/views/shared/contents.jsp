<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shared/taglibs.jsp"%>
<div class="hero-unit">
  <h1>BPA-NET ADMIN!</h1> 
  <p>
    developer by Son, Seung-Beom
  </p>
  <p>
    <a class="btn btn-primary btn-large">Learn more &raquo;</a>
  </p>
</div>
<div class="row-fluid">
  <div class="span6">
    <h2>Batch</h2>
    <p>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>No</th>
          <th>Job Name</th>
          <th>Trigger State</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${batch}" var="qrtzTriggersVO" varStatus="status">
          <c:choose>
            <c:when test="${ 'BLOCKED' eq qrtzTriggersVO.triggerState }"> 
              <tr class="error">
            </c:when>
            <c:otherwise>
              <tr>
            </c:otherwise>
          </c:choose>        
            <td>${ status.count }</td>
            <td>${ qrtzTriggersVO.qrtzJobDetailsPK.jobName }</td>
            <td>${ qrtzTriggersVO.triggerState }</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <p>
      <a class="btn" href="#">View details &raquo;</a> 
    </p>
  </div>
  <!--/span-->
  <div class="span6">
    <h2>Heading</h2>
    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
      magna mollis euismod. Donec sed odio dui.</p>
    <p>
      <a class="btn" href="#">View details &raquo;</a>
    </p>
  </div>
  <!--/span-->
</div>
<!--/row-->
<div class="row-fluid">
  <div class="span4">
    <h2>Heading</h2>
    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
      magna mollis euismod. Donec sed odio dui.</p>
    <p>
      <a class="btn" href="#">View details &raquo;</a>
    </p>
  </div>
  <!--/span-->
  <div class="span4">
    <h2>Heading</h2>
    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
      magna mollis euismod. Donec sed odio dui.</p>
    <p>
      <a class="btn" href="#">View details &raquo;</a>
    </p>
  </div>
  <!--/span-->
  <div class="span4">
    <h2>Heading</h2>
    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
      magna mollis euismod. Donec sed odio dui.</p>
    <p>
      <a class="btn" href="#">View details &raquo;</a>
    </p>
  </div>
  <!--/span-->
</div>
<!--/row-->