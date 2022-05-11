package com.company.view;

import com.company.menu.Menu;
import com.company.model.Order;
import com.company.model.OrderItem;
import com.company.model.Product;
import com.company.model.User;
import com.company.services.OrderItemServices;
import com.company.services.OrderServices;
import com.company.services.ProductManager;
import com.company.services.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserServices userServices = new UserServices();
    private static final OrderItemServices orderItemServices = new OrderItemServices();
    private static final OrderServices orderServices = new OrderServices();
    private static ArrayList<Product> products = ProductManager.getProduct();

    public static void mainMenuOrder() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ Chọn chức năng ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬     1. Tạo đơn đặt hàng               ✬");
        System.out.println("\t ✬     2. Danh sách đặt hàng             ✬");
        System.out.println("\t ✬     0. Quay lại                       ✬");
        System.out.println("\t ✬                                       ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.print(" ⭆ ");
    }

    public static void selectMainMenuOrder() {
        do {
            mainMenuOrder();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        creatOder();
                        break;
                    case 2:
                        OrderItemServices.showListOrder();
                        break;
                    case 0:
                        Menu.mainMenu();
                        Menu.selectManager();
                        break;
                    default:
                        System.out.println("Vui lòng nhập đúng chức năng!");
                        selectMainMenuOrder();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ!");
                selectMainMenuOrder();
            }
        } while (true);
    }

    public static void payBills(int totalMoney) {
        do {
            try {
            System.out.println("Bạn có chắc muốn thanh toán hoá đơn này không?");
            System.out.println("( y - thanh toán hoá đơn, l - thanh toán sau, n - huỷ hoá đơn");
            String choice = scanner.nextLine().toLowerCase();
        User user = userServices.getUserById(Menu.userId);
            switch (choice) {
                case "y":
                    System.out.println("Bạn đã thanh toán thành công hoá đơn:" + ProductManager.addCharacters(totalMoney));
                    changeProductsAfterPay(user.getName());
                    break;
                case "n":
                    orderItemServices.resetListOrderItem(user.getName());
                    orderServices.removeOrderByName(user.getName());
                    selectMainMenuOrder();
                    break;
                case "l":
                    selectMainMenuOrder();
                    break;
                default:
                    System.out.println("Vui lòng chọn đúng chức năng!");
                    break;
            }
            }catch (Exception e){
                System.out.println("Chức năng không hợp lệ!");
            }
        } while (true);

    }

    private static void changeProductsAfterPay(String name) {
        ProductManager.update();
        orderItemServices.update();
        orderItemServices.updateStatusOrderItem(name);
        selectMainMenuOrder();
    }



    private static void creatOder() {
        ProductManager.showProduct();
        System.out.println("------- BẤM 0 ĐỂ THOÁT VÀ QUAY LẠI MENU TRƯỚC ĐÓ -------");
        User user = userServices.getUserById(Menu.userId);

        long id = checkValidIdProduct(products);

        Product product = ProductManager.getProductById(id);
        int quantity = checkValidQuantity(product);
        OrderItem orderItem = null;

        long orderId = System.currentTimeMillis() / 1000;
        if (!OrderItemServices.checkNameOnList(user.getName())) {
            orderItem = new OrderItem(System.currentTimeMillis() / 100, user.getName(), product.getPrice()
                    , quantity, orderId, (int) product.getId(), product.getName(),
                    Math.round(product.getPrice() * quantity), OrderServices.formatDatetime(System.currentTimeMillis()));
            orderItemServices.add(orderItem);
        } else {
            List<OrderItem> list = orderItemServices.getListOrderItemByName(user.getName());
            orderItem = orderItemServices.getOrderItemByName(user.getName());
            if (isExistProduct(list, product)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                orderItem.setTotal((int) (orderItem.getQuantity() * orderItem.getPrice()));
                orderItem.setDate(OrderServices.formatDatetime(System.currentTimeMillis()));
            } else {
                orderItem = new OrderItem(System.currentTimeMillis() / 1000, user.getName(), product.getPrice()
                        , quantity, orderItem.getOrderId(), (int) product.getId(), product.getName(),
                        Math.round(product.getPrice() * quantity), OrderServices.formatDatetime(System.currentTimeMillis()));
                orderItemServices.add(orderItem);
            }
        }

        if (!OrderServices.checkExistNameOfOrder(user.getName())) {
            Order order = new Order(orderItem.getOrderId(), user.getName(), user.getPhone(), user.getAddress());
            orderServices.add(order);
        }
        product.setAmount(product.getAmount() - quantity);
        System.out.println("------------- BẠN ĐÃ TẠO ĐƠN THÀNH CÔNG ------------- ");
    }

    private static boolean isExistProduct(List<OrderItem> list, Product product) {
        for (OrderItem item : list) {
            if (item.getProductId() == product.getId()) {
                return true;
            }
        }
        return false;
    }

    private static int checkValidQuantity(Product product) {
        int quantity = 0;
        do {
            try {
                User user = userServices.getUserById(Menu.userId);
                List<OrderItem> list = orderItemServices.getListOrderItemByName(user.getName());
                if (isExistProduct(list,product)) {
                    System.out.println("Sản phẩm này đã có trong đơn của bạn! Vui lòng nhập số lượng muốn đặt thêm: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                } else {
                    System.out.println("Nhập số lượng muốn mua: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                while (quantity <= 0 || quantity > product.getAmount()) {
                    ProductView.backToEarlier(Integer.toString(quantity), "selectMainMenuOrder", "USER");
                    if (quantity <= 0) {
                        System.out.println("Số lượng phải lớn hơn 0! Vui lòng phải nhập lại");
                    } else {
                        System.out.println("Số lượng bạn muốn đặt đã vượt quá số lượng sản phẩm chúng tôi còn!");
                        System.out.println("Hiện tại sản phẩm bạn muốn đặt chỉ còn lại số lượng là: " + product.getAmount());
                        System.out.println("Nhập lại số lượng sản phẩm:");
                    }
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                return quantity;
            } catch (Exception e) {
                System.out.println("Số lượng nhập vào không hợp lệ!");
            }
        } while (true);
    }

    private static long checkValidIdProduct(ArrayList<Product> products) {
        do {
            try {
                System.out.println("Chọn id sản phẩm muốn mua: ");
                long id = Long.parseLong(scanner.nextLine());
                while (!ProductManager.isExistId(products, id)) {
                    ProductView.backToEarlier(Long.toString(id), "selectMainMenuOrder", "USER");
                    System.out.println("ID không tồn tại! Vui lòng nhập lại đúng id sản phẩm: ");
                    id = Long.parseLong(scanner.nextLine());
                }
                return id;
            } catch (Exception e) {
                System.out.println("Id nhập vào không hợp lệ!");
            }
        } while (true);
    }
}
