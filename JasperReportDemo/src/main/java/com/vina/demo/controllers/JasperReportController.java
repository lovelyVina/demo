package com.vina.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vina.demo.dao.MockDataFactory;

@Controller
public class JasperReportController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
    /**
     * Retrieves the PDF report file
     * 
     * @return
     */
    @RequestMapping(value = "/getpdfReport", method = RequestMethod.GET)
    public ModelAndView doSalesReportPDF(ModelAndView modelAndView){
		logger.debug("Received request to download PDF report");
		
		// Retrieve our data from a mock data provider
		MockDataFactory dataprovider = new MockDataFactory();
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource categoryData  = dataprovider.getCategoriesData();

		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		// must have the empty data source!!!
		JREmptyDataSource emptyData = new JREmptyDataSource();
		parameterMap.put("datasource", emptyData);
		parameterMap.put("JasperfishSubReportDatasource", categoryData);
		parameterMap.put("JasperfishSummaryInfo", dataprovider.getSummaryInfo());
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfReport", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    
    
    @RequestMapping(value = "/getCsvReport", method = RequestMethod.GET)
    public ModelAndView doSalesReportCSV(ModelAndView modelAndView){
		logger.debug("Received request to download PDF report");
		
		MockDataFactory dataprovider = new MockDataFactory();
		
		JRDataSource categoryData  = dataprovider.getCategoriesData();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		JREmptyDataSource emptyData = new JREmptyDataSource();
		parameterMap.put("datasource", emptyData);
		parameterMap.put("JasperfishSubReportDatasource", categoryData);
		parameterMap.put("JasperfishSummaryInfo", dataprovider.getSummaryInfo());
		
		modelAndView = new ModelAndView("xlsReport", parameterMap);
		
		return modelAndView;
	}
    
    @RequestMapping(value = "/getHtmlReport", method = RequestMethod.GET)
    public ModelAndView doSalesReportHTML(ModelAndView modelAndView){
		logger.debug("Received request to download PDF report");
		
		MockDataFactory dataprovider = new MockDataFactory();
		
		JRDataSource categoryData  = dataprovider.getCategoriesData();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		JREmptyDataSource emptyData = new JREmptyDataSource();
		parameterMap.put("datasource", emptyData);
		parameterMap.put("JasperfishSubReportDatasource", categoryData);
		parameterMap.put("JasperfishSummaryInfo", dataprovider.getSummaryInfo());
		
		modelAndView = new ModelAndView("htmlReport", parameterMap);
		
		return modelAndView;
	}
}
