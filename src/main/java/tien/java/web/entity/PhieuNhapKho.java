/**
 * 
 */
package tien.java.web.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import tien.java.web.validator.MaPhieuExist;

/**
 * 
 */
@Entity
public class PhieuNhapKho {
	@Id
	@MaPhieuExist(message = "Mã phiếu đã tồn tại")
	private String maPhieu;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String nhaCungCap;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String tenNguoiGiaoHang;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate ngayGiao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate hanThanhToan;
	@NotNull
	private Double daTra;
	@NotNull
	private Double conNo;
	@NotNull
	private Double thueGTGT;
	@NotNull
	private Double tongTruocThue;
	@NotNull
	private Double tongSauThue;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "maPhieu")
	private List<SanPhamNhapKho> sanPhamNhapKho;

	public PhieuNhapKho() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhieuNhapKho(String maPhieu, String nhaCungCap, String tenNguoiGiaoHang, LocalDate ngayGiao,
			LocalDate hanThanhToan, Double daTra, Double conNo, Double thueGTGT, Double tongTruocThue, Double tongSauThue) {
		super();
		this.maPhieu = maPhieu;
		this.nhaCungCap = nhaCungCap;
		this.tenNguoiGiaoHang = tenNguoiGiaoHang;
		this.ngayGiao = ngayGiao;
		this.hanThanhToan = hanThanhToan;
		this.daTra = daTra;
		this.conNo = conNo;
		this.thueGTGT = thueGTGT;
		this.tongTruocThue = tongTruocThue;
		this.tongSauThue = tongSauThue;
	}

	public PhieuNhapKho(String maPhieu) {
		super();
		this.maPhieu = maPhieu;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getTenNguoiGiaoHang() {
		return tenNguoiGiaoHang;
	}

	public void setTenNguoiGiaoHang(String tenNguoiGiaoHang) {
		this.tenNguoiGiaoHang = tenNguoiGiaoHang;
	}

	public LocalDate getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(LocalDate ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public LocalDate getHanThanhToan() {
		return hanThanhToan;
	}

	public void setHanThanhToan(LocalDate hanThanhToan) {
		this.hanThanhToan = hanThanhToan;
	}

	public Double getDaTra() {
		return daTra;
	}

	public void setDaTra(Double daTra) {
		this.daTra = daTra;
	}

	public Double getConNo() {
		return conNo;
	}

	public void setConNo(Double conNo) {
		this.conNo = conNo;
	}

	public Double getThueGTGT() {
		return thueGTGT;
	}

	public void setThueGTGT(Double thueGTGT) {
		this.thueGTGT = thueGTGT;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PhieuNhapKho phieuNhapKho = (PhieuNhapKho) o;
		return Objects.equals(maPhieu, phieuNhapKho.maPhieu) && Objects.equals(nhaCungCap, phieuNhapKho.nhaCungCap)
				&& Objects.equals(tenNguoiGiaoHang, phieuNhapKho.tenNguoiGiaoHang)
				&& Objects.equals(ngayGiao, phieuNhapKho.ngayGiao)
				&& Objects.equals(hanThanhToan, phieuNhapKho.hanThanhToan) && Objects.equals(daTra, phieuNhapKho.daTra)
				&& Objects.equals(conNo, phieuNhapKho.conNo) && Objects.equals(thueGTGT, phieuNhapKho.thueGTGT)
				&& Objects.equals(tongTruocThue, phieuNhapKho.tongTruocThue)
				&& Objects.equals(tongSauThue, phieuNhapKho.tongSauThue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhieu, nhaCungCap, tenNguoiGiaoHang, ngayGiao, hanThanhToan, daTra, conNo, thueGTGT,
				tongTruocThue, tongSauThue);
	}
}
