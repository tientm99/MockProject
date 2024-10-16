package tien.java.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tien.java.web.DTO.RequestDTO;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.service.PhieuNhapKhoService;
import tien.java.web.service.SanPhamNhapKhoService;

/**
 * Controller quản lý các hoạt động liên quan đến Phiếu Nhập Kho
 * 
 * PhieuNhapKhoController			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Controller
@RequestMapping("/phieunhapkho")
public class PhieuNhapKhoController {

	@Autowired
	private PhieuNhapKhoService phieuNhapKhoService;

	@Autowired
	private SanPhamNhapKhoService sanPhamNhapKhoService;

	/**
	 * Khởi tạo đối tượng PhieuNhapKho
	 * 
	 * @return đối tượng PhieuNhapKho mới
	 */
	@ModelAttribute(value = "phieunhap")
	public PhieuNhapKho getPhieuNhapKho() {
		return new PhieuNhapKho();
	}

	/**
	 * Hiển thị form nhập kho
	 * 
	 * @param model mô hình dữ liệu
	 * @return tên của view để hiển thị form
	 */
	@GetMapping("/forminsert")
	public String form(Model model) {
		return "nhapkho/phieunhapkho";
	}

	/**
	 * Lưu dữ liệu từ form
	 * 
	 * @param request đối tượng RequestDTO chứa dữ liệu từ form
	 * @param result  kết quả kiểm tra dữ liệu đầu vào
	 * @return ResponseEntity chứa thông báo lỗi hoặc thành công
	 */
	@PostMapping("/saveData")
	@ResponseBody
	public ResponseEntity<List<SanPhamNhapKho>> saveData(@Valid @RequestBody RequestDTO request, BindingResult result) {
		try {
			String maPhieu = request.getPhieuNhapKho().getMaPhieu();
			PhieuNhapKho existingPhieuNhapKho = phieuNhapKhoService.findByMaPhieu(maPhieu);

			if (existingPhieuNhapKho == null) {
				// Nếu phiếu nhập kho không tồn tại, tạo mới và lưu dữ liệu
				phieuNhapKhoService.save(request.getPhieuNhapKho());
				List<SanPhamNhapKho> savedSanPhamNhapKho = sanPhamNhapKhoService
						.saveAll(request.getSanPhamNhapKhoDTOs());
				return ResponseEntity.ok(savedSanPhamNhapKho);
			} else {
				// Nếu phiếu nhập kho đã tồn tại, kiểm tra tính hợp lệ và lưu dữ liệu
				if (request.getSanPhamNhapKhoDTOs().get(0).getIdSPNhapKho() != null) {
					phieuNhapKhoService.save(request.getPhieuNhapKho());
					List<SanPhamNhapKho> savedSanPhamNhapKho = sanPhamNhapKhoService
							.saveAll(request.getSanPhamNhapKhoDTOs());
					return ResponseEntity.ok(savedSanPhamNhapKho);
				} else {
					// Trả về mã 409 Conflict nếu phiếu nhập kho đã tồn tại nhưng không hợp lệ
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
			}
		} catch (Exception e) {
			// Ghi log lỗi và trả về mã 500 Internal Server Error
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Xử lý lỗi khi dữ liệu không hợp lệ
	 * 
	 * @param ex ngoại lệ MethodArgumentNotValidException
	 * @return Map chứa thông báo lỗi
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	/**
	 * Xuất dữ liệu ra file Excel
	 * 
	 * @param data     đối tượng RequestDTO chứa dữ liệu cần xuất
	 * @param response đối tượng HttpServletResponse để gửi file về client
	 * @throws IOException khi xảy ra lỗi đọc ghi file
	 */
	@PostMapping("/exportToExcel")
	public void exportToExcel(@RequestBody RequestDTO data, HttpServletResponse response) throws IOException {
		phieuNhapKhoService.exportToExcel(data, response);
	}
}
