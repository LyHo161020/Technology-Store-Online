package com.company.menu;

import com.company.model.Role;
import com.company.model.User;
import com.company.services.ProductManager;
import com.company.services.UserServices;
import com.company.view.*;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static int userId;

    public static void init() {
        int choice = -1;
        ProductView.showMenuProduct();
        ProductManager.getProduct();
        do {
            try {
                System.out.print("Nhập lựa chọn: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ProductView.showProduct();
                        break;
                    case 2:
                        ProductManager.showFavouriteProduct();
                        break;
                    case 3:
                        ProductManager.showCart();
                        break;
                    case 4:
                        ProductView.showProductFunction();
                        break;
                    case 0:
                        Menu.mainMenu();
                        selectManager();
                        break;
                    default:
                        System.out.println("---------- Nhập lựa chọn phù hợp! ----------");
                        Menu.init();
                        break;
                }
            } catch (Exception e) {
                System.out.println("---------- Nhập lại lựa chọn phù hợp! ----------");
                Menu.init();
            }
        } while (true);
    }

    public static void checkLoginEmailAdmin(String emailAdmin) {
        String email = scanner.nextLine();
        while (!email.equals(emailAdmin)) {
            ProductView.backToEarlier(email, "reLogin", "ADMIN");
            System.out.println("Email bạn nhập không đúng!");
            System.out.println("( BẤM 0 ĐỂ QUAY LẠI TRANG CHỦ ĐỂ LIÊN HỆ HỖ TRỢ NẾU BẠN " +
                    "QUÊN TÀI KHOẢN EMAIL HOẶC MUỐN ĐĂNG NHẬP BẰNG TÀI KHOẢN KHÁC)");
            System.out.println("Nhập lại email của bạn: ");
            email = scanner.nextLine();
        }
        System.out.println("Bạn đã đăng nhập thành công \uD83C\uDF8A \n");
        System.out.println("CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI CỬA HÀNG CỦA CHÚNG TÔI\n");
        AdminView.mainMenuAdmin();
        AdminView.selectManagerAdmin();
    }

    public static void loginAdmin() {
        UserServices userServices = new UserServices();
        System.out.println("******************** ĐĂNG NHẬP HỆ THỐNG ********************");
        System.out.print("⭆ Username: ");
        String userName = scanner.nextLine();
        System.out.print("⭆ Password: ");
        String password = scanner.nextLine();
        if (userServices.loginAdmin(userName, password) != null) {
            User user = userServices.getUserByUserName(userName);
            if (user.getRole() == Role.ADMIN) {
                System.out.println("------ BẤM 0 NẾU BẠN MUỐN ĐĂNG NHẬP LẠI BẰNG TÀI KHOẢN KHÁC ------");
                System.out.println("BẠN ĐANG ĐĂNG NHẬP BẰNG TÀI KHOẢN ADMIN!");
                System.out.println("Vui lòng xác minh email của bạn: ");
                checkLoginEmailAdmin(user.getEmail());
            } else {
                if (user.getStatus().equals("unlock")) {
                    userId = user.getId();
                    System.out.println("Bạn đã đăng nhập thành công \uD83C\uDF8A \n");
                    System.out.println("CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI CỬA HÀNG CỦA CHÚNG TÔI\n");
                    mainMenu();
                    selectManager();
                } else {
                    System.out.println("Tài khoản của bạn đã bị khoá! Vui lòng liên hệ trung tâm hỗ trợ để mở khoá tài khoản của ban!");
                    AdminView.reLoginAdmin();
                }
            }
        } else {
            System.out.println("Tài khoản không hợp lệ!");
            AdminView.reLoginAdmin();
        }
    }

    public static void mainMenu() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬MAIN MENU✬ ✬ ✬ ✬ ✬ ✬ ✬✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Người dùng                     ✬");
        System.out.println("\t ✬     2. Sản phẩm                       ✬");
        System.out.println("\t ✬     3. Đơn đặt hàng                   ✬");
        System.out.println("\t ✬     0. Đăng xuất                      ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
//        selectManager();
    }

    public static void menuEditProduct() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬MENU EDIT✬ ✬ ✬ ✬ ✬ ✬ ✬✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Loại sản phẩm                  ✬");
        System.out.println("\t ✬     2. Tên sản phẩm                   ✬");
        System.out.println("\t ✬     3. Số lượng sản phẩm              ✬");
        System.out.println("\t ✬     4. Giá sản phẩm                   ✬");
        System.out.println("\t ✬     0. Quay lại                       ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ Chọn thông tin cần sửa:");
    }

    public static void selectManager() {
        do {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserView.showUserMenu();
                        break;
                    case 2:
                        init();
                        break;
                    case 3:
                        OrderView.selectMainMenuOrder();
                        break;
                    case 0:
                        AdminView.reLoginAdmin();
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        mainMenu();
                        selectManager();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
                mainMenu();
                selectManager();
            }
        } while (true);
    }
}
