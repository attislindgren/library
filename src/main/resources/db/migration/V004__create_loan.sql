CREATE TABLE loan
(
    isbn VARCHAR(64),
    firstName VARCHAR(64),
    loanDate date,
    returnDate date,

    PRIMARY KEY(isbn, firstName)
)
