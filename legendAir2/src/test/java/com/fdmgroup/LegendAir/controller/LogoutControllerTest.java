package com.fdmgroup.LegendAir.controller;

import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutControllerTest {
	
	@Test
	public void on_get_request_session_is_invalidated_user_logged_out_redirected_to_home_page() throws ServletException, IOException {
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		InOrder order = inOrder(mockReq, mockRes, mockSession);
		
		LogoutController lc = new LogoutController();
		lc.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).invalidate();
		order.verify(mockRes).sendRedirect(mockReq.getContextPath() + "/");
	}
}
