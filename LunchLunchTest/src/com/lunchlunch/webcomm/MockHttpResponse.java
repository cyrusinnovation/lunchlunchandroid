package com.lunchlunch.webcomm;

import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;

public class MockHttpResponse implements HttpResponse {

	private HttpEntity entityToReturn = new MockHttpEntity();
	private StatusLine statusLine;

	@Override
	public void addHeader(Header arg0) {

	}

	@Override
	public void addHeader(String arg0, String arg1) {

	}

	@Override
	public boolean containsHeader(String arg0) {
		return false;
	}

	@Override
	public Header[] getAllHeaders() {
		return null;
	}

	@Override
	public Header getFirstHeader(String arg0) {
		return null;
	}

	@Override
	public Header[] getHeaders(String arg0) {
		return null;
	}

	@Override
	public Header getLastHeader(String arg0) {
		return null;
	}

	@Override
	public HttpParams getParams() {
		return null;
	}

	@Override
	public ProtocolVersion getProtocolVersion() {
		return null;
	}

	@Override
	public HeaderIterator headerIterator() {
		return null;
	}

	@Override
	public HeaderIterator headerIterator(String arg0) {
		return null;
	}

	@Override
	public void removeHeader(Header arg0) {

	}

	@Override
	public void removeHeaders(String arg0) {

	}

	@Override
	public void setHeader(Header arg0) {

	}

	@Override
	public void setHeader(String arg0, String arg1) {

	}

	@Override
	public void setHeaders(Header[] arg0) {

	}

	@Override
	public void setParams(HttpParams arg0) {

	}

	@Override
	public HttpEntity getEntity() {
		return entityToReturn;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public StatusLine getStatusLine() {
		return statusLine;
	}

	@Override
	public void setEntity(HttpEntity arg0) {
		entityToReturn = arg0;
	}

	@Override
	public void setLocale(Locale arg0) {

	}

	@Override
	public void setReasonPhrase(String arg0) throws IllegalStateException {

	}

	@Override
	public void setStatusCode(int arg0) throws IllegalStateException {
		this.statusLine = new BasicStatusLine(new ProtocolVersion("", 0, 0),
				arg0, "reason");

	}

	@Override
	public void setStatusLine(StatusLine arg0) {

	}

	@Override
	public void setStatusLine(ProtocolVersion arg0, int arg1) {

	}

	@Override
	public void setStatusLine(ProtocolVersion arg0, int arg1, String arg2) {

	}

}
