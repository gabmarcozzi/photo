package it.uniroma3.siw.photo.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Photographer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Complete name for the photographer.
	 */
	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "ph", cascade = CascadeType.ALL)
	private List<Album> albums;


	public Photographer(String name) {
		this.name = name;
		this.albums = new LinkedList<>();
	}

	public Photographer() {
		this.albums = new LinkedList<>();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public void addAlbum(Album al) {
		albums.add(al);

		al.setPh(this);
	}

	public List<Photo> getPhotos() {
		List<Photo> photos = new LinkedList<Photo>();
		for (Album album : this.getAlbums()) {
			photos.addAll(album.getPhotos());
		}
		return photos;
	}

	@Override
	public boolean equals(Object obj) {

		// Different classes
		if(!obj.getClass().equals(Photographer.class)) return false;

		Photographer other = (Photographer) obj;
		return name == other.name;
	}

}
