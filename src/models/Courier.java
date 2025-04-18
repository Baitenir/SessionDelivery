package models;

public class Courier {
    private Long id;
    private String fullName;
    private double rating;
    private boolean isAvailable;

    public Courier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
