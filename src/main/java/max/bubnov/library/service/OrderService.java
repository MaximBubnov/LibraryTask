package max.bubnov.library.service;

import max.bubnov.library.domain.Client;
import max.bubnov.library.domain.Order;
import max.bubnov.library.repo.OrderRepo;
import max.bubnov.library.utils.PeriodTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).stream().findFirst().orElse(null);
    }

    public Order createOrder(Order order) {
        order.setActive(false);
        order.setCreationDate(LocalDate.now());
        return orderRepo.save(order);
    }

    public Order updateOrder(Long id, Order order) {

        Order byId = getById(id);

        byId.setClient(order.getClient());
        byId.setBooks(order.getBooks());
        byId.setCreationDate(order.getCreationDate());
        byId.setExecutionDate(order.getExecutionDate());
        byId.setActive(order.isActive());

        return orderRepo.save(byId);
    }

    public void delete(Order order) {
        orderRepo.delete(order);
    }

    //Get Client`s orders
    public List<Order> getClientsOrders(Client client) {

       return orderRepo.findAll()
               .stream()
               .filter(c -> c.getClient().getId().equals(client.getId()))
               .collect(Collectors.toList());
    }

    //Execute order
    public Order executeOrder(Order order) {
        order.setActive(true);
        return orderRepo.save(order);
    }

    //Report-method: count of client`s order
    public Map<Client, Integer> reportOrder(PeriodTime periodTime) {

        Map<Client, Integer> map = new HashMap<>();

        List<Order> allOrders = orderRepo.findAll();

        for (Order order : ordersForPeriod(periodTime, allOrders)) {
            map.put(order.getClient(), getCountOfBooks(order.getClient()));
        }

        return map;
    }

    //Get all books, which client ordered
    private Integer getCountOfBooks(Client client) {
        List<Order> orders = client.getOrders();
        int count = 0;

        for (Order order : orders) {
            count += order.getBooks().size();
        }

        return count;
    }

    //Get list of orders for some period time. Example of custom PeriodTime in JSON below
    /*
    {
       "first" : "2020-01-20",
       "second" : "2020-01-30"
     }
     */
    private List<Order> ordersForPeriod(PeriodTime time, List<Order> orders) {

        long f = time.getFirst().toEpochDay();
        long s = time.getSecond().toEpochDay();

        List<Order> list = new ArrayList<>();

        for(Order o : orders) {
            if(o.getCreationDate().toEpochDay() >= f && o.getCreationDate().toEpochDay() <= s) {
                list.add(o);
            }
        }
        return list;
    }
}
