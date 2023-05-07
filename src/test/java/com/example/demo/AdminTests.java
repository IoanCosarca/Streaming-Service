package com.example.demo;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Admin;
import com.example.demo.Service.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminTests {
    @Mock
    private DAO<Admin> dao;

    @Test
    void testGetAll()
    {
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        admins.add(admin1);
        admins.add(admin2);
        AdminService adminService = new AdminService(dao);
        adminService.getAdmins();
        when (dao.getAll()).thenReturn(admins);
        verify(dao).getAll();
    }

    @Test
    void testFindByID()
    {
        Admin admin = new Admin(1L, "Gica", "Pop", "goci@gmail", "1234");
        AdminService adminService = new AdminService(dao);
        adminService.getAdminByID(admin.getUserID());
        when (dao.findByID(admin.getUserID())).thenReturn(admin);
        verify(dao).findByID(admin.getUserID());
    }

    @Test
    void testFindByEmail()
    {
        Admin admin = new Admin(1L, "Gica", "Pop", "goci@gmail", "1234");
        AdminService adminService = new AdminService(dao);
        adminService.getAdminByEmail(admin.getEmail());
        when (dao.findByEmail(admin.getEmail())).thenReturn(admin);
        verify(dao).findByEmail(admin.getEmail());
    }

    @Test
    void testSave()
    {
        Admin admin = new Admin(1L, "Gica", "Pop", "goci@gmail", "1234");
        AdminService adminService = new AdminService(dao);
        adminService.saveAdmin(admin);
        verify(dao).save(admin);
    }

    @Test
    void testUpdate()
    {
        Admin admin = new Admin(1L, "Gica", "Pop", "goci@gmail", "1234");
        AdminService adminService = new AdminService(dao);
        adminService.updateAdmin(admin);
        verify(dao).update(admin);
    }

    @Test
    void testDelete()
    {
        Admin admin = new Admin(1L, "Gica", "Pop", "goci@gmail", "1234");
        AdminService adminService = new AdminService(dao);
        adminService.deleteAdminByID(admin.getUserID());
        verify(dao).delete(admin.getUserID());
    }
}
