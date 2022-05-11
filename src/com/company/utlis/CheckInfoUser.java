package com.company.utlis;

import com.company.view.ProductView;

import java.util.Scanner;

public class CheckInfoUser {
    private static final Scanner scanner = new Scanner(System.in);

    public static String checkValidUserName(){
        String userName = scanner.nextLine();
        ProductView.backToEarlier(userName,"showUserMenu","USER");
        while (!ValidateUtils.isUserNameValid(userName)){
            System.out.println("Tên đăng nhập không hợp lệ! Tên đăng nhập phải gồm 6-32 ký tự!");
            System.out.print("Vui lòng nhập lại tên đăng nhập: ");
            userName = scanner.nextLine();
        }
        return userName;
    }

    public static String checkValidPassword(){
        String password = scanner.nextLine();;
        ProductView.backToEarlier(password,"showUserMenu","USER");
        while (!ValidateUtils.isPasswordValid(password)){
            System.out.println("Mật khẩu không hợp lệ! Mật khẩu phải từ 8 kí tự trở lên và không được có các kí tự đặc biệt!");
            System.out.println("Vui lòng nhập lại mật khẩu: ");
            password = scanner.nextLine();
        }
        return password;
    }

    public static String checkValidName(){
        String name = scanner.nextLine();;
        ProductView.backToEarlier(name,"showUserMenu","USER");
        while (!ValidateUtils.isNameValid(name)){
            System.out.print("Tên không hợp lệ! Vui lòng nhập lại:");
            name = scanner.nextLine();
        }
        return name;
    }


    public static String checkValidPhone(){
        String phone = scanner.nextLine();
        ProductView.backToEarlier(phone,"showUserMenu","USER");
        while (!ValidateUtils.isPhoneValid(phone)){
            System.out.println("Số điện không hợp lệ. Số điện thoại phải có dạng như này( VD: 0356974587)");
            System.out.println("Vui lòng nhập lại số điện thoại: ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    public static String checkValidEmail(){
        String email = scanner.nextLine();;
        ProductView.backToEarlier(email,"showUserMenu","USER");
        while (!ValidateUtils.isEmailValid(email)){
            System.out.println("Email không hợp lệ! Vui lòng nhập lại email tương tự như(VD: thanhthang@gmail.com): ");
            email = scanner.nextLine();
        }
        return email;
    }

    public static String checkValidAddress(){
        String address = scanner.nextLine();;
        while (address.length() == 0){
            System.out.println("Địa chỉ không được để trống! Vui lòng nhập lại địa chỉ của bạn: ");
            address = scanner.nextLine();
        }
        return address;
    }
}
