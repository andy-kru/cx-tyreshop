<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="tab-pane active">
        <form:form action="quicktyresearch" method="POST" class="form-container col-sm-offset-3">
            <label class="text-center text-uppercase h4 col-sm-1"><spring:message code="tyreQuickSearchTitle"/></label>
            <c:forEach var="attribute" items="${dataList}">
                <div class="form-group col-sm-1">
                    <label class="control-label">${attribute.getClassificationAttribute().name}</label>
                    <select class="form-control" name=${attribute.getClassificationAttribute().code}>
                        <option value="">&mdash;</option>
                        <c:forEach var="attributeValue" items="${attribute.getAttributeValues()}">
                            <option value="${attributeValue.code}">${attributeValue.code}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
            <div>
                <div class="button btn">
                    <button type="submit" class="btn btn-lg btn-default" id="confirmButton"><spring:message code="quickTyreSearchButton"/></button>
                </div>
            </div>
        </form:form>
    </div>
</body>

