<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
    <jsp:attribute name="title"><fmt:message key="team.header"/></jsp:attribute>

    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1><fmt:message key="team.header"/></h1>
            <p class="lead"><fmt:message key="team.subheader"/></p>

            <form:form method="GET"
                   action="${pageContext.request.contextPath}/teams/index"
                   acceptCharset=""
                   cssClass="form-inline">

                <fmt:message key="team_name_placeholder" var="teamNamePlaceholder"/>
                <input name="teamName" value="${param.name}" class="form-control" autocomplete="off" placeholder="${teamNamePlaceholder}"/>
                <button class="btn btn-primary search-btn" type="submit"><i class="glyphicon glyphicon-search"></i>&nbsp;<fmt:message key="search"/></button>
            </form:form>

            <c:if test="${loggedUser.id==team.teamLeader.id}">
                <p align="right">
                    <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/teams/update/${team.id}" role="button">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="team.update"/>
                    </a>
                </p>
             </c:if>

             <c:if test="${empty team}">
                <p align="right">
                    <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/teams/create" role="button">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="team.create"/>
                    </a>
                </p>
             </c:if>
        </div>
        
        <c:if test="${not empty team}">
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th><fmt:message key="num"/></th>
                            <th><fmt:message key="user.firstName"/></th>
                            <th><fmt:message key="user.lastName"/></th>
                            <th><fmt:message key="user.email"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${team.members}" var="member">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td class="col-xs-1 lead-column">${count}.</td>
                                <td class="col-xs-3 lead-column"><c:out value="${member.firstName}"/></td>
                                <td class="col-xs-3 lead-column"><c:out value="${member.lastName}"/></td>
                                <td class="col-xs-3 lead-column"><c:out value="${member.email}"/></td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </c:if>
    </jsp:attribute>
</own:masterpage>
