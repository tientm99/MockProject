/**
 * Service quản lý các hoạt động liên quan đến tình trạng thiết bị và CSVC.
 */
package tien.java.web.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tien.java.web.DTO.DanhGiaChatLuongDTO;
import tien.java.web.DTO.TinhTrangThietBi_CSVC_DTO;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.entity.TinhTrangThietBi_CSVC;
import tien.java.web.repository.SanPhamNhapKhoRepository;
import tien.java.web.repository.TTTB_CSVC_Repository;

/**
* Service quản lý các hoạt động liên quan đến TinhTrangThietBi_CSVC.
* 
* TTTB_CSVC_Service			
*			
* Date: 30-05-2024		
*			
* DATE                 AUTHOR          DESCRIPTION			
* --------------------------------------------------------------			
* 30-05-2024        Tran Minh Tien        Create			
*/
@Service
@Transactional
public class TTTB_CSVC_Service {
	
	@Autowired
	private TTTB_CSVC_Repository tttb_CSVC_Repository;
	
	@Autowired
	private SanPhamNhapKhoRepository sanPhamNhapKhoRepository;
	
	/**
	 * Lưu tất cả các đánh giá về tình trạng thiết bị và CSVC.
	 * 
	 * @param tinhTrangThietBi_CSVC_DTOs danh sách các DTO của tình trạng thiết bị và CSVC cần lưu.
	 * @return danh sách các đánh giá đã được lưu.
	 */
	public List<TinhTrangThietBi_CSVC> saveAll(List<TinhTrangThietBi_CSVC_DTO> tinhTrangThietBi_CSVC_DTOs) {
		List<TinhTrangThietBi_CSVC> tinhTrangThietBi_CSVCs = new ArrayList<TinhTrangThietBi_CSVC>();
		for (TinhTrangThietBi_CSVC_DTO tinhTrangThietBi_CSVC_DTO : tinhTrangThietBi_CSVC_DTOs) {
			TinhTrangThietBi_CSVC tinhTrangThietBi_CSVC = new TinhTrangThietBi_CSVC();
			tinhTrangThietBi_CSVC.setIdDanhGia(tinhTrangThietBi_CSVC_DTO.getIdDanhGia());
			tinhTrangThietBi_CSVC.setTinhTrang(tinhTrangThietBi_CSVC_DTO.getTinhTrang());
			tinhTrangThietBi_CSVC.setDanhGiaChatLuong(tinhTrangThietBi_CSVC_DTO.getDanhGiaChatLuong());
			tinhTrangThietBi_CSVC.setNgayDanhGiaTinhTrang(tinhTrangThietBi_CSVC_DTO.getNgayDanhGiaTinhTrang());
			
			SanPhamNhapKho sanPhamNhapKho = sanPhamNhapKhoRepository.findById(tinhTrangThietBi_CSVC_DTO.getIdSPNhapKho());
			
			tinhTrangThietBi_CSVC.setSanPhamNhapKho(sanPhamNhapKho);
			
			tinhTrangThietBi_CSVCs.add(tinhTrangThietBi_CSVC);
		}
		return tttb_CSVC_Repository.saveAll(tinhTrangThietBi_CSVCs);
	}
	
	/**
	 * Lấy đối tượng đánh giá cập nhật dựa trên ID sản phẩm nhập kho.
	 * 
	 * @param idSPNhapKho ID của sản phẩm nhập kho.
	 * @return đối tượng TinhTrangThietBi_CSVC được cập nhật.
	 */
	public TinhTrangThietBi_CSVC getEntityUpdate(Long idSPNhapKho) {
		SanPhamNhapKho sanPhamNhapKho = sanPhamNhapKhoRepository.findById(idSPNhapKho);
		return tttb_CSVC_Repository.getEntityUpdate(sanPhamNhapKho);
	}
	
	public List<DanhGiaChatLuongDTO> search(String keyword){
		return tttb_CSVC_Repository.search(keyword);
	}
	
	public void exportToExcelTTTB(List<DanhGiaChatLuongDTO> data, HttpServletResponse response)
			throws IOException {
		// Tạo workbook mới
		Workbook workbook = new XSSFWorkbook();
		System.err.println("-------------------------------------------" + data.size());
		// Tạo sheet mới
		org.apache.poi.ss.usermodel.Sheet sheet = (org.apache.poi.ss.usermodel.Sheet) workbook
				.createSheet("TinhTrangThietBi Data");

		// Tạo font in đậm
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);

		// Tạo CellStyle áp dụng font in đậm
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Viết dữ liệu đánh giá chất lượng vào các dòng
		Row headerRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
		String[] danhGiaChatLuongHeaders = { "STT", "Tên thiết bị", "Ngày nhập kho", "Đơn vị tính", "Số lượng",
				"Tình trạng", "Đánh giá chất lượng", "Ngày đánh giá", "Nhà cung cấp" };
		for (int i = 0; i < danhGiaChatLuongHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(danhGiaChatLuongHeaders[i]);
			cell.setCellStyle(headerCellStyle);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		int rowNum = 2;
		for (DanhGiaChatLuongDTO danhGia : data) {
			String ngayNhapKho = danhGia.getNgayGiao().format(formatter);
			String ngayDanhGiaChatLuong = danhGia.getNgayDanhGiaTinhTrang().format(formatter);
			Row dataRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNum++);
			dataRow.createCell(0).setCellValue(danhGia.getsTT());
			dataRow.createCell(1).setCellValue(danhGia.getTenSanPham());
			dataRow.createCell(2).setCellValue(ngayNhapKho);
			dataRow.createCell(3).setCellValue(danhGia.getDonViTinh());
			dataRow.createCell(4).setCellValue(danhGia.getSoLuong());
			dataRow.createCell(5).setCellValue(danhGia.getTinhTrang());
			dataRow.createCell(6).setCellValue(danhGia.getDanhGiaChatLuong());
			dataRow.createCell(7).setCellValue(ngayDanhGiaChatLuong);
			dataRow.createCell(8).setCellValue(danhGia.getNhaCungCap());
		}

		// Lưu file Excel vào server
		String filePath = "D:\\phieunhapkho.xlsx"; // Thay đổi đường dẫn này theo nhu cầu của bạn
		File file = new File(filePath);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}

		// Cài đặt header của response
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=DanhGiaTinhTrang.xlsx");

		// Ghi workbook vào HttpServletResponse
		workbook.write(response.getOutputStream());
		workbook.close();
	}

}
