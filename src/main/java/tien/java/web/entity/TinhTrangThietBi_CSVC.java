/**
 * 
 */
package tien.java.web.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 */
@Entity
public class TinhTrangThietBi_CSVC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDanhGia;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String tinhTrang;

	@Column(columnDefinition = "NVARCHAR(255)")
	@NotNull
	private String danhGiaChatLuong;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate ngayDanhGiaTinhTrang;

	@OneToOne
	@JoinColumn(name = "idSPNhapKho", unique = true)
	private SanPhamNhapKho idSPNhapKho;

	public TinhTrangThietBi_CSVC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TinhTrangThietBi_CSVC(Long idDanhGia, String tinhTrang, String danhGiaChatLuong,
			LocalDate ngayDanhGiaTinhTrang, SanPhamNhapKho idSPNhapKho) {
		super();
		this.idDanhGia = idDanhGia;
		this.tinhTrang = tinhTrang;
		this.danhGiaChatLuong = danhGiaChatLuong;
		this.ngayDanhGiaTinhTrang = ngayDanhGiaTinhTrang;
		this.idSPNhapKho = idSPNhapKho;
	}

	public Long getIdDanhGia() {
		return idDanhGia;
	}

	public void setIdDanhGia(Long idDanhGia) {
		this.idDanhGia = idDanhGia;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getDanhGiaChatLuong() {
		return danhGiaChatLuong;
	}

	public void setDanhGiaChatLuong(String danhGiaChatLuong) {
		this.danhGiaChatLuong = danhGiaChatLuong;
	}

	public LocalDate getNgayDanhGiaTinhTrang() {
		return ngayDanhGiaTinhTrang;
	}

	public void setNgayDanhGiaTinhTrang(LocalDate ngayDanhGiaTinhTrang) {
		this.ngayDanhGiaTinhTrang = ngayDanhGiaTinhTrang;
	}

	public SanPhamNhapKho getSanPhamNhapKho() {
		return idSPNhapKho;
	}

	public void setSanPhamNhapKho(SanPhamNhapKho idSPNhapKho) {
		this.idSPNhapKho = idSPNhapKho;
	}
	

}
