package tien.java.web.DTO;

import java.time.LocalDate;

public class DanhGiaChatLuongDTO {
	private Long idDanhGia;
	private String sTT;
	private String tenSanPham;
	private LocalDate ngayGiao;
	private String donViTinh;
	private Long soLuong;
	private String tinhTrang;
	private String danhGiaChatLuong;
	private LocalDate ngayDanhGiaTinhTrang;
	private String nhaCungCap;
	private Long idSPNhapKho;

	public DanhGiaChatLuongDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DanhGiaChatLuongDTO(String sTT, String tenSanPham, LocalDate ngayGiao, String donViTinh, Long soLuong,
			String tinhTrang, String danhGiaChatLuong, LocalDate ngayDanhGiaTinhTrang, String nhaCungCap) {
		super();
		this.sTT = sTT;
		this.tenSanPham = tenSanPham;
		this.ngayGiao = ngayGiao;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.tinhTrang = tinhTrang;
		this.danhGiaChatLuong = danhGiaChatLuong;
		this.ngayDanhGiaTinhTrang = ngayDanhGiaTinhTrang;
		this.nhaCungCap = nhaCungCap;
	}

	public DanhGiaChatLuongDTO(Long idDanhGia, String tenSanPham, LocalDate ngayGiao, String donViTinh, Long soLuong,
			String tinhTrang, String danhGiaChatLuong, LocalDate ngayDanhGiaTinhTrang, String nhaCungCap,
			Long idSPNhapKho) {
		super();
		this.idDanhGia = idDanhGia;
		this.tenSanPham = tenSanPham;
		this.ngayGiao = ngayGiao;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.tinhTrang = tinhTrang;
		this.danhGiaChatLuong = danhGiaChatLuong;
		this.ngayDanhGiaTinhTrang = ngayDanhGiaTinhTrang;
		this.nhaCungCap = nhaCungCap;
		this.idSPNhapKho = idSPNhapKho;
	}

	public Long getIdDanhGia() {
		return idDanhGia;
	}

	public void setIdDanhGia(Long idDanhGia) {
		this.idDanhGia = idDanhGia;
	}

	public String getsTT() {
		return sTT;
	}

	public void setsTT(String sTT) {
		this.sTT = sTT;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public LocalDate getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(LocalDate ngayGiao) {
		this.ngayGiao = ngayGiao;
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

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public Long getIdSPNhapKho() {
		return idSPNhapKho;
	}

	public void setIdSPNhapKho(Long idSPNhapKho) {
		this.idSPNhapKho = idSPNhapKho;
	}

}
