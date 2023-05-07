package com.example.demo.Service;

import com.example.demo.DAO.DAO;
import com.example.demo.Model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that insures the link between the application methods and the database.
 */
@Service
public class AdminService {
    private DAO<Admin> dao;

    @Autowired
    public AdminService(DAO<Admin> dao)
    {
        this.dao = dao;
    }

    /**
     * Sends the request to get all the Admins from the database.
     * @return List[Admin]
     */
    public List<Admin> getAdmins() {
        return dao.getAll();
    }

    /**
     * Sends the request to get the Admin with the specified id.
     * @param id search criteria
     * @return Admin
     */
    public Admin getAdminByID(Long id) {
        return dao.findByID(id);
    }

    /**
     * Sends the request to get the Admin with the specified id.
     * @param email search criteria
     * @return Admin
     */
    public Admin getAdminByEmail(String email) {
        return dao.findByEmail(email);
    }

    /**
     * Sends the Admin to be added to the database.
     * @param admin the entry to be added
     */
    public void saveAdmin(Admin admin) {
        dao.save(admin);
    }

    /**
     * Sends the new Admin object to update the database.
     * @param admin the entry containing the new information
     */
    public void updateAdmin(Admin admin) {
        dao.update(admin);
    }

    /**
     * Sends the id of the Admin to be deleted from the database.
     * @param id delete criteria
     */
    public void deleteAdminByID(Long id) {
        dao.delete(id);
    }
}
