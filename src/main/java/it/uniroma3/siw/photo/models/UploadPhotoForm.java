package it.uniroma3.siw.photo.models;

/**
 * Transfer object that encapsulates the information for {@link admin/photoForm.html}
 * 
 * @author Alessio Papi
 */
public class UploadPhotoForm {

	private String photographerName;

	private String albumName;

	private String photoName;


	public String getPhotographerName() {
		return photographerName;
	}

	public void setPhotographerName(String photographerName) {
		this.photographerName = photographerName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * Normalize encapsulated text. Es: " foto esempio " -> "Foto Esempio" 
	 */
	public void normalize() {
		String[] photographerAux = photographerName.split(" ");
		String[] albumAux = albumName.split(" ");
		String[] photoAux = photoName.split(" ");
		
		photographerName = "";
		albumName = "";
		photoName = "";
		
		for(String s : photographerAux) {
			if(s.trim().isEmpty()) continue;
			s = s.substring(0, 1).toUpperCase() + s.substring(1);
			photographerName = photographerName.concat(s + " ");
		}
		
		for(String s : albumAux) {
			if(s.trim().isEmpty()) continue;
			s = s.substring(0, 1).toUpperCase() + s.substring(1);
			albumName = albumName.concat(s + " ");
		}
		
		for(String s : photoAux) {
			if(s.trim().isEmpty()) continue;
			s = s.substring(0, 1).toUpperCase() + s.substring(1);
			photoName = photoName.concat(s + " ");
		}
		
		photographerName = photographerName.trim();
		albumName = albumName.trim();
		photoName = photoName.trim();
	}
	
}
