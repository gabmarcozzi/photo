package it.uniroma3.siw.photo.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	@JoinColumn
	private Photographer ph;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Photo> photos;
	
	
	public Album(String name, Photographer ph) {
		super();
		this.name = name;
		this.ph = ph;
		this.photos = new LinkedList<>();
		
		ph.addAlbum(this);
	}
	
	public Album() {
		this.photos = new LinkedList<>();
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

	public Photographer getPh() {
		return ph;
	}

	public void setPh(Photographer ph) {
		this.ph = ph;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo pic) {
		photos.add(pic);
		
		pic.setAlbum(this);
	}

	@Override
	public boolean equals(Object obj) {
		
		// Different classes
		if(!obj.getClass().equals(Album.class)) return false;
		
		Album other = (Album) obj;
		return name == other.name;
	}
}
