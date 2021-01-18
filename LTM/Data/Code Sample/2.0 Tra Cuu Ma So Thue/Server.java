package bai9v2;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;

// Lưu ý: bài này chỉ mới viết phần code chính, chưa tách client - server
public class Server {
	public static void main(String[] args) {
		try {
			String add = "https://masothue.vn";
			Document doc = Jsoup.connect(add + "/Ajax/Search")
					.data("q", "079090000555")
					.data("type", "auto")
					.userAgent("Mozilla").ignoreContentType(true).post();
			Gson json = new Gson();
			MaSoThue mst = json.fromJson(doc.body().text(), MaSoThue.class);
			if(mst.getSuccess()==0) {
				System.out.println("Không lấy được thông tin");
				return;
			}
			// Sent GET request
			doc = Jsoup.connect(add+mst.getURL()).get();
			String hoTen = doc.getElementsByTag("h1").text();
			String diaChi = doc.getElementsByAttributeValue("itemprop", "address").text();
			System.out.println(hoTen + " - " + diaChi);
		} catch (IOException e) {}
	}
}

class MaSoThue {
	private int success;
	private String type;
	private int typeId;
	private String url;

	public MaSoThue(int tmpsuccess, String tmptype, int tmptypeId, String tmpurl) {
		this.success = tmpsuccess;
		this.type = tmptype;
		this.typeId = tmptypeId;
		this.url = tmpurl;
	}

	public String getURL() {
		return url; 
	}
	public int getSuccess() {
		return success;
	}
}