<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Create Report">
    <jsp:attribute name="body">

        <a href="${pageContext.request.contextPath}/reports" class="btn btn-default" role="button">
            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
            <fmt:message key="back"/>
        </a>

        <div class="page-header">
            <h1>
                <fmt:message key="reports.create"/>
            </h1>
        </div>

        <form:form method="POST"
                   action="${pageContext.request.contextPath}/reports/create"
                   acceptCharset=""
                   modelAttribute="reportCreate"
                   cssClass="form-horizontal">


            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="startTime" cssClass="col-sm-2 control-label"><fmt:message key="report.startTime"/></form:label>
                    <div class="col-sm-10">
                    <form:input path="startTime" cssClass="form-control"/>
                    <form:errors path="startTime" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary createBtn center-block allow-vertical-space" type="submit"><fmt:message key="submit"/></button>
        </form:form>
    </jsp:attribute>
</own:masterpage>