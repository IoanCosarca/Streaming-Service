package com.example.demo.Service;

import com.example.demo.DAO.AdminDAO;
import com.example.demo.Model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private AdminDAO dao;

    @Autowired
    public AdminService(AdminDAO dao)
    {
        this.dao = dao;
    }

    public List<Admin> getAdmins() {
        return dao.getAll();
    }

    public Admin getAdminByID(Long id) {
        return dao.findByID(id);
    }

    public Admin getAdminByEmail(String email) {
        return dao.findByEmail(email);
    }

    public void saveAdmin(Admin admin) {
        dao.save(admin);
    }

    public void updateAdmin(Admin admin) {
        dao.update(admin);
    }

    public void deleteAdminByID(Long id) {
        dao.delete(id);
    }
}
