package com.depex.eatasmuch.user.model;

import com.google.gson.annotations.SerializedName;



public class Marchant {
    @SerializedName("merchant_id")
    private String marchantId;
    @SerializedName("restaurant_slug")
    private String restaurantSlug;
    @SerializedName("restaurant_name")
    private String restaurantName;
    @SerializedName("restaurant_phone")
    private String restaurantPhone;
    @SerializedName("contact_name")
    private String contactName;
    @SerializedName("contact_phone")
    private String contactPhone;
    @SerializedName("contact_email")
    private String contactEmail;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("street")
    private String street;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("post_code")
    private String postCode;
    @SerializedName("cuisine")
    private String cuisine;
    @SerializedName("service")
    private String service;
    @SerializedName("free_delivery")
    private String freeDelivery;
    @SerializedName("delivery_estimation")
    private String deliveryEstimation;
    @SerializedName("is_featured")
    private String isFeatured;
    @SerializedName("is_ready")
    private String isReady;
    @SerializedName("is_sponsored")
    private String isSponsored;

    @SerializedName("photo")
    private String imageUrl;

    @SerializedName("rating")
    private float rating;

    private boolean isPickupAvailable;
    private boolean isDeliveryAvailable;
    private boolean isBothPickDeliveryAvailable;



    public boolean isPickupAvailable() {
        return isPickupAvailable;
    }

    public void setMarchantPickupDeliveryFacility(){
        switch (service){
            case "1":
                setBothPickDeliveryAvailable(true);
                break;
            case "2":
                setDeliveryAvailable(true);
                break;
            case "3":
                setPickupAvailable(true);
                break;
            default:
                setBothPickDeliveryAvailable(false);
                setPickupAvailable(false);
                setDeliveryAvailable(false);
        }
    }

    public void setPickupAvailable(boolean pickupAvailable) {
        isPickupAvailable = pickupAvailable;
    }

    public boolean isDeliveryAvailable() {
        return isDeliveryAvailable;
    }

    public void setDeliveryAvailable(boolean deliveryAvailable) {
        isDeliveryAvailable = deliveryAvailable;
    }

    public boolean isBothPickDeliveryAvailable() {
        return isBothPickDeliveryAvailable;
    }

