package com.jds.mc.application_api.model.res;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * code 请求状态码
 *
 * <p>statusCode 信息状态码
 *
 * <p>msg 信息
 *
 * <p>data 数据 <T>
 */
@Data
@SuppressWarnings("all")
public class Result<T> {

  private int code;

  private String msg;

  private T data;

  private int pageIndex;

  private int pageSize;

  private int status;

  private static final int DEFAULT_STATUS_CODE = 0;

  public Result(int code, int status, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.status = status;
    this.data = data;
  }

  public Result(int code, int status, String msg, T data, int pageIndex, int pageSize) {
    this.code = code;
    this.msg = msg;
    this.data = data;
    this.status = status;
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
  }

  public Result() {}

  public Result<T> success(T data) {
    this.code = Constants.ResponseCode.REQUEST_SUCCESS.getCode();
    this.msg = Constants.ResponseMessage.REQUEST_SUCCESS.getMessage();
    this.data = data;
    return this;
  }

  public Result<T> success(T data, int pageIndex, int pageSize) {
    this.code = Constants.ResponseCode.REQUEST_SUCCESS.getCode();
    this.msg = Constants.ResponseMessage.REQUEST_SUCCESS.getMessage();
    this.data = data;
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
    return this;
  }

  public Result<T> customized(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
    return this;
  }

  public Result<T> customized(int code, String msg, T data, int pageIndex, int pageSize) {
    this.code = code;
    this.msg = msg;
    this.data = data;
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
    return this;
  }

  public Result<T> fail(String errorMsg) {
    this.code = Constants.ResponseCode.REQUEST_FAILED.getCode();
    this.msg = errorMsg;
    this.data = null;
    return this;
  }

  public static String build(int code, int statusCode, String msg) {
    Result<String> resultJsonUtil = new Result<>(code, statusCode, msg, "");
    return resultJsonUtil.getResultJson();
  }

  public static String build(int code, String msg) {
    return Result.build(code, Result.DEFAULT_STATUS_CODE, msg);
  }

  public static String build(int code, int statusCode, String msg, JsonArray data) {
    Result<JsonArray> resultJsonUtil = new Result<>(code, statusCode, msg, data);
    return resultJsonUtil.getResultJson();
  }

  public static String build(int code, String msg, JsonArray data) {
    return Result.build(code, Result.DEFAULT_STATUS_CODE, msg, data);
  }

  public static String build(int code, int statusCode, String msg, Map<String, Object> data) {
    JsonObject jsonObjectData = new Gson().toJsonTree(data).getAsJsonObject();
    Result<JsonObject> resultJsonUtil = new Result<>(code, statusCode, msg, jsonObjectData);
    return resultJsonUtil.getResultJson();
  }

  public static String build(int code, String msg, Map<String, Object> data) {
    return Result.build(code, Result.DEFAULT_STATUS_CODE, msg, data);
  }

  public static String build(int code, int statusCode, String msg, List<?> data) {
    JsonArray jsonArrayData = new Gson().toJsonTree(data).getAsJsonArray();
    return Result.build(code, statusCode, msg, jsonArrayData);
  }

  public static String build(int code, String msg, List<?> data) {
    return Result.build(code, Result.DEFAULT_STATUS_CODE, msg, data);
  }

  public String getResultJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("code", this.code);
    jsonObject.addProperty("msg", this.msg);
    jsonObject.addProperty("pageIndex", this.pageIndex);
    jsonObject.addProperty("pageSize", this.pageSize);

    if (this.data != null) {
      Gson gson = new Gson();
      JsonElement jsonData = gson.toJsonTree(this.data);
      jsonObject.add("data", jsonData);
    }

    return jsonObject.toString();
  }

  //  private String getResultJson() {
  //    JsonObject jsonObject = new JsonObject();
  //    jsonObject.addProperty("code", this.code);
  //    jsonObject.addProperty("msg", this.msg);
  //
  //    if (this.data != null) {
  //      Gson gson = new Gson();
  //      String jsonData = gson.toJson(this.data);
  //
  //      if (this.data instanceof List) {
  //        JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
  //        jsonObject.add("data", jsonArray);
  //      } else if (this.data instanceof Map) {
  //        JsonObject jsonObj = gson.fromJson(jsonData, JsonObject.class);
  //        jsonObject.add("data", jsonObj);
  //      } else {
  //        JsonObject jsonObj = gson.fromJson(jsonData, JsonObject.class);
  //        jsonObject.add("data", jsonObj);
  //      }
  //    }
  //    return jsonObject.toString();
  //  }
}
