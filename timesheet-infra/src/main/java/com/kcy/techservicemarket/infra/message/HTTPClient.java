package com.kcy.techservicemarket.infra.message;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HTTPClient {

	PoolingHttpClientConnectionManager cm =null;
	CloseableHttpClient httpClient = null;


	public HTTPClient() {
		cm= new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		httpClient=HttpClients.custom()
				.setConnectionManager(cm)
				.build();
	}

	public HttpResponse doPost(String url, String token, final String payload, String contentType) throws IOException {
		HttpUriRequest request = new HttpPost(url);
		addSecurityHeaders(request, token);
		HttpEntityEnclosingRequest entityEncReq = (HttpEntityEnclosingRequest) request;
		EntityTemplate ent = new EntityTemplate(new ContentProducer() {
			public void writeTo(OutputStream outputStream) throws IOException {
				outputStream.write(payload.getBytes("UTF-8"));
				outputStream.flush();
			}
		});
		ent.setContentType(contentType);
		entityEncReq.setEntity(ent);
		return httpClient.execute(request);
	}
	
	public HttpResponse doGet(String url, String token) throws IOException {
		HttpUriRequest request = new HttpGet(url);
		addSecurityHeaders(request, token);
		return httpClient.execute(request);
	}
	
	public String getResponsePayload(HttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity());
//		StringBuffer buffer = new StringBuffer();
//		InputStream in = null;
//		try {
//			if (response.getEntity() != null) {
//			    in = response.getEntity().getContent();
//			    int length;
//			    byte[] tmp = new byte[2048];
//			    while ((length = in.read(tmp)) != -1) {
//			        buffer.append(new String(tmp, 0, length));
//			    }
//			}
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} finally {
//			if (in != null) {
//				in.close();
//			}
//		}
//
//        return buffer.toString();
	}

	public HttpResponse doPut(String url, String token, final String payload, String contentType) throws IOException {
		HttpUriRequest request = new HttpPut(url);
		addSecurityHeaders(request, token);

		HttpEntityEnclosingRequest entityEncReq = (HttpEntityEnclosingRequest) request;
		EntityTemplate ent = new EntityTemplate(new ContentProducer() {
			public void writeTo(OutputStream outputStream) throws IOException {
				outputStream.write(payload.getBytes());
				outputStream.flush();
			}
		});
		ent.setContentType(contentType);
		entityEncReq.setEntity(ent);
		return httpClient.execute(request);
	}

	public HttpResponse doDelete(String url, String token) throws IOException {
		HttpUriRequest request = new HttpDelete(url);
		addSecurityHeaders(request, token);
		return httpClient.execute(request);
	}

	private void addSecurityHeaders(HttpRequest request, String token) {
		if (token != null) {
			request.setHeader(HttpHeaders.AUTHORIZATION, token);
		}
	}
}
