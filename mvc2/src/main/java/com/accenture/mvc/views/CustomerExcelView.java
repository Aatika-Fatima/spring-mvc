package com.accenture.mvc.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.accenture.mvc.model.Customer;

public class CustomerExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		List<Customer> customersList = (List<Customer>) model.get("customers");

		Sheet sheet = workbook.createSheet("Customer Sheet");
		int i = 0;
		for (Customer c : customersList) {
			Row row = sheet.createRow(i);
			row.createCell(0, 0).setCellValue(c.getId());
			row.createCell(1, 0).setCellValue(c.getFirstName());
			row.createCell(1, 1).setCellValue(c.getEmail());
		}
	}

}
