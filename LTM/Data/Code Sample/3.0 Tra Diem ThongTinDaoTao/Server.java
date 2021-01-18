package bai10;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// Lưu ý: bài này chỉ mới viết 1 phần code chính (trả về thông tin cá nhân và đoạn code HTML chứa điểm tổng kết).
// SV tự hoàn thiện theo mô hình client-server
public class Server {
	public static void main(String[] args) {
		try {
			String urlLogin = "http://thongtindaotao.sgu.edu.vn/default.aspx?page=nhapmasv&flag=XemDiemThi";
			String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";
			Connection.Response response = Jsoup.connect(urlLogin)
					.method(Connection.Method.GET)
					.execute();

			Document loginPage = response.parse();
			response = Jsoup.connect(urlLogin)
					.data("__EVENTTARGET", "")
					.data("__EVENTARGUMENT", "")
					.data("__VIEWSTATE", loginPage.getElementById("__VIEWSTATE").val())
					.data("__VIEWSTATEGENERATOR", loginPage.getElementById("__VIEWSTATEGENERATOR").val())
					.data("ctl00$ContentPlaceHolder1$ctl00$txtMaSV", "3116330016")
					.data("ctl00$ContentPlaceHolder1$ctl00$btnOK", "OK")
					.userAgent(userAgent)
					.timeout(0)
					.cookies(response.cookies())
					.method(Connection.Method.POST)
					.execute();
			loginPage = response.parse();
			String maSV = loginPage.getElementById("ctl00_ContentPlaceHolder1_ctl00_ucThongTinSV_lblMaSinhVien").text();
			String hoTen = loginPage.getElementById("ctl00_ContentPlaceHolder1_ctl00_ucThongTinSV_lblTenSinhVien").text();
			System.out.println(maSV + " - " + hoTen);
			// Lấy điểm học kỳ bất kỳ
			String url1 = "http://thongtindaotao.sgu.edu.vn/Default.aspx?page=xemdiemthi&id=3116330016";
			response = Jsoup.connect(url1)
					.method(Connection.Method.GET)
					.execute();
			response = Jsoup.connect(url1)
					.data("__EVENTTARGET", "")
					.data("__EVENTARGUMENT", "")
					.data("__VIEWSTATE", loginPage.getElementById("__VIEWSTATE").val())
					.data("__VIEWSTATEGENERATOR", loginPage.getElementById("__VIEWSTATEGENERATOR").val())
					.data("ctl00$ContentPlaceHolder1$ctl00$txtChonHK", "20192")
					.data("ctl00$ContentPlaceHolder1$ctl00$btnChonHK", "Xem")
					.userAgent(userAgent)
					.timeout(0)
					.cookies(response.cookies())
					.method(Connection.Method.POST)
					.execute();

			Document doc = response.parse();

			System.out.println(doc.getElementsByClass("row-diemTK").html());
		} catch (IOException e) { System.out.println(e);}

	}
}
