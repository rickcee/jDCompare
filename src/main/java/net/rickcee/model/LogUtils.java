/**
 * 
 */
package net.rickcee.model;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author catalrc
 * 
 */
public class LogUtils {
	
	/**
	 * 
	 * @param t The exception
	 * @return The exception trace as a string
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	/**
	 * 
	 * @param model The DBModel object
	 * @param e The exception
	 * @return The ModelAndView object redirecting to the error page.
	 */
	public static final ModelAndView setExceptionResult(DBModel model, Exception e) {
		e.printStackTrace();
		model.setExceptionMessage(e.getClass().getName() + ": " + e.getMessage());
		model.setExceptionTrace(LogUtils.getStackTrace(e));
		return new ModelAndView("Error");
	}
}
