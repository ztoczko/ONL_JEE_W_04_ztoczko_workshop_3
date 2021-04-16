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
                <div class="row">

                    <div class="col-3"></div>

                    <div class="col-6">
                        <%--                        <c:set var="action" value="${\"edit\".concat.\"?id=\".concat(param.id).concat.("&fromPage=").concat(param.fromPage).concat((param.search == null || param.search.trim().isEmpty()) ? "" : "&search=".concat(param.search))}"--%>
                            <c:url value="/user/edit" var="editSubmit">
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
                        <form class="form-group"
                              action="${editSubmit}"
                              method="post">
                            <table class="table-hover table bg-white">
                                <div class="text-center bg-gray-100 text-primary p-2" style="width: 100%">
                                    <h4 class="p-0 m-0"><b>Edytuj użytkownika</b></h4>
                                </div>
                                <tr>
                                    <th>nazwa użytkownika</th>
                                    <td>
                                        <input style="border-width: 0; width: 100%; ${(!empty error && error.contains("name")) ? "background-color: lightsalmon" : ""}"
                                               value="<c:out value="${name}" default=""/>" type="text" name="name"
                                               placeholder="<c:out value="${user.username}"/>">
                                        <c:if test="${!empty error && error.contains(\"name\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Nazwa użytkownika musi zaczynać się od litery, zawierać przynajmniej 4 znaki i składać się z liter, cyfr, myślników i podkresleń</span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>

                                    <th>e-mail</th>
                                    <td>
                                        <input style="border-width: 0; width: 100%; ${(!empty error && error.contains("email")) ? "background-color: lightsalmon" : ""}"
                                               type="email" name="email" value="<c:out value="${email}" default=""/>"
                                               placeholder="<c:out value="${user.email}"/>">
                                        <c:if test="${!empty error && error.contains(\"emailInvalid\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Wprowadzony tekst nie jestem adresem e-mail lub jest zbyt długi</span>
                                        </c:if>
                                        <c:if test="${!empty error && error.contains(\"emailExists\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Użytkownik o tym adresie e-mail już istnieje</span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>hasło</th>
                                    <td><input
                                            style="border-width: 0; width: 100%; ${(!empty error && error.contains("password")) ? "background-color: lightsalmon" : ""}"
                                            type="password" name="password1"
                                            value="<c:out value="${password1}" default=""/>">
                                        <c:if test="${!empty error && error.contains(\"passwordInvalid\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Hasło musi składać się z przynajmniej 8 znaków i nie może zawierać znaków białych</span>
                                        </c:if>
                                        <c:if test="${!empty error && error.contains(\"passwordsDontMatch\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Wprowadzone hasła nie są identyczne</span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Powtórz hasło</th>
                                    <td><input
                                            style="border-width: 0; width: 100%; ${(!empty error && error.contains("password")) ? "background-color: lightsalmon" : ""}"
                                            type="password" name="password2"
                                            value="<c:out value="${password2}" default=""/>">
                                        <c:if test="${!empty error && error.contains(\"passwordsDontMatch\")}">
                                            <span style="position: relative; text-align: justify-all; width: 100%"> Wprowadzone hasła nie są identyczne</span>
                                        </c:if>
                                    </td>
                                </tr>

                            </table>


                    </div>
                    <div class="col-3"></div>
                </div>

                <!-- button Row -->

                <div class="row">

                    <div class="col-3"></div>
                    <div class="col-6">
                        <button type="button"
                                onclick="window.location.href='${returna}'"
                                class="btn-primary btn-lg m-4" style="min-width: 10vw"><i
                                class="fas fa-arrow-circle-left"></i>&nbsp&nbsp&nbsp
                            Powrót
                        </button>

                        <button type="submit" class="btn-primary btn-lg m-4" style="min-width: 10vw"><i
                                class="fas fa-save"></i>&nbsp&nbsp&nbsp
                            Zapisz
                        </button>
                        </form>
                    </div>


                    <div class="col-3"></div>

                </div>
                <!-- /button List -->

                <%--end of EDIT--%>

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