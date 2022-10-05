package noctem.menuService.domain.categoryL.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.categoryL.dto.CategoryLDto;
import noctem.menuService.domain.categoryL.dto.CategoryLResDto;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import noctem.menuService.domain.categoryL.repository.ICategoryLRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryLServiceImple implements ICategoryLService{

    private final ICategoryLRepository iCategoryLRepository;

    /*
        1. 대 카테고리 등록
        2. 대 카테고리 수정
        3. 대 카테고리 전체 조회
        4. 대 카테고리 단건 조회
     */

    // 1. 대 카테고리 등록
    @Override
    public CategoryLDto addCategoryL(CategoryLDto categoryLDto) {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategoryLEntity categoryLEntity = mapper.map(categoryLDto, CategoryLEntity.class); // CategoryLDto -> CategoryLEntity
        iCategoryLRepository.save(categoryLEntity); // 엔터티 저장

        CategoryLDto resultCategoryLDto = mapper.map(categoryLEntity, CategoryLDto.class); // CategoryLEntity -> CategoryLDto

        return resultCategoryLDto;
    }

    // 2. 대 카테고리 수정
    @Override
    public CategoryLDto editCategoryL(Long categoryLId, CategoryLDto categoryLDto) throws Exception {

        Optional<CategoryLEntity> categoryLEntity = iCategoryLRepository.findById(categoryLId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(categoryLEntity.isPresent()){

            CategoryLEntity categoryL = mapper.map(categoryLDto, CategoryLEntity.class); // CategoryLDto -> CategoryLEntity
            categoryL.setId(categoryLId); // 동일 id로 수정
            iCategoryLRepository.save(categoryL); // 엔터티 저장

            CategoryLDto resultCategoryLDto = mapper.map(categoryL, CategoryLDto.class); // CategoryLEntity -> CategoryLDto
            return resultCategoryLDto;

        } else {
            throw new Exception("해당하는 대 카테고리 id가 없습니다.");
        }
    }

    // 3. 대 카테고리 전체 조회
    @Override
    public List<CategoryLResDto> getAllCategoryL() {

        List<CategoryLEntity> categoryLEntityList = iCategoryLRepository.findAll();
        List<CategoryLResDto> categoryLResDtoList = new ArrayList<>(); // 비어있는 categoryLDtoList 선언

        categoryLEntityList.forEach(categoryLEntity -> { // CategoryLEntity -> CategoryLDto
            categoryLResDtoList.add(new ModelMapper().map(categoryLEntity, CategoryLResDto.class));
        });

        return categoryLResDtoList;
    }

    // 4. 대 카테고리 단건 조회
    @Override
    public CategoryLDto getOneCategoryL(Long categoryLId) throws Exception {

        Optional<CategoryLEntity> categoryLEntity = iCategoryLRepository.findById(categoryLId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(categoryLEntity.isPresent()){
            CategoryLDto categoryLDto = mapper.map(categoryLEntity, CategoryLDto.class);
            return categoryLDto;
        } else {
            throw new Exception("해당하는 대 카테고리 id가 없습니다.");
        }
    }
}
