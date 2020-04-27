package com.example.lpz_6.bean;

public class News {
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;

    public News(String title, String date, String category, String author_name, String url, String image) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.author_name = author_name;
        this.url = url;
        this.image = image;
    }

    public News(){}

    @Override
    public String toString() {
        return  "title=" + title + '&' +
                "date=" + date + '&' +
                "category=" + category + '&' +
                "author_name=" + author_name + '&' +
                "url=" + url +'&' +
                "image=" + image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
}
