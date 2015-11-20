package com.vina.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractPdfStamperView;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfStamper;


public class PdfStamperView extends AbstractPdfStamperView {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String DATA = "data";
	public static final String FILENAME = "mergePdfFileName";

	@Override
	protected void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(model.get(FILENAME).toString().getBytes(), "ISO8859-1"));
		AcroFields fields = stamper.getAcroFields();
		fillData(fields, data((Object)model.get(DATA)));
		stamper.setFormFlattening(true);
	}

	private void fillData(AcroFields fields, Map<String, String> data)
			throws IOException, DocumentException {
		for (Object key : data.keySet()) {
			Object value = data.get(key);
			fields.setField(key+"", value+"");
		}
	}

	private Map<String, String> data(Object entity) {
		if(entity != null){
			try {
				return BeanUtils.describe(entity);
			} catch (Exception e) {
				logger.error("转化数据出现异常：异常原因：{}", e);
				return null;
			}
		}
		
		return null;
	}
}
