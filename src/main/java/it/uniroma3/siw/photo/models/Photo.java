package it.uniroma3.siw.photo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Photo's name.
	 * @err two photos in the same album can't have the same name
	 */
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	@JoinColumn
	private Album album;

	@Column
	private byte[] image;

	@ManyToMany(mappedBy = "photos")
	private List<Order> orders;
	
	public Photo() { }
	
	public Photo(String name, Album album) {
		super();
		this.name = name;
		this.album = album;
		
		album.addPhoto(this);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
