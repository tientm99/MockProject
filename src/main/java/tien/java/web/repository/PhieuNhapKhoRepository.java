package tien.java.web.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tien.java.web.entity.PhieuNhapKho;

/**
 * Repository cung cấp các phương thức để truy vấn và thao tác với phiếu nhập
 * kho trong cơ sở dữ liệu.
 * 
 * PhieuNhapKhoRepository			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Repository
public class PhieuNhapKhoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Lưu phiếu nhập kho vào cơ sở dữ liệu.
	 * 
	 * @param phieuNhapKho phiếu nhập kho cần lưu.
	 */
	public void save(PhieuNhapKho phieuNhapKho) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(phieuNhapKho);
	}

	/**
	 * Tìm kiếm phiếu nhập kho bằng mã phiếu.
	 * 
	 * @param maPhieu mã phiếu nhập kho cần tìm.
	 * @return phiếu nhập kho tương ứng với mã phiếu hoặc null nếu không tìm thấy.
	 */
	public PhieuNhapKho findByMaPhieu(String maPhieu) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(PhieuNhapKho.class, maPhieu);
	}

	/**
	 * Kiểm tra xem mã phiếu nhập kho đã tồn tại trong cơ sở dữ liệu hay chưa.
	 * 
	 * @param maPhieu mã phiếu nhập kho cần kiểm tra.
	 * @return true nếu mã phiếu đã tồn tại, ngược lại trả về false.
	 */
	public boolean maPhieuExist(String maPhieu) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(PhieuNhapKho.class, maPhieu) != null;
	}
}
