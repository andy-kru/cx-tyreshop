<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="tab-pane active">
        <%--@elvariable id="quickTyreSearchAttributes" type="com.reply.tyreshop.core.dto.QuickTyreSearchAttributes"--%>
        <form:form action="quicktyresearch" method="POST" class="form-container col-sm-offset-3" modelAttribute="quickTyreSearchAttributes">
            <label class="text-center text-uppercase h4 col-sm-1" id="param"><spring:message code="tyreQuickSearchTitle"/></label>
            <c:forEach var="attribute" items="${dataList}">
                <div class="form-group col-sm-1">
                    <c:set var="attributeKey" value="${attribute.code}"/>
                    <label class="control-label">${attributeKey}</label>
                    <form:select path="redirectAttributesMap['${attributeKey}']" class="form-control" multiple="false">
                        <form:option value="" label="-"/>
                        <c:forEach var="attributeValue" items="${attribute.attributesList}">
                            <form:option value="${attributeValue.code}">${attributeValue.code}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </c:forEach>
            <div>
                <div class="button btn">
                    <form:button type="submit" class="btn btn-lg btn-default" id="confirmButton"><spring:message code="quickTyreSearchButton"/></form:button>
                </div>
            </div>
        </form:form>
    </div>
</body>

