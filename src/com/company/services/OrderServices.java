package com.company.services;

import com.company.model.Order;
import com.company.model.OrderItem;
import com.company.utlis.CSVUtils;

import java.util.*;

public class OrderServices implements IOrderServices {
    public static ArrayList<Order> listOrder = new ArrayList<>();
    private static final OrderItemServices orderItemServices = new OrderItemServices();
    public static final String fileName = "D:\\hoclaptrinh\\Module2\\case_study\\product_management\\data\\order.csv";

    @Override
    public List<Order> getOrders() {
        if (listOrder.size() == 0) {
            List<String> records = CSVUtils.read(fileName);

            for (String record : records) {
                listOrder.add(new Order(record));
            }
            return listOrder;
        }
        return null;
    }

    @Override
    public void add(Order newOrder) {
        listOrder.add(newOrder);
        CSVUtils.write(fileName, listOrder);
    }

    @Override
    public void update() {
        CSVUtils.write(fileName, listOrder);
    }

    @Override
    public Order getOrderById(int id) {
        for (Order order : listOrder) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return getOrderById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Order order : listOrder) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean checkDuplicateId(int id) {
        for (Order order : listOrder) {
            if (order.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Order order) {
        listOrder.remove(order);
        CSVUtils.write(fileName, listOrder);
    }

    public static String formatDatetime(long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH) + 1;
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mMinus = calendar.get(Calendar.MINUTE);
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);

        String dateTime = Integer.toString(mHour);
        if (Integer.toString(mMinus).length() == 1) {
            dateTime += ":0" + mMinus;
        } else {
            dateTime += ":" + mMinus;
        }

        if (Integer.toString(mDay).length() == 1) {
            dateTime += " 0" + mDay;
        } else {
            dateTime += " " + mDay;
        }

        if (Integer.toString(mMonth).length() == 1) {
            dateTime += "/0" + mMonth;
        } else {
            dateTime += "/" + mMonth;
        }

        return dateTime + "/" + mYear;
    }

    public static void showListOrder() {
        if (isEmpty(listOrder)) {
            System.out.println("----------Danh sách sản đặt hàng trống!----------");
        } else {
            System.out.println("------------------------------------------------------------------- LỊCH SỬ ĐẶT HÀNG -------------------------------------------------------------------");
            for (Order order : listOrder) {
                int totalMoney = 0;
                System.out.printf("*   %-25s %-1s %-15s %-1s %-15s %-1s %-15s %-1s %-38s  * \n", "Thông tin người dùng", "ID:"
                        , order.getId(), "Tên:", order.getName(),
                        "Số điện thoại:", order.getPhone(), "Địa chỉ:", order.getAddress());
                System.out.printf("*%-150s*\n", "");
                System.out.printf("*            %-15s %-30s %-12s %-17s %-20s %-18s %-10s *\n", "ID",
                        "Tên sản phẩm", "Giá", "Số lượng", "Tổng tiền", "Thời gian đặt", "Trạng thái đơn hàng");
                for (OrderItem orderItem : orderItemServices.listOrderItem) {

                    if (order.getId() == orderItem.getOrderId()) {
                        totalMoney += orderItem.getTotal();
                        System.out.printf("*       %-16s %-32s %-15s  %-13s %-20s %-27s %-12s *\n", orderItem.getId(), orderItem.getProductName(),
                                ProductManager.addCharacters((int) orderItem.getPrice()), orderItem.getQuantity(),
                                ProductManager.addCharacters((int) orderItem.getTotal()), orderItem.getDate(), orderItem.getStatus());
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------TỔNG TIỀN: "
                        + ProductManager.addCharacters(totalMoney) + " ----------------------------------------------");


            }
        }
    }

    public static boolean checkExistNameOfOrder(String name) {
        for (Order order : listOrder) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void removeOrderByName(String name) {
        if (!OrderItemServices.isExistNameOfOrderItemList(name)) {
            listOrder.removeIf(order -> order.getName().equals(name));
            CSVUtils.write(fileName, listOrder);
        }
    }


    private static boolean isEmpty(ArrayList<Order> listOrder) {
        return listOrder.size() == 0;
    }
}
