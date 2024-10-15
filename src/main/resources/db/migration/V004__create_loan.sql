CREATE TABLE loan
(
    isbn VARCHAR(64),
    email VARCHAR(64),
    loanDate date,
    returnDate date,

    PRIMARY KEY(isbn, email)
)
