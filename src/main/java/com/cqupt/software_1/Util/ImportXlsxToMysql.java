package com.cqupt.software_1.Util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportXlsxToMysql {
    public static void main(String[] args) {
        String xlsxFilePath = "C:\\Users\\hp\\Desktop\\example.xlsx";
        String dbUrl = "jdbc:mysql://10.16.48.219:3306/result_save";
        String username = "root";
        String password = "111111";
        String tableName = "";

        try (FileInputStream fileInputStream = new FileInputStream(xlsxFilePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream);
             Connection connection = DriverManager.getConnection(dbUrl, username, password)) {

            Sheet sheet = workbook.getSheetAt(0);

            // 获取表头行
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            // 构建 SQL 插入语句
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
            for (int i = 0; i < columnCount; i++) {
                String columnName = getCellValueAsString(headerRow.getCell(i));
                sqlBuilder.append(columnName);
                if (i < columnCount - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(") VALUES (");
            for (int i = 0; i < columnCount; i++) {
                sqlBuilder.append("?");
                if (i < columnCount - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(")");

            String insertSql = sqlBuilder.toString();

            // 准备插入语句
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                // 遍历行
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);

                    // 设置参数值
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        Cell cell = row.getCell(columnIndex);
                        String cellValue = getCellValueAsString(cell);
                        statement.setString(columnIndex + 1, cellValue);
                    }

                    // 执行插入操作
                    statement.executeUpdate();
                }
            }

            System.out.println("Data imported successfully!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        String cellValue;

        if (cellType == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            cellValue = String.valueOf(cell.getNumericCellValue());
        } else if (cellType == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cellType == CellType.FORMULA) {
            cellValue = cell.getCellFormula();
        } else {
            cellValue = "";
        }

        return cellValue;
    }
}
