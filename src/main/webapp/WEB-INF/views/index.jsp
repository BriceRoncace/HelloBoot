<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:page>
  <jsp:attribute name="body">
    
    <h3 class="text-center">${msg}</h3>
    <p class="text-center">You are logged on as <strong>${user.username}</strong></p>
    
    <div class="container">
      <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Java</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">${javaVersion}</h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li><b>Home</b> ${javaHome}</li>
              <li><b>VM</b> ${javaVmName}</li>
              <li><b>Vendor</b> ${javaVendor}</li>
              <li><b>OS</b> ${javaOs}</li>
            </ul>
            <a href="${javaVendorUrl}" class="btn btn-lg btn-block btn-outline-primary">${javaVendor}</a>
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Server</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">${servletServerInfo}</h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li><b>Servlet Version</b> ${servletVersion}</li>
              <li><b>Effective Version</b> ${servletEffectiveVersion}</li>
            </ul>
            <a href="https://tomcat.apache.org/" class="btn btn-lg btn-block btn-outline-primary">Tomcat</a>
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Spring Boot</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">${springBootVersion}</h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li><b>Spring</b> ${springVersion}</li>
              <li><b>Hibernate</b> ${hibernateVersion}</li>
              <li>&nbsp;</li>
              <li>&nbsp;</li>
            </ul>
            <a href="https://docs.spring.io/spring-boot/docs/${springBootVersion}/reference/html/appendix-dependency-versions.html" class="btn btn-lg btn-block btn-outline-primary">Spring Boot Dependencies</a>
          </div>
        </div>
      </div>

    </div>
    
  </jsp:attribute>
  <jsp:attribute name="javascript">
    <script type="text/javascript" src="<c:url value="/assets/js/cjis.index.js"/>"></script>
    <script>
      $(function() {
        cjis.index.init();
      });
    </script>
  </jsp:attribute>
</t:page>