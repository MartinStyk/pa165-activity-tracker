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
            <h1><fmt:message key="application_name"/></h1>
            <p class="lead"><fmt:message key="index_welcome"/></p>
            <p><fmt:message key="index_text"/></p>
            <p align="right">
                <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/login" role="button">
                    <fmt:message key="sign_in"/>
                </a>
            </p>
        </div>

    </jsp:attribute>
</own:masterpage>
