
Inicio
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
versão: 3.11.4
keyspace twitter criado
usando keyspace twitter
jul 02, 2019 10:31:38 AM TweetRepository createTable
INFO: Começando
jul 02, 2019 10:31:38 AM TweetRepository createTable
INFO: CREATE TABLE IF NOT EXISTS tweets(id uuid PRIMARY KEY, user text, text text, favoriteCount int, isFavorited Boolean, geolocation text, contributors text, source text, isTruncated text, date date);
jul 02, 2019 10:31:39 AM TweetRepository createTable
INFO: Finalizada
jul 02, 2019 10:31:39 AM TweetRepository createTableByFavCount
INFO: Começando
jul 02, 2019 10:31:39 AM TweetRepository createTableByFavCount
INFO: CREATE TABLE IF NOT EXISTS tweets_favoriteCount(id uuid, user text, text text, favoriteCount int, date date,  PRIMARY KEY (user, favoriteCount, id)) WITH CLUSTERING ORDER BY ( favoriteCount DESC, id ASC );
jul 02, 2019 10:31:40 AM TweetRepository createTableByFavCount
INFO: Finalizada
Inserindo Tweets
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: INSERT INTO tweets(id, user, text, favoriteCount, isFavorited, date) VALUES (7103be2d-2e6d-4d74-b527-f9ed18387583, 'user', 'texto do user 0', 3, true, '2019-01-20');
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: INSERT INTO tweets_favoriteCount(id, user, text, favoriteCount, date) VALUES (7103be2d-2e6d-4d74-b527-f9ed18387583, 'user', 'texto do user 0', 3, '2019-01-20');
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Finalizando
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: INSERT INTO tweets(id, user, text, favoriteCount, isFavorited, date) VALUES (10a3b270-960b-4106-87ae-2ff4c51d0b0d, 'user', 'texto do user 1', 6, true, '2019-01-21');
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: INSERT INTO tweets_favoriteCount(id, user, text, favoriteCount, date) VALUES (10a3b270-960b-4106-87ae-2ff4c51d0b0d, 'user', 'texto do user 1', 6, '2019-01-21');
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Finalizando
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: INSERT INTO tweets(id, user, text, favoriteCount, isFavorited, date) VALUES (33592854-dbc2-4f42-aaf9-dc324d7d62c5, 'user', 'texto do user 2', 64, true, '2019-01-22');
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: INSERT INTO tweets_favoriteCount(id, user, text, favoriteCount, date) VALUES (33592854-dbc2-4f42-aaf9-dc324d7d62c5, 'user', 'texto do user 2', 64, '2019-01-22');
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Finalizando
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: INSERT INTO tweets(id, user, text, favoriteCount, isFavorited, date) VALUES (7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052, 'user', 'texto do user 3', 72, true, '2019-01-23');
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: INSERT INTO tweets_favoriteCount(id, user, text, favoriteCount, date) VALUES (7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052, 'user', 'texto do user 3', 72, '2019-01-23');
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Finalizando
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: INSERT INTO tweets(id, user, text, favoriteCount, isFavorited, date) VALUES (f1e82407-5531-44ae-8d73-e3767b3e1589, 'user', 'texto do user 4', 38, true, '2019-01-24');
jul 02, 2019 10:31:40 AM TweetRepository inserttweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: INSERT INTO tweets_favoriteCount(id, user, text, favoriteCount, date) VALUES (f1e82407-5531-44ae-8d73-e3767b3e1589, 'user', 'texto do user 4', 38, '2019-01-24');
jul 02, 2019 10:31:40 AM TweetRepository inserttweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository insertTweetBatch
INFO: Finalizando
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: SELECT * FROM tweets;
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: tweet id: 33592854-dbc2-4f42-aaf9-dc324d7d62c5 From user: user Date: 2019-01-22 isFavorited: true favoritedCount: 64
texto do user 2
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: tweet id: f1e82407-5531-44ae-8d73-e3767b3e1589 From user: user Date: 2019-01-24 isFavorited: true favoritedCount: 38
texto do user 4
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: tweet id: 10a3b270-960b-4106-87ae-2ff4c51d0b0d From user: user Date: 2019-01-21 isFavorited: true favoritedCount: 6
texto do user 1
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: tweet id: 7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052 From user: user Date: 2019-01-23 isFavorited: true favoritedCount: 72
texto do user 3
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: tweet id: 7103be2d-2e6d-4d74-b527-f9ed18387583 From user: user Date: 2019-01-20 isFavorited: true favoritedCount: 3
texto do user 0
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: SELECT * FROM tweets_favoriteCount WHERE USER = 'user' ;
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: tweet id: 7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052 From user: user Date: 2019-01-23 isFavorited: true favoritedCount: 72
texto do user 3
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: tweet id: 33592854-dbc2-4f42-aaf9-dc324d7d62c5 From user: user Date: 2019-01-22 isFavorited: true favoritedCount: 64
texto do user 2
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: tweet id: f1e82407-5531-44ae-8d73-e3767b3e1589 From user: user Date: 2019-01-24 isFavorited: true favoritedCount: 38
texto do user 4
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: tweet id: 10a3b270-960b-4106-87ae-2ff4c51d0b0d From user: user Date: 2019-01-21 isFavorited: true favoritedCount: 6
texto do user 1
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: tweet id: 7103be2d-2e6d-4d74-b527-f9ed18387583 From user: user Date: 2019-01-20 isFavorited: true favoritedCount: 3
texto do user 0
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: DELETE FROM tweets WHERE id = 7103be2d-2e6d-4d74-b527-f9ed18387583;
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: DELETE FROM tweets_favoriteCount WHERE id = 7103be2d-2e6d-4d74-b527-f9ed18387583 AND  favoriteCount = 3 AND  user = 'user';
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: DELETE FROM tweets WHERE id = 10a3b270-960b-4106-87ae-2ff4c51d0b0d;
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: DELETE FROM tweets_favoriteCount WHERE id = 10a3b270-960b-4106-87ae-2ff4c51d0b0d AND  favoriteCount = 6 AND  user = 'user';
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: DELETE FROM tweets WHERE id = 33592854-dbc2-4f42-aaf9-dc324d7d62c5;
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: DELETE FROM tweets_favoriteCount WHERE id = 33592854-dbc2-4f42-aaf9-dc324d7d62c5 AND  favoriteCount = 64 AND  user = 'user';
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: DELETE FROM tweets WHERE id = 7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052;
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: DELETE FROM tweets_favoriteCount WHERE id = 7a8d89f2-6f0a-4cfb-a950-0c62a2e5f052 AND  favoriteCount = 72 AND  user = 'user';
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: DELETE FROM tweets WHERE id = f1e82407-5531-44ae-8d73-e3767b3e1589;
jul 02, 2019 10:31:40 AM TweetRepository deletetweet
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: DELETE FROM tweets_favoriteCount WHERE id = f1e82407-5531-44ae-8d73-e3767b3e1589 AND  favoriteCount = 38 AND  user = 'user';
jul 02, 2019 10:31:40 AM TweetRepository deletetweetByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: SELECT * FROM tweets;
jul 02, 2019 10:31:40 AM TweetRepository selectAll
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: SELECT * FROM tweets_favoriteCount WHERE USER = 'user' ;
jul 02, 2019 10:31:40 AM TweetRepository selectAllByFavCount
INFO: Finalizada
jul 02, 2019 10:31:40 AM TweetRepository deleteTable
INFO: Começando
jul 02, 2019 10:31:40 AM TweetRepository deleteTable
INFO: DROP TABLE IF EXISTS tweets;
jul 02, 2019 10:31:44 AM TweetRepository deleteTable
INFO: Finalizada
jul 02, 2019 10:31:44 AM TweetRepository deleteTable
INFO: Começando
jul 02, 2019 10:31:44 AM TweetRepository deleteTable
INFO: DROP TABLE IF EXISTS tweets_favoriteCount;
jul 02, 2019 10:31:47 AM TweetRepository deleteTable
INFO: Finalizada
Tabelas deletadas

Process finished with exit code 0
