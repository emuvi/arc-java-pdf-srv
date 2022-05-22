package br.net.pin.pdf_srv;

import com.google.gson.Gson;

public class ForExtract {
  public String path;
  public Integer textsOfPage;
  public Integer imageOfPage;

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  public static ForExtract fromString(String json) {
    return new Gson().fromJson(json, ForExtract.class);
  }
}
