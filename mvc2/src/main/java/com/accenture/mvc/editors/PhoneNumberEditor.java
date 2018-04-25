package com.accenture.mvc.editors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class PhoneNumberEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.length() == 10) {
			String phoneMask = "###-###-####";
			String phoneNumber = text;

			MaskFormatter maskFormatter;
			try {
				maskFormatter = new MaskFormatter(phoneMask);
				maskFormatter.setValueContainsLiteralCharacters(false);
				maskFormatter.valueToString(phoneNumber);
				System.out.println("Phone-------------" + maskFormatter.valueToString(phoneNumber));
				setValue(maskFormatter.valueToString(phoneNumber));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
