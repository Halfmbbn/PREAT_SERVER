package com.andes.preat.presentation.hatefood;

import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.service.hateFood.HateFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dislikes")
public class HateFoodController {
    private final HateFoodService hateFoodService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getHateFoods() {
        List<HateFoodsResponse> hateFoods = hateFoodService.getHateFoods();
        return ResponseEntity.ok().body(new BaseResponse(hateFoods));
    }
}
