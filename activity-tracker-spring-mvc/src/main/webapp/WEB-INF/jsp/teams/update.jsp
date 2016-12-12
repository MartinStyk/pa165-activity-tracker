<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Update Team">
    <jsp:attribute name="body">

            <a href="${pageContext.request.contextPath}/teams" class="btn btn-default" role="button">
                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                <fmt:message key="back"/>
            </a>

            <div class="page-header">
                <h1>
                   <fmt:message key="team.update"/>
                </h1>
            </div>

        <form:form method="POST"
                   action="${pageContext.request.contextPath}/teams/update/${teamUpdate.id}"
                   acceptCharset=""
                   modelAttribute="teamUpdate"
                   cssClass="form-horizontal">

            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="team.name"/></form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
                
                
            <%--http://stackoverflow.com/questions/22332452/spring-forms-bind-exception-illegalstateexception-cannot-convert-value-of-typ --%>
            <div class="form-group ${teamLeader_error?'has-error':''}">
                <form:label path="teamLeader" cssClass="col-sm-2 control-label"><fmt:message key="team.teamLeader"/></form:label>
                <div class="col-sm-10">
                    <form:select path="teamLeader" cssClass="form-control">
                        <c:forEach items="${teamUpdate.members}" var="m">
                            <form:option value="${m.id}"> ${m.email} </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="teamLeader" cssClass="help-block"/>
                </div>
            </div>

                    <%--           <div class="form-group ${teamLeader_error?'has-error':''}">
                <form:label path="teamLeader" cssClass="col-sm-2 control-label"><fmt:message key="team.teamLeader"/></form:label>
                <div class="col-sm-10">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><fmt:message key="user.firstName"/></th>
                                <th><fmt:message key="user.lastName"/></th>
                                <th><fmt:message key="user.email"/></th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <tr>
                                <td><c:out value="${teamUpdate.teamLeader.firstName}"/></td>
                                <td><c:out value="${teamUpdate.teamLeader.lastName}"/></td>
                                <td><c:out value="${teamUpdate.teamLeader.email}"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div> --%>

            <div class="form-group ${members_error?'has-error':''}">
                <form:label path="members" cssClass="col-sm-2 control-label"><fmt:message key="team.members"/></form:label>
                <div class="col-sm-10">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><fmt:message key="user.firstName"/></th>
                                <th><fmt:message key="user.lastName"/></th>
                                <th><fmt:message key="user.email"/></th>
                                <th><fmt:message key="remove"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${teamUpdate.members}" var="u">
                                <tr>
                                    <td><c:out value="${u.firstName}"/></td>
                                    <td><c:out value="${u.lastName}"/></td>
                                    <td><c:out value="${u.email}"/></td>
                                    <td>
                                        <form method="post" action="${pageContext.request.contextPath}/team/update/${teamUpdate.id}/removeUser/${u.id}">
                                            <td class="col-xs-1 text-center">
                                                <button class="btn btn-default" type="submit">
                                                    <span class="sr-only"><fmt:message key="remove"/></span>
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                                </button>
                                            </td>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

    <%--    <div class="form-group ${userEmail_error?'has-error':''}">
                <form path="userEmail" cssClass="col-sm-2 control-label"><fmt:message key="team.addUser"/></form>
                <div class="col-sm-10">
                    <form:input path="userEmail" cssClass="form-control"/>
                    <form:errors path="userEmail" cssClass="help-block"/>
                </div>
            </div>
    --%>
            <button class="btn btn-primary updateBtn center-block allow-vertical-space" type="submit"><fmt:message key="submit"/></button>
        </form:form>
    </jsp:attribute>
</own:masterpage>
