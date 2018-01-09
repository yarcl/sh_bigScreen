package com.xqkj.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xqkj.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * 
 */
public class PageTag extends TagSupport {

	private String url;
	private PageBean page;
	private int num = 2;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		StringBuffer buffer = new StringBuffer();
		if(page==null){
			page = new PageBean();
		}
		num = this.page.getNowPage();
		buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td width='20%' height='25'>");
		buffer.append("<table border='0' cellspacing='0' cellpadding='3'><tr>");
		if(page.getNowPage()==1){
			buffer.append("<td style='color:black'>首页&nbsp;&nbsp;上一页</td>");
		}else {
			// 首页
			buffer.append("<td><a href='");
			buffer.append(url);
			buffer.append("&nowPage=1'");
			// 上一页 下一页
			buffer.append(" style='color:blue;'>首页</a></td> <td><a href='");
			buffer.append(url);
			buffer.append("&nowPage=");
			buffer.append(page.getNowPage() - 1);
			buffer.append("' style='color:blue;'>上一页</a></td>");
			buffer.append("<td>");
		}
		buffer.append("<td>");
		boolean flag = false;
		for (int i = 1; i <= page.getTotalPage(); i++) {
			if(num>3&&!flag){
				buffer.append("<td><a href='");
				buffer.append(url);
				buffer.append("&nowPage=");
				buffer.append(page.getNowPage() - 1);
				buffer.append("' style='color:blue;'>");
				buffer.append("...");
				buffer.append("</a>");
				i = num-2;
				flag = true;
			}
			if(num==i){
				buffer.append("&nbsp;<span style='color:black'>[");
				buffer.append(i);
				buffer.append("]</span>&nbsp;");
			}else if(i<=num+2){
				buffer.append("&nbsp;<a href='");
				buffer.append(url);
				buffer.append("&nowPage=");
				buffer.append(i);
				buffer.append("' style='color:blue;'>");
				buffer.append((i)+"</a>&nbsp;");
			}else if(i>num+2){
				buffer.append("<td><a href='");
				buffer.append(url);
				buffer.append("&nowPage=");
				buffer.append(page.getNowPage() + 1);
				buffer.append("' style='color:blue;'>");
				buffer.append("...");
				buffer.append("</a></td>");
				break;
			}
			/*if(page.getNowPage()<page.getTotalPage()-num){
				
			}*/
			
			
		}
		buffer.append("</td>");
		if(page.getNowPage()==page.getTotalPage()){
			buffer.append("<td style='color:black'>下一页&nbsp;&nbsp;尾页</td>");
		}else{
			buffer.append("<td><a href='");
			buffer.append(url);
			buffer.append("&nowPage=");
			buffer.append(page.getNowPage() + 1);
			buffer.append("' style='color:blue;'>下一页</a></td><td><a href='");
			buffer.append(url);
			buffer.append("&nowPage=");
			buffer.append(page.getTotalPage());
			buffer.append("' style='color:blue;'>尾页</a></td>");
		}
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("<td width='20%' align='center'>");
		buffer.append("<table border='0' cellspacing='0' cellpadding='3' onmousemove='changePage();'>");
		buffer.append("<tr>");
		buffer.append("<td><a id='go1' href='");
		buffer.append(url);
		buffer.append("&nowPage=");
		buffer.append(page.getNowPage());
		buffer.append("'>GO</a></td>");
		buffer.append("<td><input id='pp'  type='text' size='3' value='"+page.getNowPage()+"' onmouseout='changePage();'");
		buffer.append(" onkeyup='value=value.replace(/[^\\d]/g, \"\")' ");
		buffer.append("style='width:25;height:18'> /页</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append(" </td>");
		buffer.append("<td width='20%' align='right'>共");
		buffer.append(page.getCount());
		buffer.append("条记录显示到");
		buffer.append(page.getNowPage());
		buffer.append("/");
		buffer.append(page.getTotalPage());
		buffer.append("</td></tr></table>");
		
		buffer.append("<script> function changePage(){");
		buffer.append("var value = document.getElementById('pp').value;");
		buffer.append("document.getElementById('go1').href='"+url+"&nowPage='+value}");
		buffer.append("</script>");
		
		
		try {
			out.print(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.EVAL_PAGE;
	}

}
