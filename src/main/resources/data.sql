INSERT INTO authority (id, name) VALUES
(UUID(), 'ROLE_USER'),
(UUID(), 'ROLE_ADMIN');

INSERT INTO code_language_category (id, language_name) VALUES
(UUID(), 'Java'),
(UUID(), 'Python'),
(UUID(), 'JavaScript'),
(UUID(), 'C++'),
(UUID(), 'Ruby'),
(UUID(), 'Swift'),
(UUID(), 'Go'),
(UUID(), 'Kotlin'),
(UUID(), 'Rust'),
(UUID(), 'TypeScript');

INSERT INTO member (email, nickname, authority_id) VALUES
('user1@example.com', 'UserOne', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
('user2@example.com', 'UserTwo', (SELECT id FROM authority LIMIT 1 OFFSET 1)),
('user3@example.com', 'UserThree', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
('user4@example.com', 'UserFour', (SELECT id FROM authority LIMIT 1 OFFSET 1)),
('user5@example.com', 'UserFive', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
('user6@example.com', 'UserSix', (SELECT id FROM authority LIMIT 1 OFFSET 1)),
('user7@example.com', 'UserSeven', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
('user8@example.com', 'UserEight', (SELECT id FROM authority LIMIT 1 OFFSET 1)),
('user9@example.com', 'UserNine', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
('user10@example.com', 'UserTen', (SELECT id FROM authority LIMIT 1 OFFSET 1));

INSERT INTO code_review (id, email, title, content, create_date, language_id) VALUES
(UUID(), 'user1@example.com', 'Code Review 1', 'Content for code review 1', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 0)),
(UUID(), 'user2@example.com', 'Code Review 2', 'Content for code review 2', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 1)),
(UUID(), 'user3@example.com', 'Code Review 3', 'Content for code review 3', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 2)),
(UUID(), 'user4@example.com', 'Code Review 4', 'Content for code review 4', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 3)),
(UUID(), 'user5@example.com', 'Code Review 5', 'Content for code review 5', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 4)),
(UUID(), 'user6@example.com', 'Code Review 6', 'Content for code review 6', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 5)),
(UUID(), 'user7@example.com', 'Code Review 7', 'Content for code review 7', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 6)),
(UUID(), 'user8@example.com', 'Code Review 8', 'Content for code review 8', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 7)),
(UUID(), 'user9@example.com', 'Code Review 9', 'Content for code review 9', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 8)),
(UUID(), 'user10@example.com', 'Code Review 10', 'Content for code review 10', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 9));

INSERT INTO project_review (id, email, github_url, title, content, create_date) VALUES
(UUID(), 'user1@example.com', 'https://github.com/user1/repo1', 'Project Review 1', 'Content for project review 1', NOW()),
(UUID(), 'user2@example.com', 'https://github.com/user2/repo2', 'Project Review 2', 'Content for project review 2', NOW()),
(UUID(), 'user3@example.com', 'https://github.com/user3/repo3', 'Project Review 3', 'Content for project review 3', NOW()),
(UUID(), 'user4@example.com', 'https://github.com/user4/repo4', 'Project Review 4', 'Content for project review 4', NOW()),
(UUID(), 'user5@example.com', 'https://github.com/user5/repo5', 'Project Review 5', 'Content for project review 5', NOW()),
(UUID(), 'user6@example.com', 'https://github.com/user6/repo6', 'Project Review 6', 'Content for project review 6', NOW()),
(UUID(), 'user7@example.com', 'https://github.com/user7/repo7', 'Project Review 7', 'Content for project review 7', NOW()),
(UUID(), 'user8@example.com', 'https://github.com/user8/repo8', 'Project Review 8', 'Content for project review 8', NOW()),
(UUID(), 'user9@example.com', 'https://github.com/user9/repo9', 'Project Review 9', 'Content for project review 9', NOW()),
(UUID(), 'user10@example.com', 'https://github.com/user10/repo10', 'Project Review 10', 'Content for project review 10', NOW());

