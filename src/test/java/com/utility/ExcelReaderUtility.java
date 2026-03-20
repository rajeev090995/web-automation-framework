package com.utility;

import com.ui.pojo.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

  public static Iterator<User> readExcelFile(String fileName) {
    File xlsxFile = new File(
        System.getProperty("user.dir") + File.separator + "testdata" + File.separator
            + fileName);
    XSSFWorkbook xssfWorkbook = null;
    List<User> userList;
    Row row;
    Cell emailAddressCell;
    Cell passwordCell;

    try {
      xssfWorkbook = new XSSFWorkbook(xlsxFile);
      userList = new ArrayList<User>();
      XSSFSheet xssfSheet = xssfWorkbook.getSheet("LoginTestData");
      Iterator<Row> rowIterator = xssfSheet.iterator();
      rowIterator.next(); // Skip the header row
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        emailAddressCell = row.getCell(0);
        passwordCell = row.getCell(1);
        User user = new User(emailAddressCell.toString(), passwordCell.toString());
        userList.add(user);
        xssfWorkbook.close();
      }
    } catch (InvalidFormatException | IOException e) {
      throw new RuntimeException(e);
    }
    return userList.iterator();


  }
}
