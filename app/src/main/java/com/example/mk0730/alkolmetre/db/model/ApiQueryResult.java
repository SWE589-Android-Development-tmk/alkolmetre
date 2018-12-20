package com.example.mk0730.alkolmetre.db.model;

import android.content.ContentResolver;
import android.net.Uri;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;

@Entity
public class ApiQueryResult extends BaseModel {
    public static final String PATH = "query_result";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
            CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
            CONTENT_AUTHORITY + "/" + PATH;

    @Id
    private Long id;

    @NotNull
    private Integer productId;

    @NotNull
    private String name;
    private String origin;
    private String tastingNote;
    private java.util.Date releaseDate;
    private String sugarContent;
    private Integer alcoholContent;
    private Boolean isVqa;
    private Boolean isDiscontinued;
    private Integer priceInCents;
    private Integer pricePerLiterInCents;
    private String imageUrl;
    private int page;

    private long apiQueryId;

    @Generated(hash = 1708763712)
    public ApiQueryResult(Long id, @NotNull Integer productId, @NotNull String name,
            String origin, String tastingNote, java.util.Date releaseDate,
            String sugarContent, Integer alcoholContent, Boolean isVqa,
            Boolean isDiscontinued, Integer priceInCents,
            Integer pricePerLiterInCents, String imageUrl, int page,
            long apiQueryId) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.origin = origin;
        this.tastingNote = tastingNote;
        this.releaseDate = releaseDate;
        this.sugarContent = sugarContent;
        this.alcoholContent = alcoholContent;
        this.isVqa = isVqa;
        this.isDiscontinued = isDiscontinued;
        this.priceInCents = priceInCents;
        this.pricePerLiterInCents = pricePerLiterInCents;
        this.imageUrl = imageUrl;
        this.page = page;
        this.apiQueryId = apiQueryId;
    }


    @Generated(hash = 1511380564)
    public ApiQueryResult() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Integer getProductId() {
        return this.productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getOrigin() {
        return this.origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getTastingNote() {
        return this.tastingNote;
    }


    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }


    public java.util.Date getReleaseDate() {
        return this.releaseDate;
    }


    public void setReleaseDate(java.util.Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getSugarContent() {
        return this.sugarContent;
    }


    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }


    public Integer getAlcoholContent() {
        return this.alcoholContent;
    }


    public void setAlcoholContent(Integer alcoholContent) {
        this.alcoholContent = alcoholContent;
    }


    public Boolean getIsVqa() {
        return this.isVqa;
    }


    public void setIsVqa(Boolean isVqa) {
        this.isVqa = isVqa;
    }


    public Boolean getIsDiscontinued() {
        return this.isDiscontinued;
    }


    public void setIsDiscontinued(Boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }


    public Integer getPriceInCents() {
        return this.priceInCents;
    }


    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }


    public Integer getPricePerLiterInCents() {
        return this.pricePerLiterInCents;
    }


    public void setPricePerLiterInCents(Integer pricePerLiterInCents) {
        this.pricePerLiterInCents = pricePerLiterInCents;
    }


    public String getImageUrl() {
        return this.imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getPage() {
        return this.page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public long getApiQueryId() {
        return this.apiQueryId;
    }


    public void setApiQueryId(long apiQueryId) {
        this.apiQueryId = apiQueryId;
    }
}
