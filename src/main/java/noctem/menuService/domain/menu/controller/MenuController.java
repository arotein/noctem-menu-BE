package noctem.menuService.domain.menu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.menu.dto.CartAndOptionsReqServDto;
import noctem.menuService.domain.menu.dto.MenuDetailResDto;
import noctem.menuService.domain.menu.dto.MenuDto;
import noctem.menuService.domain.menu.dto.MenuListResDto;
import noctem.menuService.domain.menu.dto.vo.RequestMenu;
import noctem.menuService.domain.menu.dto.vo.ResponseMenu;
import noctem.menuService.domain.menu.service.IMenuService;
import noctem.menuService.domain.size.repository.ISizeRepository;
import noctem.menuService.global.common.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menu-service")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    private final IMenuService iMenuService;
    private final ISizeRepository iSizeRepository;

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
        6. 소카테고리-메뉴 조회
        7-1. 장바구니 목록 조회 (리스트)
        7-2. 장바구니 목록 조회 (requestparam)
        8. 메뉴 하위 정보 상세 조회
        9. 소 카테고리 - 메뉴 조회(관리자용)
        10. 메뉴 검색 (이름)
     */

    // 1. 메뉴 등록
    @PostMapping("/menu")
    public ResponseEntity<ResponseMenu> addMenu(@RequestBody RequestMenu requestMenu){

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        MenuDto menuDto = modelMapper.map(requestMenu, MenuDto.class); // RequstMenu -> MenuDto
        iMenuService.addMenu(menuDto);

        ResponseMenu responseMenu = modelMapper.map(menuDto, ResponseMenu.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMenu);
    }

    // 2. 메뉴 수정
    @PutMapping("/menu/{menuId}")
    public ResponseEntity<ResponseMenu> editMenu(@PathVariable Long menuId,
                                                 @RequestBody RequestMenu requestMenu) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        MenuDto menuDto = modelMapper.map(requestMenu, MenuDto.class); // RequestMenu -> MenuDto
        iMenuService.editMenu(menuId, menuDto);

        ResponseMenu responseMenu = modelMapper.map(menuDto, ResponseMenu.class); // MenuDto -> ResponseMenu

        return ResponseEntity.status(HttpStatus.OK).body(responseMenu);
    }

    // 3. 메뉴 삭제
    @DeleteMapping("/menu/{menuId}")
    public ResponseEntity<ResponseMenu> deleteMenu(@PathVariable Long menuId) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        MenuDto menuDto = iMenuService.deleteMenu(menuId);

        ResponseMenu responseMenu = modelMapper.map(menuDto, ResponseMenu.class); // MenuDto -> ResponseMenu

        return ResponseEntity.status(HttpStatus.OK).body(responseMenu);
    }

    // 4. 메뉴 전체 조회
    @GetMapping("/menu")
    public ResponseEntity<List<ResponseMenu>> getAllMenu(){

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        Iterable<MenuDto> menuDtoIterable = iMenuService.getAllMenu();
        List<ResponseMenu> responseMenuList = new ArrayList<>();

        menuDtoIterable.forEach(menuDto -> { // MenuDto -> ResponseMenu
            responseMenuList.add(modelMapper.map(menuDto, ResponseMenu.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseMenuList);
    }

    // 5. 메뉴 단건 조회
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<ResponseMenu> getOneMenu(@PathVariable Long menuId) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        MenuDto menuDto = iMenuService.getOneMenu(menuId);

        ResponseMenu responseMenu = modelMapper.map(menuDto, ResponseMenu.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseMenu);
    }

    // 6. 소카테고리-메뉴 조회
    @GetMapping("/{categorySId}/menu")
    public CommonResponse getMenuList(@PathVariable Long categorySId) throws JsonProcessingException {

        List<MenuListResDto> menuList = iMenuService.getMenuList(categorySId);
        menuList.forEach(e -> e.setIndex(menuList.indexOf(e)));

        return CommonResponse.builder()
                .data(menuList)
                .build();
    }

    // 7-1. 장바구니 목록 조회 (리스트)
    @GetMapping("/menu/cart")
    public CommonResponse getCartAndOptions(@RequestBody List<CartAndOptionsReqServDto> cartAndOptionsReqServDto) {

        return CommonResponse.builder()
                .data(iMenuService.getMenuCartList(cartAndOptionsReqServDto))
                .build();
    }

    // 7-2. 장바구니 목록 조회 (requestparam)
    @GetMapping("/menu/cart/{cartOrMyMenuId}/{sizeId}")
    public CommonResponse getCartAndOptions(
            @PathVariable Long cartOrMyMenuId,
            @PathVariable Long sizeId,
            @RequestParam List<Long> optionIdList) {
        return CommonResponse.builder()
                .data(iMenuService.getMenuCart(cartOrMyMenuId, sizeId, optionIdList))
                .build();
    }

    // 8. 메뉴 하위 정보 상세 조회
    @GetMapping("product/{menuId}")
    public CommonResponse getProductDetail(@PathVariable Long menuId){


//        menuDetail.forEach(e -> e.setIndex(menuList.indexOf(e)));

        return CommonResponse.builder()
                .data(iMenuService.getMenuDetail(menuId))
                .build();
    }

    // 9. 소 카테고리 - 메뉴 조회(관리자용)
    @GetMapping("/admin/menu/{categorySId}")
    public CommonResponse getMenuByCategoryS(@PathVariable Long categorySId){
        return CommonResponse.builder()
                .data(iMenuService.getMenuForAdmin(categorySId))
                .build();
    }

    // 10. 메뉴 검색 (이름)
    @GetMapping("/menu/searchName")
    public CommonResponse getMenuSearchByName(String searchKeyword){

        List<MenuListResDto> MenuListResDtoList = iMenuService.getMenuListSearchByName(searchKeyword);
        MenuListResDtoList.forEach(e -> e.setIndex(MenuListResDtoList.indexOf(e)));

        return CommonResponse.builder()
                .data(MenuListResDtoList)
                .build();
    }
}
