package com.example.demo.Controller;

import com.example.demo.Model.Admin;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the operations on the Admin table, not how they are implemented.
 */
@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    /**
     * Returns all the Admins in the table.
     * @return List[Admin]
     */
    @GetMapping("/getAdmins")
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    /**
     * Returns the Admin with the specified id.
     * @param id search criteria
     * @return Admin
     */
    @GetMapping("/getAdminByID/{id}")
    public Admin getAdminByID(@PathVariable Long id) {
        return adminService.getAdminByID(id);
    }

    /**
     * Returns the Admin with the specified email.
     * @param email seatch criteria
     * @return Admin
     */
    @GetMapping("/getAdminByEmail/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }

    /**
     * Adds a new Admin to the database.
     * @param admin the new instance to be added in the table
     */
    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
    }

    /**
     * Updates the details of an Admin in the database.
     * @param admin the instance containing all the modified values to be added in the database
     */
    @PutMapping("/updateAdmin")
    public void updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
    }

    /**
     * Deletes the Admin with the specified id.
     * @param id delete criteria
     */
    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminByID(id);
    }
}
