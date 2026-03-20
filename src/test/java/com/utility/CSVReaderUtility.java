package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

  public static Iterator<User> readCSVFile(String fileName) {

    File file = new File(System.getProperty("user.dir") + File.separator +"testdata"+File.separator+fileName);
    FileReader fileReader = null;
    CSVReader csvReader;
    String [] data;
    List<User> userList;
    User user;
    try {
      fileReader = new FileReader(file);
      csvReader = new CSVReader(fileReader);
      csvReader.readNext(); // skip the header row
      // no row or once we reach end of the file, readNext() will return null
      userList = new ArrayList<User>();
      while ((data = csvReader.readNext()) != null) {
        user = new User(data[0],data[1]);
        userList.add(user);
      }
    } catch (CsvValidationException|IOException e) {
      throw new RuntimeException(e);
    }
    return userList.iterator();
  }
}
