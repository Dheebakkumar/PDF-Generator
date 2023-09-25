package com.PdfGeneratorPay.aw.Controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PdfGeneratorPay.aw.Service.PayService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pdf")
public class PayController {
	
	@Autowired
	PayService payService;
	
	@GetMapping("/pay/aw")
	public void generatePdfPay(HttpServletResponse response) throws Exception {
		
		String headerKey = "Content-Disposition";
		String HeaderValue = "inline ; filename = pay.pdf";
		response.addHeader(headerKey, HeaderValue);
		
		OutputStream outputStream = response.getOutputStream();
		payService.paySlip(outputStream);
		
		outputStream.close();
		

		
	}

}
