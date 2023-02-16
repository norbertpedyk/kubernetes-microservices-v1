TRUNCATE TABLE posts;
INSERT INTO posts (id, author_id, text,topic, posted_at)
VALUES (1, 1, 'This is my first post','topic 1' ,'2022-01-01 12:00:00'),
       (2, 1, 'Hello World', 'topic 2','2022-02-01 12:00:00'),
       (3, 1, 'I love PostgreSQL','topic 3' ,'2022-03-01 12:00:00'),
       (4, 2, 'Today is a beautiful day','topic 4' ,'2022-04-01 12:00:00'),
       (5, 3, 'My thoughts on programming','topic 5' ,'2022-05-01 12:00:00'),
       (6, 3, 'Why I switched to PostgreSQL', 'topic 6','2022-06-01 12:00:00');