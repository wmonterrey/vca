<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<jsp:include page="fragments/headTag.jsp" />
<!-- Styles required by this views -->
<spring:url value="/resources/vendors/css/dataTables.bootstrap4.min.css" var="dataTablesCSS" />
<link href="${dataTablesCSS}" rel="stylesheet" type="text/css"/>

</head>
<!-- BODY options, add following classes to body to change options

// Header options
1. '.header-fixed'					- Fixed Header

// Brand options
1. '.brand-minimized'       - Minimized brand (Only symbol)

// Sidebar options
1. '.sidebar-fixed'					- Fixed Sidebar
2. '.sidebar-hidden'				- Hidden Sidebar
3. '.sidebar-off-canvas'		- Off Canvas Sidebar
4. '.sidebar-minimized'			- Minimized Sidebar (Only icons)
5. '.sidebar-compact'			  - Compact Sidebar

// Aside options
1. '.aside-menu-fixed'			- Fixed Aside Menu
2. '.aside-menu-hidden'			- Hidden Aside Menu
3. '.aside-menu-off-canvas'	- Off Canvas Aside Menu

// Breadcrumb options
1. '.breadcrumb-fixed'			- Fixed Breadcrumb

// Footer options
1. '.footer-fixed'					- Fixed footer

-->
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
  <!-- Header -->
  <jsp:include page="fragments/bodyHeader.jsp" />
  <div class="app-body">
  	<!-- Navigation -->
  	<jsp:include page="fragments/sideBar.jsp" />
    <!-- Main content -->
    <main class="main">

      <!-- Breadcrumb -->
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a></li>
        <li class="breadcrumb-item active">&nbsp;</li>
      </ol>
	  <!-- Container -->
      <div class="container-fluid">
        <div class="animated fadeIn">
        	<div class="row">
	            <div class="col-6 col-lg-3">
		            <div class="card">
			            <div class="card-body p-3 clearfix">
				            <i class="fa fa-home bg-primary p-3 font-2xl mr-3 float-left"></i>
				            <div class="text-uppercase text-muted font-weight-bold font-xs text-right mb-0 mt-2"><spring:message code="vivtot" /></div>
				            <div class="h5 text-right"><c:out value="${totalEncuestas}" /></div>
				            <div class="text-uppercase text-muted font-weight-bold font-xs text-right mb-0 mt-2"><spring:message code="vivact" /></div>
				            <div class="h5 text-right"><c:out value="${totalEncuestasActivas}" /> (<c:out value="${pEncuestasActivas}" />%)</div>
			            </div>
			            <div class="card-footer px-3 py-2">
			            	<a class="font-weight-bold font-xs btn-block text-muted" href="<spring:url value="/census/" htmlEscape="true "/>"><spring:message code="viewcensus" /> <i class="fa fa-angle-right float-right font-lg"></i></a>
			            </div>
		            </div>
	            </div>
            </div>
            
            <div class="row">
            	<div class="col-12 col-lg-12">
            	<div class="card">
            		<div class="card-header">
            			<b><spring:message code="vivday" /></b>

            		</div>
					<div class="card-body">
						<div class="chart-wrapper daychart" style="height:300px;margin-top:20px;">
                  			<canvas id="dates-chart" height="300"></canvas>
              			</div>
            		</div>
          		</div>
          		</div>
            </div>
          
          
          

        </div>

      </div>
      <!-- /.container-fluid -->
    </main>
    
  </div>
  <!-- Pie de página -->
  <jsp:include page="fragments/bodyFooter.jsp" />

  <!-- Bootstrap and necessary plugins -->
  <jsp:include page="fragments/corePlugins.jsp" />
  <jsp:include page="fragments/bodyUtils.jsp" />

  <!-- GenesisUI main scripts -->
  <spring:url value="/resources/js/app.js" var="App" />
  <script src="${App}" type="text/javascript"></script>
  
  <!-- Lenguaje -->
  <c:choose>
	<c:when test="${cookie.evcaLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.evcaLang.value}"/>
	</c:otherwise>
  </c:choose>
  
  <!-- Custom scripts required by this view -->
  <spring:url value="/resources/js/views/home.js" var="ProcessDashboardCenso" />
  <script src="${ProcessDashboardCenso}"></script>
  
  <spring:url value="/view/censo/pordia/" var="vivPorDiaUrl"/>
  

<script>

jQuery(document).ready(function() {
	var parametros = {vivPorDiaUrl: "${vivPorDiaUrl}"};
	ProcessDashboardCenso.init(parametros);
});

</script>

</body>
</html>