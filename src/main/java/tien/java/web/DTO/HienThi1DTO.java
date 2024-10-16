package tien.java.web.DTO;

import java.util.List;

import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;

public class HienThi1DTO {
	private PhieuNhapKho phieuNhapKho;
	private List<SanPhamNhapKho> sanPhamNhapKhos;

	public HienThi1DTO() {
		super();
	}

	public HienThi1DTO(PhieuNhapKho phieuNhapKho, List<SanPhamNhapKho> sanPhamNhapKhos) {
		super();
		this.phieuNhapKho = phieuNhapKho;
		this.sanPhamNhapKhos = sanPhamNhapKhos;
	}

	public PhieuNhapKho getPhieuNhapKho() {
		return phieuNhapKho;
	}

	public void setPhieuNhapKho(PhieuNhapKho phieuNhapKho) {
		this.phieuNhapKho = phieuNhapKho;
	}

	public List<SanPhamNhapKho> getSanPhamNhapKhos() {
		return sanPhamNhapKhos;
	}

	public void setSanPhamNhapKhos(List<SanPhamNhapKho> sanPhamNhapKhos) {
		this.sanPhamNhapKhos = sanPhamNhapKhos;
	}

}
