package com.github.luisfeliperochamartins.rememberTools.domain.tools;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tools")
public class Tools {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String link;
	private String description;
	private List<String> tags;

	public Tools() {}

	public Tools(Integer id, String title, String link, String description, List<String> tags) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", title = ").append(title);
		sb.append(", link = ").append(link);
		sb.append(", description = ").append(description);
		sb.append(", tags = ").append(tags);
		return sb.toString();
	}
}
