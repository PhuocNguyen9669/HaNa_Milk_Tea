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
                <%@include file="/cp/layout/navbar/topnav-menu-right.jsp" %>
                <form method="get">
                    <div class="input-group input-group-sm">
                        <input name="search" type="text" class="form-control">
                        <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                        </div>
                    </div>
                    <a href="/cp" class="btn btn-success btn-sm ml-3" >
                        <i class="fa fa-shopping-cart"> List product</i>
                        <span class="badge badge-light">Search</span>
                    </a>
                </form>
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
                            <a href="/cp/product">
                                <i class="fe-briefcase"></i>Product
                            </a>
                        <li class="has-submenu">
                            <a href="/cp/orders"> <i class="fe-target"></i> Order </a>
                            <ul class="submenu">
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="/cp/signup">
                                <i class="fa-thin fa-address-book"></i> Sign up </a>
                        </li>

                        <li class="has-submenu">
                            <a href="/cp/login">
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