    public void setBothPickDeliveryAvailable(boolean bothPickDeliveryAvailable) {
        isBothPickDeliveryAvailable = bothPickDeliveryAvailable;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(String marchantId) {
        this.marchantId = marchantId;
    }

    public String getRestaurantSlug() {
        return restaurantSlug;
    }

    public void setRestaurantSlug(String restaurantSlug) {
        this.restaurantSlug = restaurantSlug;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getService() {

        return service;
    }


    public void setService(String service) {
        this.service = service;
    }

    public String getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(String freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    public String getDeliveryEstimation() {
        return deliveryEstimation;
    }

    public void setDeliveryEstimation(String deliveryEstimation) {
        this.deliveryEstimation = deliveryEstimation;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getIsReady() {
        return isReady;
    }

    public void setIsReady(String isReady) {
        this.isReady = isReady;
    }

    public String getIsSponsored() {
        return isSponsored;
    }

    public void setIsSponsored(String isSponsored) {
        this.isSponsored = isSponsored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marchant marchant = (Marchant) o;

        if (Float.compare(marchant.getRating(), getRating()) != 0) return false;
        if (getMarchantId() != null ? !getMarchantId().equals(marchant.getMarchantId()) : marchant.getMarchantId() != null)
            return false;
        if (getRestaurantSlug() != null ? !getRestaurantSlug().equals(marchant.getRestaurantSlug()) : marchant.getRestaurantSlug() != null)
            return false;
        if (getRestaurantName() != null ? !getRestaurantName().equals(marchant.getRestaurantName()) : marchant.getRestaurantName() != null)
            return false;
        if (getRestaurantPhone() != null ? !getRestaurantPhone().equals(marchant.getRestaurantPhone()) : marchant.getRestaurantPhone() != null)
            return false;
        if (getContactName() != null ? !getContactName().equals(marchant.getContactName()) : marchant.getContactName() != null)
            return false;
        if (getContactPhone() != null ? !getContactPhone().equals(marchant.getContactPhone()) : marchant.getContactPhone() != null)
            return false;
        if (getContactEmail() != null ? !getContactEmail().equals(marchant.getContactEmail()) : marchant.getContactEmail() != null)
            return false;
        if (getCountryCode() != null ? !getCountryCode().equals(marchant.getCountryCode()) : marchant.getCountryCode() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(marchant.getStreet()) : marchant.getStreet() != null)
            return false;
        if (getCity() != null ? !getCity().equals(marchant.getCity()) : marchant.getCity() != null)
            return false;
        if (getState() != null ? !getState().equals(marchant.getState()) : marchant.getState() != null)
            return false;
        if (getPostCode() != null ? !getPostCode().equals(marchant.getPostCode()) : marchant.getPostCode() != null)
            return false;
        if (getCuisine() != null ? !getCuisine().equals(marchant.getCuisine()) : marchant.getCuisine() != null)
            return false;
        if (getService() != null ? !getService().equals(marchant.getService()) : marchant.getService() != null)
            return false;
        if (getFreeDelivery() != null ? !getFreeDelivery().equals(marchant.getFreeDelivery()) : marchant.getFreeDelivery() != null)
            return false;
        if (getDeliveryEstimation() != null ? !getDeliveryEstimation().equals(marchant.getDeliveryEstimation()) : marchant.getDeliveryEstimation() != null)
            return false;
        if (getIsFeatured() != null ? !getIsFeatured().equals(marchant.getIsFeatured()) : marchant.getIsFeatured() != null)
            return false;
        if (getIsReady() != null ? !getIsReady().equals(marchant.getIsReady()) : marchant.getIsReady() != null)
            return false;
        if (getIsSponsored() != null ? !getIsSponsored().equals(marchant.getIsSponsored()) : marchant.getIsSponsored() != null)
            return false;
        return getImageUrl() != null ? getImageUrl().equals(marchant.getImageUrl()) : marchant.getImageUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getMarchantId() != null ? getMarchantId().hashCode() : 0;
        result = 31 * result + (getRestaurantSlug() != null ? getRestaurantSlug().hashCode() : 0);
        result = 31 * result + (getRestaurantName() != null ? getRestaurantName().hashCode() : 0);
        result = 31 * result + (getRestaurantPhone() != null ? getRestaurantPhone().hashCode() : 0);
        result = 31 * result + (getContactName() != null ? getContactName().hashCode() : 0);
        result = 31 * result + (getContactPhone() != null ? getContactPhone().hashCode() : 0);
        result = 31 * result + (getContactEmail() != null ? getContactEmail().hashCode() : 0);
        result = 31 * result + (getCountryCode() != null ? getCountryCode().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getPostCode() != null ? getPostCode().hashCode() : 0);
        result = 31 * result + (getCuisine() != null ? getCuisine().hashCode() : 0);
        result = 31 * result + (getService() != null ? getService().hashCode() : 0);
        result = 31 * result + (getFreeDelivery() != null ? getFreeDelivery().hashCode() : 0);
        result = 31 * result + (getDeliveryEstimation() != null ? getDeliveryEstimation().hashCode() : 0);
        result = 31 * result + (getIsFeatured() != null ? getIsFeatured().hashCode() : 0);
        result = 31 * result + (getIsReady() != null ? getIsReady().hashCode() : 0);
        result = 31 * result + (getIsSponsored() != null ? getIsSponsored().hashCode() : 0);
        result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
        result = 31 * result + (getRating() != +0.0f ? Float.floatToIntBits(getRating()) : 0);
        return result;
    }


}
