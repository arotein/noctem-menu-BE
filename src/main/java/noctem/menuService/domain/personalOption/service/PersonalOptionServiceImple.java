package noctem.menuService.domain.personalOption.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.personalOption.dto.PersonalOptionListResDto;
import noctem.menuService.domain.personalOption.dto.PersonalOptionReqDto;
import noctem.menuService.domain.personalOption.dto.PersonalOptionResDto;
import noctem.menuService.domain.personalOption.entity.PersonalOptionEntity;
import noctem.menuService.domain.personalOption.repository.IPersonalOptionRepository;
import noctem.menuService.domain.size.dto.SizeReqDto;
import noctem.menuService.domain.size.dto.SizeResDto;
import noctem.menuService.domain.size.entity.SizeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonalOptionServiceImple implements IPersonalOptionService{

    private final IPersonalOptionRepository iPersonalOptionRepository;

    /*
        1. 퍼스널 옵션 등록
        2. 퍼스널 옵션 수정
        3. 퍼스널 옵션 삭제
        4. 퍼스널 옵션 전체 조회
        5. 퍼스널 옵션 단건 조회
        6. 메뉴-퍼스널 옵션 리스트 조회
     */

    // 1. 퍼스널 옵션 등록
    @Override
    public PersonalOptionReqDto addPersonalOption(PersonalOptionReqDto personalOptionReqDto) {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        PersonalOptionEntity personalOptionEntity = mapper.map(personalOptionReqDto, PersonalOptionEntity.class); // SizeDto -> SizeEntity
        iPersonalOptionRepository.save(personalOptionEntity);

        PersonalOptionReqDto resultPersonalOptionReqDto = mapper.map(personalOptionEntity, PersonalOptionReqDto.class); // SizeEntity -> SizeDto

        return resultPersonalOptionReqDto;
    }

    // 2. 퍼스널 옵션 수정
    @Override
    public PersonalOptionReqDto editPersonalOption(Long personalOptionId, PersonalOptionReqDto personalOptionReqDto) {

        Optional<PersonalOptionEntity> personalOptionEntity = iPersonalOptionRepository.findById(personalOptionId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(personalOptionEntity.isPresent()){
            PersonalOptionEntity personalOption = mapper.map(personalOptionReqDto, PersonalOptionEntity.class); // SizeDto -> SizeEntity
            personalOption.setId(personalOptionId); // 동일 id로 수정
            iPersonalOptionRepository.save(personalOption); // 엔터티 저장

            PersonalOptionReqDto resultPersonalOptionReqDto = mapper.map(personalOption, PersonalOptionReqDto.class); // SizeEntity -> SizeDto

            return resultPersonalOptionReqDto;
        }
        return null;
    }

    // 3. 퍼스널 옵션 삭제
    @Override
    public PersonalOptionResDto deletePersonalOption(Long personalOptionId, PersonalOptionResDto personalOptionResDto) {
        Optional<PersonalOptionEntity> personalOptionEntity = iPersonalOptionRepository.findById(personalOptionId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(personalOptionEntity.isPresent()){
            personalOptionEntity.get().setIsDeleted(true); // 삭제여부 true

            iPersonalOptionRepository.save(personalOptionEntity.get());
            PersonalOptionResDto resultPersonalOptionResDto = mapper.map(personalOptionEntity, PersonalOptionResDto.class);

            return resultPersonalOptionResDto;
        }
        return null;
    }

    // 4. 퍼스널 옵션 전체 조회
    @Override
    public Iterable<PersonalOptionResDto> getAllPersonalOption(PersonalOptionResDto personalOptionResDto) {
        List<PersonalOptionEntity> personalOptionEntityList = iPersonalOptionRepository.findAll();

        return personalOptionEntityList.stream().map(e ->
                        new PersonalOptionResDto(e.getOptionType(), e.getOptionName(), e.getAmount(), e.getExtraCost(), e.getEssential()))
                .collect(Collectors.toList());
    }

    // 5. 퍼스널 옵션 단건 조회
    @Override
    public PersonalOptionResDto getOnePersonalOption(Long personalOptionId) {
        Optional<PersonalOptionEntity> personalOptionEntity = iPersonalOptionRepository.findById(personalOptionId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (personalOptionEntity.isPresent() && personalOptionEntity.get().getIsDeleted() == false) { // 논리삭제가 false인 것
            PersonalOptionResDto personalOptionResDto = mapper.map(personalOptionEntity, PersonalOptionResDto.class); // SizeEntity -> SizeDto
            return personalOptionResDto;
        }
        return null;
    }

    // 6. 메뉴-퍼스널 옵션 리스트 조회
    @Override
    public List<PersonalOptionListResDto> getPersonalOptionListByMenu(Long menuId) {

        List<PersonalOptionEntity> personalOptionListByMenu = iPersonalOptionRepository.findPersonalOptionListByMenu(menuId);

        log.info("menuId : ", personalOptionListByMenu.get(0));
        System.out.println("personalOptionListByMenu.get(0) = " + personalOptionListByMenu.get(0));

        List<PersonalOptionListResDto> personalOptionListResDtos = new ArrayList<>();

        personalOptionListByMenu.stream().map(e ->
                    new PersonalOptionResDto(e.getOptionType(), e.getOptionName(), e.getAmount(),
                            e.getExtraCost(), e.getEssential()))
                .collect(Collectors.toList());

        return personalOptionListResDtos;
    }
}
