INSERT INTO hate_food (id, food, img_url) values (default , '파인애플피자', 'dummy');
INSERT INTO hate_food (id, food) values (default , '닭발');
INSERT INTO hate_food (id, food) values (default , '평양냉면');
INSERT INTO hate_food (id, food) values (default , '번데기');
INSERT INTO hate_food (id, food) values (default , '청국장');
INSERT INTO hate_food (id, food) values (default , '소 생간');
INSERT INTO hate_food (id, food) values (default , '돼지껍데기');
INSERT INTO hate_food (id, food) values (default , '홍어회');
INSERT INTO hate_food (id, food) values (default , '건포도');
INSERT INTO hate_food (id, food) values (default , '가지');
INSERT INTO hate_food (id, food) values (default , '오이');
INSERT INTO hate_food (id, food) values (default , '생굴');
INSERT INTO hate_food (id, food) values (default , '곱창');
INSERT INTO hate_food (id, food) values (default , '막창');
INSERT INTO hate_food (id, food) values (default , '대창');
INSERT INTO hate_food (id, food) values (default , '돼지 허파');
INSERT INTO hate_food (id, food) values (default , '고수');
INSERT INTO hate_food (id, food) values (default , '추어탕');
INSERT INTO hate_food (id, food) values (default , '산낙지');
INSERT INTO hate_food (id, food) values (default , '회');
INSERT INTO hate_food (id, food) values (default , '알탕');
INSERT INTO hate_food (id, food) values (default , '닭똥집');
INSERT INTO hate_food (id, food) values (default , '선지해장국');
INSERT INTO hate_food (id, food) values (default , '양고기');
INSERT INTO hate_food (id, food) values (default , '닭 퍽퍽살');
INSERT INTO hate_food (id, food) values (default , '녹차 아이스크림');
INSERT INTO hate_food (id, food) values (default , '양고기');
INSERT INTO hate_food (id, food) values (default , '육회');

INSERT INTO category (id, category_name) values (default , '한식');
INSERT INTO category (id, category_name) values (default , '국밥');
INSERT INTO category (id, category_name) values (default , '중식당');
INSERT INTO category (id, category_name) values (default , '카레');
INSERT INTO category (id, category_name) values (default , '포장마차');
INSERT INTO category (id, category_name) values (default , '샌드위치');
INSERT INTO category (id, category_name) values (default , '카페,디저트');
INSERT INTO category (id, category_name) values (default , '이탈리아음식');

-- ID  	ADDRESS  	RESTAURANT_NAME  	RESTAURANT_IMG_URL  	CATEGORY_ID
INSERT INTO restaurant (id, address, restaurant_name, restaurant_img_url, category_id) values (default , '주소1', '맛집1', '이미지1', 1);
INSERT INTO restaurant (id, address, restaurant_name, restaurant_img_url,category_id) values (default , '주소2', '맛집2', '이미지2', 2);
INSERT INTO restaurant (id, address, restaurant_name, restaurant_img_url,category_id) values (default , '주소3', '맛집3', '이미지3', 3);
INSERT INTO restaurant (id, address, restaurant_name, restaurant_img_url,category_id) values (default , '주소4', '맛집4', '이미지4', 4);

-- ID CATEGORY_NAME
-- --'한식', nan, '국밥', '중식당', '카레', '카페', '포장마차', '샌드위치', '카페,디저트',
-- '이탈리아음식', '종합분식', '초밥,롤', '곱창,막창,양', '국수', '양꼬치', '한정식', '해물,생선요리',
--        '소고기구이', '딤섬,중식만두', '와인', '육류,고기요리', '치킨,닭강정', '두부요리', '요리주점',
--        '장어,먹장어요리', '생선회', '베트남음식', '다이어트,샐러드', '양식-일반', '돈가스', '브런치',
--        '덮밥', '이자카야', '족발,보쌈', '백숙,삼계탕', '아시아음식', '찌개,전골', '맥주,호프',
--        '우동,소바', '낙지요리', '전통,민속주점', '주꾸미요리', '떡볶이', '바(BAR)', '돼지고기구이',
--        '닭갈비', '일식당', '칼국수,만두', '도시락,컵밥', '전,빈대떡', '감자탕', '피자', '추어탕',
--        '김밥', '닭발', '베이커리', '향토음식', '순대,순댓국', '오징어요리', '테이크아웃커피',
--        '아귀찜,해물찜', '만두', '냉면', '사철,영양탕', '분식', '양갈비', '곰탕,설렁탕', '백반,가정식',
--        '찜닭', '푸드코트', '멕시코,남미음식', '양식', '라이브카페', '비빔밥', '케이크전문', '스파게티스토리',
--        '일본식라면', '매운탕,해물탕', '닭볶음탕', '죽', '게요리', '바나프레소', '스파게티,파스타전문',
--        '크레페', '생선구이', '뷔페', '정육식당', '퓨전음식', '태국음식', '야식', '해장국', '스테이크,립',
--        '채식,샐러드뷔페', '햄버거', '오므라이스', '샤브샤브', '기사식당', '토스트', '와플', '북카페',
--        '과일,주스전문점', '차', '도넛'


