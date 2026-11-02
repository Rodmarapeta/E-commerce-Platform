package ecommerce.model;

public record ShippingInfo(String fullName, String email, String phone, String address, String city, String postalCode) {
    public String addressLine() {
        return address + ", " + city + " " + postalCode;
    }
}
