package tien.java.web.DTO;

import java.time.LocalDate;

public class SanPhamNhapKhoDTO {
	private Long idSPNhapKho;
	private String tenSanPham;
	private String donViTinh;
	private Long soLuong;
	private Double donGia;
	private Double thanhTien;

	private LocalDate baoHanh_BaoTri;
	private String ghiChu;

	private String maPhieu;
	private Long daTra;
	private Long conNo;
	private Double tongTruocThue;
	private Double tongSauThue;

	public SanPhamNhapKhoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamNhapKhoDTO(Long idSPNhapKho, String tenSanPham, String donViTinh, Long soLuong, Double donGia,
			Double thanhTien, LocalDate baoHanh_BaoTri, String ghiChu, String maPhieu, Long daTra, Long conNo,
			Double tongTruocThue, Double tongSauThue) {
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
		this.daTra = daTra;
		this.conNo = conNo;
		this.tongTruocThue = tongTruocThue;
		this.tongSauThue = tongSauThue;
	}

	public SanPhamNhapKhoDTO(Long idSPNhapKho, String tenSanPham, String donViTinh, Long soLuong, Double donGia,
			Double thanhTien, LocalDate baoHanh_BaoTri, String ghiChu, String maPhieu) {
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

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public Long getDaTra() {
		return daTra;
	}

	public void setDaTra(Long daTra) {
		this.daTra = daTra;
	}

	public Long getConNo() {
		return conNo;
	}

	public void setConNo(Long conNo) {
		this.conNo = conNo;
	}

	public Double getTongTruocThue() {
		return tongTruocThue;
	}

	public void setTongTruocThue(Double tongTruocThue) {
		this.tongTruocThue = tongTruocThue;
	}

	public Double getTongSauThue() {
		return tongSauThue;
	}

	public void setTongSauThue(Double tongSauThue) {
		this.tongSauThue = tongSauThue;
	}

}
