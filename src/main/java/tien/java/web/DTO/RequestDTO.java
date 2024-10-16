package tien.java.web.DTO;

import java.util.List;

import tien.java.web.entity.PhieuNhapKho;

public class RequestDTO {
	private PhieuNhapKho phieuNhapKho;
	private List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs;

	public RequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestDTO(PhieuNhapKho phieuNhapKho, List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs) {
		super();
		this.phieuNhapKho = phieuNhapKho;
		this.sanPhamNhapKhoDTOs = sanPhamNhapKhoDTOs;
	}

	public PhieuNhapKho getPhieuNhapKho() {
		return phieuNhapKho;
	}

	public void setPhieuNhapKho(PhieuNhapKho phieuNhapKho) {
		this.phieuNhapKho = phieuNhapKho;
	}

	public List<SanPhamNhapKhoDTO> getSanPhamNhapKhoDTOs() {
		return sanPhamNhapKhoDTOs;
	}

	public void setSanPhamNhapKhoDTOs(List<SanPhamNhapKhoDTO> sanPhamNhapKhoDTOs) {
		this.sanPhamNhapKhoDTOs = sanPhamNhapKhoDTOs;
	}

}
