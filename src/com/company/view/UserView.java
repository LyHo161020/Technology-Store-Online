package com.company.view;

import com.company.menu.Menu;
import com.company.model.User;
import com.company.services.UserServices;
import com.company.utlis.CheckInfoUser;
import com.company.utlis.UserUtils;
import com.company.utlis.ValidateUtils;

import java.util.Arrays;
import java.util.Scanner;

public class UserView {
    private static final UserServices userServices = new UserServices();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String fileName = "D:\\hoclaptrinh\\Module2\\case_study\\product_management\\data\\user.csv";
//    private static ArrayList<Product> users = new ArrayList<>();


    public static void showUserMenu() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ Quản lý người dùng ✬ ✬ ✬ ✬ ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Thông tin người dùng           ✬");
        System.out.println("\t ✬     2. Sửa thông tin người dùng       ✬");
        System.out.println("\t ✬     0. Quay lại                       ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
        selectUserMenu();
    }

    public static void selectUserMenu() {
        do {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
//                        addUser();
                        showInfoUser();
                        showUserMenu();
                        break;
                    case 2:
                        showMenuEditInfoUser();
                        break;
//                    case 3:
//                        UserServices.blockUser();
//                        break;
//                    case 4:
//                        UserServices.restoreUser();
//                        break;
//                    case 5:
//                        userServices.showListUser();
//                        UserView.showUserMenu();
//                        break;
                    case 0:
                        Menu.mainMenu();
                        Menu.selectManager();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        showUserMenu();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
                UserView.showUserMenu();
            }
        } while (true);
    }

    private static void showInfoUser() {
        User user = userServices.getUserById(Menu.userId);
        System.out.println("------------------------------------------------THÔNG TIN NGƯỜI DÙNG ----------------------------------------------- ");
        System.out.printf("*           %-8s %-15s %-15s %-15s %-15s %-18s %-10s *\n", "ID","Tên đăng nhập", "Mật khẩu", "Tên", "SĐT","Email","Địa chỉ");
        System.out.printf("*        %-13s %-13s %-13s %-14s %-14s %-23s %-9s *\n", user.getId(),
                    user.getUsername(), user.getPassword(), user.getName(),
                    user.getPhone(),user.getEmail(),user.getAddress());

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
//        UserView.showUserMenu();
    }


//    public static void restoreUser() {
//        System.out.println("Nhập id tài khoản muốn mở chặn: ");
//        int id = Integer.parseInt(scanner.nextLine());
//        User user = userServices.getUserById(id);
//        if(user.getStatus().equals("block")){
//            user.setStatus("unlock");
//            System.out.println("Tài khoản có id " + id + " đã được bỏ chặn!");
//        }else {
//            System.out.println("Tài khoá này không bị chặn trước đó!");
//        }
//        showUserMenu();
//    }

