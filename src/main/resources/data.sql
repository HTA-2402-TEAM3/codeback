START TRANSACTION;


-- 권한 데이터가 없을 경우에만 삽입
INSERT INTO authority (id, name)
SELECT UUID(), 'USER'
WHERE NOT EXISTS (SELECT 1 FROM authority WHERE name = 'USER');

INSERT INTO authority (id, name)
SELECT UUID(), 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM authority WHERE name = 'ADMIN');


-- 프로그래밍 언어 데이터가 없을 경우에만 삽입
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Java'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Java');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Python'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Python');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'JavaScript'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'JavaScript');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'C++'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'C++');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Ruby'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Ruby');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Swift'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Swift');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Go'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Go');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Kotlin'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Kotlin');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'Rust'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'Rust');
INSERT INTO code_language_category (id, language_name)
SELECT UUID(),
       'TypeScript'
WHERE NOT EXISTS (SELECT 1 FROM code_language_category WHERE language_name = 'TypeScript');


-- 회원 데이터가 없을 경우에만 삽입
INSERT INTO member (email, nickname, authority_id)
SELECT 'alice@google.com', 'Alice#google', (SELECT id FROM authority WHERE name = 'USER' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'alice@google.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'bob@google.com', 'Bob#google', (SELECT id FROM authority WHERE name = 'USER' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'bob@google.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'jackson@github.com', 'Jackson#github', (SELECT id FROM authority WHERE name = 'USER' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'jackson@github.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'lina@github.com', 'Lina#github', (SELECT id FROM authority WHERE name = 'USER' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'lina@github.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'percy@github.com', 'Percy#github', (SELECT id FROM authority WHERE name = 'USER' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'percy@github.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'frod@github.com', 'Frod#github', (SELECT id FROM authority WHERE name = 'ADMIN' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'frod@github.com');

INSERT INTO member (email, nickname, authority_id)
SELECT 'sungho@github.com', 'Sungho#github', (SELECT id FROM authority WHERE name = 'ADMIN' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'sungho@github.com');

-- 계속해서 나머지 회원들을 추가합니다...


-- 코드 리뷰 데이터 삽입
INSERT INTO code_review (id, email, title, content, create_date, language_id)
SELECT UUID(),
       'jackson@github.com',
       'Exploring Java Streams',
       'A deep dive into Java Streams API.',
       NOW(),
       (SELECT id FROM code_language_category WHERE language_name = 'Java' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM code_review WHERE title = 'Exploring Java Streams');

INSERT INTO code_review (id, email, title, content, create_date, language_id)
SELECT UUID(),
       'lina@github.com',
       'Python for Data Science',
       'Understanding Python libraries for data science.',
       NOW(),
       (SELECT id FROM code_language_category WHERE language_name = 'Python' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM code_review WHERE title = 'Python for Data Science');

INSERT INTO code_review (id, email, title, content, create_date, language_id)
SELECT UUID(),
       'percy@github.com',
       'Python for Data Science',
       'Understanding Python libraries for data science.',
       NOW(),
       (SELECT id FROM code_language_category WHERE language_name = 'Python' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM code_review WHERE title = 'Python for Data Science');
-- 계속해서 나머지 코드 리뷰들을 추가합니다...


-- 코드 리뷰 댓글 데이터 삽입
INSERT INTO code_review_comment (id, comment, create_date, code_review_id, base_comment_id, email)
SELECT UUID(),
       'This is a great article on Java Streams!',
       NOW(),
       (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1),
       NULL,
       'alice@google.com'
WHERE NOT EXISTS (SELECT 1
                  FROM code_review_comment
                  WHERE comment = 'This is a great article on Java Streams!'
                    AND code_review_id = (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1));

INSERT INTO code_review_comment (id, comment, create_date, code_review_id, base_comment_id, email)
SELECT UUID(),
       'Very useful tips, thanks!',
       NOW(),
       (SELECT id FROM code_review WHERE title = 'Python for Data Science' LIMIT 1),
       NULL,
       'bob@google.com'
WHERE NOT EXISTS (SELECT 1
                  FROM code_review_comment
                  WHERE comment = 'Very useful tips, thanks!'
                    AND code_review_id = (SELECT id FROM code_review WHERE title = 'Python for Data Science' LIMIT 1));
-- 계속해서 나머지 코드 리뷰 댓글들을 추가합니다...


-- 코드 리뷰 좋아요 데이터 삽입
INSERT INTO code_review_preference ( id
                                   , sender_email
                                   , entity_id
                                   , is_Like
                                   , create_date)
SELECT UUID()
     , 'alice@google.com'
     , (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1)
     , 1
     , NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM code_review_preference
                  WHERE sender_email = 'alice@google.com'
                    AND entity_id = (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1))
;

INSERT INTO code_review_preference ( id
                                   , sender_email
                                   , entity_id
                                   , is_Like
                                   , create_date)
SELECT UUID()
     , 'bob@google.com'
     , (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1)
     , 1
     , NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM code_review_preference
                  WHERE sender_email = 'bob@google.com'
                    AND entity_id = (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1))
;

INSERT INTO code_review_preference ( id
                                   , sender_email
                                   , entity_id
                                   , is_Like
                                   , create_date)
SELECT UUID()
     , 'percy@github.com'
     , (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1)
     , 1
     , NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM code_review_preference
                  WHERE sender_email = 'percy@github.com'
                    AND entity_id = (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1))
;

-- 댓글에 대한 알림
INSERT INTO notification (id, receiver_email, entity_id, message, is_read, create_date)
SELECT UUID(),
       'jackson@github.com',
       (SELECT id FROM code_review_comment WHERE comment = 'This is a great article on Java Streams!' LIMIT 1),
       'Alice#google 님이 Exploring Java Streams 게시글에 답변을 남겼습니다.',
       0,
       NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM notification
                  WHERE receiver_email = 'jackson@github.com'
                    AND entity_id = (SELECT id
                                     FROM code_review_comment
                                     WHERE comment = 'This is a great article on Java Streams!'
                                     LIMIT 1));

-- 좋아요에 대한 알림
INSERT INTO notification (id, receiver_email, entity_id, message, is_read, create_date)
SELECT UUID(),
       'jackson@github.com',
       (SELECT id
        FROM code_review_preference
        WHERE sender_email = 'bob@google.com'
          AND entity_id = (SELECT id FROM code_review WHERE title = 'Exploring Java Streams' LIMIT 1)),
       'bob#google 님이 Exploring Java Streams 게시글에 좋아요를 눌렀습니다.',
       0,
       NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM notification
                  WHERE receiver_email = 'jackson@github.com'
                    AND entity_id = (SELECT id
                                     FROM code_review_preference
                                     WHERE sender_email = 'bob@google.com'
                                       AND entity_id = (SELECT id
                                                        FROM code_review
                                                        WHERE title = 'Exploring Java Streams'
                                                        LIMIT 1)));


commit;
