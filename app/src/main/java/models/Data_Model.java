package models;


public class Data_Model {

    // Getter and Setter model for recycler view items
    private String title;
    private int price;
    private String duration;
    private String image;
    private int width;
    private int height;

    public Data_Model(String title, String image,int width,int height,int price) {

        this.title = title;
        this.price = price;
        this.duration = duration;
        this.image = image;
        this.width=width;
        this.height=height;
    }

    public String getTitle() {
        return title;
    }
    public int getPrice() {
        return price;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getImage() {
        return image;
    }
}
