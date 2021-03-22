package com.curiosearch.materiall.model.materialresponse;

public class RecommendationMeta {
    private String value;
    private String lu;
    private String recommendationMode;
    private int score;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLu() {
        return lu;
    }

    public void setLu(String lu) {
        this.lu = lu;
    }

    public String getRecommendationMode() {
        return recommendationMode;
    }

    public void setRecommendationMode(String recommendationMode) {
        this.recommendationMode = recommendationMode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+",lu = "+lu+",recommendationMode = "+recommendationMode+",score = "+score+"]";
    }
}