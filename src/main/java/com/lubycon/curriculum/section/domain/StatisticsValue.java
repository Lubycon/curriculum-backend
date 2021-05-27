package com.lubycon.curriculum.section.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StatisticsValue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "keyword", nullable = false)
  private String keyword;

  @Column(name = "value", nullable = false)
  private String value;

  @Column(name = "course_topic")
  private boolean courseTopic;

  @ManyToOne
  @JoinColumn(name = "statisics_info_id", referencedColumnName = "id", insertable = false, updatable = false)
  private StatisticsInfo statisticsInfo;
}
