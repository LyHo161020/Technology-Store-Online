package com.company.services;

import java.text.DecimalFormat;
import java.util.*;

import com.company.menu.Menu;
import com.company.model.Product;
import com.company.utlis.CSVUtils;
import com.company.view.AdminView;
import com.company.view.ProductView;

public class ProductManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> favouriteProduct = new ArrayList<>();
    public static ArrayList<Product> cart = new ArrayList<>();
    private static final String path = "D:\\hoclaptrinh\\Module2\\case_study\\product_management\\data\\product.csv";


    public ProductManager() {
    }

    public ProductManager(ArrayList products) {
        this.products = products;
    }

    public static ArrayList<Product> getProduct() {
        if (products.size() == 0) {
            List<String> records = CSVUtils.read(path);

            for (String record : records) {
                products.add(new Product(record));
            }
            return products;
        }
        return null;
    }

    public static boolean isEmpty(ArrayList<Product> list) {
        return list.size() == 0;
    }

    public static void addProduct() {
        showProduct();
        Product product = ProductView.enterProductInfo();
        if (products.contains(product)) {
            System.out.println("Sản phẩm đã tồn tại!");
        } else {
            products.add(product);
            CSVUtils.write(path, products);
            showProduct();
        }
    }

    public static int findIndexById(long id) {
        int index = 0, i;
        for (i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if (i == products.size()) {
            System.out.println("----------Không tồn tại sản phẩm có id là: " + id + " ----------");
            return -1;
        }
        return index;
    }

    public static void update(){
        CSVUtils.write(path,products);
    }

    public static void editProduct() {
        showProduct();
        try {
            System.out.print("Nhập id sản phẩm muốn sửa ( bấm 0 nếu muốn thoát và quay lại menu trước đó):");
            long id = Long.parseLong(scanner.nextLine());
            while (!isExistId(products, id)) {
                ProductView.backToEarlier(Long.toString(id), "showProductFunction", "ADMIN");
                System.out.println("Không tồn tại sản phẩm có id là: " + id);
                System.out.print("Vui lòng nhập lại id: ");
                id = Long.parseLong(scanner.nextLine());
            }
            selectMenuEditProduct(id);
        } catch (Exception e) {
            System.out.println("Vui lòng nhập đúng id!");
            editProduct();
        }
    }

    public static boolean isExistId(ArrayList<Product> list, long id) {
        for (Product product : list) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Product getProductById(long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public static void selectMenuEditProduct(long id) {
        do {
            Menu.menuEditProduct();
            Product product = getProductById(id);
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        checkValidTypeProduct(product);
                        break;
                    case 2:
                        checkValidNameProduct(product);
                        break;
                    case 3:
                        checkValidAmountProduct(product);
                        break;
                    case 4:
                        checkValidPriceProduct(product);
                        break;
                    case 0:
                        AdminView.showProductFunctionAdmin();
                        break;
                    default:
                        System.out.println("Vui lòng nhập đúng chức năng!");
                        selectMenuEditProduct(product.getId());
                        break;
                }
                System.out.println("------------ THÔNG TIN SẢN PHẨM ĐÃ ĐƯỢC THAY ĐỔI THÀNH CÔNG ------------");
                CSVUtils.write(path, products);
            } catch (Exception e) {
                System.out.println("Vui lòng nhập đúng chức năng!");
                selectMenuEditProduct(product.getId());
            }
        } while (true);
    }

    private static void checkValidPriceProduct(Product product) {
        int price = 0;
        do {
            try {
                System.out.println("Nhập giá sản phẩm muốn sửa( bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
                price = Integer.parseInt(scanner.nextLine());

                while (price <= 0 || product.getPrice() == price) {
                    if (price == 0) {
                        selectMenuEditProduct(product.getId());

                    } else if (product.getPrice() == price) {
                        System.out.println("Giá thay đổi phải khác ban đâu!Vui lòng nhập lại giá sản phẩm: ");
                    } else {
                        System.out.println("Giá sản phẩm không hợp lệ! Vui lòng nhập lại giá sản phẩm: ");
                    }

                    price = Integer.parseInt(scanner.nextLine());
                }
                product.setPrice(price);
            }catch (Exception e){
                System.out.println("Giá sản phẩm không hợp lê");
            }
        }while (price <= 0 || product.getPrice() == price);
    }

    private static void checkValidAmountProduct(Product product) {
        int amount = 0;
       do {
           try {
               System.out.println("Nhập số lượng sản phẩm muốn sửa( bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
               amount = Integer.parseInt(scanner.nextLine());

               while (amount <= 0 || product.getAmount() == amount) {
                   if (amount == 0) {
                       selectMenuEditProduct(product.getId());
                   } else if (product.getAmount() == amount) {
                       System.out.println("Số lượng thay đổi phải khác ban đâu!Vui lòng nhập lại số lượng sản phẩm: ");
                   } else {
                       System.out.println("Số lượng không hợp lê! Vui lòng nhập lại số lượng: ");
                   }
                   amount = Integer.parseInt(scanner.nextLine());
               }

               product.setAmount(amount);
           }catch (Exception e){
               System.out.println("Số lượng không hợp lệ!");
           }
       }while (amount <= 0 || product.getAmount() == amount);
    }

    private static void checkValidNameProduct(Product product) {
        System.out.println("Nhập tên sản phẩm muốn sửa( bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
        String name = scanner.nextLine();

        while (name.length() < 2 || product.getName().equals(name)) {
            if (name.equals("0")) {
                selectMenuEditProduct(product.getId());
            } else if (product.getName().equals(name)) {
                System.out.println("Tên thay đổi không được trùng với tên ban đầu!");
            } else {
                System.out.println("Tên phải ít nhất 2 ký tự! Vui lòng nhập lại tên sản phẩm: ");
            }
            name = scanner.nextLine();
        }
        product.setName(name);
    }

    private static void checkValidTypeProduct(Product product) {
        System.out.println("Nhập loại sản phẩm muốn sửa(điện thoại/laptop): ");
        String type = scanner.nextLine().toLowerCase();
        while ((!type.equals("điện thoại") && !type.equals("laptop")) || product.getType().equals(type)) {
            if (type.equals("0")) {
                selectMenuEditProduct(product.getId());
            } else if (product.getType().equals(type)) {
                System.out.println("Loại sản phẩm phải khác với trước đi thay đổi!");
            } else {
                System.out.println("Loại sản phẩm k đúng! Vui lòng nhập lại: ");
            }
            type = scanner.nextLine();
        }
        product.setType(type);
    }


    public static void searchProduct(String role) {
        ArrayList<Product> searchProduct = new ArrayList<>();
        System.out.print("Nhập thông tin sản phẩm muốn tìm(nhấn exit để thoát và quay lại menu trước): ");
        String str = scanner.nextLine().toLowerCase();
        if (str.equals("exit")) {
            if (role.equals("ADMIN")) {
                AdminView.showProductFunctionAdmin();
            } else {
                ProductView.showProductFunction();
            }
        }

        ArrayList<Product> sortProductList = sortProduct();


        for (Product product : sortProductList) {
            if (product.toString().toLowerCase().contains(str)) {
                searchProduct.add(product);
            }
        }

        if (searchProduct.size() == 0) {
            System.out.println("Không tìm thấy tìm kiếm phù hợp");
            searchProduct(role);
        } else {
            showSearch(searchProduct, role);
        }
    }

    public static String addCharacters(int price) {
        String patternTienTe = ",###₫";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        return formatTienTe.format(price);
    }
    public static void showSearch(ArrayList<Product> list, String role) {
        if (isEmpty(list)) {
            System.out.println("----------Danh sách sản phẩm trống!----------");
        } else {
            System.out.println("-------------------------------------- DANH SÁCH TÌM KIẾM ---------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (Product product : list) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", product.getId(), product.getType(), product.getName(), product.getAmount(), addCharacters(product.getPrice()));
            }

            System.out.println("-------------------------------------------------------------------------------------------------");
            if (role.equals("ADMIN")) {
                AdminView.showProductFunctionAdmin();
            } else {
                ProductView.showProductFunction();
            }
        }
    }

    public static void removeProduct() {
        try {
            showProduct();
            System.out.print("Nhập id sản phẩm muốn xoá(Bấm 0 nếu muốn bỏ qua và quay lại menu trước đó): ");
            long id = Long.parseLong(scanner.nextLine());

            while (!isExistId(products, id)) {
                ProductView.backToEarlier(Long.toString(id), "showProductFunction", "ADMIN");
                System.out.print("Id không tồn tại! Vui lòng nhập đúng id của sản phẩm: ");
                id = Long.parseLong(scanner.nextLine());
            }
            System.out.println("Sản phẩm có id là: " + id + " đã được xoá khỏi danh sách!");
            int index = findIndexById(id);
            products.remove(index);
            CSVUtils.write(path, products);
            showProduct();
        } catch (Exception e) {
            System.out.println("Id không hợp lệ! Vui lòng nhập id hợp lệ!");
            removeProduct();
        }
    }

    public static void showProduct() {
        if (isEmpty(products)) {
            System.out.println("----------Danh sách sản phẩm trống!----------");
        } else {
            System.out.println("---------------------------------------- DANH SÁCH SẢN PHẨM ------------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (Product product : products) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", product.getId(), product.getType(), product.getName(), product.getAmount(), addCharacters(product.getPrice()));
            }

            System.out.println("------------------------------------------------------------------------------------------------------");
        }
    }

    public static void showLaptop() {
        ArrayList<Product> laptop = new ArrayList<>();

        for (Product product : products) {
            if (product.getType().toLowerCase(Locale.ROOT).equals("laptop")) {
                laptop.add(product);
            }
        }

        if (laptop.size() == 0) {
            System.out.println("----------Chưa có sản phẩm nào trong danh sách này!----------");
        } else {
            System.out.println("----------------------------------------- DANH SÁCH LAPTOP --------------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (Product product : laptop) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", product.getId(), product.getType(), product.getName(), product.getAmount(), addCharacters(product.getPrice()));
            }

            System.out.println("-------------------------------------------------------------------------------------------------------");
            ProductView.showProduct();
        }
    }

    public static void showSmartPhone() {
        ArrayList<Product> smartPhone = new ArrayList<>();

        for (Product product : products) {
            if (product.getType().toLowerCase(Locale.ROOT).equals("điện thoại")) {
                smartPhone.add(product);
            }
        }

        if (smartPhone.size() == 0) {
            System.out.println("----------Chưa có sản phẩm nào trong danh sách này!----------");
        } else {
            System.out.println("-------------------------------------- DANH SÁCH ĐIỆN THOẠI -----------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (Product product : smartPhone) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", product.getId(), product.getType(), product.getName(), product.getAmount(), addCharacters(product.getPrice()));
            }

            System.out.println("-----------------------------------------------------------------------------------------------------");
            ProductView.showProduct();
            ;
        }
    }

    public static void showFavouriteProduct() {
        if (favouriteProduct.size() == 0) {
            System.out.println("----------Chưa có sản phẩm nào trong danh sách này!----------");
            ProductView.showMenuProduct();
        } else {
            System.out.println("-------------------------------------- DANH SÁCH YÊU THÍCH ------------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (Product product : favouriteProduct) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", product.getId(), product.getType(), product.getName(), product.getAmount(), addCharacters(product.getPrice()));
            }

            System.out.println("-----------------------------------------------------------------------------------------------------");
            ProductView.showMenuProduct();
        }
    }

    public static void showCart() {
        if (cart.size() == 0) {
            System.out.println("----------Chưa có sản phẩm nào trong danh sách này!----------");
            ProductView.showMenuProduct();
        } else {
            System.out.println("-------------------------------------- DANH SÁCH GIỎ HÀNG ------------------------------------------");
            System.out.printf("*             %-10s %-20s %-25s %-17s %-12s *\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá");

            for (int i = 0; i < cart.size(); i++) {
                System.out.printf("*        %-16s %-15s %-32s %-12s %-14s *\n", cart.get(i).getId(), cart.get(i).getType(), cart.get(i).getName(), cart.get(i).getAmount(), addCharacters(favouriteProduct.get(i).getPrice()));
            }

            System.out.println("-----------------------------------------------------------------------------------------------------");
            ProductView.showMenuProduct();
        }
    }

    public static void add(ArrayList<Product> list, String typeList) {
        showProduct();
        System.out.println("Nhập id sản phẩm muốn thêm(bấm 0 để quay lại menu trước đó): ");
        long id = Long.parseLong(scanner.nextLine());
        if (isExistId(list, id)) {
            System.out.println("Sản phẩm đã tồn tại trong danh sách rồi!");
            ProductView.showProductFunction();
        } else {
            ProductView.backToEarlier(Long.toString(id), "showProductFunction", "USER");
            while (!isExistId(products, id)) {
                System.out.println("Không tồn tại sản phẩm có id là: " + id);
                System.out.println("Vui lòng nhập lại id: ");
                id = Long.parseLong(scanner.nextLine());
            }
        }
        int index = findIndexById(id);
        list.add(products.get(index));
        System.out.println("---------- Sản phẩm có id là " + id + " đã được thêm vào " + typeList + " ----------");
    }

    public static void addFavourite() {
        try {
            add(favouriteProduct, "danh sách yêu thích");
        } catch (Exception e) {
            System.out.println("Id nhập vào không hợp lệ!");
            addFavourite();
        }
    }

    public static void addCart() {
        try {
            add(cart, "giỏ hàng của bạn");
        } catch (Exception e) {
            System.out.println("Id nhập vào không hợp lệ!");
            addCart();
        }
    }

    public static ArrayList<Product> sortProduct() {
        ArrayList<Product> newProducts = new ArrayList<>(products);
        Collections.sort(newProducts);
        return newProducts;
    }
}