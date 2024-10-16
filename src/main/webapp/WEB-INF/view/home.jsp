<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/bootstrap/fontawesome/css/all.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/bootstrap/css/MockProject.css" />"
	rel="stylesheet">
</head>

<body>
	<div class="container-fluid">
        <div class="row pt-2 pb-2" style="border-bottom: 2px solid;">
            <h4 class="">Quản lý cơ sở vật chất</h4>
        </div>
        <div class="row" style="background-color: rgb(240, 240, 240);">
            <ul class="nav nav-tabs ml-4 mt-2" id="myTab" role="tablist">
                <li class="nav-item rounded-top">
                    <a class="nav-link text-dark" id="home-tab" data-toggle="tab" 
                    href="${pageContext.request.contextPath}/tinhtrangthietbi/displaylist" role="tab"
                        aria-controls="home" aria-selected="false">Tình trạng thiết bị cơ sở vật chất</a>
                </li>
                <li class="nav-item rounded-top" style="border-left: 2px solid white; border-right: 2px solid white;">
                    <a class="nav-link text-dark" id="profile-tab" data-toggle="tab" 
                    href="${pageContext.request.contextPath}/phieunhapkho/forminsert" role="tab"
                        aria-controls="profile" aria-selected="false">Phiếu nhập kho</a>
                </li>
                <li class="nav-item rounded-top">
                    <a class="nav-link text-dark" id="messages-tab" data-toggle="tab" 
                    href="${pageContext.request.contextPath}/hienthi/formdisplay" role="tab"
                        aria-controls="messages" aria-selected="false">Xem thông tin nhập kho</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tab-content pl-3 pr-3">
            <div class="tab-pane" id="home" role="tabpanel" aria-labelledby="home-tab">
            </div>
            <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            </div>
            <div class="tab-pane" id="messages" role="tabpanel" aria-labelledby="messages-tab">
            </div>
        </div>


	<!-- <script src="resources/bootstrap/js/jquery.min.js"></script>
	<script src="resources/bootstrap/js/popper.min.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/bootstrap/validate/ValidatePhieuNhapKho.js"></script>
	<script src="resources/bootstrap/validate/ValidateTinhTrangThietBi.js"></script>
	<script src="resources/bootstrap/validate/HienThiThongTin.js"></script> -->

</body>

</html>