package com.andes.preat.service.hateFood;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HateFoodService {
    // TODO : 유저별 싫어하는 음식 선택 결과 조회
    // TODO : 싫어하는 음식 선택지 조회
    // TODO : 싫어하는 음식 저장
    // TODO : 싫어하는 음식 업데이트
}
