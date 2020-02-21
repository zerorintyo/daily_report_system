package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Customer;

public class CustomerValidator {
    public static List<String> validate(Customer c) {
        List<String>errors = new ArrayList<String>();

        String customer_name_error = _validateCustomer_name(c.getCustomer_name());
        if(!customer_name_error.equals("")) {
            errors.add(customer_name_error);
        }

        String content_error = _validateContent(c.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }


    private static String _validateCustomer_name(String customer_name) {
        if(customer_name == null || customer_name.equals("")) {
            return "顧客名を入力してください。";
        }
        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "顧客情報を入力してください。";
        }
        return "";
    }


}
