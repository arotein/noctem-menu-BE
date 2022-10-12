package noctem.menuService.domain.size.controller;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.size.dto.SizeByTempResDto;
import noctem.menuService.domain.size.dto.SizeReqDto;
import noctem.menuService.domain.size.service.ISizeService;
import noctem.menuService.global.common.CommonResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu-service")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SizeController {

    private final ISizeService iSizeService;

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
        6. 온도-사이즈 리스트 조회
        7. 사이즈-메뉴 조회(BE)
     */

    // 1. 사이즈 등록
    @PostMapping("/size")
    public CommonResponse addSize(@RequestBody SizeReqDto sizeReqDto){
        return CommonResponse.builder()
                .data(iSizeService.addSize(sizeReqDto))
                .build();
    }

    // 2. 사이즈 수정
    @PutMapping("/size/{sizeId}")
    public CommonResponse editSize(@PathVariable Long sizeId, SizeReqDto sizeReqDto){
        return CommonResponse.builder()
                .data(iSizeService.editSize(sizeId, sizeReqDto))
                .build();
    }

    // 3. 사이즈 삭제
    @DeleteMapping("/size/{sizeId}")
    public CommonResponse deleteSize(@PathVariable Long sizeId){
        return CommonResponse.builder()
                .data(iSizeService.deleteSize(sizeId))
                .build();
    }

    // 4. 사이즈 전체 조회
    @GetMapping("/size")
    public CommonResponse getAllSize(){
        return CommonResponse.builder()
                .data(iSizeService.getAllSize())
                .build();
    }

    // 5. 사이즈 단건 조회
    @GetMapping("/size/{sizeId}")
    public CommonResponse getOneSize(@PathVariable Long sizeId){
        return CommonResponse.builder()
                .data(iSizeService.getOneSize(sizeId))
                .build();
    }

    // 6. 온도-사이즈 리스트 조회
    @GetMapping("/{temperatureId}/size")
    public CommonResponse getSizeListByTemperature(@PathVariable Long temperatureId){

        List<SizeByTempResDto> sizeByTempResDtoList = iSizeService.getSizeListByTemperature(temperatureId);
        sizeByTempResDtoList.forEach(e -> e.setIndex(sizeByTempResDtoList.indexOf(e)));

        return CommonResponse.builder()
                .data(sizeByTempResDtoList)
                .build();
    }

    // 7. 사이즈-메뉴 조회(BE)
    @GetMapping("/size/menu/{sizeId}")
    public CommonResponse getSizeMenu(@PathVariable Long sizeId){
        return CommonResponse.builder()
                .data(iSizeService.getSizeMenu(sizeId))
                .build();
    }
}
