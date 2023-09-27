import java.util.Scanner;

public class ProductImp {
    private static Product[] arrProduct = new Product[100];
    private static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("********************* MENU ************************");
            System.out.println("1. Nhập thông tin n sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Tính lợi nhuận các sản phẩm");
            System.out.println("5. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("6. Thống kê các sản phẩm theo giá");
            System.out.println("7. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("8. Nhập thêm số lượng cho sản phẩm");
            System.out.println("9. Bán sản phẩm");
            System.out.println("10. Cập nhật trạng thái sản phẩm");
            System.out.println("11. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inputProducts(scanner);
                    break;

                case 2:
                    displayProducts();
                    break;

                case 3:
                    deleteProduct(scanner);
                    break;

                case 4:
                    calculateProfits();
                    break;

                case 5:
                    sortProductsByProfit();
                    break;

                case 6:
                    calculatePriceStatistics(scanner);
                    break;

                case 7:
                    searchProductsByName(scanner);
                    break;

                case 8:
                    inputProductQuantity(scanner);
                    break;

                case 9:
                    sellProductQuantity(scanner);
                    break;

                case 10:
                    updateProductStatus(scanner);
                    break;

                case 11:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 11);
    }

    private static void inputProducts(Scanner scanner) {
        System.out.print("Nhập số lượng sản phẩm: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData(scanner, arrProduct);
            arrProduct[productCount++] = product;
        }
    }

    private static void displayProducts() {
        System.out.println("Danh sách sản phẩm:");
        for (int i = 0; i < productCount; i++) {
            arrProduct[i].displayData();
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String deleteProductId = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (arrProduct[i].getProductId().equalsIgnoreCase(deleteProductId)) {
                for (int j = i; j < productCount - 1; j++) {
                    arrProduct[j] = arrProduct[j + 1];
                }
                arrProduct[productCount - 1] = null;
                productCount--;
                found = true;
                System.out.println("Đã xóa sản phẩm có mã " + deleteProductId);
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm có mã " + deleteProductId);
        }
    }


    private static void calculateProfits() {
        System.out.println("Lợi nhuận các sản phẩm:");
        for (int i = 0; i < productCount; i++) {
            arrProduct[i].calProfit();
            System.out.println("Sản phẩm " + arrProduct[i].getProductName() + ": " + arrProduct[i].getProfit());
        }
    }

    private static void sortProductsByProfit() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < productCount - 1; i++) {
                if (arrProduct[i].getProfit() < arrProduct[i + 1].getProfit()) {
                    Product temp = arrProduct[i];
                    arrProduct[i] = arrProduct[i + 1];
                    arrProduct[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        System.out.println("Sản phẩm sau khi sắp xếp theo lợi nhuận giảm dần:");
        for (int i = 0; i < productCount; i++) {
            arrProduct[i].displayData();
        }
    }
    private static void calculatePriceStatistics(Scanner scanner) {
        System.out.print("Nhập giá bắt đầu (fromPrice): ");
        float fromPrice = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Nhập giá kết thúc (toPrice): ");
        float toPrice = scanner.nextFloat();
        scanner.nextLine();

        int count = 0;
        for (int i = 0; i < productCount; i++) {
            if (arrProduct[i].getExportPrice() >= fromPrice && arrProduct[i].getExportPrice() <= toPrice) {
                count++;
            }
        }
        System.out.println("Số lượng sản phẩm có giá từ " + fromPrice + " đến " + toPrice + ": " + count);
    }

    private static void searchProductsByName(Scanner scanner) {
        boolean found = false;
        do {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String searchName = scanner.nextLine();

            System.out.println("Các sản phẩm có tên " + searchName + ":");
            for (int i = 0; i < productCount; i++) {
                if (arrProduct[i].getProductName().equalsIgnoreCase(searchName)) {
                    arrProduct[i].displayData();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy sản phẩm có tên " + searchName + ". Vui lòng nhập lại.");
            }
        } while (!found);
    }

    private static void inputProductQuantity(Scanner scanner) {
        boolean found = false;
        do {
            System.out.print("Nhập mã sản phẩm cần nhập: ");
            String inputProductId = scanner.nextLine();

            for (int i = 0; i < productCount; i++) {
                if (arrProduct[i].getProductId().equalsIgnoreCase(inputProductId)) {
                    found = true;
                    System.out.print("Nhập số lượng sản phẩm cần nhập: ");
                    int inputQuantity = scanner.nextInt();
                    scanner.nextLine();

                    arrProduct[i].setQuantity(arrProduct[i].getQuantity() + inputQuantity);
                    System.out.println("Đã nhập thêm " + inputQuantity + " sản phẩm " + arrProduct[i].getProductName());
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy sản phẩm có mã " + inputProductId + ". Vui lòng nhập lại.");
            }
        } while (!found);
    }

    private static void sellProductQuantity(Scanner scanner) {
        boolean found = false;
        do {
            System.out.print("Nhập tên sản phẩm cần bán: ");
            String sellProductName = scanner.nextLine();
            for (int i = 0; i < productCount; i++) {
                if (arrProduct[i].getProductName().equalsIgnoreCase(sellProductName)) {
                    found = true;
                    System.out.print("Nhập số lượng sản phẩm cần bán: ");
                    int sellQuantity = scanner.nextInt();
                    scanner.nextLine();

                    if (arrProduct[i].getQuantity() >= sellQuantity) {
                        arrProduct[i].setQuantity(arrProduct[i].getQuantity() - sellQuantity);
                        System.out.println("Đã bán " + sellQuantity + " sản phẩm " + arrProduct[i].getProductName());
                    } else {
                        System.out.println("Không đủ sản phẩm để bán.");
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy sản phẩm có tên " + sellProductName + ". Vui lòng nhập lại.");
            }
        } while (!found);
    }

    private static void updateProductStatus(Scanner scanner) {
        boolean found = false;
        do {
            System.out.print("Nhập mã sản phẩm cần cập nhật trạng thái: ");
            String updateProductId = scanner.nextLine();

            for (int i = 0; i < productCount; i++) {
                if (arrProduct[i].getProductId().equalsIgnoreCase(updateProductId)) {
                    arrProduct[i].setStatus(!arrProduct[i].isStatus());
                    System.out.println("Đã cập nhật trạng thái sản phẩm " + arrProduct[i].getProductName() +
                            " thành " + (arrProduct[i].isStatus() ? "Đang bán" : "Không bán"));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy sản phẩm có mã " + updateProductId + ". Vui lòng nhập lại.");
            }
        } while (!found);
    }
}