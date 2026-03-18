package com.example.activmq01.controller;

import com.example.activmq01.dto.ExcelRequestDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @PostMapping("/generate")
    public ResponseEntity<String> generateExcel(@RequestBody ExcelRequestDto dto) {
        String fileName = "generated-data.xlsx";
        Workbook workbook;
        Sheet sheet;
        int lastRowNum = 0;
        java.io.File file = new java.io.File(fileName);
        try {
            if (file.exists()) {
                // Load existing workbook
                try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
                //workbook.getSheetAt(0); gets the sheet at the specified index
                sheet = workbook.getSheet("Data");
                lastRowNum = sheet.getLastRowNum() + 1;
                // Add another sheet if not already present
                if (workbook.getNumberOfSheets() < 2) {
                    workbook.createSheet("Data2");
                }
            } else {
                // Create new workbook and header
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Data");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Name");
                header.createCell(1).setCellValue("Age");
                header.createCell(2).setCellValue("Gender");
                header.createCell(3).setCellValue("Phone");
                header.createCell(4).setCellValue("Email");
                lastRowNum = 1;
                // Add another sheet
                workbook.createSheet("Data2");
            }

            // Append new data row
            Row row = sheet.createRow(lastRowNum);
            row.createCell(0).setCellValue(dto.getName());
            row.createCell(1).setCellValue(dto.getAge() != null ? dto.getAge() : 0);
            row.createCell(2).setCellValue(dto.getGender());
            row.createCell(3).setCellValue(dto.getPhone());
            row.createCell(4).setCellValue(dto.getEmail());

            // Save the file to local disk (server side)
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
            workbook.close();

            return ResponseEntity.ok("Data appended successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/append-and-return-json")
    public ResponseEntity<String> appendAndReturnJson(@RequestBody ExcelRequestDto dto) {
        String fileName = "generated-data.xlsx";
        Workbook workbook;
        Sheet sheet;
        int lastRowNum = 0;
        java.io.File file = new java.io.File(fileName);
        try {
            if (file.exists()) {
                // Load existing workbook
                try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
                sheet = workbook.getSheet("Data");
                lastRowNum = sheet.getLastRowNum() + 1;
                // Add another sheet if not already present
                if (workbook.getNumberOfSheets() < 2) {
                    workbook.createSheet("Data2");
                }
            } else {
                // Create new workbook and header
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Data");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Name");
                header.createCell(1).setCellValue("Age");
                header.createCell(2).setCellValue("Gender");
                header.createCell(3).setCellValue("Phone");
                header.createCell(4).setCellValue("Email");
                lastRowNum = 1;
                // Add another sheet
                workbook.createSheet("Data2");
            }

            // Append new data row
            Row row = sheet.createRow(lastRowNum);
            row.createCell(0).setCellValue(dto.getName());
            row.createCell(1).setCellValue(dto.getAge() != null ? dto.getAge() : 0);
            row.createCell(2).setCellValue(dto.getGender());
            row.createCell(3).setCellValue(dto.getPhone());
            row.createCell(4).setCellValue(dto.getEmail());

            // Save the file to local disk (server side)
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
            workbook.close();

            // Create JSON with column names and values of the appended row
            Row header = sheet.getRow(0);
            StringBuilder json = new StringBuilder();
            json.append("{");
            for (int i = 0; i < header.getLastCellNum(); i++) {
                if (i > 0) json.append(", ");
                String colName = header.getCell(i).getStringCellValue();
                Cell dataCell = row.getCell(i);
                String value = "";
                if (dataCell != null) {
                    switch (dataCell.getCellType()) {
                        case STRING:
                            value = "\"" + dataCell.getStringCellValue() + "\"";
                            break;
                        case NUMERIC:
                            value = String.valueOf((int)dataCell.getNumericCellValue());
                            break;
                        default:
                            value = "\"\"";
                    }
                } else {
                    value = "\"\"";
                }
                json.append("\"" + colName + "\": " + value);
            }
            json.append("}");
            return ResponseEntity.ok(json.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
