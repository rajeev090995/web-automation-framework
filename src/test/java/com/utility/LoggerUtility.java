package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

  // Global config - setup for all loggers
  // singleton pattern - only one instance of logger utility will be created and shared across the entire application
  // it insures only one object of logger utility is created

  private LoggerUtility() {
    // private constructor to prevent instantiation
  }

  public static Logger getLogger(Class<?> clazz) {
    Logger logger=null;
    if (logger == null) {
      logger = LogManager.getLogger(clazz);
      return logger;
    }
    return logger;
  }
}
