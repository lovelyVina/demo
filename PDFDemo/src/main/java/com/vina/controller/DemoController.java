package com.vina.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.vina.entity.User;
import com.vina.view.PdfStamperView;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/index")
	public String demo(HttpServletRequest request, Model model){
		return "demo";
	}
	
	@RequestMapping("/ceratePdf")
	public String creatPdf(HttpServletRequest request, User user, Model model) throws IOException, DocumentException{
		String newPdfFilePath = request.getServletContext().getRealPath("/") + "upload" + File.separator + (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ (user!=null?user.getName():"demo")) + ".pdf";
		editPdfTemplate(user, request.getServletContext().getRealPath("/") + "demo.pdf", newPdfFilePath);
		model.addAttribute("pdfFilePath", newPdfFilePath);
		return "createPdfSuccess";
	}
	
	@RequestMapping("/exportPdfByView")
	public String exportPdfByView(HttpServletRequest request, User user, Model model) {
		String fileName = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ (user!=null?user.getName():"demo")) + ".pdf";
		model.addAttribute(PdfStamperView.DATA, user);
		model.addAttribute(PdfStamperView.FILENAME, fileName);
		return "pdfTest";
	}
	
	public void editPdfTemplate(User user, String templateFile, String outFile)
		throws IOException, DocumentException {
		PdfReader reader = new PdfReader(templateFile); // 模版文件目录
		PdfStamper ps = new PdfStamper(reader, new FileOutputStream(outFile)); // 生成的输出流
		BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		AcroFields s =  ps.getAcroFields();
		//设置文本域表单的字体
		// 对于模板要显中文的，在此处设置字体比在pdf模板中设置表单字体的好处：
		//1.模板文件的大小不变；2.字体格式满足中文要求
		/*s.setFieldProperty("fill_3","textfont",bf,null);
		s.setFieldProperty("fill_5","textfont",bf,null);
		s.setFieldProperty("fill_2","textfont",bf,null);
		s.setFieldProperty("fill_4","textfont",bf,null);
		s.setFieldProperty("fill_6","textfont",bf,null);
		//编辑文本域表单的内容
		s.setField("fill_3", "姚 秀 才");
		s.setField("fill_5", "cf");
		s.setField("fill_2", "cn-990000");
		s.setField("fill_4",  "模版文件目录");
		s.setField("fill_6", "模版文件目录");*/
		
		s.setFieldProperty("name","textfont",bf,null);
		s.setFieldProperty("gender","textfont",bf,null);
		//编辑文本域表单的内容
		s.setField("name", user.getName());
		s.setField("gender", user.getGender());
		
		ps.setFormFlattening(true); // 这句不能少
		ps.close();
		reader.close();
		}
}
