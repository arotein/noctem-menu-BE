-- 대 카테고리
insert menu.categoryl
values(1, now(), 0, now(), '음료');
insert menu.categoryl
values(2, now(), 0, now(), '푸드');

-- 소 카테고리
    -- 음료
insert menu.categorys
    values(1, now(), 0, now(), '이미지', 'NEW', 1);
insert menu.categorys
    values(2, now(), 0, now(), '이미지', '추천', 1);
insert menu.categorys
    values(3, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2022/08/[9200000003763]_20220803131322551.jpg',
    '리프레셔', 1);
insert menu.categorys
    values(4, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002487]_20210426091745467.jpg',
    '콜드 브루', 1);
insert menu.categorys
    values(5, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002953]_20210427132718157.jpg',
    '블론드', 1);
insert menu.categorys
    values(6, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110563]_20210426095937808.jpg',
    '에스프레소', 1);
insert menu.categorys
    values(7, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[2]_20210430111934117.jpg',
    '디카페인 커피', 1);
insert menu.categorys
    values(8, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002760]_20210415133558068.jpg',
    '프라푸치노', 1);
insert menu.categorys
    values(9, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[167004]_20210419130801597.jpg',
    '블렌디드', 1);
insert menu.categorys
    values(10, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[107025]_20210419104756955.jpg',
    '피지오', 1);
insert menu.categorys
    values(11, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2022/09/[9200000004306]_20220902105252734.jpg',
    '티바나', 1);
insert menu.categorys
    values(12, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[106509]_20210430111852870.jpg',
    '브루드 커피', 1);
insert menu.categorys
    values(13, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[72]_20210415140949967.jpg',
    '기타', 1);
insert menu.categorys
    values(14, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2022/09/[9300000004347]_20220921130952276.jpg',
    '병음료', 1);

    -- 푸드
insert menu.categorys
    values(15, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/03/[9300000003334]_20210310092057351.jpg',
    '브레드', 2);
insert menu.categorys
    values(16, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9300000001179]_20210421164934656.jpg',
    '케이크', 2);
insert menu.categorys
    values(17, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9300000000036]_20210421133443711.jpg',
    '샌드위치 & 샐러드', 2);
insert menu.categorys
    values(18, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2022/04/[9900000001200]_20220407090142827.jpg',
    '따뜻한 푸드', 2);
insert menu.categorys
    values(19, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[5110009051]_20210422111651003.jpg',
    '과일 & 요거트', 2);
insert menu.categorys
    values(20, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[5650003319]_20210421133114995.jpg',
    '스낵 & 미니 디저트', 2);
insert menu.categorys
    values(21, now(), 0, now(), 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[5110009050]_20210422110047018.jpg',
    '아이스크림', 2);


-- 메뉴
insert menu.menu
    values(1, now(), 0, now(), '아메리카노', 0, 0, 4500, 1);
insert menu.menu
    values(2, now(), 0, now(), '카페라떼', 0, 0, 5000, 1);
insert menu.menu
    values(3, now(), 0, now(), '카푸치노', 0, 0, 5000, 1);
insert menu.menu
    values(4, now(), 0, now(), '카페모카', 0, 0, 5500, 1);

-- 온도
insert menu.temperature
    values(1, now(), 0, now(), '진한 에스프레소와 시원한 정수물과 얼음을 더하다.', 'Hot Americano', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[94]_20210430103337157.jpg',
    '따뜻한 아메리카노', 'hot', 1);
insert menu.temperature
    values(2, now(), 0, now(), '진한 에스프레소와 뜨거운 물을 섞다.', 'Ice Americano', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110563]_20210426095937947.jpg',
    '아이스 아메리카노', 'ice', 1);
insert menu.temperature
    values(3, now(), 0, now(), '진한 에스프레소와 뜨거운 우유를 섞다.', 'Hot Caffe Latte', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[41]_20210415133833879.jpg',
    '따뜻한 카페라떼', 'hot', 2);
insert menu.temperature
    values(4, now(), 0, now(), '진한 에스프레소와 시원한 우유를 섞다.', 'Ice Caffe Latte', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110569]_20210415143035989.jpg',
    '아이스 카페라떼', 'ice', 2);
insert menu.temperature
    values(5, now(), 0, now(), '진한 에스프레소와 시원한 우유와 우유거품', 'Ice Cappuccino', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110601]_20210415143400773.jpg',
    '아이스 카푸치노', 'ice', 3);
insert menu.temperature
    values(6, now(), 0, now(), '진한 에스프레소와 따뜻한 우유와 우유거품', 'Hot Cappuccino', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[38]_20210415154821846.jpg',
    '따뜻한 카푸치노', 'hot', 3);
insert menu.temperature
    values(7, now(), 0, now(), '진한 초콜릿 모카 시럽과 에스프레소, 우유', 'Hot Caffe Mocha', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[46]_20210415134438165.jpg',
    '따뜻한 카페모카', 'hot', 4);
insert menu.temperature
    values(8, now(), 0, now(), '진한 초콜릿 모카 시럽과 에스프레소, 우유', 'Ice Caffe Mocha', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110566]_20210415134334280.jpg',
    '아이스 카페모카', 'ice', 4);

-- 사이즈
insert menu.size
    values(1, now(), 0, now(), 0, 'Tall', 1);
insert menu.size
    values(2, now(), 0, now(), 500, 'Grande', 1);
insert menu.size
    values(3, now(), 0, now(), 1000, 'Venti', 1);
insert menu.size
    values(4, now(), 0, now(), 0, 'Tall', 2);
insert menu.size
    values(5, now(), 0, now(), 500, 'Grande', 2);
insert menu.size
    values(6, now(), 0, now(), 1000, 'Venti', 2);
insert menu.size
    values(7, now(), 0, now(), 0, 'Tall', 3);
insert menu.size
    values(8, now(), 0, now(), 500, 'Grande', 3);
insert menu.size
    values(9, now(), 0, now(), 1000, 'Venti', 3);
insert menu.size
    values(10, now(), 0, now(), 0, 'Tall', 4);
insert menu.size
    values(11, now(), 0, now(), 500, 'Grande', 4);
insert menu.size
    values(12, now(), 0, now(), 1000, 'Venti', 4);
insert menu.size
    values(13, now(), 0, now(), 0, 'Tall', 5);
insert menu.size
    values(14, now(), 0, now(), 500, 'Grande', 5);
insert menu.size
    values(15, now(), 0, now(), 1000, 'Venti', 5);
insert menu.size
    values(16, now(), 0, now(), 0, 'Tall', 6);
insert menu.size
    values(17, now(), 0, now(), 500, 'Grande', 6);
insert menu.size
    values(18, now(), 0, now(), 1000, 'Venti', 6);
insert menu.size
    values(19, now(), 0, now(), 0, 'Tall', 7);
insert menu.size
    values(20, now(), 0, now(), 500, 'Grande', 7);
insert menu.size
    values(21, now(), 0, now(), 1000, 'Venti', 7);
insert menu.size
    values(22, now(), 0, now(), 0, 'Tall', 8);
insert menu.size
    values(23, now(), 0, now(), 500, 'Grande', 8);
insert menu.size
    values(24, now(), 0, now(), 1000, 'Venti', 8);