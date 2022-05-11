package com.company.services;

import com.company.menu.Menu;
import com.company.model.OrderItem;
import com.company.model.Product;
import com.company.model.User;
import com.company.utlis.CSVUtils;
import com.company.view.OrderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderItemServices implements IOrderItemServices {
    public static ArrayList<OrderItem> listOrderItem = new ArrayList<>();
    private static final UserServices userServices = new UserServices();
    private static final String fileName = "D:\\hoclaptrinh\\Module2\\case_study\\product_management\\data\\orderItem.csv";



    @Override
    public List<OrderItem> getOrderItems() {
        if (listOrderItem.size() == 0) {
            List<String> records = CSVUtils.read(fileName);

            for (String record : records) {
                listOrderItem.add(new OrderItem(record));
            }
            return listOrderItem;
        }
        return null;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        listOrderItem.add(newOrderItem);
        CSVUtils.write(fileName, listOrderItem);
    }

    @Override
    public void update() {
        CSVUtils.write(fileName, listOrderItem);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getOrderId() == id) {
                return orderItem;
            }
        }
        return null;
    }

    public OrderItem getOrderItemByName(String name) {
        for (OrderItem orderItem : listOrderItem) {
            if (Objects.equals(orderItem.getName(), name)) {
                return orderItem;
            }
        }
        return null;
    }

    public List<OrderItem> getListOrderItemByName(String name) {
        List<OrderItem> list = new ArrayList<>();
        for (OrderItem orderItem : listOrderItem) {
            if (Objects.equals(orderItem.getName(), name)) {
                list.add(orderItem);
            }
        }
        return list;
    }

    public boolean isExistProduct(Product product) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getProductId() == product.getId()) {
                return true;
            }
        }
        return false;
    }

    public void resetListOrderItem(String name) {
        listOrderItem.removeIf(orderItem -> (orderItem.getName().equals(name) && orderItem.getStatus().equals("unpaid")));
        CSVUtils.write(fileName, listOrderItem);
    }

    public void updateStatusOrderItem(String name) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getName().equals(name)) {
                orderItem.setStatus("paid");
            }
        }
        CSVUtils.write(fileName, listOrderItem);
    }


    public static void showListOrder() {
        User user = userServices.getUserById(Menu.userId);
        if (!checkNameOnList(user.getName())) {
            System.out.println("----------Danh sách sản đặt hàng trống!----------");
        } else {
            if (countUserPaidProducts(user.getName()) == 0) {
                System.out.println("----------Danh sách sản đặt hàng trống!----------");
            } else {
                int totalMoney = 0;
                System.out.println("------------------------------------------------- DANH SÁCH ĐẶT HÀNG CỦA BẠN -------------------------------------------------");
                System.out.printf("*             %-15s %-30s %-12s %-15s %-20s %-16s *\n", "ID", "Tên sản phẩm", "Giá", "Số lượng", "Tổng tiền", "Thời gian mua");

                for (OrderItem orderItem : listOrderItem) {
                    if (user.getName().equals(orderItem.getName()) && orderItem.getStatus().equals("unpaid")) {
                        totalMoney += orderItem.getTotal();
                        System.out.printf("*        %-16s %-32s %-15s  %-13s %-20s %-16s *\n", orderItem.getId(),
                                orderItem.getProductName(), ProductManager.addCharacters((int) orderItem.getPrice()),
                                orderItem.getQuantity(), ProductManager.addCharacters((int) orderItem.getTotal()),
                                orderItem.getDate());
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------TỔNG TIỀN: " + ProductManager.addCharacters(totalMoney) + " --------------------");
                OrderView.payBills(totalMoney);
            }
        }
    }

    public static boolean checkNameOnList(String name) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int countUserPaidProducts(String name) {
        int count = 0;
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getName().equals(name) && orderItem.getStatus().equals("unpaid")) {
                count++;
            }
        }
        return count;
    }

    public static boolean isExistNameOfOrderItemList(String name) {
        for(OrderItem orderItem : listOrderItem){
            if(orderItem.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private static boolean isEmpty(ArrayList<OrderItem> listOrderItem) {
        return listOrderItem.size() == 0;
    }
}
