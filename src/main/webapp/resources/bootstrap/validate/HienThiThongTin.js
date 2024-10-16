$(document).ready(function() {

	var daThanhToan = true;

	function showInitialDateList() {
		$.ajax({
			url: 'http://localhost:8080/JWD_MockProject/hienthi/listDate', // Địa chỉ endpoint
			type: 'GET',
			dataType: 'json',
			success: function(listDate) {
				console.log(listDate);
				var dateList = $('#dateList');
				dateList.empty(); // Xóa các phần tử cũ
				listDate.forEach(function(object) {
					var dateNhapKho = new Date(object.ngayGiao[0], object.ngayGiao[1], object.ngayGiao[2]);

					// Định dạng lại ngày thành chuỗi có định dạng YYYY-MM-DD
					var formattedDate = dateNhapKho.getFullYear() + '-' + ('0' + (dateNhapKho.getMonth())).slice(-2) + '-' + ('0' + dateNhapKho.getDate()).slice(-2);

					// Đặt giá trị của ô input kiểu date bằng chuỗi định dạng YYYY-MM-DD
					dateList.append('<li class="dateDetail"> <input class="idMaPhieu" type="hidden" value="' + object.maPhieu + '">' + formattedDate + '</li>');
				});
				// Thêm sự kiện click cho các thẻ li sau khi chúng được tạo
				displayDetail();
			},
			error: function() {
				toastr.error("Lỗi khi nhận dữ liệu", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}
		});

	}
	showInitialDateList();


	$('#chuathanhtoan').on('change', function() {

		$('#dathanhtoan').removeClass('is-invalid');
		var checkbox = $(this);

		$('#theongay').prop('checked', false);
		$('#theonam').prop('checked', false);
		$('input[type="radio"], li').not('#chuathanhtoan').on('click', function() {
			$('#tu').text('');
			$('#den').text('');
		});

		// Kiểm tra trạng thái của checkbox
		if (checkbox.is(':checked')) {
			$('#tu').text('');
			$('#den').text('');
			reset();
			$('#tableXemThongTin').show();
			$('#dateList').empty(); // Hiển thị bảng
			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/hienthi/listChuaThanhToan', // Địa chỉ endpoint
				type: 'GET',
				dataType: 'json',
				success: function(hienThi) {
					displayAll(hienThi);
				},
				error: function(error) {
					toastr.error("Lỗi khi nhận dữ liệu", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});
		} else {
			// Nếu checkbox không được chọn, ẩn bảng và xóa các hàng trong bảng
			$('#tableXemThongTin tbody').empty();
			$('#tongtruocthue').val('').prop('readonly', false);
			$('#tongsauthue').val('').prop('readonly', false);
			$('#dathanhtoan').val('').prop('readonly', false);
			$('#conno').val('').prop('readonly', false);
			showInitialDateList();
		}
	});


	//Tìm kiếm theo ngày
	$('#theongay').on('click', function() {
		$('#dathanhtoan').removeClass('is-invalid');
		$('#tableXemThongTin tbody').empty();
		$('#tongtruocthue').val('').prop('readonly', false);
		$('#tongsauthue').val('').prop('readonly', false);
		$('#dathanhtoan').val('').prop('readonly', false);
		$('#conno').val('').prop('readonly', false);

		$('#chuathanhtoan').prop('checked', false);

		$('#tungay').val('').prop('readonly', false);
		$('#denngay').val('').prop('readonly', false);
		$('#dateList').empty();

		$('input[type="radio"], #chuathanhtoan, li').not('#theongay').on('click', function() {
			$('#tu').text('');
			$('#den').text('');
		});


		$(document).off('keypress').on('keypress', function(e) {
			if (e.which === 13 && $('#theongay').is(':checked')) {
				e.preventDefault();
				var tuNgay = $('#tungay').val();
				var denNgay = $('#denngay').val();
				if (tuNgay !== '' && denNgay !== '' && isValidDate(tuNgay) && isValidDate(denNgay)) {
					sendDateRequest();
				} else {
					toastr.error("Ngày tháng nhập vào sai định dạng", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			}
		});
	});

	function sendDateRequest() {
		var isRequestRunning = false;

		if (isRequestRunning) return; // Nếu yêu cầu đang chạy, ngăn không gọi hàm nữa
		isRequestRunning = true; // Đặt cờ yêu cầu đang chạy
		$('#tableXemThongTin tbody').empty();

		$('#tu').text($('#tungay').val());
		$('#den').text($('#denngay').val());

		var theoNgay = {
			tuNgay: '',
			denNgay: ''
		};
		theoNgay.tuNgay = $('#tungay').val();
		theoNgay.denNgay = $('#denngay').val();

		$.ajax({
			url: 'http://localhost:8080/JWD_MockProject/hienthi/listTheoNgay',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(theoNgay),
			success: function(hienThiTheoNgay) {
				displayAll(hienThiTheoNgay);
			},
			error: function(error) {
				toastr.error("Lỗi khi nhận dữ liệu", "Thất bại", {
					positionClass: "toast-top-right",
				});
				isRequestRunning = false
			}
		});
	}

	$('#theonam').on('click', function() {
		$('#dathanhtoan').removeClass('is-invalid');
		if ($(this).is(':checked')) {
			// Nếu được chọn, ngăn việc nhập liệu vào các trường input date bằng cách thiết lập thuộc tính readonly
			$('#theothang').prop('disabled', false);
			$('#chuathanhtoan').prop('checked', false);
			reset();

			$('#dateList').empty();

			$('input[type="radio"], #chuathanhtoan, li').not('#theonam').on('click', function() {
				$('#theothang').prop('checked', false).prop('disabled', true);
				$('#inputthang').val('').prop('readonly', true);
			});


			$(document).off('keypress').on('keypress', function(e) {
				if (e.which === 13 && $('#theonam').is(':checked')) {
					e.preventDefault();
					if ($('#inputthang').val() !== '' && searchThangNam == true) {

						var isRequestRunning = false;

						if (isRequestRunning) return; // Nếu yêu cầu đang chạy, ngăn không gọi hàm nữa
						isRequestRunning = true; // Đặt cờ yêu cầu đang chạy
						$('#tableXemThongTin tbody').empty();

						var thang = $('#inputthang').val();

						$.ajax({
							url: 'http://localhost:8080/JWD_MockProject/hienthi/listTheoThangNam',
							type: 'POST',
							contentType: 'application/json',
							data: JSON.stringify(thang),
							success: function(hienThiTheoNgay) {
								displayAll(hienThiTheoNgay);
							},
							error: function(error) {
								toastr.error("Lỗi khi nhận dữ liệu", "Thất bại", {
									positionClass: "toast-top-right",
								});
								isRequestRunning = false;
							}
						});
					} else {
						toastr.error("Trường tháng không hợp lệ", "Thất bại", {
							positionClass: "toast-top-right",
						});
					}
				}
			});
		}
	});

	var searchThangNam = true;

	$('#theothang').on('click', function() {
		$('#inputthang').prop('readonly', false);
		$('#inputthang').on('input', function(event) {
			event.preventDefault();
			var thang = $('#inputthang').val();
			if (thang <= 0 || thang > 12) {
				$('#inputthang').addClass('is-invalid');
				searchThangNam = false;
			} else {
				$('#inputthang').removeClass('is-invalid');
				searchThangNam = true;
			}
		})
	})

	$('#updateXemThongTin').on('click', function(event) {
		event.preventDefault();
		if (daThanhToan) {
			var rowCount = $('#tableXemThongTin tbody tr').length;
			if (rowCount === 0) {
				return toastr.error("Không có dữ liệu trong bảng", "Thất bại", {
					positionClass: "toast-top-right",
				});
			}

			var phieuNhapKhoData = {
				maPhieu: $('#maPhieuHidden').val(),
				daTra: $('#dathanhtoan').val(),
				conNo: $('#conno').val(),
				tongTruocThue: $('#tongtruocthue').val(),
				tongSauThue: $('#tongsauthue').val()
			};

			var tableData = [];

			$('#tableXemThongTin tbody tr').each(function(index, tr) {
				var rowData = {
					'idSPNhapKho': $(tr).find('.idSPNhapKho').val(),
					'tenSanPham': $(tr).find('.cltensanpham').text(),
					'donViTinh': $(tr).find('.cldonvitinh').text(),
					'soLuong': $(tr).find('.clsoluong').text(),
					'donGia': $(tr).find('.cldongia').text(),
					'thanhTien': $(tr).find('.clthanhtien').text(),
					'maPhieu': $(tr).find('.maPhieu').val()
				};
				tableData.push(rowData);

				xuatExel = { phieuNhapKhoData, tableData };
			});

			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/hienthi/updateXemThongTin',
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ phieuNhapKho: phieuNhapKhoData, sanPhamNhapKhoDTOs: tableData }),
				success: function(response) {
					toastr.success("Dữ liệu đã được update thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
				},
				error: function() {
					toastr.error("Đã xảy ra lỗi khi cập nhật dữ liệu", "Lỗi", {
						positionClass: "toast-top-right",
					});
				}
			});
		} else {
			toastr.error("Trường thanh toán phải là số dương và không lớn hơn Tổng sau thuế", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}

	})

	function displayAll(hienThi) {

		xuatExel = hienThi;

		isRequestRunning = false; // Đặt lại cờ yêu cầu không chạy
		var count = 0;

		var tongTruocThue = 0;
		var tongSauThue = 0;
		var daTra = 0;
		var conNo = 0;

		hienThi.sanPhamNhapKhoDTOs.forEach(function(sanPham) {
			count++;
			var newRow =
				'<tr>' +
				'<td> <input type=text class="idSPNhapKho" hidden="true" value="' + sanPham.idSPNhapKho + '">' + count +
				'<input type=text class="maPhieu" hidden="true" value="' + sanPham.maPhieu + '"> </td>' +
				'<td class="cltensanpham">' + sanPham.tenSanPham + '</td>' +
				'<td class="cldonvitinh">' + sanPham.donViTinh + '</td>' +
				'<td class="clsoluong">' + sanPham.soLuong + '</td>' +
				'<td class="cldongia">' + sanPham.donGia + '</td>' +
				'<td class="clthanhtien">' + sanPham.thanhTien + '</td>' +
				'</tr>';

			$('#tableXemThongTin tbody').append(newRow);
		});

		hienThi.phieuNhapKho.forEach(function(phieu) {
			tongTruocThue += phieu.tongTruocThue;
			tongSauThue += phieu.tongSauThue;
			daTra += phieu.daTra;
			conNo += phieu.conNo;
		});

		$('#tongtruocthue').val(tongTruocThue).prop('readonly', true);
		$('#tongsauthue').val(tongSauThue).prop('readonly', true);
		$('#dathanhtoan').val(daTra).prop('readonly', true);
		$('#conno').val(conNo).prop('readonly', true);

		$('#dateList').empty();

		hienThi.phieuNhapKho.forEach(function(phieu) {
			var dateNhapKho = new Date(phieu.ngayGiao[0], phieu.ngayGiao[1] - 1, phieu.ngayGiao[2]);
			var formattedDate = dateNhapKho.getFullYear() + '-' + ('0' + (dateNhapKho.getMonth() + 1)).slice(-2) + '-' + ('0' + dateNhapKho.getDate()).slice(-2);

			$('#dateList').append('<li class="dateDetail"> <input class="idMaPhieu" type="hidden" value="' + phieu.maPhieu + '">' + formattedDate + '</li>');
		});

		displayDetail();
	}

	function displayDetail() {
		$('#dateList').off('click').on('click', 'li', function() {
			$('#dateList li').removeClass('selected');
			$(this).addClass('selected');
			$('#dathanhtoan').removeClass('is-invalid');
			var count = 0;
			var idMaPhieu = $(this).find('.idMaPhieu').val();
			$('#tableXemThongTin tbody').empty();
			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/hienthi/sanPhamDetail', // Địa chỉ endpoint
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(idMaPhieu),
				success: function(hienThi) {
					xuatExel = hienThi;
					console.log(hienThi);
					var sanPhamNhapKhos = hienThi.sanPhamNhapKhos;
					var phieuNhapKho = hienThi.phieuNhapKho;
					sanPhamNhapKhos.forEach(function(sanPham) {
						count++;
						var newRow =
							'<tr>' +
							'<td> <input type=text class="idSPNhapKho" hidden="true" value="' + sanPham.idSPNhapKho + '">' + count +
							'<input type=text class="maPhieu" hidden="true" value="' + sanPham.maPhieu.maPhieu + '"> </td>' +
							'<td class="cltensanpham">' + sanPham.tenSanPham + '</td>' +
							'<td class="cldonvitinh">' + sanPham.donViTinh + '</td>' +
							'<td class="clsoluong">' + sanPham.soLuong + '</td>' +
							'<td class="cldongia">' + sanPham.donGia + '</td>' +
							'<td class="clthanhtien">' + sanPham.thanhTien + '</td>' +
							'</tr>';

						// Thêm hàng mới vào bảng
						$('#tableXemThongTin tbody').append(newRow);
					});

					$('#maPhieuHidden').val(phieuNhapKho.maPhieu);
					$('#tongtruocthue').val(phieuNhapKho.tongTruocThue);
					$('#tongsauthue').val(phieuNhapKho.tongSauThue);
					$('#dathanhtoan').val(phieuNhapKho.daTra);
					$('#conno').val(phieuNhapKho.conNo);

					$('#tongtruocthue').prop('readonly', true);
					$('#tongsauthue').prop('readonly', true);
					$('#dathanhtoan').prop('readonly', false);
					$('#conno').prop('readonly', true);


					$('#dathanhtoan').on('input', function() {
						var dathanhtoan = parseFloat($('#dathanhtoan').val());
						var tongSauThue = parseFloat($('#tongsauthue').val());
						if (isNaN(dathanhtoan) || (dathanhtoan < 0 || dathanhtoan > tongSauThue)) {
							$('#dathanhtoan').addClass('is-invalid');
							daThanhToan = false;
						} else {
							$('#dathanhtoan').removeClass('is-invalid');
							var conNo = tongSauThue - dathanhtoan;
							$('#conno').val(parseFloat(conNo));
							daThanhToan = true;
						}
					})
				},
				error: function(error) {
					toastr.error("Lỗi khi nhận dữ liệu", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});
		});
	}

	var xuatExel;

	$('#xuatXemThongTin').on('click', function() {
		var rownumber = $('#tableXemThongTin tbody tr').length;
		if (rownumber === 0) {
			toastr.error("Không có dữ liệu trong bảng", "Thất bại", {
				positionClass: "toast-top-right",
			});
		} else {
			console.log("ĐÂY LÀ FILE:", xuatExel);
			// Chuẩn bị dữ liệu để xuất ra Excel
			var ws_data = [];

			if ($('#chuathanhtoan').is(':checked')) {
				ws_data.push(["Danh sách sản phẩm chưa thanh toán"]);
				ws_data.push([]);
				ws_data.push([]);
			} else {
				if ($('#theongay').is(':checked')) {
					ws_data.push(["Danh sách sản phẩm Nhập từ ngày " + $('#tungay').val() + " đến ngày " + $('#denngay').val()]);
					ws_data.push([]);
					ws_data.push([]);
				} else {
					if ($('#theonam').is(':checked')) {
						ws_data.push(["Danh sách sản phẩm Nhập của tháng " + $('#inputthang').val() + " của tất cả các năm "]);
						ws_data.push([]);
						ws_data.push([]);
					}else{
						ws_data.push(["Danh sách sản phẩm Nhập của ngày " + $('.selected').text()]);
						ws_data.push([]);
						ws_data.push([]);
					}
				}
			}
			if (Array.isArray(xuatExel.phieuNhapKho)) {
				ws_data.push(["Mã Phiếu", "Đã Trả", "Còn Nợ", "Tổng Trước Thuế", "Tổng Sau Thuế"]);
				xuatExel.phieuNhapKho.forEach(function(phieuNhapKhoData) {
					ws_data.push([phieuNhapKhoData.maPhieu, phieuNhapKhoData.daTra, phieuNhapKhoData.conNo, phieuNhapKhoData.tongTruocThue, phieuNhapKhoData.tongSauThue]);
				});

				ws_data.push([]);
				ws_data.push(["ID SP Nhập Kho", "Tên Sản Phẩm", "Đơn Vị Tính", "Số Lượng", "Đơn Giá", "Thành Tiền", "Mã Phiếu"]);

				xuatExel.sanPhamNhapKhoDTOs.forEach(function(row) {
					ws_data.push([row.idSPNhapKho, row.tenSanPham, row.donViTinh, row.soLuong, row.donGia, row.thanhTien, row.maPhieu]);
				});
			} else {
				// Chuẩn bị dữ liệu để xuất ra Excel
				ws_data.push(["Mã Phiếu", "Đã Trả", "Còn Nợ", "Tổng Trước Thuế", "Tổng Sau Thuế"]);
				ws_data.push([xuatExel.phieuNhapKho.maPhieu, xuatExel.phieuNhapKho.daTra, xuatExel.phieuNhapKho.conNo, xuatExel.phieuNhapKho.tongTruocThue, xuatExel.phieuNhapKho.tongSauThue]);
				ws_data.push([]);
				ws_data.push(["ID SP Nhập Kho", "Tên Sản Phẩm", "Đơn Vị Tính", "Số Lượng", "Đơn Giá", "Thành Tiền", "Mã Phiếu"]);

				xuatExel.sanPhamNhapKhos.forEach(function(row) {
					ws_data.push([row.idSPNhapKho, row.tenSanPham, row.donViTinh, row.soLuong, row.donGia, row.thanhTien, row.maPhieu]);
				});
			}

			// Tạo một WorkSheet
			var ws = XLSX.utils.aoa_to_sheet(ws_data);

			// Áp dụng kiểu in đậm cho các tiêu đề ở hàng đầu tiên
			var range = XLSX.utils.decode_range(ws['!ref']);
			for (var C = range.s.c; C <= range.e.c; ++C) {
				var cellAddress = { c: C, r: 0 }; // Hàng tiêu đề đầu tiên
				var cellRef = XLSX.utils.encode_cell(cellAddress);

				if (ws[cellRef]) {
					if (!ws[cellRef].s) ws[cellRef].s = {};
					ws[cellRef].s.font = { bold: true };
				}
			}

			// Tạo một WorkBook
			var wb = XLSX.utils.book_new();
			XLSX.utils.book_append_sheet(wb, ws, "ThongTin");

			// Xuất file Excel
			XLSX.writeFile(wb, 'XemThongTinNhapKho.xlsx');
		}
	});




	$('#closeXemThongTin').on('click', function(event) {
		event.preventDefault();
		window.location.href = '/JWD_MockProject/homeController';
	})

	$(document).on('dblclick', '#tableXemThongTin tbody tr', function(event) {
		var row = $(this);
		var clickedCell = $(event.target).closest('td'); // Lấy ô đã được click

		if (!row.data('editing')) { // Chỉ cho phép chỉnh sửa nếu hàng chưa ở trạng thái chỉnh sửa
			var firstInput = null; // Biến để lưu trữ ô đầu tiên cần focus

			row.find('td.cltensanpham, td.cldonvitinh, td.clsoluong, td.cldongia, td.clthanhtien').each(function() {
				var cell = $(this);
				var cellText = cell.text();
				if (cell.hasClass('cltensanpham') || cell.hasClass('cldonvitinh')) {
					cell.html('<input class="form-control" type="text" value="' + cellText + '">');
				} else {
					cell.html('<input class="form-control" type="number" value="' + cellText + '">');
				}

				// Lưu trữ ô đầu tiên cần focus
				if (!firstInput) {
					firstInput = cell.find('input');
				}
			});

			// Đánh dấu hàng đang được chỉnh sửa
			row.data('editing', true);

			// Focus vào input trong ô đã click
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

	// Sự kiện để lưu lại thay đổi khi nhấp ra ngoài hoặc nhấn Enter
	$(document).on('focusout', '#tableXemThongTin tbody tr', function(event) {
		var row = $(this);
		if (row.data('editing')) {
			// Sử dụng setTimeout để đảm bảo rằng các sự kiện focus khác (nếu có) đã được xử lý
			setTimeout(function() {
				// Kiểm tra nếu bất kỳ ô nào bên trong hàng vẫn đang được focus
				var focusedWithinRow = row.find('input:focus').length > 0;

				if (!focusedWithinRow) {
					var allInputsValid = true;
					row.find('td:not(:first-child)').each(function() {
						var cell = $(this);
						var cellInput = cell.find('input');
						var errorSpan = cell.find('.error-message');
						if (cellInput.length) {
							var cellValue = cellInput.val().trim(); // Lấy giá trị của ô input và loại bỏ khoảng trắng đầu và cuối
							if (cellValue === '') { // Kiểm tra xem ô input có rỗng không
								allInputsValid = false;  // Nếu ô input rỗng, đặt biến kiểm tra là false
								if (errorSpan.length === 0) {
									cell.append('<span class="error-message" style="color: red; font-size: 12px;">Không được để trống</span>');
								}
								cellInput.focus(); // Đặt trỏ lại vào ô input rỗng
							} else {
								errorSpan.remove(); // Xóa thông báo lỗi nếu ô input hợp lệ
								cell.html(cellValue); // Nếu ô input không rỗng, ghi đè giá trị của ô bằng giá trị mới
							}
						}
					});
					if (allInputsValid) {
						row.data('editing', false); // Nếu tất cả các ô input hợp lệ, bỏ đánh dấu hàng đang được chỉnh sửa
					}
				}
			}, 0);
		}
	});

	function isValidDate(dateString) {
		// Kiểm tra định dạng YYYY-MM-DD
		var regex = /^\d{4}-\d{2}-\d{2}$/;
		if (!dateString.match(regex)) {
			return false;
		}

		// Kiểm tra giá trị ngày hợp lệ
		var date = new Date(dateString);
		var timestamp = date.getTime();

		if (typeof timestamp !== 'number' || isNaN(timestamp)) {
			return false;
		}

		return dateString === date.toISOString().split('T')[0];
	}

	function reset() {
		$('#tableXemThongTin tbody').empty();
		$('#tongtruocthue').val('').prop('readonly', false);
		$('#tongsauthue').val('').prop('readonly', false);
		$('#dathanhtoan').val('').prop('readonly', false);
		$('#conno').val('').prop('readonly', false);
		$('#tungay').val('').prop('readonly', true);
		$('#denngay').val('').prop('readonly', true);
	}

});