package com.coy.pdfreader.service;

import com.coy.pdfreader.model.PaymentEntity;
import com.coy.pdfreader.model.RefundEntity;
import com.coy.pdfreader.repository.PaymentRepository;
import com.coy.pdfreader.repository.RefundRepository;
import com.coy.pdfreader.util.MonthConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Locale;

@Service
@Log4j2
@RequiredArgsConstructor
public class LineProcessor {

    private final RefundRepository refundRepository;
    private final PaymentRepository paymentRepository;
    private final MonthConverter monthConverter;

    public void execute(String line, String date, String operation) {
        String[] parts = line.split("\\s+");

        if (parts.length < 3) {
            return;
        }

        try {
            String amountString = parts[parts.length - 1].replace(".", "").replace(",", ".");

            BigDecimal amount = new BigDecimal(amountString);

            Integer quantity = Integer.parseInt(parts[parts.length - 2]);

            String type = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));

            String enDate = convertDatePtToEn(date);

            DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MMM/yy").toFormatter(Locale.ENGLISH);

            LocalDate localDate = LocalDate.parse(enDate, formatter);

            saveReport(type, quantity, amount, localDate, operation);
        } catch (NumberFormatException | ArithmeticException ex) {
            log.warn("The values amount and quantity are not in the accepted format. \nDetails: {}", ex.getMessage());
        }
    }

    private String convertDatePtToEn(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
        parts[1] = monthConverter.convertMonthEN(parts[1]);

        return String.join("/", parts);
    }


    public void saveReport(String type, Integer quantity, BigDecimal amount, LocalDate localDate, String operation) {
        switch (operation) {
            case "PAGAMENTOS":
                paymentRepository.save(new PaymentEntity(type, quantity, amount, localDate));
                break;

            case "ESTORNOS":
                refundRepository.save(new RefundEntity(type, quantity, amount, localDate));
                break;

            default:
                log.warn("Operation not valid: {}", operation);
        }
    }
}
