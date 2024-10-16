package tien.java.web.DTO;

public class TheoNgayDTO {
	private String tuNgay;
	private String denNgay;

	public TheoNgayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TheoNgayDTO(String tuNgay, String denNgay) {
		super();
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

}
