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
	.form-group1 {
		margin-bottom: 0px;
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
					<a class="nav-link text-dark" id="home-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/tinhtrangthietbi/displaylist"
						role="tab" aria-controls="home" aria-selected="false">Tình trạng thiết bị cơ sở vật chất
					</a>
				</li>
				<li class="nav-item rounded-top" style="border-left: 2px solid white; border-right: 2px solid white;">
					<a class="nav-link text-dark active" id="profile-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/phieunhapkho/forminsert"
						role="tab" aria-controls="profile" aria-selected="false">Phiếu nhập kho
					</a>
				</li>
				<li class="nav-item rounded-top">
					<a class="nav-link text-dark" id="messages-tab" data-toggle="tab"
						href="${pageContext.request.contextPath}/hienthi/formdisplay"
						role="tab" aria-controls="messages" aria-selected="false">Xem thông tin nhập kho
					</a>
				</li>
			</ul>
		</div>

		<div class="tab-pane active" id="profile" role="tabpanel"
			aria-labelledby="profile-tab">

			<form action="">
				<div class="row mt-3" style="border: 2px solid;">
					<div class="col-3 mt-3">
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-6 col-form-label">Nhà cung cấp</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="nhacungcap">
								<span id="errorNCC" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-6 col-form-label">Tên người giao hàng</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="tennguoigiaohang">
								<span id="errorTNGH" class="error"></span>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-4 col-form-label">Ngày giao</label>
							<div class="col-sm-8">
								<input type="date" class="form-control" id="ngaygiao">
								<span id="errorNG" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-4 col-form-label">Mã phiếu</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="maphieu"> 
								<span id="errorMP" class="error"></span>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Đã trả</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="datra"> 
								<span id="errorDT" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Còn nợ</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="conno"> 
								<span id="errorCN" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Hạn thanh toán</label>
							<div class="col-sm-7">
								<input type="date" class="form-control" id="hanthanhtoan">
								<span id="errorHTT" class="error"></span>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Tổng trước thuế</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="tongtruocthue">
								<span id="errorTTT" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Thuế GTGT(%)</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="thue"> 
								<span id="errorT" class="error"></span>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword" class="col-sm-5 col-form-label">Tổng sau thuế</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="tongsauthue" readonly="readonly"> 
								<span id="errorTST" class="error"></span>
							</div>
						</div>
					</div>
				</div>
			</form>

			<div id="divTable" class="row scrollable-table mt-3"
				style="border: 2px solid;">
				<form id="submitSanPhamNhapKho" action="" method="post">
					<table id="tablePhieuNhapKho"
						class="table table-striped table-bordered">
						<thead class="bg-secondary text-white">
							<tr>
								<th scope="col">STT</th>
								<th scope="col">Tên sản phẩm</th>
								<th scope="col">Đơn vị tính</th>
								<th scope="col">Số lượng</th>
								<th scope="col">Đơn giá</th>
								<th scope="col">Thành tiền</th>
								<th scope="col">Bảo hành-Bảo trì</th>
								<th scope="col">Ghi chú</th>
							</tr>
						</thead>
						<tbody class="bg-light">

							<tr class="tr-first1"
								style="background-color: rgb(196, 196, 196);">
								<td></td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="tensanpham" type="text" class="form-control" placeholder="Tên sản phẩm" readonly="readonly" /> 
										<span id="errortensanpham" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="donvitinh" type="text" class="form-control" placeholder="Đơn vị tính" readonly="readonly" /> 
										<span id="errordonvitinh" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="soluong" type="number" class="form-control" placeholder="Số lượng" readonly="readonly" /> 
										<span id="errorsoluong" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="dongia" type="number" class="form-control" placeholder="Đơn giá" readonly="readonly" /> 
										<span id="errordongia" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="thanhtien" type="number" class="form-control" placeholder="Thành tiền" readonly="readonly" /> 
										<span id="errorthanhtien" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<input id="baohanh" type="date" class="form-control" placeholder="Bảo hành-Bảo trì" readonly="readonly" /> 
										<span id="errorBHBT" class="error"></span>
									</div>
								</td>
								<td class=" p-0">
									<div class="form-group1">
										<textarea id="ghichu1" class="form-control" cols="1" rows="1"
											placeholder="Ghi chú" readonly="readonly"></textarea>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="row d-flex justify-content-end pt-2 pb-2">
				<button id="addPhieuNhapKho" class="btn border-dark mr-1" type="button">Thêm mới</button>
				<button id="updatePhieuNhapKho" class="btn border-dark mr-1" type="button">Cập nhật</button>
				<button id="xuatPhieuNhapKho" class="btn border-dark mr-1" type="button">Xuất</button>
				<button id="closePhieuNhapKho" class="btn border-dark mr-1" type="button">Close</button>
			</div>
		</div>
	</div>
	<script src="../resources/bootstrap/js/jquery.min.js"></script>
	<script src="../resources/bootstrap/validate/ValidatePhieuNhapKho.js"></script>

</body>
</html>