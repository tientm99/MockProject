$(document).ready(function() {
	var count = 0;
	var xuatOK = false;
	var valid = true;
	var updateOK = true;

	//-----------------THÊM MỚI VÀ CHỈNH SỬA 1 ĐÁNH GIÁ------------------------
	$('#addOrChangeTTTB').on('click', function(event) {
		event.preventDefault();
		if (edit == false) {
			var addOrUpdateOK = true;

			var tenthietbi = $("#tenthietbi").val();
			if (tenthietbi === "") {
				$("#tenthietbi").addClass('is-invalid');
				$("#errortenthietbi").text("Mời chọn sản phẩm").show();
				addOrUpdateOK = false;
			} else {
				$("#tenthietbi").removeClass('is-invalid');
				$("#errortenthietbi").text("").hide();
			}

			if ($('#tinhtrang').val() === "") {
				$("#tinhtrang").addClass('is-invalid');
				$("#errortinhtrang").text("Mời chọn tình trạng").show();
				addOrUpdateOK = false;
			} else {
				$("#tinhtrang").removeClass('is-invalid');
				$("#errortinhtrang").text("").hide();
			}

			if ($('#danhgiaCL').val() === "") {
				$("#danhgiaCL").addClass('is-invalid');
				$("#errordanhgiaCL").text("Mời đánh giá chất lượng").show();
				addOrUpdateOK = false;
			} else {
				$("#danhgiaCL").removeClass('is-invalid');
				$("#errordanhgiaCL").text("").hide();
			}

			if (addOrUpdateOK) {
				count++;

				var idSPNhapKho = $('#inputhidden').val();
				var idDanhGia = $('#iddanhgiahidden').val();

				//Lưu thông tin 1 hàng để thêm vào table
				var newRow =
					'<tr>' +
					'<td class="sTT">' + count + '<input class="idSanPham" type="hidden" value="' + idSPNhapKho + '"></td>' +
					'<td class="coltenthietbi"><input class="idDanhGia" type="hidden" value="' + idDanhGia + '">' + $('#tenthietbi option:selected').text() + '</td>' +
					'<td class="colngaynhapkho text-center">' + $('#ngaynhapkho').html() + '</td>' +
					'<td class="coldonvitinh1">' + $('#donvitinh1').html() + '</td>' +
					'<td class="colsoluong1 text-right">' + $('#soluong1').html() + '</td>' +
					'<td class="coltinhtrang">' + $('#tinhtrang').val() + '</td>' +
					'<td class="coldanhgiaCL">' + $('#danhgiaCL').val() + '</td>' +
					'<td class="colngaydanhgia text-center">' + $('#ngaydanhgia').html() + '</td>' +
					'<td class="colnhacungcap1">' + $('#nhacungcap1').html() + '</td>' +
					+'</tr>';

				//Thêm 1 hàng mới vào Table
				$('#tableTinhTrangThietBi tbody').append(newRow);

				//Làm trống tất cả các ô và input
				$('#iddanhgiahidden').val('');
				$('#tenthietbi').val('');
				$('#ngaynhapkho').html('');
				$('#donvitinh1').html('');
				$('#soluong1').html('');
				$('#tinhtrang').val('');
				$('#danhgiaCL').val('');
				$('#ngaydanhgia').html('');
				$('#nhacungcap1').html('');
			}
		}
	});

	//-----------------LƯU LẠI CÁC ĐÁNH GIÁ------------------------
	$('#updateTTTB').on('click', function(event) {
		event.preventDefault();

		//Kiểm tra bảng có rỗng không để thực hiện lưu dữ liệu
		var rowCount = $('#tableTinhTrangThietBi tbody tr').length;
		if (rowCount === 1) {
			return toastr.error("Không có dữ liệu trong bảng", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
		if (updateOK) {
			//Lấy dữ liệu từ table lưu vào array để gửi về server để lưu trữ
			var tableData = [];
			$('#tableTinhTrangThietBi tbody tr:not(:first)').each(function(index, tr) {
				var idDanhGia = $('.idDanhGia').val();
				if (idDanhGia.trim() === '') {
					var rowData = {
						'idDanhGia': null,
						'tinhTrang': $(tr).find('.coltinhtrang').text(),
						'danhGiaChatLuong': $(tr).find('.coldanhgiaCL').text(),
						'ngayDanhGiaTinhTrang': $(tr).find('.colngaydanhgia').text(),
						'idSPNhapKho': $(tr).find('.idSanPham').val()
					};
					tableData.push(rowData);
				} else {
					var rowData = {
						'idDanhGia': $(tr).find('.idDanhGia').val(),
						'tinhTrang': $(tr).find('.coltinhtrang').text(),
						'danhGiaChatLuong': $(tr).find('.coldanhgiaCL').text(),
						'ngayDanhGiaTinhTrang': $(tr).find('.colngaydanhgia').text(),
						'idSPNhapKho': $(tr).find('.idSanPham').val()
					};
					tableData.push(rowData);
				}
			})

			console.log("ĐÂY LÀ ĐANH GIA: ", tableData);
			//Gửi dữ liệu về controller để xử lý việc lưu trữ
			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/tinhtrangthietbi/savetinhtrangthietbi',
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(tableData),
				success: function(list) {
					toastr.success("Dữ liệu đã được thêm thành công", "Thành công", {
						positionClass: "toast-top-right",
					});
					getId(list);
					xuatOK = true;
				},
				error: function(xhr) {
					if (xhr.status === 409) {
						toastr.error(xhr.responseText, "Thất bại", {
							positionClass: "toast-top-right",
						});
					} else {
						toastr.error("Sản phẩm này đã được đánh giá. Mời chọn sản phẩm khác", "Thất bại", {
							positionClass: "toast-top-right",
						});
					}
				}
			});
		} else {
			toastr.error("Chỉnh sửa để cập nhật", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}

	})

	//-----------------XUẤT RA FILE EXCEL CÁC SẢN PHẨM ĐƯỢC ĐÁNH GIÁ------------------------
	$('#xuatTTTB').click(function(event) {
		event.preventDefault();

		//Kiểm tra table có rỗng không để xuất dữ liệu
		var rowCount = $('#tableTinhTrangThietBi tbody tr').length;
		if (rowCount === 1) {
			return toastr.error("Không có dữ liệu trong bảng", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}

		if (xuatOK) {
			var count = 0;
			var tableData = [];

			$('#tableTinhTrangThietBi tbody tr:not(:first)').each(function(index, tr) {
				var rowData = {
					'sTT': count++,
					'tenSanPham': $(tr).find('.coltenthietbi').text(),
					'ngayGiao': $(tr).find('.colngaynhapkho').text(),
					'donViTinh': $(tr).find('.coldonvitinh1').text(),
					'soLuong': $(tr).find('.colsoluong1').text(),
					'tinhTrang': $(tr).find('.coltinhtrang').text(),
					'danhGiaChatLuong': $(tr).find('.coldanhgiaCL').text(),
					'ngayDanhGiaTinhTrang': $(tr).find('.colngaydanhgia').text(),
					'nhaCungCap': $(tr).find('.colnhacungcap1').text()
				};
				tableData.push(rowData);
			});

			console.log('đây la data', tableData);

			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/tinhtrangthietbi/exportToExcelTTTB',
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(tableData),
				xhrFields: {
					responseType: 'blob'
				},
				success: function(response) {
					var blob = new Blob([response], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
					var link = document.createElement('a');
					link.href = window.URL.createObjectURL(blob);
					link.download = 'DanhGiaChatLuong.xlsx';
					link.click();
				},
				error: function() {
					toastr.error("Xuất dữ liệu thất bại", "Thất bại", {
						positionClass: "toast-top-right",
					});
				}
			});
		} else {
			toastr.error("Dữ liệu đang chỉnh sửa hoặc chưa được cập nhật", "Thất bại", {
				positionClass: "toast-top-right",
			});
		}
	});

	//-----------------THOÁT KHỎI GIAO DIỆN ĐÁNH GIÁ SẢN PHẨM------------------------
	$('#closeTTTB').on('click', function(event) {
		event.preventDefault();
		window.location.href = '/JWD_MockProject/homeController';
	})


	//----------Lấy Danh sách sản phẩm để hiển thị lên ô select------------------
	var productData = [];
	$.ajax({
		url: 'http://localhost:8080/JWD_MockProject/tinhtrangthietbi/listSanPhamNhapKho',
		type: 'GET',
		success: function(list) {
			productData = list;

			var select = $('#tenthietbi');
			select.empty();

			select.append('<option value="">Chọn sản phẩm</option>');

			list.forEach(function(sanPhamNhapKho) {
				select.append('<option value="' + sanPhamNhapKho.idSPNhapKho + '">' + sanPhamNhapKho.tenSanPham + '</option>');
			});
		},
		error: function(error) {
			console.log('Error:', error);
		}
	});

	$('#tenthietbi').on('change', function() {
		var selectedProductId = $(this).val();
		var conditionMet = false;

		$('#tableTinhTrangThietBi tbody tr:not(:first)').each(function(index, tr) {
			if (selectedProductId == $(tr).find('.idSanPham').val()) {
				$('#tenthietbi').val('');
				toastr.error("Sản phẩm này đã được chọn để đánh giá. Mời chọn sản phẩm khác", "Thất bại", {
					positionClass: "toast-top-right",
				});
				conditionMet = true;
				valid = false;
				return false;
			}
		});

		if (conditionMet) {
			// Nếu điều kiện đã được đáp ứng, không thực thi các dòng sau
			valid = true;
			return;
		} else {
			$.ajax({
				url: 'http://localhost:8080/JWD_MockProject/tinhtrangthietbi/getEntityUpdate',
				type: 'POST',
				contentType: "application/json",
				data: selectedProductId,
				success: function(TinhTrangThietBi_CSVC) {
					console.log("đây là đối tượng: ", TinhTrangThietBi_CSVC);
					if (TinhTrangThietBi_CSVC.idDanhGia !== null && TinhTrangThietBi_CSVC && valid == true) {
						toastr.success("Sản phẩm này đã được đánh giá trong cơ sở dữ liệu. Sửa thông tin để cập nhật", {
							positionClass: "toast-top-right",
						});

						$('#tinhtrang').val(TinhTrangThietBi_CSVC.tinhTrang);
						$('#danhgiaCL').val(TinhTrangThietBi_CSVC.danhGiaChatLuong);
						var id = TinhTrangThietBi_CSVC.idDanhGia;
						$('#iddanhgiahidden').val(id);
						console.log("ĐÂY LÀ ID: ", $('#iddanhgiahidden').val());
					}
				}
			})

			productData.forEach(function(sanPhamNhapKho) {
				if (sanPhamNhapKho.idSPNhapKho == selectedProductId) {
					$('#donvitinh1').html(sanPhamNhapKho.donViTinh);
					$('#soluong1').html(sanPhamNhapKho.soLuong);
					var ngayNhapKho = sanPhamNhapKho.maPhieu.ngayGiao;
					console.log(ngayNhapKho);
					var date = new Date(ngayNhapKho[0], ngayNhapKho[1], ngayNhapKho[2]);

					var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth())).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);

					$('#ngaynhapkho').html(formattedDate);

					var today = new Date();

					var formattedToDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

					$('#ngaydanhgia').html(formattedToDate);
					$('#tinhtrang').val('');
					$('#danhgiaCL').val('');
					$('#nhacungcap1').html(sanPhamNhapKho.maPhieu.nhaCungCap);
					$('#inputhidden').val(selectedProductId);

				}
			})
		}

	})

	// Biến cờ để theo dõi trạng thái lỗi
	var hasError = false;
	var edit = false;

	// Sự kiện để chỉnh sửa hàng khi nhấp đúp
	$(document).on('dblclick', '#tableTinhTrangThietBi tbody tr:not(:first)', function(event) {
		edit = true;
		updateOK = false;
		xuatOK = false;
		if (hasError) {
			return; // Thoát khỏi hàm nếu có lỗi
		}

		var row = $(this);
		var clickedCell = $(event.target).closest('td'); // Lấy ô đã được click

		if (!row.data('editing')) {
			var firstInput = null;
			row.find('td.coltinhtrang, td.coldanhgiaCL, td.colngaydanhgia').each(function() {
				var cell = $(this);
				var currentValue = cell.text().trim(); // Lấy giá trị hiện tại của ô
				if (cell.hasClass('coltinhtrang')) {
					cell.html('<select class="form-control">' +
						'<option value="">Tình trạng</option>' +
						'<option value="Đang sử dụng">Đang sử dụng</option>' +
						'<option value="Không sử dụng">Không sử dụng</option>' +
						'<option value="Đã thanh lý">Đã thanh lý</option>' +
						'<option value="Đã loại bỏ">Đã loại bỏ</option>' +
						'</select>');
					cell.find('select').val(currentValue); // Đặt giá trị hiện tại vào select
				} else {
					if (cell.hasClass('coldanhgiaCL')) {
						cell.html('<select class="form-control" id="danhgiaCL">' +
							'<option value="">Đánh giá chất lượng</option>' +
							'<option value="Tốt">Tốt</option>' +
							'<option value="Trung bình">Trung bình</option>' +
							'<option value="Kém">Kém</option>' +
							'<option value="Hư hỏng">Hư hỏng</option>' +
							'</select>');
						cell.find('select').val(currentValue); // Đặt giá trị hiện tại vào select
					}
				}
				if (!firstInput) {
					firstInput = cell.find('select');
				}
			});
			row.data('editing', true);

			var inputInClickedCell = clickedCell.find('select');
			if (inputInClickedCell.length) {
				inputInClickedCell.focus();
			} else if (firstInput) {
				firstInput.focus();
			}
		} else {
			// Hàng đang ở trạng thái chỉnh sửa, không cho phép chỉnh sửa lần nữa
		}
	});

	$(document).on('click', '#addOrChangeTTTB', function() {
		if (edit == true) {
			$('#tableTinhTrangThietBi tbody tr:not(:first)').each(function() {
				var row = $(this);
				if (row.data('editing')) {
					saveRowChanges(row);
				}
			});
		}
	});

	function saveRowChanges(row) {
		var allInputsValid = true;
		row.find('td:not(:first-child)').each(function() {
			var cell = $(this);
			var cellSelect = cell.find('select');
			var errorSpan = cell.find('.error-message');
			var cellValue = cellSelect.val();
			if (cellValue === '') {
				allInputsValid = false;
				if (errorSpan.length === 0 && cell.hasClass('coltinhtrang')) {
					cell.append('<span class="error-message" style="color: red; font-size: 12px;">Mời chọn tình trạng</span>');
				}
				if (errorSpan.length === 0 && cell.hasClass('coldanhgiaCL')) {
					cell.append('<span class="error-message" style="color: red; font-size: 12px;">Mời đánh giá chất lượng</span>');
				}
				cellSelect.focus();
			} else {
				errorSpan.remove();
				cell.html(cellValue);
			}
		});
		if (allInputsValid) {
			row.data('editing', false);
			updateOK = true;
			edit = false;
		}
	}

	$('#tableTinhTrangThietBi tbody').on('change', 'tr:not(:first) .coltinhtrang, .coldanhgiaCL', function() {
		xuatOK = false;
	});

	$('#search').on("keypress", function(event) {
		if (event.which === 13) { // Kiểm tra phím Enter được nhấn
			search(); // Gọi hàm search
		}
	});

	//-----------------HÀM TÌM KIẾM----------------------------
	function search() {
		const query = $('#search').val();
		$.ajax({
			url: 'http://localhost:8080/JWD_MockProject/tinhtrangthietbi/search',
			method: 'GET',
			data: { query: query },
			success: function(data) {
				xuatOK = true;
				$('#tableTinhTrangThietBi tbody tr:not(:first)').empty();
				data.forEach(function(DanhGiaChatLuongDTO, index) {
					var ngayGiao = new Date(DanhGiaChatLuongDTO.ngayGiao);

					var formattedNgayGiao = ngayGiao.getFullYear() + '-' + ('0' + (ngayGiao.getMonth() + 1)).slice(-2) + '-' + ('0' + ngayGiao.getDate()).slice(-2);

					var ngayDanhGiaTinhTrang = new Date(DanhGiaChatLuongDTO.ngayDanhGiaTinhTrang);

					var formattedNgayDanhGiaTinhTrang = ngayDanhGiaTinhTrang.getFullYear() + '-' + ('0' + (ngayDanhGiaTinhTrang.getMonth() + 1)).slice(-2) + '-' + ('0' + ngayDanhGiaTinhTrang.getDate()).slice(-2);
					var newRow =
						'<tr>' +
						'<td class="sTT">' + (index + 1) + '<input class="idSanPham" type="hidden" value="' + DanhGiaChatLuongDTO.idSPNhapKho + '"></td>' +
						'<td class="coltenthietbi"><input class="idDanhGia" type="hidden" value="' + DanhGiaChatLuongDTO.idDanhGia + '">' + DanhGiaChatLuongDTO.tenSanPham + '</td>' +
						'<td class="colngaynhapkho text-center">' + formattedNgayGiao + '</td>' +
						'<td class="coldonvitinh1">' + DanhGiaChatLuongDTO.donViTinh + '</td>' +
						'<td class="colsoluong1 text-right">' + DanhGiaChatLuongDTO.soLuong + '</td>' +
						'<td class="coltinhtrang">' + DanhGiaChatLuongDTO.tinhTrang + '</td>' +
						'<td class="coldanhgiaCL">' + DanhGiaChatLuongDTO.danhGiaChatLuong + '</td>' +
						'<td class="colngaydanhgia  text-center">' + formattedNgayDanhGiaTinhTrang + '</td>' +
						'<td class="colnhacungcap1">' + DanhGiaChatLuongDTO.nhaCungCap + '</td>' +
						+'</tr>';

					$('#tableTinhTrangThietBi tbody').append(newRow);
				})
				$('#iddanhgiahidden').val('');
				$('#tenthietbi').val('');
				$('#ngaynhapkho').html('');
				$('#donvitinh1').html('');
				$('#soluong1').html('');
				$('#tinhtrang').val('');
				$('#danhgiaCL').val('');
				$('#ngaydanhgia').html('');
				$('#nhacungcap1').html('');
			}
		})
	}

	function getId(listGroupTinhTrangThietBi) {
		$('#tableTinhTrangThietBi tbody tr:not(:first)').each(function(index, tr) {
			$(tr).find(".idDanhGia").val(listGroupTinhTrangThietBi[index].idDanhGia);
		});
	}

})