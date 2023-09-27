import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String description;
    private boolean status;

    public Product() {

    }
    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String description, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private static int currentIndex = 0;
    public void inputData(Scanner sc, Product[] arrProduct) {
        System.out.println("Nhập mã sản phẩm (4 ký tự, bắt đầu bằng 'P'): ");
        this.productId = sc.nextLine();

        while (!isValidProductId(productId) || isDuplicateProductId(productId, arrProduct)) {
            if (!isValidProductId(productId)) {
                System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
            } else {
                System.out.println("Mã sản phẩm đã trùng lặp. Vui lòng nhập lại.");
            }

            System.out.println("Nhập mã sản phẩm (4 ký tự, bắt đầu bằng 'P'): ");
            this.productId = sc.nextLine();
        }

        System.out.println("Nhập tên sản phẩm (từ 6-50 ký tự): ");
        this.productName = sc.nextLine();

        while (!isValidProductName(productName) || isDuplicateProductName(productName, arrProduct)) {
            if (!isValidProductName(productName)) {
                System.out.println("Tên sản phẩm không hợp lệ. Vui lòng nhập lại.");
            } else {
                System.out.println("Tên sản phẩm đã trùng lặp. Vui lòng nhập lại.");
            }

            System.out.println("Nhập tên sản phẩm (từ 6-50 ký tự): ");
            this.productName = sc.nextLine();
        }

        System.out.println("Nhập giá nhập sản phẩm: ");
        this.importPrice = Float.parseFloat(sc.nextLine());

        while (!isValidImportPrice(importPrice)) {
            System.out.println("Giá nhập không hợp lệ. Vui lòng nhập lại.");
            System.out.println("Nhập giá nhập sản phẩm: ");
            this.importPrice = Float.parseFloat(sc.nextLine());
        }

        System.out.println("Nhập giá bán sản phẩm (giá bán lớn hơn " + (importPrice * 1.2) + "): ");
        this.exportPrice = Float.parseFloat(sc.nextLine());

        while (!isValidExportPrice(exportPrice, importPrice)) {
            System.out.println("Giá bán không hợp lệ. Vui lòng nhập lại.");
            System.out.println("Nhập giá bán sản phẩm (giá bán lớn hơn " + (importPrice * 1.2) + "): ");
            this.exportPrice = Float.parseFloat(sc.nextLine());
        }

        System.out.println("Nhập số lượng sản phẩm: ");
        this.quantity = Integer.parseInt(sc.nextLine());

        while (!isValidQuantity(quantity)) {
            System.out.println("Số lượng không hợp lệ. Vui lòng nhập lại.");
            System.out.println("Nhập số lượng sản phẩm: ");
            this.quantity = Integer.parseInt(sc.nextLine());
        }

        System.out.println("Nhập mô tả sản phẩm: ");
        this.description = sc.nextLine();

        System.out.println("Nhập trạng thái sản phẩm (true / false): ");
        this.status = Boolean.parseBoolean(sc.nextLine());
        calProfit();
        currentIndex++;
    }

    private boolean isValidProductId(String productId) {
        return productId.length() == 4 && productId.startsWith("P");
    }

    private boolean isDuplicateProductId(String productId, Product[] arrProduct) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProduct[i] != null && arrProduct[i].getProductId().equalsIgnoreCase(productId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidProductName(String productName) {
        return productName.length() >= 6 && productName.length() <= 50;
    }

    private boolean isDuplicateProductName(String productName, Product[] arrProduct) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProduct[i] != null && arrProduct[i].getProductName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidImportPrice(float importPrice) {
        return importPrice > 0;
    }

    private boolean isValidExportPrice(float exportPrice, float importPrice) {
        return exportPrice > importPrice * 1.2;
    }

    private boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
    public void displayData(){
        System.out.println("Thông tin sản phẩm:");
        System.out.println("ID sản phẩm: " + this.productId);
        System.out.println("Tên sản phẩm: " + this.productName);
        System.out.println("Giá nhập sản phẩm: " + this.importPrice);
        System.out.println("Giá bán sản phẩm: " + this.exportPrice);
        System.out.println("Số lượng sản phẩm: " + this.quantity);
        System.out.println("Mô tả sản phẩm: " + this.description);
        System.out.println("Trạng thái sản phẩm: " + (this.status ? "Đang bán" : "Không bán"));
        System.out.println("Lợi nhuận: " + this.profit);
        System.out.println("___________________________________________________");

    }

    public void calProfit(){
        this.profit = this.exportPrice - this.importPrice;
    }
}
