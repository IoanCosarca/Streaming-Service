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

    @GetMapping("/getAdminByID/{id}")
    public Admin getAdminByID(@PathVariable Long id) {
        return adminService.getAdminByID(id);
    }

    @GetMapping("/getAdminByEmail/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }

    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
    }

    @PutMapping("/updateAdmin")
    public void updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminByID(id);
    }
}
