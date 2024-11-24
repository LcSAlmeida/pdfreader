package com.coy.pdfreader.util;



import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MonthConverter {

    private static final Map<String, String> monthPTtoEN = new HashMap<>();
    private static final Map<String, String> monthENtoPT = new HashMap<>();

     static {
        monthPTtoEN.put("JAN", "Jan");
        monthPTtoEN.put("FEV", "Feb");
        monthPTtoEN.put("MAR", "Mar");
        monthPTtoEN.put("ABR", "Apr");
        monthPTtoEN.put("MAI", "May");
        monthPTtoEN.put("JUN", "Jun");
        monthPTtoEN.put("JUL", "Jul");
        monthPTtoEN.put("AGO", "Aug");
        monthPTtoEN.put("SET", "Sep");
        monthPTtoEN.put("OUT", "Oct");
        monthPTtoEN.put("NOV", "Nov");
        monthPTtoEN.put("DEZ", "Dec");

        monthENtoPT.put("Jan", "Jan");
        monthENtoPT.put("Feb", "Fev");
        monthENtoPT.put("Mar", "Mar");
        monthENtoPT.put("Apr", "Abr");
        monthENtoPT.put("May", "Mai");
        monthENtoPT.put("Jun", "Jun");
        monthENtoPT.put("Jul", "Jul");
        monthENtoPT.put("Aug", "Ago");
        monthENtoPT.put("Sep", "Set");
        monthENtoPT.put("Oct", "Out");
        monthENtoPT.put("Nov", "Nov");
        monthENtoPT.put("Dec", "Dez");
    }

    public String convertMonthEN(String mes) {
        return monthPTtoEN.getOrDefault(mes, "Month not valid");
    }

    public String convertMonthPT(String mes) {
        return monthENtoPT.getOrDefault(mes, "Month not found");
    }
}
