package com.company.services;

import com.company.model.User;
import com.company.utlis.CSVUtils;
import com.company.view.AdminView;
import com.company.view.ProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserServices implements IUserServices {
    private static ArrayList<User> listUser = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static UserServices userServices = new UserServices();
    private static final String fileName = "D:\\hoclaptrinh\\Module2\\case_study\\product_management\\data\\user.csv";

    @Override
    public ArrayList<User> getUsers() {
        if(listUser.size() == 0){
            List<String> records = CSVUtils.read(fileName);

            for (String record : records){
                listUser.add(new User(record));
            }
            return listUser;
        }
        return null;
    }

    @Override
    public User loginAdmin(String username, String password) {
        getUsers();
        for (User user : listUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User newUser) {
        listUser.add(newUser);
        CSVUtils.write(fileName,listUser);
    }

    @Override
    public void update(String value, String options, int id) {
        User user = getUserById(id);

        switch (options) {
            case "name":
                user.setName(value);
                break;
            case "phone":
                user.setPhone(value);
                break;
            case "email":
                user.setEmail(value);
                break;
            case "address":
                user.setAddress(value);
                break;
        }
        CSVUtils.write(fileName,listUser);
    }

    @Override
    public boolean exist(int id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean checkEmailExist(String email) {
        for (User user : listUser) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        for (User user : listUser) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        for (User user : listUser) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public User getUserById(int id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        for (User user : listUser) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public void update(){
        CSVUtils.write(fileName,listUser);
    }

    public  void showListUser() {
        System.out.println("-------------------------------------------------------- DANH SÁCH NGƯỜI DÙNG --------------------------------------------------------- ");
        System.out.printf("*           %-8s %-15s %-15s %-20s %-17s %-15s %-10s %-11s *\n", "ID","Tên đăng nhập", "Mật khẩu", "Tên", "SĐT","Email","Địa chỉ","Trạng thái tài khoản");

        for (int i = 0; i < listUser.size(); i++) {
            System.out.printf("*        %-15s %-12s %-10s %-22s %-14s %-23s %-15s %-13s *\n", listUser.get(i).getId(),
                    listUser.get(i).getUsername(), listUser.get(i).getPassword(), listUser.get(i).getName(),
                    listUser.get(i).getPhone(),listUser.get(i).getEmail(),listUser.get(i).getAddress(),listUser.get(i).getStatus());
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void blockUser() {
        try {
            userServices.showListUser();
            System.out.println("------ BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ ------ ");
            System.out.println("Nhập id tài khoản muốn chặn: ");
            int id = Integer.parseInt(scanner.nextLine());

            while (!userServices.exist(id)){
                ProductView.backToEarlier(Integer.toString(id),"showUserMenu","ADMIN");
                System.out.println("Không tồn tại người dùng có id là:" + id);
                System.out.println("Vui lòng nhập lại id: ");
                id = Integer.parseInt(scanner.nextLine());
            }

            User user = userServices.getUserById(id);
            if(user.getStatus().equals("unlock")){
                user.setStatus("block");
                System.out.println("Tài khoản có id " + id + " đã bị khoá!");
            }else {
                System.out.println("Tài khoá này đã bị khoá từ trước");
            }
            CSVUtils.write(fileName,listUser);
            AdminView.showUserMenuAdmin();
        }catch (Exception e){
            System.out.println("Vui lòng nhập id hợp lệ!!");
            blockUser();
        }
    }

    public static void restoreUser() {
        try{
            userServices.showListUser();
            System.out.println("------ BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ ------ ");
            System.out.println("Nhập id tài khoản muốn mở chặn: ");
            int id = Integer.parseInt(scanner.nextLine());

            while (!userServices.exist(id)){
                ProductView.backToEarlier(Integer.toString(id),"showUserMenu","ADMIN");
                System.out.println("Không tồn tại người dùng có id là:" + id);
                System.out.println("Vui lòng nhập lại id: ");
                id = Integer.parseInt(scanner.nextLine());
            }

            User user = userServices.getUserById(id);
            if(user.getStatus().equals("block")){
                user.setStatus("unlock");
                System.out.println("Tài khoản có id " + id + " đã được bỏ chặn!");
            }else {
                System.out.println("Tài khoá này không bị chặn trước đó!");
            }
            CSVUtils.write(fileName,listUser);
            AdminView.showUserMenuAdmin();
        }catch (Exception e){
            System.out.println("Vui lòng nhập id hợp lệ!!");
            restoreUser();
        }
    }
}
