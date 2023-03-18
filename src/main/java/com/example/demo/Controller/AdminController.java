package com.example.demo.Controller;

import com.example.demo.Model.Admin;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @GetMapping("/getAdmins")
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping("/getAdmin/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }

    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminByID(id);
    }
}
