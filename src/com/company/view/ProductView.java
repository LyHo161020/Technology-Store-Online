package com.company.view;

import com.company.menu.Menu;
import com.company.model.Product;
import com.company.services.ProductManager;

import java.util.Scanner;

public class ProductView {
    public static final String ĐIỆN_THOẠI = "điện thoại";
    public static final String LAPTOP = "laptop";
    private static Scanner scanner = new Scanner(System.in);

    public static void showCustomList() {
        System.out.println("------------Product-Manager------------");
        System.out.println("          1. Tìm kiếm sản phẩm.        ");
        System.out.println("          2. Thêm vào yêu thích.       ");
        System.out.println("          3. Thêm vào giỏ hàng.        ");
        System.out.println("          0. Quay lại.                 ");
        System.out.println("---------------------------------------");
    }


    public static void showMenuProduct() {
        System.out.println("**************************** MENU ****************************");
        System.out.println("**                  1. Danh sách sản phẩm                   **");
        System.out.println("**                  2. Danh sách yêu thích                  **");
        System.out.println("**                  3. Danh sách giỏ hàng                   **");
        System.out.println("**                  4. Tuỳ chỉnh sản phẩm                   **");
        System.out.println("**                  0. Quay lại                             **");
        System.out.println("**************************************************************");
    }

