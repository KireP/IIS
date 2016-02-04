package com.hello.model;

public class TrackDTO implements Comparable<TrackDTO> {

    private Track track;
    private double similarity;

    public TrackDTO(Track track, double similarity) {
        this.track = track;
        this.similarity = similarity;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    @Override
    public int compareTo(TrackDTO o) {
        return Double.compare(o.getSimilarity(), this.similarity);
    }
}
