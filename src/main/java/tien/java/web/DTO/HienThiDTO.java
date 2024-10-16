package tien.java.web.DTO;

import java.util.List;

import tien.java.web.entity.PhieuNhapKho;

public class HienThiDTO {
	private List<PhieuNhapKho> phieuNhapKhos;
	private List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs;

	public HienThiDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HienThiDTO(List<PhieuNhapKho> phieuNhapKhos, List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs) {
		super();
		this.phieuNhapKhos = phieuNhapKhos;
		this.sanPhamNhapKhoDTOs = sanPhamNhapKhoDTOs;
	}

	public List<PhieuNhapKho> getPhieuNhapKho() {
		return phieuNhapKhos;
	}

	public void setPhieuNhapKho(List<PhieuNhapKho> phieuNhapKhos) {
		this.phieuNhapKhos = phieuNhapKhos;
	}

	public List<SanPhamNhapKhoDTO> getSanPhamNhapKhoDTOs() {
		return sanPhamNhapKhoDTOs;
	}

	public void setSanPhamNhapKhoDTOs(List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs) {
		this.sanPhamNhapKhoDTOs = sanPhamNhapKhoDTOs;
	}
}
