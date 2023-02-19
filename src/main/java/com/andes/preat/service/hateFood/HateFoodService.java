package com.andes.preat.service.hateFood;

import com.andes.preat.domain.hatefood.HateFood;
import com.andes.preat.domain.hatefood.HateFoodRepository;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HateFoodService {
    private final HateFoodRepository hateFoodRepository;

    // TODO : 싫어하는 음식 선택지 조회 - DONE
    public List<HateFoodsResponse> getHateFoods() {
        List<HateFood> hateFoods = hateFoodRepository.findAll();
        List<HateFoodsResponse> hateFoodsResponses = hateFoods.stream()
                .map(f -> HateFoodsResponse.from(f))
                .collect(Collectors.toList());
        return hateFoodsResponses;
    }
}
