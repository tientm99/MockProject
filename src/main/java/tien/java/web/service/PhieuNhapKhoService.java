package tien.java.web.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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

import tien.java.web.DTO.RequestDTO;
import tien.java.web.DTO.SanPhamNhapKhoDTO;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.repository.PhieuNhapKhoRepository;

/**
 * Service quản lý các hoạt động liên quan đến phiếu nhập kho.
 * 
 * PhieuNhapKhoService			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Service
@Transactional
public class PhieuNhapKhoService {

	@Autowired
	private PhieuNhapKhoRepository phieuNhapKhoRepository;

	/**
	 * Lưu một phiếu nhập kho vào cơ sở dữ liệu.
	 * 
	 * @param phieuNhapKho đối tượng PhieuNhapKho cần lưu.
	 */
	public void save(PhieuNhapKho phieuNhapKho) {
		phieuNhapKhoRepository.save(phieuNhapKho);
	}

	/**
	 * Tìm kiếm một phiếu nhập kho theo mã phiếu.
	 * 
	 * @param maPhieu mã của phiếu nhập kho cần tìm.
	 * @return đối tượng PhieuNhapKho nếu tìm thấy, ngược lại trả về null.
	 */
	public PhieuNhapKho findByMaPhieu(String maPhieu) {
		return phieuNhapKhoRepository.findByMaPhieu(maPhieu);
	}

	/**
	 * Kiểm tra sự tồn tại của mã phiếu nhập kho.
	 * 
	 * @param maPhieu mã của phiếu nhập kho cần kiểm tra.
	 * @return true nếu mã phiếu tồn tại, ngược lại false.
	 */
	public boolean maPhieuExist(String maPhieu) {
		return phieuNhapKhoRepository.maPhieuExist(maPhieu);
	}
	
	public void exportToExcel(RequestDTO data, HttpServletResponse response) throws IOException {
		// Tạo workbook mới
		Workbook workbook = new XSSFWorkbook();

		// Tạo sheet mới
		org.apache.poi.ss.usermodel.Sheet sheet = (org.apache.poi.ss.usermodel.Sheet) workbook
				.createSheet("PhieuNhapKho Data");

		// Tạo font in đậm
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);

		// Tạo CellStyle áp dụng font in đậm
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Viết dữ liệu PhieuNhapKho vào các dòng
		Row headerRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
		String[] phieuNhapKhoHeaders = { "Ma Phieu", "Nha Cung Cap", "Ten Nguoi Giao Hang", "Ngay Giao",
				"Han Thanh Toan", "Da Tra", "Con No", "Thue GTGT", "Tong Truoc Thue", "Tong Sau Thue" };
		for (int i = 0; i < phieuNhapKhoHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(phieuNhapKhoHeaders[i]);
			cell.setCellStyle(headerCellStyle);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String ngayGiaoString = data.getPhieuNhapKho().getNgayGiao().format(formatter);
		String hanThanhToanString = data.getPhieuNhapKho().getHanThanhToan().format(formatter);

		Row dataRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(1);
		dataRow.createCell(0).setCellValue(data.getPhieuNhapKho().getMaPhieu());
		dataRow.createCell(1).setCellValue(data.getPhieuNhapKho().getNhaCungCap());
		dataRow.createCell(2).setCellValue(data.getPhieuNhapKho().getTenNguoiGiaoHang());
		dataRow.createCell(3).setCellValue(ngayGiaoString);
		dataRow.createCell(4).setCellValue(hanThanhToanString);
		dataRow.createCell(5).setCellValue(data.getPhieuNhapKho().getDaTra());
		dataRow.createCell(6).setCellValue(data.getPhieuNhapKho().getConNo());
		dataRow.createCell(7).setCellValue(data.getPhieuNhapKho().getThueGTGT());
		dataRow.createCell(8).setCellValue(data.getPhieuNhapKho().getTongTruocThue());
		dataRow.createCell(9).setCellValue(data.getPhieuNhapKho().getTongSauThue());

		// Viết dữ liệu SanPhamNhapKhoDTOs vào các dòng
		Row headerRow1 = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(3);
		String[] headers = { "Tên Sản Phẩm", "Đơn Vị Tính", "Số Lượng", "Đơn Giá", "Thành Tiền", "Bảo Hành / Bảo Trì",
				"Ghi Chú", "Mã Phiếu" };
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow1.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerCellStyle);
		}
		int rowNum = 4; // Bắt đầu từ dòng 4 để chừa chỗ cho header
		for (SanPhamNhapKhoDTO sanPham : data.getSanPhamNhapKhoDTOs()) {
			Row row = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNum++);
			String baoHanh_BaoTriString = sanPham.getBaoHanh_BaoTri().format(formatter);
			row.createCell(0).setCellValue(sanPham.getTenSanPham());
			row.createCell(1).setCellValue(sanPham.getDonViTinh());
			row.createCell(2).setCellValue(sanPham.getSoLuong());
			row.createCell(3).setCellValue(sanPham.getDonGia());
			row.createCell(4).setCellValue(sanPham.getThanhTien());
			row.createCell(5).setCellValue(baoHanh_BaoTriString);
			row.createCell(6).setCellValue(sanPham.getGhiChu());
			row.createCell(7).setCellValue(sanPham.getMaPhieu());
		}

		// Lưu file Excel vào server
		String filePath = "D:\\phieunhapkho.xlsx"; // Thay đổi đường dẫn này theo nhu cầu của bạn
		File file = new File(filePath);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}

		// Cài đặt header của response
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=phieunhapkho.xlsx");

		// Ghi workbook vào HttpServletResponse
		workbook.write(response.getOutputStream());
		workbook.close();
	}
}
