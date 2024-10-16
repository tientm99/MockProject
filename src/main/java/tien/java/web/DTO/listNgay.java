package tien.java.web.DTO;

import java.time.LocalDate;

public class listNgay {
	private String maPhieu;
	private LocalDate ngayGiao;

	public listNgay(String maPhieu, LocalDate ngayGiao) {
		super();
		this.maPhieu = maPhieu;
		this.ngayGiao = ngayGiao;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public LocalDate getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(LocalDate ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

}
