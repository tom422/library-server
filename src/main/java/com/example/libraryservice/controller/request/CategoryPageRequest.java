package com.example.libraryservice.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryPageRequest extends BaseRequest {
    private String name;
}
