

-- Створення таблиці books
CREATE TABLE books (
                       id BIGINT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       genre VARCHAR(255),
                       available BOOLEAN NOT NULL
);

-- Вставка даних в таблицю books
INSERT INTO books (id, title, author, genre, available) VALUES
                                                            (1, 'To Kill a Mockingbird', 'Harper Lee', 'Novel', true),
                                                            (2, '1984', 'George Orwell', 'Dystopian', true),
                                                            (3, 'Pride and Prejudice', 'Jane Austen', 'Romance', false),
                                                            (4, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', true),
                                                            (5, 'Moby-Dick', 'Herman Melville', 'Adventure', true);
