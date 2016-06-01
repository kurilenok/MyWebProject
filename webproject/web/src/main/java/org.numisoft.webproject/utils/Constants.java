package org.numisoft.webproject.utils;

import java.util.ResourceBundle;

/**
 * Created by kukolka on 13.05.16.
 */
public class Constants {

    static ResourceBundle rb = ResourceBundle.getBundle("constants");

    public static final String FORWARD_TO_LOGIN = rb.getString("FORWARD_TO_LOGIN");
    public static final String REDIRECT_TO_CATALOG = rb.getString("REDIRECT_TO_CATALOG");
    public static final String REDIRECT_TO_COLLECTION = rb.getString("REDIRECT_TO_COLLECTION");
    public static final String REDIRECT_TO_400_ERROR = rb.getString("REDIRECT_TO_400_ERROR");


}
