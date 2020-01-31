package max.bubnov.library.controller;

import max.bubnov.library.domain.Client;
import max.bubnov.library.domain.Order;
import max.bubnov.library.service.OrderService;
import max.bubnov.library.utils.PeriodTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Order order) {
        orderService.delete(order);
    }

    @PutMapping("/setActive/{id}")
    public Order setActive(@PathVariable Order order) {
        return orderService.executeOrder(order);
    }

    @GetMapping("/report")
    public Map<Client, Integer> report(@RequestBody PeriodTime periodTime) {
        return orderService.reportOrder(periodTime);
    }

}
