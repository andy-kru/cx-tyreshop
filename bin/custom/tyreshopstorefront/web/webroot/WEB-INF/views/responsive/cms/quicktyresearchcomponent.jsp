<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<body>
    <div class="tab-pane active">
        <form action="javascript:submitForm()" class="form-container col-sm-offset-1" >
            <label class="text-center text-uppercase h4 col-sm-1">${quickTyreSearchData.tyreQuickSearchTitle}</label>
            <div class="form-group col-sm-1">
                <label class="control-label">${quickTyreSearchData.tyreWidth}</label>
                <select class="form-control" id="tyreWidth" name="width" >
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreWidth" items="${Width}">
                        <option value="${tyreWidth.code}">${tyreWidth.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-1">
                <label class="control-label">${quickTyreSearchData.tyreHeight}</label>
                <select class="form-control" id="tyreHeight">
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreHeight" items="${Height}">
                        <option value="${tyreHeight.code}">${tyreHeight.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-1">
                <label class="control-label">${quickTyreSearchData.tyreDiameter}</label>
                <select class="form-control" id="tyreDiameter" name="diameter">
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreDiameter" items="${Diameter}">
                        <option value="${tyreDiameter.code}">${tyreDiameter.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-1">
                <label class="control-label">${quickTyreSearchData.tyreSeason}</label>
                <select class="form-control" id="tyreSeason" name="season">
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreSeason" items="${Season}">
                        <option value="${tyreSeason.code}">${tyreSeason.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-2">
                <label class="control-label">${quickTyreSearchData.tyreLoadIndex}</label>
                <select class="form-control" id="tyreLoadIndex" name="loadindex">
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreLoadIndex" items="${LoadIndex}">
                        <option value="${tyreLoadIndex.code}">${tyreLoadIndex.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-2">
                <label class="control-label">${quickTyreSearchData.tyreSpeedIndex}</label>
                <select class="form-control" id="tyreSpeedIndex" name="speedindex">
                    <option value="">&mdash;</option>
                    <c:forEach var="tyreSpeedIndex" items="${SpeedIndex}">
                        <option value="${tyreSpeedIndex.code}">${tyreSpeedIndex.code}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <div class="button btn">
                    <button type="submit" class="btn btn-lg btn-default" id="confirmButton">${quickTyreSearchData.quickTyreSearchButton}</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        function submitForm() {
            $.ajax({
                url : 'view/QuickTyreSearchComponentController/quicktyresearch',
                async: false,
                data: {
                    Width: document.getElementById("tyreWidth").value,
                    Height: document.getElementById("tyreHeight").value,
                    Diameter: document.getElementById("tyreDiameter").value,
                    Season: document.getElementById("tyreSeason").value,
                    LoadIndex: document.getElementById("tyreLoadIndex").value,
                    SpeedIndex: document.getElementById("tyreSpeedIndex").value
                },
                success : function(data) {
                    window.location.href = data;
                }
            });
        }
    </script>
</body>

