package it.uniroma3.siw.photo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.photo.models.Admin;

@Component
public class DBPopulation implements ApplicationRunner {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.addAll();
    }

    private void deleteAll() {
        adminRepository.deleteAll();
    }

    private void addAll() {
        Admin admin = new Admin("admin", "admin", "ADMIN");
        adminRepository.save(admin);
    }

}