package com.andes.preat.service.restaurant;

import com.andes.preat.domain.hatefood.HateFood;
import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.restaurant.RestaurantRepository;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.dto.response.search.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    // TODO : 식당 선택지 조회 - 모든 리스트 - page 적용 필요
    public List<RestaurantInfoResponse> findAll() {
        List<Restaurant> foundRestaurants = restaurantRepository.findTop10By();
        List<RestaurantInfoResponse> restaurantInfoResponses = foundRestaurants.stream()
                .map(r -> RestaurantInfoResponse.from(r))
                .collect(Collectors.toList());
        return restaurantInfoResponses;
    }
    // TODO: 싫어하는 음식 검색
    public SearchResponse findByRestaurantNameContaining(final String keyword) {
        List<Restaurant> searchResults = restaurantRepository.findByNameContaining(keyword);
        if (searchResults.isEmpty()) {
            return SearchResponse.from(Collections.emptyList());
        }
        List<RestaurantInfoResponse> restaurantInfoResponses = searchResults.stream()
                .map(r -> RestaurantInfoResponse.from(r))
                .collect(Collectors.toList());
        return SearchResponse.from(restaurantInfoResponses);
    }

}
