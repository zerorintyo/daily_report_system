<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="customer_name">取引先名</label><br />
<input type="text" name="customer_name" value="${costomer.costomer_name}" />
<br /><br />

<label for="content">顧客情報</label><br />
<textarea name="content" rows="10" cols="50">${customer.content}</textarea>
<br /><br />

<label for="name">登録者</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>