    public static void showSelectionList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************************************");
        System.out.println("**               1. Danh sách tất cả sản phẩm               **");
        System.out.println("**               2. Danh sách điện thoai                    **");
        System.out.println("**               3. Danh sách laptop                        **");
        System.out.println("**               0. Quay lại                                **");
        System.out.println("**************************************************************");
        System.out.println("Nhập lựa chọn: ");
//        showProduct();
    }

    public static void showProduct() {
        do {
            try {
                showSelectionList();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ProductManager.showProduct();
                        showProduct();
                        break;
                    case 2:
                        ProductManager.showSmartPhone();
                        break;
                    case 3:
                        ProductManager.showLaptop();
                        break;
                    case 0:
                        Menu.init();
                        break;
                    default:
                        System.out.println("----------- Nhập lại lựa chọn phù hợp! -----------");
                        showProduct();
                        break;
                }
            } catch (Exception e) {
                System.out.println("----------- Nhập lại lựa chọn phù hợp! -----------");
                showProduct();
            }
        } while (true);
    }

    public static void showProductFunction() {
        int choice;
        do {
            ProductView.showCustomList();
            try {
                System.out.println("Nhập lựa chọn: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ProductManager.searchProduct("USER");
                        break;
                    case 2:
                        ProductManager.addFavourite();
                        break;
                    case 3:
                        ProductManager.addCart();
                        break;
                    case 0:
                        Menu.init();
                        break;
                    default:
                        System.out.println("------ Lựa chọn không phù hợp! ------");
                        showProductFunction();
                        break;
                }
            } catch (Exception e) {
                System.out.println("------ Lựa chọn không phù hợp! ------");
                showProductFunction();
            }
        } while (true);
    }


    public static void backToEarlier(String str, String nameMenu, String role) {
        if (str.equals("0")) {
            if (role.equals("ADMIN")) {
                switch (nameMenu) {
                    case "showProductFunction":
                        AdminView.showProductFunctionAdmin();
                        break;
                    case "showUserMenu":
                        AdminView.showUserMenuAdmin();
                        break;
                    default:
                        AdminView.reLoginAdmin();
                        break;
                }
            } else {
                switch (nameMenu) {
                    case "showProductFunction":
                        showProductFunction();
                        break;
                    case "showUserMenu":
                        UserView.showUserMenu();
                        break;
                    case "selectMainMenuOrder":
                        OrderView.selectMainMenuOrder();
                        break;
                    case "supportCenter":
                        AdminView.supportCenter();
                        break;
                    default:
                        AdminView.reLoginAdmin();
                        break;
                }
            }
        }
    }


    public static Product enterProductInfo() {
        System.out.println("------- BẤM 0 NẾU MUỐN THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ -------");
        System.out.println("Nhập thông tin sản phẩm muốn thêm!");
        System.out.println("Nhập loại sản phẩm(điện thoại/laptop):  ");
        String type = scanner.nextLine().toLowerCase();
        while (!type.equals(ĐIỆN_THOẠI) && !type.equals(LAPTOP) && !type.equals("dien thoai")) {
            backToEarlier(type, "showProductFunction", "ADMIN");
            System.out.println("Loại sản phẩm không đúng! Mời bạn nhập lại(bấm 0 nếu muốn quay lại menu trước đó): ");
            type = scanner.nextLine().toLowerCase();
        }

        System.out.print("Nhập tên sản phẩm(bấm 0 nếu muốn quay lại menu trước đó): ");
        String name = scanner.nextLine();

        while (name.length() == 0) {
            backToEarlier(name, "showProductFunction", "ADMIN");
            System.out.println("Tên không thể để trống! Mời bạn nhập lại: ");
            name = scanner.nextLine();
        }

        int amount = 0;
        do {

            try {
                System.out.print("Nhập số lượng sản phẩm(bấm 0 nếu muốn quay lại menu trước đó): ");
                amount = Integer.parseInt(scanner.nextLine());

                while (amount <= 0) {
                    backToEarlier(Integer.toString(amount), "showProductFunction", "ADMIN");
                    System.out.println("Số lượng không hợp lệ! Mời bạn nhập lại: ");
                    amount = Integer.parseInt(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Nhập số lượng hợp lệ!");
            }
        } while (amount <= 0);

        int price = 0;
        do {
            try {
                System.out.print("Nhập giá sản phẩm(bấm 0 nếu muốn quay lại menu trước đó): ");
                price = Integer.parseInt(scanner.nextLine());

                while (price <= 0) {
                    backToEarlier(Integer.toString(price), "showProductFunction", "ADMIN");
                    System.out.println("Giá không hợp lệ! Mời bạn nhập lại: ");
                    price = Integer.parseInt(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Giá sản phẩm không hợp lệ!");
            }
        } while (price <= 0);

        System.out.println("------- SẢN PHẨM ĐÃ ĐƯỢC THÊM VÀO DANH SÁCH -------");
        Product product = new Product(type, name, amount, price);
        return product;
    }


//    public static void addProduct() {
//        Product product = ProductView.enterProductInfo();
//        if (products.contains(product)) {
//            System.out.println("Sản phẩm đã tồn tại!");
//        } else {
//            products.add(product);
//        }
//    }
//
//    public static int findIndexById(long id) {
//        int index = 0, i;
//        for (i = 0; i < products.size(); i++) {
//            if (products.get(i).getId() == id) {
//                index = i;
//                break;
//            }
//        }
//
//        if (i == products.size()) {
//            System.out.println("Không tồn tại sản phẩm có id là: " + id);
//            return -1;
//        }
//        return index;
//    }
//
//    public static void editProduct() {
//        System.out.print("Nhập id sản phẩm muốn sửa: ");
//        long id = Long.parseLong(scanner.nextLine());
//        int index = findIndexById(id);
//        editInfoProduct(id, index);
//    }
//
//    public static void editInfoProduct(long id, int index) {
//        System.out.println("Nhập thông cần chỉnh sửa!");
//        System.out.println("Nhập tên sản phẩm: ");
//        String name = scanner.nextLine();
//
//        String type = products.get(index).getType();
//        System.out.println("Nhập số lượng sản phẩm: ");
//        int amount = Integer.parseInt(scanner.nextLine());
//
//        System.out.println("Nhập giá sản phẩm: ");
//        int price = Integer.parseInt(scanner.nextLine());
//
//        Product product = new Product(id,type, name, amount, price);
//        products.set(index, product);
////        products.get(index).setId(id);
//    }
//
//    public static Product searchProduct() {
//        System.out.print("Nhập id sản phẩm muốn tìm: ");
//        long id = Long.parseLong(scanner.nextLine());
//        sortProduct();
//        int index = findIndexById(id);
//        int low = 0, high = products.size() - 1;
//        return binarySearch(products, low, high, products.get(index));
//    }
//
//    public static Product binarySearch(ArrayList<Product> products, int low, int high, Product product) {
//        int mid = (low + high) / 2;
//        if (products.get(mid).equals(product)) {
//            return products.get(mid);
//        }
//
//        if (products.get(mid).compareTo(product) > 0) {
//            return binarySearch(products, low, mid - 1, product);
//        }
//
//        if (products.get(mid).compareTo(product) < 0) {
//            return binarySearch(products, mid + 1, high, product);
//        }
//
//        return null;
//    }
//
//    public static void removeProduct() {
//        System.out.print("Nhập id sản phẩm muốn xoá: ");
//        long id = Long.parseLong(scanner.nextLine());
//        System.out.println("Sản phẩm có id là: " + id + " đã được xoá khỏi danh sách!");
//        int index = findIndexById(id);
//        products.remove(index);
//    }
//
//    public static void showProduct() {
//        if (isEmpty()) {
//            System.out.println("Danh sách sản phẩm trống!");
////            ListProduct.showMenu();
//        } else {
//            System.out.println("------------------------------------- DANH SÁCH SẢN PHẨM --------------------------------------");
//            System.out.printf("*        %-10s %-20s %-25s %-17s %-8s *\n", "ID","Loại sản phẩm","Tên sản phẩm", "Số lượng", "Giá");
//
//            for (int i = 0; i < products.size(); i++) {
//                System.out.printf("*        %-11s %-15s %-32s %-12s %-10s *\n", products.get(i).getId(),products.get(i).getType(),products.get(i).getName(), products.get(i).getAmount(), products.get(i).getPrice());
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------------");
//            ProductView.showSelectionList();
//        }
//    }
//
//    public static void showLaptop(){
//        ArrayList<Product> laptop = new ArrayList<>();
//
//        for(int i = 0; i < products.size(); i++){
//            if(products.get(i).getType().toLowerCase(Locale.ROOT).equals("laptop")){
//                laptop.add(products.get(i));
//            }
//        }
//
//        if(laptop.size() == 0){
//            System.out.println("Chưa có sản phẩm nào trong danh sách này!");
////            ListProduct.showMenu();
//        }else{
//            System.out.println("------------------------------------- DANH SÁCH LAPTOP ----------------------------------------");
//            System.out.printf("*        %-10s %-20s %-25s %-17s %-8s *\n", "ID","Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");
//
//            for (int i = 0; i < laptop.size(); i++) {
//                System.out.printf("*        %-11s %-15s %-32s %-12s %-10s *\n", laptop.get(i).getId(),laptop.get(i).getType(), laptop.get(i).getName(), laptop.get(i).getAmount(), laptop.get(i).getPrice());
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------------");
//            ProductView.showSelectionList();
//        }
//    }
//
//    public static void showSmartPhone(){
//        ArrayList<Product> smartPhone = new ArrayList<>();
//
//        for(int i = 0; i < products.size(); i++){
//            if(products.get(i).getType().toLowerCase(Locale.ROOT).equals("điện thoại")){
//                smartPhone.add(products.get(i));
//            }
//        }
//
//        if(smartPhone.size() == 0){
//            System.out.println("Chưa có sản phẩm nào trong danh sách này!");
////            ListProduct.showMenu();
//        }else{
//            System.out.println("----------------------------------- DANH SÁCH ĐIỆN THOẠI --------------------------------------");
//            System.out.printf("*        %-10s %-20s %-25s %-17s %-8s *\n", "ID","Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");
//
//            for (int i = 0; i < smartPhone.size(); i++) {
//                System.out.printf("*        %-11s %-15s %-32s %-12s %-10s *\n", smartPhone.get(i).getId(),smartPhone.get(i).getType(), smartPhone.get(i).getName(), smartPhone.get(i).getAmount(), smartPhone.get(i).getPrice());
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------------");
//            ProductView.showSelectionList();
//        }
//    }
//
//    public static void showFavouriteProduct() {
//        if(favouriteProduct.size() == 0){
//            System.out.println("Chưa có sản phẩm nào trong danh sách này!");
//            ProductView.showMenu();
//        }else{
//            System.out.println("----------------------------------- DANH SÁCH YÊU THÍCH ---------------------------------------");
//            System.out.printf("*        %-10s %-20s %-25s %-17s %-8s *\n", "ID","Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");
//
//            for (int i = 0; i < favouriteProduct.size(); i++) {
//                System.out.printf("*        %-11s %-15s %-32s %-12s %-10s *\n", favouriteProduct.get(i).getId(),favouriteProduct.get(i).getType(), favouriteProduct.get(i).getName(), favouriteProduct.get(i).getAmount(), favouriteProduct.get(i).getPrice());
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------------");
//            ProductView.showMenu();
//        }
//    }
//
//    public static void showCart() {
//        if(cart.size() == 0){
//            System.out.println("Chưa có sản phẩm nào trong danh sách này!");
//            ProductView.showMenu();
//        }else{
//            System.out.println("----------------------------------- DANH SÁCH GIỎ HÀNG ---------------------------------------");
//            System.out.printf("*        %-10s %-20s %-25s %-17s %-8s *\n", "ID","Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");
//
//            for (int i = 0; i < cart.size(); i++) {
//                System.out.printf("*        %-11s %-15s %-32s %-12s %-10s *\n", cart.get(i).getId(),cart.get(i).getType(), cart.get(i).getName(), cart.get(i).getAmount(), cart.get(i).getPrice());
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------------");
//            ProductView.showMenu();
//        }
//    }
//
//    public static void addFavourite() {
//        System.out.println("Nhập id sản phẩm muốn thêm: ");
//        long id = Long.parseLong(scanner.nextLine());
//        int index = findIndexById(id);
//        favouriteProduct.add(products.get(index));
//    }
//
//    public static void addCart() {
//        System.out.println("Nhập id sản phẩm muốn thêm: ");
//        long id = Long.parseLong(scanner.nextLine());
//        int index = findIndexById(id);
//        cart.add(products.get(index));
//    }
//
//    public static ArrayList<Product> sortProduct() {
//        Collections.sort(products);
//        return products;
//    }
}

