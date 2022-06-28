package model;

public class Product {
    private int productId;
    private String nameProduct;
    private double priceProduct;
    private int quantityProduct;
    private String typeProduct;
    private String description;
    private String updatedAT;
    private String createdAT;
    private String image;

    public Product() {
    }

    public Product(int productId, String nameProduct, double priceProduct, int quantityProduct, String typeProduct, String description, String image) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct = quantityProduct;
        this.typeProduct = typeProduct;
        this.description = description;
        this.image = image;
    }

    public Product(int productId, String nameProduct, String piceProduct, int quantityProduct, String typeProduct, String description, String image) {
        this.priceProduct = Double.parseDouble(piceProduct);
        this.nameProduct = nameProduct;
        this.quantityProduct = quantityProduct;
        this.typeProduct = typeProduct;
        this.description = description;
        this.image = image;
    }

    public Product(int productId, String nameProduct, String priceProduct, int quantityProduct, String typeProduct, String description, String createdAT, String updatedAT, String image) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.priceProduct = productId;
        this.quantityProduct = quantityProduct;
        this.typeProduct = typeProduct;
        this.description = description;
        this.createdAT = createdAT;
        this.updatedAT = updatedAT;
        this.image = image;
    }

    public Product(int productId,String nameProduct, String priceProduct, String quantityProduct, String typeProduct, String description, String image) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.priceProduct = Double.parseDouble(priceProduct);
        this.quantityProduct = Integer.parseInt(quantityProduct);
        this.typeProduct = typeProduct;
        this.description = description;
        this.image = image;
    }

    public Product(String nameProduct, String priceProduct, String quantityProduct, String typeProduct, String description, String image) {
        this.nameProduct = nameProduct;
        this.priceProduct = Double.parseDouble(priceProduct);
        this.quantityProduct = Integer.parseInt(quantityProduct);
        this.typeProduct = typeProduct;
        this.description = description;
        this.image = image;
    }

    public Product(int productId, String nameProduct, Double priceProduct, int quantityProduct, String typeProduct, String description, String createdAT, String updatedAT, String image) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.priceProduct = Double.parseDouble(String.valueOf(priceProduct));
        this.quantityProduct = Integer.parseInt(String.valueOf(quantityProduct));
        this.typeProduct = typeProduct;
        this.description = description;
        this.createdAT = createdAT;
        this.updatedAT = updatedAT;
        this.image = image;
    }



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(String updatedAT) {
        this.updatedAT = updatedAT;
    }

    public String getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(String createdAT) {
        this.createdAT = createdAT;
    }
}
