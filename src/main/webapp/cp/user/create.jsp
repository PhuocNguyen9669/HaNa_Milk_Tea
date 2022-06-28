<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/cp/layout/head.jsp"%>

</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">


    <!-- Navigation Bar-->
    <header id="topnav">
        <!-- Topbar Start -->
        <div class="navbar-custom">
            <div class="container-fluid">
                <%@include file="/cp/layout/navbar/topnav-menu-right.jsp"%>

                <!-- LOGO -->
                <%@include file="/cp/layout/navbar/logo-box.jsp"%>

            </div>
        </div>
        <!-- end Topbar -->

        <div class="topbar-menu">
            <div class="container-fluid">
                <div id="navigation">
                    <!-- Navigation Menu-->
                    <ul class="navigation-menu">

                        <li class="has-submenu">
                            <a href="/cp/user">
                                <i class="fe-airplay"></i>User
                            </a>
                        </li>

                        <li class="has-submenu">
                            <a href="#">
                                <i class="fe-briefcase"></i>Product
                            </a>
                            <ul class="submenu megamenu">
                                <li>
                                </li>
                                <li>

                                </li></ul>


                        <li class="has-submenu">
                            <a href="#"> <i class="fe-target"></i> Order </a>
                            <ul class="submenu">
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="#">
                                <i class="fa-thin fa-address-book"></i> Sign up </a>
                        </li>

                        <li class="has-submenu">
                            <a href="#">
                                <i class="fe-layers"></i> Login </a>
                        </li>
                    </ul>
                    <!-- End navigation menu -->

                    <div class="clearfix"></div>
                </div>
                <!-- end #navigation -->
            </div>
            <!-- end container -->
        </div>
        <!-- end navbar-custom -->

    </header>
    <!-- End Navigation Bar-->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-6">
                        <div>
                            <form method="post">
                                <div class="mb-3">
                                    <label for="userName" class="form-label">User Name</label>
                                    <input type="text" required class="form-control" id="userName" name="userName">
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" required class="form-control" id="password" name="password">
                                </div>
                                <div class="mb-3">
                                    <label for="full_name" class="form-label">Full name</label>
                                    <input type="text" required class="form-control" id="full_name" name="full_name">
                                </div>
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone</label>
                                    <input type="tel" required class="form-control" id="phone" name="phone">
                                </div>
                                <div class="mb-3">
                                    <label for="age" class="form-label">Age</label>
                                    <input type="number" required class="form-control" id="age" name="age">
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="text" required class="form-control" id="email" name="email">
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" required class="form-control" id="address" name="address">
                                </div>
                                <div class="mb-3">
                                        <label for="role" class="form-label">Role
                                        <span class="text-danger">*</span>
                                        </label>
                                        <select required class="form-control" name="role" id="role">
                                            <option value="ADMIN">Admin</option>
                                            <option value="USER">User</option>
                                        </select>
                                </div>
                                <div class="mb-3">
                                    <label for="createdAT" class="form-label">CreatedAT</label>
                                    <input type="datetime-local" required class="form-control" id="createdAT" name="createdAT">
                                </div>
                                <div class="mb-3">
                                    <label for="img" class="form-label">Image</label>
                                    <input type="text" required class="form-control" id="img" name="img">
                                </div>
                                <%--                        <div class="mb-3">--%>
                                <%--                            <label for="cityId" class="form-label">City</label>--%>
                                <%--                            <select class="form-control" name="cityId" id="cityId">--%>
                                <%--                                <c:forEach items="${requestScope['cityList']}" var="item">--%>
                                <%--                                    <option value="${item.getId()}">${item.getName()}</option>--%>
                                <%--                                </c:forEach>--%>
                                <%--                            </select>--%>
                                <%--                        </div>--%>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-outline-primary">Add user</button>
                                </div>
                            </form>
                        </div>

                        <div class="footer" style="margin-left: 200px">
                            <c:if test="${requestScope['success'] == true}">
                                <ul class="success">
                                    <li>Thêm mới thành công</li>
                                </ul>
                            </c:if>
                            <c:if test="${!requestScope['errors'].isEmpty()}">
                                <ul class="error">
                                    <c:forEach items="${requestScope['errors']}" var="item">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-6">

                    </div>
                </div>
                <!-- start page title -->

            </div> <!-- end container-fluid -->

        </div> <!-- end content -->



        <!-- Footer Start -->
        <%@include file="/cp/layout/footer.jsp"%>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->

<!-- Right Sidebar -->
<%@include file="/cp/layout/rightbar.jsp"%>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<a href="javascript:void(0);" class="right-bar-toggle demos-show-btn">
    <i class="mdi mdi-settings-outline mdi-spin"></i> &nbsp;Choose Demos
</a>

<%@include file="/cp/layout/script/script.jsp"%>

</body>
</html>