INSERT INTO project_review_image (id, file_name, url, project_review_id) VALUES
(UUID(), 'image1.png', 'https://example.com/image1.png', (SELECT id FROM project_review LIMIT 1 OFFSET 0)),
(UUID(), 'image2.png', 'https://example.com/image2.png', (SELECT id FROM project_review LIMIT 1 OFFSET 1)),
(UUID(), 'image3.png', 'https://example.com/image3.png', (SELECT id FROM project_review LIMIT 1 OFFSET 2)),
(UUID(), 'image4.png', 'https://example.com/image4.png', (SELECT id FROM project_review LIMIT 1 OFFSET 3)),
(UUID(), 'image5.png', 'https://example.com/image5.png', (SELECT id FROM project_review LIMIT 1 OFFSET 4)),
(UUID(), 'image6.png', 'https://example.com/image6.png', (SELECT id FROM project_review LIMIT 1 OFFSET 5)),
(UUID(), 'image7.png', 'https://example.com/image7.png', (SELECT id FROM project_review LIMIT 1 OFFSET 6)),
(UUID(), 'image8.png', 'https://example.com/image8.png', (SELECT id FROM project_review LIMIT 1 OFFSET 7)),
(UUID(), 'image9.png', 'https://example.com/image9.png', (SELECT id FROM project_review LIMIT 1 OFFSET 8)),
(UUID(), 'image10.png', 'https://example.com/image10.png', (SELECT id FROM project_review LIMIT 1 OFFSET 9));

INSERT INTO project_review_tag (id, project_review_id, tag) VALUES
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 0), 'Java'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 1), 'Python'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 2), 'JavaScript'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 3), 'C++'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 4), 'Ruby'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 5), 'Swift'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 6), 'Go'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 7), 'Kotlin'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 8), 'Rust'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 9), 'TypeScript');

INSERT INTO project_review_comment (id, project_review_id, content) VALUES
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 0), 'Great project!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 1), 'Nice work!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 2), 'Interesting approach.'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 3), 'Good job!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 4), 'Well done!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 5), 'Excellent!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 6), 'Looks good!'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 7), 'Very informative.'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 8), 'I like this.'),
(UUID(), (SELECT id FROM project_review LIMIT 1 OFFSET 9), 'Nice details!');

INSERT INTO code_review_preference (id, sender_email, entity_id, is_Like, create_date) VALUES
(UUID(), 'user1@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 0), 1, NOW()),
(UUID(), 'user2@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 1), 1, NOW()),
(UUID(), 'user3@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 2), 1, NOW()),
(UUID(), 'user4@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 3), 1, NOW()),
(UUID(), 'user5@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 4), 1, NOW()),
(UUID(), 'user6@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 5), 1, NOW()),
(UUID(), 'user7@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 6), 1, NOW()),
(UUID(), 'user8@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 7), 1, NOW()),
(UUID(), 'user9@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 8), 1, NOW()),
(UUID(), 'user10@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 9), 1, NOW());

INSERT INTO code_review_comment (id, comment, create_date, code_review_id, base_comment_id, email) VALUES
(UUID(), 'Nice code!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 0), NULL, 'user1@example.com'),
(UUID(), 'Looks good!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 1),null, 'user2@example.com'),
(UUID(), 'Interesting implementation.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 2), NULL, 'user3@example.com'),
(UUID(), 'Good job!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 3), NULL, 'user4@example.com'),
(UUID(), 'Well done!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 4), NULL, 'user5@example.com'),
(UUID(), 'Excellent work!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 5), NULL, 'user6@example.com'),
(UUID(), 'Very efficient.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 6), NULL, 'user7@example.com'),
(UUID(), 'I like this approach.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 7), NULL, 'user8@example.com'),
(UUID(), 'Good structure.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 8), NULL, 'user9@example.com'),
(UUID(), 'Nice logic!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 9), NULL, 'user10@example.com');



INSERT INTO notification (id, receiver_email, entity_id, is_read, create_date) VALUES
(UUID(), 'user1@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 0), 0, NOW()),
(UUID(), 'user2@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 1), 0, NOW()),
(UUID(), 'user3@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 2), 0, NOW()),
(UUID(), 'user4@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 3), 0, NOW()),
(UUID(), 'user5@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 4), 0, NOW()),
(UUID(), 'user6@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 5), 0, NOW()),
(UUID(), 'user7@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 6), 0, NOW()),
(UUID(), 'user8@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 7), 0, NOW()),
(UUID(), 'user9@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 8), 0, NOW()),
(UUID(), 'user10@example.com', (SELECT id FROM code_review LIMIT 1 OFFSET 9), 0, NOW());

