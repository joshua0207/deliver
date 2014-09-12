package com.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.order.mode.CustomPoMast;
import com.order.mode.ShopPoDetl;
import com.order.mode.vo.LoginAdminVO;
import com.order.service.OrderManagerService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/ordermanager")
public class ShopOrderManagerController extends BaseController {

	@Autowired
	private OrderManagerService orderManagerService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	//訂單管理首頁
	@RequestMapping(value = "/shop/ordermanager", method = RequestMethod.GET)
	public String orderManager(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		String datepickerS = "";
		String datepickerE = "";
		TimeMachine time = new TimeMachine();
		String currDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		datepickerS = StringUtils.trimToEmpty(req.getParameter("datepicker")).equals("")?currDate:StringUtils.trimToEmpty(req.getParameter("datepicker"));
		datepickerE = StringUtils.trimToEmpty(req.getParameter("datepickerE")).equals("")?time.getMaxMonthDate(new Date()):StringUtils.trimToEmpty(req.getParameter("datepickerE"));
		
		req.setAttribute("datepicker", datepickerS);
		req.setAttribute("datepickerE", datepickerE);
		
		model.addAttribute("pageNumber", 1);
		model.addAttribute("pageTotal", 0);
		model.addAttribute("chk", "first");
		
		return "ordermanager/orderManager";
	}
	
	//訂單管理查詢
	@RequestMapping(value = "/shop/queryordermanager", method = RequestMethod.POST)
	public String queryOrderManager(Model model, @RequestParam String datepicker,
			@RequestParam String datepickerE, @RequestParam Integer pageNumber,
			HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		String queryDate = datepicker;      // 資料查詢起始日期
		String queryDateEnd = datepickerE;   // 資料查詢終止日期
		
		int page = pageNumber; //页号
		int pageSize = 7; //每页数据条数
		String sortString = "";//如果你想排序的话逗号分隔可以排序多列
//		new PageBounds();//默认构造函数不提供分页，返回ArrayList
//		new PageBounds(int limit);//取TOPN操作，返回ArrayList
//		new PageBounds(Order... order);//只排序不分页，返回ArrayList
//		 
//		new PageBounds(int page, int limit);//默认分页，返回PageList
//		new PageBounds(int page, int limit, Order... order);//分页加排序，返回PageList
//		new PageBounds(int page, int limit, List<Order> orders, boolean containsTotalCount);//使用containsTotalCount来决定查不查询totalCount，即返回ArrayList还是PageList
		PageBounds pageBounds = new PageBounds(page, pageSize, Order.formString(sortString));
		
		List<CustomPoMast> customPoMastList = orderManagerService.queryCustomPoMastByShopIdAndDay(shopId, queryDate, queryDateEnd, pageBounds);
		
		//获得结果集条总数
		PageList<CustomPoMast> pageList = (PageList<CustomPoMast>)customPoMastList;
		int pageTotal = pageList.getPaginator().getTotalPages();//總共頁數
//		System.out.println("totalCount1: " + pageList.getPaginator().getTotalCount());//總數EX:13
//		System.out.println("totalCount2: " + pageList.getPaginator().getLimit());//每页数据条数EX:5
//		System.out.println("totalCount3: " + pageList.getPaginator().getPage());//當前頁數是那一頁EX:2
//		System.out.println("totalCount4: " + pageList.getPaginator().getTotalPages());//總共頁數EX:3
//		System.out.println("totalCount5: " + pageList.getPaginator().getPrePage());//前一頁是那一頁EX:1
//		System.out.println("totalCount6: " + pageList.getPaginator().getNextPage());//下一頁是那一頁EX:3
//		System.out.println("totalCount7: " + pageList.getPaginator().getStartRow());//現在開始起數是第幾個EX:6
//		System.out.println("totalCount8: " + pageList.getPaginator().getOffset());//之前最後尾數是第幾個EX:5
//		System.out.println("totalCount9: " + pageList.getPaginator().getEndRow());//第一頁到這頁共有幾個總數EX:10
		
		model.addAttribute("customPoMastList", customPoMastList);
		model.addAttribute("datepicker", queryDate);
		model.addAttribute("datepickerE", queryDateEnd);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageTotal", pageTotal);
		
		return "ordermanager/orderManager";
	}
	
	
	//訂單管理查詢明細頁面
	@RequestMapping(value = "/shop/ordermanagerdetal", method = RequestMethod.POST)
	public String orderManagerDetal(Model model, @RequestParam String orderId,
		   HttpServletRequest req, HttpSession s)  throws Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		//查詢店家的單筆訂單資料
		CustomPoMast customPoMast = orderManagerService.queryCustomPoMastByOrderId(orderId);
		
		//查詢店家訂單明細資料
		List<ShopPoDetl> shopPoDetlList = orderManagerService.queryShopPoDetlByKey(orderId, shopId);
		
		model.addAttribute("customPoMast", customPoMast);
		model.addAttribute("shopPoDetlList", shopPoDetlList);
		
		return "ordermanager/orderManagerDetal";
	}
	
	

}