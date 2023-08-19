package net.java.grpc.client.response;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {

	private int status;
	private String message;
	private T data;

	public BaseResponse() {
		this.setStatus(HttpStatus.OK);
		this.setMessage(HttpStatus.OK);
		this.setData(null);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(HttpStatus statusEnum) {
		this.status = statusEnum.value();
		this.message = statusEnum.name();
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(HttpStatus statusEnum) {
		this.message = statusEnum.name();
	}

	public void setMessageError(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
