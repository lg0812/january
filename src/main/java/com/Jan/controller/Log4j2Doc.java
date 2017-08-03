package com.Jan.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by LG0812 on 2017/8/3.
 */
public class Log4j2Doc {

    /*以下三种方式效果相同*/
    private static Logger logger = LogManager.getLogger();
    private static Logger logger1 = LogManager.getLogger(Log4j2Doc.class);
    private static Logger logger2 = LogManager.getLogger(Log4j2Doc.class.getName());
}
