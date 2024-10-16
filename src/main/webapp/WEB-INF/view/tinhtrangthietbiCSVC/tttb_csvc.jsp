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
	<style type="text/css">
		.form-group {
			margin-bottom: 0px;
		}
		.form-control {
			font-size: 13px;
		}
		td {
			padding: 0px;
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
					<a class="nav-link text-dark active" id="home-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/tinhtrangthietbi/displaylist"
						role="tab" aria-controls="home" aria-selected="false">Tình
							trạng thiết bị cơ sở vật chất
					</a>
				</li>
				<li class="nav-item rounded-top"
					style="border-left: 2px solid white; border-right: 2px solid white;">
					<a class="nav-link text-dark" id="profile-tab" data-toggle="tab"
					href="${pageContext.request.contextPath}/phieunhapkho/forminsert"
					role="tab" aria-controls="profile" aria-selected="false">Phiếu
						nhập kho
					</a>
				</li>
				<li class="nav-item rounded-top">
					<a class="nav-link text-dark"
					id="messages-tab" data-toggle="tab"
					href="${pageContext.request.contextPath}/hienthi/formdisplay"
					role="tab" aria-controls="messages" aria-selected="false">Xem
						thông tin nhập kho
					</a>
				</li>
			</ul>
		</div>
		<div class="tab-content pl-3 pr-3">
			<div class="tab-pane active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div class="row scrollable-table1 mt-3" style="border: 2px solid;">
					<form id="submitForm">
						<table id="tableTinhTrangThietBi"
							class="table table-striped table-bordered">
							<thead class="bg-secondary text-white">
								<tr>
									<th scope="col" class="col-1">STT</th>
									<th scope="col">Tên thiết bị</th>
									<th scope="col" class="text-center">Ngày nhập kho</th>
									<th scope="col" class="col-1">Đơn vị tính</th>
									<th scope="col" class="col-1 text-right">Số lượng</th>
									<th scope="col">Tình trạng</th>
									<th scope="col" class="col-2">Đánh giá chất lượng</th>
									<th scope="col">Ngày đánh giá tình trạng</th>
									<th scope="col">Nhà cung cấp</th>
								</tr>
							</thead>
							<tbody class="bg-light">
								<tr class="tr-first"
									style="background-color: rgb(196, 196, 196);">
									<td>
										<input id="inputhidden" type="hidden"> 
										<input type="text" id="iddanhgiahidden" hidden="true">
									</td>
									
									<td class="p-0">
										<select class="form-control" id="tenthietbi">
												<option value="">Chọn sản phẩm</option>
										</select> 
										<span id="errortenthietbi" class="error"></span>
									</td>
									
									<td id="ngaynhapkho" class="text-center pt-2 pb-0"></td>
									
									<td id="donvitinh1" class="pt-2 pb-0"></td>
									
									<td id="soluong1" class="text-right pt-2 pb-0"></td>
									
									<td class="p-0">
										<select class="form-control" id="tinhtrang">
												<option value="">Tình trạng</option>
												<option value="Đang sử dụng">Đang sử dụng</option>
												<option value="Không sử dụng">Không sử dụng</option>
												<option value="Đã thanh lý">Đã thanh lý</option>
												<option value="Đã loại bỏ">Đã loại bỏ</option>
										</select> 
										<span id="errortinhtrang" class="error"></span>
									</td>
									
									<td class="p-0">
										<select class="form-control" id="danhgiaCL">
												<option value="">Đánh giá chất lượng</option>
												<option value="Tốt">Tốt</option>
												<option value="Trung bình">Trung bình</option>
												<option value="Kém">Kém</option>
												<option value="Hư hỏng">Hư hỏng</option>
										</select> 
										<span id="errordanhgiaCL" class="error"></span>
									</td>
									
									<td id="ngaydanhgia" class="text-center pt-2 pb-0"></td>
									
									<td id="nhacungcap1" class="pt-2 pb-0"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="row mt-2 p-2">
					<div class="col-8 d-flex justify-content-start">
						<input id="search" type="text" class="form-control" placeholder="Tìm kiếm" />
					</div>
					<div class="col-4 d-flex justify-content-end">
						<button id="addOrChangeTTTB" class="btn border-dark mr-1" type="button">Thêm mới/Chỉnh sửa</button>
						<button id="updateTTTB" class="btn border-dark mr-1" type="button">Cập nhật</button>
						<button id="xuatTTTB" class="btn border-dark mr-1" type="button">Xuất</button>
						<button id="closeTTTB" class="btn border-dark mr-1" type="button">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../resources/bootstrap/js/jquery.min.js"></script>
	<script src="../resources/bootstrap/validate/ValidateTinhTrangThietBi.js"></script>
</body>
</html>