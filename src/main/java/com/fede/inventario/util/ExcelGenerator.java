package com.fede.inventario.util;

import com.fede.inventario.dto.ExportRequest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class ExcelGenerator {

    public static ByteArrayInputStream generate(ExportRequest request) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Export");

        int rowIdx = 0;

        // ENCABEZADO
        Row headerRow = sheet.createRow(rowIdx++);
        for (int i = 0; i < request.getColumns().size(); i++) {
            headerRow.createCell(i).setCellValue(request.getColumns().get(i));
        }

        // FILAS DINÁMICAS
        for (var rowData : request.getRows()) {
            Row row = sheet.createRow(rowIdx++);
            int colIdx = 0;
            for (String col : request.getColumns()) {
                Object val = rowData.get(col);
                row.createCell(colIdx++).setCellValue(val != null ? val.toString() : "");
            }
        }

        // Auto-ajustar ancho de columnas
        for (int i = 0; i < request.getColumns().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Generar stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
