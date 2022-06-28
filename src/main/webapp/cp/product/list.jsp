<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/cp/layout/head.jsp"%>
    <style>
        .btn.btn-outline-primary {
            margin-right: 10px;
            padding-inline: 0.1rem 0.4rem;
            border: none;
        }
        .btn:hover {
            display: inline-flex;
        }
        .btn {
            padding: 0px 0px;
        }
        .btn-group {
            text-align: center;
        }
        .user-hover {
            display: none;
        }
        .user-hover:hover {
            display: block;
        }

    </style>

</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">


    <!-- Navigation Bar-->
    <header id="topnav">
        <!-- Topbar Start -->
        <div class="navbar-custom">
            <div class="container-fluid">
                <ul class="list-unstyled topnav-menu float-right mb-0">

                    <li class="dropdown notification-list">
                        <!-- Mobile menu toggle-->
                        <a class="navbar-toggle nav-link">
                            <div class="lines">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </a>
                        <!-- End mobile menu toggle-->
                    </li>

                    <li class="d-none d-sm-block">

                    </li>


                    <li class="dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <img src="/assets/images/avar.jpg" alt="user-image" class="rounded-circle">
                            <span>Phuoc Nguyen</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                            <!-- item-->
                            <div class="dropdown-header noti-title">
                                <h6 class="text-overflow m-0">Welcome !</h6>
                            </div>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="fe-user"></i>
                                <span>Profile</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="fe-settings"></i>
                                <span>Settings</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="fe-lock"></i>
                                <span>Lock Screen</span>
                            </a>

                            <div class="dropdown-divider"></div>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="fe-log-out"></i>
                                <span>Logout</span>
                            </a>

                        </div>
                    </li>

                    <li class="dropdown notification-list">
                        <a href="javascript:void(0);" class="nav-link right-bar-toggle waves-effect waves-light">
                            <i class="fe-settings noti-icon"></i>
                        </a>
                    </li>
                </ul>


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
                                <i class="fa-solid fa-users"></i>User
                            </a>
                        </li>

                        <li class="has-submenu">
                            <a href="/cp/product">
                                <i class="fa-solid fa-p"></i>Product
                            </a>
                        </li>

                        <li class="has-submenu">
                            <a href="#"> <i class="fa-brands fa-opera"></i> Order </a>
                            <ul class="submenu">
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="#">
                                <i class="fa-thin fa-address-book"></i> Sign Up </a>
                            <ul class="submenu">
                            </ul>
                        </li>

                        <li class="has-submenu">
                            <a href="#">
                                <i class="fa-solid fa-right-to-bracket"></i> Login </a>
                            <ul class="submenu">
                            </ul>
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
            <li class="d-none d-sm-block">
                <form method="get" class="app-search">
                    <div class="app-search-box">
                        <div class="input-group " style="width: 300px; margin-left: 80px">
                            <input name="search" type="text" class="form-control" placeholder="Search...">
                            <div class="input-group-append">
                                <button class="btn" type="submit">
                                    <i class="fe-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </li>

            <!-- Start Content-->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Avatar</th>
                                <th>Name Product</th>
                                <th>Price Product</th>
                                <th>Quantity Product</th>
                                <th>Type Product</th>
                                <th>Descripton Product</th>
                                <th>Create Date</th>
                                <th>Update Date</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope['productList']}" var="item">
                                <tr>
                                    <td><img src="${item.getImage()}" width="30px" height="30px"
                                             style="border-radius:50% " alt=""></td>
                                    <td>${item.getNameProduct()}</td>
                                    <td>${item.getPriceProduct()}</td>
                                    <td>${item.getQuantityProduct()}</td>
                                    <td>${item.getTypeProduct()}</td>
                                    <td>${item.getDescription()}</td>
                                    <td>${item.getCreatedAT()}</td>
                                    <td>
                                        <c:if test="${item.getUpdatedAT() == 'null'}">
                                            <p></p>
                                        </c:if>
                                        <c:if test="${item.getUpdatedAT() != 'null'}">
                                            ${item.getUpdatedAT()}
                                        </c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group" style="font-size: 14px" >
                                            <div class="btn btn-outline-primary">
                                                <a href="/cp/product?action=create">
                                                    <i class="fa-solid fa-user-plus" style="color: #00ff80"></i>
                                                    -                               <p class="user-hover">Add product</p>
                                                </a>
                                            </div>
                                            <div class="btn btn-outline-primary">
                                                <a href="/cp/product?action=edit&id=${item.getProductId()}">
                                                    <i class="fa-solid fa-pen-to-square" style="color: rebeccapurple"></i>
                                                    <p class="user-hover">Edit product</p>
                                                </a>
                                            </div>
                                            <div class="btn btn-outline-primary">
                                                    <a href="/cp/product?action=remove&id=${item.getProductId()}">
                                                    <i class="fa-solid fa-trash-can" style="color:red;"></i>
                                                    <p class="user-hover">Remove product</p>
                                                </a>
                                            </div>
                                                <%--<a href="/cp/user?action=edit&id=${item.getUserId()}">Edit</a>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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