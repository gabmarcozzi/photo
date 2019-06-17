package it.uniroma3.siw.photo.repositories;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

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
	public void run(ApplicationArguments args) {
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

		// DB population -----------------------------------------------------------------------------
		// template: population/Photographer/Album/Photo.jpeg
		try {
			File file = ResourceUtils.getFile("classpath:population");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// Get every photographer
			for(File f_ph : file.listFiles()) {
				Photographer ph = new Photographer(f_ph.getName());
				// Get his albums
				for(File f_al : f_ph.listFiles()) {
					Album al = new Album(f_al.getName(), ph);
					for(File f_pic : f_al.listFiles()) {
						Photo pic = new Photo(f_pic.getName(), al);
						BufferedImage bI = ImageIO.read(f_pic);
						ImageIO.write(bI, "jpeg", bos);
						bos.flush();
						pic.setImage(bos.toByteArray());
						bos.reset();
					}
				}
				phRep.save(ph);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// -------------------------------------------------------------------------------------------
		
		adminRepository.save(admin);
	}
	
}