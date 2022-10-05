package noctem.menuService.domain.menu.controller;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.menu.dto.CartAndOptionsReqServDto;
import noctem.menuService.domain.menu.dto.MenuDto;
import noctem.menuService.domain.menu.dto.MenuListResDto;
import noctem.menuService.domain.menu.dto.vo.RequestMenu;
import noctem.menuService.domain.menu.dto.vo.ResponseMenu;
import noctem.menuService.domain.menu.service.IMenuService;
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

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
        6. 소카테고리-메뉴 조회
        7. 장바구니 목록 조회
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
    public CommonResponse getMenuList(@PathVariable Long categorySId) {

        List<MenuListResDto> menuList = iMenuService.getMenuList(categorySId);
        menuList.forEach(e -> e.setIndex(menuList.indexOf(e)));

        return CommonResponse.builder()
                .data(menuList)
                .build();
    }

    // 7. 장바구니 목록 조회
    @GetMapping("/menu/cart")
    public CommonResponse getCartAndOptions(@RequestBody List<CartAndOptionsReqServDto> cartAndOptionsReqServDto) {

        return CommonResponse.builder()
                .data(iMenuService.getMenuCart(cartAndOptionsReqServDto))
                .build();
    }
}
