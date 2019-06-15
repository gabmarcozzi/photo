package it.uniroma3.siw.photo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DBPopulation implements ApplicationRunner {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    
    
}