package org.ktds.pointCollation.service;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ktds.pointCollation.repositories.PointCollationData;
import org.ktds.pointCollation.repositories.PointCollationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PointCollationController {
 
	private static Logger logger = LogManager.getLogger(PointCollationController.class);
	
	/**
	 * @Autowired - 애플리케이션에 있는 Bean 객체(Spring MVC 에 의해 자동으로 인스턴스가 생성되서 애플리케이션 내에서 사용할 수 있게 된것) 와
	 *              연동하기 위한 것이다. 이 것을 사용해서 MyDataRepository 의 인스턴스가 자동으로 repository 필드로 설정된다.
	 **/
	@Autowired
	PointCollationRepository pointCollationRepository;
	
	/**
	 * Iterable 는 for-each 를 사용할 수 있는 클래스 것을 명시해주는 기능을 재고하고, Iterable 상속 받은 클래스는 Iterator를 사용하여 for-each 기능을 사용할 수 있다.
	 * java 8 부터는 Iterable 에 forEach defult method 를 제공하고 있다.
	 * 
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") PointCollationData pointCollationData,
			ModelAndView mav) {
		
		logger.info("PointCollation get ");		
		
		mav.setViewName("index");
		mav.addObject("msg", "this is JPA content");
		mav.addObject("formModel", pointCollationData);
		Iterable<PointCollationData> list = pointCollationRepository.findAll();
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	/**
	 * Iterable 는 for-each 를 사용할 수 있는 클래스 것을 명시해주는 기능을 재고하고, Iterable 상속 받은 클래스는 Iterator를 사용하여 for-each 기능을 사용할 수 있다.
	 * java 8 부터는 Iterable 에 forEach defult method 를 제공하고 있다.
	 * 
	 * */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(
			@ModelAttribute("formModel") 
			@Validated PointCollationData pointCollationData,
			BindingResult result,
			ModelAndView mav) {
		
		logger.info("Spring JPA MongoDB Service get ");		
		
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "this is MongoDB 서비스 데이터 샘플");		
		Iterable<PointCollationData> list = pointCollationRepository.findAll();
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView form(
			@ModelAttribute("formModel") 
			@Validated PointCollationData pointCollationData,
			BindingResult result,
			ModelAndView mav) {
		
			logger.info("PointCollation post ");
			
			logger.info("PointCollation dt : " + pointCollationData.getApprovalDate().toString());			
			
			ModelAndView res = null;
			
			if(!result.hasErrors()) {
				pointCollationRepository.save(pointCollationData);
	
				res = new ModelAndView("redirect:/"); 
			}else {
				mav.setViewName("index");
				mav.addObject("msg", "ssory, error is occured.....");
				Iterable<PointCollationData> list = pointCollationRepository.findAll();
				mav.addObject("datalist", list);			
				
				res = mav;
			}		
		
		return res;
	}	

	
	/**
	 * HttpServletRequest 라는 것은 JSP 서블릿에서 사용되는 HttpServletRequest 와 같은 것 이다. 서븦릿에서 doGet 이나, doPost 를 할 때 반드시 필요한 기능이다.
	 * 앞에선 post 로 받는 메서드에서는 @RequestParam 을 이용했었다. 하지만 HttpServletRequest 를 사용 할 수 없는 것 은 아니다. 즉 @RequestParam 의 파라미터라는 것은
	 * HttpServletRequest 의 getParameter 를 호출해서 자동으로 파라미터를 받고 그 결과를 인수로 설정하는 것이다.
	 **/
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
		
		logger.info("Spring Data JPA get ModelAndView post find page ");
		
			mav.setViewName("find");
			
			String param = request.getParameter("fstr");
			
			if(param == "") {
				mav = new ModelAndView("redirect:/find");
			}else {
				mav.addObject("title", "find result");		
				mav.addObject("msg", "[" + param + "] 의 검색 결과");
				mav.addObject("value", param);	
				List<PointCollationData> list = pointCollationRepository.findByCompanyId(param);				
				mav.addObject("datalist", list);			
			}
			
		return mav;
	}	
	

//	
///*******  edit 을 위한 methods *******************/	
//	
//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//	public ModelAndView edit(
//			@ModelAttribute MyData mydata,
//			@PathVariable int id,
//			ModelAndView mav) {
//		
//		mav.setViewName("edit");
//		mav.addObject("title", "edit myData");		
//		MyData data = myDataRepository.findById((long)id);
//		mav.addObject("formModel", data);
//		
//		return mav;
//	}
//	
//	
//	/**
//	 * saveAndFlush 는  키갑이 존재하면 알아서 update 를 수행 
//	 * */	
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	@Transactional(readOnly=false)
//	public ModelAndView update(
//			@ModelAttribute MyData mydata,
//			ModelAndView mav) {
//		
//		myDataRepository.saveAndFlush(mydata);
//			
//		return new ModelAndView("redirect:/");
//	}	
//	
//
///*******  delete 을 위한 methods *******************/	
//	
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public ModelAndView delete(
//			@PathVariable int id,
//			ModelAndView mav) {
//		
//		mav.setViewName("delete");
//		mav.addObject("title", "delete myData");		
//		MyData data = myDataRepository.findById((long)id);
//		mav.addObject("formModel", data);
//		
//		return mav;
//	}
//	
//	
//	/**
//	 * saveAndFlush 는  키갑이 존재하면 알아서 update 를 수행 
//	 * */	
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	@Transactional(readOnly=false)
//	public ModelAndView remove(
//			@RequestParam long id,
//			ModelAndView mav) {
//		
//		myDataRepository.deleteById(id);
//			
//		return new ModelAndView("redirect:/");
//	}	
}
