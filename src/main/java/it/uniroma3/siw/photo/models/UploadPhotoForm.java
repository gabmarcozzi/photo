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

}
