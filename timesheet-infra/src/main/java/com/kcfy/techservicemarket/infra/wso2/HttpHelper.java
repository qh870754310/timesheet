package com.kcfy.techservicemarket.infra.wso2;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static byte[] downFile(String url) throws Exception {
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			conn.connect();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[1024];
			while ((len = conn.getInputStream().read(buffer)) != -1) {
				bout.write(buffer, 0, len);
			}
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();

			conn.setRequestMethod("POST");
			// 设置通用的请求属性

			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization",
					"Bearer 81ff4e92da4f1b9e615deaf432fcbb68");
			conn.setRequestProperty("Accept", "application/json");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			StringBuffer result = new StringBuffer();
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			// e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	public static String sendGet(String url) {
		URL restServiceURL;
		HttpURLConnection httpConnection = null;
		StringBuffer sb = new StringBuffer();
		try {
			restServiceURL = new URL(url);

			httpConnection = (HttpURLConnection) restServiceURL
					.openConnection();

			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;

			while ((output = responseBuffer.readLine()) != null) {

				sb.append(output);
			}

			httpConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpConnection != null)
				httpConnection.disconnect();
		}

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {

		// HttpHelper.sendGet("http://192.168.1.109:8080/public/rest/11");
		String url = "http://172.16.200.189:8280/cms/1.0/content/list";
		String param = "channel=cms_policy";
		System.out.println(HttpHelper.sendPost(url, param));
		// System.out.println(HttpHelper.sendPost("http://172.16.200.189:8280/cms/1.0/content/list",
		// "channelId=11"));
		// System.out.println(HttpHelper.sendPost("http://172.16.200.194:8080/public/rest/content/details",
		// "contentId=584"));

	}
}
