package com.laioffer.job.entity;

public class ExampleBook {
  public String title;
  public String author;
  public String date;
  public String currency;
  public String series;
  public String language;
  public String isbn;
  public float price;
  public int pages;

  public ExampleBook(String title, String author, String date, String currency,
      String series, String language, String isbn, float price, int pages) {
    this.title = title;
    this.author = author;
    this.date = date;
    this.currency = currency;
    this.series = series;
    this.language = language;
    this.isbn = isbn;
    this.price = price;
    this.pages = pages;
  }
}
