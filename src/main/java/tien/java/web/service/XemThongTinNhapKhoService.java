/**
 * Service cung cấp các phương thức để xem thông tin nhập kho.
 */
package tien.java.web.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tien.java.web.DTO.SanPhamNhapKhoDTO;
import tien.java.web.DTO.listNgay;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.repository.PhieuNhapKhoRepository;
import tien.java.web.repository.XemThongTinNhapKhoRepository;

/**
* Service quản lý các hoạt động liên quan đến XemThongTinNhapKho.
* 
* XemThongTinNhapKhoService			
*			
* Date: 30-05-2024		
*			
* DATE                 AUTHOR          DESCRIPTION			
* --------------------------------------------------------------			
* 30-05-2024        Tran Minh Tien        Create			
*/
@Service
@Transactional
public class XemThongTinNhapKhoService {

	@Autowired
	private XemThongTinNhapKhoRepository xemThongTinNhapKhoRepository;

	@Autowired
	private PhieuNhapKhoRepository phieuNhapKhoRepository;

	/**
	 * Lấy danh sách các ngày nhập kho.
	 * 
	 * @return danh sách các ngày nhập kho.
	 */
	public List<listNgay> listDate() {
		return xemThongTinNhapKhoRepository.listDate();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho theo ngày giao.
	 * 
	 * @param ngayGiao ngày giao hàng.
	 * @return danh sách sản phẩm nhập kho.
	 */
	public List<SanPhamNhapKho> getListSanPham(String maPhieu) {
		PhieuNhapKho phieuNhapKho = phieuNhapKhoRepository.findByMaPhieu(maPhieu);
		return xemThongTinNhapKhoRepository.getListSanPham(phieuNhapKho);
	}

	/**
	 * Lấy danh sách phiếu nhập kho theo ngày giao hàng.
	 * 
	 * @param ngayGiao ngày giao hàng.
	 * @return danh sách phiếu nhập kho.
	 */
	public PhieuNhapKho getListPhieu(String maPhieu) {
		return phieuNhapKhoRepository.findByMaPhieu(maPhieu);
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho chưa thanh toán.
	 * 
	 * @return danh sách sản phẩm nhập kho chưa thanh toán.
	 */
	public List<SanPhamNhapKhoDTO> getListChuaThanhToan() {
		return xemThongTinNhapKhoRepository.getListChuaThanhToan();
	}

	/**
	 * Lấy danh sách phiếu nhập kho chưa thanh toán.
	 * 
	 * @return danh sách phiếu nhập kho chưa thanh toán.
	 */
	public List<PhieuNhapKho> getListPhieuChuaThanhToan() {
		return xemThongTinNhapKhoRepository.getListPhieuChuaThanhToan();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho trong khoảng thời gian từ ngày đến ngày.
	 * 
	 * @param tuNgay  ngày bắt đầu.
	 * @param denNgay ngày kết thúc.
	 * @return danh sách sản phẩm nhập kho.
	 */
	public List<SanPhamNhapKhoDTO> getListTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
		return xemThongTinNhapKhoRepository.getListTheoNgay(tuNgay, denNgay);
	}

	/**
	 * Lấy danh sách phiếu nhập kho trong khoảng thời gian từ ngày đến ngày.
	 * 
	 * @param tuNgay  ngày bắt đầu.
	 * @param denNgay ngày kết thúc.
	 * @return danh sách phiếu nhập kho.
	 */
	public List<PhieuNhapKho> getListPhieuTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
		return xemThongTinNhapKhoRepository.getListPhieuTheoNgay(tuNgay, denNgay);
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho trong tháng của năm.
	 * 
	 * @param thang tháng.
	 * @return danh sách sản phẩm nhập kho.
	 */
	public List<SanPhamNhapKhoDTO> getListTheoThangNam(Integer thang) {
		return xemThongTinNhapKhoRepository.getListTheoThangNam(thang);
	}

	/**
	 * Lấy danh sách phiếu nhập kho trong tháng của năm.
	 * 
	 * @param thang tháng.
	 * @return danh sách phiếu nhập kho.
	 */
	public List<PhieuNhapKho> getListPhieuTheoThangNam(Integer thang) {
		return xemThongTinNhapKhoRepository.getListPhieuTheoThangNam(thang);
	}

	/**
	 * Cập nhật thông tin sản phẩm nhập kho.
	 *
	 * @param sanPhamNhapKhoDTOs Danh sách các đối tượng SanPhamNhapKhoDTO chứa thông tin sản phẩm nhập kho cần cập nhật.
	 */
	public void updateXemThongTin(List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs) {
	    xemThongTinNhapKhoRepository.updateXemThongTin(sanPhamNhapKhoDTOs);
	}

	/**
	 * Cập nhật thông tin phiếu nhập kho.
	 *
	 * @param phieuNhapKho Đối tượng PhieuNhapKho chứa thông tin phiếu nhập kho cần cập nhật.
	 */
	public void updatePhieu(PhieuNhapKho phieuNhapKho) {
	    xemThongTinNhapKhoRepository.updatePhieu(phieuNhapKho);
	}

}
