-- 대 카테고리
insert menu.categoryl
values(1, now(), 0, now(), '음료');
insert menu.categoryl
values(2, now(), 0, now(), '푸드');

-- 소 카테고리
insert menu.categorys
    values(1, now(), 0, now(), '이미지', 'NEW', 1);
insert menu.categorys
    values(2, now(), 0, now(), '이미지', '추천', 1);
insert menu.categorys
    values(3, now(), 0, now(), '이미지', '리프레셔', 1);
insert menu.categorys
    values(4, now(), 0, now(), '이미지', '콜드 브루', 1);
insert menu.categorys
    values(5, now(), 0, now(), '이미지', '블론드', 1);
insert menu.categorys
    values(6, now(), 0, now(), '이미지', '에스프레소', 1);
insert menu.categorys
    values(7, now(), 0, now(), '이미지', '디카페인 커피', 1);
insert menu.categorys
    values(8, now(), 0, now(), '이미지', '프라푸치노', 1);
insert menu.categorys
    values(9, now(), 0, now(), '이미지', '블렌디드', 1);
insert menu.categorys
    values(10, now(), 0, now(), '이미지', '피지오', 1);
insert menu.categorys
    values(11, now(), 0, now(), '이미지', '티바나', 1);
insert menu.categorys
    values(12, now(), 0, now(), '이미지', '브루드 커피', 1);
insert menu.categorys
    values(13, now(), 0, now(), '이미지', '기타', 1);
insert menu.categorys
    values(14, now(), 0, now(), '이미지', '병음료', 1);

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
    values(1, now(), 0, now(), '진한 에스프레소와 시원한 정수물과 얼음을 더하다.', 'Hot Americano', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[94]_20210430103337157.jpg', '따뜻한 아메리카노', 'hot', 1);
insert menu.temperature
    values(2, now(), 0, now(), '진한 에스프레소와 뜨거운 물을 섞다.', 'Ice Americano', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110563]_20210426095937947.jpg', '아이스 아메리카노', 'ice', 1);
insert menu.temperature
    values(3, now(), 0, now(), '진한 에스프레소와 뜨거운 우유를 섞다.', 'Hot Caffe Latte', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[41]_20210415133833879.jpg', '따뜻한 카페라떼', 'hot', 2);
insert menu.temperature
    values(4, now(), 0, now(), '진한 에스프레소와 시원한 우유를 섞다.', 'Ice Caffe Latte', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110569]_20210415143035989.jpg', '아이스 카페라떼', 'ice', 2);
insert menu.temperature
    values(5, now(), 0, now(), '진한 에스프레소와 시원한 우유와 우유거품', 'Ice Cappuccino', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110601]_20210415143400773.jpg', '아이스 카푸치노', 'ice', 3);
insert menu.temperature
    values(6, now(), 0, now(), '진한 에스프레소와 따뜻한 우유와 우유거품', 'Hot Cappuccino', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[38]_20210415154821846.jpg', '따뜻한 카푸치노', 'hot', 3);
insert menu.temperature
    values(7, now(), 0, now(), '진한 초콜릿 모카 시럽과 에스프레소, 우유', 'Hot Caffe Mocha', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[46]_20210415134438165.jpg', '따뜻한 카페모카', 'hot', 4);
insert menu.temperature
    values(8, now(), 0, now(), '진한 초콜릿 모카 시럽과 에스프레소, 우유', 'Ice Caffe Mocha', 'https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110566]_20210415134334280.jpg', '아이스 카페모카', 'ice', 4);