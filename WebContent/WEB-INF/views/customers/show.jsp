<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${customer != null}">
                <h2>顧客情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>顧客名</th>
                            <td><c:out value="${customer.customer_name}" /></td>
                        </tr>
                        <tr>
                            <th>顧客情報</th>
                            <td>
                                <pre><c:out value="${customer.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録者</th>
                            <td><c:out value="${customer.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${customer.created_at}" pattern="yyyy-MM-dd HH:mm::ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${customer.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == customer.employee.id}">
                    <p><a href="<c:url value="/customers/edit?id=${customer.id}" />">この顧客情報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

       <p><a href="<c:url value="/customers/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>