<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ attribute name="scripts" fragment="true" required="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="onw" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--    cross sit request forgery tags-->
        <meta name="_csrf" th:content="${_csrf.token}"/>
        <meta name="_csrf_header" th:content="${_csrf.headerName}"/>

        <title><c:out value="${title}"/></title>
        <!-- bootstrap css -->
        <!--<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <!--custom css stylesheet-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/site.css"  crossorigin="anonymous">
        <!-- jquery themes -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Activity Tracker</a>
                <div id="navbar" class="collapse navbar-collapse pull-left">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/reports">Reports</a></li>
                        <li><a href="${pageContext.request.contextPath}/sports">Sports</a></li>
                        <li><a href="${pageContext.request.contextPath}/teams">Teams</a></li>
                        <li><a href="${pageContext.request.contextPath}/settings">Settings</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
                <ul class="nav navbar-nav navbar-right pull-right">        
                    <c:choose>
                        <c:when test="${not empty userData}">
                            <li class="dropdown" id="menuLogin">
                              <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">
                                  <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                  <span class="userName">${userData.firstName}</span>
                                  <span class="caret"></span>
                              </a>
                              <div class="dropdown-menu" style="padding:17px;">
                                  <!--<p>Prihlaseny:</p>-->
                                  <p>Logged in:&nbsp;<span class="fullName">${userData.firstName}&nbsp;${userData.lastName}</span></p>
                                  <p>Email:&nbsp;<span class="userEmail">${userData.email}</span></p>                                  
                                  <a href="${pageContext.request.contextPath}/logout">Log out</a>
                              </div>
                            </li>

                        </c:when>
                        <c:otherwise>
                          <li><a href="${pageContext.request.contextPath}/login">Log in</a></li> 
                        </c:otherwise>
                    </c:choose>
                </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <!-- authenticated user info -->
            <c:if test="${not empty authenticatedUser}">
                <div class="row">
                    <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <c:out value="${authenticatedUser.firstName} ${authenticatedUser.lastName}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="container">
                <!-- alerts -->
                <c:if test="${not empty alert_danger}">
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <c:out value="${alert_danger}"/></div>
                    </c:if>
                    <c:if test="${not empty alert_info}">
                    <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
                </c:if>
                <c:if test="${not empty alert_success}">
                    <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
                </c:if>
                <c:if test="${not empty alert_warning}">
                    <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
                </c:if>
            </div>

            <!-- page body -->
            <div class="container">
                <jsp:invoke fragment="body"/>
            </div>
        </div>
            
        <!-- footer -->
        <footer class="footer">
            <div class="container" align="center">
                <p class="text-muted">&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp; Jan Grudman, Adam Laurenčík, Petra Ondřejková, Martin Styk</p>
            </div>
        </footer>
            
        <!-- JavaScript libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <!-- include JavaScript custom files -->
        <jsp:invoke fragment="scripts"/>
    </body>
</html>