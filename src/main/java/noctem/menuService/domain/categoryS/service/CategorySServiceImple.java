package noctem.menuService.domain.categoryS.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.categoryS.dto.CategorySDto;
import noctem.menuService.domain.categoryS.dto.CategorySResDto;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.domain.categoryS.repository.ICategorySRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySServiceImple implements ICategorySService {

    private final ICategorySRepository iCategorySRepository;

    /*
        1. 소 카테고리 등록
        2. 소 카테고리 수정
        3. 소 카테고리 삭제
        4. 소 카테고리 전체 조회
        5. 소 카테고리 단건 조회
        6. 대 카테고리 - 소 카테고리 리스트 조회
     */

    // 1. 소 카테고리 등록
    @Override
    public CategorySDto addCategoryS(CategorySDto categorySDto) {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategorySEntity categorySEntity = mapper.map(categorySDto, CategorySEntity.class); // CategorySDto -> CategorySEntity
        iCategorySRepository.save(categorySEntity); // 엔터티 저장

        CategorySDto resultCategorySDto = mapper.map(categorySEntity, CategorySDto.class); // CategorySEntity -> CategorySDto

        return resultCategorySDto;
    }

    // 2. 소 카테고리 수정
    @Override
    public CategorySDto editCategoryS(Long categorySId, CategorySDto categorySDto) throws Exception {

        Optional<CategorySEntity> categorySEntity = iCategorySRepository.findById(categorySId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(categorySEntity.isPresent()){
            CategorySEntity categoryS = mapper.map(categorySDto, CategorySEntity.class); // CategorySDto -> CategorySEntity
            categoryS.setId(categorySId); // 동일 id로 수정
            iCategorySRepository.save(categoryS); // 엔터티 저장

            CategorySDto resultCategorySDto = mapper.map(categoryS, CategorySDto.class);

            return resultCategorySDto;
        } else {
            throw new Exception("해당하는 소 카테고리 id가 없습니다.");
        }
    }

    // 3. 소 카테고리 삭제
    @Override
    public CategorySDto deleteCategoryS(Long categorySId) throws Exception {

        Optional<CategorySEntity> categorySEntity = iCategorySRepository.findById(categorySId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(categorySEntity.isPresent()){
            categorySEntity.get().setIsDeleted(true); // 삭제여부 true

            iCategorySRepository.save(categorySEntity.get());
            CategorySDto categorySDto = mapper.map(categorySEntity, CategorySDto.class); // CategorySEntity -> CategorySDto

            return categorySDto;
        } else {
            throw new Exception("해당하는 소 카테고리 id가 없습니다.");
        }
    }

    // 4. 소 카테고리 전체 조회
    @Override
    public Iterable<CategorySDto> getAllCategoryS() {

        List<CategorySEntity> categorySEntityList = iCategorySRepository.findAll();
        List<CategorySDto> categorySDtoList = new ArrayList<>(); // 비어있는 categorySDtoList 선언

        categorySEntityList.forEach(categorySEntity -> { // CategorySEntity -> CategorySDto

            if(categorySEntity.getIsDeleted() == false) // 논리삭제가 false인 것
                categorySDtoList.add(new ModelMapper().map(categorySEntity, CategorySDto.class));
        });

        return categorySDtoList;
    }

    // 5. 소 카테고리 단건 조회
    @Override
    public CategorySDto getOneCategoryS(Long categorySId) throws Exception {

        Optional<CategorySEntity> categorySEntity = iCategorySRepository.findById(categorySId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(categorySEntity.isPresent() && categorySEntity.get().getIsDeleted() == false){ // 논리삭제가 false인 것
            CategorySDto categorySDto = mapper.map(categorySEntity, CategorySDto.class); // CategoreySEntity -> CategorySDto

            return categorySDto;
        } else {
            throw new Exception("해당하는 소 카테고리 id가 없습니다.");
        }
    }

    // 6. 대 카테고리 - 소 카테고리 리스트 조회
    @Override
    public List<CategorySResDto> getCategorySListByCategoryL(Long categoryLId) {
        List<CategorySEntity> categorySListByCategoryL = iCategorySRepository.findCategorySListByCategoryL(categoryLId);

        return categorySListByCategoryL.stream().map(e ->
            new CategorySResDto(e.getCategorySName(), e.getCategorySImg()))
                .collect(Collectors.toList());
    }
}
