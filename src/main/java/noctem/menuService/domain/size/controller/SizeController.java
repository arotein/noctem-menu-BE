package noctem.menuService.domain.size.controller;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.size.dto.SizeDto;
import noctem.menuService.domain.size.service.ISizeService;
import noctem.menuService.domain.temperature.dto.TemperatureDto;
import noctem.menuService.domain.temperature.dto.vo.RequestTemperature;
import noctem.menuService.domain.temperature.dto.vo.ResponseTemperature;
import noctem.menuService.global.common.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu-service")
public class SizeController {

    private final ISizeService iSizeService;

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
     */

    // 1. 사이즈 등록
    @PostMapping("/size")
    public CommonResponse addSize(@RequestBody SizeDto sizeDto){
        return CommonResponse.builder()
                .data(iSizeService.addSize(sizeDto))
                .build();
    }

    // 2. 사이즈 수정
    @PutMapping("/size")
    public CommonResponse editSize(@PathVariable Long sizeId, SizeDto sizeDto){
        return CommonResponse.builder()
                .data(iSizeService.editSize(sizeId, sizeDto))
                .build();
    }

    // 3. 사이즈 삭제
    @DeleteMapping("/size")
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
}
