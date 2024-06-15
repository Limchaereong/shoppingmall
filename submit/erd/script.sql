use nhn_academy_36;

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS user_addresses;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS point_history;
DROP TABLE IF EXISTS users;



-- 카테고리
CREATE TABLE product_categories (
    category_id       INT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 아이디',
    category_name     VARCHAR(255) NOT NULL COMMENT '카테고리 이름',
    parent_category_id INT COMMENT '상위 카테고리 아이디',
    FOREIGN KEY (parent_category_id) REFERENCES product_categories (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 카테고리';

INSERT INTO product_categories (category_name) VALUES ('의류');
INSERT INTO product_categories (category_name) VALUES ('신발');
INSERT INTO product_categories (category_name) VALUES ('가방');

INSERT INTO product_categories (category_name, parent_category_id) VALUES ('남성 의류', 1);
INSERT INTO product_categories (category_name, parent_category_id) VALUES ('여성 의류', 1);
INSERT INTO product_categories (category_name, parent_category_id) VALUES ('스니커즈', 2);
INSERT INTO product_categories (category_name, parent_category_id) VALUES ('부츠', 2);
INSERT INTO product_categories (category_name, parent_category_id) VALUES ('클러치', 3);
INSERT INTO product_categories (category_name, parent_category_id) VALUES ('숄더백', 3);

-- 회원
CREATE TABLE users (
    user_id         VARCHAR(50)  NOT NULL COMMENT '아이디',
    user_name       VARCHAR(50)  NOT NULL COMMENT '이름',
    user_password   VARCHAR(200) NOT NULL COMMENT '비밀번호',
    user_birth      VARCHAR(8)   NOT NULL COMMENT '생년월일 : 990422',
    user_auth       VARCHAR(10)  NOT NULL COMMENT 'ROLE_ADMIN,ROLE_USER',
    user_point      INT          NOT NULL DEFAULT 1000000 COMMENT '기본 지급 포인트',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입 일자',
    latest_login_at DATETIME     DEFAULT NULL COMMENT '마지막 로그인 일자',
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';


-- 주소
CREATE TABLE user_addresses (
    user_id         VARCHAR(50)  NOT NULL COMMENT '회원 아이디',
    zip_code        VARCHAR(13)  NOT NULL COMMENT '우편번호',
    address         VARCHAR(128) NOT NULL COMMENT '주소',
    address_detail  VARCHAR(128) NOT NULL COMMENT '상세 주소',
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주소';


-- 상품
CREATE TABLE products (
    product_id         INT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 아이디',
    category_id        INT,
    product_name       VARCHAR(255) NOT NULL COMMENT '상품 이름',
    product_price      DECIMAL(10, 2) NOT NULL COMMENT '상품 가격',
    thumbnail_image    TEXT NOT NULL COMMENT '썸네일 이미지',
    detail_image       TEXT NOT NULL COMMENT '상세 이미지',
    product_description TEXT COMMENT '상품 설명',
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '상품 등록 일자',
    updated_at         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '상품 수정 일자',
    FOREIGN KEY (category_id) REFERENCES product_categories (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';

-- 의류 카테고리 상품 추가
INSERT INTO products (category_id, product_name, product_price, thumbnail_image, detail_image, product_description) VALUES
    (1, '의류 상품 A', 10000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230411_198%2F1681197878767x8Rhp_JPEG%2F51587393_0_600.jpg&type=sc960_832', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '의류 상품 A 설명'),
    (1, '의류 상품 B', 20000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshopping.phinf.naver.net%2Fmain_2505803%2F25058037863.20201201185243.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '의류 상품 B 설명'),
    (1, '의류 상품 C', 30000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8677017%2F86770171950.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '의류 상품 C 설명'),
    (4, '남성 의류 상품 A', 40000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4627156%2F46271568104.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '남성 의류 상품 A 설명'),
    (4, '남성 의류 상품 B', 50000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4570920%2F45709201406.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '남성 의류 상품 B 설명'),
    (4, '남성 의류 상품 C', 60000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8490779%2F84907797181.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '남성 의류 상품 C 설명'),
    (5, '여성 의류 상품 A', 70000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8780004%2F87800049976.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '여성 의류 상품 A 설명'),
    (5, '여성 의류 상품 B', 80000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4508291%2F45082918293.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '여성 의류 상품 B 설명'),
    (5, '여성 의류 상품 C', 90000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8741867%2F87418679123.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '여성 의류 상품 C 설명'),

    (2, '신발 상품 A', 100000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4504017%2F45040170497.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '신발 상품 A 설명'),
    (2, '신발 상품 B', 110000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_3949948%2F39499487646.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '신발 상품 B 설명'),
    (2, '신발 상품 C', 120000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_8612988%2F86129880899.1.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '신발 상품 C 설명'),
    (6, '스니커즈 상품 A', 130000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4411926%2F44119266298.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '스니커즈 상품 A 설명'),
    (6, '스니커즈 상품 B', 140000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4585653%2F45856530082.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '스니커즈 상품 B 설명'),
    (6, '스니커즈 상품 C', 150000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20231018_240%2F1697595997155UXDK0_JPEG%2F47770539095365066_91726952.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '스니커즈 상품 C 설명'),
    (7, '부츠 상품 A', 160000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4632477%2F46324774753.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '부츠 상품 A 설명'),
    (7, '부츠 상품 B', 170000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_2859234%2F28592344591.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '부츠 상품 B 설명'),
    (7, '부츠 상품 C', 180000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20191010_207%2F1570679331692B08iL_JPEG%2F8039166242566875_334303207.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '부츠 상품 C 설명'),

    (3, '가방 상품 A', 190000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4604895%2F46048951487.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4604895%2F46048951487.jpg&type=f372_372', '가방 상품 A 설명'),
    (3, '가방 상품 B', 200000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMThfMjU4%2FMDAxNjk3NTk5NzkxODE0.VcKhBq7Vu2geNvpwZ_sgfE2OfRnIjmGZpSPB_v2eKmgg.nFNoTkPfp-A513qe3kfadtXi5MNOn5MY3gWx4wQPYlUg.PNG.semolookbook%2F%25BD%25BA%25C5%25A9%25B8%25B0%25BC%25A6_2023-10-18_%25BF%25C0%25C8%25C4_12.26.14.png&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '가방 상품 B 설명'),
    (3, '가방 상품 C', 210000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA5MDdfMjg3%2FMDAxNjk0MDQwNzE4ODU1.7W93uJgOsjJ3MPPh55sZzPb95NTGbM52RYMCd3aFMB0g.C7OW7ZUICdLZ4xURz7YnpcLZIADIKFUiMAEgq9skJaog.JPEG.snh2003%2F%25B4%25F5%25B7%25CE%25BF%25EC_%25C6%25C4%25C5%25A9%25B9%25E9_%25BD%25BA%25B8%25F4_%25C5%25E4%25C7%25C1_%25C6%25F7%25BF%25F6%25B5%25E5_5.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '가방 상품 C 설명'),
    (8, '클러치 상품 A', 220000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4695286%2F46952869545.1.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '클러치 상품 A 설명'),
    (8, '클러치 상품 B', 230000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20240201_22%2F1706749902773alkud_JPEG%2F31951372596487488_1529013872.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '클러치 상품 B 설명'),
    (8, '클러치 상품 C', 240000.00, 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fimage-cdn.trenbe.com%2Fproductmain%2F1711362054952-3569c934-1fc6-45d9-9465-fa4d604d62db.jpeg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '클러치 상품 C 설명'),
    (9, '숄더백 상품 A', 250000.00, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fshopping-phinf.pstatic.net%2Fmain_4581889%2F45818896089.jpg&type=f372_372', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '숄더백 상품 A 설명'),
    (9, '숄더백 상품 B', 260000.00, 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fsitem.ssgcdn.com%2F20%2F00%2F34%2Fitem%2F1000524340020_i4_500.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '숄더백 상품 B 설명'),
    (9, '숄더백 상품 C', 270000.00, 'https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20240214_183%2F17078978338397HYEB_JPEG%2F10910428689295196_1597783063.jpg&type=a340', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjJfMjc5%2FMDAxNzEzNzk3NTEwNjk3.9ZVy5WLmUpr5zDbvRLe0oTPjK6PMtW24AsUjXe69WL0g.8ujamBM1HT-6Bxwz0jyog5XNDjBZR7xciRjxV2fXXk0g.GIF%2Fracoon%25A3%25ADpedro.gif&type=a340', '숄더백 상품 C 설명');





-- 주문
CREATE TABLE orders (
    order_id       INT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 아이디',
    user_id        VARCHAR(50)  NOT NULL COMMENT '회원 아이디',
    order_amount   DECIMAL(10, 2) NOT NULL COMMENT '주문 결제 금액',
    order_date     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '주문 날짜',
    order_status   VARCHAR(16) NOT NULL DEFAULT '주문완료' COMMENT '주문 상태',
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';


-- 주문상세
CREATE TABLE order_details (
    order_detail_id        INT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 상세 아이디',
    product_id             INT NOT NULL COMMENT '상품 아이디',
    order_id               INT NOT NULL COMMENT '주문 아이디',
    order_detail_quantity  INT NOT NULL COMMENT '주문 상세 수량',
    order_detail_amount    DECIMAL(10, 2) NOT NULL COMMENT '주문 상세 가격',
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상세';


-- 장바구니
CREATE TABLE cart_items (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id      VARCHAR(50) NOT NULL,
    product_id   INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';


-- 포인트 내역
CREATE TABLE point_history (
    point_history_id       INT AUTO_INCREMENT PRIMARY KEY,
    user_id          VARCHAR(50) NOT NULL,
    point_action           ENUM('적립', '사용') NOT NULL,
    point_amount           DECIMAL(10, 2) NOT NULL,
    point_history_date     DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포인트 내역';
