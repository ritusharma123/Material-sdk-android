package com.curiosearch.materiall.model.materialresponse;

import java.util.ArrayList;

public class Products
{
    private String id;
    private int score;
    private ArrayList<RecommendationMeta> recommendationMeta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<RecommendationMeta> getRecommendationMeta() {
        return recommendationMeta;
    }

    public void setRecommendationMeta(ArrayList<RecommendationMeta> recommendationMeta) {
        this.recommendationMeta = recommendationMeta;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+",score = "+score+",recommendationMeta = "+recommendationMeta+"]";
    }
}