package tien.java.web.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tien.java.web.DTO.DanhGiaChatLuongDTO;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.entity.TinhTrangThietBi_CSVC;

/**
 * Repository cung cấp các phương thức để truy vấn và thao tác với thông tin
 * tình trạng thiết bị CSVC trong cơ sở dữ liệu.
 * 
 * TTTB_CSVC_Repository			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Repository
public class TTTB_CSVC_Repository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Lưu danh sách tình trạng thiết bị CSVC vào cơ sở dữ liệu.
	 * 
	 * @param tinhTrangThietBi_CSVCs danh sách tình trạng thiết bị CSVC cần lưu.
	 * @return danh sách tình trạng thiết bị CSVC sau khi được lưu.
	 */
	public List<TinhTrangThietBi_CSVC> saveAll(List<TinhTrangThietBi_CSVC> tinhTrangThietBi_CSVCs) {
		Session session = sessionFactory.getCurrentSession();
		for (TinhTrangThietBi_CSVC tinhTrangThietBi_CSVC : tinhTrangThietBi_CSVCs) {
			if (tinhTrangThietBi_CSVC.getIdDanhGia() != null) {
				TinhTrangThietBi_CSVC existingEntity = findById(tinhTrangThietBi_CSVC.getIdDanhGia());
				if (existingEntity != null) {
					existingEntity.setIdDanhGia(tinhTrangThietBi_CSVC.getIdDanhGia());
					existingEntity.setTinhTrang(tinhTrangThietBi_CSVC.getTinhTrang());
					existingEntity.setDanhGiaChatLuong(tinhTrangThietBi_CSVC.getDanhGiaChatLuong());
					existingEntity.setNgayDanhGiaTinhTrang(tinhTrangThietBi_CSVC.getNgayDanhGiaTinhTrang());
					existingEntity.setSanPhamNhapKho(tinhTrangThietBi_CSVC.getSanPhamNhapKho());

					session.merge(existingEntity);
				} else {
					session.save(tinhTrangThietBi_CSVC);
				}
			} else {
				session.save(tinhTrangThietBi_CSVC);
			}
		}
		return tinhTrangThietBi_CSVCs;
	}

	/**
	 * Tìm kiếm tình trạng thiết bị CSVC bằng ID đánh giá.
	 * 
	 * @param idDanhGia ID đánh giá của tình trạng thiết bị CSVC cần tìm.
	 * @return tình trạng thiết bị CSVC tương ứng với ID hoặc null nếu không tìm
	 *         thấy.
	 */
	public TinhTrangThietBi_CSVC findById(Long idDanhGia) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(TinhTrangThietBi_CSVC.class, idDanhGia);
	}

	/**
	 * Lấy thông tin cập nhật của tình trạng thiết bị CSVC dựa trên sản phẩm nhập
	 * kho.
	 * 
	 * @param sanPhamNhapKho sản phẩm nhập kho cần cập nhật thông tin tình trạng.
	 * @return thông tin cập nhật của tình trạng thiết bị CSVC hoặc null nếu không
	 *         tìm thấy.
	 */
	public TinhTrangThietBi_CSVC getEntityUpdate(SanPhamNhapKho sanPhamNhapKho) {
		Session session = sessionFactory.getCurrentSession();
		Query<TinhTrangThietBi_CSVC> query = session
				.createQuery("From TinhTrangThietBi_CSVC Where idSPNhapKho=: idSPNhapKho", TinhTrangThietBi_CSVC.class);
		query.setParameter("idSPNhapKho", sanPhamNhapKho);
		List<TinhTrangThietBi_CSVC> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	/**
	 * Tìm kiếm đánh giá chất lượng CSVC dựa trên từ khóa.
	 *
	 * @param keyword Từ khóa tìm kiếm.
	 * @return List<DanhGiaChatLuongDTO> Danh sách các đánh giá chất lượng CSVC phù hợp với từ khóa tìm kiếm.
	 */
	public List<DanhGiaChatLuongDTO> search(String keyword) {
	    Session session = sessionFactory.getCurrentSession();
	    return session.createQuery(
	            "select new tien.java.web.DTO.DanhGiaChatLuongDTO( tt.idDanhGia, sp.tenSanPham, p.ngayGiao, sp.donViTinh, sp.soLuong, "
	                    + "tt.tinhTrang, tt.danhGiaChatLuong, tt.ngayDanhGiaTinhTrang, p.nhaCungCap, sp.idSPNhapKho) "
	                    + "from PhieuNhapKho p join p.sanPhamNhapKho sp join sp.tinhTrangThietBi_CSVC tt "
	                    + "where sp.tenSanPham LIKE :keyword OR tt.tinhTrang LIKE :keyword OR tt.danhGiaChatLuong LIKE :keyword",
	                    DanhGiaChatLuongDTO.class)
	            .setParameter("keyword", "%" + keyword + "%")
	            .getResultList();
	}

}
