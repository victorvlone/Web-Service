package com.webserive.demo.DTO;

import java.util.List;

public record ProductDTO(String name, String Description, Double Price, String imgUrl, List<String> categories) {

}
