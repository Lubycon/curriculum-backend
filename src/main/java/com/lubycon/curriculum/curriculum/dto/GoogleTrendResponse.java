package com.lubycon.curriculum.curriculum.dto;

import com.lubycon.curriculum.section.domain.GoogleTrend;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class GoogleTrendResponse {

  @NotNull
  private final String title;

  @NotNull
  private final String csvHtml;

  public GoogleTrendResponse(final GoogleTrend googleTrend) {
    this.title = googleTrend.getTitle();
    this.csvHtml = googleTrend.getCsvHtml();
  }
}
