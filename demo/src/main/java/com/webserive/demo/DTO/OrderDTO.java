package com.webserive.demo.DTO;

import java.util.List;

public record OrderDTO(Integer clienteId, List<OrderItemDTO> items) {

}
