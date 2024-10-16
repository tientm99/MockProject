/**
 * 
 */
package tien.java.web.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 */
@Entity
public class SanPhamNhapKho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSPNhapKho;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	@NotNull
	private String tenSanPham;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	@NotNull
	private String donViTinh;
	@NotNull
	private Long soLuong;
	@NotNull
	private Double donGia;
	@NotNull
	private Double thanhTien;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate baoHanh_BaoTri;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String ghiChu;

	@ManyToOne()
	@JoinColumn(name = "maPhieu")
	private PhieuNhapKho maPhieu;
	
	@OneToOne(mappedBy = "idSPNhapKho", cascade = CascadeType.ALL)
	private TinhTrangThietBi_CSVC tinhTrangThietBi_CSVC;

	public SanPhamNhapKho() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamNhapKho(Long idSPNhapKho, String tenSanPham, String donViTinh, Long soLuong, Double donGia,
			Double thanhTien, LocalDate baoHanh_BaoTri, String ghiChu, PhieuNhapKho maPhieu) {
		super();
		this.idSPNhapKho = idSPNhapKho;
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.baoHanh_BaoTri = baoHanh_BaoTri;
		this.ghiChu = ghiChu;
		this.maPhieu = maPhieu;
	}

	public SanPhamNhapKho(String tenSanPham, String donViTinh, Long soLuong, Double donGia, Double thanhTien,
			LocalDate baoHanh_BaoTri, String ghiChu, PhieuNhapKho maPhieu) {
		super();
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.baoHanh_BaoTri = baoHanh_BaoTri;
		this.ghiChu = ghiChu;
		this.maPhieu = maPhieu;
	}

	public Long getIdSPNhapKho() {
		return idSPNhapKho;
	}

	public void setIdSPNhapKho(Long idSPNhapKho) {
		this.idSPNhapKho = idSPNhapKho;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public Long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public LocalDate getBaoHanh_BaoTri() {
		return baoHanh_BaoTri;
	}

	public void setBaoHanh_BaoTri(LocalDate baoHanh_BaoTri) {
		this.baoHanh_BaoTri = baoHanh_BaoTri;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public PhieuNhapKho getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(PhieuNhapKho maPhieu) {
		this.maPhieu = maPhieu;
	}

	@Override
	public String toString() {
		return "SanPhamNhapKho [idSPNhapKho=" + idSPNhapKho + ", tenSanPham=" + tenSanPham + ", donViTinh=" + donViTinh
				+ ", soLuong=" + soLuong + ", donGia=" + donGia + ", thanhTien=" + thanhTien + ", baoHanh_BaoTri="
				+ baoHanh_BaoTri + ", ghiChu=" + ghiChu + "]";
	}

}
