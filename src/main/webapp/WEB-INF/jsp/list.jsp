<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ztoczko
  Date: 14.04.2021
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warsztat 3</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="../../theme/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../theme/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="../includes/sidebar.jsp"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column bg-gray-200">

        <!-- Main Content -->
        <div id="content">

            <jsp:include page="../includes/topbar.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
                </div>

                <!-- Content Row -->
                <div class="row flex-row">

                    <div class="col-12">
                        <c:url value="/user/edit" var="edit">
                            <c:param name="id" value="${user.id}"/>
                            <c:param name="fromPage" value="${param.fromPage}"/>
                            <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                                <c:param name="search" value="${param.search}"/>
                            </c:if>
                        </c:url>
                        <c:url value="/user/delete" var="delete">
                            <c:param name="id" value="${user.id}"/>
                            <c:param name="fromPage" value="${param.fromPage}"/>
                            <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                                <c:param name="search" value="${param.search}"/>
                            </c:if>
                        </c:url>
                        <c:url value="/user/list" var="returna">
                            <c:param name="page" value="${param.fromPage}"/>
                            <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                                <c:param name="search" value="${param.search}"/>
                            </c:if>
                        </c:url>
                        <table class="table-hover table bg-white">
                            <div class="text-center bg-gray-100 text-primary p-2">
                                <h4 class="p-0 m-0"><b>
                                    <c:choose>
                                        <c:when test="${param.search == null || param.search.trim().isEmpty()}">
                                            Lista u??ytkownik??w
                                        </c:when>
                                        <c:otherwise>
                                            Wyniki wyszukiwania
                                        </c:otherwise>

                                    </c:choose>

                                </b></h4>
                            </div>
                            <thead>                 <!-- nag????wek tabeli -->
                            <tr>
                                <th>id</th>
                                <th>nazwa u??ytkownika</th>
                                <th>adres e-mail</th>
                                <th>akcja</th>
                            </tr>
                            </thead>

                            <c:forEach var="user" items="${list}"> <!-- listowanie u??ytkownik??w-->
                                <c:url value="/user/show" var="show">
                                    <c:param name="id" value="${user.id}"/>
                                    <c:param name="fromPage" value="${param.page}"/>
                                    <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                                        <c:param name="search" value="${param.search}"/>
                                    </c:if>
                                </c:url>
                                <c:url value="/user/edit" var="edit">
                                    <c:param name="id" value="${user.id}"/>
                                    <c:param name="fromPage" value="${param.page}"/>
                                    <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                                        <c:param name="search" value="${param.search}"/>
                                    </c:if>
                                </c:url>

                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <button onclick="window.location.href='${show}'"
                                                class="btn-primary"><i
                                                class="fas fa-search"></i> wy??wietl
                                        </button>
                                        <button onclick="window.location.href='${edit}'"
                                                class="btn-primary"><i
                                                class="fas fa-pen"></i> edytuj
                                        </button>
                                        <button type="button" data-toggle="modal"
                                                data-target="${"#deleteModal".concat(user.id)}" class="btn-primary"><i
                                                class="fas fa-trash"></i> usu??
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                        <c:if test="${list.size() == 0}">
                            Brak u??ytkownik??w do wy??wietlenia
                        </c:if>
                        <div class="text-left">     <%-- komunikat pod tablic?? --%>
                            <h5>
                                <c:choose>
                                    <c:when test="${param.msg.equals(\"addFail\")}">
                                        B????d po????czenia z baz?? danych - u??ytkownik nie zosta?? dodany
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"addSuccess\")}">
                                        U??ytkownik zosta?? pomy??lnie dodany do bazy danych
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"editFail\")}">
                                        B????d po????czenia z baz?? danych - u??ytkownik nie zosta?? zmodyfikowany
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"editSuccess\")}">
                                        U??ytkownik zosta?? pomy??lnie zmodyfikowany
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"deleteFail\")}">
                                        B????d po????czenia z baz?? danych - u??ytkownik nie zosta?? usuni??ty
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"deleteSuccess\")}">
                                        U??ytkownik zosta?? pomy??lnie usuni??ty
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"deleteIdInvalid\")}">
                                        Niepoprawny numer id u??ytkownika - u??ytkownik nie zosta?? usuni??ty
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"editIdInvalid\")}">
                                        Niepoprawny numer id u??ytkownika - brak mo??liwo??ci edycji u??ytkownika
                                    </c:when>
                                    <c:when test="${param.msg.equals(\"showIdInvalid\")}">
                                        Niepoprawny numer id u??ytkownika - brak mo??liwo??ci wy??wietlenia u??ytkownika
                                    </c:when>
                                </c:choose>
                            </h5>
                        </div>
                        <div class="text-center">
                            <h5>

                                <c:forEach begin="1" end="${maxpage}" var="pageNumber">

                                    <c:choose>
                                        <c:when test="${pageNumber == page}">
                                            ${pageNumber}
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${"list?page=".concat(pageNumber).concat(param.search == null || param.search.isEmpty() ? "" : "&search=".concat(param.search))}">${pageNumber}</a>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>

                            </h5>
                        </div>
                    </div>
                </div>

                <!-- button Row -->

                <div class="row">

                    <c:if test="${!empty param.search && !param.search.trim().isEmpty()}">
                        <button onclick="window.location.href='list'" class="btn-primary btn-lg m-4"
                                style="min-width: 10vw"><i
                                class="fas fa-arrow-circle-left"></i>&nbsp&nbsp&nbsp
                            Powr??t
                        </button>
                    </c:if>

                    <button onclick="window.location.href='${'add'.concat("?fromPage=".concat(page)).concat(param.search == null || param.search.trim().isEmpty() ? "" : "&search=".concat(param.search))}'"
                            class="btn-primary btn-lg m-4" style="min-width: 10vw"><i class="fas fa-plus-circle"></i>&nbsp&nbsp&nbsp
                        Dodaj u??ytkownika
                    </button>

                </div>
                <!-- /button List -->

                <c:forEach var="user" items="${list}">
                    <c:url value="/user/delete" var="delete">
                        <c:param name="id" value="${user.id}"/>
                        <c:param name="fromPage" value="${param.page}"/>
                        <c:if test="${param.search != null && !param.search.trim().isEmpty()}">
                            <c:param name="search" value="${param.search}"/>
                        </c:if>
                    </c:url>
                    <!-- Modal -->
                    <div class="modal fade" id="${"deleteModal".concat(user.id)}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-body" style="text-align: center">
                                    <h5>Czy na pewno chcesz usun???? u??ytkownika o id ${user.id}?</h5>
                                </div>
                                <div class="modal-footer d-flex justify-content-around">
                                    <button onclick="window.location.href='${delete}'"
                                            type="button" class="btn btn-primary btn-lg" style="min-width: 10vw">Tak
                                    </button>
                                    <button type="button" class="btn btn-secondary btn-lg" data-dismiss="modal"
                                            style="min-width: 10vw">Nie
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->
        <jsp:include page="../includes/footer.jsp"/>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="../../theme/vendor/jquery/jquery.min.js"></script>
<script src="../../theme/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../theme/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../../theme/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<%--<script src="../../theme/vendor/chart.js/Chart.min.js"></script>--%>

<!-- Page level custom scripts -->
<%--<script src="../../theme/js/demo/chart-area-demo.js"></script>--%>
<%--<script src="../../theme/js/demo/chart-pie-demo.js"></script>--%>

</body>

</html>