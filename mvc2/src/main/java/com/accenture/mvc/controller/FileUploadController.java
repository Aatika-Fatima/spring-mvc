package com.accenture.mvc.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileUploadController {

	@RequestMapping("/uploadFile")
	public String handleFormUpload(@RequestParam("file") MultipartFile file, @RequestParam(name="tag", defaultValue="profile") String tag, 
			@RequestPart("file") MultipartFile multPartFile, 
			final RedirectAttributes rd) {

		long size = file.getSize();
		try {
			byte b[] = new byte[(int) size];
			InputStream inputStream = file.getInputStream();
			int ch;
			while( (ch = inputStream.read()) != -1) {
				System.out.print((char)ch);
			}
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File Tag == " + tag);
	//	ModelMap m = new ModelMap(); 
	//	m.addAttribute("msg", "photo uploaded successfully");
		 rd.addAttribute("msg", "file uploaded successfully");
		return "redirect:showRedirect";
		//return "uploadPhoto";
	}
	@RequestMapping("/showRedirect") 
	public String show(@ModelAttribute("msg") String msg) {
		return "uploadPhoto";
	}
}
 
