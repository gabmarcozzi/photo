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
	
	@Column(nullable = false)
	private String complete_name;
	
	@OneToMany(mappedBy = "ph", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Album> albums;

	
	public Photographer(String complete_name) {
		this.complete_name = complete_name;
		this.albums = new LinkedList<>();
	}

	public Photographer() {
		this.albums = new LinkedList<>();
	}
	
	
	public String getComplete_name() {
		return complete_name;
	}

	public void setComplete_name(String complete_name) {
		this.complete_name = complete_name;
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
	}
	
}
