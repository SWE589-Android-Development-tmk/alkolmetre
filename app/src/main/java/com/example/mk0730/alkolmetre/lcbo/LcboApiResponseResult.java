
package com.example.mk0730.alkolmetre.lcbo;

import com.squareup.moshi.Json;

public class LcboApiResponseResult {

    @Json(name = "id")
    private Integer id;
    @Json(name = "is_dead")
    private Boolean isDead;
    @Json(name = "name")
    private String name;
    @Json(name = "tags")
    private String tags;
    @Json(name = "is_discontinued")
    private Boolean isDiscontinued;
    @Json(name = "price_in_cents")
    private Integer priceInCents;
    @Json(name = "regular_price_in_cents")
    private Integer regularPriceInCents;
    @Json(name = "limited_time_offer_savings_in_cents")
    private Integer limitedTimeOfferSavingsInCents;
    @Json(name = "limited_time_offer_ends_on")
    private Object limitedTimeOfferEndsOn;
    @Json(name = "bonus_reward_miles")
    private Integer bonusRewardMiles;
    @Json(name = "bonus_reward_miles_ends_on")
    private Object bonusRewardMilesEndsOn;
    @Json(name = "stock_type")
    private String stockType;
    @Json(name = "primary_category")
    private String primaryCategory;
    @Json(name = "secondary_category")
    private String secondaryCategory;
    @Json(name = "origin")
    private String origin;
    @Json(name = "package")
    private String _package;
    @Json(name = "package_unit_type")
    private String packageUnitType;
    @Json(name = "package_unit_volume_in_milliliters")
    private Integer packageUnitVolumeInMilliliters;
    @Json(name = "total_package_units")
    private Integer totalPackageUnits;
    @Json(name = "volume_in_milliliters")
    private Integer volumeInMilliliters;
    @Json(name = "alcohol_content")
    private Integer alcoholContent;
    @Json(name = "price_per_liter_of_alcohol_in_cents")
    private Integer pricePerLiterOfAlcoholInCents;
    @Json(name = "price_per_liter_in_cents")
    private Integer pricePerLiterInCents;
    @Json(name = "inventory_count")
    private Integer inventoryCount;
    @Json(name = "inventory_volume_in_milliliters")
    private Integer inventoryVolumeInMilliliters;
    @Json(name = "inventory_price_in_cents")
    private Integer inventoryPriceInCents;
    @Json(name = "sugar_content")
    private Object sugarContent;
    @Json(name = "producer_name")
    private String producerName;
    @Json(name = "released_on")
    private Object releasedOn;
    @Json(name = "has_value_added_promotion")
    private Boolean hasValueAddedPromotion;
    @Json(name = "has_limited_time_offer")
    private Boolean hasLimitedTimeOffer;
    @Json(name = "has_bonus_reward_miles")
    private Boolean hasBonusRewardMiles;
    @Json(name = "is_seasonal")
    private Boolean isSeasonal;
    @Json(name = "is_vqa")
    private Boolean isVqa;
    @Json(name = "is_ocb")
    private Boolean isOcb;
    @Json(name = "is_kosher")
    private Boolean isKosher;
    @Json(name = "value_added_promotion_description")
    private Object valueAddedPromotionDescription;
    @Json(name = "description")
    private Object description;
    @Json(name = "serving_suggestion")
    private String servingSuggestion;
    @Json(name = "tasting_note")
    private String tastingNote;
    @Json(name = "updated_at")
    private String updatedAt;
    @Json(name = "image_thumb_url")
    private String imageThumbUrl;
    @Json(name = "image_url")
    private String imageUrl;
    @Json(name = "varietal")
    private String varietal;
    @Json(name = "style")
    private String style;
    @Json(name = "tertiary_category")
    private String tertiaryCategory;
    @Json(name = "sugar_in_grams_per_liter")
    private Object sugarInGramsPerLiter;
    @Json(name = "clearance_sale_savings_in_cents")
    private Integer clearanceSaleSavingsInCents;
    @Json(name = "has_clearance_sale")
    private Boolean hasClearanceSale;
    @Json(name = "product_no")
    private Integer productNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(Boolean isDead) {
        this.isDead = isDead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getIsDiscontinued() {
        return isDiscontinued;
    }

    public void setIsDiscontinued(Boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public Integer getRegularPriceInCents() {
        return regularPriceInCents;
    }

    public void setRegularPriceInCents(Integer regularPriceInCents) {
        this.regularPriceInCents = regularPriceInCents;
    }

    public Integer getLimitedTimeOfferSavingsInCents() {
        return limitedTimeOfferSavingsInCents;
    }

    public void setLimitedTimeOfferSavingsInCents(Integer limitedTimeOfferSavingsInCents) {
        this.limitedTimeOfferSavingsInCents = limitedTimeOfferSavingsInCents;
    }

    public Object getLimitedTimeOfferEndsOn() {
        return limitedTimeOfferEndsOn;
    }

    public void setLimitedTimeOfferEndsOn(Object limitedTimeOfferEndsOn) {
        this.limitedTimeOfferEndsOn = limitedTimeOfferEndsOn;
    }

    public Integer getBonusRewardMiles() {
        return bonusRewardMiles;
    }

    public void setBonusRewardMiles(Integer bonusRewardMiles) {
        this.bonusRewardMiles = bonusRewardMiles;
    }

    public Object getBonusRewardMilesEndsOn() {
        return bonusRewardMilesEndsOn;
    }

    public void setBonusRewardMilesEndsOn(Object bonusRewardMilesEndsOn) {
        this.bonusRewardMilesEndsOn = bonusRewardMilesEndsOn;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getPackageUnitType() {
        return packageUnitType;
    }

    public void setPackageUnitType(String packageUnitType) {
        this.packageUnitType = packageUnitType;
    }

    public Integer getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(Integer packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public Integer getTotalPackageUnits() {
        return totalPackageUnits;
    }

    public void setTotalPackageUnits(Integer totalPackageUnits) {
        this.totalPackageUnits = totalPackageUnits;
    }

    public Integer getVolumeInMilliliters() {
        return volumeInMilliliters;
    }

    public void setVolumeInMilliliters(Integer volumeInMilliliters) {
        this.volumeInMilliliters = volumeInMilliliters;
    }

    public Integer getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(Integer alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public Integer getPricePerLiterOfAlcoholInCents() {
        return pricePerLiterOfAlcoholInCents;
    }

    public void setPricePerLiterOfAlcoholInCents(Integer pricePerLiterOfAlcoholInCents) {
        this.pricePerLiterOfAlcoholInCents = pricePerLiterOfAlcoholInCents;
    }

    public Integer getPricePerLiterInCents() {
        return pricePerLiterInCents;
    }

    public void setPricePerLiterInCents(Integer pricePerLiterInCents) {
        this.pricePerLiterInCents = pricePerLiterInCents;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public Integer getInventoryVolumeInMilliliters() {
        return inventoryVolumeInMilliliters;
    }

    public void setInventoryVolumeInMilliliters(Integer inventoryVolumeInMilliliters) {
        this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
    }

    public Integer getInventoryPriceInCents() {
        return inventoryPriceInCents;
    }

    public void setInventoryPriceInCents(Integer inventoryPriceInCents) {
        this.inventoryPriceInCents = inventoryPriceInCents;
    }

    public Object getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(Object sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Object getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Object releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Boolean getHasValueAddedPromotion() {
        return hasValueAddedPromotion;
    }

    public void setHasValueAddedPromotion(Boolean hasValueAddedPromotion) {
        this.hasValueAddedPromotion = hasValueAddedPromotion;
    }

    public Boolean getHasLimitedTimeOffer() {
        return hasLimitedTimeOffer;
    }

    public void setHasLimitedTimeOffer(Boolean hasLimitedTimeOffer) {
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
    }

    public Boolean getHasBonusRewardMiles() {
        return hasBonusRewardMiles;
    }

    public void setHasBonusRewardMiles(Boolean hasBonusRewardMiles) {
        this.hasBonusRewardMiles = hasBonusRewardMiles;
    }

    public Boolean getIsSeasonal() {
        return isSeasonal;
    }

    public void setIsSeasonal(Boolean isSeasonal) {
        this.isSeasonal = isSeasonal;
    }

    public Boolean getIsVqa() {
        return isVqa;
    }

    public void setIsVqa(Boolean isVqa) {
        this.isVqa = isVqa;
    }

    public Boolean getIsOcb() {
        return isOcb;
    }

    public void setIsOcb(Boolean isOcb) {
        this.isOcb = isOcb;
    }

    public Boolean getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(Boolean isKosher) {
        this.isKosher = isKosher;
    }

    public Object getValueAddedPromotionDescription() {
        return valueAddedPromotionDescription;
    }

    public void setValueAddedPromotionDescription(Object valueAddedPromotionDescription) {
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getServingSuggestion() {
        return servingSuggestion;
    }

    public void setServingSuggestion(String servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }

    public String getTastingNote() {
        return tastingNote;
    }

    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTertiaryCategory() {
        return tertiaryCategory;
    }

    public void setTertiaryCategory(String tertiaryCategory) {
        this.tertiaryCategory = tertiaryCategory;
    }

    public Object getSugarInGramsPerLiter() {
        return sugarInGramsPerLiter;
    }

    public void setSugarInGramsPerLiter(Object sugarInGramsPerLiter) {
        this.sugarInGramsPerLiter = sugarInGramsPerLiter;
    }

    public Integer getClearanceSaleSavingsInCents() {
        return clearanceSaleSavingsInCents;
    }

    public void setClearanceSaleSavingsInCents(Integer clearanceSaleSavingsInCents) {
        this.clearanceSaleSavingsInCents = clearanceSaleSavingsInCents;
    }

    public Boolean getHasClearanceSale() {
        return hasClearanceSale;
    }

    public void setHasClearanceSale(Boolean hasClearanceSale) {
        this.hasClearanceSale = hasClearanceSale;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

}
