package noctem.menuService.domain.personalOption.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.personalOption.dto.PersonalOptionListResDto;
import noctem.menuService.domain.personalOption.service.IPersonalOptionService;
import noctem.menuService.global.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menu-service")
@Slf4j
public class PersonalOptionController {

    private IPersonalOptionService iPersonalOptionService;

    /*
        6. 메뉴-퍼스널 옵션 리스트 조회
     */

    // 6. 메뉴-퍼스널 옵션 리스트 조회
    @GetMapping("/{menuId}/personalOption")
    public CommonResponse getPersonalOptionByMenuId(@PathVariable Long menuId){

        List<PersonalOptionListResDto> personalOptionListByMenu = iPersonalOptionService.getPersonalOptionListByMenu(menuId);
        personalOptionListByMenu.forEach(e -> e.setIndex(personalOptionListByMenu.indexOf(e)));

        return CommonResponse.builder()
                .data(personalOptionListByMenu)
                .build();
    }

}
