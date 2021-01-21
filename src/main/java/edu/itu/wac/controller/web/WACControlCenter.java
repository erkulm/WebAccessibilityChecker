//package edu.itu.wac.controller.web;
//
//import edu.itu.wac.entity.Website;
//import edu.itu.wac.service.response.ErrorReportResponse;
//import listener.ListenerClass;
//import models.*;
//import utility.UtilityCMD;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.*;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Vector;
//import java.util.concurrent.TimeUnit;
//
//@WebServlet("/WACControlCenter")
//public class WACControlCenter extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    private Connection conn = null;
//    private Vector<ErrorReportResponse> testHistory;
//    public static Vector<String> websiteList;
//    public static Vector<Website> comparibleWebsiteList;
//
//    public Thread thread = null;
//
//    public WACControlCenter() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		removeAttributes(request);
//
//		String website = (String)request.getParameter("website");
//		if(website==null) {
//			website = (String) request.getSession().getAttribute("website");
//		}
//
//		String action = request.getParameter("action");
//		if("checkAccessibility".equals(action) || "reCheckAccessibility".equals(action)) {
//			request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//		}
//		else if("fillMainPageData".equals(action)) {
//			request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//		}
//		else if("getWebsiteTestHistory".equals(action)) {
//			WACTester test = new WACTester(conn);
//			String web = (String) request.getParameter("website");
//			test.isCheckedBefore(web);
//			request.getSession().setAttribute("website", website);
//			request.getSession().setAttribute("websiteTestHistory", test.getTestList());
//			if( test.getTestList().size()>0) {
//				request.getSession().setAttribute("websiteGroupId", test.getTestList().get(0).getWebsiteGroupId());
//				request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//			}
//			else
//				request.getRequestDispatcher("accessibilityTest.jsp").forward(request, response);
//		}
//		else if("showDashboard".equals(action)) {
//			request.getRequestDispatcher("websiteDashboard.jsp").forward(request, response);
//		}
//		else if("showComparisonDashboard".equals(action)) {
//			request.getRequestDispatcher("websiteComparisonDashboard.jsp").forward(request, response);
//		}else if("getComparisonCriterions".equals(action)) {
//			request.getSession().setAttribute("showComparisonChart", "Y");
//			request.getRequestDispatcher("websiteDashboard.jsp").forward(request, response);
//		}else if("sortWebsiteTestHistory".equals(action)) {
//			request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//		}else if("getLastTestHistory".equals(action)) {
//			request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//		}else if("getWebsiteErrorReport".equals(action)){
//			request.getRequestDispatcher("showErrors.jsp").forward(request, response);
//		}else if("getWebsiteSubPages".equals(action)){
//			request.getRequestDispatcher("showSubPages.jsp").forward(request, response);
//		}else if("checkUserCredentials".equals(action)){
////			String userCheckResult =
////					performCheckUserCredentials(request);
////			if("OK".equals(userCheckResult)) {
////				performFillMainPageData(request);
////				request.getRequestDispatcher("mainPage.jsp").forward(request, response);
////			}else {
////				request.getSession().setAttribute("userCredentialError", "Y");
////				request.getRequestDispatcher("accessibility.jsp").forward(request, response);
////			}
//		}else if("returnToMainPage".equals(action)) {
//			request.getRequestDispatcher("mainPage.jsp").forward(request, response);
//		}
//	}
//
//
//	public void removeAttributes(HttpServletRequest request) {
//	//	request.getSession().removeAttribute("website");
//	//	request.getSession().removeAttribute("totalErrorCount");
//	//	request.getSession().removeAttribute("totalPageCount");
//	//	request.getSession().removeAttribute("testHistory");
//		request.getSession().removeAttribute("enableReTestButton");
//		request.getSession().removeAttribute("showComparisonChart");
//		request.getSession().removeAttribute("userCredentialError");
//	}
//
//}
