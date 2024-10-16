package tien.java.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tien.java.web.DTO.HienThi1DTO;
import tien.java.web.DTO.HienThiDTO;
import tien.java.web.DTO.RequestDTO;
import tien.java.web.DTO.TheoNgayDTO;
import tien.java.web.DTO.listNgay;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.service.XemThongTinNhapKhoService;

/**
 * Controller quản lý các hoạt động hiển thị thông tin nhập kho.
 * 
 * XemThongTinNhapKhoController			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Controller
@RequestMapping("/hienthi")
public class XemThongTinNhapKhoController {

	@Autowired
	private XemThongTinNhapKhoService xemThongTinNhapKhoService;
	
	/**
	 * Khởi tạo đối tượng PhieuNhapKho.
	 * 
	 * @return đối tượng PhieuNhapKho mới.
	 */
	@ModelAttribute(value = "phieunhap")
	public PhieuNhapKho getPhieuNhapKho() {
		return new PhieuNhapKho();
	}

	/**
	 * Hiển thị form nhập kho.
	 * 
	 * @param model mô hình dữ liệu.
	 * @return tên của view để hiển thị form.
	 */
	@GetMapping("/formdisplay")
	public String form(Model model) {
		return "hienthinhapkho/xemthongtinnhapkho";
	}

	/**
	 * Lấy danh sách các ngày nhập kho.
	 * 
	 * @return ResponseEntity chứa danh sách các ngày.
	 */
	@GetMapping("/listDate")
	@ResponseBody
	public ResponseEntity<List<listNgay>> listDate() {
		System.err.println("111111111111");
		return ResponseEntity.ok(xemThongTinNhapKhoService.listDate());
	}

	/**
	 * Lấy danh sách các phiếu nhập kho chưa thanh toán.
	 * 
	 * @return ResponseEntity chứa thông tin hiển thị của các phiếu nhập kho chưa
	 *         thanh toán.
	 */
	@GetMapping("/listChuaThanhToan")
	@ResponseBody
	public ResponseEntity<HienThiDTO> getListChuaThanhToan() {
		HienThiDTO hienThi = new HienThiDTO();
		hienThi.setPhieuNhapKho(xemThongTinNhapKhoService.getListPhieuChuaThanhToan());
		hienThi.setSanPhamNhapKhoDTOs(xemThongTinNhapKhoService.getListChuaThanhToan());
		return ResponseEntity.ok(hienThi);
	}

	/**
	 * Lấy chi tiết sản phẩm nhập kho theo ngày.
	 * 
	 * @param date ngày cần lấy thông tin.
	 * @return ResponseEntity chứa thông tin hiển thị của các sản phẩm nhập kho theo
	 *         ngày.
	 */
	@PostMapping("/sanPhamDetail")
	public ResponseEntity<HienThi1DTO> selectDate(@RequestBody String maPhieu) {
		maPhieu = maPhieu.replaceAll("\"", "");
		HienThi1DTO hienThi = new HienThi1DTO();
		hienThi.setSanPhamNhapKhos(xemThongTinNhapKhoService.getListSanPham(maPhieu));
		hienThi.setPhieuNhapKho(xemThongTinNhapKhoService.getListPhieu(maPhieu));
		return ResponseEntity.ok(hienThi);
	}

	/**
	 * Lấy thông tin nhập kho trong khoảng thời gian từ ngày đến ngày.
	 * 
	 * @param request đối tượng chứa thông tin từ ngày đến ngày.
	 * @return ResponseEntity chứa thông tin hiển thị của các sản phẩm nhập kho
	 *         trong khoảng thời gian từ ngày đến ngày.
	 */
	@PostMapping("/listTheoNgay")
	public ResponseEntity<HienThiDTO> listTheoNgay(@RequestBody TheoNgayDTO request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate tuNgay = LocalDate.parse(request.getTuNgay(), formatter);
		LocalDate denNgay = LocalDate.parse(request.getDenNgay(), formatter);
		HienThiDTO hienThi = new HienThiDTO();
		hienThi.setSanPhamNhapKhoDTOs(xemThongTinNhapKhoService.getListTheoNgay(tuNgay, denNgay));
		hienThi.setPhieuNhapKho(xemThongTinNhapKhoService.getListPhieuTheoNgay(tuNgay, denNgay));
		return ResponseEntity.ok(hienThi);
	}

	/**
	 * Lấy thông tin nhập kho theo tháng và năm.
	 * 
	 * @param thang chuỗi chứa thông tin tháng.
	 * @return ResponseEntity chứa thông tin hiển thị của các sản phẩm nhập kho theo
	 *         tháng và năm.
	 */
	@PostMapping("/listTheoThangNam")
	public ResponseEntity<HienThiDTO> listTheoThangNam(@RequestBody String thang) {
		thang = thang.replaceAll("\"", "");
		HienThiDTO hienThi = new HienThiDTO();
		Integer thangInt = Integer.parseInt(thang);
		hienThi.setSanPhamNhapKhoDTOs(xemThongTinNhapKhoService.getListTheoThangNam(thangInt));
		hienThi.setPhieuNhapKho(xemThongTinNhapKhoService.getListPhieuTheoThangNam(thangInt));
		return ResponseEntity.ok(hienThi);
	}
	
	/**
	 * Cập nhật thông tin nhập kho và thông tin sản phẩm nhập kho.
	 *
	 * @param request Đối tượng RequestDTO chứa thông tin phiếu nhập kho và danh sách sản phẩm nhập kho.
	 * @return ResponseEntity<?> Đối tượng ResponseEntity đại diện cho kết quả trả về từ server.
	 *         Trả về ResponseEntity.ok() nếu cập nhật thành công, hoặc trả về thông báo lỗi nếu có ngoại lệ xảy ra.
	 */
	@PostMapping("/updateXemThongTin")
	public ResponseEntity<?> updateXemThongTin(@RequestBody RequestDTO request) {
	    try {
	        xemThongTinNhapKhoService.updatePhieu(request.getPhieuNhapKho());
	        xemThongTinNhapKhoService.updateXemThongTin(request.getSanPhamNhapKhoDTOs());
	        return (ResponseEntity<?>) ResponseEntity.ok();
	    } catch (Exception e) {
	        return ResponseEntity.ok("lỗi");
	    }
	}

}
