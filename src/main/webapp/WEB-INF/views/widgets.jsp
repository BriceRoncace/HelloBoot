<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:page>
  <jsp:attribute name="body">
    <h3>Widgets <span class="badge badge-light">${widgets.size()}</span> <button type="submit" id="addWidget" class="btn btn-sm btn-primary float-right"><i class="fas fa-plus"></i></button></h3>
    
    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Expiration</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="widget" items="${widgets}">
          <tr>
            <td><a href="#" data-id="${widget.id}" data-name="${widget.name}" data-expiration="${widget.expiration}">${widget.id}</a></td>
            <td>${widget.name}</td>
            <td><spring:eval expression="widget.expiration"/></td>
            <th>
              <form action="<c:url value="/deleteWidget"/>" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${widget.id}"/>
                <button type="submit" class="btn btn-sm btn-primary"><i class="fas fa-trash"></i></button>
              </form>
            </th>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <div id="saveWidgetModal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Save Widget</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          
          <form action="<c:url value="/saveWidget"/>" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" id="id" name="id" />
            
            <div class="modal-body">
              
              <div class="form-group">
                <label>Name</label>
                <input type="text" id="name" data-modal-clear data-modal-focus class="form-control" name="name"/>
              </div>
               
              <div class="form-group">
                <label>Expiration</label>
                <input type="date" id="expiration" data-modal-clear class="form-control" name="expiration"/>
              </div>
              
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Save</button>
            </div>
          </form>
            
        </div>
      </div>
    </div>
    
  </jsp:attribute>
  <jsp:attribute name="javascript">
    <script type="text/javascript" src="<c:url value="/assets/js/cjis.widgets.js"/>"></script>
    <script>
      $(function() {
        cjis.init();
        cjis.widgets.init();
      });
    </script>
  </jsp:attribute>
</t:page>