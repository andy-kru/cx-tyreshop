<%@ page trimDirectiveWhitespaces="true" %>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>

<link href="../../../../_ui/responsive/common/css/callbackaddon.css" rel="stylesheet">
<script src="../../../../_ui/responsive/common/js/callbackaddon.js"></script>
<meta http-equiv="content-type" content="text/html; charset=cp1251">
<button class="open-button" onclick="openForm()" id="openButton"><spring:message code="openB"/></button>
<div class="form-popup" id="myForm">
    <form action="javascript:submitForm()" class="form-container">
        <meta http-equiv="content-type" content="text/html; charset=cp1251">
        <span id="headText"><h1><spring:message code="headText"/></h1></span>
        <label for="inputName" id="textForInputName"><b><spring:message code="customerName"/></b><br></label>
        <input type="text" placeholder="<spring:message code="inputName"/>" id="inputName" required>
        <label for="inputPhone"  id="textForInputPhone"><b><spring:message code="customerPhone"/></b><br></label>
        <input type="text" placeholder="+375 29 1234567" id="inputPhone" required>
        <span id="confirmationText" style="display: none"><h2><spring:message code="confirmationText"/></h2><br></span>
        <label for="inputComment" id="textForInputComment"><b><spring:message code="customerComment"/></b></label>
        <input type="text" placeholder="<spring:message code="inputComment"/>" id="inputComment" >
        <button type="submit"  class="btn" id="confirmButton"><spring:message code="confirmB"/></button>
        <button type="button" class="btn cancel" onclick="closeForm()"><spring:message code="closeB"/></button>
    </form>
</div>



