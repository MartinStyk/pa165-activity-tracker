<%-- 
    Document   : index
    Created on : Dec 3, 2016, 12:08:22 AM
    Author     : Adam
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
    <jsp:attribute name="scripts">
        <!--    This is how you include page-specific javascript files to the page. 
            This section will be rendered at the very bottom pg the page after script tags
            that include Javascript libraries like jQuery and jQuery UI,
            so there is no need to include then on every page.-->
        <script src="${pageContext.request.contextPath}/js/test.js"></script>
    </jsp:attribute>
    <jsp:attribute name="body">

        <div class="jumbotron homepage">
            <c:choose>
                <c:when test="${empty loggedUser}">
                    <h1><fmt:message key="application_name"/></h1>
                    <p class="lead"><fmt:message key="index_welcome"/></p>
                    <p><fmt:message key="index_text"/></p>
                    <p align="right">
                        <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/login" role="button">
                            <fmt:message key="sign_in"/>
                        </a>
                    </p>
                    
                </c:when>
                <c:otherwise>
                    <h1>
                        <fmt:message key="index_hi">
                            <fmt:param value="${loggedUser.firstName}"/>
                        </fmt:message>
                    </h1>
                    <p><fmt:message key="index_textlogin"/></p>

                    <div class="row homepagePanels">

                        <div class=" col-lg-4 col-md-4 col-sm-4 allBurnCalories">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                  <h3 class="panel-title">
                                      <img class="icon calorie" src="http://calcuworld.com/wp-content/uploads/2013/02/Calories-Burned-150x150.png"
                                           style="max-height:25px; max-width:25px"/>
                                      <span><fmt:message key="user.totalCalories"/></span>
                                  </h3>
                                </div>
                                <div class="panel-body">
                                    <span>${statistics.totalCalories}</span>
                                </div>
                            </div>
                        </div>

                        <div class=" col-lg-4 col-md-4 col-sm-4 BurnCalorieLastWeek">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                  <h3 class="panel-title">
                                      <img class="icon calorie" src="http://calcuworld.com/wp-content/uploads/2013/02/Calories-Burned-150x150.png"
                                           style="max-height:25px; max-width:25px"/>
                                      <span><fmt:message key="user.caloriesLastWeek"/></span>
                                  </h3>
                                </div>
                                <div class="panel-body">
                                    <span>${statistics.caloriesLastWeek}</span>
                                </div>
                            </div>
                        </div>
 
                        <div class=" col-lg-4 col-md-4 col-sm-4 BurnCalorieLastMounth">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                  <h3 class="panel-title">
                                      <img class="icon calorie" src="http://calcuworld.com/wp-content/uploads/2013/02/Calories-Burned-150x150.png"
                                           style="max-height:25px; max-width:25px"/>
                                      <span><fmt:message key="user.caloriesLastMonth"/></span>
                                  </h3>
                                </div>
                                <div class="panel-body">
                                    <span>${statistics.caloriesLastMonth}</span>
                                </div>
                            </div>
                        </div>
 
                    </div>

                </c:otherwise>
            </c:choose>
            
        </div>

    </jsp:attribute>
</own:masterpage>
