package tien.java.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tien.java.web.DTO.SanPhamNhapKhoDTO;
import tien.java.web.entity.PhieuNhapKho;
import tien.java.web.entity.SanPhamNhapKho;
import tien.java.web.repository.SanPhamNhapKhoRepository;

/**
 * Service quản lý các hoạt động liên quan đến sản phẩm nhập kho.
 * 
 * SanPhamNhapKhoService			
 *			
 * Date: 30-05-2024		
 *			
 * DATE                 AUTHOR          DESCRIPTION			
 * --------------------------------------------------------------			
 * 30-05-2024        Tran Minh Tien        Create			
 */
@Service
@Transactional
public class SanPhamNhapKhoService {

    @Autowired
    private SanPhamNhapKhoRepository sanPhamNhapKhoRepository;

    @Autowired
    private PhieuNhapKhoService phieuNhapKhoService;

    /**
     * Lưu danh sách các sản phẩm nhập kho vào cơ sở dữ liệu.
     * 
     * @param listSanPhamNhapKhoDTO danh sách các DTO của sản phẩm nhập kho cần lưu.
     */
    public List<SanPhamNhapKho> saveAll(List<SanPhamNhapKhoDTO> listSanPhamNhapKhoDTO) {
        List<SanPhamNhapKho> sanPhamNhapKhos = new ArrayList<>();
        for (SanPhamNhapKhoDTO sanPhamNhapKhoDTO : listSanPhamNhapKhoDTO) {
        	SanPhamNhapKho entity = new SanPhamNhapKho();
            entity.setIdSPNhapKho(sanPhamNhapKhoDTO.getIdSPNhapKho());
            entity.setTenSanPham(sanPhamNhapKhoDTO.getTenSanPham());
            entity.setDonViTinh(sanPhamNhapKhoDTO.getDonViTinh());
            entity.setSoLuong(sanPhamNhapKhoDTO.getSoLuong());
            entity.setDonGia(sanPhamNhapKhoDTO.getDonGia());
            entity.setThanhTien(sanPhamNhapKhoDTO.getThanhTien());
            entity.setBaoHanh_BaoTri(sanPhamNhapKhoDTO.getBaoHanh_BaoTri());
            entity.setGhiChu(sanPhamNhapKhoDTO.getGhiChu());
            entity.setMaPhieu(phieuNhapKhoService.findByMaPhieu(sanPhamNhapKhoDTO.getMaPhieu()));

            sanPhamNhapKhos.add(entity);
        }
        return sanPhamNhapKhoRepository.saveAll(sanPhamNhapKhos);
    }

    /**
     * Lấy danh sách tất cả sản phẩm nhập kho.
     * 
     * @return danh sách tất cả sản phẩm nhập kho.
     */
    public List<SanPhamNhapKho> getAll() {
        return sanPhamNhapKhoRepository.getAll();
    }

    /**
     * Xóa nhóm sản phẩm nhập kho theo mã phiếu.
     * 
     * @param maPhieu mã phiếu của nhóm sản phẩm nhập kho cần xóa.
     */
    public void deleteGroupSPNK(PhieuNhapKho maPhieu) {
        sanPhamNhapKhoRepository.deleteGroupSPNK(maPhieu);
    }
}
