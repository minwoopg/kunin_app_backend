-- ─────────────────────────────────────────
--  카테고리 (Flutter ProductCategory enum과 매칭)
-- ─────────────────────────────────────────
INSERT INTO categories (id, code, name, display_order, is_active) VALUES
(1, 'medicalDevice', '의료기기', 1, true),
(2, 'diagnostic',    '진단장비', 2, true),
(3, 'beautyCare',     '뷰티케어', 3, true),
(4, 'medicine',       '의약품', 4, true),
(5, 'health',         '건강식품', 5, true);

-- ─────────────────────────────────────────
--  상품 14개 (kip_app/lib/data/mock/mock_products.dart 기준)
-- ─────────────────────────────────────────

-- p001
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(1, 1, '초음파 진단기기 KIP-U100', 9800000, '프리미엄 휴대용 초음파 진단기기',
'최신 영상처리 기술을 적용한 휴대용 초음파 진단기기입니다.
고해상도 화질과 직관적인 UI로 빠르고 정확한 진단이 가능합니다.

· 무선 프로브 지원
· 배터리 연속 사용 4시간
· DICOM 표준 지원',
NULL, 'NEW', 4.8, 12, 5, 'KIP MEDICAL', '대한민국', true, NOW(), NOW());

-- p002
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(2, 1, '환자 모니터 KIP-M200', 1250000, '다기능 환자 생체신호 모니터',
'심전도, 혈압, 산소포화도를 동시에 모니터링할 수 있는 환자 모니터입니다.

· 12.1인치 풀컬러 터치스크린
· NIBP / SpO2 / ECG / RESP 동시 측정
· 알람 기능 내장',
NULL, 'BEST', 4.6, 28, 8, 'KIP MEDICAL', '대한민국', true, NOW(), NOW());

-- p003
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(3, 1, '전동 휠체어 KIP-WC500', 2150000, '경량 알루미늄 프레임 전동 휠체어',
'가볍고 견고한 알루미늄 프레임으로 제작된 전동 휠체어입니다.

· 1회 충전 주행거리 약 25km
· 최대 속도 6km/h
· 접이식 디자인',
NULL, 'NONE', 4.5, 9, 3, 'KIP MOBILITY', '대한민국', true, NOW(), NOW());

-- p004
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(4, 2, '산소포화도 측정기 KIP-OX1', 180000, '휴대용 펄스옥시미터',
'손가락에 끼우는 것만으로 산소포화도와 맥박을 측정할 수 있습니다.

· 1초 빠른 측정
· OLED 디스플레이
· 자동 전원 차단 기능',
NULL, 'NEW', 4.7, 56, 42, 'KIP MEDICAL', '대한민국', true, NOW(), NOW());

-- p005
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(5, 2, '디지털 혈압계 KIP-BP300', 89000, '가정용 자동 혈압측정기',
'간편한 원터치 조작으로 정확한 혈압을 측정할 수 있는 가정용 혈압계입니다.

· 부정맥 감지 기능
· 최대 90회 측정값 저장
· 대형 LCD 디스플레이',
NULL, 'NONE', 4.4, 103, 67, 'KIP MEDICAL', '대한민국', true, NOW(), NOW());

-- p006
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(6, 2, '비접촉 체온계 KIP-TM150', 45000, '1초 비접촉 적외선 체온계',
'이마에 가까이 대기만 하면 1초 안에 체온을 측정합니다.

· 비접촉식 측정
· 발열 알람 기능
· 32회 측정 기록 저장',
NULL, 'BEST', 4.5, 210, 120, 'KIP MEDICAL', '대한민국', true, NOW(), NOW());

-- p007
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(7, 3, '홈케어 뷰티 디바이스 KIP-2026', 398000, '프리미엄 LED 홈케어 뷰티 디바이스',
'LED 광선 테라피와 고주파 마사지를 결합한 프리미엄 홈케어 디바이스입니다.

· 3가지 LED 모드 (레드/블루/그린)
· EMS 마이크로커런트
· 1회 충전 약 90분 사용',
NULL, 'NEW', 4.9, 87, 15, 'KIP BEAUTY', '대한민국', true, NOW(), NOW());

-- p008
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(8, 3, '이온 클렌징 디바이스 KIP-C50', 129000, '실리콘 음이온 클렌징 브러시',
'초음파 진동과 음이온 기술로 모공 속 노폐물을 깨끗하게 제거합니다.

· IPX7 방수
· 3단계 강도 조절
· USB-C 충전',
NULL, 'NONE', 4.6, 64, 34, 'KIP BEAUTY', '대한민국', true, NOW(), NOW());

-- p009
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(9, 3, '두피 마사지기 KIP-SC10', 79000, '전동 두피 마사지 디바이스',
'4개의 실리콘 헤드가 두피를 자극하여 혈액순환을 돕습니다.

· 방수 설계
· 무선 충전식
· 저소음 모터',
NULL, 'NONE', 4.3, 41, 50, 'KIP BEAUTY', '대한민국', true, NOW(), NOW());

-- p010
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(10, 4, '의료용 소독제 KIP-GEL250', 28000, '손소독 겔 250ml',
'에탄올 함유 손소독제로 99.9% 살균 효과가 있습니다.

· 250ml 휴대용 용량
· 보습 성분 함유
· 식약처 인증',
NULL, 'NONE', 4.2, 156, 200, 'KIP PHARMA', '대한민국', true, NOW(), NOW());

-- p011
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(11, 4, '상처 드레싱 밴드 KIP-WD30', 15000, '방수 상처 드레싱 밴드 30매',
'습윤 환경을 유지하여 흉터를 최소화하는 상처 드레싱 밴드입니다.

· 30매입
· 방수 / 투명 재질
· 다양한 사이즈 구성',
NULL, 'NONE', 4.1, 23, 89, 'KIP PHARMA', '대한민국', true, NOW(), NOW());

-- p012
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(12, 5, '프로바이오틱스 KIP-BIO100', 42000, '장 건강 유산균 100억 CFU',
'1박스 30포, 1일 1포로 장 건강과 면역력 관리에 도움을 줍니다.

· 100억 CFU 고함량
· 코팅 공법으로 위산에서 보호
· 1박스 30포 (1개월분)',
NULL, 'BEST', 4.7, 198, 75, 'KIP HEALTH', '대한민국', true, NOW(), NOW());

-- p013
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(13, 5, '오메가3 KIP-OM90', 35000, '고함량 정제 오메가3 90캡슐',
'혈중 중성지질 개선에 도움을 줄 수 있는 정제어유 함유 제품입니다.

· EPA+DHA 1,000mg
· 90캡슐 (3개월분)
· 비린맛 저감 공법',
NULL, 'NONE', 4.4, 77, 60, 'KIP HEALTH', '대한민국', true, NOW(), NOW());

-- p014 (품절 테스트)
INSERT INTO products
(id, category_id, name, price, description, detail_description, image_url, tag, rating, review_count, stock_quantity, manufacturer, origin, is_active, created_at, updated_at)
VALUES
(14, 5, '비타민D KIP-VD60', 19800, '고함량 비타민D3 60캡슐',
'뼈와 면역 건강에 필요한 비타민D를 보충할 수 있는 제품입니다.

· 1캡슐당 비타민D3 1000IU
· 60캡슐 (2개월분)
· 소형 캡슐로 섭취 편리',
NULL, 'NONE', 4.3, 34, 0, 'KIP HEALTH', '대한민국', true, NOW(), NOW());
