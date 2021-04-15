<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-beer"></i>
        </div>
        <div class="sidebar-brand-text mx-3">CL ADMIN</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <span class="nav-link" style="text-align: center">Witaj <c:out
                value="${(pageContext.request.getSession(false).getAttribute(\"user\")).username}"
                default="Gościu"/></span>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Nav Item - Lists -->
    <li class="nav-item">
        <a class="nav-link" href="list">
            <i class="fas fa-fw fa-home"></i>
            <span>Lista użytkowników</span></a>
    </li>

    <!-- Nav Item - Settings -->
    <li class="nav-item">
        <a class="nav-link" href="adminSettings">
            <i class="fas fa-fw fa-toolbox"></i>
            <span>Ustawienia</span></a>
    </li>

    <!-- Nav Item - Logout -->
    <li class="nav-item">
        <a class="nav-link" href="logout">
            <i class="fas fa-fw fa-power-off"></i>
            <span>Wyloguj</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->
