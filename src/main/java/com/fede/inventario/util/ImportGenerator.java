package com.fede.inventario.util;

import com.fede.inventario.dto.ImportResponse;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.*;

public class ImportGenerator {

    public static ImportResponse readExcel(InputStream input) throws Exception {

        Workbook workbook = WorkbookFactory.create(input);
        Sheet sheet = workbook.getSheetAt(0);

        List<String> columns = new ArrayList<>();
        List<Map<String, Object>> rows = new ArrayList<>();

        boolean isHeader = true;

        for (Row row : sheet) {

            // leer encabezado
            if (isHeader) {
                for (Cell cell : row) {
                    columns.add(cell.getStringCellValue());
                }
                isHeader = false;
                continue;
            }

            // leer filas
            Map<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = row.getCell(i);

                if (cell == null) {
                    map.put(columns.get(i), null);
                    continue;
                }

                switch (cell.getCellType()) {
                    case STRING -> map.put(columns.get(i), cell.getStringCellValue());
                    case NUMERIC -> {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            map.put(columns.get(i), cell.getLocalDateTimeCellValue().toString());
                        } else {
                            map.put(columns.get(i), cell.getNumericCellValue());
                        }
                    }
                    case BOOLEAN -> map.put(columns.get(i), cell.getBooleanCellValue());
                    case FORMULA -> map.put(columns.get(i), cell.getCellFormula());
                    default -> map.put(columns.get(i), null);
                }
            }

            rows.add(map);
        }

        ImportResponse resp = new ImportResponse();
        resp.setColumns(columns);
        resp.setRows(rows);

        return resp;
    }
}
