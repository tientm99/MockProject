<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="vi">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/bootstrap/fontawesome/css/all.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/bootstrap/css/MockProject.css" />" rel="stylesheet">
	
	<!-- Toastr CSS -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">
	
	<!-- jQuery -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- Toastr JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.3/xlsx.full.min.js"></script>

	<style type="text/css">
		.form-group1 {
			margin-bottom: 0px;
		}
		
		h1 {
			color: #333;
		}
		
		#dateList {
			list-style-type: none;
			padding: 0;
		}
		
		.dateDetail {
			background-color: #f4f4f4;
			margin: 5px 0;
			padding: 10px;
			border: 1px solid #ddd;
			border-radius: 4px;
			transition: background-color 0.3s, transform 0.3s;
		}
		
		.dateDetail:hover {
			background-color: #e0e0e0;
			transform: scale(1.02);
			cursor: pointer;
		}
		
		.scrollable-ulli {
			overflow-y: auto;
			display: block;
		}
		
		span {
			color: red;
		}
		
		.selected {
			background-color: #d3d3d3;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row pt-2 pb-2" style="border-bottom: 2px solid;">
			<h4 class="">Quản lý cơ sở vật chất</h4>
		</div>
		<div class="row" style="background-color: rgb(240, 240, 240);">
			<ul class="nav nav-tabs ml-4 mt-2" id="myTab" role="tablist">
				<li class="nav-item rounded-top">
					<a class="nav-link text-dark"
						id="home-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/tinhtrangthietbi/displaylist"
						role="tab" aria-controls="home" aria-selected="false">
						Tình trạng thiết bị cơ sở vật chất
					</a>
				</li>
				<li class="nav-item rounded-top"
					style="border-left: 2px solid white; border-right: 2px solid white;">
					<a class="nav-link text-dark" id="profile-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/phieunhapkho/forminsert"
						role="tab" aria-controls="profile" aria-selected="false">
						Phiếu nhập kho
					</a>
				</li>
				<li class="nav-item rounded-top">
					<a class="nav-link text-dark active" id="messages-tab"
						data-toggle="tab"
						href="${pageContext.request.contextPath}/hienthi/formdisplay"
						role="tab" aria-controls="messages" aria-selected="false">
						Xem thông tin nhập kho
					</a>
				</li>
			</ul>
		</div>

		<div class="tab-content pl-3 pr-3">
			<div class="tab-pane active" id="messages" role="tabpanel"
				aria-labelledby="messages-tab">
				<div class="row mt-3 pt-2 pb-2" style="border: 2px solid;">
					<div class="col-5">
						<h5>Thông tin nhập</h5>
					</div>
					<div class="col-4">
						<h5>
							Thông tin nhập từ ngày : <span id="tu"></span>
						</h5>
					</div>
					<div class="col-3">
						<h5>
							đến ngày : <span id="den"></span>
						</h5>
					</div>
				</div>
				<div class="row">
					<div class="col-3 scrollable-ulli"
						style="border: 2px solid; height: 450.5px; border-top: none;">
						<ul id="dateList"></ul>
					</div>
					<div class="col-9" style="height: 450.5px;">
						<div class="row scrollable-table"
							style="border: 2px solid; border-left: none; border-top: none;">
							<form id="submitForm" action="" method="post">
								<table id="tableXemThongTin"
									class="table table-striped table-bordered">
									<thead class="bg-secondary text-white">
										<tr>
											<th scope="col">STT</th>
											<th scope="col">Tên sản phẩm</th>
											<th scope="col">Đơn vị tính</th>
											<th scope="col">Số lượng</th>
											<th scope="col">Đơn giá</th>
											<th scope="col">Thành tiền</th>
										</tr>
									</thead>
									<tbody class="bg-light">
									</tbody>
								</table>
							</form>
						</div>
						<div class="row mt-3"
							style="border: 2px solid; border-left: none;">
							<div class="col-4 mt-5">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="chuathanhtoan"> 
									<label class="form-check-label" for="disabledFieldsetCheck"> Chưa thanh toán </label>
								</div>
							</div>
							<div class="col-2">
								<div class="form-group">
									<label for="exampleInputPassword1">Tổng trước thuế</label> 
									<input type="text" class="form-control" id="tongtruocthue"> 
									<input type="text" class="form-control" id="maPhieuHidden" hidden="true">
								</div>
							</div>
							<div class="col-2">
								<div class="form-group">
									<label for="exampleInputPassword1">Tổng sau thuế</label> 
									<input type="text" class="form-control" id="tongsauthue">
								</div>
							</div>
							<div class="col-2">
								<div class="form-group">
									<label for="exampleInputPassword1">Đã thanh toán</label> 
									<input type="text" class="form-control" id="dathanhtoan">
								</div>
							</div>
							<div class="col-2">
								<div class="form-group">
									<label for="exampleInputPassword1">Còn nợ</label> 
									<input type="text" class="form-control" id="conno">
								</div>
							</div>
						</div>
					</div>
				</div>
				<form class="">
					<div class="row pt-3 mt-3" style="border: 2px solid;">
						<div class="col-3">
							<div class="row">
								<div class="form-check col-5 text-right mt-2">
									<input class="form-check-input" type="radio" name="exampleRadios" id="theongay" value="option1"> 
									<label class="form-check-label" for="exampleRadios"> Theo ngày </label>
								</div>
								<div class="form-group col-7">
									<input type="date" class="form-control" id="tungay" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-3">
							<div class="form-group row">
								<label class="col-5 text-center mt-2" for="inlineFormInputGroup">đến ngày</label> 
								<input type="date" class="form-control col-7" id="denngay" readonly="readonly">
							</div>
						</div>
						<div class="col-3">
							<div class="form-check text-right mt-2">
								<input class="form-check-input" type="radio" name="exampleRadios" id="theonam" value="option1"> 
								<label class="form-check-label" for="exampleRadios1"> Theo năm </label>
							</div>
						</div>
						<div class="col-2">
							<div class="form-check text-right mt-2">
								<input class="form-check-input" type="checkbox" id="theothang" disabled="disabled"> 
								<label class="form-check-label" for="disabledFieldsetCheck1"> Theo tháng </label>
							</div>
						</div>
						<div class="col-1">
							<div class="form-check">
								<input class="form-control" type="number" id="inputthang" readonly="readonly">
							</div>
						</div>
					</div>

				</form>
				<div class="row d-flex justify-content-end pt-2 pb-2">
					<button id="updateXemThongTin" class="btn border-dark mr-1" type="button">Cập nhật</button>
					<button id="xuatXemThongTin" class="btn border-dark mr-1" type="button">Xuất</button>
					<button id="closeXemThongTin" class="btn border-dark mr-1" type="button">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script src="../resources/bootstrap/js/jquery.min.js"></script>
	<script src="../resources/bootstrap/validate/HienThiThongTin.js"></script>
</body>
</html>