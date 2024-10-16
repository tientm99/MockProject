/**
 * Controller quản lý các hoạt động liên quan đến tình trạng thiết bị cơ sở vật chất.
 */
package tien.java.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tien.java.web.DTO.DanhGiaChatLuongDTO;
import tien.java.web.DTO.TinhTrangThietBi_CSVC_DTO;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.entity.TinhTrangThietBi_CSVC;
import tien.java.web.service.SanPhamNhapKhoService;
import tien.java.web.service.TTTB_CSVC_Service;

/**
 * Controller quản lý các hoạt động liên quan đến tình trạng thiết bị cơ sở vật
 * chất.
 * 
 * TTTB_CSVC_Controller			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Controller
@RequestMapping("/tinhtrangthietbi")
public class TTTB_CSVC_Controller {

	@Autowired
	private TTTB_CSVC_Service tttb_CSVC_Service;

	@Autowired
	private SanPhamNhapKhoService sanPhamNhapKhoService;

	/**
	 * Khởi tạo đối tượng TinhTrangThietBi_CSVC
	 * 
	 * @return đối tượng TinhTrangThietBi_CSVC mới
	 */
	@ModelAttribute(value = "tinhtrang")
	public TinhTrangThietBi_CSVC getEntity() {
		return new TinhTrangThietBi_CSVC();
	}

	/**
	 * Hiển thị danh sách tình trạng thiết bị
	 * 
	 * @param model mô hình dữ liệu
	 * @return tên của view để hiển thị danh sách
	 */
	@GetMapping("/displaylist")
	public String list(Model model) {
		return "tinhtrangthietbiCSVC/tttb_csvc";
	}

	/**
	 * Lấy danh sách tất cả sản phẩm nhập kho
	 * 
	 * @return ResponseEntity chứa danh sách sản phẩm nhập kho
	 */
	@GetMapping("/listSanPhamNhapKho")
	@ResponseBody
	public ResponseEntity<List<SanPhamNhapKho>> getAll() {
		return ResponseEntity.ok(sanPhamNhapKhoService.getAll());
	}

	/**
	 * Lưu dữ liệu tình trạng thiết bị
	 * 
	 * @param tinhTrangThietBi_CSVC_DTOs danh sách các đối tượng
	 *                                   TinhTrangThietBi_CSVC_DTO cần lưu
	 * @return ResponseEntity chứa danh sách tình trạng thiết bị sau khi lưu
	 */
	@PostMapping("/savetinhtrangthietbi")
	@ResponseBody
	public ResponseEntity<List<TinhTrangThietBi_CSVC>> saveData(
			@RequestBody List<TinhTrangThietBi_CSVC_DTO> tinhTrangThietBi_CSVC_DTOs) {
		try {
			return ResponseEntity.ok(tttb_CSVC_Service.saveAll(tinhTrangThietBi_CSVC_DTOs));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Lấy đối tượng TinhTrangThietBi_CSVC cần cập nhật
	 * 
	 * @param idSPNhapKho ID của sản phẩm nhập kho
	 * @return ResponseEntity chứa đối tượng TinhTrangThietBi_CSVC cần cập nhật
	 */
	@PostMapping("/getEntityUpdate")
	public ResponseEntity<TinhTrangThietBi_CSVC> getUpdate(@RequestBody String idSPNhapKho) {
		Long idSPNhapKhoLong = Long.parseLong(idSPNhapKho);
		return ResponseEntity.ok(tttb_CSVC_Service.getEntityUpdate(idSPNhapKhoLong));
	}
	
	/**
	 * Tìm kiếm đánh giá chất lượng CSVC dựa trên từ khóa.
	 *
	 * @param keyword Từ khóa tìm kiếm.
	 * @return ResponseEntity<List<DanhGiaChatLuongDTO>> Đối tượng ResponseEntity chứa danh sách các đánh giá chất lượng CSVC
	 *         phù hợp với từ khóa tìm kiếm, hoặc trả về danh sách trống nếu không tìm thấy kết quả.
	 */
	@GetMapping("/search")
	public ResponseEntity<List<DanhGiaChatLuongDTO>> search(@RequestParam("query") String keyword){
	    return ResponseEntity.ok(tttb_CSVC_Service.search(keyword));
	}


	/**
	 * Xuất dữ liệu đánh giá tình trạng thiết bị ra file Excel
	 * 
	 * @param data     danh sách các đối tượng DanhGiaChatLuongDTO cần xuất
	 * @param response đối tượng HttpServletResponse để gửi file về client
	 * @throws IOException khi xảy ra lỗi đọc ghi file
	 */
	@PostMapping("/exportToExcelTTTB")
	public void exportToExcelTTTB(@RequestBody List<DanhGiaChatLuongDTO> data, HttpServletResponse response)
			throws IOException {
		tttb_CSVC_Service.exportToExcelTTTB(data, response);
	}

}
