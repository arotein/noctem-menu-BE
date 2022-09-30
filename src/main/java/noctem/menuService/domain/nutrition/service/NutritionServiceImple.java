package noctem.menuService.domain.nutrition.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.nutrition.dto.NutritionResDto;
import noctem.menuService.domain.nutrition.entity.NutritionEntity;
import noctem.menuService.domain.nutrition.repository.INutritionRepository;
import noctem.menuService.domain.size.dto.SizeResDto;
import noctem.menuService.domain.size.entity.SizeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NutritionServiceImple implements INutritionService{

    private INutritionRepository inutritionRepository;

    /*
        1. 영양정보 등록
        2. 영양정보 수정
        3. 영양정보 삭제
        4. 영양정보 전체 조회
        5. 영양정보 단건 조회
     */

    // 4. 영양정보 전체 조회
    @Override
    public List<NutritionResDto> getAllNutrition() {
        List<NutritionEntity> nutritionEntityList = inutritionRepository.findAll();

//        List<NutritionResDto> nutritionResDtoList = new ArrayList<>(); // 비어 있는 sizeDto 리스트 선언
//
//        nutritionEntityList.forEach(nutritionEntity -> {
//            if (nutritionEntity.getIsDeleted() == false)
//                nutritionResDtoList.add(new ModelMapper().map(nutritionEntity, NutritionResDto.class));
//        });
//
//        return nutritionResDtoList;

        System.out.println("nutritionEntityList = " + nutritionEntityList);
        log.info("nutritionEntityList = ", nutritionEntityList);

        return nutritionEntityList.stream().map(e ->
                new NutritionResDto(e.getId(), e.getKcal(), e.getCarbohydrates(), e.getSugars(), e.getSodium(),
                        e.getProtein(), e.getFat(), e.getCholesterol(), e.getTransFat(), e.getCaffeine(),
                        e.getSaturatedFat()))
                .collect(Collectors.toList());
    }

    // 5. 영양정보 단건 조회
    @Override
    public NutritionResDto getOneNutrition(Long nutritionId) {
        Optional<NutritionEntity> n = inutritionRepository.findById(nutritionId);

        if(n.isPresent())
            return new NutritionResDto(n.get().getId(), n.get().getKcal(), n.get().getCarbohydrates(), n.get().getSugars()
            , n.get().getSodium(), n.get().getProtein(), n.get().getFat(), n.get().getCholesterol(), n.get().getTransFat()
            , n.get().getCaffeine(), n.get().getSaturatedFat());

        return null;
    }
}