//    public static void blockUser() {
//        System.out.println("Nhập id tài khoản muốn chặn: ");
//        int id = Integer.parseInt(scanner.nextLine());
//        User user = userServices.getUserById(id);
//        if(user.getStatus().equals("unlock")){
//            user.setStatus("block");
//            System.out.println("Tài khoản có id " + id + " đã bị khoá!");
//        }else {
//            System.out.println("Tài khoá này đã bị khoá từ trước");
//        }
//        CSVUtils.write(fileName,u);
//        showUserMenu();
//    }
public static String checkValidUserName(String value){
    String userName = value;
    ProductView.backToEarlier(userName,"showUserMenu","USER");
    while (!ValidateUtils.isUserNameValid(userName)){
        System.out.println("Tên đăng nhập không hợp lệ! Tên đăng nhập phải gồm 6-32 ký tự!");
        System.out.print("Vui lòng nhập lại tên đăng nhập: ");
        userName = scanner.nextLine();
    }
    return userName;
}

    public static String checkValidPassword(String value){
        String password = value;
        ProductView.backToEarlier(password,"showUserMenu","USER");
        while (!ValidateUtils.isPasswordValid(password)){
            System.out.println("Mật khẩu không hợp lệ! Mật khẩu phải từ 8 kí tự trở lên và không được có các kí tự đặc biệt!");
            System.out.println("Vui lòng nhập lại mật khẩu: ");
            password = scanner.nextLine();
        }
        return password;
    }

    public static String checkValidName(String value){
        String name = value;
        ProductView.backToEarlier(name,"showUserMenu","USER");
        while (!ValidateUtils.isNameValid(name)){
            System.out.print("Tên không hợp lệ! Vui lòng nhập lại:");
            name = scanner.nextLine();
        }
        return name;
    }


    public static String checkValidPhone(String value){
        String phone = value;
        ProductView.backToEarlier(phone,"showUserMenu","USER");
        while (!ValidateUtils.isPhoneValid(phone)){
            System.out.println("Số điện không hợp lệ. Số điện thoại phải có dạng như này( VD: 0356974587)");
            System.out.println("Vui lòng nhập lại số điện thoại: ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    public static String checkValidEmail(String value){
        String email = value;
        ProductView.backToEarlier(email,"showUserMenu","USER");
        while (!ValidateUtils.isEmailValid(email)){
            System.out.println("Email không hợp lệ! Vui lòng nhập lại email tương tự như(VD: thanhthang@gmail.com): ");
            email = scanner.nextLine();
        }
        return email;
    }

    public static String checkValidAddress(String value){
        String address = value;
        while (address.length() == 0){
            System.out.println("Địa chỉ không được để trống! Vui lòng nhập lại địa chỉ của bạn: ");
            address = scanner.nextLine();
        }
        return address;
    }
    public static void addUser() {
        try {
            User user = enterInfoUser();
            userServices.addUser(user);
            AdminView.reLoginAdmin();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static void editUser(int id) {
        try{
            int choice = Integer.parseInt(scanner.nextLine());

            boolean isValidOption = Arrays.asList(UserUtils.VALID_OPTIONS).contains(choice);
            if(!isValidOption) System.out.println("Chọn lựa chọn phù hợp!");

            System.out.print("Nhập " + UserUtils.CHOICE_NAMES[choice - 1] +" mà bạn muốn sửa đổi: ");
            String value = scanner.nextLine();

            value = validateInputValue(value, UserUtils.OPTIONS_UPDATE[choice - 1]);
            userServices.update(value, UserUtils.OPTIONS_UPDATE[choice - 1], id);

            System.out.println("Bạn đã đổi " + UserUtils.CHOICE_NAMES[choice - 1] + " thành công!\uD83C\uDF89");
            showUserMenu();

        }catch (Exception e){
            System.out.println("Nhập lựa chọn phù hợp!");
        }
    }

    private static String validateInputValue(String value, String choice) {
        String validatedValue = "";
        switch (choice) {
            case "name":
                validatedValue = checkValidName(value);
                break;
            case "phone":
                validatedValue = checkValidPhone(value);
                break;
            case "email":
                validatedValue = checkValidEmail(value);
                break;
            case "address":
                validatedValue = checkValidAddress(value);
                break;
            default:
                break;
        }
        return validatedValue;
    }

    public static void showMenuEditInfoUser() {
        try {
            System.out.println("\t ✬ ✬ ✬ ✬  Chọn thông tin cần sửa ✬ ✬ ✬ ✬");
            System.out.println("\t ✬                                       ✬");
            System.out.println("\t ✬     1. Tên                            ✬");
            System.out.println("\t ✬     2. Số điện thoại                  ✬");
            System.out.println("\t ✬     3. Email                          ✬");
            System.out.println("\t ✬     4. Địa chỉ                        ✬");
            System.out.println("\t ✬     0. Quay lại                       ✬");
            System.out.println("\t ✬                                       ✬");
            System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
            System.out.print(" ⭆ ");
            System.out.println("------ BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ ------");
            do { editUser(Menu.userId);} while(true);
        }catch (Exception e){
            System.out.println("Id nhập vào không hợp lệ!");
            showMenuEditInfoUser();
        }
    }


    public static User enterInfoUser() {

        int id = (int) (System.currentTimeMillis() / 1000);
        System.out.println("------ BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ ------");
        System.out.print("Nhập tên đăng nhập(6-32 ký tự):");

        String userName = CheckInfoUser.checkValidUserName();

        System.out.println("Nhập mật khẩu: ");
        ProductView.backToEarlier(userName,"showUserMenu","USER");
        String password = CheckInfoUser.checkValidPassword();

        System.out.println("Nhập tên người dùng: ");
        String name = CheckInfoUser.checkValidName();
        ProductView.backToEarlier(userName,"showUserMenu","USER");

        System.out.println("Nhập SĐT: ");
        String phone = CheckInfoUser.checkValidPhone();

        System.out.println("Nhập email(VD: thanhthang@gmail.com):");
        String email = CheckInfoUser.checkValidEmail();

        System.out.println("Nhập địa chỉ: ");
        String address = CheckInfoUser.checkValidAddress();

        System.out.println("--------- TÀI KHOẢN ĐÃ ĐƯỢC THÊM ---------");
        User user = new User(id, userName, password, name, phone, email, address,"USER");
        return user;
    }

}
