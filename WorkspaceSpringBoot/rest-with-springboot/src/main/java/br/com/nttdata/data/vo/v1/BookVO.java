package br.com.nttdata.data.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","title","author","launchDate","price"})
public class BookVO extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private String title;
	private String author;
	private Date launchDate;
	private Double price;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long id) {
		this.key = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
