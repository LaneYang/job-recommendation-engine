//only need partial fields of the returned github job objects,
//so we have to trim the data model
package com.laioffer.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.Set;
//unused fields in the response can be safely ignored
//null fields can be skipped and not included.
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Item {

  private String id;
  private String title;
  private String location;
  private String companyLogo;
  private String url;
  private String description;
  private Set<String> keywords;
  private boolean favorite;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @JsonProperty("company_logo")
  public String getCompanyLogo() {
    return companyLogo;
  }

  public void setCompanyLogo(String companyLogo) {
    this.companyLogo = companyLogo;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(Set<String> keywords) {
    this.keywords = keywords;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return favorite == item.favorite && Objects.equals(id, item.id) && Objects
        .equals(title, item.title) && Objects.equals(location, item.location)
        && Objects.equals(companyLogo, item.companyLogo) && Objects
        .equals(url, item.url) && Objects.equals(description, item.description)
        && Objects.equals(keywords, item.keywords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, location, companyLogo, url, description, keywords, favorite);
  }

  @Override
  public String toString() {
    return "Item{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", location='" + location + '\'' +
        ", companyLogo='" + companyLogo + '\'' +
        ", url='" + url + '\'' +
        ", description='" + description + '\'' +
        ", keywords=" + keywords +
        ", favorite=" + favorite +
        '}';
  }
}
