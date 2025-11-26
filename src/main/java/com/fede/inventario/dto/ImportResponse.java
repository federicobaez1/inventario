package com.fede.inventario.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ImportResponse {
    private List<String> columns;
    private List<Map<String, Object>> rows;
}
