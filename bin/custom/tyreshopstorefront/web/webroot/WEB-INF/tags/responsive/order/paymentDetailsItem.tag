<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<div class="label-order">
    <spring:theme code="text.account.paymentType"/>
</div>
<div class="value-order">
        ${fn:escapeXml(order.paymentModeCode.toUpperCase())}
        <c:if test="${not empty order.bankTransferReference}">
            (${order.bankTransferReference})
        </c:if>
            <c:if test="${not empty order.commonPaymentInfo.cardAccountNumber}">
                (${order.commonPaymentInfo.cardAccountNumber})
            </c:if>

</div>

