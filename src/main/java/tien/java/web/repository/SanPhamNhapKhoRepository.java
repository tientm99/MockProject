package tien.java.web.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;

/**
 * Repository cung cấp các phương thức để truy vấn và thao tác với thông tin sản
 * phẩm nhập kho trong cơ sở dữ liệu.
 * 
 * SanPhamNhapKhoRepository			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Repository
public class SanPhamNhapKhoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Lưu danh sách sản phẩm nhập kho vào cơ sở dữ liệu.
	 * 
	 * @param sanPhamNhapKhos danh sách sản phẩm nhập kho cần lưu.
	 */
	public List<SanPhamNhapKho> saveAll(List<SanPhamNhapKho> sanPhamNhapKhos) {
		Session session = sessionFactory.getCurrentSession();
		for (SanPhamNhapKho sanPhamNhapKho : sanPhamNhapKhos) {
			if (sanPhamNhapKho.getIdSPNhapKho() != null) {
				SanPhamNhapKho existingEntity = findById(sanPhamNhapKho.getIdSPNhapKho());
				if (existingEntity != null) {
					existingEntity.setIdSPNhapKho(sanPhamNhapKho.getIdSPNhapKho());
					existingEntity.setDonViTinh(sanPhamNhapKho.getDonViTinh());
					existingEntity.setTenSanPham(sanPhamNhapKho.getTenSanPham());
					existingEntity.setDonGia(sanPhamNhapKho.getDonGia());
					existingEntity.setSoLuong(sanPhamNhapKho.getSoLuong());
					existingEntity.setThanhTien(sanPhamNhapKho.getThanhTien());
					existingEntity.setBaoHanh_BaoTri(sanPhamNhapKho.getBaoHanh_BaoTri());
					existingEntity.setGhiChu(sanPhamNhapKho.getGhiChu());
					existingEntity.setMaPhieu(sanPhamNhapKho.getMaPhieu());

					session.merge(existingEntity);
				} else {
					session.save(sanPhamNhapKho);
				}
			} else {
				session.save(sanPhamNhapKho);
			}
		}
		return sanPhamNhapKhos;
	}

	/**
	 * Truy vấn và lấy danh sách tất cả sản phẩm nhập kho từ cơ sở dữ liệu.
	 * 
	 * @return danh sách tất cả sản phẩm nhập kho.
	 */
	public List<SanPhamNhapKho> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<SanPhamNhapKho> query = session.createQuery("FROM SanPhamNhapKho", SanPhamNhapKho.class);
		return query.getResultList();
	}

	/**
	 * Tìm kiếm sản phẩm nhập kho bằng ID nhập kho (kiểu Long).
	 * 
	 * @param idNhapKho ID nhập kho của sản phẩm cần tìm.
	 * @return sản phẩm nhập kho tương ứng với ID hoặc null nếu không tìm thấy.
	 */
	public SanPhamNhapKho findById(Long idSPNhapKho) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(SanPhamNhapKho.class, idSPNhapKho);
	}

	/**
	 * Xóa nhóm sản phẩm nhập kho dựa trên phiếu nhập kho.
	 * 
	 * @param maPhieu phiếu nhập kho của nhóm sản phẩm cần xóa.
	 */
	public void deleteGroupSPNK(PhieuNhapKho maPhieu) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPhamNhapKho> sanPhamNhapKhos = getGroupSPNK(maPhieu);
		for (SanPhamNhapKho sanPhamNhapKho : sanPhamNhapKhos) {
			session.delete(sanPhamNhapKho);
		}
	}

	/**
	 * Truy vấn và lấy danh sách sản phẩm nhập kho dựa trên phiếu nhập kho.
	 * 
	 * @param maPhieu phiếu nhập kho của nhóm sản phẩm.
	 * @return danh sách sản phẩm nhập kho của nhóm tương ứng với phiếu nhập kho.
	 */
	public List<SanPhamNhapKho> getGroupSPNK(PhieuNhapKho maPhieu) {
		Session session = sessionFactory.getCurrentSession();
		Query<SanPhamNhapKho> query = session
				.createQuery("FROM SanPhamNhapKho WHERE maPhieu = :maPhieu", SanPhamNhapKho.class)
				.setParameter("maPhieu", maPhieu);
		return query.getResultList();
	}
}
