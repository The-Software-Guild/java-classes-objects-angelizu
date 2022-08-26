package com.sg.dvdlibrary.dto;

public class Dvd {

    private String title;
    private String movieID;
    private String releaseDate;
    private String mpaaRating; //G, PG, PG-13, R, NC-17
    private String directorName;
    private String studio;
    private String userRating;

    //could have added other constructors as well to make it more neat
    // like String movieTitle, etc...
    public Dvd(String movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dvd dvd = (Dvd) o;

        if (!title.equals(dvd.title)) return false;
        if (!movieID.equals(dvd.movieID)) return false;
        if (!releaseDate.equals(dvd.releaseDate)) return false;
        if (!mpaaRating.equals(dvd.mpaaRating)) return false;
        if (!directorName.equals(dvd.directorName)) return false;
        if (!studio.equals(dvd.studio)) return false;
        return userRating.equals(dvd.userRating);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + movieID.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + mpaaRating.hashCode();
        result = 31 * result + directorName.hashCode();
        result = 31 * result + studio.hashCode();
        result = 31 * result + userRating.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "title='" + title + '\'' +
                ", movieID='" + movieID + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userRating='" + userRating + '\'' +
                '}';
    }

}
