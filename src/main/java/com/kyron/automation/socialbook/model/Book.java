package com.kyron.automation.socialbook.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @JsonInclude(Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(Include.NON_NULL)
    @NotEmpty(message = "The field 'name' can't be empty.")
    private String name;

    @JsonInclude(Include.NON_NULL)
    @NotNull(message = "The field 'publication' can't be empty.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publication;

    @JsonInclude(Include.NON_NULL)
    @NotNull(message = "The field 'publisher' can't be empty.")
    private String publisher;

    @JsonInclude(Include.NON_NULL)
    @NotEmpty(message = "The field 'summary' can't be empty.")
    @Size(max = 1500, message = "The field 'summary' can't be more than 1500 characters.")
    private String summary;

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @JsonInclude(Include.NON_NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private Author author;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
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

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
