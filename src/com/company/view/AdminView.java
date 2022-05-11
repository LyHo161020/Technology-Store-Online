package com.company.view;

import com.company.menu.Menu;
import com.company.model.User;
import com.company.services.OrderItemServices;
import com.company.services.OrderServices;
import com.company.services.ProductManager;
import com.company.services.UserServices;
import java.util.Scanner;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserServices userServices = new UserServices();
    private static final OrderItemServices orderItemServices = new OrderItemServices();
    private static final OrderServices orderServices = new OrderServices();


    public static void showUserMenuAdmin() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ Quản lý người dùng ✬ ✬ ✬ ✬ ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Chặn người dùng                ✬");
        System.out.println("\t ✬     2. Khôi phục người dùng           ✬");
        System.out.println("\t ✬     3. Danh sách người dùng           ✬");
        System.out.println("\t ✬     0. Quay lại                       ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
        selectUserMenuAdmin();
    }

    public static void mainMenuAdmin() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬MAIN MENU✬ ✬ ✬ ✬ ✬ ✬ ✬✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Quản lý người dùng             ✬");
        System.out.println("\t ✬     2. Quản lý sản phẩm               ✬");
        System.out.println("\t ✬     3. Quản lý đơn đặt hàng           ✬");
        System.out.println("\t ✬     0. Đăng xuất                      ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
    }


    public static void showOrderMenuAdmin() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ Quản lý người dùng ✬ ✬ ✬ ✬ ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Danh sách Order                ✬");
        System.out.println("\t ✬     2. Doanh thu                      ✬");
        System.out.println("\t ✬     0. Quay lại                       ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
    }


    public static void showMenuProductAdmin() {
        System.out.println("**************************** MENU ****************************");
        System.out.println("**                  1. Danh sách sản phẩm                   **");
        System.out.println("**                  2. Tuỳ chỉnh sản phẩm                   **");
        System.out.println("**                  0. Quay lại                             **");
        System.out.println("**************************************************************");
    }

    public static void showCustomListAdmin() {
        System.out.println("------------Product-Manager------------");
        System.out.println("          1. Thêm sản phẩm.            ");
        System.out.println("          2. Sửa sản phẩm.             ");
        System.out.println("          3. Xoá sản phẩm.             ");
        System.out.println("          4. Tìm kiếm sản phẩm.        ");
        System.out.println("          0. Quay lại.                 ");
        System.out.println("---------------------------------------");
    }

    public static void reLoginAdmin() {
       do {
           try {
               userServices.getUsers();
               orderItemServices.getOrderItems();
               orderServices.getOrders();
               System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ CHỌN ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
               System.out.println("✿                                              ✿");
               System.out.println("✿                 1.Đăng ký                    ✿");
               System.out.println("✿                 2.Đăng nhập                  ✿");
               System.out.println("✿                 3.Liên hệ hỗ trợ             ✿");
               System.out.println("✿                 0.Thoát                      ✿");
               System.out.println("✿                                              ✿");
               System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
               System.out.print(" ⭆ ");
               int choice = Integer.parseInt(scanner.nextLine());
               if (choice == 1) {
                   UserView.addUser();
               } else if (choice == 2) {
                   Menu.loginAdmin();
               } else if(choice == 3){
                   AdminView.supportCenter();
               } else if (choice == 0) {
                   System.exit(0);
               } else {
                   System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại ");
                   reLoginAdmin();
               }
           }catch (Exception e){
               System.out.println("Lựa chọn không hợp lệ!");
           }
       }while (true);
    }

    public static void selectUserMenuAdmin() {
        do {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserServices.blockUser();
                        break;
                    case 2:
                        UserServices.restoreUser();
                        break;
                    case 3:
                        userServices.showListUser();
                        showUserMenuAdmin();
                        break;
                    case 0:
                        mainMenuAdmin();
                        selectManagerAdmin();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        showUserMenuAdmin();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
                showUserMenuAdmin();;
            }
        } while (true);
    }

    public static void initAdmin() {
        int choice = -1;

        do {
            AdminView.showMenuProductAdmin();
            ProductManager.getProduct();
            try {
                System.out.print("Nhập lựa chọn: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        AdminView.showProductAdmin();
                        break;
                    case 2:
                        AdminView.showProductFunctionAdmin();
                        break;
                    case 0:
                        mainMenuAdmin();
                        selectManagerAdmin();
                        break;
                    default:
                        System.out.println("---------- Chọn lựa chọn phù hợp! ----------");
                        initAdmin();
                        break;
                }
            } catch (Exception e) {
                System.out.println("---------- Chọn lại lựa chọn phù hợp! ----------");
            }
        } while (true);
    }

    public static void selectManagerAdmin() {
        do {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        AdminView.showUserMenuAdmin();
                        break;
                    case 2:
                        AdminView.initAdmin();
                        break;
                    case 3:
                        showOrderMenuAdmin();
                        selectOrderMenuAdmin();
                        break;
                    case 0:
                        AdminView.reLoginAdmin();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        mainMenuAdmin();
                        selectManagerAdmin();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại!");
                mainMenuAdmin();
                selectManagerAdmin();
            }
        } while (true);
    }

    public static void showProductFunctionAdmin() {
        int choice;
        do {
            AdminView.showCustomListAdmin();
            try {
                System.out.println("Nhập lựa chọn: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ProductManager.addProduct();
                        break;
                    case 2:
                        ProductManager.editProduct();
                        break;
                    case 3:
                        ProductManager.removeProduct();
                        break;
                    case 4:
                        ProductManager.searchProduct("ADMIN");
                        break;
                    case 0:
                        AdminView.initAdmin();
                        break;
                    default:
                        System.out.println("------ Lựa chọn không phù hợp! ------");
                        showProductFunctionAdmin();
                        break;
                }
            } catch (Exception e) {
                System.out.println("------ Lựa chọn không phù hợp! ------");
                showProductFunctionAdmin();
            }
        } while (true);
    }

    public static void showProductAdmin() {
        do {
            ProductView.showSelectionList();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ProductManager.showProduct();
                        break;
                    case 2:
                        ProductManager.showSmartPhone();
                        break;
                    case 3:
                        ProductManager.showLaptop();
                        break;
                    case 0:
                        AdminView.initAdmin();
                        break;
                    default:
                        System.out.println("----------- Nhập lại lựa chọn phù hợp! -----------");
                        break;
                }
            } catch (Exception e) {
                System.out.println("----------- Nhập lại lựa chọn phù hợp! -----------");
            }
        } while (true);
    }

    public static void selectOrderMenuAdmin(){
        do {
            showOrderMenuAdmin();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:
                        OrderServices.showListOrder();
                        break;
                    case 2:
                        showTurnover();
                        break;
                    case 0:
                        mainMenuAdmin();
                        selectManagerAdmin();
                        break;
                    default:
                        System.out.println("Chọn chức năng phù hợp!");
                        break;
                }

            }catch (Exception e){
                System.out.println("Chọn chức năng phù hợp!");
            }
        }while (true);
    }

    private static void showTurnover() {

    }

    public static void supportCenter() {
        do {
            try {
                menuSupportCenter();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:
                        changePassword();
                        break;
                    case 2:
                        openAccountKey();
                        break;
                    case 0:
                        reLoginAdmin();
                    default:
                        System.out.println("Chọn lựa chọn phù hợp!");
                        break;
                }
            }catch (Exception e){
                System.out.println("Chọn lựa chọn phù hợp!");
            }
        }while (true);
    }

    private static void openAccountKey() {
        User user = enterInfoUserSupport();
        if(user.getStatus().equals("unlock")){
            System.out.println("Tài khoản của bạn hiện không bị khoá!");
        }else {
            user.setStatus("unlock");
            System.out.println("Tài khoản của bạn đã được mở khoá thành công! Bây giờ bạn có thể đăng nhập với tài khoản của bạn!");
        }
    }

    private static void changePassword() {
        User user = enterInfoUserSupport();
        user.setPassword("12345678");
        System.out.println("Mật khẩu đã được được đặt lại mặt định! Mật khẩu của bạn là: " + user.getPassword());
        userServices.update();
    }

    private static User enterInfoUserSupport() {
        System.out.println("--------- BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚc ĐÓ ---------");
        System.out.println("Vui lòng nhập tên đăng nhâp của bạn: ");
        String userName = scanner.nextLine();

        while (userServices.getUserByUserName(userName) == null){
            ProductView.backToEarlier(userName,"supportCenter","USER");
            System.out.println("Tên đăng nhập không tồn tại!");
            System.out.println("Vui lòng nhập lại tên đăng nhập: ");
            userName = scanner.nextLine();
        }
        User user = userServices.getUserByUserName(userName);

        System.out.println("Vui lòng xác nhận lại Email tài khoản: ");
        String email = scanner.nextLine();

        while (!user.getEmail().equals(email)){
            ProductView.backToEarlier(email,"supportCenter","USER");
            System.out.println("Email bạn nhập không đúng! Vui lòng nhập lại email:  ");
            email = scanner.nextLine();
        }
        return user;
    }

    private static void menuSupportCenter() {
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ CHỌN ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.println("✿                                              ✿");
        System.out.println("✿                 1.Quên mật khẩu              ✿");
        System.out.println("✿                 2.Mở khoá tài khoản          ✿");
        System.out.println("✿                 0.Quay lại                   ✿");
        System.out.println("✿                                              ✿");
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.print(" ⭆ ");
    }
}
