<%-- 
    Document   : index
    Created on : Dec 3, 2016, 12:08:22 AM
    Author     : Adam Laurencik
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Reports">
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1><fmt:message key="reports.header"/></h1>
            <p class="lead"><fmt:message key="reports.subheader"/></p>

            <%--   <c:if test="${isAdmin}"> --%>
            <p align="right">
                <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/reports/create" role="button">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="reports.create"/>
                </a>
            </p>
            <%--  </c:if> --%>
        </div>

    </jsp:attribute>
</own:masterpage>
