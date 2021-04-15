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

                <div class="row">

                    <!-- Content Column -->
                    <div class="col-3"></div>
                    <div class="col-6">
                        <table class="table-hover table bg-white">
                            <div class="text-center bg-gray-100 text-primary p-2" style="width: 100%">
                                <h4 class="p-0 m-0"><b>Szczegóły użytkownika</b></h4>
                            </div>
                            <tr>
                                <th>id</th>
                                <td>${user.id}</td>
                            </tr>
                            <tr>
                                <th>nazwa użytkownika</th>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <th>e-mail</th>
                                <td>${user.email}</td>
                            </tr>

                        </table>
                    </div>
                    <div class="col-3"></div>
                </div>

                <div class="row">

                    <div class="col-3"></div>
                    <div class="col-6">
                        <button type="button"
                                onclick="window.location.href='${"list".concat("?page=".concat(param.fromPage)).concat(param.search == null || param.search.trim().isEmpty() ? "" : "&search=".concat(param.search))}'"
                                class="btn-primary btn-lg m-4" style="min-width: 10vw"><i
                                class="fas fa-arrow-circle-left"></i>&nbsp&nbsp&nbsp
                            Powrót
                        </button>
                        <button onclick="window.location.href='${"edit?id=".concat(user.id).concat(param.fromPage == null ? "" : "&fromPage=".concat(param.fromPage)).concat((param.search == null || param.search.trim().isEmpty()) ? "" : "&search=".concat(param.search))}'"
                                class="btn-primary btn-lg m-4" style="min-width: 10vw"><i
                                class="fas fa-pen"></i> Edytuj
                        </button>
                        <button type="button" data-toggle="modal" data-target="#deleteModal"
                                class="btn-primary btn-lg m-4" style="min-width: 10vw"><i
                                class="fas fa-trash"></i> Usuń
                        </button>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-body" style="text-align: center">
                                    <h5>Czy na pewno chcesz usunąć użytkownika o id ${user.id}?</h5>
                                </div>
                                <div class="modal-footer d-flex justify-content-around">
                                    <button onclick="window.location.href='${"delete?id=".concat(user.id).concat(param.fromPage == null ? "" : "&fromPage=".concat(param.fromPage)).concat((param.search == null || param.search.trim().isEmpty()) ? "" : "&search=".concat(param.search))}'"
                                            type="button" class="btn btn-primary btn-lg" style="min-width: 10vw">Tak
                                    </button>
                                    <button type="button" class="btn btn-secondary btn-lg" data-dismiss="modal"
                                            style="min-width: 10vw">Nie
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-3"></div>

                </div>
                <!-- /Show User-->

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