package tien.java.web.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tien.java.web.DTO.SanPhamNhapKhoDTO;
import tien.java.web.DTO.listNgay;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;

/**
 * Repository cung cấp các phương thức để truy vấn và thao tác với thông tin
 * PhieuNhapKho trong cơ sở dữ liệu.
 * 
 * XemThongTinNhapKhoRepository			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Repository
public class XemThongTinNhapKhoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Lấy danh sách ngày nhập kho không trùng lặp.
	 *
	 * @return Danh sách các ngày nhập kho.
	 */
	public List<listNgay> listDate() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT new tien.java.web.DTO.listNgay( p.maPhieu, p.ngayGiao) FROM tien.java.web.entity.PhieuNhapKho p", listNgay.class)
				.getResultList();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho theo ngày.
	 *
	 * @param ngayGiao Ngày nhập kho.
	 * @return Danh sách sản phẩm nhập kho.
	 */
	public List<SanPhamNhapKho> getListSanPham(PhieuNhapKho phieuNhapKho) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM SanPhamNhapKho WHERE maPhieu =: maPhieu", SanPhamNhapKho.class)
				.setParameter("maPhieu", phieuNhapKho).getResultList();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho chưa thanh toán.
	 *
	 * @return Danh sách sản phẩm nhập kho chưa thanh toán.
	 */
	public List<SanPhamNhapKhoDTO> getListChuaThanhToan() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(
				"select new tien.java.web.DTO.SanPhamNhapKhoDTO( sp.idSPNhapKho, sp.tenSanPham, sp.donViTinh, sp.soLuong, sp.donGia,\r\n"
						+ "sp.thanhTien, sp.baoHanh_BaoTri, sp.ghiChu, sp.maPhieu.maPhieu) from tien.java.web.entity.PhieuNhapKho p \r\n"
						+ "join p.sanPhamNhapKho sp \r\n" + "where p.daTra = 0",
				SanPhamNhapKhoDTO.class).getResultList();
	}

	/**
	 * Lấy danh sách phiếu nhập kho theo ngày.
	 *
	 * @param ngayGiao Ngày nhập kho.
	 * @return Danh sách phiếu nhập kho.
	 */
	public PhieuNhapKho getListPhieu(String maPhieu) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(PhieuNhapKho.class, maPhieu);
	}

	/**
	 * Lấy danh sách phiếu nhập kho chưa thanh toán.
	 *
	 * @return Danh sách phiếu nhập kho chưa thanh toán.
	 */
	public List<PhieuNhapKho> getListPhieuChuaThanhToan() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From PhieuNhapKho WHERE daTra = 0", PhieuNhapKho.class).getResultList();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho trong khoảng thời gian từ ngày đến ngày.
	 *
	 * @param tuNgay  Ngày bắt đầu.
	 * @param denNgay Ngày kết thúc.
	 * @return Danh sách sản phẩm nhập kho trong khoảng thời gian.
	 */
	public List<SanPhamNhapKhoDTO> getListTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(
				"select new tien.java.web.DTO.SanPhamNhapKhoDTO( sp.idSPNhapKho, sp.tenSanPham, sp.donViTinh, sp.soLuong, sp.donGia,\r\n"
						+ "sp.thanhTien, sp.baoHanh_BaoTri, sp.ghiChu, sp.maPhieu.maPhieu) from tien.java.web.entity.PhieuNhapKho p \r\n"
						+ "join p.sanPhamNhapKho sp \r\n" + "where p.ngayGiao >= :tuNgay AND p.ngayGiao <= :denNgay",
				SanPhamNhapKhoDTO.class).setParameter("tuNgay", tuNgay).setParameter("denNgay", denNgay)
				.getResultList();
	}

	/**
	 * Lấy danh sách phiếu nhập kho trong khoảng thời gian từ ngày đến ngày.
	 *
	 * @param tuNgay  Ngày bắt đầu.
	 * @param denNgay Ngày kết thúc.
	 * @return Danh sách phiếu nhập kho trong khoảng thời gian.
	 */
	public List<PhieuNhapKho> getListPhieuTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("From PhieuNhapKho WHERE ngayGiao >= :tuNgay AND ngayGiao <= :denNgay", PhieuNhapKho.class)
				.setParameter("tuNgay", tuNgay).setParameter("denNgay", denNgay).getResultList();
	}

	/**
	 * Lấy danh sách sản phẩm nhập kho theo tháng và năm.
	 *
	 * @param thang Tháng.
	 * @return Danh sách sản phẩm nhập kho theo tháng và năm.
	 */
	public List<SanPhamNhapKhoDTO> getListTheoThangNam(Integer thang) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(
				"select new tien.java.web.DTO.SanPhamNhapKhoDTO( sp.idSPNhapKho, sp.tenSanPham, sp.donViTinh, sp.soLuong, sp.donGia,\r\n"
						+ "sp.thanhTien, sp.baoHanh_BaoTri, sp.ghiChu, sp.maPhieu.maPhieu) from tien.java.web.entity.PhieuNhapKho p \r\n"
						+ "join p.sanPhamNhapKho sp \r\n" + "where MONTH(p.ngayGiao) = :thang",
				SanPhamNhapKhoDTO.class).setParameter("thang", thang).getResultList();
	}

	/**
	 * Lấy danh sách các phiếu nhập kho theo tháng và năm.
	 *
	 * @param thang Tháng cần lấy phiếu nhập kho.
	 * @return Danh sách các phiếu nhập kho trong tháng và năm đã chỉ định.
	 */
	public List<PhieuNhapKho> getListPhieuTheoThangNam(Integer thang) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM PhieuNhapKho WHERE MONTH(ngayGiao) = :thang", PhieuNhapKho.class)
				.setParameter("thang", thang).getResultList();
	}
	
	/**
	 * Cập nhật thông tin sản phẩm nhập kho.
	 *
	 * @param sanPhamNhapKhoDTOs Danh sách các đối tượng SanPhamNhapKhoDTO chứa thông tin sản phẩm nhập kho cần cập nhật.
	 */
	public void updateXemThongTin(List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs){
	    Session session = sessionFactory.getCurrentSession();
	    for (SanPhamNhapKhoDTO sanPhamNhapKhoDTO : sanPhamNhapKhoDTOs) {
	    	SanPhamNhapKho sanPhamNhapKho = session.find(SanPhamNhapKho.class, sanPhamNhapKhoDTO.getIdSPNhapKho());
	        sanPhamNhapKho.setTenSanPham(sanPhamNhapKhoDTO.getTenSanPham());
	        sanPhamNhapKho.setDonViTinh(sanPhamNhapKhoDTO.getDonViTinh());
	        sanPhamNhapKho.setSoLuong(sanPhamNhapKhoDTO.getSoLuong());
	        sanPhamNhapKho.setDonGia(sanPhamNhapKhoDTO.getDonGia());
	        sanPhamNhapKho.setThanhTien(sanPhamNhapKhoDTO.getThanhTien());
	    }
	}

	/**
	 * Cập nhật thông tin phiếu nhập kho.
	 *
	 * @param phieuNhapKho Đối tượng PhieuNhapKho chứa thông tin phiếu nhập kho cần cập nhật.
	 */
	public void updatePhieu(PhieuNhapKho phieuNhapKho){
	    Session session = sessionFactory.getCurrentSession();
	    PhieuNhapKho phieuNhapKhoData = session.find(PhieuNhapKho.class, phieuNhapKho.getMaPhieu());
	    phieuNhapKhoData.setConNo(phieuNhapKho.getConNo());
	    phieuNhapKhoData.setDaTra(phieuNhapKho.getDaTra());
	    phieuNhapKhoData.setTongTruocThue(phieuNhapKho.getTongTruocThue());
	    phieuNhapKhoData.setTongSauThue(phieuNhapKho.getTongSauThue());
	}

}
