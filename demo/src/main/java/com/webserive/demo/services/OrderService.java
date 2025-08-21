package com.webserive.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserive.demo.DTO.OrderDTO;
import com.webserive.demo.entities.Category;
import com.webserive.demo.entities.Order;
import com.webserive.demo.entities.OrderItem;
import com.webserive.demo.entities.OrderStatus;
import com.webserive.demo.entities.Product;
import com.webserive.demo.entities.User;
import com.webserive.demo.repository.OrderRepository;
import com.webserive.demo.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    public Order newOrder(OrderDTO orderDTO) {

        User client = userRepository.findById(orderDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Usuário com id " + orderDTO.clienteId() + " não encontrado!"));

        Order newOrder = new Order();
        newOrder.setClient(client);
        newOrder.setMoment(LocalDate.now());
        newOrder.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        List<OrderItem> products = orderDTO.items().stream()
                .map(itemDTO -> {
                    Product product = productService.findProduct(itemDTO.productName());

                    if (product == null) {
                        throw new RuntimeException("Produto " + itemDTO.productName() + " não encontrado!");
                    }
                    OrderItem newOrderItem = new OrderItem();
                    newOrderItem.setProduct(product);
                    newOrderItem.setQuantity(itemDTO.quantity());
                    newOrderItem.setPrice(product.getPrice());
                    newOrderItem.setOrder(newOrder);

                    return newOrderItem;

                })
                .toList();

        newOrder.setItems(products);

        return orderRepository.save(newOrder);

    }

    public Order deleteOrder(Integer id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return order;
                })
                .orElseThrow(() -> new RuntimeException("Pedido com id " + id + " não encontrado!"));
    }

}
