package com.lubycon.curriculum.curriculum.service;

import com.lubycon.curriculum.curriculum.domain.Curriculum;
import com.lubycon.curriculum.curriculum.dto.CurriculumResponse;
import com.lubycon.curriculum.curriculum.dto.CurriculumSectionsResponse;
import com.lubycon.curriculum.curriculum.repository.CurriculumRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CurriculumService {

  private final CurriculumRepository curriculumRepository;

  @Transactional(readOnly = true)
  public List<CurriculumResponse> getCurriculums() {
    return findAll()
        .stream()
        .map(CurriculumResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CurriculumSectionsResponse getCurriculumSections(final long curriculumId) {
    final Curriculum curriculum = findById(curriculumId);
    return CurriculumSectionsResponse.toResponse(curriculum);
  }


  private List<Curriculum> findAll() {
    return curriculumRepository.findAll();
  }

  private Curriculum findById(final long id) {
    return curriculumRepository.findById(id)
        .orElseThrow(RuntimeException::new); // FIXME: 예외 바꾸기
  }

}
