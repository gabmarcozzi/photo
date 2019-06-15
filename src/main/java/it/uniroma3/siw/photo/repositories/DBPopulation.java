package it.uniroma3.siw.photo.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.photo.models.Admin;

import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.models.Photographer;

@Component
public class DBPopulation implements ApplicationRunner {
    @Autowired
    private AdminRepository adminRepository;


	@Autowired
	private PhotographerRepository phRep;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.addAll();
    }

    private void deleteAll() {
        adminRepository.deleteAll();
        phRep.deleteAll();
    }

    private void addAll() {
        Admin admin = new Admin("admin", "password", "ADMIN");
        String adminPassword = new BCryptPasswordEncoder().encode(admin.getPassword());
        admin.setPassword(adminPassword);

        Photo p = new Photo();
    	Album a = new Album();
    	Photographer usr1 = new Photographer("Alessio Papi");
        
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Photo> photos = new ArrayList<>();
        
        p.setName("Photo 1");
        p.setAlbum(a);
        p.setImage(null);
        // TODO set bytestream for the image
        
        a.setName("Album 1");
        a.setPh(usr1);
        a.setPhotos(photos);
        
        usr1.setAlbums(albums);
        
        albums.add(a);
        photos.add(p);
        
        phRep.save(usr1);
        adminRepository.save(admin);
    }
}