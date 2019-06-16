package it.uniroma3.siw.photo.repositories;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.photo.images.ImagesDir;
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

	private void addAll() throws Exception {
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
		
		File file = new File(ImagesDir.class.getResource("image1.jpeg").toURI());
		FileInputStream fis = new FileInputStream(file);
		InputStream is = new BufferedInputStream(fis);
		BufferedImage bImage;
		bImage = ImageIO.read(is);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos );
		p.setImage(bos.toByteArray());

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