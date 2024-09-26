package StreamAPI.one;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        System.out.println("Группируйте заказы по продуктам.");
        groupingPrint(orders);
        System.out.println("Для каждого продукта найдите общую стоимость всех заказов.");
        System.out.println("Отсортируйте продукты по убыванию общей стоимости." );
        groupingPrintCostSort(orders);


        sortPrintCost(orders);

    }

    public static void groupingPrint(List<Order> orders) {
        orders.stream().collect(Collectors.groupingBy(Order::getProduct))
                .entrySet().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }



    public static void groupingPrintCostSort(List<Order> orders) {
        orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
                .entrySet()
                .stream().sorted(Comparator.comparing(e -> e.getKey()))
                .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }

    public static void sortPrintCost(List<Order> orders) {
        ListAndCost listAndCost = new ListAndCost();
        listAndCost.setOrders( orders.stream().sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .toList());
        listAndCost.setSelectCost(listAndCost.getOrders().stream()
                .collect(Collectors.summingDouble(Order::getCost))
                .intValue());
        System.out.println("список трех самых дорогих продуктов:");
        listAndCost.getOrders().stream().toList().forEach(e -> System.out.println(e));
        System.out.println("На сумм:" + listAndCost.getSelectCost());
    }

    private static class ListAndCost{
        private int selectCost;
        private List<Order> orders;

        public ListAndCost(int selectCost, List<Order> orders) {
            this.selectCost = selectCost;
            this.orders = orders;
        }
        public ListAndCost() {
            this.selectCost = 0;
            this.orders = new ArrayList<>();
        }
        public int getSelectCost() {
            return selectCost;
        }
        public void setSelectCost(int selectCost) {
            this.selectCost = selectCost;
        }
        public List<Order> getOrders() {
            return orders;
        }
        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    }

}