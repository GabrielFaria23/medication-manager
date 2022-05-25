package com.example.demo.utils;

import org.springframework.stereotype.Component;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Component
public class FormatterUtils {

    public String formatAnvisaNumber(String anvisaNumber) throws ParseException {
        MaskFormatter mf = new MaskFormatter("#.####.####.###-#");
        mf.setValueContainsLiteralCharacters(false);
        mf.setPlaceholderCharacter('_');
        return mf.valueToString(anvisaNumber);
    }

    public String formatTelephoneSacNumber(String telephoneSac) throws ParseException {
        MaskFormatter mf = new MaskFormatter("(##)#####-####");
        mf.setValueContainsLiteralCharacters(false);
        mf.setPlaceholderCharacter('_');
        return mf.valueToString(telephoneSac);
    }
}
