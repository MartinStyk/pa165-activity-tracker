<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Add Members">
    <jsp:attribute name="body">

        <a href="${pageContext.request.contextPath}/teams/detail/${currentTeam.id}" class="btn btn-default" role="button">
            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
            <fmt:message key="back"/>
        </a>

        <div class="page-header">
            <h1>
                <fmt:message key="team.addUser"/>
            </h1>
        </div>

        <form:form method="POST"
                   action="${pageContext.request.contextPath}/teams/addUsers/${currentTeam.id}"
                   acceptCharset=""
                   modelAttribute="users"
                   cssClass="form-horizontal">

            <div class="form-group ${addUsers_error?'has-error':''}">
                <div class="col-sm-12">
                                       
                    <form:select multiple="true"  class="form-control" path="members" items="${users.members}" itemLabel="email" itemValue="id"/>
                    <form:errors path="members" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary createBtn center-block allow-vertical-space" type="submit"><fmt:message key="submit"/></button>
        </form:form>
<%--        <div class="form-group ${addUsers_error?'has-error':''}">
            <div class="col-sm-10">
                <select name="members" >
                    <options items="">
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}" label="${user.email}"
                    </c:forEach>
                </select>                  
                <input type="submit" value="Submit" />
                
                
                                <form:select path="id" cssClass="form-control">
                                        <c:forEach items="${users}" var="user">
                                            <form:option value="${user.id}"> ${user.email} </form:option>
                                        </c:forEach>
                                    </form:select>
                                  <form:errors path="users" cssClass="help-block"/>
            </div>
        </div>
--%>
    </jsp:attribute>
</own:masterpage>
