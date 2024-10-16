$(document).ready(function() {

	var xuatOK = false;

	//-------------LƯU THÔNG TIN CỦA PHIẾU NHẬP KHO---------------------------------
	$("#updatePhieuNhapKho").on("click", function(event) {
		event.preventDefault();

		var updateOK = true;

		//validate dữ liệu của Phiếu Nhập kho
		var nhaCungCap = $("#nhacungcap").val();
		if (nhaCungCap.trim() === '') {
			$("#nhacungcap").addClass('is-invalid');
			$("#errorNCC").text("Không được để trống").show();
			updateOK = false;
		} else {
			$("#nhacungcap").removeClass('is-invalid');
			$("#errorNCC").text('').hide();
		}

		var tenNguoiGiaoHang = $("#tennguoigiaohang").val();
		if (tenNguoiGiaoHang.trim() === '') {
			$("#tennguoigiaohang").addClass('is-invalid');
			$("#errorTNGH").text("Không được để trống").show();
			updateOK = false;
		} else {
			$("#tennguoigiaohang").removeClass('is-invalid');
			$("#errorTNGH").text('').hide();
		}

		var maPhieu = $("#maphieu").val();
		if (maPhieu.trim() === '' || !/^MNK\d{5}$/.test(maPhieu)) {
			$("#maphieu").addClass('is-invalid');
			$("#errorMP").text("Không được để trống và có định dạng MNKxxxxx").show();
			updateOK = false;
		} else {
			$("#maphieu").removeClass('is-invalid');
			$("#errorMP").text('').hide();
		}

		if ($("#datra").val().trim() === "" || !checkDaTra()) {
			$("#datra").addClass('is-invalid');
			$("#errorDT").text("Không được để trống và không được nhập số âm").show();
			updateOK = false;
		} else {
			$("#datra").removeClass('is-invalid');
			$("#errorDT").text('').hide();
		}

		if ($("#conno").val().trim() === "" || !checkConNo()) {
			$("#conno").addClass('is-invalid');
			$("#errorCN").text("Không được để trống và không được nhập số âm").show();
			updateOK = false;
		} else {
			$("#conno").removeClass('is-invalid');
			$("#errorCN").text('').hide();
		}

		if ($("#tongtruocthue").val().trim() === "" || !checkTongTruocThue()) {
			$("#tongtruocthue").addClass('is-invalid');
			$("#errorTTT").text("Không được để trống và không được nhập số âm").show();
			updateOK = false;
		} else {
			$("#tongtruocthue").removeClass('is-invalid');
			$("#errorTTT").text('').hide();
		}

		if ($("#thue").val().trim() === "") {
			$("#thue").addClass('is-invalid');
			$("#errorT").text("Không được để trống").show();
			updateOK = false;
		} else {
			if (!checkThue()) {
				$("#thue").addClass('is-invalid');
				$("#errorT").text("Phải là số và có giá trị từ 0 đến 100").show();
				updateOK = false;
			} else {
				$("#thue").removeClass('is-invalid');
				$("#errorT").text('').hide();
			}
		}

		if (!isValidDate($("#hanthanhtoan").val()) || !checkDate($("#hanthanhtoan").val())) {
			$("#hanthanhtoan").addClass('is-invalid');
			$("#errorHTT").text("Ngày nhập vào không đúng định dạng").show();
			updateOK = false;
		} else {
			$("#hanthanhtoan").removeClass('is-invalid');
			$("#errorHTT").text('').hide();
		}

		if (!isValidDate($("#ngaygiao").val()) || !checkDate($("#ngaygiao").val())) {
			$("#ngaygiao").addClass('is-invalid');
			$("#errorNG").text("Ngày nhập vào không đúng định dạng").show();
			updateOK = false;
		} else {
			$("#ngaygiao").removeClass('is-invalid');
			$("#errorNG").text('').hide();
		}

		if (updateOK) {
			var rowCount = $('#tablePhieuNhapKho tbody tr').length;

			//nếu trong bảng chưa có dữ liệu thì chưa thể lưu
			if (rowCount === 1) {
				return toastr.error("Không có dữ liệu trong bảng", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}

			var phieuNhapKhoData = {
				maPhieu: $('#maphieu').val(),
				nhaCungCap: $('#nhacungcap').val(),
				tenNguoiGiaoHang: $('#tennguoigiaohang').val(),
				ngayGiao: $('#ngaygiao').val(),
				hanThanhToan: $('#hanthanhtoan').val(),
				daTra: $('#datra').val(),
				conNo: $('#conno').val(),
				thueGTGT: $('#thue').val(),
				tongTruocThue: $('#tongtruocthue').val(),
				tongSauThue: $('#tongsauthue').val()
			};

			var tableData = [];

			$('#tablePhieuNhapKho tbody tr:not(:first)').each(function(index, tr) {
				var rowData = {
					'idSPNhapKho': $(tr).find('.idSPNhapKhoHidden').val(),
					'tenSanPham': $(tr).find('.cltensanpham').text(),
					'donViTinh': $(tr).find('.cldonvitinh').text(),
					'soLuong': $(tr).find('.clsoluong').text(),
					'donGia': $(tr).find('.cldongia').text(),
					'thanhTien': $(tr).find('.clthanhtien').text(),
					'baoHanh_BaoTri': $(tr).find('.clbaohanh').text(),
					'ghiChu': $(tr).find('.clghichu1').text(),
					maPhieu: $('#maphieu').val()
				};
				tableData.push(rowData);
			});

			console.log("đây là table: ", tableData);

			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/phieunhapkho/saveData',
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ phieuNhapKho: phieuNhapKhoData, sanPhamNhapKhoDTOs: tableData }),
				success: function(list) {
					xuatOK = true;
					toastr.success("Lưu dữ liệu thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
					$('#tablePhieuNhapKho tbody tr:not(:first)').each(function(index, tr) {
						$(tr).find(".idSPNhapKhoHidden").val(list[index].idSPNhapKho);
					})
				},
				error: function() {
					toastr.error("Mã phiếu đã tồn tại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});

		}
	});

	//Nhập thông tin của các sản phẩm khi nhập đầy đủ thông tin Phiếu Nhập Kho
	$('.tr-first1').on('click', function() {
		var nhaCungCap = $("#nhacungcap").val().trim();
		var tenNguoiGiaoHang = $("#tennguoigiaohang").val().trim();
		var maPhieu = $("#maphieu").val().trim();
		var daTra = $("#datra").val().trim();
		var conNo = $("#conno").val().trim();
		var tongTruocThue = $("#tongtruocthue").val().trim();
		var thue = $("#thue").val().trim();
		if (nhaCungCap != '' && tenNguoiGiaoHang != '' && maPhieu != ''
			&& daTra != '' && conNo != '' && tongTruocThue != ''
			&& thue != '' && $("#ngaygiao").val() != '' && $("#hanthanhtoan").val() != '') {
			$('#tensanpham, #donvitinh, #soluong, #dongia, #thanhtien, #baohanh, #ghichu1').prop('readonly', false);
		} else {
			$('#tensanpham, #donvitinh, #soluong, #dongia, #thanhtien, #baohanh, #ghichu1').prop('readonly', true);
			toastr.error("Nhập đầy đủ thông tin của Phiếu Nhập Kho", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	//Hàm tính toán tổng sau thuế
	function calculateTongSauThue() {
		var thue = parseFloat($('#thue').val()) || 0;
		var tongTruocThue = parseFloat($('#tongtruocthue').val()) || 0;
		if (thue !== 0 && tongTruocThue !== 0) {
			var tongSauThue = tongTruocThue * ((thue + 100) / 100);
			$('#tongsauthue').val(tongSauThue.toFixed(2));
		} else {
			$('#tongsauthue').val('');
		}
	}

	$('#thue, #tongtruocthue').on('input', function() {
		calculateTongSauThue();
	});


	var count = 0;

	//-------------THÊM MỚI 1 SẢN PHẨM NHẬP KHO VÀO BẢNG---------------------------------
	$("#addPhieuNhapKho").on("click", function(event) {
		event.preventDefault();
		var addOK = true;
		if (!$('#tensanpham, #donvitinh, #soluong, #dongia, #thanhtien, #baohanh, #ghichu1').prop('readonly')) {

			//Validate các trường khi nhập sản phẩm 
			var tenSanPham = $("#tensanpham").val();

			if (tenSanPham.trim() === '') {
				$("#tensanpham").addClass('is-invalid');
				$("#errortensanpham").text("Không được để trống").show();
				addOK = false;
			} else {
				$("#tensanpham").removeClass('is-invalid');
				$("#errortensanpham").text("").hide();
			}

			var donViTinh = $("#donvitinh").val();

			if (donViTinh.trim() === '') {
				$("#donvitinh").addClass('is-invalid');
				$("#errordonvitinh").text('Không được để trống').show();
				addOK = false;
			} else {
				$("#donvitinh").removeClass('is-invalid');
				$("#errordonvitinh").text('').hide();
			}

			var soLuong = $("#soluong").val();

			if (soLuong === '' || soLuong < 0 || soLuong == 0) {
				$("#soluong").addClass('is-invalid');
				$("#errorsoluong").text('Không được để trống và giá trị nhập phải lớn hơn 0').show();
				addOK = false;
			} else {
				$("#soluong").removeClass('is-invalid');
				$("#errorsoluong").text('').hide();
			}

			var donGia = $("#dongia").val();

			if (donGia === '' || donGia < 0 || donGia == 0) {
				$("#dongia").addClass('is-invalid');
				$("#errordongia").text('Không được để trống và giá trị nhập phải lớn hơn 0').show();
				addOK = false;
			} else {
				$("#dongia").removeClass('is-invalid');
				$("#errordongia").text('').hide();
			}

			var thanhTien = $("#thanhtien").val();

			if (thanhTien === '' || thanhTien < 0 || thanhTien == 0) {
				$("#thanhtien").addClass('is-invalid');
				$("#errorthanhtien").text('Không được để trống và giá trị nhập phải lớn hơn 0').show();
				addOK = false;
			} else {
				$("#thanhtien").removeClass('is-invalid');
				$("#errorthanhtien").text('').hide();
			}

			if (!isValidDate($("#baohanh").val()) || !checkDate($("#baohanh").val())) {
				$("#baohanh").addClass('is-invalid');
				$("#errorBHBT").text("Ngày nhập vào không đúng định dạng").show();
				addOK = false;
			} else {
				$("#baohanh").removeClass('is-invalid');
				$("#errorBHBT").text('').hide();
			}
			if (addOK) {

				count++;

				// Tạo một hàng mới và thêm dữ liệu vào
				var newRow =
					'<tr>' +
					'<td><input type="text" class="idSPNhapKhoHidden" hidden="true">' + (count) + '</td>' +
					'<td class="cltensanpham">' + $('#tensanpham').val() + '</td>' +
					'<td class="cldonvitinh">' + $('#donvitinh').val() + '</td>' +
					'<td class="clsoluong">' + $('#soluong').val() + '</td>' +
					'<td class="cldongia">' + $('#dongia').val() + '</td>' +
					'<td class="clthanhtien">' + $('#thanhtien').val() + '</td>' +
					'<td class="clbaohanh">' + $('#baohanh').val() + '</td>' +
					'<td class="clghichu1">' + $('#ghichu1').val() + '</td>' +
					'</tr>';

				// Thêm hàng mới vào bảng
				$('#tablePhieuNhapKho tbody').append(newRow);

				// Xóa dữ liệu trong các ô input
				$('#tensanpham').val('');
				$('#donvitinh').val('');
				$('#soluong').val('');
				$('#dongia').val('');
				$('#thanhtien').val('');
				$('#baohanh').val('');
				$('#ghichu1').val('');
			}
		} else {
			toastr.error("Nhập đầy đủ thông tin phiếu nhập kho rồi Click vào ô nhập trong bảng để thêm sản phẩm", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	//-------------NHÁY ĐÚP VÀO HÀNG MUỐN CHỈNH SỬA ĐỂ CHỈNH SỬA THÔNG TIN---------------------------------
	$(document).on('dblclick', '#tablePhieuNhapKho tbody tr:not(:first)', function(event) {
		var row = $(this);
		var clickedCell = $(event.target).closest('td'); // Lấy ô đã được click

		if (!row.data('editing')) {

			// Biến để lưu trữ ô đầu tiên cần focus
			var firstInput = null;
			row.find('td.cltensanpham, td.cldonvitinh, td.clsoluong, td.cldongia, td.clthanhtien, td.clbaohanh, td.clghichu1').each(function() {
				var cell = $(this);
				var cellText = cell.text();
				if (cell.hasClass('clbaohanh')) {
					cell.html('<input class="form-control" type="date" value="' + cellText + '">');
				} else {
					if (cell.hasClass('cltensanpham') || cell.hasClass('cldonvitinh') || cell.hasClass('clghichu1')) {
						cell.html('<input class="form-control" type="text" value="' + cellText + '">');
					} else {
						cell.html('<input class="form-control" type="number" value="' + cellText + '">');
					}
				}

				// Lưu trữ ô đầu tiên cần focus
				if (!firstInput) {
					firstInput = cell.find('input');
				}
			});

			// Đánh dấu hàng đang được chỉnh sửa
			row.data('editing', true);

			// Focus vào input trong ô đã click, hoặc focus vào ô input đầu tiên nếu không có input trong ô đã click
			var inputInClickedCell = clickedCell.find('input');
			if (inputInClickedCell.length) {
				inputInClickedCell.focus();
				// Di chuyển con trỏ chuột vào cuối input
				inputInClickedCell[0].selectionStart = inputInClickedCell[0].selectionEnd = inputInClickedCell.val().length;
			} else if (firstInput) {
				// Nếu không có input trong ô đã click, focus vào ô đầu tiên
				firstInput.focus();
				// Di chuyển con trỏ chuột vào cuối input
				firstInput[0].selectionStart = firstInput[0].selectionEnd = firstInput.val().length;
			}
		} else {
			// Hàng đang ở trạng thái chỉnh sửa, không cho phép chỉnh sửa lần nữa
		}
	});

	//-------------LƯU LẠI VÀ THOÁT KHỎI CHẾ ĐỘ CHỈNH SỬA BẰNG VIỆC CLICK RA KHỎI HÀNG ĐANG CHỈNH SỬA---------------------------------
	$(document).on('focusout', '#tablePhieuNhapKho tbody tr:not(:first)', function() {
		var row = $(this);
		if (row.data('editing')) {
			setTimeout(function() {
				var focusedWithinRow = row.find('input:focus').length > 0;

				if (!focusedWithinRow) {
					var allInputsValid = true;
					row.find('td:not(:first-child)').each(function() {
						var cell = $(this);
						var cellInput = cell.find('input');
						var errorSpan = cell.find('.error-message');
						if (cellInput.length) {
							var cellValue = cellInput.val().trim();
							if (!cell.hasClass('clghichu1')) {
								if (cellInput.attr('type') === 'number') {
									if (cellValue === '' || parseFloat(cellValue) <= 0) {
										allInputsValid = false;
										if (errorSpan.length === 0) {
											cell.append('<span class="error-message" style="color: red; font-size: 12px;">Phải là số lớn hơn 0</span>');
										}
										cellInput.focus();
									} else {
										errorSpan.remove();
										cell.html(cellValue);
									}
								} else if (cellInput.attr('type') === 'date') {
									var dateRegex = /^\d{4}-\d{2}-\d{2}$/;
									if (!dateRegex.test(cellValue)) {
										allInputsValid = false;
										if (errorSpan.length === 0) {
											cell.append('<span class="error-message" style="color: red; font-size: 12px;">Định dạng ngày không hợp lệ (YYYY-MM-DD)</span>');
										}
										cellInput.focus();
									} else {
										errorSpan.remove();
										cell.html(cellValue);
									}
								} else if (cellInput.attr('type') === 'text') {
									if (cellValue === '') {
										allInputsValid = false;
										if (errorSpan.length === 0) {
											cell.append('<span class="error-message" style="color: red; font-size: 12px;">Không được để trống</span>');
										}
										cellInput.focus();
									} else {
										errorSpan.remove();
										cell.html(cellValue);
									}
								}
							} else {
								cell.html(cellValue);
							}
						}
					});
					if (allInputsValid) {
						row.data('editing', false);
					}
				}
			}, 0);
		}
	});


	// Sự kiện để lưu lại thay đổi khi nhấn phím Enter
	$(document).on('keypress', '#tablePhieuNhapKho tbody tr:not(:first) input', function(e) {
		if (e.which === 13) { // Kiểm tra phím Enter (mã phím Enter là 13)
			var cellInput = $(this);
			var cell = cellInput.closest('td');
			var row = cell.closest('tr');
			// Lấy giá trị của ô input và loại bỏ khoảng trắng đầu và cuối
			var cellValue = cellInput.val().trim();
			var errorSpan = cell.find('.error-message');

			// Kiểm tra xem ô input có rỗng không
			if (!cell.hasClass('clghichu1') && cellValue === '') {

				// Nếu ô input rỗng, đặt biến kiểm tra là false
				allInputsValid = false;
				if (errorSpan.length === 0) {
					cell.append('<span class="error-message" style="color: red; font-size: 12px;">Không được để trống</span>');
				}
				// Đặt trỏ lại vào ô input rỗng
				cellInput.focus();
			} else {
				// Xóa thông báo lỗi nếu ô input hợp lệ
				errorSpan.remove();
				// Nếu ô input không rỗng, ghi đè giá trị của ô bằng giá trị mới
				cell.html(cellValue);

				// Kiểm tra nếu tất cả các ô trong hàng không còn ô input nào nữa
				if (row.find('input').length === 0) {
					// Bỏ đánh dấu hàng đang được chỉnh sửa
					row.data('editing', false);
					xuatOK = false;
				}
			}
		}
	});

	//-------------XUẤT THÔNG TIN VỪA ĐƯỢC LƯU RA FILE EXCEL---------------------------------
	$('#xuatPhieuNhapKho').click(function(event) {
		event.preventDefault();

		//Kiểm tra xem table có dữ liệu không để xuất dữ liệu
		var rowCount = $('#tablePhieuNhapKho tbody tr').length;
		if (rowCount === 1) {
			return toastr.error("Không có dữ liệu", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}

		if (xuatOK) {
			var phieuNhapKhoData = {
				maPhieu: $('#maphieu').val(),
				nhaCungCap: $('#nhacungcap').val(),
				tenNguoiGiaoHang: $('#tennguoigiaohang').val(),
				ngayGiao: $('#ngaygiao').val(),
				hanThanhToan: $('#hanthanhtoan').val(),
				daTra: $('#datra').val(),
				conNo: $('#conno').val(),
				thueGTGT: $('#thue').val(),
				tongTruocThue: $('#tongtruocthue').val(),
				tongSauThue: $('#tongsauthue').val()
			};

			var tableData = [];

			$('#tablePhieuNhapKho tbody tr:not(:first)').each(function(index, tr) {
				var rowData = {
					'tenSanPham': $(tr).find('.cltensanpham').text(),
					'donViTinh': $(tr).find('.cldonvitinh').text(),
					'soLuong': $(tr).find('.clsoluong').text(),
					'donGia': $(tr).find('.cldongia').text(),
					'thanhTien': $(tr).find('.clthanhtien').text(),
					'baoHanh_BaoTri': $(tr).find('.clbaohanh').text(),
					'ghiChu': $(tr).find('.clghichu1').text(),
					'maPhieu': $('#maphieu').val()
				};
				tableData.push(rowData);
			});

			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/phieunhapkho/exportToExcel',
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ phieuNhapKho: phieuNhapKhoData, sanPhamNhapKhoDTOs: tableData }),
				xhrFields: {
					responseType: 'blob'
				},
				success: function(response) {
					var blob = new Blob([response], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
					var link = document.createElement('a');
					link.href = window.URL.createObjectURL(blob);
					link.download = 'PhieuNhapKho.xlsx';
					link.click();
				},
				error: function() {
					toastr.error("Xuất dữ liệu thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});
		} else {
			toastr.error("Vui lòng nhấn cập nhật để xuất dữ liệu", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	// Thiết lập sự kiện change cho các trường đầu vào
	$('#maphieu, #nhacungcap, #tennguoigiaohang, #ngaygiao, #hanthanhtoan, #datra, #conno, #thue, #tongtruocthue, #tongsauthue').on('change', function() {
		xuatOK = false;
	});

	$('#tablePhieuNhapKho tbody').on('change', 'tr:not(:first) .cltensanpham, .cldonvitinh, .clsoluong, .cldongia, .clthanhtien, .clbaohanh, .clghichu1', function() {
		xuatOK = false;
	});

	//---------------------THOÁT KHỎI GIAO DIỆN PHIẾU NHẬP KHO-------------------------------
	$('#closePhieuNhapKho').on('click', function(event) {
		event.preventDefault();
		window.location.href = '/JWD_MockProject/homeController';
	})


	function checkDaTra() {
		var daTra = $("#datra").val();
		if (isNaN(daTra) || daTra < 0) {
			return false;
		}
		return true;
	}

	function checkConNo() {
		var conNo = $("#conno").val();
		if (isNaN(conNo) || conNo < 0) {
			return false;
		}
		return true;
	}

	function checkTongTruocThue() {
		var tongTruocThue = $("#tongtruocthue").val();
		if (isNaN(tongTruocThue) || tongTruocThue < 0) {
			return false;
		}
		return true;
	}

	function checkThue() {
		var thue = $("#thue").val();
		if (isNaN(thue) || thue < 0 || thue > 100) {
			return false;
		}
		return true;
	}

	function isValidDate(dateString) {
		var date = new Date(dateString);
		// Kiểm tra nếu date không hợp lệ hoặc không phải là ngày
		if (isNaN(date.getTime())) {
			return false;
		}
		return true;
	}

	function checkDate(dateString) {
		var date = new Date(dateString);
		var cutoffDate = new Date('1950-01-01');
		var futureDate = new Date('3000-01-01');
		// Kiểm tra nếu date không hợp lệ hoặc không phải là ngày
		if (date < cutoffDate || date > futureDate) {
			return false;
		}
		return true;
	}
});