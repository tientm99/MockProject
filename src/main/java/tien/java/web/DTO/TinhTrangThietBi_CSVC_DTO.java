package tien.java.web.DTO;

import java.time.LocalDate;

public class TinhTrangThietBi_CSVC_DTO {
	private Long idDanhGia;

	private String tinhTrang;

	private String danhGiaChatLuong;

	private LocalDate ngayDanhGiaTinhTrang;

	private Long idSPNhapKho;

	public TinhTrangThietBi_CSVC_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TinhTrangThietBi_CSVC_DTO(Long idDanhGia, String tinhTrang, String danhGiaChatLuong,
			LocalDate ngayDanhGiaTinhTrang, Long idSPNhapKho) {
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

	public Long getIdSPNhapKho() {
		return idSPNhapKho;
	}

	public void setIdSPNhapKho(Long idSPNhapKho) {
		this.idSPNhapKho = idSPNhapKho;
	}

}
